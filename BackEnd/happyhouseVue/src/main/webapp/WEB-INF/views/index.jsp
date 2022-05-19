<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Framework Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/apt.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<header id="index_header" class="jumbotron text-center mb-1">
			<div class="carousel slide" id="carousel-545281">
					<ol class="carousel-indicators">
						<li data-slide-to="0" data-target="#carousel-545281"
							class="active"></li>
						<li data-slide-to="1" data-target="#carousel-545281"></li>
						<li data-slide-to="2" data-target="#carousel-545281"></li>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100" alt="Carousel Bootstrap First"
								src="img/home1.png">
							<div class="carousel-caption">
								<h4>Happy House</h4>
								<p>행복한 우리집</p>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" alt="Carousel Bootstrap Second"
								src="img/home2.png">
							<div class="carousel-caption">
								<h4>Happy House</h4>
								<p>행복한 우리집</p>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" alt="Carousel Bootstrap Third"
								src="img/home3.jpg">
							<div class="carousel-caption">
								<h4>Happy House</h4>
								<p>행복한 우리집</p>
							</div>
						</div>
					</div>
					<a class="carousel-control-prev" href="#carousel-545281"
						data-slide="prev"><span class="carousel-control-prev-icon"></span>
						<span class="sr-only">Previous</span></a> <a
						class="carousel-control-next" href="#carousel-545281"
						data-slide="next"><span class="carousel-control-next-icon"></span>
						<span class="sr-only">Next</span></a>
				</div>
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/">홈으로</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> 동네 정보 </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">APT 매매</a> <a
							class="dropdown-item" href="#">APT 전월세</a> <a
							class="dropdown-item" href="#">주택 매매</a> <a class="dropdown-item"
							href="#">주택 전월세</a> <a class="dropdown-item" href="#">상권 정보</a> <a
							class="dropdown-item" href="#">환경 정보</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#">Notice</a></li>
				<li class="nav-item"><a class="nav-link" href="#">News</a></li>
				
				<c:if test="${empty userInfo}">
					<li class="nav-item"><a class="nav-link" href="/user/regist">회원	가입</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/idFind">아이디 찾기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/pwFind">비밀번호 찾기</a></li>
				</c:if>
				<c:if test="${!empty userInfo}">
				<!-- /guestbook/list?pg=1&key=&word= -->
					<li class="nav-item"><a class="nav-link" href="/guestbook/list?pg=1&key=&word=">게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="/plus/smartSearch">스마트검색</a></li>							
					<li class="nav-item"><a class="nav-link" href="/user/myInfoCheck">내 정보
							조회</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/updateCheck">내 정보 수정</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/delete">회원
							탈퇴</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a>
					</li>
				</c:if>
				<c:if test="${!empty userInfo}">
					<li class="nav-item"><a class="nav-link" href="#"><strong>${userInfo.username}</strong>
							(${userInfo.userid})님 안녕하세요.</a></li>
				</c:if>
			</ul>
		</nav>

		<c:if test="${!empty userInfo}">
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<div class="form-group form-inline justify-content-center">
						<label class="mr-2" for="sido">시도 : </label> <select
							class="form-control" id="sido">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="gugun">구군 : </label> <select
							class="form-control" id="gugun">
							<option value="0">선택</option>
						</select> <label class="mr-2 ml-3" for="dong">읍면동 : </label> <select
							class="form-control" id="dong">
							<option value="0">선택</option>
						</select>
						<!-- <button type="button" id="aptSearchBtn">검색</button> -->
						
					<input type="text" placeholder="아파트명 입력" id="aptName" name="aptName" class="form-control" style="margin: 5px">
					<input type="button" id="aptNameBtn" class="form-control" value="검색" onclick="searchByName()">
					
					</div>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
					<div id="map" style="width: 100%; height: 500px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3d36a2f0f865319b995b5114bf19d849&libraries=services"></script>
					<script type="text/javascript" src="js/map.js"></script>
					<script type="text/javascript" src="js/list.js"></script>
					</div>
				</div>
			</section>
		</c:if>
		<main id="main"> <!-- ======= About Section ======= -->
   <section id="about" class="about section-bg">
      <div class="container" data-aos="fade-up">

         <div class="section-title">
			<br><br><br>
            <h3>오늘의 주요기사</h3>
            <p>주요기사를 요약해서 전해드립니다.</p>
         </div>

         <div class="row">
            <div
               class="col-lg-6 pt-4 pt-lg-0 content d-flex flex-column justify-content-center"
               data-aos="fade-up" data-aos-delay="100">
               <h3>주택 관련 기사</h3>
               <ul>
                  <li><i class="bx bx-store-alt"></i>
                     <div>
                        <h5>
                           <b>정부 산불 이재민에 임시조립주택 제공</b>
                        </h5>
                        <p>정부는 산불 이재민이 임시 거주할 조립주택의 수요를 파악해 260동을 지원하기로 했다.</p>
                     </div></li>
                  <li><i class="bx bx-store-alt"></i>
                     <div>
                        <h5>
                           <b>LH, 영광·영암에 공공임대주택 420가구 공급</b>
                        </h5>
                        <p>한국토지주택공사 광주전남지역본부는 3일 올해 상반기 전남 영광군과 영암군에 총 420가구 공공임대주택을
                           공급한다고 밝혔다.</p>
                     </div></li>

                  <li><i class="bx bx-store-alt"></i>
                     <div>
                        <h5>
                           <b>대선 이후, 주택가격 하락 희망정부 탄생 기대? </b>
                        </h5>
                        <p>이재명 윤석열 주택건설 250~311만 채 건설이 실현되면 일인가구 포함 약 350만 명~622만명의
                           주거난이 5년 내에 해결된다.</p>
                     </div></li>
               </ul>
            </div>

            <div
               class="col-lg-6 pt-4 pt-lg-0 content d-flex flex-column justify-content-center"
               data-aos="fade-up" data-aos-delay="100">
               <h3>오늘의 기사</h3>
               <ul>
                  <li><i class="bx bx-images"></i>
                     <div>
                        <h5>
                           <b>‘페이커’ “광동전, 연승 신기록 의식 않겠다”</b>
                        </h5>
                        <p>“실감이 나지 않지만, 좋은 기록을 세워 기쁘다”고 소감을 밝혔다. 그는 시즌 초만 해도 이같은 연승
                           행진을 예상하지 못했다면서 “기세를 타고, 여러모로 운이 좋아 좋은 기록을 세울 수 있었던 것 같다”</p>
                     </div></li>
                  <li><i class="bx bx-images"></i>
                     <div>
                        <h5>
                           <b>러, 수도 키이우 외곽 공세 강화 진입로 '격전'</b>
                        </h5>
                        <p>러시아군이 우크라이나의 수도 키이우(키예프)에 공세를 강화했다. 10일(현지시간) AFP·AP 통신,
                           CNN 방송 등에 따르면 키이우로 이어지는 주요 고속도로마다 러시아군과 우크라이나군의 격전이 이어지고 있다.</p>
                     </div></li>
                  <li><i class="bx bx-images"></i>
                     <div>
                        <h5>
                           <b>[속보]“우크라·러시아 휴전 합의 결렬”</b>
                        </h5>
                        <p>우크라이나와 러시아 간 개전 이후 처음으로 고위급 회담이 열렸지만 휴전 합의에 이르지는 못했다.</p>
                     </div></li>
               </ul>
            </div>
         </div>

      </div>
   </section>

		
	</div>
</body>
</html>
