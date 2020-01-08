var tipTimeout = null;
function showPopover(target, msg, type) {

	clearTimeout(tipTimeout);

	target.attr("data-original-title", msg);
	$('[data-toggle="tooltip"]').tooltip();
	var tp = target.tooltip('show');

	$($(document.body).find(".tooltip")[0]).removeClass("success").removeClass(
			"error");
	if (typeof (type) != "undefined")
		$($(document.body).find(".tooltip")[0]).addClass(type);

	$($(document.body).find(".tooltip")[0]).css("z-index", 1100);

	target.focus();

	// 2秒后消失提示框
	tipTimeout = setTimeout(function() {
		 target.attr("data-original-title", "");
		 target.tooltip('hide');
	}, 2000);
};


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


function showRefreshTip(){
	$(".refreshBtn").popover({
			placement:'auto left',
				content: "点击这里重新加载菜单！",
				
		});
	$(".refreshBtn").popover('show');
	
	setTimeout(function() {
		//$(".refreshBtn").popover('hide');
		 
	}, 4000);
}


function refreshMenu(){
var url = getRPath()+"/privilege/permission/refreshMenu";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			window.location.reload();
		}
	});
	
	
}

/**
 * 获取当前项目名称
 */
function getRPath() {
    return getRootPath();//.ctx;
}
/**
 * 获取当前项目名称
 */
function getRootPath() {
    //获取路径
    var pathName = window.document.location.pathname;
    //截取，得到项目名称
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return projectName
}


function cconfirm(msg, donecallback, cancelcallback) {
	swal({
		title : '确定执行操作吗？',
		text : msg,// '你将无法恢复它！',
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : '确定',
		cancelButtonText : '取消',
		confirmButtonClass : 'btn btn-success',
		cancelButtonClass : 'btn btn-danger',
		buttonsStyling : true,
		allowOutsideClick : false,// 如果设置为false，用户无法通过点击弹窗外部关闭弹窗。
		allowEscapeKey : false, // 如果设置为false，用户无法通过按下Escape键关闭弹窗。
		allowEnterKey : false,
	}).then(function(rst) {

		if (rst.value) {
			if (typeof (donecallback) == "function")
				donecallback();
		}

		if (rst.dismiss) {
			if (typeof (cancelcallback) == "function")
				cancelcallback();
		}

		/*
		 * swal( '已删除！', '你的文件已经被删除。'+rst.dismiss, 'success' );
		 */
	})
};



function success(msg) {
	/*
	 * swal({ title: "操作成功", text: msg, timer: 500, type:"success",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	// swal("干得漂亮！", "你点击了按钮！","success")
	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}

	showPopover($(target), msg, "success");
};

function msg(msg) {
	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	success(msg);
};

function info(msg) {

	/*
	 * swal({ title : "提示信息", text : msg, // timer: 1000, type : "info",
	 * showConfirmButton : true });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg);
};

function error(msg) {
	/*
	 * swal({ title: "操作异常", text: msg, timer: 1000, type:"error",
	 * showConfirmButton: false });
	 * 
	 * return;
	 */

	var target = $('<div class="row col-xs-12 toptooltip ">'
			+ '<span class="col-sm-5 col-xs-4"></span>'
			+ '<span id="message_new"  class="col-sm-1 col-xs-2" style="margin-left: 20px;"></span>'
			+ '</div> ');
	var size = $("body").find(".toptooltip").length;
	if (size != 0)
		target = $("#message_new");
	else {
		$("body").prepend(target);
		target = $($(target).find("#message_new")[0]);
	}
	;

	showPopover($(target), msg, "error");
};


