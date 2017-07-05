<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function addFavorite(){
		try{
			window.external.AddFavorite('http://localhost:8000${pageContext.request.contextPath}', '첫번째 웹');
		}catch (e) { // var형이므로 Exception 이런거 안써도됨
			alert('현재 브라우저에서는 사용할 수 없습니다.\n크롬에서는 ctrl+d를 사용해주세요.');
		}
	}
	
	function noMember(){
		if(confirm('로그인이 필요합니다. 로그인하시겠습니까?')){
			location.href="${pageContext.request.contextPath}/member/loginForm.do";
		}
	}
</script>
<table border="1" width="100%" style="min-width:700px">
	<tr>
		<td rowspan="2" style="width:200px; height:50px">
			<a href="${pageContext.request.contextPath}">
			<img src="${pageContext.request.contextPath}/images/logo.png" />
			</a>
		</td>
		<td  align="right">
			<a href="#" onclick="addFavorite()">즐겨찾기</a>
			<c:if test="${ not empty member }">
				[${sessionScope.member.id}님 로그인 중]
			</c:if>
		</td>
	</tr>
	
	<tr>
		<td>
		<nav>
		<c:if test="${sessionScope.member.type eq 'S' }">
			<a href="${pageContext.request.contextPath}/member/members.do">회원관리</a>
			||
		</c:if>
		<a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
		||
		<c:choose>
		<c:when test="${not empty sessionScope.member.id}">
			<a href="${pageContext.request.contextPath}/member/mypage.do">마이페이지</a>  <!-- ?id=${sessionScope.member.id} -->
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/member/joinForm.do">회원가입</a>
		</c:otherwise>
		</c:choose>
		||
		<c:choose>
		<c:when test="${empty sessionScope.member.id}">
			<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>			
		</c:otherwise>
		</c:choose>
		</nav>
		</td>
	</tr>
</table>
