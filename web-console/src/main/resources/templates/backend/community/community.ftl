

<div>

	<div class="queryclass">

		<form class="form-inline">
			<div class="form-group ">
				<label class='query_label' for="search_telephone">所在地区:</label>
				<div id="distpicker" data-toggle="distpicker" class="in-line">
					<select class="form-control" id="q_province"
						data-province="---- 选择省 ----">
					</select> <select class="form-control" id="q_city" data-city="---- 选择市 ----"></select>
					<select class="form-control" id="q_district"
						data-district="---- 选择区 ----"></select>
				</div>
			</div>

		</form>

		<form class="form-inline margin-top-10">
			<div class="form-group ">
				<label class='query_label' for="search_telephone">小区名称:</label> <input
					id="q_c_name" type="text" name="q_name" class="form-control qinput"
					placeholder="" aria-controls="dataTables-example">
			</div>


			<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="doSearch_c()">
				<i class="fa fa-search fa-lg"></i> <span>查询</span>
			</button>
			
			
			<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="test()">
				<i class="fa fa-search fa-lg"></i> <span>生成controller测试</span>
			</button>
				<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="test2()">
				<i class="fa fa-search fa-lg"></i> <span>生成service测试</span>
			</button>
				<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="testJS()">
				<i class="fa fa-search fa-lg"></i> <span>生成pageAnd JS测试</span>
			</button>
				<button type="button"
				class="btn hide  button-primary button-rounded button-small"
				onclick="test4()">
				<i class="fa fa-search fa-lg"></i> <span>生成js测试</span>
			</button>
		</form>

	</div>


	<div class="mainbody">
		<div class="row">
			<div class="col-xs-4" style="margin-top: 16px;">小区列表</div>
			<div class="col-xs-1 col-xs-push-7" style="padding-top: 10px;">
				<button type="button" class="btn btn-default" id="btnAdd_c">新增</button>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive" style="margin: 0 10px 10px 10px;">
				<table id="table_list_c"
					class="table table-bordered table-hover table-striped"></table>
			</div>
		</div>
	</div>


</div>


<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="static" id="myModal_c"
	tabindex="-1" role="dialog" aria-labelledby="myModal_cLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content width800">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModal_cLabel">
					<span id="myModal_c_title">添加</span>小区
				</h4>

			</div>

			<form id="mform_c" name="mform_c" class="form-horizontal" role="form"
				action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-12 row">


							<input type="hidden" id="id" name="id" />

							<div class="form-group in-line">
								<label for="name" class="labelc control-label in-line">地区:</label>

								<div class="col-xs-11 autowidth" id="imgs">
									<div id="distpicker2" data-toggle="distpicker2"
										class=" in-line">
										<select class="form-control in-line selectdist"
											name="province" id="province" data-province="---- 选择省 ----">
										</select> <select class="form-control in-line selectdist" id="city"
											name="city" data-city="---- 选择市 ----"></select> <select
											class="form-control in-line selectdist" id="district"
											name="district" data-district="---- 选择区 ----"></select>
									</div>
								</div>

							</div>

							<div class="form-group">
								<label for="name" class="labelc control-label">地址:</label>

								<div class="col-xs-10">
									<input type="text" name="address" class="form-control txt"
										id="address" placeholder="地址">
									<p class="help-block"></p>
								</div>
							</div>




							<div class="form-group">

								<input value="1" type="hidden" id="imgs_count"> <label
									for="name" class="labelc control-label">小区名:</label>

								<div class="col-xs-10 " id="imgs">
									<input type="text" name="name" class="form-control txt"
										id="name" placeholder="小区名称">
									<p class="help-block"></p>

								</div>

							</div>




							<div class="form-group inline">
								<div class="form-group">
									<label for="name" class="labelc control-label">经度</label>

									<div class="col-xs-9">

										<input type="text" name="longitude"
											class="form-control txt-short" id="longitude"
											placeholder="小区经度">
										<p class="help-block"></p>

									</div>
								</div>

								<div class="form-group">
									<label for="name" class="labelc control-label">纬度</label>

									<div class="col-xs-9">

										<input type="text" name="latitude"
											class="form-control txt-short" id="latitude"
											placeholder="小区纬度">
										<p class="help-block"></p>

									</div>
								</div>
							</div>


							<div class="form-group " id="container2_c">
								<iframe class="mapframe" src="/manager/community/map"></iframe>
							</div>


							<div class="modal-footer">

								<button type="button" class="btn btn-alarm" id="openmap_c">在地图上选择经纬度</button>

								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="close">关闭</button>
								<button type="button" class="btn btn-primary" id="btnSubmit_c">
									提交更改</button>
							</div>


						</div>




					</div>
				</div>

			</form>


		</div>
	</div>
</div>
