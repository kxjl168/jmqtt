<!-- 模态框（Modal） -->
<div class="modal fade" data-backdrop="static" id="myModal_item"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				
				
				<h4 class="modal-title" id="myModal_itemLabel">
					<span id="myModal_item_title">添加</span>	mqtt规则表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">规则名称</label>

										<div class="col-lg-9">
										<input type="text" name="name" 
											
											class="form-control" id="name"
												placeholder="规则名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">prokey</label>

										<div class="col-lg-9">
										<input type="text" name="productKey" 
											
											class="form-control" id="productKey"
												placeholder="prokey" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">规则描述</label>

										<div class="col-lg-9">
										<input type="text" name="ruleDesc" 
											
											class="form-control" id="ruleDesc"
												placeholder="规则描述" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">select</label>

										<div class="col-lg-9">
										<input type="text" name="selectdata" 
											
											class="form-control" id="selectdata"
												placeholder="select" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">topic</label>

										<div class="col-lg-9">
										<input type="text" name="topic" 
											
											class="form-control" id="topic"
												placeholder="topic" >
											<p class="help-block"></p>
										</div>
									</div>
									
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">where</label>

										<div class="col-lg-9">
										<input type="text" name="wheredata" 
											
											class="form-control" id="wheredata"
												placeholder="where" >
											<p class="help-block"></p>
										</div>
									</div>
									










						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="close">关闭</button>
					<button type="button" class="btn btn-primary" id="btnSubmit_item">
						提交更改</button>
				</div>
			</form>


		</div>
	</div>
</div>
