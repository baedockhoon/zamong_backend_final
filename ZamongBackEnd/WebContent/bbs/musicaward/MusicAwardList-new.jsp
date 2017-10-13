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
	<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>"/>
	
    <title>뮤직어워드</title>
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
			<h1>뮤직어워드 페이지</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>뮤직어워드 리스트 보기</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
		<table width="100%">
	        <tr align="right">		                          
	         <td><a href="<c:url value='/ZAMONG/MusicVideoWrite.do'/>"><button type="button" class="btn btn-sm btn-info">글작성</button></a></td>
	        </tr>
	      </table><br/>
		<c:choose>
          	<c:when test="${empty list }">		                        
              	<tr bgcolor="white" align="center">
               	<td colspan="8">등록된 자료가 없습니다</td>
              	</tr>
  			</c:when>  
	       	<c:otherwise>
	       		<c:forEach var="item" items="${list }">
					<table class="table table-striped">
						<tbody>
							<tr bgcolor="#FFFFFF">
							<td width="588px"><br>
							<table border="0" width="100%">
								<tbody>
									<tr>
										<td width="7%">제 목</td> 
										<td width="83%"><h1>9월 1주차 자몽주간 인기상 TOP10</h1></td>
										<td width="10%">&nbsp;</td>
									</tr>
									<tr>
										<td>등록일</td>
										<td>&nbsp;2017년 9월 11일</td>
										<td>&nbsp;수정</td>
									</tr>
									<tr>
										<td>내용</td>
										<td>&nbsp;내용 블라블라</td>
										<td>&nbsp;삭제</td>
									</tr>
									<tr>
										<td>종료날짜</td>
										<td>&nbsp;2017년 10월 1일</td>
										<td>&nbsp;<br></td>
									</tr>
								</tbody>
							</table>
					</c:forEach>
             </c:otherwise>                           
          </c:choose>
          
				</td>
			</tr>
			</tbody>
		</table>

			<!-- 페이징 -->
            <table style="width:100%">
				<tr align="center">
					<td>${pagingString}</td>
				</tr>
			</table>
			
			<!-- 검색UI -->
			<form method="post" action="<c:url value='/ZAMONG/MusicVideoList.do'/>">
				<div align="center">
				<select name="searchColumn">
					<option value="mv_title">제목</option>
					<option value="at_no">아티스트</option>
					<option value="ss_no">음원</option>
					<option value="mv_contents">내용</option>
				</select>
				<input type="text" name="searchWord" /><input type="submit" class="btn btn-default" value="검색">
				</div>
			</form>
			
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