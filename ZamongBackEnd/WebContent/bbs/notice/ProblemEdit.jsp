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
 	<script type="text/javascript" src="<c:url value='/Editor/js/service/HuskyEZCreator.js' />" charset="utf-8"></script>
 
 <!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<c:url value='/Editor/js/HuskyEZCreator.js' />" charset="utf-8"></script>


<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->


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
<!-- <script>
	function isValidate(formObject) {
		if(formObject.service_environment.value == ""){
			alert("사용환경을 선택하세요");
			formObject.service_environment.focus();
			return false;
		}//if
		else if(formObject.mq_os.value == ""){
			alert("OS를 선택하세요");
			formObject.mq_os.focus();
			return false;
		}//if
	
		else if(formObject.mq_browser.value == ""){
			alert("브라우저를 선택하세요");
			formObject.mq_browser.focus();
			return false;
		}//if
		else if(formObject.usemodel.value.length == 0){
			alert("사용모델을 입력하세요");
			formObject.usemodel.focus();
			return false;
		}//if
		else if(formObject.tel.value.length == 0){
			alert("번호를 입력하세요");
			formObject.tel1.focus();
			return false;
		}
	
		else if(formObject.email.value.length == 0){
			alert("이메일을 입력하세요");
			formObject.email.focus();
			return false;
		}//if
		else if(formObject.title.value.length == 0){
			alert("제목을 입력하세요");
			formObject.title.focus();
			return false;
		}//if
		else if(formObject.contents.value.length == 0){
			alert("내용 입력하세요");
			formObject.contents.focus();
			return false;
		}
			else if(formObject.file.value.length == 0){
				alert("파일을 첨부하세요");
				formObject.file.focus();
				return false;
		}//if
		
		
	}//isValidate()
</script> -->
 <script>
	$(function(){

		$("a").click(function(){
			if(this.id == "list"){
				history.back();
			} 
		});
	});
	
	function selChange(val){
	       if(val=="sel"){
	            $(".boxEmailEndInput > input").val("");
	          }else{
	            $(".boxEmailEndInput > input").val(val); 
	          }
	   }
	function selChange1(val){
	       if(val=="sel1"){
	            $(".boxEmailEndInput1 > input").val("");
	          }else{
	            $(".boxEmailEndInput1 > input").val(val); 
	          }
	   }
	function selChange2(val){
	       if(val=="sel2"){
	            $(".boxEmailEndInput2 > input").val("");
	          }else{
	            $(".boxEmailEndInput2 > input").val(val); 
	          }
	   }
