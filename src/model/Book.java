package model;

public class Book {
	
	private String bookNo;
	private String bookTitle;
	private String bookAuthor;
	private String bookState;
	private String bookRentDate;
	private String bookReturnDate;
	private String userId;
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookState() {
		return bookState;
	}
	public void setBookState(String bookState) {
		this.bookState = bookState;
	}
	public String getBookRentDate() {
		return bookRentDate;
	}
	public void setBookRentDate(String bookRentDate) {
		this.bookRentDate = bookRentDate;
	}
	public String getBookReturnDate() {
		return bookReturnDate;
	}
	public void setBookReturnDate(String bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", bookState="
				+ bookState + ", bookRentDate=" + bookRentDate + ", bookReturnDate=" + bookReturnDate + ", userId="
				+ userId + "]";
	}
	
	

}
