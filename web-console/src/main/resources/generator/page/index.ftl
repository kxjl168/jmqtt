<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>${pagetitle}管理</title>


<link rel="stylesheet" type="text/css" href="${r'$'}{ctx}/css/iot.css">

<link rel="stylesheet" href="${r'$'}{ctx}/js/ztree/zTreeStyle.css">
</head>

<body>





	<div>

		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="${r'$'}{ctx}/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					首页&nbsp;><span>&nbsp;${pagetitle}列表</span>
				</h1>
			</div>
		</div>


	<div class="modal-body">
		<div class="row">
		
	
		
		<#if queryFields?size lte 2>
		<div class="queryclass">
		
		<#elseif queryFields?size lte 4>
		<div class="queryclass3">
		<#else>
		<div class="queryclass4">
		</#if>
			



				<#if queryFields?size lte 2>
				<form class="form-inline">
					<#list queryFields as field>

					<div class="form-group">
						<label for="name" class="lb_text col-xs-5 control-label">${field.displayName}:</label>

						<div class="col-xs-7">
							<input id="q_${field.id}" type="text" name="q_${field.id}"
								class="form-control inputtxt" placeholder=""
								aria-controls="dataTables-example">
						</div>
					</div>

					</#list>
				</form>
				<#else>

				<form class="form-inline">
					<#list 0..1 as it> <#assign field=queryFields[it]>

					<div class="form-group">
						<label for="name" class="lb_text col-xs-5 control-label">${field.displayName}:</label>

						<div class="col-xs-7">
							<input id="q_${field.id}" type="text" name="q_${field.id}"
								class="form-control inputtxt" placeholder=""
								aria-controls="dataTables-example">
						</div>
					</div>

					</#list>
				</form>

				<form class="form-inline margin-top-10">
					<#list 2..queryFields?size-1 as it> <#assign field=queryFields[it]>

					<div class="form-group">
						<label for="name" class="lb_text col-xs-5 control-label">${field.displayName}:</label>

						<div class="col-xs-7">
							<input id="q_${field.id}" type="text" name="q_${field.id}"
								class="form-control inputtxt" placeholder=""
								aria-controls="dataTables-example">
						</div>
					</div>

					</#list>
				</form>


				</#if>


				<form class=" form-inline margin-top-10">
					


					<button type="button" id="btnQry" onclick="doSearch_item()"
						class="btn  button-primary button-rounded button-small">
						<i class="fa fa-search fa-lg"></i> <span>查询</span>
					</button>

				</form>

			</div>
		</div>


		<div class="mainbody">
			<div class="row">
				<div class="col-xs-5" style="margin-top: 16px;">${pagetitle}列表</div>
				<div class="col-xs-1 col-xs-push-6" style="padding-top: 10px;">


					<button type="button" class="btn btn-default" id="btnAdd_item">新增</button>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">

					<div class="table-responsive" style="margin: 10px;">
						<table id="table_list_item"
							class="table table-bordered table-hover table-striped"></table>
					</div>
				</div>
			</div>

		</div>



		<div class="hide row">




			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">${pagetitle}列表</span>
					</div>
					<div class="panel-body">

						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row ">
								<div class=" col-sm-9"></div>

								<div class="col-sm-3 "></div>
							</div>


						</div>
					</div>


				</div>
			</div>
		</div>
		
		</div>

		<!-- 模态框（Modal） -->
		<${r'#'}include "form.ftl">


		<script
			src="${r'$'}{ctx}/vendor/pageAuto/${ctrollerModelMapping}/js/${ctrollerModelMapping}.js"></script>
</body>
</html>