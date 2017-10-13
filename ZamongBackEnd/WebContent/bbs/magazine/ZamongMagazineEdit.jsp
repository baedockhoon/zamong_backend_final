<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html>
  <head> 
  
  
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Image/자몽.jpg'/>"/>
	
    <title>자몽매거진 수정</title>
    <!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<c:url value='/Editor/js/HuskyEZCreator.js' />" charset="utf-8"></script>
	
	
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  	
  	<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>

<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<c:url value='/Editor/SmartEditor2Skin.html' />",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec();
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
       $("#insert").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });     
});
 
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
}
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
	      	<form id="frm" action="${pageContext.request.contextPath}/ZAMONG/zamongMagazineEdit.do" method="post">
	      	<input type="hidden" name="mg_no" value="${dto.mg_no}"/>
		   	<input type="hidden" name="nowPage" value="${nowPage}"/>
			<table width="75%" bgcolor="gray" cellspacing="1" class="table table-striped">                  	
          		<tr bgcolor="white" >
          			<td width="30%" align="center">제목</td>
          			<td><input type="text" id="title" name="title" style="width:650px" value="${dto.mg_title }" required/></td>
          		</tr>
          		<tr>
					<td align="center">구분</td>
					<td>
		    		<input type="hidden" value="${dto.mg_division }" />
					 <select name="Magazine_category"  required="required">
						 <c:forEach var="item" items="${dto.mg_division }">
					 		<option value="${item}">${item}</option>
						</c:forEach>
							<option value="금주의 신보">금주의 신보</option>
							<option value="금주의 차트">금주의 차트</option>
							<option value="아티스트 갤러리">아티스트 갤러리</option>
							<option value="이슈 포커스">이슈 포커스</option>
							<option value="멜론 스테이지">멜론 스테이지</option>
							<option value="HIPHOPLE">HIPHOPLE</option>
							<option value="원더풀 재즈">원더풀 재즈</option>
							<option value="스쿨 오브 록">스쿨 오브 록</option>
							<option value="클래식 AtoZ">클래식 AtoZ</option>
							<option value="EDM Floor">EDM Floor</option>
							<option value="인디 스트리트">인디 스트리트</option>
							<option value="TV별책부록">TV별책부록</option>
							<option value="아이돌 탐구생활">아이돌 탐구생활</option>
							<option value="STATION H">STATION H</option>
							<option value="SM STATION">SM STATION</option>
							<option value="Hi-Fi 가이드">Hi-Fi 가이드</option>
							<option value="뮤직툰">뮤직툰</option>
					</select> 
				</td>					
				</tr>
          		<tr bgcolor="white" >
          			<td align="center">내용</td>
          			<td>
		                <textarea rows="10" cols="30" id="ir1" name="content" style="width:650px; height:350px;" required>
						${dto.mg_contents}
						</textarea>
		            </td>
          		</tr>
          		<tr bgcolor="white" >
          			<td width="30%" align="center">등록일</td>
          			<td>
          			${dto.mg_regidate }
          			</td>
          		</tr>
          		<tr bgcolor="white" align="center">
          			<td colspan="50">
          			<input type="submit" id="insert" value="수정"/>
          			<a href="javascript:history.back()"><input type="button" value="뒤로"/></a>
          			</td>
          		</tr>
          	</table>
          	</form>
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