<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/jquery.jqplot.css"/>

<script type="text/javascript" src="./js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="./js/jquery.jqplot.js"></script>
<script type="text/javascript" src="./js/plugins/jqplot.categoryAxisRenderer.js"></script>
</head>
<body>
<script type="text/javascript">
jQuery(document).ready(function () {
    var line = [['트와이스', 6], ['러블리즈', 5], ['베리굿', 0], ['라붐', 10], ['여자친구', 6],['ㅇㅇ구', 6],['여ㅇ', 6],['여ㅇㅇ구', 10]];
    jQuery("#chart").jqplot([line], {
          title:"자몽 차트"
        , seriesDefaults:{
            renderer:jQuery.jqplot.BarRenderer
        }
        , axes:{
            xaxis:{
                renderer:jQuery.jqplot.CategoryAxisRenderer
            }
        }
    });
});
</script>
<div id="chart" style="width:750px;height:324px;"></div>
</body>
</html>