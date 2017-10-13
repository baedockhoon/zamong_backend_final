<%@page import="com.zamong.bp.service.BuyproductDTO"%>
<%@page import="com.zamong.bp.service.impl.BuyproductDAO"%>
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

<title>결제</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
	
 <!-- 	<script>
	function goSubmit() {
	    window.opener.name = "parentPage"; // 부모창의 이름 설정
	    document.myForm.target = "parentPage"; // 타켓을 부모창으로 설정 
	    document.myForm.action = "<c:url value='/ZAMONG/Payment/Write.do'/>";
	    document.myForm.submit();
	    alert("충전성공");
	    self.close();
	}
	
	</script>  -->
</head>

<body>
	<div class="popup_title cash">
	<jsp:include page="/Template/Top.jsp" />

	<hr/>
	<hr/>
	<hr/>
		<div class="txt_title type01">
			<h1>자몽캐쉬 충전</h1>
			<p class="txt_title_info">자몽이용권, 곡, 뮤직비디오 등 결제 시 사용가능합니다.</p>
		</div>



	</div>


	<div class="popup_cntt box_scroll">
		<div class="tbl_style">
			<input type="hidden" name="bp_no"/>
				<input type="hidden" name="me_no"/>
			<table border="1" style="width: 100%" class="board_style03">
				<caption>이 표는 자몽캐쉬 충전 결제 리스트로 최종 결제금액, 결제방법 내용을 포함하고 있습니다.</caption>
				<colgroup>
					<col style="width: 119px; *width: 103px;">
					<col style="width: 160px; *width: 144px;">
					<col>
				</colgroup>
			<c:choose>
							<c:when test="${empty list }">
							<tr bgcolor="white" align="center">
								<td colspan="3">충전금액 :0원</td>
							</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${list}" varStatus="loop">
									<tr bgcolor="white" align="center">
										<th scope="row" class="bg_gray">충전금액 : ${item.bp_price }원</th>
															
					<td style="vertical-align: middle">
		 		<input type="submit" value="전환"/> 		 				 
						</td>
						<th scope="row" class="bg_gray">포인트 : ${item.bp_price }원</th>
						</tr>
								
								</c:forEach>
							</c:otherwise>
						</c:choose>
							
					
						
						
						
					
							
						
					
					
		
	</table>
	</div>

     

</div>
</body>
</html>