<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-header">
	<!-- 아래에 실제내용 표시 -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active">
			<a href="<c:url value='/ZAMONG/NoticeList.do'/>">통합검색</a>
		</li>
		<li role="presentation">
			<a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">아티스트</a>
		</li>
		<li role="presentation">
			<a href="<c:url value='/ZAMONG/ProblemList.do'/>">곡</a>
		</li>
		<li role="presentation">
			<a href="<c:url value='/ZAMONG/ProblemList.do'/>">앨범</a>
		</li>
	</ul>
</div>