$(function() {
	InitQuery_b();

	

	$("#distpicker3").distpicker({
		  autoSelect: false
		});
	

	$("#btnAdd_b").click(function() {

		
		destorymodelBuildingselect();
		initmodelBuildingselect();
		
		  $('#mform_b')[0].reset();
		  $('#mform_b').find("#id").val("");
		$("#myModal_b_title").html("添加");
		
	
		$("#myModal_b").modal();
	});

	
	$("#btnCreateFloorRoom").click(function(){
		if(typeof($("#floorHeight_index").val())=="undefined"||typeof($("#roomNumber_index").val())=="undefined")
			{
			info("'层' 和 '户' 数没有填全!");
			return;
			}
		//buildingid,layernum,roomnum
		addAllRoom(  $("#mform_r").find("#id").val(), $("#floorHeight_index").val(),$("#roomNumber_index").val() );
		
	});
	
	//房间编辑框内数据点击事件-删除单个
	 $(".roomNo").on("click","li.roomitem>span",function(e){  
		 //del room
		 var item=e.target;
		 var floorNo=$(item).parent().parent().find(".floorNo ").attr('layer');
		 var roomId=$(item).parent().find(".room_id").val();
		 var roomName= $(item).parent().find(".tooltip-f").html();
		 
		 //info(floorNo+"层删除房间"+roomId+"/"+roomName);
		 
		 
		 cconfirm("确定要删除房间"+roomName+"吗?",function(){
			
			 //检查是否已有锁关联
			 $.ajax({
					type : "post",
					url : '/manager/room/load',
					data : {
						id : roomId,
					},
					async : false,
					dataType : "json",
					success : function(response) {
						
						if(!response)
							{
							
							//数据库没有房间信息，新添加未提交的，直接修改界面
							
							$(item).parent().remove();
							
							ShowOrHideAddRoomBtn(floorNo);
							return;
							}
			
						if(response.isLock!=null&&response.isLock=='1')
							{
							 error("该房间已绑定门锁，请先解除绑定！");
							}
						else
							{
							
							$(item).parent().remove();
							
							ShowOrHideAddRoomBtn(floorNo);
							}
							
						
					},
					error:function(){
						
						//失败，数据库没有房间信息，新添加未提交的，直接修改界面
						
						$(item).parent().remove();
						
						ShowOrHideAddRoomBtn(floorNo);
					}
			 });
			 
			 
		 },function(){
			 
		 });
		 
		 
	 });
	//房间编辑框内数据点击事件 -新增单个
	 $(".roomNo").on("click","li.addRoom",function(e){
		 //add room
		 var item=e.target;
		 
		 var fllorNo=$(item).parent().find(".floorNo ").attr('layer');
		 
		// info(fllorNo+"层添加新房间");
		 
		 $("#myModal_eroom_title").html("添加");
		 

		 $("#myModal_eroom").find('#id').val("");
		 $("#myModal_eroom").find('#fllorNo').val(fllorNo);
		 $("#myModal_eroom").find('#roomName').val("");
		 
		 $("#myModal_eroom").modal();
	 });
	//房间编辑框内数据点击事件 -编辑单个
	 $(".roomNo").on("click","li.roomitem .tooltip-f",function(e){
		 //modify room
		 var item=e.target;
		 var fllorNo=$(item).parent().parent().find(".floorNo ").attr('layer');
		 var roomName= $(item).html();
		 var roomId= $(item).parent().find('.room_id').val();
		 
		// info('编辑'+roomName+"/"+roomId);
		 
			$("#myModal_eroom_title").html("修改");
			
			 $("#myModal_eroom").find('#roomName').val(roomName);
			 $("#myModal_eroom").find('#id').val(roomId);
			 $("#myModal_eroom").find('#fllorNo').val(fllorNo);
			 $("#myModal_eroom").modal();
	 });
	 
		//房间编辑框内hover 详情名字显示
	 $(".roomNo").on("mouseover","li.roomitem .tooltip-f",function(e){
		 	var item=e.target;
		 	var roomName= $(item).html();
		 	$(this).popover({
		 			placement:'auto bottom',
		 				content: roomName,
		 		});
		 	$(this).popover('show');
	 });
	 $(".roomNo").on("mouseout","li.roomitem .tooltip-f",function(e){
		 	var item=e.target;
		 
		 	$(this).popover('destroy');
	 });
	
	
	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_b').on('hide.bs.modal', function(e) {
		  $('#mform_b')[0].reset();

		$("#mform_b").data('bootstrapValidator').resetForm();

	});
	
	

	
