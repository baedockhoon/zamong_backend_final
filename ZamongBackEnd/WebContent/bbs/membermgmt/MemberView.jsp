<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Image/자몽.jpg'/>" />
	
<title>DCND BackEnd 회원관리 페이지</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
<%	
	Calendar cal = Calendar.getInstance();
	
	request.setCharacterEncoding("UTF-8"); //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
	String inputYn = request.getParameter("inputYn");
	String roadAddrPart1 = request.getParameter("roadAddrPart1");
	String zipNo = request.getParameter("zipNo");
	String addrDetail = request.getParameter("addrDetail");

%>
	
</head>


<body role="document">


	<!-- 고정된 네비바 -->
	<jsp:include page="/Template/Top.jsp" />
	<!-- 고정 네비바 끝 -->
	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<!--  <div class="jumbotron">
        <h1>회원 관리 페이지</h1>
        
      </div> -->
		<div class="jumbotron">
			<h1>회원관리 페이지</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
	
			<!-- 실제 내용의 제목 표시 -->
			<div class="page-header">
				<h1>회원상세정보</h1>
			</div>
			<div class="tableBox">
				<table class="table table-striped">
					<caption>개인정보수정</caption>
					<colgroup>
						<col style="width: 25%;" />
						<col style="width: 75%;" />
					</colgroup>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">아이디</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine formRelateive">
								<span class="idInputBoxWrap">
									${dto.me_id }</span> <span class="fl pL10 dB btnInputBoxWrap"></span>
								<spanclass ="fl pL10dBbtnInputBoxWrap"> </span>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">이름</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								${dto.me_name }
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">생년월일</span></th>
						<td class="end">
								<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								${dto.me_birth }
							</div>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">성별확인</span></th>
						<td>
						<div>
							${dto.me_gender }
						</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">비밀번호</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								${dto.me_pass }
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span class="txt dB lh30 alignLeft pL10 fl">닉네임</span></th>
						<td class="end">
							<div class="soloSelectBoxTd soloSelectBoxTd03">
								${dto.me_nickname }
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">이메일</span></th>
						<td class="end">
							<div class="emailBoxWrap">
								${dto.me_email }
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span class="txt dB lh30 alignLeft pL10 fl">주소</span></th>
						<td class="end">
							<div class="fullBox">
								${ad_dto.ad_zip }${ad_dto.ad_basic_addr }${ad_dto.ad_detail_addr }
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span class="txt dB lh30 alignLeft pL10 fl">연락처</span></th>
						<td class="end">
							<div class="phoneBoxWrap">
								${dto.me_tel }
							</div>
						</td>
					</tr>
					<tr>
			         	<td>이미지를 넣어주세요</td>
			         	<div class="soloSelectBoxTd soloSelectBoxTd03">
			         	<td>${dto.me_photo }<td>
					</tr>
				</table>
			</div>
			<div align="right">
				<a href="<c:url value='/ZAMONG/MemberList.do'/> " class="btn btn-sm btn-info">목록</a></td>
				<a href="<c:url value='/ZAMONG/MemberEdit.do?me_no=${dto.me_no}&nowPage=${nowPage }'/>" class="btn btn-sm btn-info">수정</a> 
				<a href='javascript:isDelete(${dto.me_no})'><button
									type="button" class="btn btn-sm btn-info">삭제</button></a>
			</div>
	</div>
	
	
	<script type="text/javascript">
	function isDelete(me_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="MemberDelete.do?me_no="+me_no;
		}//////////////////			
		
	}/////////////////////	
	</script>
	
	<!-- /내용끝 -->
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value="" /> <input
			type="hidden" id="returnUrl" name="returnUrl" value="" /> <input
			type="hidden" id="resultType" name="resultType" value="" />
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
		<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
	</form>
	
	
	
	
</body>
</html>