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
					<span id="myModal_item_title">添加</span>	租户信息表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">住户名称</label>

										<div class="col-lg-9">
										<input type="text" name="name" 
											
											class="form-control" id="name"
												placeholder="住户名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">联系号码</label>

										<div class="col-lg-9">
										<input type="text" name="telephone" 
											
											class="form-control" id="telephone"
												placeholder="联系号码" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">性别</label>

										<div class="col-lg-9">
										<input type="text" name="gender" 
											
											class="form-control" id="gender"
												placeholder="性别" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">名族</label>

										<div class="col-lg-9">
										<input type="text" name="nationality" 
											
											class="form-control" id="nationality"
												placeholder="名族" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">出生年月</label>

										<div class="col-lg-9">
										<input type="text" name="birth" 
											  readonly="readonly"  
											
											class="form-control" id="birth"
												placeholder="出生年月" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#birth").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#birth").data('datetimepicker')
						 .setDate('2019-1-10 13:15:39');
                            });
                            </script>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">证件号</label>

										<div class="col-lg-9">
										<input type="text" name="idCrad" 
											
											class="form-control" id="idCrad"
												placeholder="证件号" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">户籍地址</label>

										<div class="col-lg-9">
										<input type="text" name="permanentAddress" 
											
											class="form-control" id="permanentAddress"
												placeholder="户籍地址" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">证件办理地址</label>

										<div class="col-lg-9">
										<input type="text" name="idCradAddress" 
											
											class="form-control" id="idCradAddress"
												placeholder="证件办理地址" >
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
