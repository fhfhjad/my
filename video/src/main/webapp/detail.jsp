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
<script src="http://qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js" charset="utf-8"></script>
</head>
<body>
	<header>
		<%@ include file="/head.jsp" %>
	</header>
	<section>
		<div id="videodetail">
			<h2 class="detail_name"></h2>
			<div class="video" id="element_id"></div>
			<div class="videodetail">
				<span class="title">剧情介绍</span>
				<div class="htmleditor">
					<p></p>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>
</body>
</html>
<script>
	$(function(){
		var arry = window.location.href;
		var arr = arry.split("&");
		var relarr = arr[1].split("=");
		$(".detail_name").html(decodeURIComponent(relarr[1]));
		var description = arr[2].split("=")
		$(".htmleditor").html(decodeURIComponent(description[1]));
		
		/**获取视频地址**/
		var file_id  = $.getUrlParam('fileId');
		var option;
		$.ajax({
			url:'videoServerlet?actionName=describeVodPlayUrls&fileId='+file_id,
			type:'get',
			dataType:'json',
			success:function(data){
				option = {'width':data.playSet[1].vwidth,'height':data.playSet[1].vheight,'url':data.playSet[1].url}
			},
			error:function(){
				
			}
			
		});
		
		//视频参数
		 new qcVideo.Player(
              /*代码中的id_video_container将会作为播放器放置的容器使用,可自行替换*/
              "element_id",
              {
            	  'app_id':'1251768111',
            	  'file_id':file_id,
            	  //'stretch_patch':
              },
              option
          );
	})
	
</script>