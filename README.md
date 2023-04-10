# Happy House :house:
주택 실거래 내역 정보를 제공하는 HappyHouse 입니다.

:trophy:광주 5반 우수 프로젝트 선정:trophy:

 <br/><br/>
## :blush:개요
- SAFFY 7기 1학기 최종 관통 프로젝트
- 주택 거래 정보 검색 및 주택 관련 정보 제공서비스
<img width="60%" src="https://user-images.githubusercontent.com/64458685/170626666-cc6447b7-fc1f-498c-af57-8af98bb65d33.png"/>

### 서비스 소개
- `주택 거래가`를 조회 할 수 있는 웹 사이트입니다.
- 지역 및 아파트 검색을 통해서 `아파트 매매내역`을 조회 할 수 있습니다.
- `주택 관련 뉴스`와 `주변 음식점 정보`를 제공합니다.

### 주요 특징
- 지역/아파트명 검색으로 `주택 매매 거래 내역` 조회 기능
- 아파트 목록에 대한 평균 거래 금액/건축 년도 `필터링` 기능
- 카카오 `지도 API`를 사용하여 주택의 위치를 시각적으로 표현
- 지도에 표시되는 지역의 `주변 시설` 4종(은행, 편의점, 병원, 지하철역) 을 시각적으로 표현
- 마커 또는 리스트 클릭 시 해당 아파트와 관련된 거래 내역과 평균 거래 금액, 평점 조회 기능
- 로그인 시 `평점` 등록 및 `관심 지역` 등록 기능
- 네이버 API를 활용한 특정 지역에 대한 `주변 음식점 정보`와 `주택 관련 뉴스` 제공
- 회원을 관리할 수 있는 `관리자 페이지` 제공

 <br/><br/>
## :+1:TEAM
- 김윤민(팀장), 정지원(팀원) <br/>

<img width="60%" src="https://user-images.githubusercontent.com/64458685/170626847-e274f17f-b81b-40b0-bcd1-e6a3d79887f8.png"/>
 <br/><br/>



## :calendar:프로젝트 일정
전체 기간 : 2022.0519 ~ 2022.05.26 <br>
![image](https://user-images.githubusercontent.com/64458685/170627276-347e107c-1d20-43ff-92d0-7709fe7bc480.png)
 <br/><br/>
## :wrench:개발 환경

### FrontEnd

|환경|버전|
|:---:|:---:|
|VSCode|1.67.2|
|Vue|2.6.10|
|BootStrap|4.6.1|
|bootstrap-vue|2.22.0|

### BackEnd

|환경|버전|
|:---:|:---:|
|JAVA(opnejdk)|1.8.0_192|
|STS|3.9.14.RELEASE|
|Mysql|8.0.28|
|mybatis-springboot|2.2.2|
|Springboot|2.6.7|

 <br/><br/>
## :star:개발 결과
### 메인페이지
![Untitled](https://user-images.githubusercontent.com/64458685/230898073-db28d49c-52c9-43b4-ba82-4310dd55d50e.png)

### 아파트 거래 조회 페이지
![거래조회](https://user-images.githubusercontent.com/64458685/230898100-2bb07cf4-2b9c-4d82-a51f-6584bb3d3df3.png)

### 아파트 상세 모달창
![상세모달](https://user-images.githubusercontent.com/64458685/230898109-d594c946-949e-4a68-a66b-ca0979071312.png)

### 주변 맛집 검색 페이지
![맛집검색](https://user-images.githubusercontent.com/64458685/230898104-7bb4ac87-bd37-4b31-b9ba-93dd811d2999.png)

### 주요 기능
>  <strong>회원관리</strong>
>   * 회원가입
>   * 로그인
>   * 아이디 찾기
>   * 비밀번호 찾기 -> 임시비밀번호 발급
>   * 마이페이지
>   
> <strong> 공지사항 & QA 게시판</strong>
>   * 글 목록
>   * 글 상세 정보 
>   * 등록 & 수정 & 삭제 (공지사항은 관리자만 작성 가능)
>   * 댓글 작성
>   * 게시판 검색 기능
>   
>   <strong>아파트 검색</strong>
>   * 검색화면 (지역과 아파트명으로 검색) 
>   * 관심지역 설정
>   * 아파트 조회 필터링 기능
>   * 아파트 상세 정보
>   * 아파트 평점 
>   * 아파트 주변 시설 검색 (은행, 편의점, 병원, 지하철역)
>   * 해당 지역의 맛집 검색
>
>   <strong>주택 관련 뉴스 제공</strong>
>   * 주택과 관련된 뉴스를 매번 다르게 제공함
>
>   <strong>지역 맛집 검색 기능</strong>
>   * 지역과 키워드를 입력하면 관련 음식점 목록 제공
>
>  <strong>관리자 페이지</strong>
>   * 회원 목록 조회
>   * 회원 정보 수정, 탈퇴
>   * 회원 권한 변경


 <br/><br/>
### :raised_hands:요구사항 목록
[:point_right:요구사항 목록(Notion):point_left:](https://road-desk-e0c.notion.site/9319dcda34d349efa2a62d604f7ceeda?v=78581bd5ac974ce3b002550d212d5bc4)
 <br/><br/>
### :computer:화면 설계서
[:point_right:화면설계서 보러가기:point_left:](./docs/screen)
 <br/><br/>
### :camera:시연영상
[:point_right:시연영상 보러가기:point_left:](https://youtu.be/Z8zZywKfSx4)
