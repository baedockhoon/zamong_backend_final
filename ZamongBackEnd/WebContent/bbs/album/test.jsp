<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!doctype html>
<html lang="ko">
<head>
<title>Melon::음악이 필요한 순간, 멜론</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="keywords" content="음악서비스, 멜론차트, 멜론TOP100, 최신음악, 인기가요, 뮤직비디오, 앨범, 플레이어, 스트리밍, 다운로드, 아티스트플러스, 아티스트채널" />
<meta name="description" content="국내 최대 1,000만곡 확보 No.1 음악사이트, 멜론! 최신음악과 실시간 차트는 기본, 내 취향을 아는 똑똑한 추천 라디오, 내가 좋아하는 아티스트의 새로운 소식까지 함께 즐겨보세요." />
<meta property="og:title" content=""/>
<meta property="og:image" content=""/>
<meta property="og:description" content="음악이 필요한 순간, 멜론"/>
<link rel="stylesheet" href="http://www.melon.com/resource/script/player/melonPlayer.css" type="text/css" title="mgoon"/>
<link rel="stylesheet" href="melon.css" type="text/css" />
<script type="text/javascript" src="http://www.melon.com/resource/script/web/webplayer/v4/melonweb_wp_ui.js"></script>
<script type="text/javascript" src="http://cdnimg.melon.co.kr/static/web/resource/script/w1/bg/p/16391k6met.js"></script>
<script type="text/javascript" src="http://log.melon.com/mlog.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico?2" />


<script type="text/javascript">
//document.domain="melon.com";
//var __PLAYSONGLIST = '30613202';
//var __POCID = "WP10";
</script>
<style>
button:focus {outline:0 !important;}
</style>
</head>
<body onLoad='window.blur();window.focus();' style="overflow:hidden;">
	<div class="wrap" id="wrap">
		<div class="box_loading">
			<div><img width="51" height="51" src="http://cdnimg.melon.co.kr/resource/image/web/webplayer/loading_v4.gif" alt="로딩 중"/></div>
		</div>
		<div class="player_top">
			<h1><a href="http://www.melon.com" title="Melon 메인으로 이동" target="_blank">
				<img width="61" height="16" src="http://cdnimg.melon.co.kr/resource/image/web/webplayer/h1_logo.png" alt="Melon 로고"/>
			</a></h1>
			<div class="box_music_info">
				<div class="music_info d_box_marquee">
					<p class="d_marquee"><span class="span_title"></span></p>
				</div>
			</div>
			<div class="box_player">
				<div class="controller">
					<button type="button" class="btn_prev"><span>이전</span></button>
					<div class="ctrl_play">
						<button type="button" class="btn_play d_btn_play"><span>재생</span></button>
						<button type="button" class="btn_pause d_btn_play"><span>정지</span></button>
					</div>
					<button type="button" class="btn_next"><span>다음</span></button>
				</div>
				<div class="box_btn_lyrics">
					 <button type="button" class="btn_lyrics_on d_btn_lyrics"><span>가사보기</span></button>
					 <button type="button" class="btn_lyrics_off d_btn_lyrics"><span>가사끄기</span></button>
				</div>
				<div class="volume">
					<button type="button" class="btn_volume d_btn_volume"><span>볼륨</span></button>
					<button type="button" class="btn_volume_off d_btn_volume"><span>음소거</span></button>
					<div class="bar" id="player_volumeslider"></div>
				</div>
			</div>
			<div class="box_play_bar">
				<div id="player" style="position: relative; display:block; width:354px; height: 22px;"></div>
			</div>
		</div>
		<div class="container">
			<div class="thumb">
				<img src="http://cdnimg.melon.co.kr/resource/image/web/webplayer/thumb_noimg.jpg" alt="앨범 커버 이미지" width="344" height="344" id="albumImg" />
				<span class="frame"></span>
			</div>

			<div class="box_lyrics">
				<a href="#" class="btn_focus focus_disable">현재 가사 바로가기</a>
				<div class="txt_area">

				</div>
			</div>
		</div>
		<div class="player_btm">

		
			<div class="box_banner">
				<a href="http://melon.com/kakaovip" alt="" target="_blank" data="LOG_PRT_CODE=1&MENU_PRT_CODE=3&MENU_ID_LV1=&CLICK_AREA_PRT_CODE=Z17&ACTION_AF_CLICK=V1&CLICK_CONTS_TYPE_CODE=&CLICK_CONTS_ID=&PROMO_CONTS_TYPE_CODE=&PROMO_CONTS_ID=&PROMO_SEQ=87">
					<img width="384" height="88" src="http://cdnimg.melon.co.kr/resource/image/cds/promotion/PROMO20170713183715.png" alt="맥플레이어 소개"/>
				</a>
			</div>


			<div class="box_link">
				<a href="http://www.melon.com" title="Melon 메인으로 이동" target="_blank">
					<span class="txt">음악이 필요한 순간 멜론, 더 많음 음악을 만나보세요!</span>
					<span class="bl_arrow"></span>
				</a>
			</div>
		</div>

