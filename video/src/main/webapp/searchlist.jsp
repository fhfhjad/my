<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String fileName = (String)request.getParameter("txtinput");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0">
<title>搜索列表</title>
<link href="css/mb.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>

</head>
<body>
	<header>
		<%@ include file="/head.jsp" %>
	</header>
	<input type="hidden" id="fileName" value="<%= fileName %>">
	<section style="padding-top:15px;">
		<ul class="videolist">
			
		</ul>
	</section>
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>
<script type="text/javascript">
function selectByName(serchName){
	$.ajax({
	 	type: "post",
        dataType:'json', //接受数据格式 
        data:{ 
        	'fileName':serchName
		    },    
        url: "videoServerlet?actionName=describeVodPlayInfo",
        success: function(data){
			$(".videolist").empty();
			var _videolist = '';
        	if(data.code==0){
        		$.each(data.fileSet, function(index, item){
        			_videolist += '<li><a href="#" class="pic"><img alt="" src="'+item.image_url+'" width="100%"/></a><a href="#" class="name">'+item.fileName+'</a></li>';
				});
				$(".videolist").append(_videolist);
        	}
         },
        error: function(){
        //请求出错处理
            alert("Error!");
        }
	
	});
}

$(document).ready(function(){
	var searchName = $("#fileName").val();
	//根据筛选列表
	selectByName(searchName);
	
});

</script>
</body>
</html>