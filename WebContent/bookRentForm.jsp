<%@page import="java.util.List"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 대출</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-weight:bold;">
<jsp:include page="HeaderForUser.jsp"/>

<p>
<h1>" 대출하실 책의 정보를 입력해주세요 :) "</h1>
</p>


<%
List<Book> booklist = (List)request.getAttribute("booklist");
%>
${msg }

<form action="bookRentSearch.do">
<p>
<input type="text" name="msg" border="3" style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 430px; border:1; border-color:#CD853F;">
<input type="submit" value="검색" style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:25px; width: 70px; border:1; border-color:#CD853F;">
</p>
</form>

<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th style="font-weight:bold;">도서번호</th>
<th style="font-weight:bold;">도서제목</th>
<th style="font-weight:bold;">도서저자</th>
<th style="font-weight:bold;">도서상태</th>
</tr>
<%
for(Book book : booklist){
%>
<tr>
<td><%=book.getBookNo() %></td>
<td><%=book.getBookTitle() %></td>
<td><%=book.getBookAuthor() %></td>
<td><%=book.getBookState() %></td>
<td bgcolor="#CD853F"><input type="button" value="대출" onclick="location.href='bookRent.do?bookNo=<%=book.getBookNo()%>'"
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:none;"></td>
</tr>
<%
}
%>
</table>

<p>
<input type="button" value="전체 조회"
 onclick="location.href='bookRentForm.do'" style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>
 
<jsp:include page="FooterForUser.jsp"/>
</center>
</body>
</html>