package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Book;
import model.Booklog;

public class BookDao {

	private static BookDao instance;

	public static BookDao getInstance() {
		if (instance == null)
			instance = new BookDao();
		return instance;
	}

	private Connection conn;
	private static String URL = "jdbc:mysql://localhost:3306/test";
	private static String USERNAME = "root";
	private static String PASSWORD = "0213";

	private BookDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------
	public void bookInsert(Book book) {
		String sql = "insert into book values (?, ?, ?, ?, curdate(), curdate(), '-');";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setString(3, book.getBookAuthor());
			pstmt.setString(4, book.getBookState());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// ------------------------------------------------------------
	public void bookDelete(String bookno) {
		String sql = "delete from book where bookNo = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bookno));
			pstmt.executeUpdate();// �쁾
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// -------------------------------------------------------------
	public void bookUpdate(String BookNo, String BookState, String UserId) {
		String sql = "update book set bookState = ?, bookRentDate=curdate(), bookReturnDate=date_add(curdate(), interval 14 day), userId=? where bookNo=?; ";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(3, Integer.parseInt(BookNo));
			pstmt.setString(1, BookState);
			pstmt.setString(2, UserId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// -------------------------------------------------------------

	public List<Book> SelectOne(String text) {
		String sql = "select*from book where bookTitle like ? || bookAuthor like ?";
		PreparedStatement pstmt = null;
		Book book = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");
			pstmt.setString(2, "%" + text + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));		
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userId"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed())
					pstmt.close();
				if (rs != null && rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾

	}

	// ----------------------------------------------------------------

	public Book SelectAllByBookno(String bookno) {
		String sql = "select* from book where bookNo = ? ";
		PreparedStatement pstmt = null;
		Book book = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed())
					pstmt.close();
				if (rs != null && rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;

	}

	// ---------------------------------------------------------------------------------------
	public List<Book> bookTablebyId(String userid) {
		String sql = "select* from book where userId like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userId"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// ---------------------------------------------------------------------------------------
	public List<Book> bookTablebyBookNo(String BookNo) {
		String sql = "select* from book where BookNo=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BookNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				book.setBookRentDate(rs.getString("bookReturnDate"));
				Date date = new Date();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String date0 = sim.format(date.getTime());
				Date date1 = sim.parse(date0);
				Date date2 = sim.parse(rs.getString("bookReturnDate"));
				long diff = date2.getTime() - date1.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if (diffDays < 0) {
					book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
				} else {
					book.setBookReturnDate(rs.getString("bookReturnDate"));
				}
				book.setUserId(rs.getString("userId"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// ----------------------------------------------------------------

	// --------------------------------------------------------------

	public List<Book> bookSelectAllAdmin() {
		String sql = "select* from book order by bookReturnDate";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Book> list = new ArrayList<Book>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getString("bookno"));
				book.setBookTitle(rs.getString("booktitle"));
				book.setBookAuthor(rs.getString("bookauthor"));
				book.setBookState(rs.getString("bookstate"));
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userid"));

				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}
	// ------------------------------------------------------------------

	public List<Book> bookSelectAllGenaral() {
		String sql = "select * from book order by bookNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Book> list = new ArrayList<Book>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userId"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}

	// ------------------------------------------------------------------

	public List<Book> bookAdminMainBookState() {
		String sql = "select * from book where userId not like '-' order by bookReturnDate";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Book> list = new ArrayList<Book>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Book book = new Book();

				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				if (rs.getString("userId").equals("-")) {
					book.setBookRentDate("-");
					book.setBookReturnDate("-");
				} else {
					book.setBookRentDate(rs.getString("bookRentDate"));
					Date date = new Date();
					SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
					String date0 = sim.format(date.getTime());
					Date date1 = sim.parse(date0);
					Date date2 = sim.parse(rs.getString("bookReturnDate"));
					long diff = date2.getTime() - date1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					if (diffDays < 0) {
						book.setBookReturnDate(String.valueOf(Math.abs(diffDays)) + "�씪 �뿰泥댁쨷");
					} else {
						book.setBookReturnDate(rs.getString("bookReturnDate"));
					}
				}
				book.setUserId(rs.getString("userId"));

				list.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}

	public List<Book> bookRent() {
		String sql = "select * from book where userId like '-'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Book> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Book book = new Book();

				book.setBookNo(rs.getString("bookNo"));
				book.setBookTitle(rs.getString("bookTitle"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setBookState(rs.getString("bookState"));
				book.setBookRentDate(rs.getString("bookRentDate"));
				book.setBookReturnDate(rs.getString("bookReturnDate"));
				book.setUserId(rs.getString("userId"));
				list.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}

	// -----------------------------------------------------------------------booklog
	
	public void booklogInsert(int bookno, String state) {
		String sql = "insert into booklog values (?, sysdate(), ?);";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookno);
			pstmt.setString(2, state);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public List<Booklog> booklogSelectAll() {
		String sql = "select * from booklog";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booklog> list = new ArrayList<Booklog>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Booklog booklog = new Booklog();
				booklog.setBookno(rs.getInt("bookno"));
				booklog.setDate(rs.getString("date"));
				booklog.setState(rs.getString("state"));
				list.add(booklog);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}

	public List<Booklog> booklogSelect(String text) {
		int s = Integer.parseInt(text);
		String sql = "select * from booklog where bookno = ? || state like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booklog> list = new ArrayList<Booklog>();

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s);
			pstmt.setString(2, "%" + text + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Booklog booklog = new Booklog();
				booklog.setBookno(rs.getInt("bookno"));
				booklog.setDate(rs.getString("date"));
				booklog.setState(rs.getString("state"));
				list.add(booklog);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;// �쁾
	}
	
	
}
