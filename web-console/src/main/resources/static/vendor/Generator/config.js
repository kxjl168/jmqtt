/**
 * 
 */

$(function() {

	initColTable();

	initQueryTable();

	initValidate_Configitem();

	$("#btnSubmit_item").click(function() {

		
		
		$("#mform_base").data('bootstrapValidator').resetForm();

		$("#mform_base").data("bootstrapValidator").validate();
		var flag = $("#mform_base").data("bootstrapValidator").isValid();

		if (!flag) {
			$('#myTab li:eq(0) a').tab('show');
			return;
		}
		var data = $("#mform_base").serialize();
		if(geneType=="4")
			{
			var selectCol = $("#table_list_col").bootstrapTable('getSelections');

			if (selectCol == null || selectCol.length == 0) {
				error("请至少选择一个呈现字段!")
				return;
			}

			var selectQCol = $("#table_list_query").bootstrapTable('getSelections');
			if (selectQCol == null || selectQCol.length == 0) {
				error("请至少选择一个查询字段!")
				return;
			}

			var data = $("#mform_base").serialize();

			data += "&cols=" + encodeURIComponent(JSON.stringify(selectCol))
					+ "&qcols=" + encodeURIComponent(JSON.stringify(selectQCol));

			
			}
		
		data+='&choose='+geneType;
		var msg="确定生成?";
		if (geneType) {
			var url = getRPath() + "/generator/create";
			cconfirm(msg, function() {
				$.ajax({
					type : "post",
					url : url,
					dataType : "json",
					data :data,
					success : function(response) {
						// debugger;
						if (response.bol) {
							$('#myModal_item').modal("hide");
							doSearch_item();
							success("操作成功！");
							showRefreshTip();
						} else {
							error(response.message);
						}
					}
				});
			});

		} else {
			generatorAll();
		}

	});

});

function generatorAll() {

	$("#mform_base").data('bootstrapValidator').resetForm();

	$("#mform_base").data("bootstrapValidator").validate();
	var flag = $("#mform_base").data("bootstrapValidator").isValid();

	if (!flag) {
		$('#myTab li:eq(0) a').tab('show');
		return;
	}

	var selectCol = $("#table_list_col").bootstrapTable('getSelections');

	if (selectCol == null || selectCol.length == 0) {
		error("请至少选择一个呈现字段!")
		return;
	}

	var selectQCol = $("#table_list_query").bootstrapTable('getSelections');
	if (selectQCol == null || selectQCol.length == 0) {
		error("请至少选择一个查询字段!")
		return;
	}

	var data = $("#mform_base").serialize();

	data += "&cols=" + encodeURIComponent(JSON.stringify(selectCol))
			+ "&qcols=" + encodeURIComponent(JSON.stringify(selectQCol));

	var url = getRPath() + "/generator/createAll";
	var msg = "确定要全部重新生成吗?";
	cconfirm(msg, function() {
		$.ajax({
			type : "post",
			url : url,
			data : data,
			async : false,
			dataType : "json",
			success : function(response) {
				// debugger;
				if (response.bol) {
					$('#myModal_item').modal("hide");
					doSearch_item();
					success("操作成功！");
					//showRefreshTip();
					refreshMenu();
				} else {
					error(response.message);
				}
			}
		});
	});

}

