<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 입고</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-weight:bold;">
<jsp:include page="HeaderForAdmin.jsp"/>

<form action="bookAdd.do">

<p>
<h1>" 입고하실 책의 정보를 입력해주세요 :) "</h1>
<h2>

도서 제목 : <input type="text" name="bookTitle"><br>
도서 저자 : <input type="text" name="bookAuthor"><br>
</h2>
</p>

<p>
<input type="submit" value="입 고" 
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px;">&nbsp;
<input type="button" value="다 시" onclick="location.href='bookAddForm.do'" 
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px;">&nbsp;
<input type="button" value="메 인" onclick="location.href='main2.do'" 
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px;">
</p>
</form>

</font>
<jsp:include page="FooterForAdmin.jsp"/>
</center>

</body>
</html>