

<div>

	<div class="queryclass2 bquery">

		<form class="form-inline">
			<div class="form-group ">
				<label class='query_label' for="search_telephone">小区房源:</label>
				<div class="in-line">
					<select
						class="form-control txt-select-building in-line selectdist "
						id="q_b_community" name="q_b_community">

					</select> <select class="form-control txt-select-building in-line  "
						id="q_building" name="q_building">

					</select>


				</div>
			</div>

			<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="doSearch_b()">
				<i class="fa fa-search fa-lg"></i> <span>查询</span>
			</button>

		</form>

		<form class="form-inline margin-top-10">
			<!-- 	<div class="form-group ">
				<label class='query_label' for="search_telephone">小区名称:</label> <input
					id="q_b_name" type="text" name="q_name" class="form-control qinput"
					placeholder="" aria-controls="dataTables-example">
			</div>


			<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="doSearch()">
				<i class="fa fa-search fa-lg"></i> <span>查询</span>
			</button>
			 -->
		</form>

	</div>


	<div class="mainbody">
		<div class="row">
			<div class="col-xs-4" style="margin-top: 16px;">楼栋列表</div>
			<div class="col-xs-1 col-xs-push-7" style="padding-top: 10px;">
				<button type="button" class="btn btn-default" id="btnAdd_b">新增</button>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive" style="margin: 0 10px 10px 10px;">
				<table id="table_list_b"
					class="table table-bordered table-hover table-striped"></table>
			</div>
		</div>
	</div>


</div>


<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="static" id="myModal_b"
	tabindex="-1" role="dialog" aria-labelledby="myModal_bLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content width800">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModal_bLabel">
					<span id="myModal_b_title">添加</span>楼栋
				</h4>

			</div>

			<form id="mform_b" name="mform_b" class="form-horizontal" role="form"
				action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-12 row">


							<input type="hidden" id="id" name="id" />

							<div class="form-group in-line">
								<label for="name" class="labelc control-label in-line">地区:</label>

								<div class="col-xs-11 autowidth" id="imgs">
									<div id="distpicker3" data-toggle="distpicker" class=" in-line">
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

								<input value="1" type="hidden" id="imgs_bount"> <label
									for="name" class="labelc control-label">小区名:</label>

								<div class="col-xs-10 select-model" id="imgs">
									<select class="form-control  in-line selectdist  txt"
										id="communityId" name="communityId" data-city="选择小区"></select>
									<p class="help-block"></p>

								</div>

							</div>

							<div class="form-group">
								<label for="name" class="labelc control-label">楼栋名:</label>

								<div class="col-xs-10">
									<input type="text" name="name" class="  txt form-control"
										id="name" placeholder="楼栋名">
									<p class="help-block"></p>
								</div>
							</div>



							<div class="form-group inline">
								<div class="form-group">

									<input value="1" type="hidden" id="imgs_bount"> <label
										for="name" class="labelc control-label">楼层高:</label>

									<div class="col-xs-9 " id="imgs">
										<input type="text" name="floorHeight"
											class="form-control txt-short" id="floorHeight"
											placeholder="楼层高">
										<p class="help-block"></p>

									</div>

								</div>

								<div class="form-group">

									<input value="1" type="hidden" id="imgs_bount"> <label
										for="name" class="labelc control-label">房间/层:</label>

									<div class="col-xs-9 " id="imgs">
										<input type="text" name="roomNumber"
											class="form-control  txt-short" id="roomNumber"
											placeholder="房间数/层">
										<p class="help-block"></p>

									</div>

								</div>
							</div>



							<div class="form-group inline">
								<div class="form-group">
									<label for="name" class="labelc control-label">经度:</label>

									<div class="col-xs-9">

										<input type="text" name="longitude"
											class="form-control txt-short" id="longitude"
											placeholder="小区经度">
										<p class="help-block"></p>

									</div>
								</div>

								<div class="form-group">
									<label for="name" class="labelc control-label">纬度:</label>

									<div class="col-xs-9">

										<input type="text" name="latitude"
											class="form-control txt-short" id="latitude"
											placeholder="小区纬度">
										<p class="help-block"></p>

									</div>
								</div>

							</div>

							<div class="form-group " id="container2_b">
								<iframe class="mapframe" src="/manager/building/map"></iframe>
							</div>


							<div class="modal-footer">

								<button type="button" class="btn btn-alarm" id="openmap_b">在地图上选择经纬度</button>

								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="close">关闭</button>
								<button type="button" class="btn btn-primary" id="btnSubmit_b">
									提交更改</button>
							</div>


						</div>




					</div>
				</div>

			</form>


		</div>
	</div>
</div>





