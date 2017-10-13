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
	
    <title>아티스트찾기팝업</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	
<script type="text/javascript">
	function sendTxt(at_name){
	window.opener.document.p_form.p_txt.value = at_name;
	self.close();
	}
</script>

  </head>
  <body role="document">

<form name="c_form" method="post">	
	<div align="center">
	<select name="searchColumn">
			<option value="" ${map.searchColumn == "" ? "selected=selected" : "" }>전체</option>
			<option value="S" ${map.searchColumn == "S" ? "selected=selected" : "" }>아티스트명</option>
			<option value="G" ${map.searchColumn == "G" ? "selected=selected" : "" }>그룹명</option>
		</select>
	<input type="text" name="searchWord">
	<a href="<c:url value='/ZAMONG/SearchArtist.do'/>"><button type="button" class="btn btn-default" id="assign">검색하기</button></a></div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th align="center">아티스트관리번호</th>
                <th align="center">이름 </th>
                <th align="center">소속사</th>
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
                <td align="center">${item.at_no}</td>					                             
                <td align="center"><a href='javascript:sendTxt(${item.at_no })' id="c_txt">${item.at_name}</a></td>
                <td align="center">${item.at_belong}</td>
             	</tr>
            	</c:forEach>
           	</c:otherwise>                           
       </c:choose>
		</tbody>
	</table>
</form>


			
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>