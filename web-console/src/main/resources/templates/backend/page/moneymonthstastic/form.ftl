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
					<span id="myModal_item_title">添加</span>	月统计
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">月  2010-10</label>

										<div class="col-lg-9">
										<input type="text" name="dayTime" 
											
											class="form-control" id="dayTime"
												placeholder="月  2010-10" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额类型, 1:押金，2：消费，3:退款，4:充值</label>

										<div class="col-lg-9">
										<input type="text" name="moneyType" 
											
											class="form-control" id="moneyType"
												placeholder="金额类型, 1:押金，2：消费，3:退款，4:充值" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额类型, 1:押金</label>

										<div class="col-lg-9">
										<input type="text" name="num1" 
											
											class="form-control" id="num1"
												placeholder="金额类型, 1:押金" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额类型, 2：消费，</label>

										<div class="col-lg-9">
										<input type="text" name="num2" 
											
											class="form-control" id="num2"
												placeholder="金额类型, 2：消费，" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额类型, 3:退款，</label>

										<div class="col-lg-9">
										<input type="text" name="num3" 
											
											class="form-control" id="num3"
												placeholder="金额类型, 3:退款，" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">金额类型, 4:充值</label>

										<div class="col-lg-9">
										<input type="text" name="num4" 
											
											class="form-control" id="num4"
												placeholder="金额类型, 4:充值" >
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
