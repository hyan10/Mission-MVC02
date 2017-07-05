package kr.co.bit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.vo.MemberVO;
import kr.co.bit.framework.ModelAndView;
import kr.co.bit.framework.annotation.Controller;
import kr.co.bit.framework.annotation.RequestMapping;
import kr.co.bit.member.service.MemberService;

@Controller
public class MemberController {

	@RequestMapping("/member/mypage.do")
	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView result = new ModelAndView();
		String url = "/jsp/member/mypage.jsp";
		
		HttpSession session = request.getSession();
		MemberVO member = getService(request).getMember(((MemberVO)session.getAttribute("member")).getId());
		
		result.setView(url);
		result.addAttribute("member", member);

		return result;
	}
	
	public ModelAndView join(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView();
		
		return result;
	}
	
	private MemberService getService(HttpServletRequest request) {
		ServletContext sc = request.getServletContext();
		MemberService memberService = (MemberService)sc.getAttribute("memberService");
		return memberService;
	}
}
