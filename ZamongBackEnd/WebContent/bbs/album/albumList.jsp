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

<title>앨범 리스트</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
</head>

<body role="document">

	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>
				<a href="<c:url value='/ZAMONG/Album/List.do'/>">음원 목록</a>
			</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>음원 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div class="page-header">
			<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active">
					<a href="<c:url value='/ZAMONG/Album/List.do?searchColumn='/>">통합검색</a>
				</li>
				<li role="presentation">
					<a href="<c:url value='/ZAMONG/Album/List.do?searchColumn=at'/>">아티스트</a>
				</li>
				<li role="presentation">
					<a href="<c:url value='/ZAMONG/ProblemList.do?searchColumn=ss'/>">곡</a>
				</li>
				<li role="presentation">
					<a href="<c:url value='/ZAMONG/ProblemList.do?searchColumn=al'/>">앨범</a>
				</li>
			</ul>
		</div>
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
							<td colspan="5">
								<a href="<c:url value='/ZAMONG/Album/Write.do'/>" class="btn btn-sm btn-info">음원 등록</a>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								앨범명으로 검색
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<th>곡명</th>
							<th>아티스트</th>
							<th>앨범</th>
							<th>좋아요</th>
						</tr>

					</thead>
					<c:choose>
						<c:when test="${empty album }">
							<tr bgcolor="white" align="center">
								<td colspan="6">등록된 자료가 없어요</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="alItem" items="${album }" varStatus="loop">
								<tr bgcolor="white" align="center">
									<td>${alItem.al_no}</td>
									<td>
										<a href='<c:url value="/ZAMONG/Sound/View.do?ss_no=${alItem.ss_no}&nowPage=${nowPage }"/>'>
										<c:if test="${alItem.ss_albumtitle eq 1 }" var="result">
											타이틀
										</c:if>
										${alItem.ss_title }
										</a>
									</td>
									<td>
										${alItem.al_artist } 
									</td>
									<td>
										<a href='<c:url value="/ZAMONG/Album/View.do?al_no=${alItem.al_no}&nowPage=${nowPage }"/>'>
											${alItem.al_albumname }
										</a>
									</td>
									<td>${empty alItem.ss_likecount ? "0" : alItem.ss_likecount }</td>

								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</table>
				<%-- <table width="100%">
					<tr align="center">
						<td>${alPageing }</td>
					</tr>
				</table> --%>
				
				<table class="table table-striped">
					<thead>
						<tr>
							<td colspan="5">
								곡명으로 검색
							</td>
						</tr>
						<tr>
							<th>번호</th>
							<th>곡명</th>
							<th>아티스트</th>
							<th>앨범</th>
							<th>좋아요</th>
						</tr>

					</thead>
					<c:choose>
						<c:when test="${empty sound }">
							<tr bgcolor="white" align="center">
								<td colspan="6">등록된 자료가 없어요</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="ssItem" items="${sound }" varStatus="loop">
								<tr bgcolor="white" align="center">
									<td>${ssItem.al_no}</td>
									<td>
										<a href='<c:url value="/ZAMONG/Sound/View.do?ss_no=${ssItem.ss_no}&nowPage=${nowPage }"/>'>
										<c:if test="${ssItem.ss_albumtitle == 1 }" var="result">
											타이틀 
										</c:if>
										${ssItem.ss_title }
										</a>
									</td>
									<td>
										${ssItem.al_artist } 
									</td>
									<td>
										<a href='<c:url value="/ZAMONG/Album/View.do?al_no=${ssItem.al_no}&nowPage=${nowPage }"/>'>
											${ssItem.al_albumname }
										</a>
									</td>
									<td>${empty ssItem.ss_likecount ? "0" : ssItem.ss_likecount }</td>
<td><a href='javascript:isDelete(${ssItem.ss_no })'><button
												type="button" class="btn btn-xs btn-primary">음원</button></a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</table>
				<%-- <table width="100%">
					<tr align="center">
						<td>${ssPageing }</td>
					</tr>
				</table> --%>
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
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	 <script>
		function isDelete(ss_no) {
			if (confirm("음원을 구매 하시겠습니까?")) {
		var pop = window.open("<c:url value='/ZAMONG/payment/UserList1.do?ss_no='/>"+ss_no, "pop",
		"width=700,height=600, scrollbars=yes, resizable=yes");
			}//////////////////			

		}/////////////////////	
		/* function isabc() {
			alert($("form").get(0));
			$("form").submit();
		} */
	</script>
	<script type="text/javascript">
		$(function(){
			//alert($(".page-header li:eq(0)").html());
			$("li").click(function(){
				//alert($(this).html());
				for(var i = 0; i < $(".page-header li").length; i++){
					$(".page-header li:eq("+i+")").removeClass("active");
				}
				$(this).addClass("active");
			});
		});
	</script>
</body>
</html>