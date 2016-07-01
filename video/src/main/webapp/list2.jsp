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

function getSecond(sSecondId){
	$.ajax({
		url:"videoServerlet?actionName=describeVodInfo&classId="+sSecondId,
		type:'get',
		dataType:'json',
		success:function(data){
			if(data.code==0){
				var htmlStr="";
				data.fileSet.forEach(function(e){
					var filename= encodeURIComponent(e.fileName);
					var description = encodeURIComponent(e.description);
					htmlStr+= '<li>'
							 +'<a href="/video/detail.jsp?fileId='+e.fileId +'&fileName='+filename+'&description='+description+'" class="pic"><img alt="" src="'+e.imageUrl+'" width="100%"/></a>'
							 +'<a href="/video/detail.jsp?fileId='+e.fileId +'&fileName='+filename+'&description='+description+'" class="name">'+e.fileName+'</a>'
							 +'</li>';
				})
				$("#videolist").html(htmlStr);
				
			}
		},
		error:function(){}
	});
}


$(function(){
	getSecond(typeId);
})
</script>
