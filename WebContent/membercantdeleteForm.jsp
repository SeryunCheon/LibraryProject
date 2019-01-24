<%@page import="model.Book"%>
<%@page import="java.util.List"%>
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
<jsp:include page="HeaderForUser.jsp"/>
<font color="#CD853F" style="font-family:Georgia; font-weight:bold;">
<h1> ${msg} </h1>
	<%
	List<Book> booklist =(List<Book>)request.getAttribute("booklist");
	%>
<h2>아직 대여중인 책 목록</h2>
	<p>
<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
		<tr>

			
			<th>책번호</th>
			<th>책이름</th>
			<th>저자</th>
			<th>대여유무</th>
			<th>대여날짜</th>
			<th>반납날짜</th>
			<th>대출자</th>
		</tr>
		<%
 		for(Book b : booklist){
		%>
		<tr>

			<td><%=b.getBookNo()   %></td>
			<td><%=b.getBookTitle() %></td>
			<td><%=b.getBookAuthor()  %></td>
			<td><%=b.getBookState() %></td>
			<td><%=b.getBookRentDate() %></td>
			<td><%=b.getBookReturnDate() %></td>
			<td><%=b.getUserId() %></td>	
		</tr>
		<%
 			}
 		%> 
	</table>
	</p>

	<p>
	<input type="button" value="내 정보" onclick="location.href='memberUpdateForm.do'"
		style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 65px; border:1; border-color:#CD853F;">
	<input type="button" value="메인" onclick="location.href='main3.do'"
		style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 65px; border:1; border-color:#CD853F;">
	</p>


	</font>
<jsp:include page="FooterForUser.jsp"/>	
</center>
</body>
</html>