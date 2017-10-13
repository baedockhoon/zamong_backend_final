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
	
    <title>자몽매거진</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
 
 	<script> 
	function isDelete(mg_no){
		if(confirm("정말로 삭제 하시겠습니까?")){
			location.href="<c:url value='/ZAMONG/zamongMagazineDelete.do'/>?mg_no="+mg_no;
		}//////////////////
		
	}/////////////////////	
	</script> 
 
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>자몽 매거진</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>매거진 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
		<table width="100%">
	        <tr align="right">
	         <td><a href="<c:url value='/bbs/magazine/ZamongMagazineWrite.jsp'/>"><button type="button" class="btn btn-sm btn-info">글작성</button></a></td>
	        </tr>
	      </table>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>글번호</th>
						<th>분류</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
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
							<td>${item.mg_no }</td>
							<td>${item.mg_division }</td>
							<td><a href='<c:url value="/ZAMONG/zamongMagazineView.do?mg_no=${item.mg_no}&nowPage=${nowPage }"/>'>${item.mg_title }</a></td>
							<td>${item.mg_regidate}</td>
							<td>${item.mg_hitcount}</td>
							<td>
							<a href="javascript:isDelete(${item.mg_no})">삭제</a>
							</td>
						</tr>
					</c:forEach>
                         </c:otherwise>                 
                    </c:choose>
				</tbody>
			</table>
			<!-- 페이징 -->
            <table width="100%">
              <tr align="center">
                <td>${pagingString }</td>
              </tr>
            </table>
            
            <select name="searchColumn">
					<option value="nt_title">제목</option>
					<option value="nt_contents">내용</option>			
				</select> 
					&nbsp; <input type="text" id="tags" size="30" name="searchWord" /><input
						type="submit" value="검색" />
            
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