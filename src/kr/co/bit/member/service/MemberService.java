package kr.co.bit.member.service;

import java.util.List;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class MemberService {
	private MemberDAO dao;

	public MemberService() {
	}

	public MemberService(MemberDAO dao) {
		this.dao = dao;
	}

	/**
	 * 회원 정보
	 * @param memberId
	 * @return
	 */
	public MemberVO getMember(String memberId){
		return dao.getMember(memberId);
	}
	
	/**
	 * 회원 가입
	 * @param member
	 * @return true/false
	 */
	public boolean joinMember(MemberVO member){
		return dao.joinMember(member);
	}
	
	/**
	 * 회원 관리용 전체 회원 목록
	 * @return list
	 */
	public List<MemberVO> getMembers(){
		return dao.getMembers();
	}
	
}
