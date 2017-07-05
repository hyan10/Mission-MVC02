package kr.co.bit.board.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.service.BoardService;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;

@Controller
public class BoardController {
	
	private BoardDAO dao;
	private BoardService service;
	
	private void test(){
		
	}
	
	@RequestMapping("/board/insert.do")
	public void insert(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		System.out.println("게시글 등록서비스");
		
		getService(request);
	}


	@RequestMapping("/board/list.do")
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("게시글 조회서비스");
		
		ServletContext sc = request.getServletContext();
/*
		BoardService boardService = (BoardService)sc.getAttribute("boardService");
		List<BoardVO> list = boardService.selectAllBoard();
*/
/* test (have no service)		
		BoardDAO dao = (BoardDAO)sc.getAttribute("boardDAO");
		List<BoardVO> list = dao.selectAllBoard();
*/
		List<BoardVO> list = getService(request).selectAllBoard();
		
		ModelAndView mav = new ModelAndView();
		mav.setView("/jsp/board/list.jsp");
		mav.addAttribute("list",list);
		
		return mav;
	}

	private BoardService getService(HttpServletRequest request) {
		ServletContext sc = request.getServletContext();
		BoardService boardService = (BoardService)sc.getAttribute("boardService");
		return boardService;
	}
}
