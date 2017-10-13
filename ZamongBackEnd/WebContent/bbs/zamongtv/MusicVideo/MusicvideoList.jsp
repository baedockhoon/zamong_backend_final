<%@page import="com.zamong.mv.service.MusicVideoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.zamong.mv.service.impl.MusicVideoDAO"%>
<%@page import="java.util.Map"%>
<%@page import="model.PagingUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ include file="../../Common/IsMember.jsp" %> --%>


<!DOCTYPE html>
<html>
  <head>


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
 <jsp:include page="/Template/autocomplete.jsp" />
    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->

	<hr/>
		<div class="page-header">
			<h1>뮤직비디오 목록</h1>
		</div>
			<div class="page-header">
		<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
        		<li role="presentation" class="active"><a href="<c:url value='/ZAMONG/MusicVideoList.do'/>">뮤직비디오</a></li>
        		<li role="presentation"><a href="<c:url value='/ZAMONG/BroadCastList.do'/>">방송</a></li>
      		</ul>
      	</div>
	<hr/>
	
		<!-- 아래에 실제내용 표시 -->
		<div>
		<table style="width:100%">
	        <tr align="right">		                          
	         <td><a href="<c:url value='/ZAMONG/MusicVideoWrite.do'/>"><button type="button" class="btn btn-sm btn-info">글작성</button></a></td>
	        </tr>
	      </table>
		                        
			<table class="table table-striped">
				<thead>
					<tr>
						<th>번호</th>
                        <th>아티스트번호</th>
                        <th>음원번호</th>
                        <th>뮤직비디오 제목 </th>
                        <!-- <th>영상이미지</th> -->
                        <!-- <th>뮤비 설명</th> -->
                        <th>조회수</th>
                        <th>게시일</th>
                        <th>관리</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
                  	<c:when test="${empty list }">		                        
                      	<tr bgcolor="white" align="center">
                       	<td colspan="8">등록된 자료가 없습니다</td>
                      	</tr>
           			</c:when>       
                    	<c:otherwise>               	
                          <c:forEach var="item" items="${list }">
                       	<tr bgcolor="white" align="center">
                          <td>${item.mv_no}</td>					                             
                          <td>${item.at_no}</td>
                          <td>${item.ss_no}</td>
                          <td align="left"><a href='<c:url value="/ZAMONG/MusicVideoView.do?mv_no=${item.mv_no}"/>'><img width="160" height="120" src="${item.mv_image}"/>&nbsp;${item.mv_title}</a></td>
                          <!-- <td><img width="160" height="120" src="${item.mv_image}"/></td> -->
                          <%-- <td>${item.mv_contents}</td> --%>
                          <td>${item.mv_hitcount}</td>
                          <td>${item.mv_regidate}</td>
                          <td><a href='<c:url value="/ZAMONG/MusicVideoEdit.do?mv_no=${item.mv_no}"/>'>수정</a> | 
                          <a href='javascript:isDelete(${item.mv_no})'>삭제</a></td>
                       	</tr>
                      	</c:forEach>
                     	</c:otherwise>                           
                 </c:choose>
				</tbody>
			</table>
				<!-- 페이징 -->
            <!-- 페이징 -->
			<table style="width:100%">
				<tr align="center">
					<td>${pagingString}</td>
				</tr>
			</table>
			<!-- 검색UI -->
			<form method="post" action="<c:url value='/ZAMONG/BroadCastList.do'/>">
				<div align="center">
				<select name="searchColumn">
					<option value="br_title">제목</option>
					<option value="at_no">아티스트</option>
					<option value="ss_no">음원</option>
					<option value="br_contents">내용</option>
				</select>
				<input type="text" id="tags" name="searchWord" /><input type="submit" class="btn btn-default" value="검색">
				</div>
			</form>
		</div>
	</div> 




               
               
	<!-- /container(내용 끝) -->
	<jsp:include page="/Template/Foot.jsp" />

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<!-- 	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script> -->
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>