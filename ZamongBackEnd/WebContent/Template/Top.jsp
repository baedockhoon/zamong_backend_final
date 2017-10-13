<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" type="text/css" />
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
<!-- jQuery UI CSS파일 --> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<%--  네비바 삭제후 <jsp:include page="/Template/Top.jsp" /> 추가  --%>

<script>
	$(function (){
		$(".dropdown").click(function(){
			$(this).children(".dropdown-menu").toggle();
		});
	});
</script>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container"> 
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand">Zamong 관리자페이지</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/ZAMONG/Main.do'/>">홈</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">회원관리<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
				<li><a href="<c:url value='/ZAMONG/MemberList.do'/>">회원</a></li>
				<li><a href="<c:url value='/ZAMONG/Payment/List.do'/>">결제내역</a></li>
				<li><a href="<c:url value='/ZAMONG/Product/List.do'/>">상품테이블</a></li>
				</ul>
				</li>
				<li><a href="<c:url value='/ZAMONG/Artist/List.do'/>">아티스트</a></li>
				<%-- <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">음원관리
					<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value='/ZAMONG/Album/List.do'/>">앨범 / 음원 관리</a></li>
					</ul>
					</li> --%>
					<li><a href="<c:url value='/ZAMONG/Album/List.do'/>">음원관리</a></li>
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">자몽TV<span
						class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value='/ZAMONG/MusicVideoList.do'/>">뮤직비디오</a></li>
						<li><a href="<c:url value='/ZAMONG/BroadCastList.do'/>">방송</a></li>
					</ul></li>
				<li><a class="active"
					href="<c:url value='/ZAMONG/MusicAwardList.do'/>">뮤직어워드</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">매거진
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value='/ZAMONG/zamongMagazine.do'/>">자몽매거진</a></li>
						<li><a href="<c:url value='/ZAMONG/TodayMagazine.do'/>">오늘의 매거진</a></li>
					</ul></li>
				<li class="dropdown"><a
					href="<c:url value='#'/>"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-expanded="false">공지사항 <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						
						<li><a href="<c:url value='/ZAMONG/NoticeList.do'/>">공지사항
								목록</a></li>
						<li><a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">자주하는 질문</a></li>
						<li><a href="<c:url value='/ZAMONG/ProblemList.do'/>">문의사항</a></li>
					</ul></li>
				<li><a class="active"
					href="<c:url value='/ZAMONG/Statistics.do'/>">통계</a></li>
					
					<li><a class="active"
					href="<c:url value='/ZAMONG/zamongAssignList.do'/>">평점</a></li>
					<!-- BackEnd 로그인처리 -->					

			       <c:if test="${empty sessionScope.ADMIN_ID }" var="isNotMember">
			       		<li><a href="<c:url value='/ZAMONG/Login.do'/>">관리자 로그인</a></li>
        		   </c:if>
				   <c:if test="${not isNotMember }">
				   		<li><a href="<c:url value='/ZAMONG/Logout.do'/>">로그아웃</a></li>
        		   		<%-- <li><a href="${pageContext.request.contextPath }/bbs/adminlogin/Logout.jsp">로그아웃</a></li> --%>
       			   </c:if>
			       <li></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<!-- 고정된 nav바 끝 -->

