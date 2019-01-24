package service;

import java.util.List;

import Dao.MemberDao;
import model.Member;

public class memberService {
	public MemberDao memberDao;

	public memberService() {
		memberDao = MemberDao.getInstance();
	}

	public boolean spellingCheck(String msg) {
		for (int i = 0; i < msg.length(); i++) {
			char chr = msg.charAt(i);
			if (chr >= 0x61 && chr <= 0x7A) {
				// 영문(소문자) OK!
			} else if (chr >= 0x41 && chr <= 0x5A) {
				// 영문(대문자) OK!
			} else if (chr >= 0x30 && chr <= 0x39) {
				// 숫자 OK!
			} else {
				return false; // 영문자도 아니고 숫자도 아님!
			}
		}
		return true;
	}

	

	public boolean logIn(String userId, String pw) {
		List<Member> list = memberDao.SelectOne(userId);
		if (list.size() == 0)
			return false;
		else {
			if (list.get(0).getPw().equals(pw))
				return true;
			else
				return false;

		}

	}

	public boolean memberSort(String userId) {
		if (memberDao.SelectOne(userId).size() != 0) {// 아이디 있는지 확인
			if (memberDao.SelectOne(userId).get(0).isAdmin()) {// 아이디의 admin true, false 확인
				return true;// 관리자면 true
			} else {
				return false;// 사용자면 false
			}
		} else {
			return false;// 로그인상태가 아니면 false
		}
	}

	public boolean idCheck(String userId) {
		if (memberDao.SelectOne(userId).size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Member> memberSelectOne(String msg) {
		return memberDao.SelectOne(msg);
	}

	public void memberDelete(String userId) {
		memberDao.memberDelete(userId);
	}

	public boolean memberUpdate(String userId, String pw, String phone, String addr, String age, boolean gender) {
		String UserId = userId;
		String Addr = addr;
		String Phone = phone;
		String Age = age;
		boolean Gender = gender;
		List<Member> list = memberDao.SelectOne(userId);
		Member originMember = list.get(0);
		if (originMember.getPw().equals(pw)) {
			memberDao.memberUpdate(UserId, Phone, Addr, Age, Gender);
			return true;
		} else
			return false;
	}

	public List<Member> memberSelectAll() {
		return memberDao.SelectAll();

	}

	public boolean memberJoin(String num, String id, String pw, String name, String addr, String phone, String age,
			boolean gender, boolean admin) {
		if (memberDao.SelectOne(id).size() == 0) {
			Member member = new Member();
			member.setNum(num);
			member.setUserId(id);
			member.setPw(pw);
			member.setName(name);
			member.setAddr(addr);
			member.setPhone(phone);
			member.setAge(age);
			member.setGender(gender);
			member.setAdmin(admin);
			memberDao.memberInsert(member);
			return true;
		}
		return false;
	}

}
