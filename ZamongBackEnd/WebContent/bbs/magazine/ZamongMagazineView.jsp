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
	
    <title>자몽매거진 상세보기</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
 	
 <script> 
	function isDelete(mg_no){
		if(confirm("정말로 삭제 할래?")){
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
	      	<form action="/ZAMONG/zamongMagazineView.do" method="post">
			<table width="75%" bgcolor="gray" cellspacing="1" class="table table-striped">                  	
          		<tr bgcolor="white">
          			<td width="30%" align="center">제목</td>
          			<td >
          			${dto.mg_title }
          			</td>
          		</tr>
          		<tr bgcolor="white" >
          			<td align="center">분류</td>
          			<td>
          			${dto.mg_division }
          			</td>
          		</tr>
          		<tr bgcolor="white" >
          			<td align="center">내용</td>
          			<td>
		                ${dto.mg_contents }
		            </td>
          		</tr>
          		<tr bgcolor="white" >
          			<td align="center">조회수</td>
          			<td>
		                ${dto.mg_hitcount }
		            </td>
          		</tr>
          		<tr bgcolor="white" >
          			<td width="30%" align="center">등록일</td>
          			<td>
          			${dto.mg_regidate }
          			</td>
          		</tr>
          		<tr bgcolor="white" align="center">
          			<td colspan="2">
          			<a href="<c:url value='/ZAMONG/zamongMagazineEdit.do?mg_no=${dto.mg_no}&nowPage=${nowPage}'/>" class="btn btn-sm btn-primary">수정</a> | 
          			<a href="<c:url value='/ZAMONG/zamongMagazine.do?mg_no=${dto.mg_no}'/>" class="btn btn-sm btn-primary">목록</a> |
          			<a href="javascript:isDelete(${dto.mg_no})" class="btn btn-sm btn-primary">삭제</a>
          			</td>
          		</tr>
          	</table>
          	</form>
          	
          	<%-- <form id="frm" action="<c:url value='/ZAMONG/comment.do'/>"
		    method="post">
		    <input type="hidden" name="no" value="${item.mg_cm_no}" />
		    <!-- 수정 및 삭제용 파라미터 -->
		    <input type="hidden" name="c_no" />
		    <table width="75%">
		     <tr>
		      <td><input id="c_title" type="text" size="50" name="c_title" />
		       <input id="submit" type="submit" value="등록" /></td>
		     </tr>
		    </table>
		   </form>
          	
          	
          	
          	<table width="75%" bgcolor="gray" cellspacing="1">
   <tr bgcolor="white" align="center">
    <th width="50%">코멘트</th>
    <th width="25%">올린일</th>
    <th width="10%">삭제</th>
   </tr>
   <c:if test="${empty cm_dto }" var="result">
    <tr bgcolor="white" align="center">
     <td colspan="4">등록된 코멘트가 없어요</td>
    </tr>
   </c:if>
   <c:if test="${not result}">
    <c:forEach items="${cm_dto }" var="item">
     <tr bgcolor="white" align="center">
      <td align="left">
	      <span style="cursor: pointer;"title="${item.mg_cm_no}">
	      	${item.mg_cm_contents }
	      </span>
      </td>
      <td>${item.mg_cm_regidate }
      </td>
      <td>
        <span title="${item.mg_cm_no}"style="cursor: pointer; color: green; font-size: 1.4em; font-weight: bold">
		삭제
        </span></td>
     </tr>
    </c:forEach>
   </c:if>

  </table> --%>
  
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