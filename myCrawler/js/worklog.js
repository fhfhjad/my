/* 编辑今日计划  flag 表示是否全刷新 flag=true 是页面全刷新  可能是来自日计划列表页面  flag=false 是根据其他日志等进行刷新 这个是日志列表中的新建*/
function editPendingMatter(pendingId, startTime, logTime,workLogId,from) {
	var paramData = {
		id:pendingId,
		startTime:startTime,
		workLogTime: logTime,
		workLogId: workLogId
	};
	var id="newOrEditPendingMatter"+(pendingId <=0 ?"":pendingId);
	$.post(globalCp +'/worklog/dayplan/editDayPlanPre.do', paramData, function(html){
		if (html.indexOf("#mgtModalWarn") > 0) {
			$(document.body).append(html);
			$("#mgtModalWarn").modal({backdrop:"static"}); return;
		}
		$(".popover").remove();
		var target = $("#"+id); 
		target.popover({content:html,noTitle:true, placement:(pendingId <=0 ?"right":"left"),trigger:"manual",
			hasClose:false,html:true}).popover('show');
		var pover = $("#"+id+" + .popover").css("max-width","500px");
		if (pendingId > 0) {
			pover.css("left",target.offset().left - pover.outerWidth(true));
		}
		pover.find(".mgtPopClose_dayPlan").eq(0).click(function(){
			destroyDayPlan(pover);
		});
		pover.find("#newFrom").val(from+"");
		$(window).scroll(function(){
			$('#modal_datepicker').datetimepicker('place');
		});
		pover.find('#modal_datepicker').datetimepicker()
			.on('changeDate', function(ev){
			$("#startTime").val(ev.date.valueOf());
			if (ev.selectType == "day") {
				$(".datetimepicker").hide();
			}
		});
		pover.find("#btn_worklog_delete_dayPlan").confirmDialog(
			delDayPlan(pover)
		);
		pover.find("#btn_worklog_ok_dayPlan").click(function(){
			modalOKDayPlan(pover,true);
		});
		pover.find("#dayPlanContent").focus(function () { //获取焦点触发
			countWords(this, 140);
	        $(this).keyup(function () { // 键盘按下事件
	        	countWords(this, 140);
	        });
	    });
		pover.find("#dayPlanContent").validate({
			expression: "return pub_v.empty('dayPlanContent',140); ",
			mode:2,live:false});
		
		pover.find("#dayPlanContent").focus();
		setPointerPos(pover.find("#dayPlanContent"));
	});
};

/* 新建,修改   工作记录,工作小结 */
function editSegment(logId, segId, type, workLogTime){
	var param = {
		workLogId: logId,
		segmentId: segId,
		type: type,
		workLogTime: workLogTime
	};
	$.post(globalCp +'/worklog/logSegment/editSegmentPre.do', param, function(data){
		$(document.body).append(data);
		if ($("#mgtModalWarn").length > 0) {
			$("#mgtModalWarn").modal({backdrop:"static"}); return;
		}
		$("#myModal").modal({backdrop:"static"});
		setPointerPos($("#modalContent"));
	});
}

/*设置今日计划状态(完成，未完成)*/
function updatePendingMatterStatus(pendingId,workLogTime){
	var chbx = $('#pend_'+pendingId +"> a").find("i");
	var status = chbx.hasClass("mpic-ok") ? 0 : 1;
	var url = globalCp+'/worklog/updatePendingMatterStatus.do';
	$.post(url,{pendingId:pendingId, status:status},function(data){
		if (data.ok) {
			if(workLogTime && workLogTime>0){
				partialRefresh(0,'dayplan',workLogTime);
				if (data.noWorkLog) {
					$("#commentinfoarea").addClass("hide");
				} else {
					$("#commentinfoarea").removeClass("hide");
					$("#commentLogId").val(data.workLogId);
				}
			}else{
				window.location.href = getUrl()+"t="+new Date().getTime();
			}
		} else {
			showModalWarn(data.errorMsg);
		}
	});
}

/* 添加日志评论   动态加载 评论部分  局部刷新 */
function commentSubmit(logId){
	$("#logCommentForm").find(".count").eq(0).html("");
	if(!$("#logCommentForm").validated(false))
		return ;
	
	var commentContent = Editor.getValue("logComment");		
	var atts = $("#logCommentAttach").mgtfileupload({result:true});
	$.postJson(globalCp + '/worklog/saveLogcomment.do', {'workLogId':logId, 'content':commentContent, 'attachList':atts}, function(data){
		if (data.ok) {
			mgtNotification( {message:"<i class='mpic-notify-success'></i>提交成功",type: 'success',position:"top-center",closable:false});
			$.post(globalCp + '/worklog/loadcomment.do',{workLogId:logId},function(html){
				$("#commentinfoarea").html(html);
			});
		}else{
			if (data.error) {
 				showModalWarn(data.message); return;
 			}
 			$("#logCommentForm").find(".count").html("<EM style='color:#F41A57'>"+data.message+"</EM>");
 		}
	});
};
/**
 * 弹出导出窗口
 */
function showExportDialog(){
	$.post(globalCp +'/worklog/popup/exportDialog.do', function(data){
		$(document.body).append(data);
		$(".modal").modal({backdrop:"static"});
	    $('#exportModal').on('hidden', function () {
	    	$("#closeBtn").click();
	     });
	});
};

