package kr.co.bit.login.service;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class LoginService {
	
	private MemberDAO dao;
	
	public LoginService(MemberDAO dao){
		this.dao = dao;
	}
	
	public MemberVO login(MemberVO member){		
		return dao.loginMember(member);
	}

}
