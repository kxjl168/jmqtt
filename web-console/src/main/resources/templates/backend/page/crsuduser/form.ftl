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
					<span id="myModal_item_title">添加</span>	用户表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">手机号</label>

										<div class="col-lg-9">
										<input type="text" name="userPhone" 
											
											class="form-control" id="userPhone"
												placeholder="手机号" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户token值</label>

										<div class="col-lg-9">
										<input type="text" name="userToken" 
											
											class="form-control" id="userToken"
												placeholder="用户token值" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">token生成时间</label>

										<div class="col-lg-9">
										<input type="text" name="createTime" 
											
											class="form-control" id="createTime"
												placeholder="token生成时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">token修改时间</label>

										<div class="col-lg-9">
										<input type="text" name="updateTime" 
											
											class="form-control" id="updateTime"
												placeholder="token修改时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用于计算过期时间的基础天数：(时间戳/1000/60/60/24)</label>

										<div class="col-lg-9">
										<input type="text" name="baseDay" 
											
											class="form-control" id="baseDay"
												placeholder="用于计算过期时间的基础天数：(时间戳/1000/60/60/24)" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">余额</label>

										<div class="col-lg-9">
										<input type="text" name="balance" 
											
											class="form-control" id="balance"
												placeholder="余额" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">押金</label>

										<div class="col-lg-9">
										<input type="text" name="deposit" 
											
											class="form-control" id="deposit"
												placeholder="押金" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">身份证号</label>

										<div class="col-lg-9">
										<input type="text" name="identityNumber" 
											
											class="form-control" id="identityNumber"
												placeholder="身份证号" >
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
