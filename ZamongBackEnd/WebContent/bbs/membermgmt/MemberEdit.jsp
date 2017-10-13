<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("<c:url value='/bbs/popup/jusoPopup.jsp'/>", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadAddrPart1, addrDetail, zipNo) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.addrDetail.value = addrDetail;
		document.form.zipNo.value = zipNo;
	}
	
	
	 $(function() {
		    $( "#testDatepicker" ).datepicker({
		    	numberOfMonths: [1,1],
		         changeyear: true, 
		         changeMonth: true, 
		         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
		         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
		         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		         showButtonPanel: true, 
		         currentText: '오늘 날짜', 
		         closeText: '닫기', 
		         dateFormat: "yy-mm-dd",
		         
		  });
		});
</script>
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
	/* String inputYn = request.getParameter("inputYn");
	String roadAddrPart1 = request.getParameter("roadAddrPart1");
	String zipNo = request.getParameter("zipNo");
	String addrDetail = request.getParameter("addrDetail"); */
%>


</head>
<body role="document">
	<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
	<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
	<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->


	<!-- 고정된 네비바 -->
	<jsp:include page="/Template/Top.jsp" />
	<!-- 고정 네비바 끝 -->
	<script type="text/javascript">
		var pwdok = false;
		function pwdOK() {
			var pwd = $("#password").val();
			var enPassword = $("#enPassword").val();

			if (pwd != enPassword) {
				$("#pwdOk").get(0).innerHTML = "비밀번호가 일치하지 않습니다";
			} else {
				$("#pwdOk").get(0).innerHTML = "";
				pwdok = true;
			}
		}
	</script>

	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h1>회원관리 페이지</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<form name="form" id="form" method="post" action="${pageContext.request.contextPath}/ZAMONG/MemberEdit.do">
         <!-- 실제 내용의 제목 표시 -->
         <input type="hidden" name="me_no" value="${dto.me_no}"/>
          <input type="hidden" name="nowPage" value="${nowPage}"/>
			<!-- 실제 내용의 제목 표시 -->
			<div class="page-header">
				<h1>회원가입</h1>
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
								<span class="idInputBoxWrap"><input name="me_id"
									type="text" value="${dto.me_id }" /></span> <span class="fl pL10 dB btnInputBoxWrap"></span>
								<span class="fl pL10dBbtnInputBoxWrap"> </span>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">이름</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								<input name="me_name" type="text" value="${dto.me_name }" />
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">생년월일</span></th>
							<td><input type="text" name="me_birth" id="testDatepicker" value="${dto.me_birth }"></td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">성별확인</span></th>
						<td>
							<div>
							<c:choose>
							<c:when test="${me_gender== 'M' }">
								<span><input type="radio" name="me_gender" value="M" checked="checked" />남</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="me_gender" value="F"/>여</span>
							</c:when>
							<c:otherwise>
								<input type="radio" name="me_gender" value="M"/>남</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<span><input type="radio" name="me_gender" value="F" checked="checked"/>여</span>
							</c:otherwise>
							</c:choose>
							</div>
							
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">비밀번호</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								<input id="password" name="me_pass" title="비밀번호입력란"
									type="password" value="${dto.me_pass }" />
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span> <span
							class="txt dB lh30 alignLeft pL10 fl">비밀번호 확인</span></th>
						<td class="end">
							<div class="divDefine fl inputBoxDefine idInputBoxWrap1">
								<input id="enPassword" name="me_check_pass" title="비밀번호확인입력란"
									type="password" onkeyup="javascript:pwdOK()" value="${dto.me_pass }" /> <span
									id="pwdOk" style="color: red; font-weight: 600;"></span>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">닉네임</span></th>
						<td class="end">
							<div class="soloSelectBoxTd soloSelectBoxTd03">
								<input type="text" name="me_nickname" value="${dto.me_nickname }"/>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="star">*</span><span
							class="txt dB lh30 alignLeft pL10 fl">이메일</span></th>
						<td class="end">
							<div class="emailBoxWrap">
								<span class="boxEmailStart"><input id="email"
									name="me_email1" title="이메일 앞자리" type="text" value="${me_email1 }" /></span> <span
									class="boxEmailMool">@</span> <span class="boxEmailEndInput"><input
									id="emailNm" name="me_email2" title="이메일 앞자리" type="text"
									value="${me_email2 }" /></span>
							</div>
						</td>
					</tr>

					<tr>
						<th scope="row"><span class="txt dB lh30 alignLeft pL10 fl">주소</span></th>
						<td class="end">
							<div class="fullBox">
								<span class="banBoxInput"> <input id="zipNo"
									name="me_addr1" title="주소검색란" readonly="readonly" type="text" value="${me_addr1 }"/>
								</span> <span class="blueBtn"> <input type="button" value="주소검색"
									onclick="goPopup();" />
								</span>
							</div>
							<div class="fullBox pt0">
								<input id="roadAddrPart1" name="me_addr2" title="주소입력란"
									readonly="readonly" type="text" value="${me_addr2 }"/>
							</div>
							<div class="fullBox pt0">
								<input id="addrDetail" name="me_addr3" title="나머지 주소입력란"
									type="text" value="${me_addr3 }"/>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="txt dB lh30 alignLeft pL10 fl">연락처</span></th>
						<td class="end">
							<div class="phoneBoxWrap">
								<input id="tel2" name="me_tel1" type="text" value="${me_tel1 }"
									maxlength="3" /> -<span class="boxPhoneCenter"> <input
									id="tel2" name="me_tel2" type="text" value="${me_tel2 }" maxlength="4" />
								</span> <span class="phoneMool">-</span> <span class="boxPhoneEnd">
									<input id="tel3" name="me_tel3" type="text" value="${me_tel3 }"
									maxlength="4" />
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td>이미지를 넣어주세요</td>
						<td><input type="file" name="me_photo" value="${dto.me_photo }"
							maxlength="50" /><td>
					
					</tr>
				</table>
			</div>
			<div align="right">
            <input type="submit" class="btn btn-sm btn-info" value="수정완료">
         </div>
		</form>
	</div>

	<!-- /내용끝 -->
	<jsp:include page="/Template/Foot.jsp" />
</body>
</html>