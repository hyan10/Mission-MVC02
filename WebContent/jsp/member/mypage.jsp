<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<script>

	function onAction(type) {
		switch (type) {
		case 'u':
		//	location.href = "updateForm.do" //?id=${param.id}"
			break;
		case 'l':
			location.href = "<%=request.getContextPath()%>";
			break;
		}
	}
</script>
</head>
<body>
	<!-- html5 -->
	<header>
		<!-- xml, include, forward는 /가 Mission-Web/ 까지를 의미 -->
		<jsp:include page="/jsp/include/topMenu.jsp" />
	</header>

	<section>
		<div align="center">
			<br />
			<hr width="100%" />
			<h2>마이페이지</h2>
			<hr width="100%" />
			<br />

			<table width="100%" border="1">

				<tr>
					<th width="25%">id</th>
					<td>${member.id}</td>
				</tr>

				<tr>
					<th>이름</th>
					<td><c:out value="${member.name}" /></td>
					<%-- <td> <%= title %> </td> --%>
				</tr>

				<tr>
					<th>메일주소</th>
					<td>
						<c:if test="${not empty member.email_id }">
							${member.email_id}@${member.email_domain}
						</c:if>
					</td>
				</tr>

				<tr>
					<th>주소</th>
					<td><c:out value="${member.basic_addr} ${member.detail_addr }" /></td>
				</tr>
				
				<tr>
					<th>전화번호</th>
					<td>
						<c:if test="${not empty member.tel1}">
							${member.tel1}-${member.tel2}-${member.tel3}
						</c:if>
					</td>
				</tr>

				<tr>
					<th>등록일</th>
					<td>${member.reg_date}</td>
				</tr>

			</table>

			<br />
			<br /> <input type="button" value="수정" onclick="onAction('u')" />&nbsp;&nbsp;
			<input type="button" value="홈으로" onclick="onAction('l')" />
		</div>
	</section>

	<footer>
		<!-- action include 써도 된다.  -->
		<%@ include file="/jsp/include/bottom.jsp"%>
	</footer>
</body>
</html>