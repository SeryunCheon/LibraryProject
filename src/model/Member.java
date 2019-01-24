package model;

public class Member {
String num;
String userId;
String pw;
String name;
String addr;
String phone;
String age;
boolean gender;
boolean admin;
public boolean isAdmin() {
	return admin;
}
public void setAdmin(boolean admin) {
	this.admin = admin;
}


public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}


public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPw() {
	return pw;
}
public void setPw(String pw) {
	this.pw = pw;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public boolean isGender() {
	return gender;
}
public void setGender(boolean gender) {
	this.gender = gender;
}
@Override
public String toString() {
	return "Member [num=" + num + ", userId=" + userId + ", pw=" + pw + ", name=" + name + ", addr=" + addr + ", phone="
			+ phone + ", age=" + age + ", gender=" + gender + ", admin=" + admin + "]";
}



}
