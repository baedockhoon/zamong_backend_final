<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>audio.js</title>
<link rel="stylesheet" href="melon.css" type="text/css" />
<meta content="width=device-width, initial-scale=0.6" name="viewport">
<style>
body {
	color: #666;
	font-family: sans-serif;
	line-height: 1.4;
}

h1 {
	color: #444;
	font-size: 1.2em;
	padding: 14px 2px 12px;
	margin: 0px;
}

h1 em {
	font-style: normal;
	color: #999;
}

a {
	color: #888;
	text-decoration: none;
}

#wrapper {
	width: 400px;
	margin: 40px auto;
}

ol {
	padding: 0px;
	margin: 0 auto;
	list-style: decimal-leading-zero inside;
	color: #ccc;
	width: 360px;
	border-top: 1px solid #ccc;
	font-size: 0.9em;
}

ol li {
	position: relative;
	margin: 0px;
	padding: 9px 2px 10px;
	border-bottom: 1px solid #ccc;
	cursor: pointer;
}

ol li a {
	display: block;
	text-indent: -3.3ex;
	padding: 0px 0px 0px 20px;
}

li.playing {
	color: #aaa;
	text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3);
}

li.playing a {
	color: #000;
}

li.playing:before {
	content: '♬';
	width: 14px;
	height: 14px;
	padding: 3px;
	line-height: 14px;
	margin: 0px;
	position: absolute;
	left: -24px;
	top: 9px;
	color: #000;
	font-size: 13px;
	text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.2);
}

#shortcuts {
	position: fixed;
	bottom: 0px;
	width: 100%;
	color: #666;
	font-size: 0.9em;
	margin: 60px 0px 0px;
	padding: 20px 20px 15px;
	background: #f3f3f3;
	background: rgba(240, 240, 240, 0.7);
}

#shortcuts div {
	width: 460px;
	margin: 0px auto;
}

#shortcuts h1 {
	margin: 0px 0px 6px;
}

#shortcuts p {
	margin: 0px 0px 18px;
}

#shortcuts em {
	font-style: normal;
	background: #d3d3d3;
	padding: 3px 9px;
	position: relative;
	left: -3px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
	-moz-box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
	-o-box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

@media screen and (max-device-width: 480px) {
	#wrapper {
		position: relative;
		left: -3%;
	}
	#shortcuts {
		display: none;
	}
}

.controller {
	margin: 0px auto;
}

.box_player {
	margin-top: -60px;
}

.ctrl_play {
	width: 40px;
	height: 40px;
	margin: 0 24px;
	float: left;
	cursor: pointer
}

.btn_prev, .btn_next {
	width: 28px;
	height: 28px;
	margin-top: 6px;
	float: left;
	cursor: pointer
}
</style>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<Script src="//code.jquery.com/jquery-1.9.1.js">
	
</script>
<Script src="//code.jquery.com/ui/1.10.4/jquery-ui.js">
	
</script>
<script src="<c:url value='/js/audio.min.js' />"></script>
<script>
	$(function() {
		// Setup the player to autoplay the next track
		var a = audiojs.createAll({
			trackEnded : function() {
				var next = $('ol li.playing').next();
				if (!next.length)
					next = $('ol li').first();
				next.addClass('playing').siblings().removeClass('playing');
				audio.load($('a', next).attr('data-src'));
				audio.play();
			}
		});

		$("#asd").draggable(
				{
					axis : "x",
					containment : "#player_volumeslider",
					drag : function() {
						var ss = parseInt(parseInt($(this).css("left"))
								/ (parseInt($("#player_volumeslider").css("width")) - 3) * 100);
						$("#player_volumeslider").children("div").css("width",ss + "%");
						$(this).css("left", ss + "%").attr("title", "볼륨" + ss);
						audio.setVolume(ss / 100);
					},
					stop : function() {
						var ss = parseInt(parseInt($(this).css("left"))
								/ (parseInt($("#player_volumeslider").css("width")) - 3) * 100);
						$(this).attr("title", "볼륨" + ss);
						audio.setVolume(ss / 100);
					}
				});

		$(".ctrl_play").click(function() {
			if ($("div").is(".playing")) {
				audio.pause();
			} else {
				audio.play();
			}
		});

		$(".d_btn_volume").click(
				function() {
					if ($(this).is(".btn_volume_off")) {
						$(this).parent("div").removeClass("on");
						var ss = parseInt($("#player_volumeslider").children( "div").css("width")) / 100;
						audio.setVolume(ss);
					} else {
						$(this).parent("div").addClass("on");
						audio.setVolume(0);
					}
				});
		// Load in the first track
		var audio = a[0];
		first = $('ol a').attr('data-src');
		$('ol li').first().addClass('playing');
		audio.load(first);

		audio.setVolume(parseInt($("#player_volumeslider").children("div").css(
				"width")) / 100);
		// Load in a track on click
		$('ol li').click(function(e) {
			e.preventDefault();
			$(this).addClass('playing').siblings().removeClass('playing');
			audio.load($('a', this).attr('data-src'));
			audio.play();
		});

		$(".btn_next").click(function() {
			var next = $('li.playing').next();
			if (!next.length)
				next = $('ol li').first();
			next.click();
		});
		$(".btn_prev").click(function() {
			var prev = $('li.playing').prev();
			if (!prev.length)
				prev = $('ol li').last();
			prev.click();
		});

		// Keyboard shortcuts
		$(document).keydown(function(e) {
			var unicode = e.charCode ? e.charCode : e.keyCode;
			// right arrow
			if (unicode == 39) {
				var next = $('li.playing').next();
				if (!next.length)
					next = $('ol li').first();
				next.click();
				// back arrow
			} else if (unicode == 37) {
				var prev = $('li.playing').prev();
				if (!prev.length)
					prev = $('ol li').last();
				prev.click();
				// spacebar
			} else if (unicode == 32) {
				audio.playPause();
			} else if (unicode == 38) {
				audio.setVolume('0.7');
			} else if (unicode == 40) {
				audio.setVolume('0.1');
			}
		})
	});
