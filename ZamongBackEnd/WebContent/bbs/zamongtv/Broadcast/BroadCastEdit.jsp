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
	
    <title>영상</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
	<script>
		function cancel() {
			if(confirm("취소시 내용을 모두 잃을 수 있습니다. 정말로 취소 하시겠습니까?")){
				history.back();
			}
		}
		
	</script>
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>영상</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>영상 게시물 수정하기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form action="${pageContext.request.contextPath}/ZAMONG/BroadCastEdit.do" method="post">
			<!-- 키값 -->
		       <input type="hidden" name="br_no" value="${dto.br_no}"/>번호 : ${dto.br_no}
				<table class="table table-striped" style="">
					<tr>
              			<td>아티스트</td>
              			<td>
              			<input type="text" name="at_no" style="width:10%" value="${dto.at_no }"/><button type="button" class="btn btn-default" id="assign">아티스트 찾기</button> 버튼클릭 후 아티스트 선택
              			</td>
		            </tr>
					<tr bgcolor="white">
              			<td>제목</td>
              			<td >
              			<input type="text" name="br_title" style="width:80%" value="${dto.br_title }"/>
              			</td>
		            </tr>
					<tr>
						<td>내용</td>
						<td><textarea cols="120" rows="20" name="br_contents">${dto.br_contents }</textarea>
						</td>
					</tr>
					<tr>
					<!-- <input type="hidden" name="ad_no" /> -->
						<td>링크</td>
						<td>
						 <input type="text"  name="br_link" style="width:80%" value='${dto.br_link }'/>
						</td>					
					</tr>
					<tr>
              			<td>프로그램명</td>
              			<td>
              			<input id="p_txt" type="text"  name="br_programname" style="width:10%" value='${dto.br_programname }'/><a href="javascript:FindArtistPopup();"><button type="button" class="btn btn-default" id="assign">프로그램명</button></a> ${artistnull }
              			</td>
		            </tr>
					<tr>
						<td>종방여부</td>
						<td>
						 <input type="text"  name="br_endof" style="width:80%" value='${dto.br_endof }'/>
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
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>