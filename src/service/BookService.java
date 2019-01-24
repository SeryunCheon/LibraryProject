package service;

import java.util.List;

import Dao.BookDao;
import model.Book;
import model.Booklog;

public class BookService {

	private BookDao bookdao;

	public BookService() {
		bookdao = bookdao.getInstance();
	}

	public void bookAdd(String bookTitle, String bookAuthor){
		int s = bookdao.bookSelectAllGenaral().size();
		int a= 0;
				for(Book boo : bookdao.bookSelectAllGenaral()) {
					int b = Integer.parseInt(boo.getBookNo());
					a=Math.max(a, b);
				}
		int booknum = a+1;
		String num = String.valueOf(booknum);
		Book book = new Book();
		book.setBookNo(num);
		book.setBookTitle(bookTitle);
		book.setBookAuthor(bookAuthor);
		book.setBookState("대출가능");
		bookdao.bookInsert(book);
		int b = Integer.parseInt(num);
		String c = "입고";
		bookdao.booklogInsert(b,c);
		
	}
	public List<Booklog> booklogSelectAll() {
		//리턴 : 책 로그 전체 보기
		return bookdao.booklogSelectAll();
	}
	public List<Booklog> booklogSelectOne(String text) {
		//리턴 : 책 로그 검색//책번호 또는 유저id, 내용
		return bookdao.booklogSelect(text);
	}	
	
	public boolean bookDelete(String BookNo) {		
		bookdao.bookDelete(BookNo);
		int b = Integer.parseInt(BookNo);
		String c = "출고";
		bookdao.booklogInsert(b,c);
		return true;
	}

	public List<Book> bookSelectAllGenaral() {
		//리턴 : bookNo로 오름차순 
		return bookdao.bookSelectAllGenaral();
	}
	
	
	public List<Book> bookSelectAllAdmin() {
		//리턴 : bookdate로 오름차순
		return bookdao.bookSelectAllAdmin();
	}
	
	
	//id로 book table 조회, 조회한 값을 list에 담아서리턴 시켜줘야함
	public List<Book> bookSelectAllById(String userid) {
		
		return bookdao.bookTablebyId(userid);
	}

	//BookNo로 book table 조회, 조회한 값을 list에 담아서리턴 시켜줘야함
	public List<Book> bookSelectAllByBookNo(String BookNo) {
		
		return bookdao.bookTablebyBookNo(BookNo);
	}
	
	//리턴 : book table에서 state까지 나오는 테이블
	public List<Book> bookSearch(String msg) {		
		
		return bookdao.SelectOne(msg); 
	}

	//리턴 : book table에서 대여가능한 책목록
	public List<Book> BookRentAble() {		
		
		return bookdao.bookRent(); 
	}
	
	//리턴 : 현재 대출 중인 도서 테이블
	public List<Book> adminMainBookState() {

		return bookdao.bookAdminMainBookState();	
	}
	
	//리턴 : 대출 되는 도서
	public boolean bookRent(String userId, String bookNo) {
		if(bookdao.SelectAllByBookno(bookNo).getBookState().equals("대출가능")) {
			String no = bookNo;
			String BookState ="대출중";
			String id = userId;
			
			bookdao.bookUpdate(no, BookState, id);
			int b = Integer.parseInt(bookNo);
			String c = id+ " 대출";
			bookdao.booklogInsert(b,c);
			
			
			
			return true;
		}

		return false;
		
	}
	
	//리턴 : 반납 되는 도서
	public boolean bookReturn(String BookNo) {
		Book tempBook =	bookdao.SelectAllByBookno(BookNo);
		String id =tempBook.getUserId();
		if(tempBook.getBookState().equals("대출중")){
			String UserId ="-";
			String BookState="대출가능";
			String No = BookNo;
			bookdao.bookUpdate(No, BookState, UserId);
			int b = Integer.parseInt(BookNo);
			String c = id+ " 반납";
			bookdao.booklogInsert(b,c);
			
			return true;
		}
		return false;
	}	

}
