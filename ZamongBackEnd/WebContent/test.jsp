<%@page import="com.zamong.me.service.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>"/>
    <title>회원관리 페이지</title>
  </head>
<body>	
	<div>
	<br/><br/><br/><br/><br/><br/><br/>
		<th><a href="<c:url value='/ZAMONG/Like.do?me_no=2&lk_flag=at_12&lk_targetno=2'/>">좋아요</a></th>
		<br/><br/>
		<br/><br/>
		<h2></h2><th><a href="<c:url value='/ZAMONG/Like.do?me_no=2&lk_flag=ss0000&lk_targetno=12'/>">음원 좋아요</a></th><h2/><br/><br/><br/>
		<h2></h2><th><a href="<c:url value='/ZAMONG/Like.do?me_no=2&lk_flag=al0000&lk_targetno=13'/>">앨범 좋아요</a></th><h2/><br/><br/><br/>
		<h2></h2><th><a href="<c:url value='/ZAMONG/Like.do?me_no=2&lk_flag=mv0000&lk_targetno=14'/>">뮤직비디오 좋아요</a></th><h2/>
		
		
		
	</div>	
</body>
</html>