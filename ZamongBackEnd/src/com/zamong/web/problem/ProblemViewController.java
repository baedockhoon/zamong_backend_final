package com.zamong.web.problem;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;
import com.zamong.pr.service.ProblemDTO;
import com.zamong.pr.service.impl.ProblemDAO;
import com.zamong.qu.service.UserQuestionDTO;
import com.zamong.qu.service.impl.UserQuestionDAO;


public class ProblemViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("mq_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		ProblemDAO dao = new ProblemDAO(req.getServletContext());
		dao.updateVisitCount(no);
		ProblemDTO dto = dao.selectOne(no);
	
		//줄바꿈 처리

		dto.setMq_contents(dto.getMq_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("nowPage", nowPage);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/notice/ProblemView.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
}



