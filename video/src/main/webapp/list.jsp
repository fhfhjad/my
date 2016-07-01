<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0">
<title></title>
<link href="css/mb.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
</head>
<body>
	<header>
		<%@ include file="/head.jsp" %>
	</header>
	<section style="padding-top:15px;" >
		<ul class="videolist" id="videolist">
			
		</ul>
	</section>
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>
</body>
</html>
<script>
var typeId = $.getUrlParam('id');
var imgUrl="";

function getFirst(){
	$.ajax({
		url:"videoServerlet?actionName=describeAllClass",
		type:'get',
		dataType:'json',
		success:function(data){
			if(data.code==0){
				data.data.forEach(function(e){
					var _id = e.info.id;
					if(_id == typeId){
						var sSecond = e.subclass;
						if(sSecond.length != 0){
							var htmlStr="";
							for(var m in sSecond){
								var _sSecondId = sSecond[m].info.id;
								
								var image=null;
								$.ajax({
									url:"videoServerlet?actionName=describeVodInfo&classId="+_sSecondId,
									type:'get',
									dataType:'json',
									async: false,
									success:function(data2){
										if(data2.code==0){
											image = data2.fileSet[0].imageUrl;
										}
									},
									error:function(){}
								});
								htmlStr+= '<li><a href="list2.jsp?id='+_sSecondId+'" class="pic"><img alt="" src="'+image+'" width="100%"/></a>'
										 +'<a href="list2.jsp?id='+_sSecondId+'" class="name">'+sSecond[m].info.name+'</a></li>';
								
							}
							$("#videolist").html(htmlStr);
						}
					}
				});
			}
		},
		error:function(){}
	});
}
$(function(){
	getFirst();
})
</script>
