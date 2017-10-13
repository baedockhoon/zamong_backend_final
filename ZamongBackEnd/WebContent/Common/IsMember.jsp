<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그인 여부 판단]
	if(session.getAttribute("USER_ID")==null){
		out.println("<script>");
		out.println("alert('로그인후 이용하세요')");
		out.println("location.replace('"+request.getContextPath()+"/bbs/adminlogin/Login.jsp');");
		out.println("</script>");	
		
	}
%>