$("#btnSubmit_eroom").click(function() {
		
		//单个房间修改

	 var fllorNo= $("#myModal_eroom").find('#fllorNo').val();
	 var roomName= $("#myModal_eroom").find('#roomName').val();
	 var roomId=  $("#myModal_eroom").find('#id').val();
	 
	 if(roomId!='')
		 {
		 //修改房间 替换原名称
		var roomidInput= $(".roomNo").find(".f_"+fllorNo).find(".room_id[value='"+roomId+"']");
		$(roomidInput).parent().find('.tooltip-f').html(roomName);
		 }
	 else
		 {
		 //新增房间
		 var eleFloor= $(".roomNo").find(".f_"+fllorNo);
		 var buildingid=$("#mform_r").find("#id").val();
		roomId=guid();
		
		addOneRoom(buildingid,fllorNo,roomId,roomName);
		 
		 }
		
	 
	 $('#myModal_eroom').modal("hide");

	});
	
$("#btnSubmit_r").click(function() {
		
		//房间创建整体修改

		
		var url = "/manager/room/modifyRooms";

		var allrooms=new Array();
		
		var floors= $("#mform_r").find('.floor');
		
		$.each(floors,function(index,itemfloor){
			
			
		var layerid= $(itemfloor).find('.floorNo').attr("layer");
			
			var rooms= $(itemfloor).find(".roomitem");
			 $.each(rooms,function(index,item){
				 
				 var room={};
				 room.id= $(item).find(".room_id").val();
					room.name= $(item).find(".tooltip-f").html();
					room.layer= layerid;
				 
					allrooms.push(room);
				 
			 })
			
			
			
			
		});
		
		info("数据提交中...");
		
		$('#btnSubmit_r').attr("disabled",true);
		
		
		//return;
	
		 

			$.ajax({
				type : "post",
				url : url,
				data : {rooms:JSON.stringify(allrooms),building_id: $("#mform_r").find("#id").val()},
				async : true,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.bol) {
						$('#myModal_r').modal("hide");
						$('#btnSubmit_r').removeAttr("disabled");
						//doSearch_b();
						success("操作成功！");
					} else {
						error( response.message);
					}
				},
				error:function(){
					$('#btnSubmit_r').removeAttr("disabled");
				}
				
			});
		
	});

	$("#btnSubmit_b").click(function() {
		
		//楼栋编辑
		
		$("#mform_b").data('bootstrapValidator').resetForm();
		$("#mform_b").data("bootstrapValidator").validate();
		// flag = true/false
		var flag = $("#mform_b").data("bootstrapValidator").isValid();

		var url = "/manager/building/saveOrUpdate";

		if (flag) {
			var data = $("#mform_b").serializeArray();

		
			
		    

			$.ajax({
				type : "post",
				url : url,
				data : data,
				async : false,
				dataType : "json",
				success : function(response) {
					// debugger;
					if (response.bol) {
						$('#myModal_b').modal("hide");
						doSearch_b();
						success("操作成功！");
					} else {
						error( response.message);
					}
				}
			});
		}
	});

	initValidate_b();
	
	$("#openmap_b").click(function() {
		$("#container2_b").show();
		$("#openmap_b").hide();
	});

	$("#container2_b").hide();


		
	initCommunitySelect();
	
	$("#q_building").hide();
	//initSelectBuildingquery();
	
	//initmodelBuildingselect();
	
	$("#mform_b").find("#district").change(function(){
		
	
		
		destorymodelBuildingselect();

		 initmodelBuildingselect();

	});
});




function setxy_b(x,y)
{
cconfirm("您确定要修改位置吗?",function(){
	
	
$("#mform_b").find("#longitude").val(x);
$("#mform_b").find("#latitude").val(y);
		
	}
	,function(){});
	
}

