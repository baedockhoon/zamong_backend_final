<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 어느 컨트롤러에서 왔는지에 따라 분기 -->
<c:choose>
	<c:when test="${WHERE eq 'INS' }">
		<c:set var="SUC_MSG" value="어워드 후보등록 성공"/>
		<c:set var="FAIL_MSG" value="어워드 후보등록 실패"/>
		<c:set var="SUC_URL" value="/ZAMONG/MusicAwardCandidatesAddPopup.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="SUC_MSG" value="어워드 후보삭제 성공"/>
		<c:set var="FAIL_MSG" value="어워드 후보삭제 실패"/>
		<c:set var="SUC_URL" value="/ZAMONG/MusicAwardView.do?ma_no=${requestScope.ma_no}"/>
	</c:otherwise>
</c:choose>


<script>
	<c:choose>
		<c:when test="${SUC_FAIL ==1}">
			alert("${SUC_MSG}");
			//location.href='<c:url value="${SUC_URL}"/>';
			history.back();
		</c:when>
		<c:when test="${SUC_FAIL ==0}">
			alert("${FAIL_MSG}");
			history.back();
		</c:when>
		<c:otherwise>	
			alert("후보등록 실패하였습니다. 관리자에게 문의하세요");
			history.back();
		</c:otherwise>
	</c:choose>
</script>