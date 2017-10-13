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
		
		location.href="<c:url value='/bbs/product/ProductWrite.jsp'/>";
	}
	
	/* function goPopup(pd_no) {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("<c:url value='/bbs/product/Product.jsp?pd_no='/>"+pd_no, "pop",
				"width=700,height=600, scrollbars=yes, resizable=yes");

		
	}		  */
	 function goPopup(pd_no) {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("<c:url value='/bbs/payment/Payment.jsp?pd_no='/>"+pd_no, "pop",
				"width=700,height=600, scrollbars=yes, resizable=yes");
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
				<li role="presentation" ><a
					href="<c:url value='/ZAMONG/MemberList.do'/>">회원관리</a></li>
				<li role="presentation" ><a
					href="<c:url value='/ZAMONG/Payment/List.do'/>">결제내역</a></li>					
				<li role="presentation" class="active"><a
					href="<c:url value='/bbs/product/Producttest.jsp'/>">스트리밍</a></li>
				<li role="presentation"><a
					href="<c:url value='/ZAMONG/Product/List.do'/>">상품</a></li>
			</ul>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
		<div style="text-align: right">
				<a class="btn btn-sm btn-primary" href="javascript:iswrite()">상품 등록</a>
							</div>
					
					
						
				 <table style="border-spacing:2px; width:600px;"border="1">
				 <c:choose>
					<c:when test="${empty list }">
						<tr bgcolor="white" align="center">
							<td colspan="6">등록된 자료가 없어요</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${list}" varStatus="loop">
							    <tr style="text-align:left; height:90px">
								   <th style="text-align: center;">&nbsp;${item.pd_no }&nbsp;</th>
								    <th style="text-align: center;">등록일:  &nbsp;&nbsp;${item.pd_regidate }</th>
                		<th colspan="4" style="text-align: center;"> ${item.pd_name } : 정기 결제   &nbsp;&nbsp;&nbsp; ${item.pd_price }원 
                	
                		</th>
                		 <td align="center"><input type="button" value="구매" class="btn btn-sm btn-primary" onclick="goPopup(${item.pd_no});"/></td>  
								
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
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