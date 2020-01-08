var action="new";
$(function() {
	
	
	loadMenuTree("");
	
	groupTable();

	initValidate();

	
	/*showPopover($("#message"),
			"xxxxx");*/
	
	
	
	$("#type").change(function(){
		
		changeParentList();
	});
	
	$("#q_pid").click(function(){
		showMenu();
	})
	

	$("#cleanselect").click(function(){
		var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.cancelSelectedNode();
	$("q_pid").val("");
	hideMenu();
	})
	
	
	
	
	
	$("#btnAdd").click(function() {
		
		action="new";
		
		$("#id").val("");
		$("#name").val("");
	
		$("#url").val("");
		$("#percode").val("");
		$("#parentid").val("");
		$("#sortstring").val("");
	
		

		$('#type').get(0).selectedIndex = 1;
//
//		$("#available").find("option[value='" + data.type + "']").attr(
//				"selected", true);

		$("#faction").html("新增");
		
		changeParentList(function(){
				
			$('#parentid').get(0).selectedIndex = 1;
		})
		
		

		$("#id").removeAttr("readonly")
		$("#myModal").modal();
	});

	
	$('#myModal').on('show.bs.modal', function(e) {
		//$('#mform input').val('');
		
		//action="new";

	/*	$("#type").find("option[value=2]").attr(
				"selected", true);
		$("#available").find("option[value=1]").attr(
				"selected", true);*/
		// $(".help-block").html('');
		//$("#mform").data('bootstrapValidator').resetForm(true);

	});
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal').on('hide.bs.modal', function(e) {

		$('#mform input').val('');
		// $(".help-block").html('');

		$("#mform").data('bootstrapValidator').resetForm(true);

	});

	$("#btnSubmit")
			.click(
					function() {

						// 触发全部验证
						$("#mform").data("bootstrapValidator").validate();

						// flag = true/false
						var flag = $("#mform").data("bootstrapValidator")
								.isValid();

						var url=getRPath()+"/privilege/permission/saveOrUpdate.action";
						if(action=="new")
							url=getRPath()+"/privilege/permission/save.action";
						else
							url=getRPath()+"/privilege/permission/update.action";
						
						if (flag) {
							var data = $("#mform").serialize();

							$
									.ajax({
										type : "post",
										url : url,
										data : data,
										async : false,
										dataType : "json",
										success : function(response) {
											//debugger;
											if (response.result) {
												//window.location.href = "/privilege/permission/index";
												$("#myModal").modal("hide");
												doquery();
											} else {
												showPopover($("#message"),
														response.message);
											}
										}
									});
						}
					});
});


/**
 * 行点击链接 显示父节点下的数据
 * @param id
 * @param name
 * @returns
 */
function showparent(id,name)
{
	//showMenu();
	var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	if(zTree!=null)
		{
	
	
	var nd=zTree.getNodesByParam("id", id, null);
	zTree.selectNode(nd[0]);
	
	$("#q_pid").val(name);
	setTimeout(function() {
		doquery();	
	}, 500);
	
		}
}

function showMenu() {
	var cityObj = $("#q_pid");
	var cityOffset = $("#q_pid").offset();
	//$("#menuContent").slideDown("fast");
	$("#menuContent").show();

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	
	var menuids="";
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	if(zTree!=null)
		{
	var nodes = zTree.getSelectedNodes();
	for ( var i = 0, l = nodes.length; i < l; i++) {		
		menuids+=nodes[i].name+"";

	}
		}
	
	$("#q_pid").val(menuids);
	
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}


/**
 * 根据权限菜单类型/获取父节点下拉框选项
 * @param successCallback
 * @returns
 */
