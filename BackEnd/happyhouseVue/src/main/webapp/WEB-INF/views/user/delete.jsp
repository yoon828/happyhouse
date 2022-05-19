<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>


<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			deleteUser();
		});
		$("#homeBtn").click(function() {
			location.href = "${root}/";
		});
	});

	function deleteUser() {
		if (!$("#deluserpw").val()) {
			alert("비밀번호 입력!!!");
			return;
		} else {
			$("#deleteform").attr("action", "${root}/user/delete").submit();
		}
	}
</script>

<div class="container text-center mt-3">
	<div class="col-lg-8 mx-auto">
		<h2>회원 탈퇴</h2>
		<form id="deleteform" class="text-left mb-3" method="post" action="">
			<div class="form-group">
				<label for="userid">아이디</label> <input type="text"
					class="form-control" id="userid" name="userid" placeholder=""
					disabled value="${userInfo.userid}"> <input type="hidden"
					class="form-control" id="deluserid" name="deluserid" placeholder=""
					value="${userInfo.userid}">
			</div>
			<div class="form-group">
				<label for="userpwd">비밀번호</label> <input type="password"
					class="form-control" id="deluserpw" name="deluserpw" placeholder="">
			</div>

			<c:if test="${empty deleteMsg}">
				<div class="form-group">
					<label>회원 탈퇴를 위해 비밀번호를 입력 해주세요.</label>
				</div>
			</c:if>
			<div class="text-danger mb-2">${deleteMsg}</div>
			<div class="form-group text-center">
				<button type="button" id="deleteBtn" class="btn btn-outline-primary">회원탈퇴</button>
				<button type="button" id="homeBtn" class="btn btn-outline-primary">홈으로</button>
			</div>
		</form>
	</div>
</div>
</div>
</body>
</html>
