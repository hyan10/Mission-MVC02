<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	HashMap<Integer, String> map = new HashMap<>();
	map.put(1,"a");
	out.println(map.get(1));
	map.put(1,"b");
	out.println(map.get(1));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="/Lecture-Web/jquery/js/jquery-3.2.1.min.js"></script>
<script>
	 function goWriteForm(){
		location.href = "writeForm.do";  <%=request.getContextPath()%>/board/
	}
	
	function onAction(boardNo){
		<c:choose>
			<c:when test="${not empty member}">
				location.href = "detail.do?type=list&no="+boardNo;
			</c:when>
			<c:otherwise>
				if(confirm('로그인이 필요합니다. 로그인하시겠습니까?')){
					location.href = "<%=request.getContextPath()%>/member/loginForm.do?url=board/detail&no="+boardNo;
				}
			</c:otherwise>
		</c:choose>
	}
	
	function next(nextNo){
		location.href = "list.do?nextNo="+nextNo;  /board/
	}
	
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css"/>
</head>
<body>
<%-- 
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"/>
	</header>
--%>	
	<section>
		<div align="center">
		<br/>
		<hr width="100%"/>
			<h2 >게시판 목록</h2>
		<hr width="100%"/>
		<br/>

		<table border="1" width="100%" class="list">
		
			<tr>
				<th width="7%">번호</th>
				<th>제목</th>
				<th width="16%">작성자</th>
				<th width="20%">등록일</th>
			</tr>

		<c:forEach var="board"  items="${list}" varStatus="loop">
			<tr <c:if test="${loop.index%2 == 1}">class="even"</c:if>>
				<td>${board.no}</td>
				<td>
					<a href="javascript:onAction('${board.no}')"><c:out value="${board.title}"/></a>
				</td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
			</tr>
		</c:forEach>
		</table>
		<br/>
		<!-- begin="0" end="5" -->
		<%-- <c:forEach var="i" items="${listAll}" step="${size}" varStatus="loop">
			<fmt:parseNumber var="page" value="${(loop.index/size)+1}" integerOnly="true"/>
			<a href="javascript:next(${page})">${page}</a>&nbsp;
		</c:forEach> --%>
		
		<br/><br/>
		
		<c:if test="${not empty member}">
			<input type="button" value="새글 등록" onclick="goWriteForm()"/>
		</c:if>
	</div>
	</section>
<%--
	<footer>
	<!-- action include 써도 된다.  -->
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer> --%>
</body>
</html>