<!-- 模态框（Modal） -->
		<div class="modal fade" data-backdrop="static" id="myModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							权限菜单编辑 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform" name="mform" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单ID</label>

										<div class="col-lg-9">
											<input type="text" name="id" class="form-control" id="id"
												placeholder="权限菜单ID" readonly="readonly">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单名称</label>

										<div class="col-lg-9">
											<input type="text" name="name" class="form-control" id="name"
												placeholder="权限菜单名称">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单类型</label>

										<div class="col-lg-9">
											<select name="type" class="form-control" id="type">

												<option value="1">一级菜单</option>
												<option value="2" selected="selected">二级菜单</option>
												<option value="3">按钮</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">URL</label>

										<div class="col-lg-9">
											<input type="text" name="url" class="form-control" id="url"
												placeholder="URL">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限代码</label>

										<div class="col-lg-9">
											<input type="text" name="percode" class="form-control"
												id="percode" placeholder="权限代码">
											<p class="help-block"></p>
											<p class="tip-block small text-info">示例:action:view</p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">父菜单ID</label>

										<div class="col-lg-9">
											<!-- 	<input type="text" name="parentid" class="form-control"
												id="parentid" placeholder="父菜单ID"> -->
											<select name="parentid" class="form-control" id="parentid">
											</select>
											<p class="help-block"></p>

										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">排序号</label>

										<div class="col-lg-9">
											<input type="text" name="sortstring" class="form-control"
												id="sortstring" placeholder="排序号">
											<p class="help-block"></p>
										</div>
									</div>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否可见</label>

										<div class="col-lg-9">

											<select name="available" class="form-control" id="available">

												<option value="1" selected="selected">可见</option>
												<option value="0">不可见</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>







								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="close">关闭</button>
							<button type="button" class="btn btn-primary" id="btnSubmit">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>
