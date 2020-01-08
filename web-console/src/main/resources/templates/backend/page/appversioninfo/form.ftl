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
					<span id="myModal_item_title">添加</span>	app版本
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">apk下载或者ios安装url路径</label>

										<div class="col-lg-9">
										<input type="text" name="fileHttpUrl" 
											
											class="form-control" id="fileHttpUrl"
												placeholder="apk下载或者ios安装url路径" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">版本号</label>

										<div class="col-lg-9">
										<input type="text" name="versionNo" 
											
											class="form-control" id="versionNo"
												placeholder="版本号" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">版本更新信息</label>

										<div class="col-lg-9">
										<input type="text" name="message" 
											
											class="form-control" id="message"
												placeholder="版本更新信息" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否强制更新,1:非强制， 2:强制更新</label>

										<div class="col-lg-9">
										<input type="text" name="forceUpdate" 
											
											class="form-control" id="forceUpdate"
												placeholder="是否强制更新,1:非强制， 2:强制更新" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">Android或者IOS</label>

										<div class="col-lg-9">
										<input type="text" name="appType" 
											
											class="form-control" id="appType"
												placeholder="Android或者IOS" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">更新时间</label>

										<div class="col-lg-9">
										<input type="text" name="mdate" 
											  readonly="readonly"  
											
											class="form-control" id="mdate"
												placeholder="更新时间" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<script>
                            $(function() {
						$("#mdate").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("#mdate").data('datetimepicker')
						 .setDate('2019-11-12 9:59:57');
                            });
                            </script>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">发布人id user表id</label>

										<div class="col-lg-9">
										<input type="text" name="cuser" 
											
											class="form-control" id="cuser"
												placeholder="发布人id user表id" >
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
