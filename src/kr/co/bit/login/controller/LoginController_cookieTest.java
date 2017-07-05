package kr.co.bit.login.controller;

import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.vo.MemberVO;
import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;
import kr.co.bit.login.service.LoginService;

@Controller
public class LoginController_cookieTest {

	/**
	 * 로그인 등록폼 서비스
	 */
	@RequestMapping("/member/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/jsp/member/loginForm.jsp");
		
		/*Cookie[] cookies = request.getCookies();

		String value="";
		if(cookies != null){
			for(Cookie c : cookies){			
				String name = URLDecoder.decode(c.getName(),"utf-8");
				if(name.equals("saveId")){
					value = URLDecoder.decode(c.getValue(),"utf-8");
				}
			}
		}else {
			// 쿠키 없음
		}

		request.setAttribute("saveId", value);*/
		
		return mav;
	}

	/**
	 * 로그인 수행 서비스
	 */
	@RequestMapping("/member/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	/*
	 * Filter에서 처리
		request.setCharacterEncoding("UTF-8");
	*/
		ModelAndView mav = new ModelAndView();
		
		ServletContext sc = request.getServletContext();
		LoginService loginService = (LoginService)sc.getAttribute("loginService");
		
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("pw"));
		member = loginService.login(member);
		

		if(member == null){
			mav.setView("redirect:"+request.getContextPath()+"/member/loginForm.do");
		}else {
			mav.setView(request.getContextPath()); //"/index.jsp");
			/*String detail_url = request.getParameter("url");
			String no = request.getParameter("no");
			mav.setView("redirect:"+request.getContextPath()+detail_url!=""?("/"+detail_url+".do?no="+no):"");*/
			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			// 아이디 저장 체크
			String saveId = request.getParameter("saveId");
			System.out.println(saveId);
			
			Cookie cookie = new Cookie("saveId",request.getParameter("id"));
			if(saveId!=null) { // saveId.equals("save")){
//				Cookie cookie = new Cookie("saveId",request.getParameter("id"));
				response.addCookie(cookie);
			}else {
				
				cookie.setMaxAge(0);
				
				/*Cookie[] cookies = request.getCookies();
				if(cookies != null){
					for(Cookie c : cookies){			
						String name = URLDecoder.decode(c.getName(),"utf-8");
						if(name.equals("saveId")){
							c.setMaxAge(0);
							response.addCookie(c);
							System.out.println("쿠키지움");
						}
					}
				}*/
			}
	
			response.addCookie(cookie);
		}

		return mav;
	}

	/**
	 * 로그아웃 서비스
	 */
	@RequestMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		// session.removeAttribute("member");
		session.invalidate();
		
		// 로그아웃 후 메인페이지로 이동 (sendRedirect)
//		mav.setView("redirect:/Mission-MVC02/board/list.do");
		mav.setView("redirect:"+request.getContextPath());

		return mav;
	}
}
