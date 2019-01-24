package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Member;

public class MemberDao {

	private static MemberDao instance;

	public static MemberDao getInstance() {
		if (instance == null)
			instance = new MemberDao();

		return instance;
	}

	private Connection conn;
	private static String URL = "jdbc:mysql://localhost:3306/test";
	private static String USERNAME = "root";
	private static String PASSWORD = "0213";

	private MemberDao() {
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

	public void memberInsert(Member m) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(m.getNum()));
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getPw());
			pstmt.setString(4, m.getName());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddr());
			pstmt.setString(7, m.getAge());
			pstmt.setBoolean(8, m.isGender());
			pstmt.setBoolean(9, m.isAdmin());
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

	public void memberUpdate(String UserId,String phone,String addr,String age,boolean gender) {
		String sql = "update member set phone=?, Addr=?, Age=?, Gender=? where userid like ?;";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(5, UserId);
			pstmt.setString(1, phone);
			pstmt.setString(2, addr);
			pstmt.setString(3, age);
			pstmt.setBoolean(4, gender);
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

	public void memberDelete(String userId) {
		String sql = "delete from member where userid like ?;";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	public List<Member> SelectOne(String msg) {
		String sql = "select * from member where userid like ?|| name like ?;";
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+msg+"%");
			pstmt.setString(2, "%"+msg+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member();
				m.setNum(rs.getString("num"));
				m.setUserId(rs.getString("userid"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setAddr(rs.getString("addr"));
				m.setAge(rs.getString("age"));
				m.setGender(rs.getBoolean("gender"));
				m.setAdmin(rs.getBoolean("admin"));
				list.add(m);
			}

		} catch (SQLException e) {
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
		return list;

	}

	public List<Member> SelectAll() {
		String sql = "select * from member order by num;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Member> list = new ArrayList<Member>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member m = new Member();

				m.setNum(rs.getString("num"));
				m.setUserId(rs.getString("userid"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setAddr(rs.getString("addr"));
				m.setAge(rs.getString("age"));
				m.setGender(rs.getBoolean("gender"));
				m.setAdmin(rs.getBoolean("admin"));

				list.add(m);
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
		return list;
	}
}
