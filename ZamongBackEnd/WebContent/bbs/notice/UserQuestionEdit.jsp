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
	
    <title>공지사항</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
 	<script type="text/javascript" src="<c:url value='/Editor/js/HuskyEZCreator.js' />" charset="utf-8"></script>
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
 

 
 
</script>
 <script>
	$(function(){

		$("a").click(function(){
			if(this.id == "list"){
				history.back();
			} 
		});
	});
</script>
 <!-- <script>
	function isValidate(formObject) {
		if(formObject.qu_largedivide.value == ""){
			alert("대분류를 선택하세요");
			formObject.qu_largedivide.focus();
			return false;
		}//if
		else if(formObject.qu_mediumdivide.value == ""){
			alert("중분류를 선택하세요");
			formObject.qu_mediumdivide.focus();
			return false;
		}//if
		 if(formObject.title.value.length == 0){
			alert("제목을 입력하세요");
			formObject.title.focus();
			return false;
		}//if
		 if(formObject.contents.value.length == 0){
			alert("내용 입력하세요");
			formObject.contents.focus();
			return false;
		}//if

		
	}//isValidate()
</script> -->
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
	      	<form id="frm" onsubmit="return isValidate(this);"  action="${pageContext.request.contextPath}/ZAMONG/UserQuestionEdit.do" method="post">
				<!-- 키값 -->
		        <input type="hidden" name="qu_no" value="${dto.qu_no}"/>
		    	 <input type="hidden" name="nowPage" value="${nowPage}"/>
				<table class="table table-striped" style="">
					<tr>
						<td>대분류</td>
						<td>
			   			
			   			
						 <select name="qu_largedivide" required="required">
						 	<option value="">대분류</option>
							<option value="서비스문의/오류">서비스문의/오류</option>
							<option value="결제/해지/환불">결제/해지/환불</option>
							<option value="이벤트">이벤트</option>
							<option value="이용권 사용문의">이용권 사용문의</option>
							<option value="회원정보">회원정보</option>			
						</select> 
					
					
						</td>	
						<td>
						
			    <input type="hidden" value="${dto.qu_mediumdivide }" /> 
						 <select name="qu_mediumdivide" required="required">
						 <c:forEach var="item" items="${dto.qu_mediumdivide }">
					 		<option value="${item}">${item}</option>
						 </c:forEach>
						  	<option value="">중분류</option>
							<option value="Windows플레이어">Windows플레이어</option>
							<option value="재생/다운로드">재생/다운로드</option>
							<option value="멜론설치/지원가기">멜론설치/지원가기</option>
							<option value="이용권사용문의">이용권사용문의</option>
							<option value="회원탈퇴">회원탈퇴</option>
							<option value="결제방법">결제방법</option>
							<option value="이벤트">이벤트</option>			
						</select> 
						</td>					
					</tr>
					<tr bgcolor="white" >
          			<td align="center">제목</td>
          			<td>		              
						<input type="text" name="title" value="${dto.qu_title }" required="required"/>		
		            </td>
          		</tr>
          		<tr bgcolor="white" >
          			<td align="center">내용</td>
          			<td>
		                <textarea rows="10" cols="30" id="ir1" name="contents" style="width:650px; height:350px;">
						${dto.qu_contents}
						</textarea>
		            </td>
          		</tr>
          	
          		<tr bgcolor="white" align="center">
          			<td colspan="50">
          			<input type="submit" id="insert" value="수정" class="btn btn-sm btn-primary"/>
          			<a id="list" class="btn btn-sm btn-primary">취소</a>
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