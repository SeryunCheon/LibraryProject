<%@page import="java.util.List"%>
<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 조회 / 관리자</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-weight:bold;">
<jsp:include page="HeaderForAdmin.jsp"/>
<%
List<Member> memberlist = (List)request.getAttribute("list");
%>

<h1>"전체 멤버 정보는 아래와 같습니다"</h1>
<form action="memberSelectOne.do">
<p>
<input type="text" name="msg" border="3" style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 370px; border:1; border-color:#CD853F;">
<input type="submit" value="검색" style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:30px; width: 50px; border:1; border-color:#CD853F;">
</p>
</form>

<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th style="font-weight:bold;">회원번호</th>
<th style="font-weight:bold;">회원 I D</th>
<th style="font-weight:bold;">회원 P W</th>
<th style="font-weight:bold;">회원이름</th>
</tr>
<%
for(Member member : memberlist){
%>
<tr>
<td><%=member.getNum() %></td>
<td><%=member.getUserId() %></td>
<td><%=member.getPw() %></td>
<td><%=member.getName() %></td>
<td><input type="button" value="수정"
onclick="location.href='memberUpdateFormAdmin.do?id=<%=member.getUserId()%>'"
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:none;"></td>
</tr>
<%
}
%>

</table>
<p>
<input type="button" value="전체 조회"
onclick="location.href='memberSelectForm.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
<input type="button" value="메인"
onclick="location.href='main2.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>

<jsp:include page="FooterForAdmin.jsp"/>
</center>
</body>
</html>