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


<p>
<h1>" 출고도서 "</h1>
</p>
		
		<table border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7" align="center" style="margin: auto; text-align: center;">
			<tr>
				<th style="font-weight:bold;">고유 번호</th>
				<td style="font-weight:bold;">제 목</td>
				<td style="font-weight:bold;">저 자</td>
				<td style="font-weight:bold;">대출 상태</td>
				
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
			</tr>
		
		</table>
		
		
		<p>
		<h3>출고 하시겠습니까?</h3>
		</p>
		
		
	<p>	
<input type="button" value="예" onclick="location.href='bookDeleteResult.do?bookNo=<%=book.getBookNo()%>'"
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:none;">
<input type="button" value="아니오" onclick="location.href='bookDeleteForm.do'"
style= "background-color:#CD853F; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 50px; border:none;">
</p>
		<%
				}
			%>
	<jsp:include page="FooterForAdmin.jsp"/>
	</center>
</body>
</html>