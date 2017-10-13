<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after*  these tags -->
	<link rel="icon" href="<c:url value='../Images/zamonglogo.gif'/>"/>
	
    <title>Zamong BackEnd 페이지</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- Custom styles for this template -->
    <link href="<c:url value='/Css/Signin.css'/>" rel="stylesheet">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  </head>
  
<body >
<%-- <jsp:include page="/Template/Top.jsp" /> --%>
<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
		<div style="text-align: center;"><img src="${pageContext.request.contextPath}/Images/logo_melon142x99.png" alt="로고이미지"/></div><br/>
		<br/>
		<c:if test="${empty sessionScope.ADMIN_ID }" var="isNotMember">
			<h3 align="center">자몽 관리자로 로그인하세요</h3><br/>
		</c:if>
		<c:if test="${not isNotMember }">
			<h3 align="center">관리자계정 <font color="red">${sessionScope.ADMIN_ID }</font>로 로그인하셨습니다</h3><br/>
		</c:if>
			<div style="text-align: center;"><img src="${pageContext.request.contextPath}/Images/zamonglogo.gif" alt="로고이미지"/></div><br/>
		<!-- 아래에 실제내용 표시 -->
		<c:if test="${empty sessionScope.ADMIN_ID }" var="isNotMember">
		<form method="post" action="<c:url value='/ZAMONG/LoginProcess.do'/>" class="form-signin">
			<h4 class="form-signin-heading" align="center">관리자 로그인</h4>
			<label for="inputID" class="sr-only">아이디</label> 
			<input type="text" name="zamongadminid" id="id" class="form-control" placeholder="아이디" required autofocus> 
			<label for="inputPassword" class="sr-only">비밀번호</label> 
			<input type="password" name="zamongadminpass" id="inputPassword" class="form-control" placeholder="비밀번호" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
			
			 
			<span style="color:red;font-size:1.4em;font-weight: bold">${ERROR_MASSAGE }</span>
		</form>
		</c:if>
		<c:if test="${not isNotMember }">
		<div align="center"><a href="${pageContext.request.contextPath }/bbs/adminlogin/Logout.jsp"><strong>로그아웃</strong></a></div>
        </c:if>
	</div>
</div>
</body>
</html>
