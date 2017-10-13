package com.zamong.web.problem;

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
import com.zamong.pr.service.ProblemDTO;
import com.zamong.pr.service.impl.ProblemDAO;
import com.zamong.qu.service.UserQuestionDTO;
import com.zamong.qu.service.impl.UserQuestionDAO;


public class ProblemEditController extends HttpServlet {
	 
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getMethod().toUpperCase().equals("GET")){//수정폼으로 이동
			String no = req.getParameter("mq_no");
			String nowPage = req.getParameter("nowPage");
			//모델 호출 및 결과 값 받기]
			ProblemDAO dao = new ProblemDAO(req.getServletContext());
			ProblemDTO dto= dao.selectOne(no);
			dao.close();
			//리퀘스트 영역에 저장]
			req.setAttribute("dto", dto);
			//포워드]
			req.getRequestDispatcher("/bbs/notice/ProblemEdit.jsp").forward(req, resp);
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
			String no = req.getParameter("mq_no");	
			
			req.setAttribute("mq_no", no);
			req.setAttribute("nowPage", nowPage);
						
			String mq_useenv = req.getParameter("service_environment");
			String mq_os = req.getParameter("mq_os");
			String mq_browser = req.getParameter("mq_browser");
			String mq_uploadfile = req.getParameter("file");
			String title = req.getParameter("title");
			String mq_largedivide = req.getParameter("1");
			String mq_mediumdivide = req.getParameter("2");
			String contents= req.getParameter("contents");
			String tel = req.getParameter("tel");	
			String email = req.getParameter("email");
				String usemodel = req.getParameter("usemodel");
			//데이타베이스 CRUD작업과 관련된 모델 호출]
			ProblemDAO dao = new ProblemDAO(req.getServletContext());
			ProblemDTO dto = new ProblemDTO();
			dto.setMq_usemodel(usemodel);
			dto.setMq_browser(mq_browser);
			dto.setMq_contents(contents);
			dto.setMq_tel(tel);
			dto.setMq_email(email);
			dto.setMq_uploadfile(mq_uploadfile);
			dto.setMq_title(title);
			dto.setMq_os(mq_os);
			dto.setMq_useenv(mq_useenv);
			dto.setMq_largedivide(mq_largedivide);
			dto.setMq_mediumdivide(mq_mediumdivide);
			dto.setMq_no(no);
			
			
			
			
			sucorfail = dao.update(dto);
			
			dao.close();
			
	
		req.setAttribute("SUC_FAIL", sucorfail);
		//5-2]어느 컨트롤러에서 포워드 됬는지 판단용
		req.setAttribute("WHERE","EDT");
		//6]포워드
		req.getRequestDispatcher("/bbs/notice/ProblemMessage.jsp").forward(req, resp);
		}/////////else
			

	}////////////service

}///////////////NoticeEditController
