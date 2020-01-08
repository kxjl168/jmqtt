$(function() {
	InitQuery_room();



/*	$("#distpicker").distpicker({
		  autoSelect: false
		});
	$("#distpicker2").distpicker({
		  autoSelect: false
		});
	*/

	$("#btnAdd_room").click(function() {

	
		  $('#mform_room')[0].reset();
		  
		  $('#mform_room').find("#id").val("");
		  
		$("#myModal_room_title").html("添加");
		
		$("#distpicker2").distpicker({
			  autoSelect: true
			});
		
		$("#myModal_room").modal();
	});

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_room').on('hide.bs.modal', function(e) {
		  $('#mform_room')[0].reset();

		$("#mform_room").data('bootstrapValidator').resetForm();

	});


	$("#btnSubmit_room").click(function() {
		
		
		
		$("#mform_room").data('bootstrapValidator').resetForm();
		
		    // var bool2 = bv.isValid();
		$("#mform_room").data("bootstrapValidator").validate();
		// flag = true/false
		var flag = $("#mform_room").data("bootstrapValidator").isValid();

		var url = "/manager/roomLock/saveOrUpdate";

		if (flag) {
			var data = $("#mform_room").serialize();

			/**/

			$.ajax({
				type : "post",
				url : url,
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.bol) {
						$('#myModal_room').modal("hide");
						doSearch_room();
						success("操作成功！");
					} else {
						error( response.message);
					}
				}
			});
		}
	});

	initValidate_room();
	


	
	$("#openmap_room").click(function() {
		$("#container2_room").show();
		$("#openmap_room").hide();
	});

	$("#container2_room").hide();
	
	
	initRoomQ_CommunitySelect();
	$("#q_room_building").hide();
	$("#q_room_room").hide();



});


function setxy_room(x,y)
{
cconfirm("您确定要修改位置吗?",function(){
	$("#mform_room").find("#longitude").val(x);
	$("#mform_room").find("#latitude").val(y);
		
	}
	,function(){});
	
}



function initValidate_room() {
	$("#mform_room").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			lockId : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			
			
			
			


		}
		

	
	});

}



function InitQuery_room() {
	// 初始化Table
	$('#table_list_room').bootstrapTable({
		url : '/manager/room/roomList', // 请求后台的URL（*）
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
			 var cids=	$("#q_room_community").val();
			 var communityId=(cids==null||cids.length==0)?"":cids[0];
			 
			 var bids=	$("#q_room_building").val();
			 var buildingId=(bids==null||bids.length==0)?"":bids[0];
			 
			 var ids=	$("#q_room_room").val();
			 var roomid=(ids==null||ids.length==0)?"":ids[0];
			
			
			
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				
				isLock : $("#q_is_lock").val(),
				
				communityId:communityId,
				buildingId: buildingId,
				id: roomid,
				
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}, {
			field : 'name',
			title : '房间名称',
			align : 'center',
			valign : 'middle'
		}
		, {
			field : 'communityName',
			title : '小区名称',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

				 return value;//"<img class='rowimg img-responsive' src='"+$("#val2").val()+value+"' />";
	           }
		}
,
		
		{
			field : 'buildingName',
			title : '楼栋名称',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

				 return value;//
	             if(value=="1")
	          	   return "启用";
	             else if(value=="0")
	          	   return "禁用";
				 }
		}
		,
		
		{
			field : 'address',
			title : '地址',
			align : 'center',
			valign : 'middle'
		},

		{
			field : 'isLock',
			title : '是否配锁',
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {

				
	             if(value=="1")
	          	   return "是";
	             else
	          	   return "<span class='text-warning'>否</span>";
				 }
		},
		
		{
			field : 'lockStatus',
			title : '是否激活',
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {

				
	             if(value=="1")
	          	   return "是";
	             else
	          	   return "<span class='text-warning'>否</span>";
				 }
		},
		
	/*	{
			field : 'district',
			title : '排序',
			align : 'center',
			valign : 'middle'
		},*/
		
		
		
		{
			title : '操作',
			field : 'vehicleno',
			align : 'center',
			formatter : modifyAndDeleteButton_room,
			events : PersonnelInformationEvents_room
		}

		]
	});
}

function modifyAndDeleteButton_room(value, row, index) {
	
	var enable=" disabled='disabled'";
	if(row.isLock=="1")
		enable="";
	
	return [ '<div class="">'
		+ '<button id = "updatelock" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">配置门锁</i> </button>&nbsp;'
			+ '<button id = "updateunlock" type = "button" '+enable+' class = "btn btn-warning"><i class="glyphicon glyphicon-pencil">门锁解绑</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除房间</i> </button>'
			+ '</div>' ].join("");
}




window.PersonnelInformationEvents_room = {
		
		"click #updatelock" : function(e, value, row, index) {
			$.ajax({
				type : "post",
				url : '/manager/room/load',
				data : {
					id : row.id
				},
				async : false,
				dataType : "json",
				success : function(response) {
					
				   $("#mform_room").fill(response);
				     
				   destoryRoomQ_SelectLockquery();
				   
				   $("#mform_room").find("#lockId").html("");
				   
				   initRoomQ_SelectLockquery();
				   
				  
				   if( response.lockId)
					   {
				   var option = new Option(response.lockName, response.lockId, true, true);
				   
				   $(option).attr("imei",response.lockImei);
				   
				    $("#mform_room").find("#lockId").append($(option)).trigger('change');

				    // manually trigger the `select2:select` event
				    $("#mform_room").find("#lockId").trigger({
				        type: 'select2:select',
				        params: {
				            data: {text:response.lockName,imei:response.lockImei, id:response.lockId}
				        }
				    });
					   }
				   
				   $("#myModal_room_title").html("编辑");
				   
				
					$("#myModal_room").modal();
					
				
				}
			});

		},
	"click #updateunlock" : function(e, value, row, index) {
		
		cconfirm("确定要解绑门锁吗?",function(){
			
			$.ajax({
				type : "post",
				url : '/manager/roomLock/delete',
				data : {
					roomId : row.id
				},
				async : false,
				dataType : "json",
				success : function(response) {
					if(response.bol)
						{
					success("解绑成功!");
					doSearch_room();
						}
					else
						{
						error(response.message);
						}
				}
			});
		});
		
	

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/room/delete";
		cconfirm(msg,function() {
			$.ajax({
				type : "post",
				url : url,
				data : {
					"id" : row.id
				},
				success : function(response) {
					if (response.bol) {
						success("删除成功！");
						doSearch_room();
					} else {
						error(""+response.message);
					}
				}
			});
		});
		
	}
};

function doSearch_room() {
	
	
	
	var opt = {
		silent : true
	};
	$("#table_list_room").bootstrapTable('refresh', opt);
	
	//success("test");
}

