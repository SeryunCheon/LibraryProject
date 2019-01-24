<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>출고</title>
</head>
<body>
<center>
<font color="#CD853F" style="font-family:Georgia;" >
<jsp:include page="HeaderForAdmin.jsp"></jsp:include>

<h1>" 출고하실 책의 정보를 검색하세요 " </h1>	
	<form action="bookDeleteSearchAdmin.do">
<p>
				<input type="text" name="msg" style= "background-color:white; font-family:Georgia; font-weight:bold; height:27px; width:340px; border:1; border-color:#CD853F;"> 
				<input type="submit" value="검색" style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:1; border-color:#CD853F;">
	</p>

	</form>
	
	${msg }
	<form action="bookDeleteRemind.do">
	<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
		<tr>
			<th style="font-weight:bold;">고유 번호</th>
			<th style="font-weight:bold;">제 목</th>
			<th style="font-weight:bold;">저 자</th>
			<th style="font-weight:bold;">대출 상태</th>
			<th style="font-weight:bold;">출 고</th>
		</tr>
		<%
			List<Book> list = (List) request.getAttribute("booklist");
			for (Book book : list) {
		%>
		<tr>
			<th><%=book.getBookNo()%></th>
			<td><%=book.getBookTitle()%></td>
			<td><%=book.getBookAuthor()%></td>
			<td><%=book.getBookState()%></td>
			<th><input type="button" value="출 고" onclick="location.href='bookDeleteRemind.do?bookNo=<%=book.getBookNo()%>'"
			style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;"></th>
		</tr>
		<%
			}
		%>
	</table>
	
	</p>
	<input type="button" value="메인" onclick="location.href='main2.do'"
		style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
		</p>
</form>


<jsp:include page="FooterForAdmin.jsp"/>
</center>
</font>
</body>
</html>