<div class="layer_popup" id="layerPop01" style="width:342px;">
<div class="box_layer_pop">
<div class="layer_title">
<h2>안내</h2>
</div>
<div class="layer_cntt">
<div class="box_txt">
<p>해당 컨텐츠는 청소년 유해매체물로서 정보통신이용촉진 <br/>및 정보보호 등에 관한 법률 및 청소년보호법의 규정에 <br/>의하여 19세 미만의 청소년이 이용할 수 없습니다. <br/> <br/>이용자 확인을 위해 로그인 후 사용해 주세요.</p>
</div>
<div class="btn_area btn_area02">
<a href="https://www.melon.com/muid/web/login/login_inform.htm" class="btn btn_green" target="_blank">로그인</a>
<a href="#" class="btn btn_gray last_child  d_close">취소</a>
</div>
</div>
<a href="#" class="btn_close d_close">닫기</a>
</div>
<span class="shadow"></span>
</div>

<div class="layer_popup" id="layerPop02" style="width:342px;">
<div class="box_layer_pop">
<div class="layer_title">
<h2>멜론 서비스 이용안내</h2>
</div>
<div class="layer_cntt">
<div class="box_txt">
<p>로그인 전에는 1분 미리듣기만 가능합니다.<br/>로그인을 하시겠습니까? </p>
</div>
<div class="btn_area btn_area02">
<a href="https://www.melon.com/muid/web/login/login_inform.htm" class="btn btn_green" target="_blank">로그인</a>
<a href="https://www.melon.com/muid/web/join/stipulationagreement_inform.htm" class="btn btn_green last_child" target="_blank">회원가입</a>
</div>
</div>
<a href="#" class="btn_close d_close">닫기</a>
</div>
<span class="shadow"></span>
</div>

<div class="layer_popup" id="layerPop03" style="width:342px;">
<div class="box_layer_pop">
<div class="layer_title">
<h2>설치안내</h2>
</div>
<div class="layer_cntt">
<div class="box_txt">
<p>멜론 웹플레이어를 사용하시려면 <br/> Adobe Flash Player가 설치되어 있어야 합니다.</p>
</div>
<div class="btn_area btn_area02">
<a href="#" onclick="javascript:flash_install();window.close();" class="btn btn_green">설치 이동</a>
<a href="javascript:window.close();" class="btn btn_green last_child">취소</a>
</div>
</div>
<a href="javascript:window.close();" class="btn_close">닫기</a>
</div>
<span class="shadow"></span>
</div>

</div>
<script type="text/javascript">$(window).load(function(){ webPlayer.PLAY.setupPlayer('S'); }); </script>
<script type="text/javascript" src="http://www.melon.com/resource/script/web/webplayer/v4/melonweb_wp_svc.js?v=4220709"></script>
</body>
</html>