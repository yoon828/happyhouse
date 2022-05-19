<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
        	var isId = false;
        	// 아이디 중복검사
        	$("#userid").keyup(function () {
        		var ckid = $("#userid").val();
        		if(ckid.length < 6 || ckid.length > 16) {
        			$("#idresult").text("아이디는 6자이상 16자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
        			isId = false;
        		} else {
	                $.ajax({
	                	url: '${root}/user/idcheck',
	                	data: {'ckid': ckid},
	                  	type: 'GET',
	                  	dataType: 'json',
	                  	success: function (response) {
	                  		console.log(response);
	                    	var cnt = response.idcount;
	                    	if(cnt == 0) {
	                    		$("#idresult").text(ckid + "는 사용가능합니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
	                    		isId = true;
	                    	} else {
	                    		$("#idresult").text(ckid + "는 사용할 수 없습니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
	                    		isId = false;
	                    	}
	                  	}, 
	                  	error: function(request, status, error) {
	                  		console.log("status : " + request.status + "\tmsg : " + error);
	                  	}
					});
        		}
			}); 
        	
        	// 회원가입
            $("#registerBtn").click(function () {
                if (!$("#username").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!isId) {
                    alert("아이디 확인!!!");
                    return;
                } else if (!$("#userpwd").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if ($("#userpwd").val() != $("#pwdcheck").val()) {
                    alert("비밀번호 확인!!!");
                    return;
                } else {
                	$("#useraddress").val($("#emailid").val() + "@" + $("#emaildomain").val());
                    $("#memberform").attr("action", "${root}/user/regist").submit();
                }
            });

         
        });
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2>회원가입</h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
            <input type="hidden" id="email" name="email">
                <div class="form-group">
                    <label for="username">이름</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="이름입력...">
                </div>
                <div class="form-group">
                    <label for="userid">아이디</label>
                    <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디입력...">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="userpwd">비밀번호</label>
                    <input type="password" class="form-control" id="userpwd" name="userpw" placeholder="비밀번호입력...">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">비밀번호재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="비밀번호재입력...">
                </div>
                <div class="form-group">
                    <label for="emailid">이메일</label><br>
                    <div id="email" class="custom-control-inline" name ="email">
                        <input type="text" class="form-control" id="emailid" name="emailid" placeholder="이메일아이디입력..."
                            size="25"> @
                        <input type="text" class="form-control" id="emaildomain" name="emaildomain" placeholder= "이메일 도메인 입력.."
                            size="25">
                        <input type="hidden" class="form-control" id="useraddress" name="useraddress" 
                        size="25">
                    </div>
                </div>
                <div class="form-group">
                    <label for="usernumber">전화번호</label>
                    <input type="text" class="form-control" id="usernumber" name="usernumber" placeholder="전화번호입력...">
                </div>
               
                <div class="form-group text-center">
                    <button type="button" id="registerBtn" class="btn btn-outline-primary">회원가입</button>
                    <button type="reset" class="btn btn-outline-danger">초기화</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
