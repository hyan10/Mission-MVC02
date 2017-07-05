<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
</head>
<body>

	<header>
		<!-- xml, include, forward는 /가 Mission-Web/ 까지를 의미 -->
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>
	<section align="center">
		<br />
		<hr width="100%" />
		<h2>회원 목록</h2>
		<hr width="100%" />
		<br />

		<table border="1" width="100%" class="list">

			<tr>
				<th>id</th>
				<th width="16%">이름</th>
				<th width="30%">메일</th>
				<th width="30%">주소</th>
				<th width="30%">전화번호</th>
				<th width="20%">등록일</th>
			</tr>

			<c:forEach var="member" items="${members}" varStatus="loop">
				<tr <c:if test="${loop.index%2 == 1}">class="even"</c:if>>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td>
						<c:if test="${ not empty member.email_id }">
							${member.email_id}@${member.email_domain}
						</c:if>
					</td>
					<td>${member.basic_addr} ${member.detail_addr}</td>
					<td>
						<c:if test="${not empty member.tel1}">
							${member.tel1}-${member.tel2}-${member.tel3}
						</c:if>
					</td>
					<td>${member.reg_date}</td>
				</tr>
			</c:forEach>

		</table>
		<br />
	</section>

	<footer>
		<!-- action include 써도 된다.  -->
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>
</body>
</html>