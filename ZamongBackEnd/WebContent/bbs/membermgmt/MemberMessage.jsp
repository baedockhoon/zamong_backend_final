<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 어느 컨트롤러에서 왔는지에 따라 분기 -->
<c:choose>
	<c:when test="${WHERE eq 'INS' }">
		<c:set var="SUC_MSG" value="회원가입이 성공적으로 완료되었습니다"/>
		<c:set var="FAIL_MSG" value="회원 가입 실패"/>
		<c:set var="SUC_URL" value="/ZAMONG/MemberList.do?me_no=${requestScope.me_no}"/>
	</c:when>
	<c:when test="${WHERE eq 'EDT' }">
		<c:set var="SUC_MSG" value="회원수정이 성공적으로 완료되었습니다"/>
		<c:set var="FAIL_MSG" value="회원 수정 실패"/>
		<c:set var="SUC_URL" value="/ZAMONG/MemberView.do?me_no=${requestScope.me_no}&nowPage=${nowPage}"/>
	</c:when>
	<c:otherwise>
		<c:set var="SUC_MSG" value="삭제가 성공적으로 완료되었습니다."/>
		<c:set var="FAIL_MSG" value="삭제 실패"/>
		<c:set var="SUC_URL" value="/ZAMONG/MemberList.do?"/>
	</c:otherwise>
</c:choose>


<script>
	<c:choose>
		<c:when test="${SUC_FAIL ==1}">
			alert("${SUC_MSG}");
			location.href='<c:url value="${SUC_URL}"/>';
		</c:when>
		<c:when test="${SUC_FAIL ==0}">
			alert("${FAIL_MSG}");
			history.back();
		</c:when>
		<c:otherwise>	
			alert("파일 용량 초과");
			history.back();
		</c:otherwise>
	</c:choose>
</script>