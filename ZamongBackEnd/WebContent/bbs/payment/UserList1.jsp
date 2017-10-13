<%@page import="com.zamong.bp.service.BuyproductDTO"%>
<%@page import="com.zamong.bp.service.impl.BuyproductDAO"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String pd_no = request.getParameter("ss_no");
String me_no = request.getParameter("me_no");
%>
<!DOCTYPE html>
<html>
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

<title>결제 페이지</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<script>
/* 	function goSubmit() {
	    window.opener.name = "parentPage"; // 부모창의 이름 설정
	    document.myForm.target = "parentPage"; // 타켓을 부모창으로 설정 
	    document.myForm.action = "<c:url value='/ZAMONG/Payment/Write.do'/>";
	    document.myForm.submit();
	    alert("충전성공");
	    self.close();
	}
	 */
	 function goPopup(ss_no, me_no) {
			// 주소검색을 수행할 팝업 페이지를 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open("<c:url value='/ZAMONG/Cash/View.do?ss_no="+ss_no+"&me_no="+me_no+"'/>", "pop",
					"width=700,height=600, scrollbars=yes, resizable=yes");

			
		}
	</script>  
</head>

<body>
	<div class="popup_title cash">


		<div class="txt_title type01">
			<h1>구매 내역</h1>
			<p class="txt_title_info">자몽이용권, 곡, 뮤직비디오 등 결제 시 사용가능합니다.</p>
		</div>



	</div>


	<div class="popup_cntt box_scroll">	
	 <input type="hidden" name="pd_no" value="<%=pd_no%>"/>
	 <input type="hidden" name="me_no" value="<%=me_no%>"/>
		<div class="tbl_style">
				
				<table class="table table-striped">
				
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
						
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
										<td>${item.me_id}</td>
									
								
				
								
										<!-- <td><a class="btn btn-sm btn-primary" href="javascript:iswrite()">충전</a><td> -->
									  <td><input type="button" value="구매" class="btn btn-sm btn-primary" onclick="goPopup(${ss_no},${item.me_no});"/></td>  	
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</table>
					
			
			<div class="popup_cntt box_scroll" style="text-align: center;">
			 	 <input type="button" onclick="goSubmit()" value="확인" /> 
<!-- 		 <input type="submit" value="확인"/>    --> 
			</div>

		</div>
	 	 
	</div>
<script>
function selChange(val){
    if(val=="sel"){
         $(".boxEmailEndInput > input").val("");
       }else{
         $(".boxEmailEndInput > input").val(val); 
       }
    
}
</script>

</body>
</html>