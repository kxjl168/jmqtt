function test(){
	
	
	var url = getRPath()+"/manager/community/test";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}
function testJS(){
var url = getRPath()+"/manager/community/testJS";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}

function test2(){
	
	
	var url = getRPath()+"/manager/community/testService";
	
	$.ajax({
		type : "post",
		url : url,
		//data : data,
		async : false,
		dataType : "json",
		success : function(response) {
			success("操作成功！");
		}
	});
}

$(function() {
	InitQuery_c();



	$("#distpicker").distpicker({
		  autoSelect: false
		});
	$("#distpicker2").distpicker({
		  autoSelect: false
		});
	

	$("#btnAdd_c").click(function() {

	
		  $('#mform_c')[0].reset();
		  
		  $('#mform_c').find("#id").val("");
		  
		$("#myModal_c_title").html("添加");
		
		$("#distpicker2").distpicker({
			  autoSelect: true
			});
		
		$("#myModal_c").modal();
	});

	
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用
	$('#myModal_c').on('hide.bs.modal', function(e) {
		  $('#mform_c')[0].reset();

		$("#mform_c").data('bootstrapValidator').resetForm();

	});


	$("#btnSubmit_c").click(function() {
		
		
		
		$("#mform_c").data('bootstrapValidator').resetForm();
		
		
		 
		    // var bool2 = bv.isValid();
		$("#mform_c").data("bootstrapValidator").validate();
		// flag = true/false
		var flag = $("#mform_c").data("bootstrapValidator").isValid();

		var url = "/manager/community/saveOrUpdate";

		if (flag) {
			var data = $("#mform_c").serialize();

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
						$('#myModal_c').modal("hide");
						doSearch_c();
						success("操作成功！");
					} else {
						error( response.message);
					}
				}
			});
		}
	});

	initValidate_c();
	


	
	$("#openmap_c").click(function() {
		$("#container2_c").show();
		$("#openmap_c").hide();
	});

	$("#container2_c").hide();



});


function setxy_c(x,y)
{
cconfirm("您确定要修改位置吗?",function(){
	$("#mform_c").find("#longitude").val(x);
	$("#mform_c").find("#latitude").val(y);
		
	}
	,function(){});
	
}



function initValidate_c() {
	$("#mform_c").bootstrapValidator({
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
			district : {
				validators : {
					notEmpty : {
						message : '不能为空'
					},
					 callback: {
						                         message: '不能为空',
						                          callback: function(value, validator) {
						 
						                             if (value == ""||$("#mform_c").find("#city").val()==""||$("#mform_c").find("#province").val()=="") {
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
		

	
	});

}



function InitQuery_c() {
	// 初始化Table
	$('#table_list_c').bootstrapTable({
		url : '/manager/community/communityList', // 请求后台的URL（*）
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
			var param = {
				pageSize : params.limit, // 每页要显示的数据条数
				offset : params.offset, // 每页显示数据的开始行号
				sortName : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				
				name : $("#q_c_name").val(),
				
				province: $("#q_province").val(),
				city: $("#q_city").val(),
				district: $("#q_district").val(),
				
			};
			return param;
		},
		columns : [ {
			field : 'id',
			visible : false
		}, {
			field : 'name',
			title : '小区名称',
			align : 'center',
			valign : 'middle'
		}
		, {
			field : 'province',
			title : '省(直辖市)',
			align : 'center',
			valign : 'middle',
			 formatter: function (value, row, index) {

				 return value;//"<img class='rowimg img-responsive' src='"+$("#val2").val()+value+"' />";
	           }
		}
,
		
		{
			field : 'city',
			title : '城市',
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
			field : 'district',
			title : '区(县)',
			align : 'center',
			valign : 'middle'
		},
		{
			field : 'address',
			title : '地址',
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
			formatter : modifyAndDeleteButton_c,
			events : PersonnelInformationEvents_c
		}

		]
	});
}

function modifyAndDeleteButton_c(value, row, index) {
	return [ '<div class="">'
			+ '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
			+ '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
			+ '</div>' ].join("");
}




window.PersonnelInformationEvents_c = {
	"click #update" : function(e, value, row, index) {
		$.ajax({
			type : "post",
			url : '/manager/community/load',
			data : {
				id : row.id
			},
			async : false,
			dataType : "json",
			success : function(response) {
				
			   $("#mform_c").fill(response);
			     
		/*	     var $distpicker=$("#distpicker2");
			     $distpicker.distpicker('destory');
			    $distpicker.distpicker({
			    	  province: response.province,
			    	  city: response.city,
			    	  district: response.district,
			    	  
			    	});
			    */
			   
			   $("#myModal_c_title").html("编辑");
			   
			   setArea("#mform_c",response);
				$("#myModal_c").modal();
				
			
			}
		});

	},

	"click #delete" : function(e, value, row, index) {
		var msg = "您真的确定要删除吗？";
		var url = "/manager/community/delete";
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
						doSearch_c();
					} else {
						error(""+response.message);
					}
				}
			});
		});
		
	}
};

function doSearch_c() {
	
	
	
	var opt = {
		silent : true
	};
	$("#table_list_c").bootstrapTable('refresh', opt);
	
	//success("test");
}

