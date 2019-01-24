<%@page import="model.Booklog"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<font color="#CD853F">
<jsp:include page="HeaderForAdmin.jsp"/> <br>
<p>
<h1>"책 입/출고 및 대출/반납 현황"</h1>
</p>
<form action="booklogSearch.do">
<table style="margin: auto; text-align: center;">
<tr><td><font style="font-size: 50px;">Book history search : </font><td></td><td> <input type="text" name="msg" style=" color:#CD853F; height:35px; width: 250px; border:1; border-color:#CD853F;"onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
> <input type="submit" value="검색" 
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:35px; width: 50px; border:1; border-color:#CD853F;">
</td></tr>
</table>
</form>
<p>
<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th style="font-weight:bold;">고유 번호</th>
<td style="font-weight:bold;">날 짜</td>
<td style="font-weight:bold;">내 용</td>
</tr>

<%List<Booklog> loglist = (List)request.getAttribute("loglist");
 for(Booklog log : loglist){
	 
%>

<tr>
<th><%=log.getBookno()%></th>
<td><%=log.getDate() %></td>
<td><%=log.getState() %></td>

</tr>
<%}%>
</table>
</P>
${msg }
<br>

<input type="button" value="전체조회" onclick="location.href='log.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 65px; border:1; border-color:#CD853F;">

<p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>
<jsp:include page="FooterForAdmin.jsp"/>
</font>
</center>
</body>
</html>