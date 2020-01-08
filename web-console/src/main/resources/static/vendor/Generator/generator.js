var action="new";
$(function() {
	groupTable();


	initValidate();

});
	// modal 新增基本字段事件 关闭事件事件， 清空已有的值 恢复禁用





function loadMenuTree(role_id)
{
	
	var setting = {
			isSimpleData : true, // 数据是否采用简单 Array 格式，默认false
			treeNodeKey : "id", // 在isSimpleData格式下，当前节点id属性
			treeNodeParentKey : "pId", // 在isSimpleData格式下，当前节点的父节点id属性
			
			check : {
				autoCheckTrigger : false,
				chkboxType : {"Y": "ps", "N": "ps"},
				chkStyle : "checkbox",
				enable : true,
				nocheckInherit : true,
				radioType : "level"
				},
			view : {
				showLine : true, // 是否显示节点间的连线
			//	addHoverDom : addHoverDom, // 增加节点 点击新增
			//	removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				enable : false,
				editNameSelectAll : false,
			},
			callback : {
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
	
	

	// 防止页面乱码现象
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		data : {
			role_id : role_id
		},
		dataType : "json",
		url : getRPath()+"/privilege/permission/getMenuTree.do",// 请求的action路径
		error : function() {// 请求失败处理函数
			error('请求失败');
		},
		success : function(data) { // 请求成功后处理函数
			var data1 = eval('[' + data + ']');
			zNodes = data1; // 把后台封装好的简单Json格式赋给treeNodes

			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});

	$("#selectAll").bind("click", selectAll);
	$("#treeDemo a").each(
			function() {
	
			});	
	
	
}

function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	var ck= $("#selectAll").get(0).checked;
	//alert(ck);
	zTree.checkAllNodes(ck);
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
						message : '角色名称不能为空'
					}
				}
			}
		
			,
			percode : {
				validators : {
				/*	notEmpty : {// 非空
						message : '权限代码不能为空'
					}*/
				}
			}

			

		}

	/*
	 * , captchaCode: { validators: { notEmpty: { message: '验证码不能为空' } } }
	 */
	});

}

function groupTable() {
	// 初始化Table
	$('#table_list').bootstrapTable({
		url : getRPath()+'/generator/list', // 请求后台的URL（*）
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
				sort : params.sort, // 要排序的字段
				sortOrder : params.order, // 排序规则
				tableName : $("#q_name").val()
			};
			return param;
		},
		columns : [ {
			field : 'tableName',
			title : '表名',
			align : 'center',
			visible : true
		}

		,
			{
			title : '已生成pojo/mapper',
			field : 'createPojo',
			align : 'center',
                formatter: function (v) {
                    if (v){
                        return ['<p class="text-done">是</p>']
                    }else {
                        return ['<p>否</p>']
                    }
                },
            visible : true

            }
            ,
            {
                title : '已生成service',
                field : 'createService',
                align : 'center',
                formatter: function (v) {
                    if (v){
                    	  return ['<p class="text-done">是</p>']
                    }else {
                        return ['<p>否</p>']
                    }
                },
                visible : true

            }
            ,
            {
                title : '已生成controller',
                field : 'createController',
                align : 'center',
                formatter: function (v) {
                    if (v){
                    	  return ['<p class="text-done">是</p>']
                    }else {
                        return ['<p>否</p>']
                    }
                },
                visible : true

            }
            ,
            {
                title : '已生成页面',
                field : 'createPageAndJs',
                align : 'center',
                formatter: function (v) {
                    if (v){
                    	  return ['<p class="text-done">是</p>']
                    }else {
                        return ['<p>否</p>']
                    }
                },
                visible : true

            }
           ,
			{
			title : '操作',
			field : 'xxx',
			align : 'center',
			formatter : createButtonA,
			events : groupEventsA,
            visible : true

            }

		]
	});

}

/**
 * @return {string}
 */
