<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
<table class="table table-striped">
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td>노래 제목</td>
		<td id="ss_title">
			<input type="text" value="${dto.ss_title }" name="ss_title${index }" id="ss_title${index }" required />
			<input type="checkbox" value="1" name="ss_albumtitle${index }" id="ss_albumtitle${index }">노래 대표곡
		</td>
	</tr>
	<tr>
		<td>노래 장르</td>
		<td>
			<%-- <input type="text" value="${empty dto.ss_genre ? '' : dto.ss_genre }" name="ss_genre${index }" id="ss_genre${index }" required /> --%>
			<select name="ss_genre${index }" id="ss_genre${index }" required>
				<option value="" ${empty dto.ss_genre ? "selected" : "" }>==선택==</option>
				<option value="발라드" ${!empty dto.ss_genre && dto.ss_genre eq "발라드"? "selected" : "" }>발라드</option>
				<option value="힙합/랩" ${!empty dto.ss_genre && dto.ss_genre eq "힙합/랩"? "selected" : "" }>힙합/랩</option>
				<option value="댄스" ${!empty dto.ss_genre && dto.ss_genre eq "댄스"? "selected" : "" }>댄스</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>노래 가사</td>
		<td id="li_contents"><textarea cols="100%" rows="10" name="ly_contents${index }" id="ly_contents${index }" required>${dto.ly_contents }</textarea></td>
	</tr>
	<tr style="display: none;">
		<td>노래 재생시간</td>
		<td id="ss_duration"><input type="text" name="ss_duration${index }" id="ss_duration${index }" value="1" required></td>
	</tr>
	<input type="hidden" name="ss_path${index }" value="${dto.ss_path }">
</table>

<c:if test="${index == 0 }">
	<script>
		var albuminfo ="<table class='table table-striped'>";
		albuminfo +="<input type='hidden' value='${dto.al_albumimage }' name='al_albumimage' />"
		albuminfo +="<tr class='album_info'>";
		albuminfo += "<td>앨범명</td>";
		albuminfo += "<td><input type='text' name='al_albumname' id='al_albumname' value='${dto.al_albumname }' required /></td>";
		albuminfo += "</tr>";
		albuminfo += "<tr>";
		albuminfo += "<td>앨범 이미지</td>";
		albuminfo += "<td><img style='width: 300px; height: 300px;' src='<c:url value='/Images/Sound/${dto.al_albumimage }'/>' alt='앨범 대표 이미지'></td>";
		albuminfo += "</tr>";
		albuminfo += "<tr>";
		albuminfo += "<td>발매일</td>";
		albuminfo += "<td colspan='2'><input type='text' name='al_releasedate' id='datePicker' value='${dto.al_releasedate }' required /></td>";
		albuminfo += "</tr>";
		albuminfo += "<tr>";
		albuminfo += "<td>발매사</td>";
		albuminfo += "<td colspan='2'><input type='text' name='al_publishcorp' id='al_publishcorp' required />";
		albuminfo += "</td>";
		albuminfo += "</tr>";
		albuminfo += "<tr>";
		albuminfo += "<td>기획사</td>";
		albuminfo += "<td colspan='2'><input type='text' name='al_entertaincorp' id='al_entertaincorp' required /></td>";
		albuminfo += "</tr>";
		albuminfo += "<tr>";
		albuminfo += "<td>앨범소개</td>";
		albuminfo += "<td colspan='2'><input type='text' name='al_albuminfo' id='al_albuminfo' required />";
		albuminfo += "</td>";
		albuminfo += "</tr>";
		albuminfo += "</table>";
		
		$(function (){
			$("#album_info").html("").html(albuminfo);
		
			
			$("#datePicker").datepicker({
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
		})
	</script>
</c:if>