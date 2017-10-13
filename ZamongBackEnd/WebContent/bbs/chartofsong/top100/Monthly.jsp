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
	<link rel="icon" href="<c:url value='/Image/자몽.jpg'/>"/>
	
    <title>Top 100</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script>
	$(function(){
		$("#assign").click(function(){
			location.href="<c:url value='/bbs/chartofsong/assign.jsp'/>";
		});
	});
	</script>
  </head>

  <body role="document">

   <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>자몽차트</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation"><a href="<c:url value='/ZAMONG/Daily.do'/>">일간</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/Weekly.do'/>">주간</a></li>
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/Monthly.do'/>">월간</a></li>
      		</ul>
      	</div>

		<!-- 아래에 실제내용 표시 -->
		<div>
			<button type="button" class="btn btn-default" id="assign">음원 등록</button>
			<div>
				<table class="table table-striped">
				<thead>
					<tr>
						<th>NO</th>
						<th>음원</th>
						<th>가수</th>
						<th>앨범</th>
						<th>좋아요</th>
						<th>듣기</th>
						<th>담기</th>
						<th>다운</th>
						<th>뮤비</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>울보의 사랑</td>
						<td>란(RAN)</td>
						<td>울보의 사랑</td>
						<td>좋아요 48</td>
						<td><input type="checkbox" class="listen" checked="checked"/>듣기</td>
						<td><input type="checkbox" class="putlist" checked="checked"/>담기</td>
						<td><input type="checkbox" class="download" />다운</td>
						<td><input type="checkbox" class="musicvideo" />뮤비</td>
						<td><button type="button" class="btn btn-sm btn-info">저장</button></td>
					</tr>
					<tr>
						<td>2</td>
						<td>완벽한 사랑</td>
						<td>시와</td>
						<td>완벽한 사랑</td>
						<td>좋아요 51</td>
						<td><input type="checkbox" class="listen" checked="checked"/>듣기</td>
						<td><input type="checkbox" class="putlist" checked="checked"/>담기</td>
						<td><input type="checkbox" class="download" />다운</td>
						<td><input type="checkbox" class="musicvideo" />뮤비</td>
						<td><button type="button" class="btn btn-sm btn-info">저장</button></td>
					</tr>
					<tr>
						<td>3</td>
						<td>This night</td>
						<td>시우(XIU)</td>
						<td>This night</td>
						<td>좋아요 3</td>
						<td><input type="checkbox" class="listen" checked="checked"/>듣기</td>
						<td><input type="checkbox" class="putlist" checked="checked"/>담기</td>
						<td><input type="checkbox" class="download" />다운</td>
						<td><input type="checkbox" class="musicvideo" />뮤비</td>
						<td><button type="button" class="btn btn-sm btn-info">저장</button></td>
					</tr>
				</tbody>
			</table>
			</div>
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