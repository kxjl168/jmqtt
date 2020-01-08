/**
 * 房间界面 select2相关
 */

//查询条件 小区选择select
function initRoomQ_CommunitySelect() {

	$("#q_room_community").select2({
		// dropdownParent : $("#myModal"),
		placeholder : "选择小区",
		minimumInputLength : 0,
		maximumSelectionLength : 1,
		theme : "bootstrap",
		multiple : true,
		language : {
			errorLoading : function() {
				return "无法载入结果。"
			},
			inputTooLong : function(e) {
				var t = e.input.length - e.maximum, n = "请删除" + t + "个字符";
				return n
			},
			inputTooShort : function(e) {
				var t = e.minimum - e.input.length, n = "请再输入至少" + t + "个字符";
				return n
			},
			loadingMore : function() {
				return "载入更多结果…"
			},
			maximumSelected : function(e) {
				var t = "";// "最多只能选择" + e.maximum + "个";
				return t
			},
			noResults : function() {
				return "未找到结果"
			},
			searching : function() {
				return "搜索中…"
			}

		},

		ajax : {
			type : "post",
			url : "/manager/community/communityList",
			dataType : "json",
			data : function(params) {

				var page = params.page || 0;

				var query = {
					name : params.term,
					pagenum : page,

					pageSize : 10,

				}

				// Query parameters will be ?search=[term]&type=public
				return query;
			},

			processResults : function(data, params) {
				var selectdatas = [];
				/*
				 * if(typeof(params.page)=="undefined") selectdatas.push({ id :
				 * -1, text : '新建属性' });
				 */

				$.each(data.rows, function(index, item) {
					selectdatas.push({
						id : item.id,
						text : item.name
					});
				});

				return {
					results : selectdatas,
					pagination : {
						more : (params.page * 10 >= data.total) ? false : true
					}
				};
			},
		},

		templateResult : formatRepo,
		escapeMarkup : function(markup) {
			return markup;
		}, // let our custom formatter work
		templateSelection : function(data, container) {
			// Add custom attributes to the <option> tag for the selected option
			// $(data.element).attr('data-custom-attribute', data.customValue);

			// $(container).parents("tr").find(".attr_id").val(data.id);
			return data.text;
		},

	});

	// Bind an event
	$('#q_room_community').on('select2:select', function(e) {
		// alert("11");
		$("#q_room_building").show();
		destoryRoomQ_SelectBuildingquery();
		initRoomQ_SelectBuildingquery();
	});

	$('#q_room_community').on('select2:unselect', function(e) {
		// alert("11");
		destoryRoomQ_SelectBuildingquery();
		initRoomQ_SelectBuildingquery();

	});

}


function destoryRoomQ_SelectBuildingquery() {
	$("#q_room_building").select2().val(null).trigger("change");
	$("#q_room_building").select2("destroy");
}

//查询条件 楼栋选择select
function initRoomQ_SelectBuildingquery() {
	$("#q_room_building")
			.select2(
					{
						// dropdownParent : $("#myModal"),
						placeholder : "选择楼栋",
						minimumInputLength : 0,
						maximumSelectionLength : 1,
						theme : "bootstrap",
						multiple : true,
						language : {
							errorLoading : function() {
								return "无法载入结果。"
							},
							inputTooLong : function(e) {
								var t = e.input.length - e.maximum, n = "请删除"
										+ t + "个字符";
								return n
							},
							inputTooShort : function(e) {
								var t = e.minimum - e.input.length, n = "请再输入至少"
										+ t + "个字符";
								return n
							},
							loadingMore : function() {
								return "";// "载入更多结果…"
							},
							maximumSelected : function(e) {
								var t = "";// "最多只能选择" + e.maximum + "个";
								return t
							},
							noResults : function() {
								return "未找到结果"
							},
							searching : function() {
								return "搜索中…"
							}

						},

						ajax : {
							type : "post",
							url : "/manager/building/buildingList",
							dataType : "json",
							data : function(params) {

								var page = params.page || 0;

								var cids = $("#q_room_community").val();

								var query = {
									name : params.term,
									pagenum : page,

									pageSize : 10,
									communityId : (cids == null || cids.length == 0) ? ""
											: cids[0],
								}

								// Query parameters will be
								// ?search=[term]&type=public
								return query;
							},

							processResults : function(data, params) {
								var selectdatas = [];
								/*
								 * if(typeof(params.page)=="undefined")
								 * selectdatas.push({ id : -1, text : '新建属性' });
								 */

								$.each(data.rows, function(index, item) {
									selectdatas.push({
										id : item.id,
										text : item.name
									});
								});

								return {
									results : selectdatas,
									pagination : {
										more : (params.page * 10 >= data.total) ? false
												: true
									}
								};
							},
						},

						templateResult : formatRepo,
						escapeMarkup : function(markup) {
							return markup;
						}, // let our custom formatter work
						templateSelection : function(data, container) {
							// Add custom attributes to the <option> tag for the
							// selected option
							// $(data.element).attr('data-custom-attribute',
							// data.customValue);

							// $(container).parents("tr").find(".attr_id").val(data.id);
							return data.text;
						},

					});
	
	
	
	// Bind an event
	$('#q_room_building').on('select2:select', function(e) {
		// alert("11");
		$("#q_room_room").show();
		destoryRoomQ_SelectRoomquery();
		initRoomQ_SelectRoomquery();
	});

	$('#q_room_building').on('select2:unselect', function(e) {
		// alert("11");
		destoryRoomQ_SelectRoomquery();
		initRoomQ_SelectRoomquery();

	});


}



function destoryRoomQ_SelectRoomquery() {
	$("#q_room_room").select2().val(null).trigger("change");
	$("#q_room_room").select2("destroy");
}

