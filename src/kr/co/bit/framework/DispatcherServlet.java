package kr.co.bit.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DispatherServlet
 */
// @WebServlet(urlPatterns={"*.do"})
@WebServlet(urlPatterns={"*.do"},
			initParams={
					@WebInitParam(name="controllers",
								value="kr.co.bit.board.controller.BoardController|kr.co.bit.login.controller.LoginController")
					})
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping mappings = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		String ctrlNames = config.getInitParameter("controllers");
		System.out.println(ctrlNames);
		
		try {
			mappings = new HandlerMapping(ctrlNames);			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서비스호출");
		
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length());
		System.out.println(uri);
		
		CtrlAndMethod cam = mappings.getCtrlAndMethod(uri);
		
		/*
		 * http://localhost:8000/Mission-MVC02/board/list.do 입력 시
		 * uri: /board/list.do
		 * cam: target ==> kr.co.bit.board.controller.BoardController 객체
		 * 		method ==> select()
		 */
		
		String view = "";
		
		try {
			
			if(cam == null){
				throw new Exception("요청하신 URL은 존재하지 않습니다.");
			}
			
			Object target = cam.getTarget();
			Method method = cam.getMethod();
			
			ModelAndView mav = (ModelAndView) method.invoke(target, request, response);
			
			view = mav.getView();
			
			// request 공유 영역에 객체 등록
			Map<String, Object> map = mav.getModel();
			
		/*	Set<Entry<String,Object>> set = map.entrySet();			
			for(Entry<String,Object> entry : set){
				request.setAttribute(entry.getKey(), entry.getValue());
				System.out.println(entry.getKey()+": "+ entry.getValue());
			}*/
			
			Set<String> keys = map.keySet();
			for(String key : keys){
				request.setAttribute(key, map.get(key));
				System.out.println(key+": "+ map.get(key));
			}
			
			
		} catch (Exception e) {
			request.setAttribute("exception", e);
			view = "/ErrorServlet";
		}
		
		

		// 해당 callPage로 응답 (forward, sendRedirect)
		/* 에러페이지도 forward 시키기 위해 catch 밑에 써준다. */
		if(view.startsWith("redirect:")){
//			response.sendRedirect(view.substring("redirect:".length()).trim());
			response.sendRedirect(view.replace("redirect:", "").trim());
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

}