</script>
</head>
<body>
	<div id="wrapper">
		<div class="player_top">
			<audio preload></audio>
			<div class="box_player">
				<div class="controller">
					<div class="btn_prev"></div>
					<div class="ctrl_play"></div>
					<!-- <button type="button" class="ctrl_play"></button> -->
					<div class="btn_next"></div>
				</div>
				<div class="box_btn_lyrics">
					<button type="button" class="btn_lyrics_on d_btn_lyrics">
						<span>가사보기</span>
					</button>
					<button type="button" class="btn_lyrics_off d_btn_lyrics">
						<span>가사끄기</span>
					</button>
				</div>
				<div class="volume">
					<button type="button" class="btn_volume d_btn_volume">
						<span>볼륨</span>
					</button>
					<button type="button" class="btn_volume_off d_btn_volume">
						<span>음소거</span>
					</button>
					<div
						class="bar ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
						id="player_volumeslider" aria-disabled="false">
						<div
							class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min"
							style="width: 40%;"></div>
						<a id="asd"
							class="ui-slider-handle ui-state-default ui-corner-all" href="#"
							title="볼륨40" style="margin-left: -5px; left: 40%;"></a>
					</div>
					<!-- <div class="bar" id="player_volumeslider"></div> -->
				</div>
			</div>
		</div>
		<div class="thumb">
			<img
				src="http://cdnimg.melon.co.kr/resource/image/web/webplayer/thumb_noimg.jpg"
				alt="앨범 커버 이미지" width="344" height="344" id="albumImg"> <span
				class="frame"></span>
		</div>
		<ol>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/01-dead-wrong-intro.mp3">dead
					wrong intro</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/02-juicy-r.mp3">juicy-r</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/03-its-all-about-the-crystalizabeths.mp3">it's
					all about the crystalizabeths</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/04-islands-is-the-limit.mp3">islands
					is the limit</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/05-one-more-chance-for-a-heart-to-skip-a-beat.mp3">one
					more chance for a heart to skip a beat</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/06-suicidal-fantasy.mp3">suicidal
					fantasy</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/07-everyday-shelter.mp3">everyday
					shelter</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/08-basic-hypnosis.mp3">basic
					hypnosis</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/09-infinite-victory.mp3">infinite
					victory</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/10-the-curious-incident-of-big-poppa-in-the-nighttime.mp3">the
					curious incident of big poppa in the nighttime</a></li>
			<li><a href="#"
				data-src="http://kolber.github.io/audiojs/demos/mp3/11-mo-stars-mo-problems.mp3">mo
					stars mo problems</a></li>
		</ol>
	</div>
	<div id="shortcuts">
		<!-- <div>
        <h1>Keyboard shortcuts:</h1>
        <p><em>&rarr;</em> Next track</p>
        <p><em>&larr;</em> Previous track</p>
        <p><em>Space</em> Play/pause</p>
      </div> -->
	</div>
</body>
</html>
