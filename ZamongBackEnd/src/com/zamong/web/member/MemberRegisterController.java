package com.zamong.web.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.me.service.MemberDTO;
import com.zamong.me.service.impl.MemberDAO;

public class MemberRegisterController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		
			
		
		
			req.setCharacterEncoding("UTF-8");
			
			String me_id = req.getParameter("me_id");
			String me_pass = req.getParameter("me_pass");
			String me_check_pass = req.getParameter("me_check_pass");
			String me_name = req.getParameter("me_name");
			String me_nickname = req.getParameter("me_nickname");
			String me_gender = req.getParameter("me_gender");
			String me_birth = req.getParameter("me_birth");
			String me_email1 = req.getParameter("me_email1");
			String me_email2 = req.getParameter("me_email2");
			String me_email = me_email1+"@"+me_email2;
			String me_tel1 = req.getParameter("me_tel1");
			String me_tel2 = req.getParameter("me_tel2");
			String me_tel3 = req.getParameter("me_tel3");
			String me_tel = me_tel1+"-"+me_tel2+"-"+me_tel3;
			String me_photo	= req.getParameter("me_photo");
		
			
			MemberDTO dto = new MemberDTO();
			
		
			dto.setMe_birth(me_birth);
			dto.setMe_email(me_email);
			dto.setMe_gender(me_gender);
			dto.setMe_id(me_id);
			dto.setMe_name(me_name);
			dto.setMe_nickname(me_nickname);
			dto.setMe_pass(me_pass);
			dto.setMe_photo(me_photo);
			dto.setMe_tel(me_tel);
			
			req.setAttribute("dto", dto);
			
			MemberDAO dao = new MemberDAO(req.getServletContext());
			int affected = dao.insert(dto);
			dao.close();
			
			
			req.setAttribute("SUC_FAIL", affected);
			
			// 5-2]어느 컨트롤러에서 포워드 됬는지 판단용
			req.setAttribute("WHERE", "INS");
			// 6]포워드
			req.getRequestDispatcher("/bbs/membermgmt/MemberMessage.jsp").forward(req, resp);
			
			//req.getRequestDispatcher("/ZAMONG/MemberList.do").forward(req, resp);
		
	}
	
	
	
}



