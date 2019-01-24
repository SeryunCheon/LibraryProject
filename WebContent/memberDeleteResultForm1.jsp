<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 탈퇴완료</title>
</head>
<body>
<center>
<jsp:include page="HeaderForUser.jsp"/>
<font color="#CD853F"/>
<h1> ${msg} </h1>
<p>
<input type ="button"  value="메인으로"   style= "background-color:#FFC341; color:white; font-weight:bold;" onclick="location.href='main1.do'" >
</p>
<jsp:include page="FooterForUser.jsp"/>
</center>	
</body>
</html>