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
	
    <title>방송</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>방송 목록</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>방송 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
		<table width="100%">
	        <tr align="right">		                          
	         <td><a href="<c:url value='/ZamongTV/zamongWrite.do'/>"><button type="button" class="btn btn-sm btn-info">글작성</button></a></td>
	        </tr>
	      </table>
		                        
			<table class="table table-striped">
				<thead>
					<tr>
						<th>번호</th>
                        <th>아티스트번호</th>
                        <th>뮤비 제목 </th>
                        <th>링크 </th>
                        <th>뮤비 설명</th>
                        <th>조회수</th>
                        <th>게시일</th>
                        <th>관리</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
                  	<c:when test="${empty list }">		                        
                      	<tr bgcolor="white" align="center">
                       	<td colspan="8">등록된 자료가 없어요</td>
                      	</tr>
           			</c:when>       
                    	<c:otherwise>               	
                          <c:forEach var="item" items="${list }">   
                       	<tr bgcolor="white" align="center">
                          <td>${item.tv_no}</td>					                             
                          <td>${item.at_no}</td>
                          <td align="left">${item.tv_title}</td>
                          <td>${item.tv_link}</td>
                          <td>${item.tv_content}</td>
                          <td>${item.tv_hitcount}</td>
                          <td>${item.tv_postdate}</td>
                          <td><a href='<c:url value="/ZamongTV/zamongView.do?no=${item.tv_no}"/>'>수정</a> | <a href="#">삭제</a></td>
                       	</tr>
                      	</c:forEach>
                     	</c:otherwise>                           
                 </c:choose>
				</tbody>
			</table>
			<!-- 페이징 -->
            <table width="100%">
              <tr align="center">
                <td>1 2 3 4 5 6 7 8 9</td>
              </tr>
            </table>
		</div>
	</div>
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>