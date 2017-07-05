package kr.co.bit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.service.BoardService;
import kr.co.bit.login.service.LoginService;
import kr.co.bit.member.service.MemberService;

@WebListener
public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("리스너 종료");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("리스너 초기화");
		ServletContext sc = event.getServletContext();
		
		BoardDAO dao = new BoardDAO();
		BoardService service = new BoardService(dao);
		sc.setAttribute("boardService", service);
		sc.setAttribute("boardDAO", dao);
		
		MemberDAO memberDAO = new MemberDAO();
		LoginService loginService = new LoginService(memberDAO);
		sc.setAttribute("loginService", loginService);
		
		MemberService memberService = new MemberService(memberDAO);
		sc.setAttribute("memberService", memberService);
	}

}
