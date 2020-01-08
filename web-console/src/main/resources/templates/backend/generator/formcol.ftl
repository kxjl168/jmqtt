<!-- 模态框（Modal） -->
<div class="modal fade modalfield" data-backdrop="static" id="myModal_item"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>


				<h4 class="modal-title" id="myModal_itemLabel">
					<span id="myModal_item_title">生成参数设置</span>
				</h4>

			</div>

		

				<div class="modal-body">
					<div class="row">


						<div class="row">

							<ul class="nav nav-tabs" id="myTab">
								
								<li id="tabnormal" class="active"><a href="#identifier3" data-toggle="tab">基础配置</a></li>
								<li id="tabcol" class=""><a href="#identifier1" data-toggle="tab">显示字段</a></li>
								<li id="tabquery" class=""><a href="#identifier2" data-toggle="tab">查询条件配置</a></li>
								

							</ul>

							<div class="tab-content">
							<div class="row tab-pane fade in  active" id="identifier3">

									<div class="row col-xs-12 ">
										<form id="mform_base" name="mform_base" class="margin-top-10" >
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">基础包名</label>

												<div class="col-lg-9">
													<input type="text" name="basepackageName" class="form-control" value="${basePacakge}"
														id="basepackageName" placeholder="基础包名">
													<p class="help-block"></p>
												</div>
											</div>
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">表名</label>

												<div class="col-lg-9">
													<input type="text" name="tableName" readonly="readonly" class="form-control"
														id="tableName" placeholder="表名">
													<p class="help-block"></p>
												</div>
											</div>
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">PoJo名称</label>

												<div class="col-lg-9">
												<select class="form-control in-line " id="tableNameStartFromSecondUnderLine" name="tableNameStartFromSecondUnderLine">
				
				<option value="false" >全称</option>
				<option value="true" selected="selected">第一个下划线开始</option>
		</select>
													<p class="help-block"></p>
												</div>
											</div>
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">Dao子目录名称</label>

												<div class="col-lg-9">
													<input type="text" value="plus" name="daosubfold" class="form-control width70"
														id="daosubfold" placeholder="plus">
													<p class="help-block"></p>
												</div>
											</div>
											
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">ID类型</label>

												<div class="col-lg-9">
												<select class="form-control in-line " id="idType" name="idType">
				
				<option value="1" selected="selected">Integer</option>
				<option value="2">Varchar</option>
		</select>
													<p class="help-block"></p>
												</div>
											</div>
											
											
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">表字段类型</label>

												<div class="col-lg-9">
												<select class="form-control in-line " id="useActualColumnNames" name="useActualColumnNames">
				
				<option value="false" >下划线分割模式</option>
				<option value="true" selected="selected">驼峰模式</option>
		</select>
													<p class="help-block"></p>
												</div>
											</div>
											
											
											
											
										
											
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">日志功能名称</label>

												<div class="col-lg-9">
													<input type="text" name="logName" class="form-control width70"
														id="logName" placeholder="功能名称">管理
													<p class="help-block"></p>
												</div>
											</div>
											
											<div class="form-group">
												<label for="name" class="col-lg-3 control-label labeltxt">页面功能名称</label>

												<div class="col-lg-9">
													<input type="text" name="pagetitle" class="form-control width70"
														id="pagetitle" placeholder="功能名称">管理
													<p class="help-block"></p>
												</div>
											</div>
										</form>

									</div>
								</div>
							
								<div class="row tab-pane fade in  " id="identifier1">

									<div class="row">
										<div class="col-sm-12">

											<div class="table-responsive" style="margin: 10px;">
												<table id="table_list_col"
													class="table table-bordered table-hover table-striped"></table>
											</div>
										</div>
									</div>
								</div>



								<div class="row tab-pane fade in  " id="identifier2">

									<div class="row">
										<div class="col-sm-12">

											<div class="table-responsive" style="margin: 10px;">
												<table id="table_list_query"
													class="table table-bordered table-hover table-striped"></table>
											</div>
										</div>
									</div>
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
			

		</div>
	</div>
</div>