//查询条件 楼栋选择select
function initRoomQ_SelectRoomquery() {
	$("#q_room_room")
			.select2(
					{
						// dropdownParent : $("#myModal"),
						placeholder : "选择房间",
						minimumInputLength : 0,
						maximumSelectionLength : 1,
						theme : "bootstrap",
						multiple : true,
						language : {
							errorLoading : function() {
								return "无法载入结果。"
							},
							inputTooLong : function(e) {
								var t = e.input.length - e.maximum, n = "请删除"
										+ t + "个字符";
								return n
							},
							inputTooShort : function(e) {
								var t = e.minimum - e.input.length, n = "请再输入至少"
										+ t + "个字符";
								return n
							},
							loadingMore : function() {
								return "";// "载入更多结果…"
							},
							maximumSelected : function(e) {
								var t = "";// "最多只能选择" + e.maximum + "个";
								return t
							},
							noResults : function() {
								return "未找到结果"
							},
							searching : function() {
								return "搜索中…"
							}

						},

						ajax : {
							type : "post",
							url : "/manager/room/roomList",
							dataType : "json",
							data : function(params) {

								var page = params.page || 0;

								var cids = $("#q_room_building").val();

								var query = {
									name : params.term,
									pagenum : page,

									pageSize : 10,
									buildingId : (cids == null || cids.length == 0) ? ""
											: cids[0],
								}

								// Query parameters will be
								// ?search=[term]&type=public
								return query;
							},

							processResults : function(data, params) {
								var selectdatas = [];
								/*
								 * if(typeof(params.page)=="undefined")
								 * selectdatas.push({ id : -1, text : '新建属性' });
								 */

								$.each(data.rows, function(index, item) {
									selectdatas.push({
										id : item.id,
										text : item.name
									});
								});

								return {
									results : selectdatas,
									pagination : {
										more : (params.page * 10 >= data.total) ? false
												: true
									}
								};
							},
						},

						templateResult : formatRepo,
						escapeMarkup : function(markup) {
							return markup;
						}, // let our custom formatter work
						templateSelection : function(data, container) {
							// Add custom attributes to the <option> tag for the
							// selected option
							// $(data.element).attr('data-custom-attribute',
							// data.customValue);

							// $(container).parents("tr").find(".attr_id").val(data.id);
							return data.text;
						},

					});

}




function destoryRoomQ_SelectLockquery() {
	$("#lockId").select2().val(null).trigger("change");
	$("#lockId").select2("destroy");
}

//查询条件 楼栋选择select
function initRoomQ_SelectLockquery() {
	$("#lockId")
			.select2(
					{
						// dropdownParent : $("#myModal"),
						placeholder : "选择门锁",
						minimumInputLength : 0,
						maximumSelectionLength : 1,
						theme : "bootstrap",
						multiple : true,
						language : {
							errorLoading : function() {
								return "无法载入结果。"
							},
							inputTooLong : function(e) {
								var t = e.input.length - e.maximum, n = "请删除"
										+ t + "个字符";
								return n
							},
							inputTooShort : function(e) {
								var t = e.minimum - e.input.length, n = "请再输入至少"
										+ t + "个字符";
								return n
							},
							loadingMore : function() {
								return "";// "载入更多结果…"
							},
							maximumSelected : function(e) {
								var t = "";// "最多只能选择" + e.maximum + "个";
								return t
							},
							noResults : function() {
								return "未找到结果"
							},
							searching : function() {
								return "搜索中…"
							}

						},

						ajax : {
							type : "post",
							url : "/manager/lock/lockKeyList",
							dataType : "json",
							data : function(params) {

								var page = params.page || 0;

								var cids = $("#q_room_building").val();

								var query = {
									querykey : params.term,
									pagenum : page,

									pageSize : 10,
								
								}

								// Query parameters will be
								// ?search=[term]&type=public
								return query;
							},

							processResults : function(data, params) {
								var selectdatas = [];
								/*
								 * if(typeof(params.page)=="undefined")
								 * selectdatas.push({ id : -1, text : '新建属性' });
								 */

								$.each(data.rows, function(index, item) {
									selectdatas.push({
										id : item.id,
										text : item.name,
										imei:item.imei,
									});
								});

								return {
									results : selectdatas,
									pagination : {
										more : (params.page * 10 >= data.total) ? false
												: true
									}
								};
							},
						},

						templateResult : formatRepoLock,
						escapeMarkup : function(markup) {
							return markup;
						}, // let our custom formatter work
						templateSelection : function(data, container) {
							
							var id=data.imei;
							if(typeof(data.imei)=='undefined')
								id=$(data.element).attr("imei");
							
							var html = ""
								+ data.text+"&nbsp;&nbsp;("+ id+") ";
								

							// $(container).parents("tr").find(".attr_id").val(data.id);
							return html;
						},

					});

}


function formatRepoLock(repo) {
	if (repo.loading) {
		return repo.text;
	}

	var markup = 	 " <div class=\"lock-item in-line\"> "
	+ "<div class=\"lockname col-xs-3\">"+repo.text + " </div> "
	+ "  <div class=\"lockid col-xs-6\">(IMEI:" + repo.imei+")</div>"

			"</div> ";
	


	if (repo.id == -1) {
		markup = '<button type="button "  style=" width: 100%;" class=" btn btn-danger btn-warning " data-dismiss="modal ">'
				+ repo.text + '</button>';
	}

	return markup;
}

function formatRepo(repo) {
	if (repo.loading) {
		return repo.text;
	}

	var markup = repo.text;

	if (repo.id == -1) {
		markup = '<button type="button "  style=" width: 100%;" class=" btn btn-danger btn-warning " data-dismiss="modal ">'
				+ repo.text + '</button>';
	}

	return markup;
}
