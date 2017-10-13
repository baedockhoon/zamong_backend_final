<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>앨범 관리</title>
	<!-- Bootstrap core CSS -->
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
</head>

<body role="document">
	<jsp:include page="/Template/Top.jsp" />
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>앨범 등록</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>앨범 등록</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form id="frm" name="frm" action="<c:url value='/ZAMONG/Album/Write.do'/>" method="post">
				<input type="hidden" name="index" id="index" value="1" />
				<table class="table table-striped" id="grop_info">
					<tr>
						<td width="120px">
							<img id="at_image" src="<c:url value='/Images/Artist/bagicPerson.png'/>" style="width: 100px; height: 100px;"/>
						</td>
						<td>가수</td>
						<td>
							<input type="hidden" name="al_artistno" id="al_artistno" value="" />
							<input type="hidden" name="al_divide" id="al_divide" value="" />
							<input type="text" name="al_artist" id="at_name" value="" onclick="javascript:search();" required/>
							<input type="button" value="가수검색" onclick="javascript:search();"/>
						</td>
					</tr>
				</table>
				<table class="table table-striped" id="grop_info">
					<tr>
						<td colspan="2">
						<div class="table table-striped" style="display: none;" id="album_info">
						</div>
						</td>
					</tr>
				<!-- </table>
				<table class="table table-striped" id="artist_info"> -->
					<tr>
						<td>음원 등록</td>
						<td>
							<input type="file" id="ss_name0" name="ss_name0" onchange="test(0);"/> 
							<div class="table table-striped" style="display: none;" id="sound_info0">
							</div>
							<div id=file_add></div>
						</td>
					</tr>
					<tr>
						<td colspan = "2">
							<input type="button" value="음원 파일 추가" onclick="fileAdd();" />
						</td>
					</tr>
				</table>
				<!-- <a id="artist_next" class="btn btn-sm btn-info" align="center">다음</a> -->
				<input type="submit" id="album_submit" class="btn btn-sm btn-info" value="등록">
				<input type="button" id="album_submit" onclick="submitPop();" class="btn btn-sm btn-info" value="검토">
			</form>
		</div>
	</div>
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript">
		function search(){
			window.open("${pageContext.request.contextPath}/Common/FindArtistPopup.jsp", "_blank"
						, "toolbar=yes,scrollbars=yes,resizable=no,location=no,top=500,left=500,width=400,height=400");
		}
		
		function submitPop(){
			window.open("${pageContext.request.contextPath}/bbs/album/submitPop.jsp", "_blank"
					, "toolbar=yes,scrollbars=no,resizable=no,location=no,top=500,left=500,width=700,height=450");
		}
		
		
		function test(no) {
			//alert($("#frm").find("input[type=file]#ss_name"+no));
			var formData = new FormData($("#frm")[0]);
			//formData.append("uploadfile", $("input[name=ss_name"+no+"]")[0].files[0]);

			formData.append("index", no);
			if (formData != null) {
				$.ajax({
					type : 'post',
					url : "<c:url value='/ZAMONG/Sound/Write.do' />",
					enctype : "multipart/form-data",
					data : formData,
					processData : false,
					contentType : false,
					success : function(data) {
						console.log(data);
						if (no == 0){
							$("#album_info").css("display", "block");
							//$("#album_info").html("").html(data.albuminfo);
						}
						$("#sound_info"+no).css("display", "block");
						$("#sound_info"+no).html("").html(data);
					},
					error : function(error) {
						alert("파일 업로드에 실패하였습니다.");
						console.log(error);
						//console.log(error.status);
					}
				});
			}
		}

		var uf = "";
		function fileAdd() {
			//alert('#file_add' + uf);
			var addform = "<input type='file' id='ss_name" + (uf + 1)+"'"
					+ " name='ss_name" + (uf + 1)+"'"
					+ " onchange='test("+(uf + 1)+");''/>"
					+ " <table class='table table-striped' style='display: none;' id='sound_info"+(uf+1)+"'></table>"
					+ " <div id='file_add" + (uf + 1)+"'></div>";
			$('#file_add' + uf).append(addform);
			uf++;
			$('#index').val(uf+1);
		}
	</script>
</body>
</html>