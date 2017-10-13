<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login.jsp</title>
    
	<meta charset="utf-8" />
	

</head>
<body >
    <div class="allWrap">
        <div class="partWrap">
            <!-- 탑메뉴 및 로고 감싸는 div 시작 -->
            <div class="header">                
            </div>
            <!-- 탑메뉴 및 로고 감싸는 div 끝 -->
            <!--Left메뉴 및 실제 내용 감싸는 div시작-->
            <div class="section">              
                <div class="body">
                    <div class="content">    
                    	
                       <div class="container theme-showcase" role="main">

						<!-- Main jumbotron for a primary marketing message or call to action -->
						<div class="jumbotron">
							<h1>자몽 관리자 페이지</h1>
						</div>
				
						<!-- 실제내용의 제목표시 -->
						<div class="page-header">
							<h1>로그인</h1>
						</div>
						<!-- 아래에 실제내용 표시 -->
						
					            <form method="post" action="LoginProcess.jsp" >
					             <table style="width:300px;border-spacing:1px;background-color:yellowgreen">
					              <tr style="background-color:white">
					               <td style="width:25%">아이디</td>
					               <td><input type="text" name="user"  value='<%=request.getParameter("user")==null ? "" :request.getParameter("user") %>'/></td>
					              </tr>
					              <tr style="background-color:white">
					               <td>비밀번호</td>
					               <td><input type="password" name="pass" value='<%=request.getParameter("pass")==null ? "" :request.getParameter("pass") %>'/></td>
					              </tr>
					              <tr style="background-color:white;text-align:center">     
					               <td colspan="2"><input type="submit" value="로그인"/></td>
					              </tr>
					             </table>  
					            </form>
	  						<span style="color:red;font-weight:bold"><%=request.getAttribute("ERROR_MSG")==null ? "" : request.getAttribute("ERROR_MSG")%></span>
                    </div>
                </div>
            </div>
            <!--Left메뉴 및 실제 내용 감싸는 div끝-->
            <!--footer를 감싸는 div 시작-->
            <!--footer를 감싸는 div 끝-->
        </div>
    </div>
</body>
</html>