</script>
 
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>문의사항 수정</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>수정</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
	      	<form id="frm"  onsubmit="return isValidate(this);"  action="${pageContext.request.contextPath}/ZAMONG/ProblemEdit.do" method="post">
				<!-- 키값 -->
		        <input type="hidden" name="mq_no" value="${dto.mq_no}" />
		    	 <input type="hidden" name="nowPage" value="${nowPage}"/>
			<table class="table table-striped" style="">
				<hr/>
		 <tr>
			
				<th>대분류</th>
				<td>
				<hr/>
				<input type="radio" name="1" value="서비스이용문의/오류" required>서비스이용 문의/오류
						<input type="radio" name="1" value="결제/해지/환불">결제/해지/환불
						<input type="radio" name="1" value="이벤트">이벤트
						<input type="radio" name="1" value="이용권 사용문의">이용권 사용문의
						<input type="radio" name="1" value="회원 정보">회원 정보
						<input type="radio" name="1" value="아지톡">아지톡
					    <input type="radio" name="1" value="기타">기타		
						</td>
						
				</tr>
				
				<tr>
				<th>중분류</th>
				<td>
		
				<hr/>
				<input type="radio" name="2" value="멜론 메인 " required>멜론 메인 
						<input type="radio" name="2" value="멜론 차트">멜론 차트
						<input type="radio" name="2" value="멜론 TV">멜론 TV
						<input type="radio" name="2" value="피드">피드
						<input type="radio" name="2" value="어학">어학
						<input type="radio" name="2" value="멜론DJ">멜론DJ
					    <input type="radio" name="2" value="친구">친구
					    <input type="radio" name="2" value="마이뮤직">마이뮤직
					    <input type="radio" name="2" value="재생/다운로드">재생/다운로드
					    <input type="radio" name="2" value="검색/장르">검색/장르
					   <input type="radio" name="2" value="채널  ">채널   		         
						</td>
				</tr> 
				<tr>
				<th>사용환경</th>
					<td>
						<span class="boxEmailEndInput"><input  type="text" value="${dto.mq_useenv }" /></span>
                          <span class="boxEmailEnd">
                             <select id="service_environment" name="service_environment" title="담당부서" onchange="javascript:selChange(this.value);"required="required">
                                 <option value="sel">사용환경 선택</option>
                                <option value="웹/웹플레이어">웹/웹플레이어</option>
							<option value="Window플레이어">Window플레이어</option>
							<option value="Mac플레이어">Mac플레이어</option>
							<option value="모바일 앱">모바일 앱</option>
							<option value="모바일 웹">모바일 웹</option>
							<option value="MP3/휴대폰">MP3/휴대폰</option>
                              </select>
                     </span>
					</td>
			
					<th>운영 체계</th>
					<td>
					<span class="boxEmailEndInput1"><input  type="text" value="${dto.mq_os }" /></span>
                          <span class="boxEmailEnd1">
					   <select  name="mq_os"onchange="javascript:selChange1(this.value);" required="required">
					    <option value="sel1">운영 체계 선택</option>
							<option value="윈도우7">윈도우7</option>
							<option value="윈도우8">윈도우8</option>
							<option value="윈도우XP">윈도우XP</option>
							<option value="기타">기타</option>					
						</select>
						</span> 
						</td>
				</tr>
				<tr>
				<th>브라우저</th>
				<td>
				<span class="boxEmailEndInput2"><input  type="text" value="${dto.mq_browser }" /></span>
                          <span class="boxEmailEnd2">
			   <select  name="mq_browser"onchange="javascript:selChange2(this.value);" required="required">
			  			  <option value="sel2">브라우저 선택</option>
							<option value="인터넷익스플로어">인터넷익스플로어</option>
							<option value="크롬">크롬</option>
							<option value="사파리">사파리</option>
							<option value="파이어폭스">파이어폭스</option>
							<option value="기타">기타</option>
							
						</select> 
						</span>
											
				</td>
					<th>사용 모델/버전</th>
					<td><input type="text" name="usemodel" title="제목 입력란" value="${dto.mq_usemodel }" required="required"/></td>
				</tr>
				
			  
			  
			  <tr>
			   <td>전화번호</td>
						<td ><input type="text" name="tel"
							value="${dto.mq_tel }" required="required"/>
						<td>			   
					  </tr>
			  <tr>
			    <td>E-mail</td>
						<td ><input type="text" name="email"
							value="${dto.mq_email }" required="required"/>
						<td>
				
			  </tr>		  
					  <tr>
					    <th scope="row"><span>*</span>제목</th>
					    <td class="end">
						
								<input type="text" name="title" value="${dto.mq_title }"required="required" />
						</td>
					  </tr>
					  <tr>
					    <th scope="row"><span>*</span>내용</th>
					    <td class="end">
							
				  <textarea rows="10" cols="30" id="ir1" name="contents" style="width:650px; height:350px;">${dto.mq_contents }</textarea>
						
						</td>
					  </tr>
				
					 <tr>
					    <th scope="row"><span class="pL25 m_pL0">첨부파일</span></th>
					    <td class="end">
							<div class="pL10 mt10">
					            <input  type="file" name="file" title="첨부파일 선택" value="${dto.mq_uploadfile }" required="required"/>
							</div>
							<p >* 보안을 위해 업로드 하는 파일은 zip 압축파일로 제한이 됩니다.</p> 
							<p >* 파일첨부가 필요한 경우 zip 파일로 압축하여 업로드 하시기 바랍니다.</p> 
						</td>
					  </tr> 
							<tr>
						<td colspan="2">
							 
							  <input type="submit" id="insert" value="입력" class="btn btn-sm btn-primary"/>
				              
							           <a id="list" class="btn btn-sm btn-primary" >뒤로</a>
						</td>
					</tr>
					</table>
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