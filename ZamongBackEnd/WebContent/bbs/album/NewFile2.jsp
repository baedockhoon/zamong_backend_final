<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<OBJECT classid="clsid:F08DF954-8592-11D1-B16A-00C0F0283628" id="Slider1" width="200" height="40" border="2"> <PARAM name="BorderStyle" value="1" /> <PARAM name="MousePointer" value="0" /> <PARAM name="Enabled" value="1" /> <PARAM name="Min" value="0" /> <PARAM name="Max" value="10" /> </OBJECT>
<div class="volume">
<button type="button" class="btn_volume d_btn_volume"><span>볼륨</span></button>
<div class="bar ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" id="player_volumeslider" aria-disabled="false">
<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 40%;"></div>
<a class="ui-slider-handle ui-state-default ui-corner-all" href="#" title="볼륨40" style="left: 40%;"><span class="blind">볼륨40</span></a>
</div>
<button type="button" class="btn_volume_off d_btn_volume"><span>음소거</span></button>
		<!-- <div class="bar" id="player_volumeslider"></div> -->
</div>

</body>
</html>