<%@page import="com.zamong.mv.service.MusicVideoDTO"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.mv.service.impl.MusicVideoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="../../Common/IsMember.jsp" %> --%>
<!DOCTYPE html>
<html>
  <head>
  <%
//1]파라미터(키값) 받기
String no = request.getParameter("mv_no");
//현재 페이지번호 받기
String nowPage = request.getParameter("nowPage");
//2]CRUD작업용 BbsDAO생성
MusicVideoDAO dao = new MusicVideoDAO(application);
//조회수 업데이트
//상세보기용 메소드 호출	
//이전글/다음글 조회
Map<String,MusicVideoDTO> map = dao.prevNnext(no);
//3]자원반납
dao.close();
%>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>"/>
	
    <title>뮤직비디오</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
	<script> 
	function isDelete(mv_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="MusicVideoDelete.do?mv_no="+mv_no;
		}//////////////////			
		
	}/////////////////////	
</script>
	
  </head>
  <body role="document">
    <jsp:include page="/Template/Top.jsp" />
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>뮤직비디오</h1>
		</div>
		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>뮤직비디오 게시글 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
				<input type="hidden" name="mv_no" value="${dto.mv_no}"/>번호 : ${dto.mv_no}
				<table class="table table-striped" style="">
					<tr>
              			<td>아티스트번호</td>
              			<td>
              			${dto.at_no }
              			</td>
		            </tr>
		            <tr>
              			<td>음원번호</td>
              			<td>
              			${dto.ss_no }
              			</td>
		            </tr>
					<tr>
              			<td>제목</td>
              			<td >
              			${dto.mv_title }
              			</td>
		            </tr>
					<tr>
						<td>내용</td>
						<td>
						${dto.mv_contents }
						</td>
					</tr>
					<tr>
					<!-- <input type="hidden" name="ad_no" /> -->
					<td>링크</td>
					<td>
					 ${dto.mv_link }
					</td>					
					</tr>
					
					<tr align="center">
					<td colspan="50">
					<a href="<c:url value='/ZAMONG/MusicVideoEdit.do?mv_no=${dto.mv_no}'/>" id="update"><button type="button" class="btn btn-sm btn-success">수정</button></a> | 
					<a href='javascript:isDelete(${dto.mv_no})'><button type="button" class="btn btn-sm btn-danger">삭제</button></a> | 
					<a href="<c:url value='/ZAMONG/MusicVideoList.do'/>"><button type="button" class="btn btn-sm btn-warning">목록</button></a>
					</tr>				
				</table>

			<!-- PREV & NEXT -->
			<table width="75%">
				<tr>
					<td width="15%">이전:</td>
					<td><%=map.get("PREV") == null ? "이전글이 없습니다"
					: String.format("<a href='MusicVideoView.do?mv_no=%s&nowPage=%s'>%s</a>", map.get("PREV").getMv_no(),
							nowPage, map.get("PREV").getMv_title())%></td>
				</tr>
				<tr>
					<td>다음:</td>
					<td><%=map.get("NEXT") == null ? "다음글이 없습니다"
					: String.format("<a href='MusicVideoView.do?mv_no=%s&nowPage=%s'>%s</a>", map.get("NEXT").getMv_no(),
							nowPage, map.get("NEXT").getMv_title())%></td>
				</tr>
			</table>

		</div>
	</div>
	<!-- /container(내용 끝) -->
	<jsp:include page="/Template/Foot.jsp" />
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>