/**
 * 楼栋界面 select2相关
 */

//查询条件 小区选择select
function initCommunitySelect() {

	$("#q_b_community").select2({
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
	$('#q_b_community').on('select2:select', function(e) {
		// alert("11");
		$("#q_building").show();
		destorySelectBuildingquery();
		initSelectBuildingquery();
	});

	$('#q_b_community').on('select2:unselect', function(e) {
		// alert("11");
		destorySelectBuildingquery();
		initSelectBuildingquery();

	});

}
function destorySelectBuildingquery() {
	$("#q_building").select2().val(null).trigger("change");
	$("#q_building").select2("destroy");
}

//查询条件 楼栋选择select
function initSelectBuildingquery() {
	$("#q_building")
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

								var cids = $("#q_b_community").val();

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

}

function destorymodelBuildingselect() {
	$("#mform_b").find("#communityId").select2().val(null).trigger('change');
	$("#mform_b").find("#communityId").select2("destroy");
}


//楼栋编辑  小区选择select
function initmodelBuildingselect() {

	$("#mform_b")
			.find("#communityId")
			.select2(
					{
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
							url : "/manager/community/communityList",
							dataType : "json",
							data : function(params) {

								var page = params.page || 0;

								var query = {
									name : params.term,
									pagenum : page,

									pageSize : 10,

									// 选择空，不能查询到楼栋信息
									province : $("#mform_b").find("#province")
											.val() == "" ? "-1" : $("#mform_b")
											.find("#province").val(),
									city : $("#mform_b").find("#city").val() == "" ? "-1"
											: $("#mform_b").find("#city").val(),
									district : $("#mform_b").find("#district")
											.val() == "" ? "-1" : $("#mform_b")
											.find("#district").val(),

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

	/*
	 * // Bind an event $("#mform_b").find("#communityId").on('select2:select',
	 * function (e) {
	 * $("#mform_b").data("bootstrapValidator").validateField("province"); });
	 * 
	 * $("#mform_b").find("#communityId").on('select2:unselect', function (e) {
	 * $("#mform_b").data("bootstrapValidator").validateField("province");
	 * 
	 * 
	 * });
	 */
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
