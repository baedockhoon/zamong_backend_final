<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="i"  begin="1" end="10">
    ${i}
</c:forEach>
 
 
<style>
#divtable {display: table; width: 100%;}
.row {display: table-row;}
.cell {display: table-cell; padding: 3px; border-bottom: 1px solid #DDD;}
.col1 {width: 10%;}
.col2 {width: 10%;}
.col3 {width: 20%;}
.col4 {width: 25%;}
.col5 {width: 15%;}
.col6 {width: 15%;}
</style>

<div id="divtable">
<div class="row">
<span class="cell col1">&nbsp;</span>
<span class="cell col2">�ĺ�������ȣ</span>
<span class="cell col3">������</span>
<span class="cell col4">��Ƽ��Ʈ�̸�</span>
<span class="cell col5">�����</span>
<span class="cell col6">����</span>
</div>
<div class="row">
<span class="cell col1">�ĺ� 1</span>
<span class="cell col1">3</span>
<span class="cell col2">����</span>
<span class="cell col3">�̸�</span>
<span class="cell col4">�����</span>
<span class="cell col4">����|����</span>
</div>
</div>


</body>

</html>