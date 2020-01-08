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
					<span id="myModal_item_title">添加</span>	${pagetitle}
				</h4>

			</div>

			<form id="mform_item" name="mform_item" class="form-horizontal"
				role="form" action="" method="post">

				<div class="modal-body">
					<div class="row">

						<div class="col-lg-12">

							<input type="hidden" id="id" name="id">






	<#list colFields as field>
					   <#if field.type=='input'>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">${field.displayName}</label>

										<div class="col-lg-9">
										<input type="text" name="${field.id}" 
											  <#if field.colType=='timestamp'>
											  readonly="readonly"  
											  </#if>
											
											class="form-control" id="${field.id}"
												placeholder="${field.displayName}" >
											<p class="help-block"></p>
										</div>
									</div>
									
									  <#if field.colType=='timestamp'>
									<script>
                            $(function() {
						$("${r'#'}${field.id}").datetimepicker({
							 format: 'yyyy-mm-dd hh:ii:ss',
							 language: 'zh-CN',
							 autoclose:true,
						        startDate:new Date()
						});
						 $("${r'#'}${field.id}").data('datetimepicker')
						 .setDate('${.now}');
                            });
                            </script>
                            </#if>
						</#if>
						 <#if field.type=='select'>
								<div class="form-group">
										<label for="name" class="col-lg-3 control-label">${field.displayName}</label>

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
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="close">关闭</button>
					<button type="button" class="btn btn-primary" id="btnSubmit_item">
						提交更改</button>
				</div>
			</form>


		</div>
	</div>
</div>