/**
 * 计算输入的字数
 * @param str
 * @returns
 */
function getCode(str){
	var length = 0;
	for (var i = 0; i < str.length; i++) {
		var iCode = str.charCodeAt(i);
		if ((iCode >= 0 && iCode <= 255)
				|| (iCode >= 0xff61 && iCode <= 0xff9f)) {
			length += 1;
		} else {
			length += 2;
		}

	}
	return Math.ceil(length/2);
}
function getCodeSubStr(strValue,length,padLast){
	var index = 0 ;
	var len = 0 ;
	if(length>=getCode(strValue)){
		return strValue ;
	}else{
		for (; index < strValue.length; index++) {
			var iCode = strValue.charCodeAt(index);
			if ((iCode >= 0 && iCode <= 255)
					|| (iCode >= 0xff61 && iCode <= 0xff9f)) {
				len += 1;
			} else {
				len += 2;
			}
			if(length<Math.round(len/2)){
				break;
			}
		}
		return strValue.substring(0, index)+padLast;
	}
}
function countWords(countObj, maxNum){
	countObj = $(countObj);
	var bool;
	var num = $.trim(countObj.val()).length;
    var showObj = countObj.parent().find(".count");
    var _num = maxNum - num;
	if (_num < 0) {
    	showObj.html('<EM>超出' + (-_num) + i18n.cell +'字</EM>');
    	showObj.find("em").css("color", "#F41A57");
        bool = false;
    } else {
    	showObj.html('');
    	showObj.find("em").removeAttr("style");
        bool = true;
    }
	return bool;
}


/* 删除评阅信息 */
function deleteComment(commentId,workLogId,isArea){
	if($.isNumeric(commentId)){
		$.post(globalCp+"/worklog/deleteComment.do?commentId="+commentId,function(result){
			if(result.ok){
				if(isArea){
					if(result.tip){//局部刷新这里需要变更一下 当日志存在的时候进行局部刷新 否则提示信息
						$.post(globalCp + '/worklog/loadcomment.do',{workLogId:workLogId},function(html){
							$("#commentinfoarea").html(html);
						});
					}else{
						showModalWarn(result.message);
					}
				}else{
					window.location.href = getUrl()+"t="+new Date().getTime()+"#commentArea";
				}
			}else{
				showModalWarn(result.message);
			}
		});
	}
}
/**显示编辑评论的modal 主要用来编辑评论信息
 * commentId 评论的id
 * */
function editComment(commentId){
	var p={commentId:commentId};
	var url=globalCp+"/worklog/popup/editComment.do";
	$.post(url,p,function(html){
		$(document.body).append(html);
		$(".modal").modal({backdrop:"static"});
		setPointerPos($("#commentText"));
	});
}

// 补充日志提示信息确认
function userProtertySet(tipDom,type){
	$.post(globalCp+"/worklog/userProtertySetter.do",{property:type}, function(data){
		if (data.ok) {
			$(tipDom).parents(".popover").remove();
		}
	});
}
function commitEditComment(commentId,workLogId){
	$("#editCommentForm").find(".count").eq(0).html("");
	if(!$("#editCommentForm").validated(false))
		return ;
	var commentText = $.trim(Editor.getValue("commentText"));
	var length = commentText.length;
	if (length > 20000) {
		$("#forEditComment").find(".count").html("<EM style='color:#F41A57'>超出"+(length-20000)+"个字</EM>");
		return;
	}
	var url=globalCp+"/worklog/editCommentSave.do";
	var contentText = Editor.getValue("commentText");
	var attac = $("#editCommentAttach").mgtfileupload({result:true});
	var p={id:commentId,content:contentText,attachList:attac};
	$.postJson(url,p,function(data){
 		if(data.ok){
	 		$("#forEditComment").modal("hide").remove();
 			if(getUrl().indexOf("mycomment.do")>0){
 				window.location.href = getUrl().substring(0,getUrl().indexOf("mycomment.do")+13)+"curr=1&type=sended";
 			}else{
				$.post(globalCp + '/worklog/loadcomment.do',{workLogId:workLogId},function(html){
					$("#commentinfoarea").html(html);
				});
 			}
 		}else{
 			if (data.error) {
 				showModalWarn(data.message); return;
 			}
 			$("#forEditComment").find(".count").html("<EM style='color:#F41A57'>"+data.message+"</EM>");
 		}
 	});
}

function getUrl(){
	var url = window.location.href;
	var regex = /([&|?]{1}t=){1}/gi;
	if (regex.test(url)) {
		if (url.indexOf('?t=') > 0) {
			url = url.substring(0,url.indexOf('?t=')+1);
		} else if (url.indexOf('&t=') > 0) {
			url = url.substring(0,url.indexOf('&t=')+1);
		}
	} else if (url.indexOf('?') > 0){
		url = url + "&";
	} else {
		url = url + "?";
	}
	return url;
}
/** 局部刷新 日志主体部分数据的方法  这里的 from 是指从哪个请求过来的  worklogtime是用来标记显示那个日期的日志的*/
function partialRefresh(paramId,from,workLogTime){
	var param = {
		from: from,
		workLogTime: workLogTime
	};
	if(workLogTime > 0){
		$.post(globalCp+"/worklog/partialRefresh.do",param,function(html){
			$("#mylogelement").html("").html(html);
		});
	}
}
