<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>float</title>
<style>
  ul{width:1500px;height:44px;/*background:white;*/list-style:none;padding-top:15px;  
margin-top:300px;margin-left:268px;margin-right:45px;} 
 ul li{float:left;margin-left:60px;font-family:Georgia} 
 ul li a{font-size:20px;color:white;font-weight:bold;text-decoration:none} 



/*  ul{width:947px;height:44px;background:white;list-style:none;padding-top:15px;  */
/* margin-top:300px;margin-left:45px;margin-right:45px;}  */
/*  ul li{float:left;margin-left:125px;font-family:바탕체}  */
/*  ul li a{font-size:25px;color:#8B4513;font-weight:bold;text-decoration:none}  */
/*  .white a{color:#8B4513}  */



div{
/* margin-left:45px; */
width: 1800px;
height: 436px;
border: 1px solid white;
background-image: url('AdminHeader.jpg');
/* background-color:  */
}

</style>
<title>관리자페이지용 헤더</title>
</head>
<body>
<div>
<ul>
<li><a href="bookAddForm.do">Add Book</a></li>
<li><a href="bookDeleteForm.do">Delete Book</a></li>
<li><a href="booklistForm.do">Book State</a></li>
<li><a href="memberSelectForm.do">List of all the users</a></li>
<li><a href="log.do">Book Log state</a></li>
<li><a href="logOut.do">Log out</a></li>
</ul>
</div>

</body>
</html>


