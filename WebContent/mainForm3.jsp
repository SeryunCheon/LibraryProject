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
<font color="#CD853F" style="font-family:Georgia;" >
<jsp:include page="HeaderForUser.jsp"/>

<form action="bookSearch.do">

<table style="margin: auto; text-align: center;">
<tr><td><h1><font style="font-size: 50px;">Book Search : </font><input type="text" name="msg">
<input type="submit" value="Go" style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:1; border-color:#CD853F;">
</h1>
</td>
</tr>
</table>


</form>

<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
<tr>
<th style="font-weight:bold;">고유 번호</th>
<td style="font-weight:bold;">제 목</td>
<td style="font-weight:bold;">저 자</td>
<td style="font-weight:bold;">대출 상태</td>
<td style="font-weight:bold;">반납예정 날짜</td>
<td style="font-weight:bold;">대출자 ID</td>
</tr>

<%List<Book> list = (List)request.getAttribute("booklist");
 for(Book book : list){
 	 %> 
	 <tr>
<th><%=book.getBookNo()%></th>
<td><%=book.getBookTitle() %></td>
<td><%=book.getBookAuthor() %></td>
<td><%=book.getBookState() %></td>
<td><%=book.getBookReturnDate()%></td>
<td><%=book.getUserId() %></td>
</tr>
<%} %> 

</table>


${msg }

<p>
<input type="button" value="전체조회" onclick="location.href='main3.do'"
 style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
</p>
<jsp:include page="FooterForUser.jsp"></jsp:include>
</font>
</center>
</body>
</html>