function initValidate_b() {
	$("#mform_b").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		// submitButtons : 'button[type="submit"]',// 提交按钮
		fields : {

			name : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			address : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			communityId : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			roomNumber : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					regexp: {
							regexp: /^\d+$/,
							message: '必须为数字'
					}
				}
			},
			
			
			district : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					 callback: {
						                         message: '不能为空',
						                          callback: function(value, validator) {
						 
						                             if (value == ""||$("#mform_b").find("#city").val()==""||$("#mform_b").find("#province").val()=="") {
						                                return false;
						                             } else {
						                                 return true;
						                             }
						 
						                        }
						                     }
				}
			},
			
			latitude : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},	
				}
			},
			longitude : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},	
				}
			},
			
			 sort: {
					notEmpty : {
						message : '排序不能为空'
					},
					 regexp: {
                          regexp: /^[+]?[1-9]\d*$/,
                          message: '排序为大于0的数字'
                      }
					
             }
			
			


		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}



function InitQuery_b() {
	// 初始化Table
	$('#table_list_b').bootstrapTable({
		url : '/manager/building/buildingList', // 请求后台的URL（*）
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
			
			 var cids=	$("#q_b_community").val();
				
			 var communityId=(cids==null||cids.length==0)?"":cids[0];
					
					 var cbids=	$("#q_building").val();
				
					 var bid=(cbids==null||cbids.length==0)?"":cbids[0];
					 
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				
				communityId : communityId,
				id:bid,
				
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}, {
			field : 'name',
			title : '楼栋名称',
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
			field : 'address',
			title : '地址',
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
			field : 'roomnum',
			title : '房间数量',
			align : 'center',
			valign : 'middle'
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
			formatter : modifyAndDeleteButton_b,
			events : PersonnelInformationEvents_b
		}

		]
	});
}

function modifyAndDeleteButton_b(value, row, index) {
	return [ '<div class="">'
		+ '<button id = "updateroom" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">编辑房间</i> </button>&nbsp;'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">编辑楼栋</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除楼栋</i> </button>'
			+ '</div>' ].join("");
}

var maxfloor=5;
var maxroom=5;
function keyupFloor(ele,index){
	var value= $(ele).val().replace(/\D/g,'');
	
	var max=maxfloor;
	if(index==1)
		max=maxfloor;
	else
		max=maxroom;
		
		
	if(parseInt(value)>max)
		$(ele).val(max);
	else if(parseInt(value)<1)
		$(ele).val(1);
	else
		$(ele).val(value);
		
	
}


window.PersonnelInformationEvents_b = {
		"click #updateroom" : function(e, value, row, index) {
			
			

			
			  $('#mform_r')[0].reset();
			  $('#mform_r').find("#id").val("");
			  $('#mform_r').fill(row);
			  
			  maxfloor=row.floorHeight;
			  maxroom=row.roomNumber;
			  
			  
			  $("#roomNumber_index").val(row.roomNumber);
			  $("#floorHeight_index").val(row.floorHeight);
			  
			  
			  
				$("#roomContainer").html("");
			$("#myModal_r").modal();
			
			$(".room_loading").show();
			$(".roomNo").html("");
			
			$.ajax({
				type : "post",
				url : '/manager/room/roomList',
				data : {
					buildingId : row.id,
					pagenum : 1,
					pageSize : 1000
				},
				async : false,
				dataType : "json",
				success : function(response) {
					
					$(".room_loading").hide();
					//设置已有房间
					
					listAllRoomInbuild(response);
					
				
				}
			});

		},
		
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url : '/manager/building/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			   $("#mform_b").fill(response);

			   
			   $("#myModal_b_title").html("编辑");
			   
			   setArea("#mform_b",response);
			   
			   //select2
				destorymodelBuildingselect();
				initmodelBuildingselect();

			   var option = new Option(response.communityName, response.communityId, true, true);
			    $("#mform_b").find("#communityId").append(option).trigger('change');

			    // manually trigger the `select2:select` event
			    $("#mform_b").find("#communityId").trigger({
			        type: 'select2:select',
			        params: {
			            data: {text:response.communityName,id:response.communityId}
			        }
			    });
			   
				$("#myModal_b").modal();
				
			
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/building/delete";
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
						doSearch_b();
					} else {
						error(""+response.message);
					}
				}
			});
		});
		
	}
};

function doSearch_b() {
	
	
	
	var opt = {
		silent : true
	};
	$("#table_list_b").bootstrapTable('refresh', opt);
	
	//success("test");
}

