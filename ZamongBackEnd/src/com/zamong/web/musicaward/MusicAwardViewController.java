package com.zamong.web.musicaward;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ma.service.impl.MusicAwardDAO;
import com.zamong.ca.service.CandidatesDTO;
import com.zamong.ca.service.impl.CandidatesDAO;
import com.zamong.ma.service.MusicAwardDTO;


public class MusicAwardViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String ma_no = req.getParameter("ma_no");
		
		
		//뮤직어워드 전체리스트 dto에 담기
		MusicAwardDAO dao = new MusicAwardDAO(req.getServletContext());
		MusicAwardDTO dto = dao.selectOne(ma_no);
		//줄바꿈 처리
		dto.setMa_contents(dto.getMa_contents().replace("\r\n","<br/>"));
		dao.close();
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		
		
		//어워드에 맞는 후보리스트 candao에 담기
		CandidatesDAO candao = new CandidatesDAO(req.getServletContext());
		Map<String,Object> map = new HashMap<String,Object>();
		List<CandidatesDTO> canlist= candao.selectList(map,ma_no);
		candao.close();
		req.setAttribute("canlist", canlist);		
		
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/bbs/musicaward/MusicAwardView.jsp").forward(req, resp);
		
		//req.getRequestDispatcher("/ZAMONG/MusicAwardView.do?ma_no="+ma_no).forward(req, resp);
		
		
		
	}/////////////////////service()
	
	
}
