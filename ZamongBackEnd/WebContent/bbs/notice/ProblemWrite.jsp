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
		else if(formObject.tel1.value.length == 0){
			alert("번호를 선택하세요");
			formObject.tel1.focus();
			return false;
		}
		else if(formObject.tel2.value.length == 0){
			alert("번호를 입력하세요");
			formObject.tel2.focus();
			return false;
		}
		else if(formObject.tel3.value.length == 0){
			alert("번호를 입력하세요");
			formObject.tel3.focus();
			return false;
		}//if
		else if(formObject.email1.value.length == 0){
			alert("이메일을 입력하세요");
			formObject.email1.focus();
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
  	
  	
  </head>

  <body role="document">

    <jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>자몽 매거진</h1>
		</div>
		<!-- 실제 내용의 제목 표시 -->
		<div class="page-header">
			<h1>회원관리</h1>
				
				
		</div>
			
		<!-- 아래의 실제 내용 표시 -->
		<div>
			<form id="frm" onsubmit="return isValidate(this);" action="${pageContext.request.contextPath}/ZAMONG/ProblemWrite.do" method="post" enctype="multipart/form-data">
			
			<table class="table table-striped" style="">
				<hr/>
				<tr>
			
				<th>대분류</th>
				<td>
				<input type="hidden" name="1" />
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
					<input type="hidden" name="2" />
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
				<%--  	<input type="hidden" name="service_environment" value="${service_environment == null ? '' :service_environment }"/> --%>
				 <select name="service_environment" required="required">
				<option value="">사용환경 선택</option>
							<option value="웹/웹플레이어">웹/웹플레이어</option>
							<option value="Window플레이어">Window플레이어</option>
							<option value="Mac플레이어">Mac플레이어</option>
							<option value="모바일 앱">모바일 앱</option>
							<option value="모바일 웹">모바일 웹</option>
							<option value="MP3/휴대폰">MP3/휴대폰</option>
						</select> 
											
				</td>
					<th>운영 체계</th>
					<td>
					<%-- <input type="hidden" name="mq_os" value="${mq_os == null ? '' :mq_os  }"/> --%>
					 <select name="mq_os" required="required">
					 			<option value="">OS 선택</option>
							<option value="윈도우7">윈도우7</option>
							<option value="윈도우8">윈도우8</option>
							<option value="윈도우XP">윈도우XP</option>
							<option value="기타">기타</option>					
						</select> 
						<td>
				</tr>
				<tr>
				<th>브라우저</th>
				<td>
				<%-- 	<input type="hidden" name="mq_browser" value="${mq_browser == null ? '' :mq_browser  }"/> --%>
				 <select name="mq_browser" required="required">
				 	 			<option value="">브라우저 선택</option>
							<option value="인터넷익스플로어">인터넷익스플로어</option>
							<option value="크롬">크롬</option>
							<option value="사파리">사파리</option>
							<option value="파이어폭스">파이어폭스</option>
							<option value="기타">기타</option>
							
						</select> 
											
				</td>
					<th>사용 모델/버전</th>
					<td><input type="text" name="usemodel" title="제목 입력란"  required="required" required="required" /></td>
				</tr>
				<tr>
				
			  
			  
			  <tr>
			    <th scope="row"><span class="star01 pL10 m_pL0 fl m_pR0">*</span><span class="fl lh22">전화번호</span></th>
			    <td class="end">
			<%--   	<input type="hidden" name="tel" value="${tel == null ? '' :tel  }"/> --%>
					<div class="phoneBoxWrap">
					  <span class="boxPhoneStart">
						   <select id="tel1" name="tel1" title="지역번호 또는 무선사업자번호 선택" required="required">
								<option value="">선택</option>
								<option value="02">02</option>
								<option value="031">031</option>
								<option value="032">032</option>
								<option value="033">033</option>
								<option value="041">041</option>
								<option value="042">042</option>
								<option value="043">043</option>
								<option value="051">051</option>
								<option value="052">052</option>
								<option value="053">053</option>
								<option value="054">054</option>
								<option value="055">055</option>
								<option value="061">061</option>
								<option value="062">062</option>
								<option value="063">063</option>
								<option value="064">064</option>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select>
					  </span>
					  <span class="phoneMool">-</span>
					  <span class="boxPhoneCenter">
						<input  name="tel2" title="핸드폰 중간자리" type="text" required="required" />
					  </span>
					  <span class="phoneMool">-</span>
					  <span class="boxPhoneEnd">
						  <input  name="tel3" title="핸드폰 끝자리" type="text" required="required"/>
					  </span>
					</div>
						</td>
					  </tr>
			  <tr>
			    <th scope="row">*<span>이메일</span></th>
			    <td class="end">
			   <%--  	<input type="hidden" name="tel" value="${email == null ? '' :email  }"/> --%>
	                    <span class="boxEmailStart">

	                        <input id="email" name="email1" title="이메일 앞자리" type="text" required="required"/>
	                    </span>
	                    <span class="boxEmailMool">@</span>	                   
	                   <span class="boxEmailEndInput"><input type="text" title="이메일 도메인 입력란" name="email2"  required="required" /></span>
                          <span class="boxEmailEnd">
                             <select id="anwser" name="email" title="이메일" onchange="javascript:selChange(this.value);" required="required">
                                 <option value="">이메일 선택</option>
			                   <option value="sel">직접입력</option>
			                   <option value="naver.com">naver.com</option>
			                   <option value="hanmail.net">hanmail.net</option>
			                   <option value="nate.com">nate.com</option>
			                   <option value="gmail.com">gmail.com</option>
			                   <option value="daum.net">daum.net</option>
			                   <option value="hotmail.com">hotmail.com</option>
			                   <option value="lycos.co.kr">lycos.co.kr</option>
			                   <option value="korea.com">korea.com</option>
			                   <option value="empal.com">empal.com</option>
			                   <option value="dreamwiz.com">dreamwiz.com</option>
			                   <option value="yahoo.com">yahoo.com</option>
			                   <option value="ymail.com">ymail.com</option>
			                   <option value="live.com">live.com</option>
			                   <option value="aol.com">aol.com</option>
			                   <option value="msn.com">msn.com</option>
			                   <option value="me.com">me.com</option>
			                   <option value="icloud.com">icloud.com</option>
			                   <option value="rocketmail.com">rocketmail.com</option>
			                   <option value="qq.com">qq.com</option>
			                   <option value="link.com">link.com</option>
                              </select>
                     </span>
	         
				</td>
			  </tr>		  
					  <tr>
					    <th scope="row"><span>*</span>제목</th>
					    <td class="end">
						
								<input type="text" name="title" title="제목 입력란" required="required" />
						</td>
					  </tr>
					  <tr>
					    <th scope="row"><span>*</span>내용</th>
					    <td class="end">
							
				  <textarea rows="10" cols="30" id="ir1" name="contents" required="required" style="width:650px; height:350px;"></textarea>
						
						</td>
					  </tr>
					  <!--    -->
					  <tr>
					    <th scope="row"><span class="pL25 m_pL0">첨부파일</span></th>
					    <td class="end">
							<div class="pL10 mt10">
					            <input  type="file" name="file" title="첨부파일 선택" required="required"/>
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
		
				<strong style="font-size: 1.4em">1:1문의 하기</strong>
		
	</div>
	
	<!-- /container(내용 끝) -->
	<jsp:include page="/Template/Foot.jsp" />
<script>
function selChange(val){
    if(val=="sel"){
         $(".boxEmailEndInput > input").val("");
       }else{
         $(".boxEmailEndInput > input").val(val); 
       }
}
</script>
  <script>
$(function(){

	$("a").click(function(){
		if(this.id == "list"){	
			location.href ="<c:url value='/ZAMONG/ProblemList.do'/>";
		} 
	});
});
	
	
</script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>