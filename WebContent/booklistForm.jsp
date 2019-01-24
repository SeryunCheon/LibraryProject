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
<font color="#CD853F" style="font-weight:bold;">
<jsp:include page="HeaderForAdmin.jsp"/>

<%
List<Book> booklist = (List<Book>)request.getAttribute("booklist");
%>
<h1> " 검색하실 도서정보를 입력하세요. "</h1>


<form action="bookListSearchAdmin.do">
<p>
<input type="text" name="msg" border="3" style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 450px; border:1; border-color:#CD853F;">
<input type="submit" value="검색" style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:30px; width: 50px; border:1; border-color:#CD853F;">
</p>
</form>

<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th style="font-weight:bold;">도서번호</th>
<th style="font-weight:bold;">도서제목</th>
<th style="font-weight:bold;">도서저자</th>
<th style="font-weight:bold;">도서상태</th>
<th style="font-weight:bold;">도서 대출 날짜</th>
<th style="font-weight:bold;">도서 반납 날짜</th>
<th style="font-weight:bold;">도서 대출자 ID</th>
</tr>
<%
for(Book book : booklist){
%>
<tr>
<td><input type="text" name="bookNo" style="text-align:center; border: none; background-color:white; color:#8B4513;" value="
<%=book.getBookNo() %>" readonly="readonly"></td>
<td><%=book.getBookTitle() %></td>
<td><%=book.getBookAuthor() %></td>
<td><%=book.getBookState() %></td>
<td><%=book.getBookRentDate() %></td>
<td><%=book.getBookReturnDate() %></td>
<td><%=book.getUserId() %></td>
</tr>
<%
}
%>
</table>

<p>
<input type="button" value="전체 조회"
onclick="location.href='booklistForm.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
<input type="button" value="메인"
onclick="location.href='main2.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>

<jsp:include page="FooterForAdmin.jsp"/>
</center>
</body>
</html>