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
					<span id="myModal_item_title">添加</span>	规则动作
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">rule id</label>

										<div class="col-lg-9">
										<input type="text" name="ruleId" 
											
											class="form-control" id="ruleId"
												placeholder="rule id" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">动作类型：REPUBLISH：转发到另一个topic。
	// OTS：存储到表格存储。
	// MNS：发送消息到消息服务。
	// ONS：发送数据到消息队列。
	// TSDB：存储到高性能时间序列数据库
	// FC：发送数据到函数计算。
	// DATAHUB：发送数据到DataHub中。
	// RDS：存储数据到云数据库中。</label>

										<div class="col-lg-9">
										<input type="text" name="type" 
											
											class="form-control" id="type"
												placeholder="该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
	// OTS：存储到表格存储。
	// MNS：发送消息到消息服务。
	// ONS：发送数据到消息队列。
	// TSDB：存储到高性能时间序列数据库
	// FC：发送数据到函数计算。
	// DATAHUB：发送数据到DataHub中。
	// RDS：存储数据到云数据库中。" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">规则动作配置</label>

										<div class="col-lg-9">
										<input type="text" name="configuration" 
											
											class="form-control" id="configuration"
												placeholder="规则动作配置" >
											<p class="help-block"></p>
										</div>
									</div>
									
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false</label>

										<div class="col-lg-9">
										<input type="text" name="errorActionFlag" 
											
											class="form-control" id="errorActionFlag"
												placeholder="String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false" >
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
