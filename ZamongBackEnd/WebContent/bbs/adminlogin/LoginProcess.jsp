<%@page import="com.zamong.ad.service.AdminLoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- LoginProcess.jsp -->
<%

	//사용자 입력값 받기]
	String adminid = request.getParameter("user").trim();
	String adminpass = request.getParameter("pass").trim();

	//실제 테이블과 연동]
	AdminLoginDAO dao = new AdminLoginDAO(application);

	boolean flag=dao.isMember(adminid,adminpass);
	dao.close();
	if(flag){
		//1.로그인 처리]-세션영역에 속성 저장
		session.setAttribute("USER_ID", adminid);
		session.setAttribute("USER_PWD",adminpass);
		//2.로그인 처리후 마이 페이지로 이동]
		response.sendRedirect("../MainPage.jsp");
	}
	else{//비회원-다시 로그인 페이지로 포워드

		//리퀘스트 영역에 필요한 데이터 저장]
		request.setAttribute("ERROR_MASSAGE","아이디 및 비밀번호가 맞지않습니다. <br/> 다시 로그인해주세요");
		//로그인 페이지로 포워드]
		request.getRequestDispatcher("./Login.jsp").forward(request, response);		
		
	}
%>