<%@page import="model.Member"%>
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
<font color="#CD853F" style="font-weight:bold;"/>
<jsp:include page="HeaderForAdmin.jsp"></jsp:include>
	<h1>"${msg }"</h1>
	<%
		Member m = (Member) request.getAttribute("member");
		List<Book> booklist = (List) request.getAttribute("booklist");
	%>
	

<table>
<tr>
<td>
		<p>
		<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
			<tr>
				<th style="font-weight:bold;">N o</th>
				<td><input type="text" name="num" value="<%=m.getNum()%>"
					readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<th style="font-weight:bold;">I D</th>
				<td><input type="text" name="id" value="<%=m.getUserId()%>"
					readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<th style="font-weight:bold;">이 름</th>
				<td><input type="text" name="name" value="<%=m.getName()%>"
					readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">전화번호</td>
				<td><input type="text" name="phone" value="<%=m.getPhone()%>" readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">주 소</td>
				<td><input type="text" name="addr" value="<%=m.getAddr()%>" readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">나 이</td>
				<td><input type="text" name="addr" value="<%=m.getAge()%>" readonly="readonly" style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">성 별</td>
				<%	
  			if(m.isGender()){%>	      	
				    <td>여자<input type="radio" name="gender" value="true" checked="checked"> 
					<%}else{%>
				    <td>남자<input type="radio" name="gender" value="false" checked="checked">
				     <%	} %>
			</tr>
			
		</table>
</p>
</td>
<td> 
&emsp;&emsp;&emsp;&emsp;    
</td>
	<td valign="top">
<p>
		<table border="1"; bordercolor="#CD853F">
			<tr>
				<th bgcolor="#CD853F" style="color:white;">대출현황</th>
			</tr>
			</table>
			<table border="1"; bordercolor="#CD853F">
			<tr>
				<th bgcolor="#CD853F" style="color:white;">고유 번호</th>
				<td bgcolor="#CD853F" style="color:white;">제 목</td>
				<td bgcolor="#CD853F" style="color:white;">저 자</td>
			</tr>
			<tr>
				<%
 					for (Book book : booklist) {
				%>
				<th><%=book.getBookNo()%></th>
				<td><%=book.getBookTitle()%></td>
				<td><%=book.getBookAuthor()%></td>
							</tr>
				<%
					}
				%> 

		</table>
		</p>
		
		</td>
		</tr>
		</table>


	<p>
	<input type="button"
			value="전체 회원조회" onclick="location.href='memberSelectForm.do'"
			style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:1; border-color:#CD853F;">
	<input type="button"
			value="메인" onclick="location.href='main2.do'"
			style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 73px; border:1; border-color:#CD853F;">
</p>
<jsp:include page="FooterForAdmin.jsp"/>
</center>	
</body>
</html>