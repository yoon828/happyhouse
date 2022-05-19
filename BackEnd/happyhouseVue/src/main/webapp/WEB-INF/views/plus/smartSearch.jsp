<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../plugin/jquery-ui/jquery-ui.css">
<link rel="stylesheet" href="../plugin/jqgrid/css/ui.jqgrid.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script src="../plugin/jqgrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../plugin/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link rel="shortcut icon" href="http://designm.ag/favicon.ico">
  <link rel="icon" href="http://designm.ag/favicon.ico">
  <link rel="stylesheet" type="text/css" media="all" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css">
  <link rel="stylesheet" type="text/css" media="all" href="http://fonts.googleapis.com/css?family=Acme">
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<style>
div.ui-slider-range.ui-widget-header {
    background: #cfdac8;
}
.white {
	color: white;
}
.container{
	margin : 10px;
}
.setting{
	background-color: #f8ecc9;
	color: black;
}
#dongContainer{
	margin-bottom: 10px;
}
</style>
<script>
$(function(){
	  $('#rangeslider').slider({
	    range: true,
	    min: 5400,
	    max: 840000,
	    values: [ 9000, 90000 ],
	    slide: function( event, ui ) {
		      $('#rangeval').html(ui.values[0]+" - "+ui.values[1]);
		      $.get(root + "/plus/price"
					,{start: ui.values[0], end: ui.values[1], dong: $("#dong").val()}
					,function(data, status){
					$("#searchResult").empty();
					$.each(data, function(index, vo) {
						let str = "<tr>"
						+ "<td>" + vo.aptCode + "</td>"
						+ "<td>" + vo.sidoName + " " + vo.gugunName + " " + vo.dongName+ " " + vo.jibun + "</td>"
						+ "<td>" + vo.aptName + "</td>"
						+ "<td>" + vo.buildYear + "</td>"
						+ "<td>" + vo.recentPrice + "</td>"
						$("#searchResult").append(str); 
					});//each
					displayMarkers(data);
				}//function
				, "json"
			);//get
	    }
	  });
	});
</script>
<title>가격대 별 매물 조회</title>
</head>
<body>
	<section class="container">
		<div class="list">
		<br/><h3>■ 스마트 조회</h3>
				<span class = "setting">▶ 원하시는 법정동을 설정해주세요</span>
				<div id = "dongContainer">
				법정동: <input type="text" name="dong" id = "dong" /> <br/>
				</div>
				<br />
				<span class = "setting">▶ 원하시는 가격대를 설정해주세요</span>
				<div id="rangedval">
				가격대: <span id="rangeval">9000 - 10000</span>(단위 : 천만원)
  				</div>
  				<div id="rangeslider"></div>
				
		</div>
		<div class="jumbotron bg-white text-center">
         <table id="resultTable" class="table table-bordered table-hover text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>법정동</th>
						<th>아파트이름</th>
						<th>건축년도</th>
						<th>실거래 가격</th>
					</tr>
				</thead>
				<tbody id="searchResult"> </tbody>
				</table>
					<div id="map" style="width: 100%; height: 500px;"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3d36a2f0f865319b995b5114bf19d849&libraries=services"></script>
					<script type="text/javascript" src="js/map.js"></script>
      </div>

	</section>
</body>
</html>