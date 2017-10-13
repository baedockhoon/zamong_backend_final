package com.zamong.web.musicaward;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.ca.service.CandidatesDTO;
import com.zamong.ca.service.impl.CandidatesDAO;
import com.zamong.ma.service.MusicAwardDTO;
import com.zamong.ma.service.impl.MusicAwardDAO;

public class CandidatesPopupWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//2]파라미터 받기
		String artist = req.getParameter("artist");
		String soundSource = req.getParameter("soundSource");
		String ma_no = req.getParameter("ma_no");
		
		//3]데이타를 전달할 DTO객체 생성및 데이타 설정
		CandidatesDAO dao = new CandidatesDAO(req.getServletContext());
		CandidatesDTO dto = new CandidatesDTO();
		dto.setAt_no(artist);
		dto.setSs_no(soundSource);
		dto.setMa_no(ma_no);
		//4]CRUD작업용 DAO계열 객체 생성
		int sucorfail;//DB입력 성공시에는 1,실패시 0
		sucorfail = dao.insert(dto);
		dao.close();
		
		
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
		req.setAttribute("WHERE", "INS");
		
		
		//req.getRequestDispatcher("/ZAMONG/MusicVideoList.do").forward(req, resp);
		req.getRequestDispatcher("/bbs/musicaward/candidates/CandidatesPopupMessage.jsp").forward(req, resp);
	}//////////////////doPost()
}
