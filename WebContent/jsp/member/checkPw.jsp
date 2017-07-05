<%@page import="kr.co.bit.board.dao.MemberDAO"%>
<%@page import="kr.co.bit.board.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean result = false;
	String msg = "비밀번호가 일치하지 않습니다.";
	
	String pw1 = request.getParameter("pw1");
	String pw2 = request.getParameter("pw2");
	
	if(pw2==null){
		// 비밀번호 타입 확인
		if(pw1.length()<3){
			result = false;
			msg = "비밀번호는 3자리 이상이어야 합니다.";
		}else {
			result = true;
			msg = "사용 가능합니다.";
		}
	} else { // 두 비밀번호 일치 확인
		if(pw1.equals(pw2)){
			result = true;
			msg = "비밀번호가 일치합니다.";
		}
	}
%>
{"result":"<%=result%>","msg":"<%=msg%>"}