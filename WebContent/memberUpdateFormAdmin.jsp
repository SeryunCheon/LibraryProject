<%@page import="java.util.StringTokenizer"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@page import="model.Member"%>
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
<font color="#CD853F" style="font-weight:bold;"/>
<jsp:include page="HeaderForAdmin.jsp"/>


<h1> "아래의 테이블에, 수정하실 회원정보를 입력해주세요. "</h1>


	<%
		Member m = (Member) request.getAttribute("member");
		List<Book> list = (List) request.getAttribute("booklist");
		String a = m.getPhone();
		StringTokenizer s = new StringTokenizer(a,"-");
	    String s1 = s.nextToken();
	    String s2 = s.nextToken();
	    String s3 = s.nextToken();
	%>
<table>
<tr>
<td>
	<form action="memberUpdateResultAdmin.do">
	<p>
		<table style="margin: auto; text-align: center;" border="3" bordercolor="#CD853F" cellspacing="4" cellpadding="7">
			<tr>
				<th style="font-weight:bold;">N o</th>
				<td><input type="text" name="num" value="<%=m.getNum()%>"
					readonly="readonly"
					style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 100px; border:none;"
					></td>
			</tr>
			<tr>
				<th style="font-weight:bold;">I D</th>
				<td><input type="text" name="id" value="<%=m.getUserId()%>"
					readonly="readonly"
					style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 150px; border:none;"></td>
			</tr>
			<tr>
				<th style="font-weight:bold;">이 름</th>
				<td><input type="text" name="name" value="<%=m.getName()%>"
					readonly="readonly"
					style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 150px; border:none;"></td>
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
				<td align="center" style="font-weight:bold;">주 소</td>
				<td><input type="text" name="addr" value="<%=m.getAddr()%>"
				style= "background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 150px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">나 이</td>
				<td><input type="text" maxlength="200" id="Text1" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" name="age" value="<%=m.getAge()%>"
				style= "IME-MODE:disabled; background-color: white; color:#8B4513; font-family:Georgia; font-weight:bold; height:27px; width: 150px; border:none;"></td>
			</tr>
			<tr>
				<td align="center" style="font-weight:bold;">성 별</td>
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
<p>
		</td>
		<td>
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		</td>
		<td>
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
			<%
 					for (Book book : list) {
				%>
			<tr>
				
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
<input type="button" value="회원 삭제" onclick="location.href='memberDeleteAdmin.do?id=<%=m.getUserId()%>'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 73px; border:1; border-color:#CD853F;">
<input type="submit" value="수정"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 62px; border:1; border-color:#CD853F;"> 
<input type="reset" value="다시"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 62px; border:1; border-color:#CD853F;">
<input type="button" value="메인" onclick="location.href='main2.do'"
style= "background-color:#8B4513; color:white; font-family:Georgia; font-weight:bold; height:27px; width: 62px; border:1; border-color:#CD853F;">
			</p>
			</form> 

<jsp:include page="FooterForAdmin.jsp"/>
</center>	
</body>
</html>