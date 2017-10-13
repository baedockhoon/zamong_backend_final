package com.zamong.web.musicvideo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.mv.service.impl.MusicVideoDAO;
import com.zamong.mv.service.MusicVideoDTO;


public class MusicVideoEditController extends HttpServlet{
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석]
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String mv_no = req.getParameter("mv_no");
			//모델 호출 및 결과 값 받기]
			MusicVideoDAO dao = new MusicVideoDAO(req.getServletContext());
			MusicVideoDTO dto= dao.selectOne(mv_no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			
			req.getRequestDispatcher("/bbs/zamongtv/MusicVideo/MusicvideoEdit.jsp").forward(req, resp);
		}
	}/////////////////doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//3]요청분석- 수정처리 요청
		//4]모델호출 및 결과값 받기
		int sucorfail;
		String mv_no = req.getParameter("mv_no");
		String at_no = req.getParameter("at_no");
		String ss_no = req.getParameter("ss_no");
		String mv_title = req.getParameter("mv_title");
		String mv_contents = req.getParameter("mv_contents");
		String mv_link = req.getParameter("mv_link");
		
		int startNum=mv_link.indexOf("https://www.youtube.com/embed/")+30;
		String image ="https://img.youtube.com/vi/"+mv_link.substring(startNum,startNum+11)+"/0.jpg";
		
		//데이타베이스 CRUD작업과 관련된 모델 호출]
			MusicVideoDAO dao = new MusicVideoDAO(req.getServletContext());
			MusicVideoDTO dto = new MusicVideoDTO();
			dto.setMv_no(mv_no);
			dto.setAt_no(at_no);
			dto.setSs_no(ss_no);
			dto.setMv_title(mv_title);
			dto.setMv_contents(mv_contents);
			dto.setMv_link(mv_link);
			dto.setMv_image(image);
			
			sucorfail=dao.update(dto);

			dao.close();
		
		//5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
		req.setAttribute("mv_no", mv_no);
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/zamongtv/MusicVideo/MusicVideoMessage.jsp").forward(req, resp);
		}

}
