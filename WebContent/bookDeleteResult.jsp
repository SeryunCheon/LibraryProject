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
<font color="#CD853F" style="font-weight:bold;"/>
<jsp:include page="HeaderForAdmin.jsp"/>

<h1>"${msg }"</h1>

<p>
<input type="button" value="재 출고"
		onclick="location.href='bookDeleteForm.do'"
		style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
<input type="button" value="메인"
		onclick="location.href='main2.do'"
		style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">		
</p>
	
<jsp:include page="FooterForAdmin.jsp"/>
</center>
</body>
</html>