package com.zamong.web.assign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.as.service.AssignDTO;
import com.zamong.as.service.impl.AssignDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class AssignController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//한글처리]
		req.setCharacterEncoding("UTF-8");	
		
		//오류시 입력값 보존을 위한 변수 선언]
		String starinput;
		String no;
		String date;
		String me_no;
		String al_no;
		
		int sucorfail;
		
		//3]요청분석- 입력처리 요청
		//4]모델호출 및 결과값 받기
		//파일 업로드와 관련된 모델(비지니스 로직) 호출
			//ad_no = req.getParameter("ad_no");
			//데이타베이스 CRUD작업과 관련된 모델 호출]
			starinput = req.getParameter("starinput");
			no = req.getParameter("no");
			me_no = req.getParameter("me_no");
			al_no = req.getParameter("al_no");
			date = req.getParameter("date");
			AssignDAO dao = new AssignDAO(req.getServletContext());
			AssignDTO dto = new AssignDTO();
			//dto.setAs_no(Integer.parseInt(no));
			//dto.setAs_regidate(date);
			//dto.setMe_no(me_no);
			//dto.setAl_no(al_no);
			dto.setAs_getpoint(starinput);
			
			
			sucorfail = dao.insert(dto);
			dao.close();
			
			req.setAttribute("SUC_FAIL", sucorfail);
			//5-2]컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
			req.setAttribute("WHERE", "INS");
			
			req.getRequestDispatcher("/bbs/assign/Assign_Message.jsp").forward(req, resp);
	}
	
	
}
