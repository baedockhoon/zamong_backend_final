<%@page import="oracle.net.aso.r"%>
<%@page import="com.zamong.me.service.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
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
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>"/>
		
    <title>회원관리 페이지</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  </head>
 <script>
function goPopup(me_no) {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("<c:url value='/ZAMONG/Cash/View.do?me_no='/>"+me_no, "pop",
			"width=550,height=500, scrollbars=yes, resizable=yes");

	
}
</script>
<% String me_no = request.getParameter("me_no"); %> 
<script>
	function iswrite() {
		
		location.href="<c:url value='/bbs/payment/Payment.jsp'/>";
	}
function isList() {
		
		location.href="<c:url value='/ZAMONG/Payment/List.do?${bp_no}'/>";
	}	
</script>
<body role="document">
		
	<jsp:include page="/Template/Top.jsp" />
		
	<div class="container theme-showcase" role="main">
		
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>회원관리 페이지</h1>
		</div>
								
		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>회원 목록</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
<div class="page-header">
			<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a
					href="<c:url value='/ZAMONG/MemberList.do'/>">회원관리</a></li>
				<li role="presentation" ><a
					href="<c:url value='/ZAMONG/Payment/List.do'/>">결제내역</a></li>
					
			<li role="presentation" >
				<a href="<c:url value='/ZAMONG/Streaming/List.do'/>">스트리밍</a>
			</li>
			<%-- <li role="presentation" >
				<a href="<c:url value='/ZAMONG/Streaming/List.do'/>">스트리밍</a>
			</li> --%>
				<li role="presentation"><a
					href="<c:url value='/ZAMONG/Product/List.do'/>">상품</a></li>
			</ul>
		</div>
	<form method="post">
	
		<div class="search">
			&nbsp; &nbsp; <span>회원검색</span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="searchColumn">
				<option value="me_name">이름</option>
				<option value="me_id">아이디</option>
			</select> &nbsp; 
			<input type="text" size="55" name="searchWord" id="tags" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit"  class="btn btn-sm btn-info" value="검색"/>
			<a href="<c:url value='/ZAMONG/MemberList.do'/> " class="btn btn-sm btn-info">전체보기</a>
			<a class="btn btn-sm btn-info"
				href=<c:url value='/bbs/membermgmt/MemberRegister.jsp'/>>회원가입</a>
		</div>
	</form>
		<!-- 아래의 실제 내용 표시 -->
			<div>
			<input type="hidden" name="me_no"/>
				<table class="table table-striped">
				
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>휴대전화</th>
							<th>주소</th>
							<th>닉네임</th>
							<th>이메일</th>
							<th>등록일</th>
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
										<td>${item.me_no}</td>
										<td>${item.me_name}</td>
										<td><a href='<c:url value="/ZAMONG/MemberView.do?me_no=${item.me_no}&nowPage=${nowPage }"/>'>${item.me_id}</a></td>
										<td>${item.me_tel}</td>
										<td>${item.me_addr}</td>
										<td>${item.me_nickname}</td>
										<td>${item.me_email}</td>
										<td>${item.me_regidate}</td>
										<!-- <td><a class="btn btn-sm btn-primary" href="javascript:iswrite()">충전</a><td> -->
									  <td><input type="button" value="캐쉬잔액" class="btn btn-sm btn-primary" onclick="goPopup(${item.me_no});"/></td>  
									<%--  <td><a class="btn btn-sm btn-primary" href='<c:url value="/bbs/payment/Payment.jsp?me_no=${item.me_no }"/>'>충전</a></td> --%>   
									
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
			</div>
			
		</div>

	<script type="text/javascript">
	function isDelete(me_no){
		if(confirm("정말로 삭제 할래?")){
			location.href="MemberDelete.do?me_no="+me_no;
		}//////////////////			
		
	}/////////////////////	
	</script>
	
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
      <jsp:include page="/Template/autocomplete.jsp" />
</body>
</html>