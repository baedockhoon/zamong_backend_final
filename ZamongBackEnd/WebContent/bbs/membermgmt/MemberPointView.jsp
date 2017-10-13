<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>



<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Image/자몽.jpg'/>" />
	
<title>DCND BackEnd 회원관리 페이지</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">

	
</head>


<body role="document">


	<!-- 고정된 네비바 -->

	<!-- 고정 네비바 끝 -->
	<div class="container theme-showcase" role="main">

	

		<!-- 실제내용의 제목표시 -->
	
			<!-- 실제 내용의 제목 표시 -->
			<div class="page-header">
				<h1>${dto.me_id }회원님의 결제정보</h1>
			</div>
			<div class="tableBox">
						
				<table class="table table-striped">
					<caption>총 포인트</caption>
					<colgroup>
						<col style="width: 25%;" />
						<col style="width: 75%;" />
					</colgroup>
					<tr>
						<th scope="row"><span class="star"></span><span
							class="txt dB lh30 alignLeft pL10 fl">${dto.me_id }님의 총금액</span></th>
						<td class="end">
						<span style="color: red; font-size: 1.8em;">${dto.bp_price }원 입니다.</span>
						</td>
				</table>
				
			</div>
			
	</div>
	

	
	<!-- /내용끝 -->
	<jsp:include page="/Template/Foot.jsp" />
	
	
	
</body>
</html>