package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.Booklog;
import model.Member;
import service.BookService;
import service.memberService;

public class libServlet extends HttpServlet {

	public BookService bookservice;
	public memberService memberservice;

	public libServlet() {
		bookservice = new BookService();
		memberservice = new memberService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req, resp);
	}

	protected void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextpath = req.getContextPath();
		String reqUri = req.getRequestURI();

		if (reqUri.equals(contextpath + "/main1.do")) {// 로그인전 화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid == null) {
				List<Book> booklist = bookservice.bookSelectAllGenaral();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("mainForm1.jsp").forward(req, resp);
			} else {
				if (memberservice.memberSort(uid)) {
					resp.sendRedirect("main2.do");
				}else {
					resp.sendRedirect("main3.do");
				}
			}
		}

		else if (reqUri.equals(contextpath + "/main2.do")) {// 관리자 화면 이동/대출 리스트
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				List<Book> booklist = bookservice.adminMainBookState();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("mainForm2.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}

		else if (reqUri.equals(contextpath + "/main3.do")) {// 사용자 화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				List<Book> booklist = bookservice.bookSelectAllGenaral();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("mainForm3.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}

		} else if (reqUri.equals(contextpath + "/loginForm.do")) {// 로그인 화면 이동
			req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
		} else if (reqUri.equals(contextpath + "/login.do")) {// 로그인 실행
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			if (memberservice.logIn(id, pw)) {
				req.setAttribute("msg", id + "님 환영합니다.");
				req.getSession().setAttribute("id", id);
				req.getSession().setAttribute("pw", pw);
				if (memberservice.memberSort(id)) {
					resp.sendRedirect("main2.do");// 관리자 메인 이동
				} else {
					resp.sendRedirect("main3.do");// 사용자 메인 이동
				}
			} else {
				req.setAttribute("msg", "아이디, 패스워드를 확인하세요.");
				req.getRequestDispatcher("loginForm.jsp").forward(req, resp);// 로그인실패
			}
		} else if (reqUri.equals(contextpath + "/joinForm.do")) {// 회원가입 화면 이동
			req.getRequestDispatcher("joinForm.jsp").forward(req, resp);

		} else if (reqUri.equals(contextpath + "/join.do")) {// id 중복검사, pw 중복검사, 회원가입
			req.setCharacterEncoding("UTF-8");
			String id = req.getParameter("id");
			if (id.length() > 3) {// id 글자수 제한
				if (memberservice.spellingCheck(id)) {// 특수문자 확인 ->없으면 진행
					if (memberservice.idCheck(id)) { // id 중복여부 확인
						req.setAttribute("msg1", "사용 가능한 아이디입니다.");
						req.setAttribute("id", id);
						String pw1 = req.getParameter("pw1");
						String pw2 = req.getParameter("pw2");
						if (pw2 != null && pw1 != null && pw1 != "") {// 패스워드 빈칸 제한
							if (pw1.length() > 7) {// 패스워드 8자이상으로
								if (pw1.equals(pw2)) {
									req.setAttribute("msg2", "비밀번호가 일치합니다.<br>모든 사항을 입력해주세요.");
									req.setAttribute("pw1", pw1);
									req.setAttribute("pw2", pw2);
									String name = req.getParameter("name");
									String addr = req.getParameter("addr");
									String phone = req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-"
											+ req.getParameter("phone3");
									String age = req.getParameter("age");
									if (name != null && addr != null && req.getParameter("phone2") != null
											&& req.getParameter("phone3") != null && age != null && !name.equals("")
											&& !addr.equals("") && !req.getParameter("phone2").equals("")
											&& !req.getParameter("phone3").equals("") && !age.equals("")) {
										boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
										boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
										int a = 0;
										for (Member m : memberservice.memberSelectAll()) {
											int b = Integer.parseInt(m.getNum());
											a = Math.max(a, b);
										}
										String num = String.valueOf(a + 1);
										String pw = pw1;
										if (memberservice.memberJoin(num, id, pw, name, addr, phone, age, gender,
												admin)) {
											req.setAttribute("msg", "회원가입 되셨습니다.");
											req.getRequestDispatcher("joinResultForm.jsp").forward(req, resp);
										} else {
											req.setAttribute("msg", "회원가입에 실패하셨습니다.");
											req.getRequestDispatcher("joinForm.jsp").forward(req, resp);
										}
									}

								} else {
									req.setAttribute("msg2", "비밀번호 다릅니다.");
								}
							} else {
								req.setAttribute("msg2", "비밀번호는 8자이상 입력해주세요");
							}
						} else {
						}
					} else {
						req.setAttribute("msg1", "이미 존재하는 아이디입니다.");
					}
				} else {
					req.setAttribute("msg1", "아이디는 알파벳, 숫자만 입력가능합니다.");
				}
			} else {
				req.setAttribute("msg1", "아이디는 4자이상 입력해주세요.");
			}
			req.getRequestDispatcher("joinForm.jsp").forward(req, resp);

		}
		// 로그인전 검색 기능
		else if (reqUri.equals(contextpath + "/bookSearchFirst.do")) {// 로그인 전 메인 페이지에서 검색 버튼 누르면 호출URI
			req.setCharacterEncoding("UTF-8");
			String msg = req.getParameter("msg");
			List<Book> booklist = bookservice.bookSearch(msg);
			if (booklist.size() == 0) {
				req.setAttribute("msg", "이책은 도서관에 없습니다.");
			}
			req.setAttribute("booklist", booklist);
			req.getRequestDispatcher("mainForm1.jsp").forward(req, resp);
		}

		// 사용자 메뉴
		else if (reqUri.equals(contextpath + "/bookSearch.do")) {// 메인 페이지에서 검색 버튼 누르면 호출URI
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String msg = req.getParameter("msg");
				List<Book> booklist = bookservice.bookSearch(msg);
				if (booklist.size() == 0) {
					req.setAttribute("msg", "이책은 도서관에 없습니다.");
				}
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("mainForm3.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/bookRentSearch.do")) {// 사용자 책 대여 페이지에서 검색부튼 누르면 호출URI
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String msg = req.getParameter("msg");
				List<Book> booklist = bookservice.bookSearch(msg);
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("bookRentForm.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/bookRentForm.do")) { // 사용자 책 대여페이지 호출, 사용자 책 대여화면으로 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				List<Book> booklist = bookservice.bookSelectAllGenaral();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("bookRentForm.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/bookRent.do")) { // 사용자 책 대여페이지의 도서목록 옆에 대출버튼 누르면 호출되는 URI
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String bookNo = req.getParameter("bookNo");
				List<Book> booklist = bookservice.bookSelectAllById(uid);
				if (booklist.size() == 3) {
					req.setAttribute("booklist", booklist);
					req.setAttribute("msg", "대출 불가 ( 대출 권수 최대 3권)");
					req.getRequestDispatcher("bookRentResultForm.jsp").forward(req, resp);
				} else {
					if (bookservice.bookRent(uid, bookNo)) {
						resp.sendRedirect("bookRentForm.do");
					} else {
						req.setAttribute("booklist", booklist);
						req.setAttribute("msg", "현재 대여중인 책입니다.");
						req.getRequestDispatcher("bookRentResultForm.jsp").forward(req, resp);
					}
				}
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/bookReturnForm.do")) { // 사용자 책 반납페이지 화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String id = (String) req.getSession().getAttribute("id");
				List<Book> booklist = bookservice.bookSelectAllById(id);
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("bookReturnForm.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}
		}

		else if (reqUri.equals(contextpath + "/bookReturn.do")) { // 사용자 책 반납
			String uid = (String) req.getSession().getAttribute("id");
			String num = req.getParameter("bookNo");
			if (uid != null) {
				if (bookservice.bookReturn(num)) {
					List<Book> booklist = bookservice.bookSelectAllById(uid);
					if (booklist.size() == 0) {
						req.setAttribute("msg", "대출중인 책이 없습니다.");
						req.setAttribute("booklist", booklist);
						req.getRequestDispatcher("bookReturnForm.jsp").forward(req, resp);
					} else {
						req.setAttribute("booklist", booklist);
						req.getRequestDispatcher("bookReturnForm.jsp").forward(req, resp);
					}
				}
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/memberUpdateForm.do")) {// 회원정보 수정 화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				List<Member> list = memberservice.memberSelectOne(uid);
				List<Book> booklist = bookservice.bookSelectAllById(uid);
				Member member = list.get(0);
				req.setAttribute("booklist", booklist);
				req.setAttribute("member", member);
				req.getRequestDispatcher("memberUpdateForm.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/memberUpdate.do")) {// 회원정보 수정 실행
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String id = req.getParameter("id");
				String pw = req.getParameter("pw");
				String addr = req.getParameter("addr");
				String phone = req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-"
						+ req.getParameter("phone3");
				String age = req.getParameter("age");
				boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
				List<Member> list = memberservice.memberSelectOne(id);
				Member orginmember = list.get(0);
				List<Book> booklist = bookservice.bookSelectAllById(id);
				req.setAttribute("booklist", booklist);
				if (orginmember.getPw().equals(pw)) {
					if (memberservice.memberUpdate(id, pw, phone, addr, age, gender)) {
						req.setAttribute("msg", "수정되었습니다.");
						List<Member> memberlist = memberservice.memberSelectOne(id);
						Member member = memberlist.get(0);
						req.setAttribute("member", member);
						req.getRequestDispatcher("memberUpdateResultForm.jsp").forward(req, resp);
					}
				} else {
					req.setAttribute("msg", "비밀번호가 다릅니다");
					req.setAttribute("member", orginmember);
					req.getRequestDispatcher("memberUpdateResultForm.jsp").forward(req, resp);
				}
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/memberDeleteForm.do")) {// 회원 탈퇴 화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				List<Book> booklist = bookservice.bookSelectAllById(uid);
				if (booklist.size() != 0) {
					req.setAttribute("msg", "대여중인 책이 있습니다.");
					req.setAttribute("booklist", booklist);
					req.getRequestDispatcher("membercantdeleteForm.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("memberDeletePWcheckForm.jsp").forward(req, resp);
				}
			} else {
				resp.sendRedirect("main1.do");
			}
		} else if (reqUri.equals(contextpath + "/memberDeleteGeneral.do")) {// 회원 탈퇴 실행
			String pw = req.getParameter("pw");
			String uid = (String) req.getSession().getAttribute("id");
			if (uid != null) {
				String pw1 = (String) req.getSession().getAttribute("pw");
				if (pw.equals(pw1)) {
					memberservice.memberDelete(uid);
					req.isRequestedSessionIdValid();
					req.setAttribute("msg", "탈퇴되었습니다.");
					req.getRequestDispatcher("memberDeleteResultForm1.jsp").forward(req, resp);
					// 로그인 전 페이지로 보내는 버튼있는 jsp
				} else {
					req.setAttribute("msg", "비밀번호가 다릅니다.");
					req.getRequestDispatcher("memberDeleteResultForm.jsp").forward(req, resp);
					// 일반사용자 메인페이지로 보내는 버튼있는 jsp
				}
			} else {
				resp.sendRedirect("main1.do");
			}

		} else if (reqUri.equals(contextpath + "/logOut.do")) {// 로그아웃
			req.getSession().invalidate();
			resp.sendRedirect("main1.do");
		}
		// 사용자메뉴 끝

		// 관리자메뉴 시작
		else if (reqUri.equals(contextpath + "/log.do")) {// 관리자 화면 이동/로그리스트
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				List<Booklog> loglist = bookservice.booklogSelectAll();
				req.setAttribute("loglist", loglist);
				req.getRequestDispatcher("logForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}else if (reqUri.equals(contextpath + "/booklogSearch.do")) { // 관리자 로그 검색
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String msg = req.getParameter("msg");
				List<Booklog> loglist = bookservice.booklogSelectOne(msg);
				if (loglist.size() == 0) {
					req.setAttribute("msg", "기록이 없습니다.");
				}
				req.setAttribute("loglist", loglist);
				req.getRequestDispatcher("logForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}
		
		

		else if (reqUri.equals(contextpath + "/bookAddForm.do")) {// 관리자 책입고 페이지 호출
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				resp.sendRedirect("bookAddForm.jsp");
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}

		else if (reqUri.equals(contextpath + "/bookAdd.do")) {// 관리자 책입고
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String bookTitle = req.getParameter("bookTitle");
				String bookAuthor = req.getParameter("bookAuthor");
				bookservice.bookAdd(bookTitle, bookAuthor);
				req.setAttribute("msg", "책제목: " + bookTitle + ", 책저자: " + bookAuthor + " 입고 완료");
				req.getRequestDispatcher("bookAddResultForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/bookDeleteForm.do")) { // 책출고 페이지이동
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				List<Book> booklist = bookservice.BookRentAble();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("bookDeleteForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/bookDeleteSearchAdmin.do")) { // 관리자 출고 책 검색
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String msg = req.getParameter("msg");
				List<Book> booklist = bookservice.bookSearch(msg);
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("bookDeleteForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/bookDeleteRemind.do")) {// 관리자 책출고 리마인드 페이지 호출
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String bookno = req.getParameter("bookNo");
				List<Book> booklist = bookservice.bookSelectAllByBookNo(bookno);
				if (booklist.size() != 0) {
					if (booklist.get(0).getUserId().equals("-")) {
						req.setAttribute("booklist", booklist);
						req.getRequestDispatcher("bookDeleteRemind.jsp").forward(req, resp);
					} else {
						List<Book> list = bookservice.BookRentAble();
						req.setAttribute("msg", "현재 대출중인 책입니다.");
						req.setAttribute("booklist", list);
						req.getRequestDispatcher("bookDeleteForm.jsp ").forward(req, resp);
					}
				}
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}

		else if (reqUri.equals(contextpath + "/bookDeleteResult.do")) {// 관리자 책출고 리마인드페이지에서 출고(예)버튼 누르면나오는 최종결과페이지 호출
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String bookno = req.getParameter("bookNo");
				bookservice.bookDelete(bookno);
				req.setAttribute("msg", "출고 되었습니다.");
				req.getRequestDispatcher("bookDeleteResult.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/booklistForm.do")) { // 관리자 책현황 보기
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				List<Book> booklist = bookservice.bookSelectAllGenaral();
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("booklistForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/bookListSearchAdmin.do")) { // 관리자 책현황 검색
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String msg = req.getParameter("msg");
				List<Book> booklist = bookservice.bookSearch(msg);
				if (booklist.size() == 0) {
					req.setAttribute("msg", "이책은 도서관에 없습니다.");
				}
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("booklistForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}

		else if (reqUri.equals(contextpath + "/memberSelectForm.do")) {// 관리자 전체회원조회
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				List<Member> list = memberservice.memberSelectAll();
				req.setAttribute("list", list);
				req.getRequestDispatcher("memberSelectForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/memberSelectOne.do")) {// 관리자 회원 1명조회
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String msg = req.getParameter("msg");
				if (memberservice.memberSelectOne(msg) != null) {
					List<Member> list = memberservice.memberSelectOne(msg);
					req.setAttribute("list", list);
				} else {
					req.setAttribute("msg", "해당 되는 회원이 없습니다.");
				}

				req.getRequestDispatcher("memberSelectForm.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/memberDeleteAdmin.do")) {// 관리자 회원 삭제
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String id = req.getParameter("id");
				List<Book> list = bookservice.bookSelectAllById(id);
				if (list.size() == 0) {
					if (uid.equals(id)) {// 관리자(사용중인) 계정 삭제시
						req.getSession().invalidate();
						memberservice.memberDelete(id);
						req.setAttribute("msg", "입력하신 회원정보가 삭제되었습니다.");
					} else {
						memberservice.memberDelete(id);
						req.setAttribute("msg", "입력하신 회원정보가 삭제되었습니다.");
					}
				} else {
					req.setAttribute("msg", "입력하신 회원은 현재 책을 대출 중입니다.");
				}
				req.setAttribute("list", list);
				req.getRequestDispatcher("memberDeleteResultForm2.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		} else if (reqUri.equals(contextpath + "/memberUpdateFormAdmin.do")) {// 관리자 /회원 정보화면 이동
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String id = req.getParameter("id");
				List<Book> booklist = bookservice.bookSelectAllById(id);
				List<Member> memberlist = memberservice.memberSelectOne(id);
				Member member = memberlist.get(0);
				req.setAttribute("member", member);
				req.setAttribute("booklist", booklist);
				req.getRequestDispatcher("memberUpdateFormAdmin.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}

		} else if (reqUri.equals(contextpath + "/memberUpdateResultAdmin.do")) {
			req.setCharacterEncoding("UTF-8");
			String uid = (String) req.getSession().getAttribute("id");
			if (memberservice.memberSort(uid)) {
				String id = req.getParameter("id");
				String addr = req.getParameter("addr");
				String phone = req.getParameter("phone1") + "-" + req.getParameter("phone2") + "-"
						+ req.getParameter("phone3");
				String age = req.getParameter("age");
				boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
				List<Member> list = memberservice.memberSelectOne(id);
				Member orginmember = list.get(0);
				String pw = orginmember.getPw();
				if (memberservice.memberUpdate(id, pw, phone, addr, age, gender)) {
					req.setAttribute("msg", "수정되었습니다.");
				} else {
					req.setAttribute("msg", "비밀번호를 확인하세요.");
				}
				Member member = new Member();
				List<Book> booklist = bookservice.bookSelectAllById(id);
				member = memberservice.memberSelectOne(id).get(0);
				req.setAttribute("booklist", booklist);
				req.setAttribute("member", member);
				req.getRequestDispatcher("memberUpdateResultFormAdmin.jsp").forward(req, resp);
			} else {
				if (uid == null) {
					resp.sendRedirect("main1.do");
				} else {
					resp.sendRedirect("main3.do");
				}
			}
		}

	}
}
