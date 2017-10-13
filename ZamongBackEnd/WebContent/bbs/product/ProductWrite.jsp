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

<title>공지사항</title>

	<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<c:url value='/Editor/js/HuskyEZCreator.js' />" charset="utf-8"></script>


<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">


 
 



</head>
<script>
$(function(){

	$("a").click(function(){
		if(this.id == "list"){	
			location.href ="<c:url value='/ZAMONG/Product/List.do'/>";
		} 
		else if(this.id == "insert"){
			location.href ="<c:url value='/ZAMONG/Product/List.do'/>";
		}
	});
});
	
	
</script>
<body role="document">

	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>자몽 매거진</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>매거진 작성</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<div>
				<form action="${pageContext.request.contextPath}/ZAMONG/Product/Write.do"
					method="post">
					
					<table class="table table-striped" style="">
						
						<tr>
							<td>상품 명</td>
							<td><input type="text" id="title" name="pd_name"
								style="width: 250px" required="required" /></td>
						</tr>
						<tr>
							<td>상품 가격</td>
							<td><input type="text" id="title" name="pd_price"
								style="width: 250px" required="required" />원</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" id="insert" value="입력"
								class="btn btn-sm btn-primary" />
								 <a id="list"
								class="btn btn-sm btn-primary">뒤로</a>
								</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- /container(내용 끝) -->
<jsp:include page="/Template/Foot.jsp" />

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"
		type="text/javascript"></script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>



</body>
</html>