<div class="modal fade" data-backdrop="static" id="myModal_r"
	tabindex="-1" role="dialog" aria-labelledby="myModal_rLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content width800">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModal_rLabel">
					<span id="myModal_r_title">编辑 • 房间</span>
				</h4>

			</div>

			<form id="mform_r" name="mform_r" class="form-horizontal" role="form"
				action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-12 row">


							<input type="hidden" id="id" name="id" />



							<div class="form-group inline">
								<div class="form-group">

									<input value="1" type="hidden" id="imgs_rount"> <label
										for="name" class="labelc control-label">小区名:</label>

									<div class="col-xs-9 select-model" id="imgs">
										<input type="text" name="communityName" readonly="readonly"
											class="  txt-short form-control" id="communityName"
											placeholder="楼栋名">
										<p class="help-block"></p>

									</div>

								</div>

								<div class="form-group">
									<label for="name" class="labelc control-label">楼栋名:</label>

									<div class="col-xs-9">
										<input type="text" name="name" readonly="readonly"
											class="  txt-short form-control" id="name" placeholder="楼栋名">
										<p class="help-block"></p>
									</div>
								</div>
							</div>



							<div class="form-group inline">
								<div class="form-group">

									<input value="1" type="hidden" id="imgs_rount"> <label
										for="name" class="labelc control-label">楼层高:</label>

									<div class="col-xs-9 " id="imgs">
										<input type="text" name="floorHeight" readonly="readonly"
											class="form-control txt-short" id="floorHeight"
											placeholder="楼层高">
										<p class="help-block"></p>

									</div>

								</div>

								<div class="form-group">

									<input value="1" type="hidden" id="imgs_rount"> <label
										for="name" class="labelc control-label">房间/层:</label>

									<div class="col-xs-9 " id="imgs">
										<input type="text" name="roomNumber" readonly="readonly"
											class="form-control  txt-short" id="roomNumber"
											placeholder="房间数/层">
										<p class="help-block"></p>

									</div>

								</div>
							</div>

							<div class="form-group inline">
								<div class="form-group">

									<input value="1" type="hidden" id="imgs_rount"> <label
										for="name" class="inline labelc control-label">添加:</label>

									<div class=" inline pull-left" id="imgs">

										<span>第</span><input type="text" onkeyup="keyupFloor(this,1);"
											onafterpaste="keyupFloor(this,1);" name="floorHeight_index"
											class="inline form-control txt-min" id="floorHeight_index"
											placeholder="1-5"><span>层;</span> <span>一层有</span><input
											type="text" onkeyup="keyupFloor(this,2)"
											onafterpaste="keyupFloor(this,2)" name="roomNumber_index"
											class="inline form-control txt-min" id="roomNumber_index"
											placeholder="1-3"><span>户;</span>

										<p class="help-block"></p>

									</div>

								</div>

								<div class="form-group">

									<button type="button" class="btn btn-alarm"
										id="btnCreateFloorRoom">批量创建&nbsp;"一层"&nbsp;的房间</button>

								</div>
							</div>


							<div class="form-group">


								<div class="col-xs-12 " id="imgs">
									<div class="roomContainer">
										<span class="room_loading">房间信息加载中...</span>
										<div class="roomdata">


											<ul class="roomNo">
												<li><ul class="clear">
														<li class="floorNo"
															style="height: 52px; line-height: 52px;">13层</li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li class="addRoom"></li>
													</ul></li>
												<li><ul class="clear">
														<li class="floorNo"
															style="height: 90px; line-height: 90px;">14层</li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li><input class="room_id" type="hidden"
															name="room_id" value="568"> <num title=""
																class="tooltip-f">1303</num><span></span></li>
														<li class="addRoom"></li>
													</ul></li>
											</ul>






										</div>


									</div>
								</div>

							</div>



							<div class="modal-footer">


								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="close">取消</button>
								<button type="button" class="btn btn-primary" id="btnSubmit_r">
									提交更改</button>
							</div>


						</div>




					</div>
				</div>

			</form>


		</div>
	</div>
</div>






<div class="modal fade" data-backdrop="static" id="myModal_eroom"
	tabindex="-1" role="dialog" aria-labelledby="myModal_eroomLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog model-min model-confirm">
		<div class="modal-content ">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModal_eroomLabel">
					<span id="myModal_eroom_title">添加</span>房间
				</h4>

			</div>

			<form id="mform_eroom" name="mform_eroom" class="form-horizontal" role="form"
				action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-12 row">


							<input type="hidden" id="id" name="id" />
							
							<input type="hidden" id="fllorNo" name="fllorNo" />



							<div class="form-group">

								 <label
									for="name" class="labelc control-label">房间名:</label>

								<div class="col-xs-7select-model" id="imgs">
									<input type="text" name="roomName"
										class="  form-control" id="roomName"
										placeholder="房间名">
									<p class="help-block"></p>

								</div>

							</div>
							
							<div class="modal-footer">


								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="close">取消</button>
								<button type="button" class="btn btn-primary" id="btnSubmit_eroom">
									提交更改</button>
							</div>

						</div>

					</div>

				</div>
			</form>
		</div>
	</div>
</div>


