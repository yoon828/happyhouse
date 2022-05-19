<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<script type="text/javascript">
	$(document).ready(
			function() {
				// 아이디 찾기
				$("#idFindBtn").click(
						function() {
							if (!$("#username").val()) {
								alert("이름 입력!!!");
								return;
							} else if (!$("#emailid").val()) {
								alert("이메일 입력!!!");
								return;
							} else if (!$("#emaildomain").val()) {
								alert("이메일 입력!!!");
								return;
							} else if (!$("#usernumber").val()) {
								alert("전화번호 확인!!!");
								return;
							} else {
								$("#useraddress").val(
										$("#emailid").val() + "@"
												+ $("#emaildomain").val());
								$("#memberform").attr("action",
										"${root}/user/idFind").submit();
							}
						});
			});
</script>
<c:if test="${empty idFindMsg}">
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2>아이디 찾기</h2>
			<form id="memberform" class="text-left mb-3" method="post" action="">
				<input type="hidden" id="email" name="email">
				<div class="form-group">
					<label for="username">이름</label> <input type="text"
						class="form-control" id="username" name="username"
						placeholder="이름입력...">
				</div>
				<div class="form-group">
					<label for="emailid">이메일</label><br>
					<div id="email" class="custom-control-inline" name="email">
						<input type="text" class="form-control" id="emailid"
							name="emailid" placeholder="이메일아이디입력..." size="25"> @ <input
							type="text" class="form-control" id="emaildomain"
							name="emaildomain" placeholder="이메일 도메인 입력.." size="25">
						<input type="hidden" class="form-control" id="useraddress"
							name="useraddress" size="25">
					</div>
				</div>
				<div class="form-group">
					<label for="usernumber">전화번호</label> <input type="text"
						class="form-control" id="usernumber" name="usernumber"
						placeholder="전화번호입력...">
				</div>

				<div class="form-group text-center">
					<button type="button" id="idFindBtn"
						class="btn btn-outline-primary">아이디 찾기</button>
					<button type="reset" class="btn btn-outline-danger">초기화</button>
				</div>
			</form>
		</div>
	</div>
</c:if>
<c:if test="${!empty idFindMsg}">
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2>${idFindMsg}</h2>
		</div>
	</div>

</c:if>
</body>
</html>
