package com.zamong.web.userquestion;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;
import com.zamong.qu.service.UserQuestionDTO;
import com.zamong.qu.service.impl.UserQuestionDAO;


public class UserQuestionEditController extends HttpServlet {
	 
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String no = req.getParameter("qu_no");
			String nowPage = req.getParameter("nowPage");
			//모델 호출 및 결과 값 받기]
			UserQuestionDAO dao = new UserQuestionDAO(req.getServletContext());
			UserQuestionDTO dto= dao.selectOne(no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			req.getRequestDispatcher("/bbs/notice/UserQuestionEdit.jsp").forward(req, resp);
		}
		else{//수정처리-POST방식
			//한글처리]
			int sucorfail;
			
			req.setCharacterEncoding("UTF-8");
			//3]요청분석- 수정처리 요청
			//4]모델호출 및 결과값 받기
			//파일 업로드와 관련된 모델(비지니스 로직) 호출
			String nowPage = req.getParameter("nowPage");
			//수정성공:1,실패:0,용량초과:-1
			String no = req.getParameter("qu_no");	
			
			req.setAttribute("qu_no", no);
			req.setAttribute("nowPage", nowPage);
						
			String qu_largedivide = req.getParameter("qu_largedivide");
			String qu_mediumdivide = req.getParameter("qu_mediumdivide");
			String title = req.getParameter("title");		
			String content= req.getParameter("contents");
			//데이타베이스 CRUD작업과 관련된 모델 호출]
			UserQuestionDAO dao = new UserQuestionDAO(req.getServletContext());
			UserQuestionDTO dto = new UserQuestionDTO();
			dto.setQu_largedivide(qu_largedivide);
			dto.setQu_mediumdivide(qu_mediumdivide);
			dto.setQu_no(no);
			dto.setQu_title(title);
			dto.setQu_contents(content);
			sucorfail = dao.update(dto);
			
			dao.close();
			
	
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/notice/UserQuestionMessage.jsp").forward(req, resp);
		}/////////else
			

	}////////////service

}///////////////NoticeEditController
