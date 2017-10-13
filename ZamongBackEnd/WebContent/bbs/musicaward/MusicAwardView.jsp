<%@page import="com.zamong.ma.service.MusicAwardDTO"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.ma.service.impl.MusicAwardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="../../Common/IsMember.jsp" %> --%>
<!DOCTYPE html>
<html>
  <head>
  <%
//1]파라미터(키값) 받기
String no = request.getParameter("ma_no");
//현재 페이지번호 받기
String nowPage = request.getParameter("nowPage");
//2]CRUD작업용 BbsDAO생성
MusicAwardDAO dao = new MusicAwardDAO(application);
//조회수 업데이트
//상세보기용 메소드 호출	
//이전글/다음글 조회
Map<String,MusicAwardDTO> map = dao.prevNnext(no);
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
	function isDelete(ma_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="MusicAwardDelete.do?ma_no="+ma_no;
		}//////////////////
	}/////////////////////
		
	function isCandidateDelete(ca_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="MusicAwardCandidatesDelete.do?ca_no="+ca_no;
		}//////////////////
	}/////////////////////
	
	
	function addcandidatespopup(ma_no){
		window.open("${pageContext.request.contextPath}/ZAMONG/MusicAwardCandidatesAddPopup.do?ma_no="+ma_no, "_blank"
				, "toolbar=yes,scrollbars=yes,resizable=no,location=no,top=200,left=700,width=600,height=300");			
	}/////////////////////	
	
	setTimeout("location.reload()",1000);
</script>


<style>
table#t01 {
    width:100%;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color:#fff;
}
table#t01 th {
    background-color: #6495ED;
    color: white;
}
</style>

  </head>
  <body role="document">
    <jsp:include page="/Template/Top.jsp" />
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>뮤직어워드</h1>
		</div>
		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>뮤직어워드 게시글 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
				<input type="hidden" name="ma_no" value="${dto.ma_no}"/>번호 : ${dto.ma_no}
				<table class="table table-striped" style="">
					<tr>
              			<td>어워드 제목</td>
              			<td >
              			${dto.ma_title }
              			</td>
		            </tr>
					<tr>
						<td>어워드 내용</td>
						<td>
						${dto.ma_contents }
						</td>
					</tr>
					<tr>
						<td>어워드 종료일</td>
						<td>
						 ${dto.ma_endofday }&nbsp;
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="right"><a href="javascript:addcandidatespopup(${dto.ma_no})"><button type="button" class="btn btn-sm btn-info">후보등록</button></a></td>
					</tr>
					<tr> <!-- 여기 TR칸 -->
						
						<!-- 후보등록 결과 -->
							<c:choose>
			                  	<c:when test="${empty canlist }">		                        
			                       	<td colspan="8">등록된 후보가 없습니다<br/>등록버튼을 클릭하여 후보를 등록해주세요</td>
			           			</c:when>  
			           			
			                    	<c:otherwise>
			                    	<table id="t01">
									 	<tr>
									 		<th width="5%" height="30px">&nbsp;</th>
									 		<th width="10%">후보관리번호</th>
									 		<th width="25%">음원명</th>
									 		<th width="25%">아티스트이름</th>
									 		<th width="20%">등록일</th>
									 		<th width="15%">관리</th>
									 	</tr>
			                          <c:forEach var="canitem" items="${canlist }" begin="0" end="10" varStatus="loop">
			                          <tr>
				                          <td height="40px">후보${loop.count}</td>
				                          <td style="text-align:center">${canitem.ca_no}</td>					                             
				                          <td>${canitem.ss_no}</td>
				                          <td>${canitem.at_no}</td>
				                          <td>${canitem.ca_regidate}</td>
				                          <td><a href="javascript:isCandidateDelete(${canitem.ca_no})">삭제</a></td>
				                          <td>
			                          </tr>
			                      	</c:forEach>
			                      	</table>
			                     	</c:otherwise>                           
			                 </c:choose>
						<!-- 후보 끝 -->
		
					</tr><!-- 여기 TR칸 끝 -->

					<hr/>

					
					<tr align="center">
					<td colspan="50">
					<a href="<c:url value='/ZAMONG/MusicAwardEdit.do?ma_no=${dto.ma_no}'/>" id="update"><button type="button" class="btn btn-sm btn-success">수정</button></a> | 
					<a href='javascript:isDelete(${dto.ma_no})'><button type="button" class="btn btn-sm btn-danger">삭제</button></a> | 
					<a href="<c:url value='/ZAMONG/MusicAwardList.do'/>"><button type="button" class="btn btn-sm btn-warning">목록</button></a>
					</tr>				
				</table>

			<!-- PREV & NEXT -->
			<table style="width:75%">
				<tr>
					<td width="15%">이전:</td>
					<td><%=map.get("PREV") == null ? "이전글이 없습니다"
					: String.format("<a href='MusicAwardView.do?ma_no=%s&nowPage=%s'>%s</a>", map.get("PREV").getMa_no(),
							nowPage, map.get("PREV").getMa_title())%></td>
				</tr>
				<tr>
					<td>다음:</td>
					<td><%=map.get("NEXT") == null ? "다음글이 없습니다"
					: String.format("<a href='MusicAwardView.do?ma_no=%s&nowPage=%s'>%s</a>", map.get("NEXT").getMa_no(),
							nowPage, map.get("NEXT").getMa_title())%></td>
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