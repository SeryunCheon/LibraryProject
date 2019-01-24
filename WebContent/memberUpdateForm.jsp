<%@page import="java.util.StringTokenizer"%>
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
<title>회원 정보 수정 페이지</title>
</head>
<body>

<center>
<jsp:include page="HeaderForUser.jsp"/>
<font color="#CD853F"  >
	<%
	Member m =(Member)request.getAttribute("member");
	List<Book> booklist =(List)request.getAttribute("booklist");
	String a = m.getPhone();
	StringTokenizer s = new StringTokenizer(a,"-");
    String s1 = s.nextToken();
    String s2 = s.nextToken();
    String s3 = s.nextToken();
%>	
	
<table>
<tr><td>
<form action="memberUpdate.do" method="post">
<p>
<table border="1" bordercolor="#CD853F">
			<tr>
				<th bgcolor="#CD853F" style="color:white;">No</th>
				<td><input type="text" name="num" value="<%=m.getNum()%>"
					readonly="readonly"
					style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>
				<th bgcolor="#CD853F" style="color:white;">ID</th>
				<td><input type="text" name="id" value="<%=m.getUserId()%>"
					readonly="readonly"
					style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>
				<th bgcolor="#CD853F" style="color:white;">PW</th>
				<td><input type="password" name="pw"
				style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>
				<th bgcolor="#CD853F" style="color:white;">이름</th>
				<td><input type="text" name="name" value="<%=m.getName()%>"
					readonly="readonly"
					style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>	   
			    <td bgcolor="#CD853F" style="color:white;">전화번호</td>
                <td><select name = "phone1">
                <%if(s1.equals("010")){ %>
                <option value="010">010</option>
                <option value="011">011</option>
                <%}else{ %>
                <option value="011">011</option>
                <option value="010">010</option>
                <%} %>
                </select>
                -
                <input type="text" maxlength="4" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                name="phone2" value="<%=s2%>"style= "IME-MODE:disabled; background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 50px; border:none;">
                -
                <input type="text" maxlength="4" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                name="phone3" value="<%=s3%>"style= "IME-MODE:disabled; background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 50px; border:none;">
                </td>
			
			</tr>
			
			<tr>
				<td bgcolor="#CD853F" style="color:white;">주소</td>
				<td><input type="text" name="addr" value="<%=m.getAddr()%>"
				style= "background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>
				<td bgcolor="#CD853F" style="color:white;">나이</td>
				<td><input type="text" maxlength="200" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
				name="age" value="<%=m.getAge()%>"		
				style= "IME-MODE:disabled; background-color:white; color:#CD853F; font-family:Georgia; font-weight:bold; height:25px; width: 200px; border:none;"></td>
			</tr>
			
			<tr>
				<td bgcolor="#CD853F" style="color:white;">성별</td>
				<%	
			if(m.isGender()){%>	     
				    <td>여자<input type="radio" name="gender" value="true" checked="checked"> 
				            남자<input type="radio" name="gender" value="false">
					<%}else{%>
				    <td>여자<input type="radio" name="gender" value="true"> 
					      남자<input type="radio" name="gender" value="false" checked="checked">
				     <%	} %>
			</tr>
			</table>
			</p>
		</td>
		<td valign="top">
<p>
		<table border="1"; bordercolor="#CD853F">
			<tr>
				<th bgcolor="#CD853F" style="color:white;">대출현황</th>
			</tr>
			</table>
			</p>
			<p>
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
<input type="button" value="탈퇴" onclick="location.href='memberDeleteForm.do'" style= "background-color:#CD853F; color:white; font-weight:bold;">
		<input type="submit" value="수정하기" style= "background-color:#CD853F; color:white; font-weight:bold;">
		<input type="reset"  value="다시" style= "background-color:#CD853F; color:white; font-weight:bold;">
		<input type="button" value="메인" onclick="location.href='main3.do'";
		 style= "background-color:#CD853F; color:white; font-weight:bold;">			
		</form>	
</p>
</font>
<jsp:include page="FooterForUser.jsp"/>

</center>	
</body>
</html>