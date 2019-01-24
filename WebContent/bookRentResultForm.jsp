<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 대출 결과</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-weight:bold;">
<jsp:include page="HeaderForUser.jsp"/>

<%
List<Book> booklist = (List)request.getAttribute("booklist");
%>
<h1>"${msg }"</h1><br>
<h3>현재 대출중인 책 목록</h3>

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
<td><input type="text" name="bookNo" style="text-align:center; border: none; color:#CD853F" value="
<%=book.getBookNo() %>" readonly="readonly" ></td>
<td><%=book.getBookTitle() %></td>
<td><%=book.getBookAuthor() %></td>
<td><%=book.getBookState() %></td>
</tr>
<%
} 
%>
</table>
</p>

<p>
<input type="button" value="추가 대출"
onclick="location.href='bookRentForm.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
<input type="button" value="메인"
onclick="location.href='main3.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>
</font>

<jsp:include page="FooterForUser.jsp"/>
</center>
</body>
</html>