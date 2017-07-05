<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${result}">
		<script>
			alert("회원가입이 완료되었습니다.");
			location.href="<%=request.getContextPath()%>";
		</script>
	</c:when>
	<c:otherwise>
		<script>alert('회원가입 실패');</script>
	</c:otherwise>
</c:choose>
