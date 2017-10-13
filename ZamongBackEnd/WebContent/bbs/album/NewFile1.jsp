<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h3>Low Volume</h3>
<audio controls volume="0.1" id="audio-example-low"class="audio-example" volume="1">
	<!-- One or more source files, each referencing the same audio but in a different file format. The browser will choose the first file which it is able to play. -->
	<source src="/wp-content/uploads/flamingos.ogg">
	<source src="http://kolber.github.io/audiojs/demos/mp3/01-dead-wrong-intro.mp3">
	You will see this text if native audio playback is not supported.
	<!-- You could use this fall-back feature to insert a JavaScript-based audio player. -->
</audio>
<small>Audio by <a href="https://soundcloud.com/beeldengeluid">Beeld
		en Geluid</a> [<a href="http://creativecommons.org/licenses/by-sa/3.0">CC
		BY-SA 3.0</a>], <a
	href="https://commons.wikimedia.org/wiki/File%3AArtis%2C_enkele_flamingo&#039;s_-_SoundCloud_-_Beeld_en_Geluid.ogg">via
		Wikimedia Commons</a></small>
<h3>High Volume</h3>
<audio controls volume="0.9" id="audio-example-loud"
	class="audio-example" src="http://kolber.github.io/audiojs/demos/mp3/01-dead-wrong-intro.mp3">
	You will see this text if native audio playback is not supported.
	<!-- You could use this fall-back feature to insert a JavaScript-based audio player. -->
</audio>
<small>Audio by JCZA [<a
	href="http://creativecommons.org/licenses/by-sa/3.0">CC BY-SA 3.0</a>]
	, via <a
	href="https://commons.wikimedia.org/wiki/File:JCZA_-_JCzarnecki-Flamenco.ogg">Wikimedia
		Commons</a></small>
<style>
.audio-example {
	display: block;
}
</style>


</html>
