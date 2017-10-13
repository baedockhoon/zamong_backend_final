<%@page import="java.util.List"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
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
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

<title>자몽 - 아티스트 리스트</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
</head>

<body role="document">

	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<%-- <div class="jumbotron">
			<h1>
				<a href="<c:url value='/ZAMONG/Artist/List.do'/>">가수 목록</a>
			</h1>
		</div> --%>
		<hr />

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>아티스트 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form method="post">
				<!--
				<select name="Notice_category" onchange="javascript:isabc();">
					<option value="">분류</option>
					<option value="서비스소식">서비스 소식</option>
					<option value="서비스오픈">서비스 오픈</option>
					<option value="서비스종료">서비스 종료</option>
					<option value="서비스점검">서비스 점검</option>
					<option value="안내">안내</option>
				</select>  
				-->
				<table class="table table-striped">
					<thead>
						<tr>
							<td colspan="6">
								<a href="<c:url value='/ZAMONG/Artist/Write.do'/>" class="btn btn-sm btn-info">아티스트 등록</a>
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<th colspan="2">가수/그룹명</th>
							<th>데뷔곡</th>
							<th>등록일</th>
							<th>좋아요</th>
						</tr>

					</thead>
					<c:choose>
						<c:when test="${empty list }">
							<tr bgcolor="white" align="center">
								<td colspan="6">등록된 자료가 없어요</td>
							</tr>
						</c:when>		
						<c:otherwise>
							<c:forEach var="item" items="${list}" varStatus="loop">
								<tr bgcolor="white" align="center">
									<td>${loop.count}</td>
									<td>
										<a href='<c:url value="/ZAMONG/Artist/View.do?"/>${empty item.at_no ? "gp_no="+=item.gp_no : "at_no="+=item.at_no }&nowPage=${nowPage }'>
										<img src="<c:url value='/Images/Artist/${item.at_image}'/>" style="width: 70px; height: 70px;"/>
										</a>
									</td>
									<td>
										${empty item.at_no ? "(그룹)" : ""} ${item.at_name} <br />
										${item.at_contry }/${item.at_gender }/${item.at_belong } 
									</td>
									<td>${item.at_debutsong }</td>
									<td>${item.at_regidate }</td>
									<td><a href="<c:url value='/ZAMONG/Like.do?me_no=16&lk_flag=at0000&lk_targetno=${item.at_no}'/>"  >좋아요 ${total}</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</table>
				<table width="100%">
					<tr align="center">
						<td>${pagingString }</td>
					</tr>
				</table>
				<hr />
				<select name="searchColumn">
					<option value="">검색</option>
					<option value="nt_title">제목</option>
					<option value="nt_contents">내용</option>
				</select> &nbsp; 
				<input type="text" size="30" name="searchWord" />
				<input type="submit" value="검색" />
			</form>
		</div>
	</div>
	
	<jsp:include page="/Template/Foot.jsp" />
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script> 

	function isDelete(nt_no){
		if(confirm("정말로 삭제 할래?")){
			location.href="NoticeDelete.do?nt_no="+nt_no;
		}//////////////////			
		
	}/////////////////////	
	function isabc(){
		alert($("form").get(0));
		$("form").submit();
	}
	
</script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
</body>
</html>