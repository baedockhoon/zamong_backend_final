<%@page import="com.zamong.me.service.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%-- <%
request.setCharacterEncoding("UTF-8");
//검색과 관련된 파라미터 받기]
//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
//1]파라미터(키값) 받기
	String no=request.getParameter("me_no");
	//현재 페이지번호 받기

	//2]CRUD작업용 BbsDAO생성
	MemberDAO dao = new MemberDAO(application);
	//조회수 업데이트

	//상세보기용 메소드 호출	
	MemberDTO dto = dao.selectOne(no);
	//이전글/다음글 조회
	//3]자원반납
	dao.close();


%> --%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />
<script>
function goPopup(bp_no,me_no) {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("<c:url value='/ZAMONG/Payment/View.do?bp_no="+bp_no+"&me_no="+me_no+"'/>", "pop",
			"width=700,height=600, scrollbars=yes, resizable=yes");

	
}
</script>


<title>공지사항</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
</head>


<body role="document">

	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->


		<hr />
		<div class="page-header">
			<h1>결제내역 보기</h1>
		</div>
		
		<div class="page-header">
			<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" ><a
					href="<c:url value='/ZAMONG/MemberList.do'/>">회원관리</a></li>
				<li role="presentation" class="active"><a
					href="<c:url value='/ZAMONG/Payment/List.do'/>">결제내역</a></li>
					
		<li role="presentation" ><a
					href="<c:url value='/ZAMONG/Streaming/List.do'/>">스트리밍</a></li>
				<li role="presentation"><a
					href="<c:url value='/ZAMONG/Product/List.do'/>">상품</a></li>
			</ul>
		</div>



		<hr />
		
		
		<div>
					
			<form method="post">
			<table class="table table-striped">
			<caption>이 표는 자몽캐쉬 충전 결제 리스트로 최종 결제금액, 결제방법 내용을 포함하고 있습니다.<hr/>
			
			회원님의 총금액을 확인 하시려면 아이디를 눌러주세요.
			</caption>
			
				<colgroup>
					<col style="width: 119px; *width: 103px;">
					<col style="width: 160px; *width: 144px;">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>아이디</th>
						<th>결제 날짜</th>
						<th>상품이름</th>
						<th>상품가격</th>
						
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
								<input type="hidden" name="me_no" value="${item.me_no }" />
							<tr bgcolor="white" align="center">
								<td align="left">${item.bp_no}</td>
			<%-- 				 <td><a
									href='<c:url value="/ZAMONG/Payment/View.do?bp_no=${item.bp_no}&me_no=${item.me_no }&nowPage=${nowPage }"/>'>${item.me_id}</a></td>	 --%>	
									<td align="left" onclick="goPopup(${item.bp_no},${item.me_no});"><a>${item.me_id}</a></td>				 
								<td align="left">${item.bp_regidate}</td>	
								<td align="left">${item.pd_name }</td>						
								<td align="left">${item.bp_price}원</td>
								

								<%-- <td><a href='javascript:isDelete(${item.nt_no})'><button
											type="button" class="btn btn-xs btn-primary">삭제</button></a></td> --%>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>


			</table>
			<select name="searchColumn">
					<option value="me_id">아이디</option>
				

				</select> &nbsp; <input type="text" id="tags" size="30" name="searchWord" /><input
					type="submit" value="검색" />
			</form>
			<table width="100%">
				<tr align="center">
					<td>${pagingString }</td>
				</tr>
			</table>
			<hr />





		</div>
	</div>
	<!-- /container(내용 끝) -->

<jsp:include page="/Template/Foot.jsp" />
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
	      <jsp:include page="/Template/autocomplete.jsp" />
</body>
</html>