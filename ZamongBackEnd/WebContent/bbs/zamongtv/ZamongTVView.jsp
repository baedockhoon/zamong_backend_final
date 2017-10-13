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
	
    <title>자몽TV</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
	<script> 
	function isDelete(tv_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="MusicVideoDelete.do?del_no="+tv_no;
		}//////////////////			
		
	}/////////////////////	
</script>
	
  </head>
  <body role="document">
    <jsp:include page="/Template/Top.jsp" />
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>자몽TV</h1>
		</div>
		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>자몽TV 게시글 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
				<input type="hidden" name="tv_no" value="${dto.tv_no}"/>번호 : ${dto.tv_no}
				<table class="table table-striped" style="">
					<tr>
              			<td>아티스트</td>
              			<td>
              			${dto.at_no }
              			</td>
		            </tr>
					<tr>
              			<td>제목</td>
              			<td >
              			${dto.tv_title }
              			</td>
		            </tr>
					<tr>
						<td>내용</td>
						<td>
						${dto.tv_contents }
						</td>
					</tr>
					<tr>
					<!-- <input type="hidden" name="ad_no" /> -->
					<td>링크</td>
					<td>
					 ${dto.tv_link }
					</td>					
					</tr>
					
					<tr align="center">
					<td colspan="50">
					<a href="<c:url value='/ZAMONG/ZamongTVEdit.do?tv_no=${dto.tv_no}'/>" id="update">수정</a> | 
					<a href='javascript:isDelete(${dto.tv_no})'>삭제</a> | 
					<a href="<c:url value='/ZAMONG/ZamongTVList.do'/>">목록</a>
					<button class="btn btn-sm btn-info" type="submit">입력</button>
					</tr>				
				</table>

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