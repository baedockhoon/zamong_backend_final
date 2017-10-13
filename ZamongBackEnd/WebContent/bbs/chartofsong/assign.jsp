<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
   <link rel="icon" href="<c:url value='/Image/자몽.jpg'/>"/>
   <!-- datepicker -->
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   <!-- <link rel="stylesheet" href="resources/demos/style.css"> -->
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    <title>Zamong 백엔드</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <!-- 부가적인 테마(Bootstrap theme) -->
   <link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
     
     
   <script>
   function datepickerfun(){
      console.log(1);
      
   }
   $(function(){
      $( ".datepicker" ).datepicker({
          showButtonPanel: true,  
           closeText: '닫기', 
           dateFormat: "yy-mm-dd",
           dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
           dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
           monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
       });
   });
   </script>
</head>

  <body role="document">

     <%-- <jsp:include page="/Template/Top.jsp" /> --%>

   <div class="container theme-showcase" role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
         <h2>음원등록</h2>
      </div>

      <!-- 실제내용의 제목표시 -->
      <div class="page-header">
         
      </div>
      <!-- 아래에 실제내용 표시 -->
      <div>
         <table class="table table-striped">
            <tr>
               <th>아티스트이름</th>
               <td><input type="text" name="artist"/><input type="submit" value="검색"/></td>
            </tr>
            <tr>
               <th>소속사</th>
               <td><input type="text" name="belongGroup" style="width:60%" value=""/></td>
            </tr>
            <tr>
               <th>솔로/그룹여부</th>
               <td><input type="radio" name="solo&group" value="solo"/>솔로
                  <input type="radio" name="solo&group" value="group"/>그룹</td>
            </tr>
            <tr>
               <th>소속그룹</th>
               <td><input type="text" name="belong" value=""/></td>
            </tr>
            <tr>
               <th>데뷔일</th>
               <td><input type="text" name="debutdate" class="datepicker" value=""/></td>
            </tr>
            <tr>
               <th>데뷔곡</th>
               <td><input type="text" name="debutsong" value=""/></td>
            </tr>
            <tr>
               <th>생일</th>
               <td><input type="text" name="birth" class="datepicker" value=""/></td>
            </tr>
            <tr>
               <th>국적</th>
               <td><input type="text" name="country" value=""/></td>
            </tr>
            <tr>
               <th>성별</th>
               <td><input type="radio" name="gender" value="male"/>남성
                  <input type="radio" name="gender" value="female"/>여성</td>
            </tr>
            
            <tr>
               <th>앨범명</th>
               <td><input type="text" name="album" style="width: 60%"/>
                  <input type="submit" value="검색"/></td>               
            </tr>
            <tr>
               <th>발매일</th>
               <td><input type="text" name="releasedate" value="" class="datepicker" /></td>
            </tr>
            <tr>
               <th>발매사</th>
               <td><input type="text" name="publishcorp" style="width:60%" value=""/></td>
            </tr>
            <tr>
               <th>기획사</th>
               <td><input type="text" name="belongGroup" style="width:60%" value=""/></td>
            </tr>
            <tr>
               <th>앨범사진</th>
               <td></td>
            </tr>
            <tr>
               <th>소개</th>
               <td><textarea rows="10" maxlength="2000" style="width:70%" name="albuminfo"></textarea></td>
            </tr>
         
            <tr>
               <th>노래제목</th>
               <td><input type="text" name="ss_title" style="width:60%"/></td>
            </tr>
            <tr>
               <th>장르</th>
               <td><select name="genre">
                     <option value="">분류</option>
                     <option value="balad">발라드</option>
                     <option value="dance">댄스</option>
                     <option value="rap&hiphop">랩/힙합</option>
                  </select></td>
            </tr>
            <tr>
               <th>재생시간</th>
               <td></td>
            </tr>
            <tr>
               <th>가사</th>
               <td></td>
            </tr>
            <tr>
               <th>노래파일경로</th>
               <td></td>
            </tr>
         </table>
         <input type="submit" value="등록"/>
      </div>
   </div>
   <!-- /container(내용 끝) -->

  </body>
</html>