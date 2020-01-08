<!-- 模态框（Modal） -->
		<div class="modal fade" data-backdrop="static" id="myModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							{{pagetitle}}编辑 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform" name="mform" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">

<input type="hidden" id="id" name="id">


					<#list fields >
					   <#if type==normal>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">[[displayName]]</label>

										<div class="col-lg-9">
											<input type="text" name="[[id]]" class="form-control" id="[[id]]"
												placeholder="[[displayName]]" >
											<p class="help-block"></p>
										</div>
									</div>
						</#if>
						 <#if type==select>
								<div class="form-group">
										<label for="name" class="col-lg-3 control-label">[[displayName]]</label>

										<div class="col-lg-9">
											<select name="type" class="form-control" id="type">



												<option value="1">一级菜单</option>
												<option value="2" selected="selected">二级菜单</option>
												<option value="3">按钮</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>
						</#if>
					</#list>

								

									

									





								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="close">关闭</button>
							<button type="button" class="btn btn-primary" id="btnSubmit">
								提交更改</button>
						</div>
					</form>


				</div>
			</div>
		</div>
