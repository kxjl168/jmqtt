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
					<span id="myModal_item_title">添加</span>	测试表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">名称</label>

										<div class="col-lg-9">
										<input type="text" name="name" 
											
											class="form-control" id="name"
												placeholder="名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">平台根url</label>

										<div class="col-lg-9">
										<input type="text" name="rootPath" 
											
											class="form-control" id="rootPath"
												placeholder="平台根url" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">创建时间</label>

										<div class="col-lg-9">
										<input type="text" name="createTime" 
											  readonly="readonly"  
											
											class="form-control" id="createTime"
												placeholder="创建时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#createTime").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#createTime").data('datetimepicker')
						 .setDate('2019-2-27 11:59:56');
                            });
                            </script>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">图标 class</label>

										<div class="col-lg-9">
										<input type="text" name="icons" 
											
											class="form-control" id="icons"
												placeholder="图标 class" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">上下线 1：上线，0 :离线</label>

										<div class="col-lg-9">
										<input type="text" name="enable" 
											
											class="form-control" id="enable"
												placeholder="上下线 1：上线，0 :离线" >
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
