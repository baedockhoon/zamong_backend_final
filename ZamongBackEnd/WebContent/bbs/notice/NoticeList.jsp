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

<title>공지사항</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">

	

</head>


<script>
	function iswrite() {
		
		location.href="<c:url value='/bbs/notice/NoticeWrite.jsp'/>";
	}
			
</script>






<body role="document">
	
  <jsp:include page="/Template/autocomplete.jsp" />
	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->


		<hr />
		<div class="page-header">
			<h1>공지사항 보기</h1>
		</div>
			<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/NoticeList.do'/>">공지사항</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">자주하는질문</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/ProblemList.do'/>">문의사항</a></li>
      		</ul>
      	</div>
		


		<hr />
		<div>

			<form method="post">
				<select name="Notice_category">
				
					<option value="서비스소식">서비스 소식</option>
					<option value="서비스오픈">서비스 오픈</option>
					<option value="서비스종료">서비스 종료</option>
					<option value="서비스점검">서비스 점검</option>
					<option value="안내">안내</option>
				</select> <input type="submit" value="검색" />
				<!-- </form> -->


				<div style="text-align: right">
				<a class="btn btn-sm btn-primary" href="javascript:iswrite()">글쓰기</a>
							</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>글번호</th>
							<th>분류</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>관리</th>
							
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
									<td>${item.nt_no}</td>
									<td>${item.nt_classification}</td>
									<td><a
										href='<c:url value="/ZAMONG/NoticeView.do?nt_no=${item.nt_no}&nowPage=${nowPage }"/>'>${item.nt_title}</a></td>
									<td>${item.nt_hitcount}</td>
									<td>${item.nt_regidate}</td>

									<td><a href='javascript:isDelete(${item.nt_no})'><button
												type="button" class="btn btn-xs btn-primary">삭제</button></a></td>

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
				<!-- <form action="" method="post"> -->
				
				<select name="searchColumn">
					<option value="nt_title">제목</option>
					<option value="nt_contents">내용</option>			
				</select> 
					&nbsp; <input type="text" id="tags" size="30" name="searchWord" /><input
						type="submit" value="검색" />
				</form>


		</div>
	</div>
	</body>
	<!-- /container(내용 끝) -->
<jsp:include page="/Template/Foot.jsp" />

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script> 

	function isDelete(nt_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="NoticeDelete.do?nt_no="+nt_no;
		}//////////////////			
		
	}/////////////////////	
	function isabc(){
		alert($("form").get(0));
		$("form").submit();
	}
	
</script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>



</html>