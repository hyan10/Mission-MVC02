<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/Lecture-Web/js/httpRequest.js"></script>
<script>
	function check() {
		var form = document.joinForm;

		if (form.id.value == "") {
			alert("아이디를 입력해주세요.");
			form.id.focus();
			return false;
		}

		if (form.name.value == "") {
			alert("이름을 입력해주세요.");
			form.id.focus();
			return false;
		}

		if (form.pw.value == "") {
			alert("비밀번호를 입력해주세요.");
			form.pw.focus();
			return false;
		}

		if ((form.email_id != "") && (form.email_domain.selectedIndex == 0)) {
			alert("도메인을 선택해주세요.");
			form.email_domain.focus();
			return false;
		}

		if (form.tel1.value != "" || form.tel2.value != ""
				|| form.tel3.value != "") {
			if (form.tel1.value == "" || form.tel2.value == ""
					|| form.tel3.value == "") {
				alert("전화번호를 모두 입력해주세요.");
				form.tel1.focus();
				return false;
			}
		}

		return true;
	}

	function back() {
		location = "<%=request.getContextPath()%>/board/list.do";
	}
	
	function checkId(){
		$.ajax({
			url: "checkId.jsp",
			type: "post",
			data: {"id":$('#id').val()},
			success: callbackId
		});
	}
	
	function checkPw(){
		$.ajax({
			url: "checkPw.jsp",
			type: "post",
			data: {"pw1":$("#pw1").val()},
			success: callbackPw
		})
	}
	
	function checkPws(){
		$.ajax({
			url: "checkPw.jsp",
			type: "post",
			data: {"pw1":$('#pw1').val(), "pw2":$('#pw2').val()},
			success: callbackPw
		});
	}
	
	function callbackId(data){
		var json = JSON.parse(data);
		console.log(json.msg);
		$('#check_id').text(json.msg);
		
		if(json.result=="true"){
			$('#check_id').css("color","blue");
		}else{
			$('#check_id').css("color","red");
		}
	}
	
	function callbackPw(data){
		var json = JSON.parse(data);
		console.log(json.msg);
		$('#check_pw').text(json.msg);
		
		if(json.result=="true"){
			$('#check_pw').css("color","blue");
		}else{
			$('#check_pw').css("color","red");
		}
	}
	
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
</head>
<body>
	<!-- html5 -->
	<header> <!-- xml, include, forward는 /가 Mission-Web/ 까지를 의미 -->
	<jsp:include page="/jsp/include/topMenu.jsp" /> </header>

	<section>
	<div align="center">
		<br />
		<hr width="80%" />
		<h2>회원가입</h2>
		<hr width="80%" />
		<br />

		<form action="join.do" method="post" name="joinForm"
			onsubmit="return check()">

			아이디: <input id="id" type="text" name="id" />
			<input type="button" onclick="checkId()" value="중복 체크"/><br/>
			<span id="check_id"></span>
			<br/>
			<br /> 이름: <input type="text" name="name" /><br />
			<br /> 비밀번호: <input id="pw1" type="password" name="pw" onkeyup="checkPw()"/><br />
			<br/> 비밀번호 확인: <input id="pw2" type="password" name="pw2" onkeyup="checkPws()" /><br/>
			<span id="check_pw"></span><br/>
			<br /> 이메일: <input type="text" name="email_id" /> @ <select
				name="email_domain">
				<option value="">도메인 선택</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="google.com">google.com</option>
			</select><br />
			<br /> 전화번호: <input type="text" name="tel1" size="3" />-<input
				type="text" name="tel2" size="4" />-<input type="text" name="tel3"
				size="3" /> <br />
			<br /> 우편번호: <input type="text" name="post" size="7" /><br />
			<br /> 주소: <input type="text" name="basic_addr" />&nbsp; <input
				type="text" name="detail_addr" /> <br />
			<br /> <input type="submit" value="회원가입" />&nbsp;&nbsp; <input
				type="button" value="취소" onclick="back()" />
		</form>
	</div>
	</section>

	<footer> <!-- action include 써도 된다.  --> <%@ include
		file="/jsp/include/bottom.jsp"%> </footer>
</head>
<body>
</html>