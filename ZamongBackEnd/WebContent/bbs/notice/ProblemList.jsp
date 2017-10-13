<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglib.tld" %>
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
		
		location.href="<c:url value='/bbs/notice/ProblemWrite.jsp'/>";
	}
			
</script>
<body role="document">

	<jsp:include page="/Template/Top.jsp" />



	<div class="container theme-showcase" role="main">

		<hr />
		<div class="page-header">
			<h1>질문 목록 보기</h1>
		</div>
			<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation"><a href="<c:url value='/ZAMONG/NoticeList.do'/>">공지사항</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">자주하는질문</a></li>
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/ProblemList.do'/>">문의사항</a></li>
      		</ul>
      	</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
		<div style="text-align: right">
				<a class="btn btn-sm btn-primary" href="javascript:iswrite()">글쓰기</a>
							</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>순서</th>
						<th>대분류</th>
						<th>중분류</th>
						<th>제목</th>
						<th>상태</th>
						<th>첨부파일</th>
						<th>등록일</th>
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
								<td>${item.mq_no}</td>
								<td>${item.mq_largedivide}</td>
								<td>${item.mq_mediumdivide}</td>
								<td><a
									href='<c:url value="/ZAMONG/ProblemView.do?mq_no=${item.mq_no}&nowPage=${nowPage }"/>'>${item.mq_title}</a></td>
								<td>${item.mq_status}</td>
								     <td><a href='<c:url value="/ZAMONG/Download.do?filename=${my:urlEncoding(item.mq_uploadfile) }&mq_no=${item.mq_no}"/>'>${item.mq_uploadfile}</a></td>
								<td>${item.mq_regidate}</td>
								<td><a href='javascript:isDelete(${item.mq_no})'><button
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
			<!-- /container(내용 끝) -->
			<jsp:include page="/Template/Foot.jsp" />
		</div>
	</div>

	<script>
function isDelete(mq_no){
	if(confirm("정말로 삭제 하시겠습니까?")){
		location.href="ProblemDelete.do?mq_no="+mq_no;
	}//////////////////			
	
}/////////////////////	

</script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>

</body>

</html>