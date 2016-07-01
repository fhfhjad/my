<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0">
<title>关于我们</title>
<link href="css/mb.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<style>
.header { height:50px; text-align:center; position:relative; background-color:#505150; }
.bg-write { padding:20px; background-color:#fff; line-height:1.8em }
.mb { margin-bottom:30px; }
.logo-container { padding:20px 0; text-align:center; }
.logo-container img { width:40%; }
h2 { line-height:50px; color:#fff; }
.jiantou { width: 40px; height: 40px; position: absolute; top: 50%; left:3%; }
.jiantou {
	-webkit-transform: translateY(-50%);
	-moz-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	-o-transform: translateY(-50%);
	transform: translateY(-50%);
}
.jiantou::before,
.jiantou::after {
	position: absolute;
	left: 20%;
	width: 2px;
	height: 50%;
	outline: 1px solid transparent;
	background: #fff;
	content: '';
	-webkit-transition: -webkit-transform 0.3s;
	-moz-transition: transform 0.3s;
	-ms-transition: transform 0.3s;
	-o-transition: transform 0.3s;
	transition: transform 0.3s;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	-o-backface-visibility: hidden;
	backface-visibility: hidden;
}
.jiantou::before {
	top: 50%;
	-webkit-transform: translateX(-50%) rotate(-135deg);
	-moz-transform: translateX(-50%) rotate(-135deg);
	-ms-transform: translateX(-50%) rotate(-135deg);
	-o-transform: translateX(-50%) rotate(-135deg);
	transform: translateX(-50%) rotate(-135deg);
	-webkit-transform-origin: 50% 0%;
	-moz-transform-origin: 50% 0%;
	-ms-transform-origin: 50% 0%;
	-o-transform-origin: 50% 0%;
	transform-origin: 50% 0%;
}
.jiantou::after {
	top: 50%;
	-webkit-transform: translateX(-50%) rotate(-45deg);
	-moz-transform: translateX(-50%) rotate(-45deg);
	-ms-transform: translateX(-50%) rotate(-45deg);
	-o-transform: translateX(-50%) rotate(-45deg);
	transform: translateX(-50%) rotate(-45deg);
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	-o-transform-origin: 0 0;
	transform-origin: 0 0;
}
</style>
</head>
<body>
	<header class="header">
		<h2>关于我们</h2>
        <a href="javascript:history.go(-1);" class="jiantou"></a>
	</header>
	<div class="bd">
    	<div class="logo-container"><img src="images/logo.png"></div>
		<div class="bg-write">
            <p class="mb">腾空视频是专门为手机用户量身打造的在线免费视屏客户端。我们拥有同类软件中领先的播放速度，丰富了正版影视内容，稳定的播放平台，为用户提供丰富多彩的视频资源。</p>
            <p>版本：</p>
            <p>官网：www.tengkong.com</p>
            <p class="mb">商务合作：wangshiyu@tengkong.com</p>
            <p>腾空网版权所有</p>
        </div>
	</div>
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>

</body>
</html>