<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
<!-- jQuery UI CSS파일 --> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script>

$(function () {
	$("#at_name").val($(opener.document).find("#at_name").val());
	$("#al_albumname").val($(opener.document).find("#al_albumname").val());
	$("#datePicker").val($(opener.document).find("#datePicker").val());
	$("#al_publishcorp").val($(opener.document).find("#al_publishcorp").val());
	$("#al_entertaincorp").val($(opener.document).find("#al_entertaincorp").val());
	$("#al_albuminfo").val($(opener.document).find("#al_albuminfo").val());
	$("#al_artistno").val($(opener.document).find("#al_artistno").val());
	$("#al_divide").val($(opener.document).find("#al_divide").val());
	$("#at_name").val($(opener.document).find("#at_name").val());
	$("img").attr("src", ($(opener.document).find("img:eq(1)").attr("src")));
	
	var index = $(opener.document).find("#index").val();
	var ssTable = "";
	for (var i = 0; i < index; i++){
		if ($(opener.document).find("#ss_albumtitle" + i).attr("checked") != null)
			var ch = "checked";
		
		ssTable += "<table  class='table table-striped' style='display: none;'>"
		+ "<tr>"
		+ "<td>노래 제목</td>"
		+ "<td><input type='text' name='ss_title" + i + "' id='ss_title" + i +"'"
		+ " value='"+ $(opener.document).find("#ss_title" + i).val()+"' required />"
		+ "<input type='checkbox' value='1' name='ss_albumtitle" + i + "' id='ss_albumtitle" + i + "'" 
		+ ch + ">노래 대표곡"
		+ "</td>"
		+ "</tr>"
		+ "<tr>"
		+ "<td>노래 장르</td>"
		+ "<td><input type='text' name='ss_genre" + i + "' id='ss_genre" + i + "'"
		+ " value='"+ $(opener.document).find("#ss_genre" + i).val()+"' required /></td>"
		+ "</tr>"
		+ "<tr>"
		+ "<td>노래 가사</td>"
		+ "<td><textarea cols='60%' rows='10' name='ly_contents" + i + "' id='ly_contents" + i + "'"
		+ "' required >"+ $(opener.document).find("#ly_contents" + i).val()+"</textarea></td>"
		+ "</tr>"
		+ "<tr>"
		+ "<td>노래 재생시간</td>"
		+ "<td><input type='text' name='ss_duration" + i + "' id='ss_duration" + i + "'"
		+ " value='"+ $(opener.document).find("#ss_duration" + i).val()+"' required /></td>"
		+ "</tr>"
		+ "</table>";
		ch = "";
	}
	console.log(ssTable);
	$(".ssTable").append(ssTable);
	
	var uf = 0;
	$("#next").click(function(){
		if (uf < index){
			$("table:eq("+uf+")").css("display", "none");
			$("table:eq("+(uf+1)+")").css("display", "block");
			uf++;
			if (uf == index){
				$(this).html("닫기");
			}
		} else {
			window.close();
		}
	});
	$("#pre").click(function(){
		if (uf > 0){
			$("table:eq("+uf+")").css("display", "none");
			$("table:eq("+(uf-1)+")").css("display", "block");
			uf--;
			if (uf != index){
				$("#next").html("다음");
			}
		}
	});
});
</script>

<div class="page-header">
	<!-- 아래에 실제내용 표시 -->
	<div class="ssTable">
		<table class="table table-striped" id="grop_info">
			<tr>
				<td rowspan="6">
					<img style='width: 300px; height: 300px;' src='' alt='앨범 대표 이미지'>
				</td>
				<td>가수</td>
				<td>
					<input type="hidden" name="al_artistno" id="al_artistno" value="" /> 
					<input type="hidden" name="al_divide" id="al_divide" value="" /> 
					<input type="text" name="al_artist" id="at_name" value="" /> 
				</td>
			</tr>
			<tr>
				<td>앨범명</td>
				<td><input type='text' name='al_albumname' id='al_albumname' value='' required /></td>
			</tr>
			<tr>
				<td>발매일</td>
				<td><input type='text' name='al_releasedate' id='datePicker'  value='' required /></td>
			</tr>
			<tr>
				<td>발매사</td>
				<td><input type='text' name='al_publishcorp' id='al_publishcorp'  value='' required /></td>
			</tr>
			<tr>
				<td>기획사</td>
				<td><input type='text' name='al_entertaincorp' id='al_entertaincorp'  value='' required /></td>
			</tr>
			<tr>
				<td>앨범소개</td>
				<td><input type='text' name='al_albuminfo' id='al_albuminfo'  value='' required /></td>
			</tr>
		</table>
	</div>
	<table  class="table table-striped" >
	<tr>
		<td style="text-align: center;">
			<button id="pre">이전</button>
			<button id="next">다음</button>
		</td>
	</tr>
	</table>
</div>