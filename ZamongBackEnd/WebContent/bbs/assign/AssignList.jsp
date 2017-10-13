<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head> 
  	
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Image/자몽.jpg'/>"/>
	
    <title>평점</title>
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
			<h1>평점관리</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>평점보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글번호</th>
						<th>아이디</th>
						<th>등록일</th>
						<th>점수</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
               	<c:when test="${empty list }">
                   <tr bgcolor="white" align="center">
                    <td colspan="6">등록된 자료가 없어요</td>
                   </tr>
               	</c:when>
                 	<c:otherwise>
                 		<c:forEach var="item" items="${list}" varStatus="loop">
						<tr>
							<td>${item.as_no }</td>
							<td>${item.me_id }</td>
							<td>${item.as_regidate }</td>
							<td>${item.as_getpoint}</td>
							<td>
							</td>
						</tr>
					</c:forEach>
                         </c:otherwise>                 
                    </c:choose>
				</tbody>
				</table>
				<table width="100%">
              <tr align="center">
                <td>${pagingString }</td>
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