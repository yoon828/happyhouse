<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
        	// 회원수정
            $("#updateBtn").click(function () {
                if (!$("#userpw").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if(!$("#emailid").val()) {
                	alert("이메일 입력!!!!");
                	return;
                } else {
                	$("#useraddress").val($("#emailid").val() + "@" + $("#emaildomain").val());
                    $("#memberform").attr("action", "${root}/user/update").submit();
                }
            });
			
         
        });
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2>회원 수정</h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
            <input type="hidden" id="email" name="email">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="showusername" name="showusername" disabled value="${updateUserInfo.username}">
                    <input type="hidden" class="form-control" id="username" name="username" value="${updateUserInfo.username}">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="showuserid" name="showuserid"  disabled value="${updateUserInfo.userid}">
                    <input type="hidden" class="form-control" id="userid" name="userid" value="${updateUserInfo.userid}">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpw" name="userpw">
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline">
                        <input type="text" class="form-control" id="emailid" name="emailid" 
                            size="25"> @
                        <input type="text" class="form-control" id="emaildomain" name="emaildomain" 
                            size="25">
                        <input type="hidden" class="form-control" id="useraddress" name="useraddress" 
                            size="25">
                    </div>
                </div>
                <div class="form-group">
                    <label for="usernumber">전화번호</label>
                    <input type="text" class="form-control" id="usernumber" name="usernumber"  value="${updateUserInfo.usernumber}">
                </div>
               
                <div class="form-group text-center">
                    <button type="button" id="updateBtn" class="btn btn-outline-primary">회원 정보 수정</button>
                    <button type="reset" class="btn btn-outline-primary">초기화</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>