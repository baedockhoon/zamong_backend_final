package com.zamong.web.problem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//파일과 관련된 업무처리 로직 클래스
public class FileUtils {
	
	//파일 업로드 로직
	public static MultipartRequest upload(
			HttpServletRequest req,
			String saveDirectory){
		MultipartRequest mr = null;
		try{
			mr = new MultipartRequest(
					req,
					saveDirectory,
					1024 * 500,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
		}//try
		catch(Exception e){e.printStackTrace();}//catch
		return mr;
	}//upload
	
	//파일 삭제 로직
	public static void deleteFile(HttpServletRequest req, String directory, String original){
		//서버의 물리적 경로 얻기
		String saveDirectory = req.getServletContext().getRealPath(directory);
		
		//파일 객체 생성
		File file = new File(saveDirectory+File.separator+original);
		
		//파일 존재 여부 판단후 삭제
		if(file.exists()){
			file.delete();
		}//if
		
	}//deleteFile
	
	//파일 다운로드 로직
	public static void download(
			HttpServletRequest request,
			HttpServletResponse response,
			String directory,
			String filename
			){
		
		/*
			다운로드 원리
			1]웹브라우저가 인식하지 못하는 컨텐츠 타입을
			응답헤더에
			설정해주면 웹브라우저는 자체 다운로드 창을 띄운다
			2]서버에 저장된 파일을 출력스트림을 통해
			웹브라우저에
			출력한다
		*/
		
		//1]파라미터로 넘어오는 파일명 받기
		//2]파일이 저장된 서버의 물리적 경로 얻기
		String saveDirectory = request.getServletContext().getRealPath(directory);
		
		//3]파일 크기를 얻기 위한 파일 객체 생성
		//  다운로드시 프로그래스바를 표시하기 위함
		File file = new File(saveDirectory+File.separator+filename);
		long length = file.length();
		
		/*
			다운로드를 위한 응답헤더 설정
		4]다운로드창을 보여주기 위한 응답헤더 설정
		4-1]웹브라우저가 인식하지 못하는 컨텐츠타입(MIME)타입 설정
		*/
		response.setContentType("binary/octect-stream");
		
		//4-2]다운로드시 프로그래스바를 표기하기 위한
		//	  컨텐츠 길이 설정
		response.setContentLengthLong(length);
		
		/*
		4-3]응답헤더명:Content-Disposition
			응답헤더명에 따른 값:attachment;filename=파일명
			setHeader(응답헤더명,헤더값)으로 추가
			브라우저 종류에 따라 한글 파일명이 깨지는 경우가 있음으로
			브라우저별 인코딩 방식을 달리하자(파일명을 인코딩)
		*/
		boolean isIE = request.getHeader("user-agent").toUpperCase().indexOf("MSIE") != -1 ||
					   request.getHeader("user-agent").indexOf("11.0") != -1;
		try{
			if(isIE){//인터넷 익스프롤러
				filename = URLEncoder.encode(filename,"UTF-8");
			}//if
			else{//기타
				//new String(byte[] bytes, String charset)사용
				//1]파일명을 byte형 배열로 변환
				//2]String 클래스의 생성자에 변환한 배열과
				//  charset는 8859_1을 지정
				filename = new String(filename.getBytes("UTF-8"),"8859_1");
			}//else
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			//GET /JSPProject11/FileUpDown_12/Download.jsp?filename=JSP.txt HTTP/1.0
			/*
				IO작업을 통해서 서버에 있는 파일을 웹브라우저에 바로 출력
				
				데이터 소스:파일 -노드 스트림:FIleInputStream
						             필터 스트림:BufferedInputStream
				데이터 목적지:웹브라우저 -노드 스트림:response.getOutputStream()
				 				   	     필터 스트림:BufferedOutputStream
			*/
			//5]서버에 있는 파일에 연결할 입력 스트림 생성
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			
			//out.clear();
			//out = PageContext.pushBody();
			//6]웹브라우저에 연결할 출력 스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			//7]bis로 읽고 bos로 웹브라우저에 출력
			int data;
			while((data=bis.read()) != -1){
				bos.write(data);
				bos.flush();
			}//while
				
			//8]스트림 닫기
			bis.close();
			bos.close();
		}//try
		catch(Exception e){ e.printStackTrace(); };//catch
	}//download
	
	
	
	
}//class