function changeParentList(successCallback){
	var type=$("#type").val()-1;
	
	var data="type="+type+ "&pageSize=40";
	$.ajax({
		type : "post",
		url : getRPath()+"/privilege/permission/list",
		data : data,
		async : false,
		dataType : "json",
		success : function(data) {

			var lst= eval(data.rows);
			
			var html="";
			if(lst.length)
			{
			$.each(lst,function(index,item){
				if(index==0)
				html+=	' <option value="'+item.id+'"  selected="selected" >'+item.name+'</option> ';
				else
					html+=	' <option value="'+item.id+'"  >'+item.name+'</option> ';
				
			});
				}
			else
				{
				html+=	' <option value="0"  selected="selected" >根节点</option> ';
				}
			$("#parentid").html(html);
			
			
			if(typeof(successCallback)=='function')
				{
				successCallback();
				}
			
		}
	});
}

function initValidate() {
	$("#mform").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			id : {// 
				validators : {
					notEmpty : {// 非空
						message : 'ID不能为空'
					}
				}
			},
			name : {
				validators : {
					notEmpty : {
						message : '权限菜单名称不能为空'
					}
				}
			}

			,
			percode : {
				validators : {
					notEmpty : {// 非空
						message : '权限代码不能为空'
					}
				}
			}

			,
			parentid : {
				validators : {
					notEmpty : {// 非空
						message : '父菜单ID不能为空'
					}
				}
			}

			,
			sortstring : {
				validators : {
					notEmpty : {// 非空
						message : '排序号不能为空'
					},
					 regexp: {
		                 regexp: /^[+]?[1-9]\d*$/,
		                 message: '排序为大于0的数字'
		             }
				}
			},
				icon : {
                    validators : {
                        notEmpty : {// 非空
                            message : 'icon不能为空'
                        }
                    }
                }

		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}

function subtransfer() {
	var phone = $("#phone").val();
	$.ajax({
		type : "post",
		url : getRPath()+"/manager/user/permissionTransfer",
		data : {
			phone : phone
		},
		dataType : "json",
		success : function(data) {
			alert(data.message);
		}
	});
}

function groupTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : getRPath()+'/privilege/permission/list', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大

		// showColumns: true, //是否显示所有的列
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		// queryParamsType : "limit",
		queryParams : function queryParams(params) { // 设置查询参数
			
			//permission ids;
			var menuids="";
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if(zTree!=null)
				{
			var nodes = zTree.getSelectedNodes();
			for ( var i = 0, l = nodes.length; i < l; i++) {		
				menuids+=nodes[i].id+"";

			}
				}
			
			
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sort : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				name : $("#q_name").val(),
				//pid:$("q_pid").val(),
				pid:menuids,
				
				
				
			};
			return param;
		},
		columns : [ 
			{
			field : 'id',
			visible : true,
			title : '权限ID',
			align : 'center',
			valign : 'middle'
		},

		{
			field : 'name',
			title : '权限菜单名称',
			align : 'center',
			valign : 'middle'
		}

		, {
			field : 'type',
			title : '菜单类型',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

               if(value=="1")
            	   return "一级菜单";
               else if(value=="2")
            	   return "二级菜单";
               else if(value=="3")
            	   return "按钮";
                 return value;
             }
		
		}, {
			field : 'url',
			title : '菜单url',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'percode',
			title : '权限代码字符串',
			align : 'center',
			valign : 'middle'
		}
	/*	, {
			field : 'parentid',
			title : '父结点id',
			align : 'center',
			valign : 'middle'
		}*/
		, {
			field : 'parentname',
			title : '父结点',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

				 var html="根节点";
				 if(row.parentid!=0)
	                html="<a href='#' onclick='showparent(\""+row.parentid+"\",\""+row.parentname+"\")' >"+row.parentname+"</a>";

	                 return html;
	             }
		}
		, {
			
			field : 'sortstring',
			title : '排序号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'available',
			title : '是否可用', // 1：可用，0不可用
			align : 'center',
			valign : 'middle'
				,
				 formatter: function (value, row, index) {

	               if(value=="1")
	            	   return "可用";
	               else if(value=="0")
	            	   return "不可用";

	                 return str;
	             }
		}
            	, {
            field : 'icon',
            title : '图标',
            align : 'center',
            valign : 'middle'
        }

		, {
			title : '操作',
			field : 'xxx',
			align : 'center',
			formatter : DeleteButton,
			events : groupEvents
		}

		]
	});

}

