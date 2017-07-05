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
	 * ȸ�� ����
	 * @param memberId
	 * @return
	 */
	public MemberVO getMember(String memberId){
		return dao.getMember(memberId);
	}
	
	/**
	 * ȸ�� ����
	 * @param member
	 * @return true/false
	 */
	public boolean joinMember(MemberVO member){
		return dao.joinMember(member);
	}
	
	/**
	 * ȸ�� ������ ��ü ȸ�� ���
	 * @return list
	 */
	public List<MemberVO> getMembers(){
		return dao.getMembers();
	}
	
}
