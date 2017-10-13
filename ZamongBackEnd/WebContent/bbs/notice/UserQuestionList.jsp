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

<title>공지사항</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">

</head>
<script>
	function iswrite() {
	
		location.href="<c:url value='/bbs/notice/UserQuestionWrite.jsp'/>";
	}
			
</script>
<body role="document">

	<jsp:include page="/Template/Top.jsp" />



	<div class="container theme-showcase" role="main">



		<hr />
		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>질문 목록 보기</h1>
		</div>
		<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation"><a href="<c:url value='/ZAMONG/NoticeList.do'/>">공지사항</a></li>
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">자주하는질문</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/ProblemList.do'/>">문의사항</a></li>
      		</ul>
      	</div>
		<!-- 아래에 실제내용 표시 -->
		<hr />

				
		<!-- <table border="1" class="table table-striped">

			<tr>
				<td style="cursor: pointer; text-align: center; height: 50px;">서비스문의/오류</td>
				<td style="cursor: pointer; text-align: center;">결제/해지/환불</td>
				<td style="cursor: pointer; text-align: center;">이벤트</td>
				<td style="cursor: pointer; text-align: center;">이용권 사용문의</td>
				<td style="cursor: pointer; text-align: center;">회원정보</td>
				<td style="cursor: pointer; text-align: center;">아지톡</td>
				<td style="cursor: pointer; text-align: center;">기타</td>
			</tr>
		</table> -->


		<div>
		<form method="post">
				<select name="qu_largedivide">
						<option value="">대분류</option>				
					<option value="서비스문의/오류">서비스문의/오류</option>
					<option value="결제/해지/환불">결제/해지/환불</option>
					<option value="이벤트">이벤트</option>
					<option value="이용권 사용문의">이용권 사용문의</option>
					<option value="회원정보">회원정보</option>				
				</select> <input type="submit" value="검색" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name="qu_mediumdivide">
				<option value="">중분류</option>		
					<option value="Windows 플레이어">Windows 플레이어</option>
					<option value="재생/다운로드">재생/다운로드</option>
					<option value="멜론설치/지원가기">멜론설치/지원가기</option>
					<option value="이용권 사용문의">이용권 사용문의</option>
					<option value="회원탈퇴">회원탈퇴</option>
					<option value="결제방법">결제방법</option>
					<option value="이벤트">이벤트</option>
				</select> <input type="submit" value="검색" />
		
			<div style="text-align: right">
				<a class="btn btn-sm btn-primary" href="javascript:iswrite()">글쓰기</a>
							</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글번호</th>
						<th>등록일</th>
						<th>대분류</th>
						<th>중분류</th>
						<th>제목</th>
						<th>조회수</th>
						<th>최종 수정일</th>
							
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
								<td>${item.qu_no}</td>
								<td>${item.qu_regidate}</td>
								<td>${item.qu_largedivide}</td>
								<td>${item.qu_mediumdivide}</td>
								<td><a
									href='<c:url value="/ZAMONG/UserQuestionView.do?qu_no=${item.qu_no}&nowPage=${nowPage }"/>'>${item.qu_title}</a></td>
								<td>${item.qu_hitcount}</td>
								<td>${item.qu_lastdate}</td>
								<td><a href='javascript:isDelete(${item.qu_no})'><button
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
			<select name="searchColumn">
					<option value="qu_title">제목</option>
					<option value="qu_contents">내용</option>

				</select> &nbsp; <input type="text"  id="tags" size="30" name="searchWord" /><input
					type="submit" value="검색" />

			</form>
		</div>
	</div>
<jsp:include page="/Template/Foot.jsp" />
	<script>
function isDelete(qu_no){
	if(confirm("정말로 삭제 하시겠습니까?")){
		location.href="UserQuestionDelete.do?qu_no="+qu_no;
	}//////////////////			
	
}/////////////////////	

</script>


	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  <jsp:include page="/Template/autocomplete.jsp" />
</body>

</html>