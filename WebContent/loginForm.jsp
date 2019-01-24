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
<font color="#CD853F" style="font-family:Georgia; font-weight:bold;">
<jsp:include page="MainHeader.jsp"/>
<form action="login.do" method="post">


<p>
<h2>
I&nbsp;&nbsp; D : <input type = "text" name = "id" ><br>
P W : </td><td><input type = "password" name = "pw"><br>
</h2>
</p>



<p>
<input type = "submit" value="로그인" style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px;">
<input type = "button" value="회원가입" onclick="location.href='joinForm.jsp'" style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px;">
</p>

				<p>
				
				<table align="center">
					<tr>
						<td>${msg }&nbsp;</td>
					</tr>
				</table>
				</p>


			</form>
<jsp:include page="FooterForUser.jsp"/>
</font>
</center>
</body>
</html>