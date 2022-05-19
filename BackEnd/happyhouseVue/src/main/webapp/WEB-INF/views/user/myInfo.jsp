<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
    $(document).ready(function() {
		$("#homeBtn").click(function() {
			location.href = "${root}/";
		});
	});
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2>내 정보</h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
            <input type="hidden" id="email" name="email">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="showusername" name="showusername" disabled value="${infoUser.username}">
                    <input type="hidden" class="form-control" id="username" name="username" value="${infoUser.username}">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="showuserid" name="showuserid"  disabled value="${infoUser.userid}">
                    <input type="hidden" class="form-control" id="userid" name="userid" value="${infoUser.userid}">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline">
                        <input type="text" class="form-control" id="emailid" name="emailid"  disabled
                        value="${infoUser.useraddress}"
                            size="25"> 
                         </div>
                </div>
                <div class="form-group">
                    <label for="usernumber">전화번호</label>
                    <input type="text" class="form-control" id="usernumber" name="usernumber" disabled value="${infoUser.usernumber}">
                </div>
               
                <div class="form-group text-center">
                    <button type="button" id="homeBtn" class="btn btn-outline-primary">홈으로</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>