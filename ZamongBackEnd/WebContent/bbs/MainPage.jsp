<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="/Common/IsMember.jsp" %> --%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

    <title>DCND BackEnd 페이지</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">

  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>메인 페이지</h1> 
			<h3> - 관리대상 전체가 한눈에 보이게 아래 표시</h3>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1><font color="red">${sessionScope.ADMIN_ID }</font> 관리자님 환영합니다.</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<br/>
		<br/>
		<div>
			<legend>
					<h2>회원관리 간략페이지 표시</h2>
			</legend>
			<legend>
					<h2>자몽차트 - top100 표시</h2>
			</legend>
			<legend>
					<h2>자몽차트 - 시대별 차트 표시</h2>
			</legend>
			<legend>
					<h2>최신 표시</h2>
			</legend>
			<legend>
					<h2>장르 표시</h2>
			</legend>
			<legend>
					<h2>자몽TV - 뮤직비디오 표시</h2>
			</legend>
			<legend>
					<h2>자몽TV - 방송 표시</h2>
			</legend>
			<legend>
					<h2>뮤직어워드 표시</h2>
			</legend>
			<legend>
					<h2>매거진 표시</h2>
			</legend>
			<legend>
					<h2>공지사항 표시</h2>
			</legend>
			
			
		</div>
	</div>
	<!-- /container(내용 끝) -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>