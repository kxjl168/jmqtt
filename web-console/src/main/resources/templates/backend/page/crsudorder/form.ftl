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
					<span id="myModal_item_title">添加</span>	订单表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">订单号</label>

										<div class="col-lg-9">
										<input type="text" name="orderNo" 
											
											class="form-control" id="orderNo"
												placeholder="订单号" >
											<p class="help-block"></p>
										</div>
									</div>
									
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
										<label for="name" class="col-lg-3 control-label">订单生成时间</label>

										<div class="col-lg-9">
										<input type="text" name="createTime" 
											
											class="form-control" id="createTime"
												placeholder="订单生成时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">订单修改时间</label>

										<div class="col-lg-9">
										<input type="text" name="updateTime" 
											
											class="form-control" id="updateTime"
												placeholder="订单修改时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">付款时间</label>

										<div class="col-lg-9">
										<input type="text" name="payTime" 
											
											class="form-control" id="payTime"
												placeholder="付款时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">订单金额</label>

										<div class="col-lg-9">
										<input type="text" name="payAmount" 
											
											class="form-control" id="payAmount"
												placeholder="订单金额" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">1:充值  2:消费 3:退款</label>

										<div class="col-lg-9">
										<input type="text" name="payType" 
											
											class="form-control" id="payType"
												placeholder="1:充值  2:消费 3:退款" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">订单标题</label>

										<div class="col-lg-9">
										<input type="text" name="paySubject" 
											
											class="form-control" id="paySubject"
												placeholder="订单标题" >
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
