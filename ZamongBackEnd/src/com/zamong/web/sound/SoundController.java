package com.zamong.web.sound;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.oreilly.servlet.MultipartRequest;
import com.zamong.at.service.ArtistDTO;
import com.zamong.at.service.ArtistGropDTO;
import com.zamong.at.service.impl.ArtistDAO;
import com.zamong.at.service.impl.ArtistGropDAO;
import com.zamong.ss.service.SoundDTO;

import model.FileUtils;
import model.PagingUtil;

public class SoundController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		String mode = req.getMethod();

		if (url.toUpperCase().contains("WRITE.DO")) {
			req.setCharacterEncoding("UTF-8");
			SoundDTO dto = new SoundDTO();
			String saveDirectory = req.getServletContext().getRealPath("/Images/Sound");
			MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/Mp3"));
			int sucorfail = 0;// DB입력 성공시에는 1, 실패시0, 파일용량 초과시에는 -1

			if (mr != null) {// 파일 업로드 성공시 데이터 입력처리
				// 기타 파라미터 받아서 테이블에 입력처리
				String index = mr.getParameter("index");
				String fileName = mr.getFilesystemName("ss_name"+index);  
			    String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
			    int i = -1;
			    i = fileName.lastIndexOf("."); // 파일 확장자 위치
			    String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기

			    File oldFile = new File(req.getServletContext().getRealPath("/Mp3") + File.separator + fileName);
			    File newFile = new File(req.getServletContext().getRealPath("/Mp3") + File.separator + realFileName); 
			    
			    oldFile.renameTo(newFile); // 파일명 변경
				
				Mp3File mp3file;
				try {
					//mr.getFilesystemName("ss_name"+index)
					
					mp3file = new Mp3File(newFile);
					if (mp3file.hasId3v2Tag()) {
						ID3v2 id3v2Tag = mp3file.getId3v2Tag();
						
						/*int durationTime = Integer.parseInt(metadata.get("xmpDM:duration").substring(0, metadata.get("xmpDM:duration").indexOf(".")));
						System.out.println("곡재생시간 : "+metadata.get("xmpDM:duration"));
						System.out.println("곡재생시간(시분변환) : "+(durationTime/(1000*60))%60 + "분 "+(durationTime/1000)%(60)+"초");*/
						
						/*System.out.println("WmpRating: "+id3v2Tag.getWmpRating());
						System.out.println("Track: " + id3v2Tag.getTrack());
						System.out.println("Artist: " + id3v2Tag.getArtist());
						System.out.println("Title: " + id3v2Tag.getTitle());
						System.out.println("Album: " + id3v2Tag.getAlbum());
						System.out.println("Year: " + id3v2Tag.getYear());
						System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
						System.out.println("Comment: " + id3v2Tag.getComment());
						System.out.println("Lyrics: \r\n" + id3v2Tag.getLyrics());
						System.out.println("Composer: " + id3v2Tag.getComposer());
						System.out.println("Publisher: " + id3v2Tag.getPublisher());
						System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
						System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
						System.out.println("Copyright: " + id3v2Tag.getCopyright());
						System.out.println("URL: " + id3v2Tag.getUrl());
						System.out.println("Encoder: " + id3v2Tag.getEncoder());*/
						
						
						if (index.equals("0")) {
							//byte[] albumImageData = id3v2Tag.getAlbumImage();
							/*if (albumImageData != null) {
								System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
								System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
							}*/
							if (mp3file.hasId3v2Tag()) {
								  //ID3v2 id3v2Tag = mp3file.getId3v2Tag();
								  byte[] imageData = id3v2Tag.getAlbumImage();
								  
								  ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
								  BufferedImage bufferedImage = ImageIO.read(inputStream);
	
								  ImageIO.write(bufferedImage, "png", new File(saveDirectory + File.separator + realFileName + ".png")); //저장하고자 하는 파일 경로를 입력합니다.
	
								  sucorfail=1;
							}
						}
						String ss_title = id3v2Tag.getTitle();
						String ly_contents = id3v2Tag.getLyrics();
						String al_releasedate = id3v2Tag.getYear();
						if(al_releasedate != null && al_releasedate.length() == 8) {
							al_releasedate = al_releasedate.substring(0, 4)+"-"+al_releasedate.substring(4, 6)+"-"+al_releasedate.substring(6);
						}
						System.out.println(al_releasedate);
						String al_albumname = id3v2Tag.getAlbum();
						String ss_path = realFileName;

						/*if(ss_path == null){//파일 미교체
							ss_path = mr.getParameter("ss_path");
						}//if
*/						dto.setSs_title(ss_title);
						dto.setLy_contents(ly_contents);
						if (index.equals("0")) {
							dto.setAl_releasedate(al_releasedate);
							dto.setAl_albumname(al_albumname);
						}
						dto.setSs_path(ss_path);
						dto.setAl_albumimage(realFileName+".png");

						//DB 업데이트 성공 및 파일 교체시 기존 업로드된 파일 삭제
						/*if(sucorfail == 1 && mr.getFilesystemName("ss_name") != null)
							//기존 업로드된 파일 삭제
							FileUtils.deleteFile(req, "/Upload/Mp3", mr.getParameter("ss_path"));*/
					}
					req.setAttribute("index", index);
					req.setAttribute("dto", dto);
					req.getRequestDispatcher("/bbs/album/soungWrite.jsp").forward(req, resp);
					
					
					/*resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					String data;
					data = "<tr>";
					data += "<td colspan='2'><input type='checkbox' value='1' name='ss_albumtitle'>노래 대표곡</td>";
					data += "</tr>";
					data += "<tr>";
					data += "<td>노래 제목</td>";
					data += "<td id='ss_title'><input type='text' value='"+dto.getSs_title()+"' name='ss_title' /></td>";
					data += "</tr>";
					data += "<tr>";
					data += "<td>노래 장르</td>";
					data += "<td></td>";
					data += "</tr>";
					data += "<tr>";
					data += "<td>노래 가사</td>";
					data += "<td id='li_contents'><textarea cols='50' rows='30' name='li_contents'>"+dto.getLy_contents()+"</textarea></td>";
					data += "</tr>";
					out.println(data);*/
					
				} catch (UnsupportedTagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*ArtistDAO dao = new ArtistDAO(req.getServletContext());

				// 그룹정보

				req.setAttribute("dto", dto);
				sucorfail = dao.insert(dto);
				dao.close();*/

			} // if
			else {
				sucorfail = -1;
			} // else
			
			
			/*	// 5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
				// 5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
			req.setAttribute("SUC_FAIL", sucorfail);

			// 5-2]컨트롤러 구분용-입력:INS, 수정:EDT, 삭제:DEL
			req.setAttribute("WHERE", "INS");

			if (sucorfail == 1) {// 입력성공
				// ※입력후 바로 목록으로 이동]-반드시 List.jsp가 아닌 List.do로 이동
				// req.getRequestDispatcher("/DATAROOM/List.do").forward(req, resp);
				req.getRequestDispatcher("/ZAMONG/Album/List.do").forward(req, resp);
			} // if
			else {// 입력실패 혹은 파일용량 초과
				req.setAttribute("errorMessage", sucorfail == 0 ? "입력 실패" : "파일용량 초과");

				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('" + (sucorfail == 0 ? "입력실패" : "파일용량초과") + "')");
				out.println("history.back();");
				out.println("</script>");

			} // else
*/		} else if (url.toUpperCase().contains("LIST.DO")) {

			req.getRequestDispatcher("/bbs/album/albumList.jsp").forward(req, resp);
		}

	}

}
