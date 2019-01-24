<%@page import="java.util.List"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 반납</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-family:Georgia;" >
<jsp:include page="HeaderForUser.jsp"/>

<%
List<Book> booklist = (List)request.getAttribute("booklist");
%>

<form action="bookReturn.do">
<h1>" 현재 대출중이신 책의 정보는 아래와 같습니다. "</h1>

<p>
<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th bgcolor="#CD853F" style="color:white;">도서번호</th>
<th bgcolor="#CD853F" style="color:white;">도서제목</th>
<th bgcolor="#CD853F" style="color:white;">도서저자</th>
<th bgcolor="#CD853F" style="color:white;">도서상태</th>
</tr>
<%
for(Book book : booklist){
%>

<tr>
<td><input type="text" name="bookNo" style="text-align:center; border: none; background-color:white; color:#8B4513;"
 value="<%=book.getBookNo() %>" readonly="readonly"></td>
<td><%=book.getBookTitle() %></td>
<td><%=book.getBookAuthor() %></td>
<td><%=book.getBookState() %></td>
<td bgcolor="white" style="border-right:none; border-left:none; border-top:none; border-bottom:none;">
<input type="submit" value="반납하기" style= "background-color:#8B4513; color:white; font-weight:bold;  height:37px; width: 100px;border:1; border-color:#8B4513; padding: 0px; margin: 0px;"></td>
</tr>
<%
}
%>
</table>
</p>
</form>
${msg }
<p>
<input type="button" value="메인"
onclick="location.href='main3.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>

<jsp:include page="FooterForUser.jsp"/>
</center>
</body>
</html>