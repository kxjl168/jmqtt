

<div>

	<div class="queryclass">

		<form class="form-inline">
			<div class="form-group ">
				<label class='query_label' for="search_telephone">小区房间:</label>
				<div class="in-line">
					<select
						class="form-control txt-select-building in-line selectdist "
						id="q_room_community" name="q_room_community">

					</select> 
					<select class="form-control txt-select-building in-line  "
						id="q_room_building" name="q_room_building">
					</select>
					<select class="form-control txt-select-building in-line  "
						id="q_room_room" name="q_room_room">

					</select>


				</div>
			</div>

		</form>

		<form class="form-inline margin-top-10">
			<div class="form-group ">
				<label class='query_label' for="search_telephone">是否配锁:</label> 
				<select class="form-control txt-select-building in-line  "
						id="q_is_lock" name="q_is_lock">

					<option value="">全部</option>
					<option value="0">未配门锁</option>
					<option value="1">已配门锁</option>
					</select>

			</div>


			<button type="button"
				class="btn  button-primary button-rounded button-small"
				onclick="doSearch_room()">
				<i class="fa fa-search fa-lg"></i> <span>查询</span>
			</button>
		</form>

	</div>


	<div class="mainbody">
		<div class="row">
			<div class="col-xs-4" style="margin-top: 16px;">房间列表</div>
			<div class="col-xs-1 col-xs-push-7" style="padding-top: 10px;">
				<button type="button" class="hide btn btn-default" id="btnAdd_room">新增</button>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive" style="margin: 0 10px 10px 10px;">
				<table id="table_list_room"
					class="table table-bordered table-hover table-striped"></table>
			</div>
		</div>
	</div>


</div>


<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="static" id="myModal_room"
	tabindex="-1" role="dialog" aria-labelledby="myModal_roomLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content width800">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModal_roomLabel">
					<span id="myModal_room_title">配置</span>门锁
				</h4>

			</div>

			<form id="mform_room" name="mform_room" class="form-horizontal" role="form"
				action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-xs-12 row">


<input type="hidden" id="id" name="id" />

							
								<div class="form-group inline">

									 <label
										for="name" class="labelc control-label">小区房间:</label>

									<div class="form-group width100 col-xs-9 select-model" id="imgs">
										<input type="text" name="communityName"
											class="  txt-r form-control" id="communityName"
											readonly="readonly"
											placeholder="">
											
											<input type="text" name="buildingName" readonly="readonly"
											class=" txt-r form-control" id="communityName"
											placeholder="">
											
											<input type="text" name="name" readonly="readonly"
											class="  txt-r form-control" id="communityName"
											placeholder="">
										
									</div>

								</div>

								
							
							<div class="form-group">
								<label for="name" class="labelc control-label">小区地址:</label>

								<div class="col-xs-10">
									<input type="text" name="address" class="form-control txt" readonly="readonly"
										id="address" placeholder="地址">
									<p class="help-block"></p>
								</div>
							</div>




							<div class="form-group">

								<input value="1" type="hidden" id="imgs_roomount"> <label
									for="name" class="labelc control-label">门锁:</label>

								<div class="col-xs-10 " id="imgs">
										<select class="form-control  in-line selectdist  txt"
										id="lockId" name="lockId" data-city="选择门锁"></select>
									<p class="help-block"></p>

								</div>

							</div>


					

							
							
						

							<div class="modal-footer">

							

								<button type="button" class="btn btn-default"
									data-dismiss="modal" id="close">关闭</button>
								<button type="button" class="btn btn-primary" id="btnSubmit_room">
									提交更改</button>
							</div>


						</div>




				</div>
		</div>

		</form>


	</div>
</div>
</div>
