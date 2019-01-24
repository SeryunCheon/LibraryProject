
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
<br>
<jsp:include page="MainHeader.jsp"></jsp:include>
${msg }
<form action="join.do" method="post">
<p>
<table>
<tr>
<td>I D : </td>
<td>
<%
if(request.getAttribute("id")==null){
	%>
<input type = "text" name="id" >
 <%}
else{ String id = (String)request.getAttribute("id");%>
<input type = "text" name="id" value="<%=id%>"><% request.setAttribute("id", id);}%>
&nbsp;&nbsp;<input type="submit" value="중복확인" 
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:23px; width: 100px; border: 1px solid #CD853F;"></td>
</tr>
<tr>
<td colspan="3">${msg1}</td>
</tr>
<tr>
<% if(request.getAttribute("pw1")==null){ %>
<td>비밀번호</td>
<td><input type="password" name ="pw1"></td></tr>
<tr>
<td>비밀번호 확인</td> 
<td><input type="password" name ="pw2">&nbsp;&nbsp;
<input type="submit" value="pw확인" 
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:23px; width: 100px; border: 1px solid #CD853F;"></td>

<%}else{ 
String pw1 = (String)request.getAttribute("pw1");
String pw2 = (String)request.getAttribute("pw2");%>
<td>비밀번호</td>
<td><input type="password" name ="pw1" value="<%=pw1%>"></td></tr>
<tr>
<td>비밀번호 확인</td> 
<td><input type="password" name ="pw2" value="<%=pw2%>">&nbsp;&nbsp;
<input type="submit" value="pw확인" 
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:23px; width: 100px; border: 1px solid #CD853F;"></td>
<%} %>
</tr>
<tr>
<td colspan="3">
${msg2 }
</td>
</tr>
<tr>
<td>이 름</td>
<td><input type="text" name="name"></td>
<td>나 이</td>
<td><input type="text" maxlength="2" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
name="age" style= "IME-MODE:disabled;"></td>
</tr>
<tr>
<td>주 소</td>
<td><input type="text" name="addr"></td>
<td>전화번호</td>
<td><select name = "phone1">
<option value="010">010</option>
<option value="011">011</option>
</select>-
<input type="text" maxlength="4" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
name="phone2" style= "IME-MODE:disabled;width:50px;text-align: center;" >-
<input type="text" maxlength="4" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
name="phone3" style= "IME-MODE:disabled;width:50px;text-align: center;">
</td>
</tr>
<tr>
<td>성 별</td>
<td>여자<input type="radio" name="gender" value="true" checked="checked" >
남자<input type="radio" name="gender" value="false"></td>
<td>사용자</td>
<td>일반<input type="radio" name="admin" value="false" checked="checked">
관리자<input type="radio" name="admin" value="true"></td>
</tr>
</table>
</p>

<p>
<input type="submit" value="가입" style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:23px; width: 100px; border: 1px solid #8B4513;" >
<input type="reset" value="다시" style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold;  height:23px; width: 100px;border: 1px solid #8B4513;" >
<input type="button" value="취소" onclick="location.href='loginForm.jsp'" style= "background-color:#8B4513; color:white; height:23px; width: 100px; font-family:Georgia; font-weight:bold; border: 1px solid #8B4513;">
</p>


</form>
<jsp:include page="FooterForUser.jsp"/>
</font>
</center>
</body>
</html>