/**
 * @return {string}
 */
function DeleteButton(value, row, index) {
	return [ '<div class="">'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-edit">编辑</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}

window.groupEvents = {
	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath()+"/privilege/permission/delete";
		//cconfirm(msg);
		cconfirm(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				dataType :"json",
				data : {
					"id" : row.id,
				},
				success : function(response) {
					if (response.bol) {
						success("删除成功！");
						doquery();
					} else {
						error("删除失败！"+response.message);
					}
				}
			});
		}
		);
		
	},

	"click #update" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = getRPath()+"/privilege/permission/load";

		/*// 触发全部验证
		$("#mform").data("bootstrapValidator").validate();

		// flag = true/false
		var flag = $("#mform").data("bootstrapValidator").isValid();*/

		/*if (flag) {*/
			$.ajax({
				type : "post",
				url : url,
				data : {
					id :row.id,
				},
				async : false,
				dataType : "json",
				success : function(data) {

					action="update";
					$("#id").val(data.id);
					$("#name").val(data.name);

					$("#type").val(data.type);
					
					
					changeParentList(function(){
						$('#parentid').val(data.parentid);// get(0).selectedIndex = 1;
					})
					
					

					$("#url").val(data.url);
					$("#percode").val(data.percode);
					
					//$("#parentid").val(data.parentid);

					$("#icon").val(data.icon);

					$("#sortstring").val(data.sortstring);
					$("#faction").html("修改");
					
					$("#available").find("option:selected").removeAttr('selected');
					$("#available").val(data.available);
					/*$("#available").find(
							"option[value='" + data.available + "']").attr(
							"selected", true);*/

					$("#myModal").modal();

				}
			});
	/*	}*/

	},

};


function loadMenuTree(role_id)
{
	
	var setting = {
			isSimpleData : true, // 数据是否采用简单 Array 格式，默认false
			treeNodeKey : "id", // 在isSimpleData格式下，当前节点id属性
			treeNodeParentKey : "pId", // 在isSimpleData格式下，当前节点的父节点id属性
			
			/*check : {
				autoCheckTrigger : false,
				chkboxType : {"Y": "ps", "N": "ps"},
				chkStyle : "checkbox",
				enable : true,
				nocheckInherit : false,
				radioType : "level"
				},*/
			view : {
				showLine : true, // 是否显示节点间的连线
			//	addHoverDom : addHoverDom, // 增加节点 点击新增
			//	removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : false,
				editNameSelectAll : false,
			//	renameTitle : renameTitle, // 编辑按钮说明文字
			//	removeTitle : removeTitle, // 删除按钮说明文字
			//	showRemoveBtn : showRemoveBtn, // 是否显示移除按钮
			//	showRenameBtn : showRenameBtn
			// 是否显示编辑按钮
			},
			callback : {
				onClick:function(){
					hideMenu();
				}
			//	beforeDrag : beforeDrag,
			//	beforeEditName : beforeEditName, // 编辑节点
			//	beforeRemove : beforeRemove, // 删除节点
			//	beforeRename : beforeRename,
			//	onRemove : onRemove,
			//	onRename : onRename,
			//	beforeDrop : beforeDrop,
			//	beforeClick : beforeClick,
			//	onCheck : onCheck
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPID : 0
				}
			}
		};
	
	
	//var role_id=$("#role_id").val();
	
	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			role_id : role_id
		},
		dataType : "json",
		url : getRPath()+"/privilege/permission/getMenuTreeSecond.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
//			if (typeof (data1.name) == undefined) {
//				data1.name = '';
//			}
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});


	
	
}


function doquery() {
	var opt = {
		url : getRPath()+'/privilege/permission/list',
		silent : true,
		pageNumber :1
	};
	$("#table_list").bootstrapTable('refresh', opt);
}