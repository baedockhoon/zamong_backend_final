<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>자몽 - 아티스트 상세보기</title>
	<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
</head>

<body role="document">
	<jsp:include page="/Template/Top.jsp" />
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>아티스트 상세보기</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>아티스트 상세보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form id="frm" action="<c:url value='/ZAMONG/Artist/Write.do'/>" method="post" enctype="multipart/form-data">
				<table class="table table-striped" >
					<tr>
						<td>그룹여부</td>
						<td>
							
							<c:if test='${group eq "G" }'>
								그룹
							</c:if>
							<c:if test='${group eq "A" }'>
								솔로
							</c:if>
						</td>
					</tr>
				</table>
				<c:if test='${group eq "G" }'>
					<div  class="table table-striped" id="grop_info">
						<table class="table table-striped">
							<tr>
								<td>그룹명</td>
								<td>
									${dto.gp_name }
								</td>
							</tr>
							<tr>
								<td>그룹 성별</td>
								<td>
									${dto.gp_gender }
								</td>
							</tr>
							<tr>
								<td>소속사</td>
								<td>
									${dto.gp_belong }
								</td>
							</tr>
							<tr>
								<td>데뷔일</td>
								<td>
									${dto.gp_debutdate }
								</td>
							</tr>
							<tr>
								<td>그룹 타이틀 이미지</td>
								<td>
									<img src="<c:url value='/Images/Artist/${dto.gp_image }' />" />
								</td>
							</tr>
							<!-- <tr bgcolor="white" align="center">
								<td colspan="2">
									<a herf="#" id="artist_next" class="btn btn-sm btn-info">다음</a>
								</td>
							</tr> -->
						</table>
					</div>
				</c:if>
				<div class="table table-striped" id="artist_info">
					<c:if test="${group eq 'G' }" var="result">
						<c:forEach var="item" items="${list }">
							<table class="table table-striped">
						<tr class="artist_info">
							<td>아티스트 명</td>
							<td>
								${item.at_name }
							</td>
						</tr>
						<tr>
							<td>소속사</td>
							<td>
								${item.at_belong }
							</td>
						</tr>
						<tr>
							<td>데뷔일</td>
							<td>
								${item.at_debutdate }
							</td>
						</tr>
						<tr>
							<td>데뷔곡</td>
							<td>
								${item.at_debutsong }
							</td>
						</tr>
						<tr>
							<td>생일</td>
							<td>
								${item.at_birth }
							</td>
						</tr>
						<tr>
							<td>국적</td>
							<td>
								${item.at_contry }
							</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>
								${item.at_gender eq "M" ? "남성" : "여성" }
							</td>
						</tr>
						<tr>
							<td>아티스트 정보</td>
							<td>
								${item.at_artistinfo }
							</td>
						</tr>
						<tr>
							<td>아티스트 프로필 사진</td>
							<td>
								<img width="250px" height="250px" src="<c:url value='/Images/Artist/${item.at_image }' />" />
							</td>
						</tr>
					</table>
						</c:forEach>
					</c:if>
					<c:if test="${not result }">
						<table class="table table-striped">
							<tr class="artist_info">
								<td>아티스트 명</td>
								<td>
									${dto.at_name }
								</td>
							</tr>
							<tr>
								<td>소속사</td>
								<td>
									${dto.at_belong }
								</td>
							</tr>
							<tr>
								<td>데뷔일</td>
								<td>
									${dto.at_debutdate }
								</td>
							</tr>
							<tr>
								<td>데뷔곡</td>
								<td>
									${dto.at_debutsong }
								</td>
							</tr>
							<tr>
								<td>생일</td>
								<td>
									${dto.at_birth }
								</td>
							</tr>
							<tr>
								<td>국적</td>
								<td>
									${dto.at_contry }
								</td>
							</tr>
							<tr>
								<td>성별</td>
								<td>
									${dto.at_gender eq "M" ? "남성" : "여성" }
								</td>
							</tr>
							<tr>
								<td>아티스트 정보</td>
								<td>
									${dto.at_artistinfo }
								</td>
							</tr>
							<tr>
								<td>아티스트 프로필 사진</td>
								<td>
									<img width="250px" height="250px" src="<c:url value='/Images/Artist/${dto.at_image }' />" />
								</td>
							</tr>
						</table>
					</c:if>
				</div>
				<div class="table table-striped" style="text-align: -webkit-center">
					<button type="button" id="back" class="btn btn-sm btn-info">목록</button>
					<input type="submit" id="artist_submit" class="btn btn-sm btn-info" value="수정">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/Template/Foot.jsp" />
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
<script>
$(function (){
	
	$("#back").click(function(){
		history.back();
	})
})
</script>	
</body>
</html>