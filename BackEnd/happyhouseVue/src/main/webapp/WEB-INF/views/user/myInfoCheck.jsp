<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>


<script type="text/javascript">
	$(document).ready(function() {
		$("#infoChkBtn").click(function() {
			infoCheckUser();
		});
		$("#homeBtn").click(function() {
			location.href = "${root}/";
		});
	});

	function infoCheckUser() {
		if (!$("#infoChkuserpw").val()) {
			alert("비밀번호 입력!!!");
			return;
		} else {
			$("#infoform").attr("action", "${root}/user/myInfoCheck").submit();
		}
	}
</script>

<div class="container text-center mt-3">
	<div class="col-lg-8 mx-auto">
		<h2>회원 수정</h2>
		<form id="infoform" class="text-left mb-3" method="post" action="">
			<div class="form-group">
				<label for="userid">아이디</label> <input type="text"
					class="form-control" id="userid" name="userid" placeholder=""
					disabled value="${userInfo.userid}"> <input type="hidden"
					class="form-control" id="infoChkuserid" name="infoChkuserid"
					value="${userInfo.userid}">
			</div>
			<div class="form-group">
				<label for="userpwd">비밀번호</label> <input type="password"
					class="form-control" id="infoChkuserpw" name="infoChkuserpw" >
			</div>

			<c:if test="${empty updateMsg}">
				<div class="form-group">
					<label>내 정보 조회를 위해 비밀번호를 입력 해주세요.</label>
				</div>
			</c:if>
			<div class="text-danger mb-2">${infoMsg}</div>
			<div class="form-group text-center">
				<button type="button" id="infoChkBtn" class="btn btn-outline-primary">내 정보 조회</button>
				<button type="button" id="homeBtn" class="btn btn-outline-primary">홈으로</button>
			</div>
		</form>
	</div>
</div>
</div>
</body>
</html>