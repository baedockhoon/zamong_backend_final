package com.zamong.web.problem;

import java.io.IOException;
import java.io.PrintWriter;

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


public class ProblemWriteController extends HttpServlet {
 
	String getStringValue(String value){
	      switch(value){
	         case "웹/웹플레이어":return "웹/웹플레이어";
	         case "Window플레이어":return "Window플레이어";
	         case "Mac플레이어":return "Mac플레이어";
	         case "모바일앱":return "모바일앱";
	         case "모바일웹":return "모바일웹";
	         default : return "MP3/휴대폰";
	      }
	   }
	String getStringVal(String value){
	      switch(value){
	         case "윈도우7":return "윈도우7";
	         case "윈도우8":return "윈도우8";
	         case "윈도우XP":return "윈도우XP";
	         default : return "기타";
	      }
	}
	  	String getStringVal1(String value){
		      switch(value){
		         case "인터넷익스플로어":return "인터넷익스플로어";
		         case "크롬":return "크롬";
		         case "사파리":return "사파리";
		         case "파이어폭스":return "파이어폭스";
		         default : return "기타";
		      }
	   }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/ZAMONG/ProblemWrite.jsp");
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		//한글처리]
				req.setCharacterEncoding("UTF-8");	
				
				//오류시 입력값 보존을 위한 변수 선언]
				int sucorfail;
		String mq_useenv;
		String mq_os;
		String mq_browser;
		String mq_uploadfile;
		String title;
		String mq_largedivide;
		String mq_mediumdivide;
		String contents;
		String tel1;
		String tel2;
		String tel3;
		String tel;
		String email1;
		String email2;
		String email;
		String usemodel;
		MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/Upload"));


		
		
		if(mr != null){
			mq_useenv = getStringValue(mr.getParameter("service_environment"));
			mq_os = getStringVal(mr.getParameter("mq_os"));
			mq_browser = getStringVal1(mr.getParameter("mq_browser"));
			mq_uploadfile = mr.getFilesystemName("file");
			title = mr.getParameter("title");
			mq_largedivide = mr.getParameter("1");
			mq_mediumdivide = mr.getParameter("2");
			contents = mr.getParameter("contents");
			tel1 = mr.getParameter("tel1");
			tel2 = mr.getParameter("tel2");
			tel3 = mr.getParameter("tel3");
			tel = tel1 + "-" + tel2 + "-" + tel3;
			email1 = mr.getParameter("email1");
			email2 = mr.getParameter("email2");
			email = email1 + "@" + email2;
			usemodel = mr.getParameter("usemodel");
			
			
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
					req.setAttribute("dto",dto);
					sucorfail =dao.insert(dto);
					
					dao.close();			  
		}
					else{
						sucorfail = -1;
					}//else
		req.setAttribute("SUC_FAIL", sucorfail);
		
		//5-2]컨트롤러 구분용-입력:INS, 수정:EDT, 삭제:DEL
		req.setAttribute("WHERE", "INS");
					
					if(sucorfail == 1){//입력성공
						//※입력후 바로 목록으로 이동]-반드시 List.jsp가 아닌 List.do로 이동
						req.getRequestDispatcher("/ZAMONG/ProblemList.do").forward(req, resp);
					}//if
					else{//입력실패 혹은 파일용량 초과
						req.setAttribute("errorMessage", sucorfail == 0 ? "입력 실패" : "파일용량 초과");
						
						
						resp.setContentType("text/html; charset=UTF-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('"+(sucorfail == 0 ? "입력실패" : "파일용량초과")+"')");
						out.println("history.back();");
						out.println("</script>");
						
					}//else
				
				/*
				  문제
				  자바스크립트로 입력 성공시에는 "입력성공"
				  				입력 실패시에는 "입력실패"
				  				용량초과시에는 "파일용량 초과"
				  				라는 메시지를 alert()로 띄운후
				  				입력 성공시에는 목록으로
				  				실패시(용량초과 포함)에는 다시
				  				입력페이지로 이동하여라
				 */
				 
				//메시지를 자스로 뿌려주는 페이지로 포워딩
			/*	req.getRequestDispatcher("/bbs/notice/ProblemMessage.jsp").forward(req, resp);*/
				
			}//doPost
	
}



