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
					<span id="myModal_item_title">添加</span>	mqtt节点信息表
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">平台名称</label>

										<div class="col-lg-9">
										<input type="text" name="name" 
											
											class="form-control" id="name"
												placeholder="平台名称" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">ip:port</label>

										<div class="col-lg-9">
										<input type="text" name="ipPort" 
											
											class="form-control" id="ipPort"
												placeholder="ip:port" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">上下线 1：上线，0 :离线</label>

										<div class="col-lg-9">
										<input type="text" name="status" 
											
											class="form-control" id="status"
												placeholder="上下线 1：上线，0 :离线" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">状态最后检查时间</label>

										<div class="col-lg-9">
										<input type="text" name="refreshTime" 
											  readonly="readonly"  
											
											class="form-control" id="refreshTime"
												placeholder="状态最后检查时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#refreshTime").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#refreshTime").data('datetimepicker')
						 .setDate('2020-1-6 13:51:43');
                            });
                            </script>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">在线用户数</label>

										<div class="col-lg-9">
										<input type="text" name="onlineUsers" 
											
											class="form-control" id="onlineUsers"
												placeholder="在线用户数" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">配置信息</label>

										<div class="col-lg-9">
										<input type="text" name="configinfo" 
											
											class="form-control" id="configinfo"
												placeholder="配置信息" >
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
