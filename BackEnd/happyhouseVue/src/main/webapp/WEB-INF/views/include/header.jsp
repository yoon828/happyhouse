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
								src="../img/home1.png">
							<div class="carousel-caption">
								<h4>Happy House</h4>
								<p>행복한 우리집</p>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" alt="Carousel Bootstrap Second"
								src="../img/home2.png">
							<div class="carousel-caption">
								<h4>Happy House</h4>
								<p>행복한 우리집</p>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" alt="Carousel Bootstrap Third"
								src="../img/home3.jpg">
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
					<li class="nav-item"><a class="nav-link" href="/user/regist">회원
							가입</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/user/idFind">아이디 찾기</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/pwFind">비밀번호 찾기</a></li>

				</c:if>
				<c:if test="${!empty userInfo}">
					<li class="nav-item"><a class="nav-link" href="/guestbook/list?pg=1&key=&word=">게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="/plus/smartSearch">스마트검색</a></li>		
					<li class="nav-item"><a class="nav-link" href="/user/myInfoCheck">내 정보
							조회</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/updateCheck">내
							정보 수정</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/delete">회원
							탈퇴</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/logout">로그아웃</a>
					</li>
				</c:if>
				<c:if test="${!empty userInfo}">
					<li class="nav-item"><a class="nav-link" href="#"><strong>${userInfo.username}</strong>
							(${userInfo.userid})님 안녕하세요.</a></li>
				</c:if>
			</ul>
		</nav>
