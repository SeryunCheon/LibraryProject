<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-family:Georgia;" >
<jsp:include page="HeaderForUser.jsp"/>
 <br><br>
 <h1>" ${msg } "</h1> 
 <br><br><br>
 
<p>				
<input type = "button" value="로그인 화면으로" onclick="location.href='loginForm.jsp'"
	style= "background-color:#8B4513; color:white; height:23px; width: 110px; font-family:Georgia; font-weight:bold; border: 1px solid #8B4513;">
</p>
<jsp:include page="FooterForUser.jsp"></jsp:include>
</font>
</center>
</body>
</html>