function initColTable() {
	// 初始化Table
	$('#table_list_col').bootstrapTable({
		url : getRPath() + '/generator/listCols', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 20, // 每页的记录行数（*）
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
				tableName : curTableName,
			};
			return param;
		},
		columns : [ {
			title : '是否显示',
			checkbox : true,
			formatter : function(i, row) { // 每次加载 checkbox 时判断当前 row 的 id
				// 是否已经存在全局 Set() 里
				if (seletColRows < rowColmax) {
					seletColRows++;

					return {
						checked : true
					// 存在则选中
					}
				} else
					return {
						checked : false
					// 存在则选中
					}

			}
		}, {
			field : 'sqlcolname',
			title : '字段名',
			align : 'center',
			visible : true
		}

		, {
			field : 'id',
			title : 'Pojo名称',
			align : 'center',
			visible : true
		}

		, {
			field : 'displayName',
			title : '字段显示名称',
			align : 'center',

			visible : true,
			editable : {
				type : 'text',
				title : '字段显示名称',
				validate : function(v) {
					if (!v)
						return '不能为空';

				}
			}
		}

		],
		onEditableSave : function(field, row, oldValue, $el) {
			/*
			 * $.ajax({ type: "post", url: "/Editable/Edit", data: row,
			 * dataType: 'JSON', success: function (data, status) { if (status ==
			 * "success") { alert('提交数据成功'); } }, error: function () {
			 * alert('编辑失败'); }, complete: function () { }
			 * 
			 * });
			 */
		}
	});

}

function initValidate_Configitem() {
	$("#mform_base").bootstrapValidator({
		feedbackIcons : {
			/* input状态样式通过，刷新，非法三种图片 */
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded : [ ":disabled" ], // 不可见的也验证
		fields : {

			basepackageName : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			pagetitle : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},
			logName : {
				validators : {
					notEmpty : {
						message : '不能为空'
					}
				}
			},

		}

	});

}

function initQueryTable() {
	// 初始化Table
	$('#table_list_query').bootstrapTable({
		url : getRPath() + '/generator/listCols', // 请求后台的URL（*）
		method : 'post', // 请求方式（*）
		contentType : 'application/x-www-form-urlencoded',
		toolbar : '#toolbar', // 工具按钮用哪个容器
		showHeader : true,
		searchAlign : 'left',
		buttonsAlign : 'left',

		searchOnEnterKey : true,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : false, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortName : 'id', // 排序字段
		sortOrder : "desc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 20, // 每页的记录行数（*）
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
				tableName : curTableName,
			};
			return param;
		},
		columns : [ {
			title : '是否显示',
			checkbox : true,
			formatter : function(i, row) { // 每次加载 checkbox 时判断当前 row 的 id
				// 是否已经存在全局 Set() 里
				if (seletRows < rowmax) {
					seletRows++;

					return {
						checked : true
					// 存在则选中
					}
				} else
					return {
						checked : false
					// 存在则选中
					}

			}
		}, {
			field : 'sqlcolname',
			title : '字段名',
			align : 'center',
			visible : true
		}

		, {
			field : 'id',
			title : 'Pojo名称',
			align : 'center',
			visible : true
		}

		, {
			field : 'displayName',
			title : '字段显示名称',
			align : 'center',

			visible : true,
			editable : {

				type : 'text',
				title : '字段显示名称',
				validate : function(v) {
					if (!v)
						return '不能为空';

				}
			}
		}

		]
	});

}

var geneType = "";// 生成模式 1，2，3，4 ,5， “” pojo/dao, service,controller,page,menu,all

var seletRows = 0;// 默认已选查询条件
var rowmax = 2;// 默认最大选择查询条件

var seletColRows = 0;// 默认已选列表显示字段
var rowColmax = 8;// 默认最大列表显示字段

var curTableName = "";
function loadCols(row, type) {

	geneType = type;
	seletRows = 0;
	
	seletColRows=0;
	
	curTableName = row.tableName;
	var opt = {
		silent : true
	};
	$("#table_list_col").bootstrapTable('refresh', opt);
	$("#table_list_query").bootstrapTable('refresh', opt);

	row.logName = row.tableDisplayName;
	row.pagetitle = row.tableDisplayName;

	$("#mform_base").fill(row);

	if(type) //单独生成
		{
		$('#tabcol').hide();
		$('#tabquery').hide();
		if(type=="4")
			{
			$('#tabcol').show();
			$('#tabquery').show();
			}
		}
	else{
		$('#tabcol').show();
		$('#tabquery').show();
	}
	
	$("#myModal_item").modal();

}
