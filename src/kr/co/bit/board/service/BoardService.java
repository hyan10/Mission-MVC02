package kr.co.bit.board.service;

import java.util.List;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class BoardService {
	
	private BoardDAO dao;
	
	public BoardService(BoardDAO dao){
		this.dao = dao;
	}
	
	public List<BoardVO> selectAllBoard(){
		List<BoardVO> list = dao.selectAllBoard();
		return list;
	}
}
