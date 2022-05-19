let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){					
					$.get(root + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#dong", function() {
					$.get(root + "/map/apt"
							,{dong: $("#dong").val()}
							,function(data, status){
								$("tbody").empty();
								$.each(data, function(index, vo) {
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${vo.aptCode}</td>
										<td>${vo.aptName}</td>
										<td>${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
										<td>${vo.buildYear}</td>
										<td>${vo.recentPrice}</td>
									`;
									$("tbody").append(str);
								});
								displayMarkers(data);
							}
							, "json"
					);
				});

				
				function searchByName() {
					$.get(root+"/map/aptName"
							,{aptName:$("#aptName").val()}
							,function(data, status){
								$("#tbody").empty();
								$.each(data, function(index, vo) {
									let str = `
										<tr class="${colorArr[index%3]}">
										<td>${vo.aptCode}</td>
										<td>${vo.aptName}</td>
										<td>${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}</td>
										<td>${vo.buildYear}</td>
										<td>${vo.recentPrice}</td>
									`;
									$("tbody").append(str);
								});
								displayMarkers(data);
							}

							, "json"
					); //get
				} //click
				
