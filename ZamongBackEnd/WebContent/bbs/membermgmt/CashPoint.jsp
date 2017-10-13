<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
String me_no = request.getParameter("me_no"); %>


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
				<h1>음원 결제</h1>
			</div>
			<input type="hidden" name="me_no" value="<%=me_no %>" />
			<div class="tableBox">
				<table class="table table-striped">
					 
					<tr>
						<th scope="row"><span class="star"></span><span
							class="txt dB lh30 alignLeft pL10 fl">${dto.me_id }님의 캐쉬 잔액은</span></th>
						<td class="end">
				<span style="color: red; font-size: 1.8em;">${dto.ch_havecash }</span>원 입니다.
						</td>
				</table>
			이 음원은 가격이 500캐쉬입니다. 구매하시려면 확인버튼을 눌러주세요.
			<hr/>
			구매하실경우 500캐쉬가 차감됩니다.
						
			</div>
			<%-- <div align="right">
				<a href="<c:url value='/ZAMONG/MemberList.do'/> " class="btn btn-sm btn-info">목록</a></td>
				<a href="<c:url value='/ZAMONG/MemberEdit.do?me_no=${dto.me_no}&nowPage=${nowPage }'/>" class="btn btn-sm btn-info">수정</a> 
				<a href='javascript:isDelete(${dto.me_no})'><button
									type="button" class="btn btn-sm btn-info">삭제</button></a>
			</div> --%>
	</div>
	

	
	<!-- /내용끝 -->
	
	
	
	
</body>
</html>