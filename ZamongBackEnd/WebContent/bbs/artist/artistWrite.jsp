<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />
	<title>자몽 - 아티스트 등록</title>
	<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
</head>

<body role="document">
	<jsp:include page="/Template/Top.jsp" />
	<script type="text/javascript">
		$(function(){
			$("#grop_info").css("display", "none");
			$("#artist_info").css("display", "none");
			$("#artist_submit").css("display", "none");
			
			$("#artist_next").click(function(){
				$("#grop_info").css("display", "none");
				$("#artist_info").css("display", ""); /* .attr({"type": "submit", "value": "등록"}); */
				$("#artist_next").css("display", "none");
				$("#artist_submit").css("display", "");
			});
		});
	</script>
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>아티스트 등록</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>아티스트 등록</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form id="frm" action="<c:url value='/ZAMONG/Artist/Write.do'/>" method="post" enctype="multipart/form-data">
				<table class="table table-striped" >
					<tr>
						<td>그룹여부</td>
						<td>
							<input type="radio" name="group" value="G" />그룹
							<input type="radio" name="group" value="A" />솔로
						</td>
					</tr>
				</table>
				<div  class="table table-striped" id="grop_info">
					<table class="table table-striped">
						<tr>
							<td>그룹명</td>
							<td>
								<input type="text" name="gp_name" />
							</td>
						</tr>
						<tr>
							<td>그룹 성별</td>
							<td>
								<input type="radio" name="gp_gender" value="혼성" />혼성 
								<input type="radio" name="gp_gender" value="여성" />여성 
								<input type="radio" name="gp_gender" value="님성" />남성
							</td>
						</tr>
						<tr>
							<td>소속사</td>
							<td>
								<input type="text" name="gp_belong" id="gp_belong" />
							</td>
						</tr>
						<tr>
							<td>데뷔일</td>
							<td>
								<input type="text" name="gp_debutdate" id="gp_debutdate" class="datePicker"/>
							</td>
						</tr>
						<tr>
							<td>그룹 타이틀 이미지</td>
							<td><input type="file" name="gp_image" /></td>
						</tr>
						<!-- <tr bgcolor="white" align="center">
							<td colspan="2">
								<a herf="#" id="artist_next" class="btn btn-sm btn-info">다음</a>
							</td>
						</tr> -->
					</table>
				</div>
				<div class="table table-striped" id="artist_info">
					<table class="table table-striped">
						<tr class="artist_info">
							<td>아티스트 명</td>
							<td>
								<input type="text" name="at_name" />
							</td>
						</tr>
						<tr>
							<td>소속사</td>
							<td>
								<input type="text" name="at_belong" id="at_belong" /> 
								<span class="belong"><input type="checkbox" id="belong" value="1">그룹과 동일</span>
							</td>
						</tr>
						<tr>
							<td>데뷔일</td>
							<td>
								<input type="text" name="at_debutdate" id="at_debutdate" class="datePicker"/> 
								<span class="debut"><input type="checkbox" id="debut" value="1">그룹과 동일</span>
							</td>
						</tr>
						<tr>
							<td>데뷔곡</td>
							<td>
								<input type="text" name="at_debutsong" />
							</td>
						</tr>
						<tr>
							<td>생일</td>
							<td>
								<input type="text" name="at_birth" class="datePicker"/>
							</td>
						</tr>
						<tr>
							<td>국적</td>
							<td>
								<input type="text" name="at_contry" />
							</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>
								<input type="radio" name="at_gender" value="F" />여성 
								<input type="radio" name="at_gender" value="M" />남성 
							</td>
						</tr>
						<tr>
							<td>아티스트 정보</td>
							<td>
								<textarea name="at_artistinfo" rows="10" cols="80%"></textarea>
							</td>
						</tr>
						<tr>
							<td>아티스트 프로필 사진</td>
							<td>
								<input type="file" name="at_image" />
							</td>
						</tr>
					</table>
				</div>
				<div class="table table-striped" style="text-align: -webkit-center">
					<button type="button" id="artist_next" class="btn btn-sm btn-info">다음</button>
					<input type="submit" id="artist_submit" class="btn btn-sm btn-info" value="등록">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/Template/Foot.jsp" />
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
<script>
$(function (){
	
	$(".datePicker").datepicker({
				numberOfMonths : [ 1, 1 ],
				changeYear : true,
				changeMonth : true,
				dayNames : [ '월요일', '화요일', '수요일', '목요일', '금요일', '토요일',
						'일요일' ],
				dayNamesMin : [ '월', '화', '수', '목', '금', '토', '일' ],
				monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7',
						'8', '9', '10', '11', '12' ],
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
						'7월', '8월', '9월', '10월', '11월', '12월' ],
				showButtonPanel : true,
				currentText : '오늘 날짜',
				closeText : '닫기',
				dateFormat : "yy-mm-dd"
	});
	
	$("input[name='group']").click(function(){
		if ($(this).val() == "G"){
			$("#grop_info").css("display", "block");
			$("#artist_info").css("display", "none");
			$("#artist_next").css("display", "block");
			$("#artist_submit").css("display", "none");
			$(".belong").css("display", "block");
			$(".debut").css("display", "block");
		} else {
			$("#grop_info").css("display", "none");
			$("#artist_info").css("display", "block");
			$("#artist_next").css("display", "none");
			$("#artist_submit").css("display", "block");
			$(".belong").css("display", "none");
			$(".debut").css("display", "none");
		}
	});

	$("#belong").click(function (){
		if ($(this).prop("checked") == true){
			$("#at_belong").val($("#gp_belong").val());
		} else {
			$("#at_belong").val("");
		}
	});
	$("#debut").click(function (){
		if ($(this).prop("checked") == true){
			$("#at_debutdate").val($("#gp_debutdate").val());
		} else {
			$("#at_debutdate").val("");
		}
	});
})
</script>	
</body>
</html>