package kr.co.bit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.MemberVO;
import kr.co.bit.util.ConnectionFactory;

public class MemberDAO {
	public boolean joinMember(MemberVO member) {
		boolean result = false;

		StringBuilder sql = new StringBuilder();
		sql.append(
				"insert into t_member (id, name, password, email_id, email_domain, tel1, tel2, tel3, post, basic_addr, detail_addr, reg_date) ");
		sql.append(" values (?,?,?,?,?,?,?,?,?,?,?,sysdate)");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setString(1, member.getId());
			st.setString(2, member.getName());
			st.setString(3, member.getPassword());
			st.setString(4, member.getEmail_id());
			st.setString(5, member.getEmail_domain());
			st.setString(6, member.getTel1());
			st.setString(7, member.getTel2());
			st.setString(8, member.getTel3());
			st.setString(9, member.getPost());
			st.setString(10, member.getBasic_addr());
			st.setString(11, member.getDetail_addr());

			if (st.executeUpdate() > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {

		}

		return result;
	}

	public MemberVO loginMember(MemberVO member) {
		MemberVO result = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, type from t_member where ");
		sql.append(" id=? and password=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setString(1, member.getId());
			st.setString(2, member.getPassword());
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				result = new MemberVO();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setType(rs.getString("type"));
			}

		} catch (Exception e) {

		}

		return result;
	}

	public MemberVO getMember(String id) {
		MemberVO result = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_member where id=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setString(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				result = new MemberVO();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setTel1(rs.getString("tel1"));
				result.setTel2(rs.getString("tel2"));
				result.setTel3(rs.getString("tel3"));
				result.setEmail_id(rs.getString("email_id"));
				result.setEmail_domain(rs.getString("email_domain"));
				result.setBasic_addr(rs.getString("basic_addr"));
				result.setDetail_addr(rs.getString("detail_addr"));
				result.setReg_date(rs.getString("reg_date"));
				
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//	System.out.println(result);

		return result;
	}

	public List<MemberVO> getMembers() {
		List<MemberVO> result = new ArrayList<MemberVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_member");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setEmail_id(rs.getString("email_id"));
				member.setEmail_domain(rs.getString("email_domain"));
				member.setBasic_addr(rs.getString("basic_addr"));
				member.setDetail_addr(rs.getString("detail_addr"));
				member.setReg_date(rs.getString("reg_date"));

				result.add(member);
			}

			System.out.println(result.get(0).getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean update(MemberVO member) {
		boolean result = false;

		StringBuilder sql = new StringBuilder();
		sql.append("update t_member set name=?, email_id=?, email_domain=?,");
		sql.append(" basic_addr=?, detail_addr=?, tel1=?, tel2=?, tel3=?, post=? ");
		
		if(member.getPassword()!=null){
			sql.append(", password=? ");
		}
		
		sql.append(" where id=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setString(1, member.getName());
			st.setString(2, member.getEmail_id());
			st.setString(3, member.getEmail_domain()); //!=null?member.getEmail_domain():" ");
			st.setString(4, member.getBasic_addr()); //!=null?member.getBasic_addr():null);
			st.setString(5, member.getDetail_addr()); //!=null?member.getDetail_addr():null);
			st.setString(6, member.getTel1()); //!=null?member.getTel1():null);
			st.setString(7, member.getTel2()); //!=null?member.getTel2():null);
			st.setString(8, member.getTel3()); // !=null?member.getTel3():null);
			st.setString(9, member.getPost()); //!=null?member.getPost():null);
			if(!member.getPassword().equals("")){
				st.setString(10, member.getPassword());
				st.setString(11, member.getId());
			}else {
				st.setString(10, member.getId());
			}

			if (st.executeUpdate() > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;

	}
	
	public boolean checkID(String id){
		boolean result = false;
		
		
		
		return result;
	}
}
