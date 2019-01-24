package model;

public class Booklog {
int bookno;
String date;
String state;
public int getBookno() {
	return bookno;
}
public void setBookno(int bookno) {
	this.bookno = bookno;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
@Override
public String toString() {
	return "Booklog [bookno=" + bookno + ", date=" + date + ", state=" + state + "]";
}
	
	
	
}
