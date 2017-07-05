<%@page import="kr.co.bit.board.dao.MemberDAO"%>
<%@page import="kr.co.bit.board.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDAO dao = new MemberDAO();
	MemberVO member = dao.getMember(request.getParameter("id"));
	
	boolean result = false;
	String msg = "이미 존재하는 아이디입니다.";
	if(member==null){
		result = true;
		msg = "사용 가능합니다.";
	}
%>
{"result":"<%=result%>","msg":"<%=msg%>"}