<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 탈퇴 비밀번호 확인</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-family:Georgia; font-weight:bold;">
<jsp:include page="HeaderForUser.jsp"/>

<form action="memberDeleteGeneral.do" method="post">


<!-- face="돋움" -->
<h1>"비밀번호를 입력하세요"</h1>

<p>
<h3>비밀번호 : <input type ="password" name="pw" style= "background-color:white; font-weight:bold;"></h3><br>
</p>

<p>
<input type="submit"   value="탈퇴"   
style= "background-color:#8B4513; color:white; height:23px; width: 100px; font-family:Georgia; font-weight:bold; border: 1px solid #8B4513;">
<input type="reset"    value="다시"     
style= "background-color:#8B4513; color:white; height:23px; width: 100px; font-family:Georgia; font-weight:bold; border: 1px solid #8B4513;">
<input type ="button"  value="메인으로" onclick="location.href='main3.do'"  
style= "background-color:#8B4513; color:white; height:23px; width: 100px; font-family:Georgia; font-weight:bold; border: 1px solid #8B4513;">
</p>


			
</form>			

<jsp:include page="FooterForUser.jsp"/>
</font>	
</center>			
</body>
</html>