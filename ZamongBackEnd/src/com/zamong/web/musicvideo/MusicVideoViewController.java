package com.zamong.web.musicvideo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mv.service.impl.MusicVideoDAO;
import com.zamong.mv.service.MusicVideoDTO;


public class MusicVideoViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String mv_no = req.getParameter("mv_no");
		//4]모델 호출 및 결과값 받기
		MusicVideoDAO dao = new MusicVideoDAO(req.getServletContext());
		MusicVideoDTO dto = dao.selectOne(mv_no);
		
		//줄바꿈 처리
		dto.setMv_contents(dto.getMv_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/bbs/zamongtv/MusicVideo/MusicvideoView.jsp").forward(req, resp);
		
		
	}/////////////////////service()
	
	
}
