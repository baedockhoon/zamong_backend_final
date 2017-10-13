<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="../../Common/IsMember.jsp" %> --%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>"/>
	
    <title>뮤직어워드</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
	<!-- jQuery UI CSS파일 --> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
	<!-- jQuery 기본 js파일 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
	<!-- jQuery UI 라이브러리 js파일 -->
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
	<script>
		function cancel() {
			if(confirm("취소시 내용을 모두 잃을 수 있습니다. 정말로 취소 하시겠습니까?")){
				history.back();
			}
		}
		
	</script>
	
	<script>
	$(function() {
	    $( "#testDatepicker" ).datepicker({
	    	numberOfMonths: [1,2],
	         changeMonth: true, 
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         minDate: 0,
	         showButtonPanel: true, 
	         currentText: '오늘 날짜', 
	         closeText: '닫기', 
	         dateFormat: "yymmdd"
	  });
	});
</script>
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>뮤직어워드</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>뮤직어워드 게시물 수정하기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form action="${pageContext.request.contextPath}/ZAMONG/MusicAwardEdit.do" method="post">
			<!-- 키값 -->
		       <input type="hidden" name="ma_no" value="${dto.ma_no}"/>번호 : ${dto.ma_no}
				<table class="table table-striped" style="">
					<td>어워드 제목</td>
              			<td >
              			<input type="text"  name="title" style="width:80%" value="${dto.ma_title }"/>
              			</td>
		            </tr>
					<tr>
						<td>어워드 내용</td>
						<td><textarea cols="120" rows="20" name="contents">${dto.ma_contents }</textarea>
						</td>
					</tr>
					<tr>
					<!-- <input type="hidden" name="ad_no" /> -->
						<td>어워드 종료일자</td>
						<td>
						 <input type="text" name="endofday" style="width:10%" id="testDatepicker" value="${dto.ma_endofday }"/>
						 
						</td>					
					</tr>
					<tr align="center">
					<td colspan="50">
					<a href="javascript:cancel()">취소</a>
					<button class="btn btn-sm btn-info" type="submit">수정</button>
					</tr>				
				</table>
				</form>
		</div>
	</div>
	<!-- /container(내용 끝) -->
	<jsp:include page="/Template/Foot.jsp" />


	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>