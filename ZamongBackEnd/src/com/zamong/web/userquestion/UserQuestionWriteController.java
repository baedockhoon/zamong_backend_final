package com.zamong.web.userquestion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;
import com.zamong.qu.service.UserQuestionDTO;
import com.zamong.qu.service.impl.UserQuestionDAO;


public class UserQuestionWriteController extends HttpServlet {
 
	String getStringValue(String value){
	      switch(value){
	         case "서비스문의/오류":return "서비스문의/오류";
	         case "결제/해지/환불":return "결제/해지/환불";
	         case "이벤트":return "이벤트";
	         case "이용권 사용문의":return "이용권 사용문의";
	         default : return "회원정보";
	      }
	   }
	String getStringVal(String value){
	      switch(value){
	         case "Windows 플레이어":return "Windows 플레이어";
	         case "재생/다운로드":return "재생/다운로드";
	         case "멜론설치/지원가기":return "멜론설치/지원가기";
	         case "이용권 사용문의":return "이용권 사용문의";
	         case "회원탈퇴":return "회원탈퇴";
	         case "결제방법":return "결제방법";
	         default : return "이벤트";
	      }
	   }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/ZAMONG/NoticeWrite.jsp");
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		//한글처리]
				req.setCharacterEncoding("UTF-8");	
				
				//오류시 입력값 보존을 위한 변수 선언]
			
				String qu_largedivide;
				String qu_mediumdivide;
				String title;
				String content;
			//	String hitcount;
				//3]요청분석- 입력처리 요청
				//4]모델호출 및 결과값 받기
				//파일 업로드와 관련된 모델(비지니스 로직) 호출
					//ad_no = req.getParameter("ad_no");
					qu_largedivide = getStringValue(req.getParameter("qu_largedivide"));
					qu_mediumdivide = getStringVal(req.getParameter("qu_mediumdivide"));
					title = req.getParameter("title");
					content= req.getParameter("contents");
				//	hitcount = req.getParameter("hitcount");
				
					//데이타베이스 CRUD작업과 관련된 모델 호출]
					UserQuestionDAO dao = new UserQuestionDAO(req.getServletContext());
					UserQuestionDTO dto = new UserQuestionDTO();
		
					dto.setQu_largedivide(qu_largedivide);
					dto.setQu_mediumdivide(qu_mediumdivide);
					dto.setQu_title(title);
					dto.setQu_contents(content);
					
				
					
					
					dao.insert(dto);
			
					dao.close();			  
					req.getRequestDispatcher("/ZAMONG/UserQuestionList.do").forward(req, resp);
	}
}



