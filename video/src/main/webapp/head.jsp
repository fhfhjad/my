<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="logo_search">
	<a href="/video" class="logo"><img alt="" src="<%=request.getContextPath() %>/images/logo.png" width="100%"/></a>
	<div class="search">
		<form class="formId" action="/searchlist.jsp">
			<input type="text" class="txtinput" name="txtinput" placeholder="请输入文字"/>
			<a href="javascript:void(0)" class="btnsubmit"><img alt="" src="<%=request.getContextPath() %>/images/submit.png"/></a>
		</form>
	</div>
</div>
<div class="nav" id="navUl">
	
</div>
<script>
	$(function(){
		
		//搜索
		$(".btnsubmit").click(function(){
			if($(".txtinput").val()=="" || $(".txtinput").val()==null){
				return;
			}else{
				$(".formId").submit();
			}
					
		});
		/*  $.ajax({
			url:'searchlist.jsp',
			type:'post',
			data:$(".txtinput").val(),
			async: false,
			success:function(data){
				$(".btnsubmit").click(function(){
					$(".formId").submit();
				});
			},
			error:function(){
				
			}
		});  */
		
		//导航栏数据绑定
		$.ajax({
			url:"videoServerlet?actionName=describeAllClass",
			type:'get',
			dataType:'json',
			success:function(data){
				if(data.code==0){
					var htmlStr='<ul>'
						+'<li class="active"><a title="index" href="index.jsp">首页</a></li>';
					data.data.forEach(function(e){  
						if(e.info.id != 0){
							htmlStr+='<li><a title="'+e.info.id+'" href="list.jsp?id='+e.info.id+'">'+e.info.name+'</a></li>';
						}
						
					})  
					$("#navUl").html(htmlStr+'<button class="navmore">'
							+'	<span>...</span>'
							+'</button>'
							+'</ul>');
					checknav();
					inpage();
				}
			},
			error:function(){
				
			}
			
		});
		
		//导航栏收放效果
		function checknav(){
			var m = 1;
			$(".navmore span").click(function(){
				
				if(m==1){
					$(this).parents("ul").css("height",100);
					m++;
				}else{
					$(this).parents("ul").css("height",50);
					m=1;
				}
			});
		}
		//获取当前页面高亮
		function inpage(){
			$(".nav ul li a").each(function(){
				var id =$(this).attr("title");
				var pageid = $.getUrlParam('id');
				if(id == pageid){
					$(this).parent().addClass("active")
					$(this).parent().siblings().removeClass("active");
					document.title = $(this).html()+'—腾空视频';
				}
			});
		}
	})
</script>
<script>
(function($){
	$.getUrlParam = function(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	}
})(jQuery);
</script>