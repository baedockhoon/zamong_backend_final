<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>

$(function() {
    $( "#testDatepicker" ).datepicker({
    	numberOfMonths: [1,2],
         changeMonth: true, 
         dayNames: ['������', 'ȭ����', '������', '�����', '�ݿ���', '�����', '�Ͽ���'],
         dayNamesMin: ['��', 'ȭ', '��', '��', '��', '��', '��'], 
         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
         monthNames: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
         minDate: 0,
         showButtonPanel: true, 
         currentText: '���� ��¥', 
         closeText: '�ݱ�', 
         dateFormat: "yymmdd"
  });
});

</script>  
<title>Insert title here</title>
</head>
<body>
<br/>
<br/>
<br/>
<br/>
<br/><input type="text" id="testDatepicker">
<br/>
<br/>
<br/>
<br/>
<center><a href="http://localhost:8080/ZamongProject/ZAMONG/MusicAwardList.do"><font size="20">���������</font></a></center>
<br/>
<br/>
<br/>
<br/>
<center><a href="http://localhost:8080/ZamongProject/ZAMONG/BroadCastList.do"><font size="20">�ĺ����</font></a></center>
</body>
</html>