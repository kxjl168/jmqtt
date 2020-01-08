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
					<span id="myModal_item_title">添加</span>	收货地址
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">用户id</label>

										<div class="col-lg-9">
										<input type="text" name="userId" 
											
											class="form-control" id="userId"
												placeholder="用户id" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">收货人</label>

										<div class="col-lg-9">
										<input type="text" name="receiver" 
											
											class="form-control" id="receiver"
												placeholder="收货人" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">手机</label>

										<div class="col-lg-9">
										<input type="text" name="phone" 
											
											class="form-control" id="phone"
												placeholder="手机" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">详细地址</label>

										<div class="col-lg-9">
										<input type="text" name="address" 
											
											class="form-control" id="address"
												placeholder="详细地址" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否默认地址(0:否  1:是)</label>

										<div class="col-lg-9">
										<input type="text" name="isDefault" 
											
											class="form-control" id="isDefault"
												placeholder="是否默认地址(0:否  1:是)" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">1正常 0删除  逻辑删除标识</label>

										<div class="col-lg-9">
										<input type="text" name="state" 
											
											class="form-control" id="state"
												placeholder="1正常 0删除  逻辑删除标识" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">省市区县</label>

										<div class="col-lg-9">
										<input type="text" name="provinceCity" 
											
											class="form-control" id="provinceCity"
												placeholder="省市区县" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">测试时间</label>

										<div class="col-lg-9">
										<input type="text" name="testDate" 
											  readonly="readonly"  
											
											class="form-control" id="testDate"
												placeholder="测试时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#testDate").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#testDate").data('datetimepicker')
						 .setDate('2019-1-21 14:21:59');
                            });
                            </script>










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
