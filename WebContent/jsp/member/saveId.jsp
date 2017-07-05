<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//쿠키 생성
	Cookie cookie = new Cookie("id",request.getParameter("id"));
	response.addCookie(cookie);
	
	
%>