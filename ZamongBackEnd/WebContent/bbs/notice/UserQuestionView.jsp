<%@page import="com.zamong.qu.service.UserQuestionDTO"%>
<%@page import="com.zamong.qu.service.impl.UserQuestionDAO"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@page import="com.zamong.nt.service.NotiDataDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%
//1]파라미터(키값) 받기
String no = request.getParameter("qu_no");
//현재 페이지번호 받기
String nowPage = request.getParameter("nowPage");
//2]CRUD작업용 BbsDAO생성
UserQuestionDAO dao = new UserQuestionDAO(application);
//조회수 업데이트
//상세보기용 메소드 호출	
//이전글/다음글 조회
Map<String,UserQuestionDTO> map = dao.prevNnext(no);
//3]자원반납
dao.close();
%>
 <script>
 $(function(){
	$(":button").click(function(){
			
		if(this.id == "edit"){
			location.href = "<c:url value='/ZAMONG/UserQuestionEdit.do?qu_no=${dto.qu_no}&nowPage=${nowPage }'/>"
			
		}
		
		
		
		else if(this.id == "list"){
			location.href = "/ZAMONG/UserQuestionList.do";
			
		} 
	});
}); 
</script>



<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

<title>공지사항 등록</title>
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
		<div class="jumbotron">
			<h1>자주하는 질문 목록</h1>
		</div>
		<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation"><a href="<c:url value='/ZAMONG/NoticeList.do'/>">공지사항</a></li>
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/UserQuestionList.do'/>">자주하는질문</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/ProblemList.do'/>">문의사항</a></li>
      		</ul>
      	</div>
            <hr/>
		<!-- 실제내용의 제목표시 -->
	
		<!-- 아래에 실제내용 표시 -->
		<div>
			<div>
				<form action="/ZAMONG/UserQuestionView.do" method="post">
			
				<table  class="table table-striped">
				<tr bgcolor="white">
				
				<th>${dto.qu_no }</th>
			 	<th>${dto.qu_largedivide}</th>
			<th>${dto.qu_mediumdivide}</th>
			<th>${dto.qu_title}</th>
			<th>${dto.qu_hitcount}</th>
			<th>${dto.qu_lastdate} </th>
			
				</tr>
				
				</table>
					<table class="table table-striped" style="">
						<tr bgcolor="white">
						
							<td>${dto.qu_contents}</td>
						<tr bgcolor="white" align="center">
							<td colspan="2">
							
							<a href="<c:url value='/ZAMONG/UserQuestionEdit.do?qu_no=${dto.qu_no}&nowPage=${nowPage }'/>" class="btn btn-sm btn-primary">수정</a> 
							 
							 
							<a href="<c:url value='/ZAMONG/UserQuestionList.do'/> " class="btn btn-sm btn-primary">목록</a></td>
						</tr>
					</table>
				</form>
			</div>																											
			 <table width="75%" >
	                          <tr>
	                           <td width="15%">이전:</td>
	                        <td><%=map.get("PREV") ==null?"이전글이 없습니다": String.format("<a href='UserQuestionView.do?qu_no=%s&nowPage=%s'>%s</a>",map.get("PREV").getQu_no(),nowPage,map.get("PREV").getQu_title()) %></td> 
	                          </tr>
	                          <tr>
	                           <td>다음:</td>
	                            <td><%=map.get("NEXT") ==null?"다음글이 없습니다": String.format("<a href='UserQuestionView.do?qu_no=%s&nowPage=%s'>%s</a>",map.get("NEXT").getQu_no(),nowPage,map.get("NEXT").getQu_title()) %></td> 
	                          </tr>
	                         </table>   
		</div>
	</div>
	<!-- /container(내용 끝) -->

<jsp:include page="/Template/Foot.jsp" />
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"
		type="text/javascript"></script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
</body>
</html>