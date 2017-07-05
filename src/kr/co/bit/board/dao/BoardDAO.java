package kr.co.bit.board.dao;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.ConnectionFactory;
import kr.co.bit.util.JDBCClose;

public class BoardDAO {

	/**
	 * 전체 게시물 목록 조회하는 기능
	 */

	public List<BoardVO> selectAllBoard() {

		List<BoardVO> boards = new ArrayList<>();

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, title, writer, to_char(reg_date,'yyyy-mm-dd') as reg_date ");
			sql.append(" from t_board order by no desc");

			st = conn.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String regDate = rs.getString("reg_date");

				BoardVO board = new BoardVO();
				board.setNo(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setRegDate(regDate);

				boards.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, st);
		}

		return boards;
	}

	public List<BoardVO> selectBoard(int nextPage){

		int size = 5;
		List<BoardVO> boards = new ArrayList<>();

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			int nextNo = (nextPage-1)*size;
			int lastNo = nextNo + size;

	/*		if (lastNo < size) {
				lastNo = size;
			}*/

			sql.append("select * ");
			sql.append(" from (select rownum r, s.no, s.title, s.writer, s.content, s.view_cnt, s.reg_date ");
			sql.append("        from (select * from t_board order by reg_date desc) s ");
			sql.append("        where rownum<=?) a ");
			sql.append(" where a.r>?");
			
		//	System.out.println(sql);

			st = conn.prepareStatement(sql.toString());
			st.setInt(1, lastNo);
			st.setInt(2, nextNo);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String regDate = rs.getString("reg_date");

				BoardVO board = new BoardVO();
				board.setNo(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setRegDate(regDate);

				boards.add(board);
			}
			
		//	System.out.println(nextNo+", "+lastNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, st);
		}

		return boards;
	}

	public boolean insertBoard(BoardVO board) {

		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_board (no, title, writer, content) ");
		// sql.append(" values (seq_t_board_no.nextval, ?, ?, ?) ");
		sql.append(" values (?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {
			int loc = 1;
			st.setInt(loc++, board.getNo());
			st.setString(loc++, board.getTitle());
			st.setString(loc++, board.getWriter());
			st.setString(loc++, board.getContent());

			if (st.executeUpdate() > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public BoardVO selectByNo(int no) {

		BoardVO board = new BoardVO();

		StringBuilder sql = new StringBuilder();
		sql.append("select no, title, writer, content, view_cnt, to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
		sql.append(" from t_board where no=?"); // +no);

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				board.setNo(rs.getInt("no"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getString("reg_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	public boolean updateViewCnt(BoardVO board) {
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update t_board set view_cnt = ?"); // view_cnt = view_cnt +
														// 1
		sql.append("where no=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setInt(1, board.getViewCnt());
			st.setInt(2, board.getNo());

			if (st.executeUpdate() > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 게시물 조회수
	 * @param no
	 * @return
	 */
	public boolean updateViewCnt(int no) {
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update t_board set view_cnt = view_cnt + 1");
		sql.append("where no=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setInt(1, no);

			if (st.executeUpdate() > 0) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateBoard(BoardVO board) {
		boolean result = false;

		StringBuilder sql = new StringBuilder();
		sql.append("update t_board set title=?, content=?, writer=?");
		sql.append(" where no=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setString(1, board.getTitle());
			st.setString(2, board.getContent());
			st.setString(3, board.getWriter());
			st.setInt(4, board.getNo());

			if (st.executeUpdate() > 0) {
				result = true;
			} else {
				result = false;
			}

			System.out.println(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 게시물 삭제 서비스
	 * @param no
	 * @return
	 */
	public boolean deleteBoard(int no) {
		boolean result = false;

		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_board where no=?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			st.setInt(1, no);

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

	public int selectNo() {
		int no = 0;

		String sql = "select seq_t_board_no.nextval from dual";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql);) {

			ResultSet rs = st.executeQuery();
			rs.next();
			// no = rs.getInt(1);

			return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return no;
	}

	/**
	 * 첨부파일 등록 서비스
	 */

	public boolean insertFile(BoardFileVO fileVO) {
		boolean result = false;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_board_file (no, board_no, file_ori_name, file_save_name, file_size) ");
		sql.append(" values (seq_t_board_file_no.nextval, ?, ?, ?, ?)");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {
			int loc = 1;
			st.setInt(loc++, fileVO.getBoardNo());
			st.setString(loc++, fileVO.getFileOriName());
			st.setString(loc++, fileVO.getFileSaveName());
			st.setInt(loc++, fileVO.getFileSize());

			if (st.executeUpdate() > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<BoardFileVO> selectFileByNo(int no){
		List<BoardFileVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append(" from t_board_file ");
		sql.append(" where board_no=?");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());
		){
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				
				BoardFileVO file = new BoardFileVO();
				
				file.setNo(rs.getInt("no"));
				file.setFileOriName(rs.getString("file_ori_name"));
				file.setFileSaveName(rs.getString("file_save_name"));
				file.setFileSize(rs.getInt("file_size"));
				
				System.out.println(file);
				
				list.add(file);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public BoardFileVO selectFile(int no){
		BoardFileVO result = new BoardFileVO();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append(" from t_board_file ");
		sql.append(" where no=?");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());
			){
			
			st.setInt(1, no);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				
				result.setNo(rs.getInt("no"));
				result.setFileOriName(rs.getString("file_ori_name"));
				result.setFileSaveName(rs.getString("file_save_name"));
				result.setFileSize(rs.getInt("file_size"));
				
				System.out.println(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 해당 게시물의 첨부파일 삭제 서비스
	 */
	public boolean deleteFile(int boardNo){
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete t_board_file ");
		sql.append(" where board_no=?");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());
		){
			st.setInt(1, boardNo);
			
			if(st.executeUpdate()>0){
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/*public File download(int no){
		File file = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("");
		
		BoardFileVO fileVO = selectFile(no);
		String path = "";
		String ori_name = fileVO.getFileOriName();
		String save_name = fileVO.getFileSaveName();
		
		
		try (
				
		) {
			
			file = new File(path, save_name);
			FileInputStream fis = new FileInputStream(file);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;
	}*/
}
