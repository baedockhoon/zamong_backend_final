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
	
    <title>뮤직비디오</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<!-- jQuery선언 -->
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
	
	<script>
 		function FindArtistPopup() {
    	    window.open("${pageContext.request.contextPath}/bbs/zamongtv/FindArtistPopup.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=no,location=no,top=500,left=500,width=400,height=400");
    }
 		function FindSoundSourcePopup() {
    	    window.open("${pageContext.request.contextPath}/bbs/zamongtv/FindSoundSourcePopup.jsp", "_blank", "toolbar=yes,scrollbars=yes,resizable=no,location=no,top=500,left=500,width=400,height=400");
    	}
</script>
  </head>

  <body role="document">
	<div class="container theme-showcase" role="main">

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h3>뮤직어워드 후보등록하기</h3>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form action="${pageContext.request.contextPath}/ZAMONG/MusicAwardCandidatesPopupWrite.do" method="post" name="p_form">
				<table class="table table-striped" style="">
		            <tr>
              			<td>아티스트</td>
              			<td>
              			<input id="cInput" type="hidden" name="ma_no" value="${ma_no }">
              			<input id="p_txt" type="text"  name="artist" style="width:10%" required="required"/><a href="javascript:FindArtistPopup();"><button type="button" class="btn btn-default" id="assign">아티스트 찾기</button></a> ${artistnull }
              			</td>
		            </tr>
		            <tr>
              			<td>음원</td>
              			<td>
              			<input id="ss_txt" type="text"  name="soundSource" style="width:10%" required="required"/><a href="javascript:FindSoundSourcePopup();"><button type="button" class="btn btn-default" id="assign">음원 찾기</button></a> ${artistnull }
              			</td>
		            </tr>
		            
		            <tr>
		            	<td>어워드번호</td>
		            	<td colspan="2">${ma_no }</td>
		            </tr>

					<tr align="center">
					
					<td colspan="50">
					<button class="btn btn-sm btn-default" onclick="window.close()">취소</button>
<!-- 					<button class="btn btn-sm btn-info" type="submit" onclick="window.close()">입력</button> -->
					<a href="javascript:opener.location.reload();"><button class="btn btn-sm btn-info" type="submit">입력</button></a>
					
					</tr>				
				</table>
				</form>
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