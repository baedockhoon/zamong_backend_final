<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
.starinput>.input,
.starinput>.input>label:hover,
.starinput>.input>input:focus+label,
.starinput>.input>input:checked+label{
    display: inline-block;
    vertical-align: top;
    background: url(http://localhost:8080/ZamongProject/Images/Assign/star.png) no-repeat;
}
.starinput{
    white-space: nowrap;
}
.starinput>.input{
    display:inline-block;
    width: 100px;
    background-size: 100px;
    height: 19px;
    white-space: nowrap;
    overflow: hidden;
    position: relative;
}
.starinput>.input>input{
    position: absolute;
    width: 1px;
    height: 1px;
    opacity: 0;
}
.starinput>.input.focus{
    outline: 1px dotted #ddd;
}
.starinput>.input>label{
    width: 10px;
    height: 0;
    padding: 19px 0 0 0;
    overflow: hidden;
    float: left;
    cursor: pointer;
    position: absolute;
    top: 0;
    left: 0;
}
.starinput>.input>label:hover,
.starinput>.input>input:focus+label,
.starinput>.input>input:checked+label{
    background-size: 100px;
    background-position: 0 bottom;
}
.starinput>.input>label:hover~label{
    background-image: none;
}
.starinput>.input>label[for="p1"]{ 
    width: 10px;
    z-index: 10;
}
.starinput>.input>label[for="p2"]{ 
    width: 20px;
    z-index: 9;
}
.starinput>.input>label[for="p3"]{ 
    width: 30px;
    z-index: 8;
}
.starinput>.input>label[for="p4"]{ 
    width: 40px;
    z-index: 7;
}
.starinput>.input>label[for="p5"]{ 
    width: 50px;
    z-index: 6;
}
.starinput>.input>label[for="p6"]{ 
    width: 60px;
    z-index: 5;
}
.starinput>.input>label[for="p7"]{ 
    width: 70px;
    z-index: 4;
}
.starinput>.input>label[for="p8"]{ 
    width: 80px;
    z-index: 3;
}
.starinput>.input>label[for="p9"]{ 
    width: 90px;
    z-index: 2;
}
.starinput>.input>label[for="p10"]{ 
    width: 100px;
    z-index: 1;
}
.starinput>output{
    display: inline-block;
    width: 36px;
    text-align: right;
}
.starinput>output>b{
    font:bold 18px Helvetica, Arial, sans-serif;
    vertical-align: middle;
}

</style>

<script>
//star rating
/* var starRating = function(){
  var $star = $(".starinput"),
      $result = $star.find("output>b");
  $(document)
    .on("focusin", ".starinput>.input", function(){
    $(this).addClass("focus");
  })
    .on("focusout", ".starinput>.input", function(){
    var $this = $(this);
    setTimeout(function(){
      if($this.find(":focus").length === 0){
        $this.removeClass("focus");
      }
    }, 100);
  })
    .on("change", ".starinput :radio", function(){
    $result.text($(this).next().text());
  })
    .on("mouseover", ".starinput label", function(){
    $result.text($(this).text());
  })
    .on("mouseleave", ".starinput>.input", function(){
    var $checked = $star.find(":checked");
    if($checked.length === 0){
      $result.text("0");
    } else {
      $result.text($checked.next().text());
    }
  });
}; */
//starRating();

/* $(function (){
	$(":input").mouseover(function (){
		alert("11");
	});
}) */
$(function (){
	if ($(":input[type='radio']:checked").length > 0){
		$("output > b").html($(":input[type='radio']:checked").val());
	}
	$(":input").click(function (){
		//alert($(this).val());
		$("output > b").html($(this).val());
	});
})
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/ZAMONG/zamongAssign.do" method="post">
	<input type="hidden" name="no" value="${dto.as_no }"/>
	<input type="hidden" name="date" value="${dto.as_regidate }"/>
	<input type="hidden" name="me_no" value="${dto.me_no }"/>
	<input type="hidden" name="al_no" value="${dto.al_no }"/>
	<span class="starinput">
	  <span class="input">
	    <!-- <input type="radio" name="starinput" id="p1" value="1"><label for="p1">1</label> -->
	    <input type="radio" name="starinput" id="p2" value="1"><label for="p2">1</label>
	    <!-- <input type="radio" name="starinput" id="p3" value="3"><label for="p3">3</label> -->
	    <input type="radio" name="starinput" id="p4" value="2"><label for="p4">2</label>
	    <!-- <input type="radio" name="starinput" id="p5" value="5"><label for="p5">5</label> -->
	    <input type="radio" name="starinput" id="p6" value="3"><label for="p6">3</label>
	    <!-- <input type="radio" name="starinput" id="p7" value="7"><label for="p7">7</label> -->
	    <input type="radio" name="starinput" id="p8" value="4"><label for="p8">4</label>
	    <!-- <input type="radio" name="starinput" id="p9" value="9"><label for="p9">9</label> -->
	    <input type="radio" name="starinput" id="p10" value="5"><label for="p10">5</label>
	  </span>
	  <output for="starinput"><b>0</b>점</output>
	</span>
       <input type="submit" value="입력"/>
</form>
</body>
</html>