<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html ng-app>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta charset="UTF-8">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0">
  <title>腾空视频</title>
  <link href="css/mb.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <script src="js/jquery-1.10.2.js"></script>
 </head>
 <body>
	<header class="head">
		<%@ include file="/head.jsp" %>
	</header>
	<section class="typevideolist">
	
	</section>
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>
 </body>
</html>
<script>
	$(function(){
		$.ajax({
			url:'videoServerlet?actionName=describeClass',
			type:'get',
			dataType:'json',
			success:function(data){
				var htmlStr = '';
				if(data.code == 0){
					data.data.forEach(function(e){
						htmlStr += '<div class="typelist">'
							+'<div class="hd">'
							+'	<a class="title" href="#">'+e.name+'</a>'
							+'	<a class="more" href="#">更多</a>'
							+'</div>'
							+'<ul class="videolist">';
							var id= e.id;
							htmlStr+=ssss(id);
							htmlStr +='</ul></div>';
					});
					
					$(".typevideolist").html(htmlStr);
					
				}
			},
			error:function(data){
				
			}
		});
	})
	
	
	function ssss(id){
		var htmlStr="";
		$.ajax({
			url:'videoServerlet?actionName=describeVodInfo&classId='+id+'&pageNo=1&pageSize=4',
			type:'get',
			async:false,
			dataType:'json',
			success:function(result){
				if(result.code == 0&&result.fileSet!=undefined){
					var lth=null;
					if(result.fileSet.length>4){
						lth=4
					}else{
						lth=result.fileSet.length;
					}
					
					for(var i=0;i<lth;i++){
						var r=result.fileSet[i];
						var filename= encodeURIComponent(r.fileName);
						var description = encodeURIComponent(r.description);
						htmlStr += '<li>'
							 +'<a href="/video/detail.jsp?fileId='+r.fileId +'&fileName='+filename+'&description='+description+'" class="pic"><img alt="" src="'+r.imageUrl+'" width="100%"/></a>'
							 +'<a href="/video/detail.jsp?fileId='+r.fileId +'&fileName='+filename+'&description='+description+'" class="name">'+r.fileName+'</a>'
							 +'</li>';	
					}
					/*  result.fileSet.forEach(function(r){
						var filename= encodeURIComponent(r.fileName);
						var description = encodeURIComponent(r.description);
						htmlStr += '<li>'
							 +'<a href="/video/detail.jsp?fileId='+r.fileId +'&fileName='+filename+'&description='+description+'" class="pic"><img alt="" src="'+r.imageUrl+'" width="100%"/></a>'
							 +'<a href="/video/detail.jsp?fileId='+r.fileId +'&fileName='+filename+'&description='+description+'" class="name">'+r.fileName+'</a>'
							 +'</li>'; 
						
					}) */
				}
			},
			error:function(result){
				
			}
		});
		return htmlStr;
	}
</script>