function createButtonA(value, row, index) {
    var pclass="";
    var sclass="";
    var jclass="";
    var cclass="";
    
    var pbtnClass=" fa-square-o";
    var sbtnClass=" fa-square-o";
    var cbtnClass=" fa-square-o";
    var jbtnClass=" fa-square-o";
	if (row.createPojo){
		//pclass="disabled";
		pbtnClass=" fa-check-square-o"
	}
    if (row.createService){
       // sclass="disabled";
        sbtnClass=" fa-check-square-o"
    }
    if (row.createController){
       // cclass="disabled";
        cbtnClass=" fa-check-square-o"
    }
    if (row.createPageAndJs){
        //jclass="disabled";
       jbtnClass=" fa-check-square-o"
    }
   
	
        return [
        	'<div class="pick">'
			+ '<button id = "create" type = "button" '+pclass+'  class = "btn btn-info"><i class="fa '+pbtnClass+'">生成POJO/Dao</i> </button>&nbsp;'
            + '<button id = "create1" type = "button" '+sclass+'  class = "btn btn-info"><i class="fa '+sbtnClass+'">生成Service</i> </button>&nbsp;'
            + '<button id = "create2" type = "button" '+cclass+'  class = "btn btn-info"><i class="fa '+cbtnClass+'">生成Controller</i> </button>&nbsp;'
            + '<button id = "create3" type = "button" '+jclass+'  class = "btn btn-info"><i class="fa '+jbtnClass+'">生成页面</i> </button>&nbsp;'
            + '<button id = "create4" type = "button"   class = "btn btn-info"><i class="fa fa-menu">生成菜单</i> </button>&nbsp;'
        + '<button id = "createAll" type = "button" '+''+'  class = "btn btn-warning"><i class="fa fa-crosshairs ">一键全部生成</i> </button>&nbsp;'
			+'</div>'
        ].join("");
   
}

window.groupEventsA = {
    "click #create" : function(e, value, row, index) {
    	
    	 var msg = "对应java文件已存在,确定要覆盖重新生成吗？";
    	if (row.createPojo){
    		
    		   cconfirm(msg,function(){
    		loadCols(row,1);
    		   });
        	
    	}
    	else{
    		loadCols(row,1);
    		
    	}
    	
    	return;
    	
    	
        if (row.createService){
           // sclass="disabled";
            sbtnClass=" fa-check-square-o"
        }
        if (row.createController){
           // cclass="disabled";
            cbtnClass=" fa-check-square-o"
        }
        if (row.createPageAndJs){
            //jclass="disabled";
           jbtnClass=" fa-check-square-o"
        }
       
        
    	
    	
    	
        var msg = "您真的确定要生成吗？";
        var url = getRPath()+"/generator/create";
        cconfirm(msg,function(){
            $.ajax({
                type : "post",
                url : url,
                dataType :"json",
                data : {
                    "choose" :1,
                    "tableName" : row.tableName
                },
                success : function(response) {
                    if (response) {
                        success("生成成功！");
                        doSearch_item();
                    } else {
                        error("生成失败！");
                    }
                }
            });
        });
    },
    "click #create1" : function(e, value, row, index) {
    	 var msg = "对应java文件已存在,确定要覆盖重新生成吗？";
    	if (row.createService){
    		
 		   cconfirm(msg,function(){
 		loadCols(row,2);
 		   });
     	
 	}
 	else{
 		loadCols(row,2);
 		
 	}
 	
 	return;
    	
    	loadCols(row,2);
    	return;
    	
       
        var url = getRPath()+"/generator/create";
        cconfirm(msg,function(){
            $.ajax({
                type : "post",
                url : url,
                dataType :"json",
                data : {
                    "choose" :2,
                    "tableName" : row.tableName
                },
                success : function(response) {
                    if (response) {
                        success("生成成功！");
                        doSearch_item();
                    } else {
                        error("生成失败！");
                    }
                }
            });
        });
    },
    "click #create2" : function(e, value, row, index) {
    	 var msg = "对应java文件已存在,确定要覆盖重新生成吗？";
     	if (row.createController){
     		
  		   cconfirm(msg,function(){
  		loadCols(row,3);
  		   });
      	
  	}
  	else{
  		loadCols(row,3);
  		
  	}
  	
  	return;
    
    	
    },
    "click #create3" : function(e, value, row, index) {
    	 var msg = "对应页面文件已存在,确定要覆盖重新生成吗？";
      	if (row.createPageAndJs){
      		
   		   cconfirm(msg,function(){
   		loadCols(row,4);
   		   });
       	
   	}
   	else{
   		loadCols(row,4);
   		
   	}
   	
   	return;
    	
       
    },
    
    "click #create4" : function(e, value, row, index) {
    	 var msg = "重新生成菜单";
      
      		
   		   cconfirm(msg,function(){
   		loadCols(row,5);
   		 
   		   	});
   	
  
    },
	"click #createAll" : function(e, value, row, index) {
		 var msg = "!确定要重新生成全部java及页面文件吗?这会覆盖已有内容";
	     
	   		   cconfirm(msg,function(){
	   		loadCols(row);
	   		   });
		
		
	}
};
/**
 * @return {string}
 */


function doSearch_item() {

    var opt = {
		url : getRPath()+'/generator/list',
		silent : true
	};
	$("#table_list").bootstrapTable('refresh', opt);
}

