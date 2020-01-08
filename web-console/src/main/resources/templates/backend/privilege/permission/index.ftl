<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>权限菜单管理</title>


<link rel="stylesheet" type="text/css" href="${ctx}/css/iot.css">

<link rel="stylesheet" href="${ctx}/js/ztree/zTreeStyle.css">
</head>

<body>





	<div>

		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="${ctx}/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					系统权限管理&nbsp;><span>&nbsp;系统权限列表</span>
				</h1>
			</div>
		</div>
<div class="modal-body">
		<div class="row">
			<div class="queryclass">

				<form class="form-inline">
					<div class="form-group">
						<label for="name" class="lb_text col-lg-5 control-label">权限菜单名称:</label>

						<div class="col-lg-7">
							<input id="q_name" type="text" name="q_name"
								class="form-control " placeholder=""
								aria-controls="dataTables-example">
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="lb_text col-lg-5 control-label">父菜单:</label>

						<div class="col-lg-7">
							<input id="q_pid" type="text" name="q_pid" readonly="readonly"
								class="form-control " placeholder=""
								aria-controls="dataTables-example">


							<div id="menuContent" class="help-block menuContent"
								style="display: none; position: absolute;">
								<span id="cleanselect" class="cleanbtn">清除选择</span>
								<ul id="treeDemo" class="ztree"
									style="margin-top: 0; width: 160px;"></ul>
							</div>


						</div>
					</div>

				</form>

				<form class=" form-inline margin-top-10">
					<div class="form-group hide">
						<label class='query_label' for="search_telephone">小区名称:</label> <input
							id="q_c_name" type="text" name="q_name"
							class="form-control qinput" placeholder=""
							aria-controls="dataTables-example">
					</div>


					<button type="button" id="btnQry" onclick="doquery()"
						class="btn  button-primary button-rounded button-small"
						onclick="doSearch_c()">
						<i class="fa fa-search fa-lg"></i> <span>查询</span>
					</button>

				</form>

			</div>
		</div>


		<div class="mainbody">
			<div class="row">
				<div class="col-xs-4" style="margin-top: 16px;">小区列表</div>
				<div class="col-xs-1 col-xs-push-7" style="padding-top: 10px;">


					<button type="button" class="btn btn-default" id="btnAdd">新增</button>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">

					<div class="table-responsive" style="margin: 10px;">
						<table id="table_list"
							class="table table-bordered table-hover table-striped"></table>
					</div>
				</div>
			</div>

		</div>
</div>


		<div class="hide row">




			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="header">系统权限列表</span>
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
							权限菜单编辑 <span id="message" style="margin-left: 20px;"></span>
						</h4>

					</div>

					<form id="mform" name="mform" class="form-horizontal" role="form"
						action="" method="post">

						<div class="modal-body">
							<div class="row">

								<div class="col-lg-12">


									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单ID</label>

										<div class="col-lg-9">
											<input type="text" name="id" class="form-control" id="id"
												placeholder="权限菜单ID" readonly="readonly">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单名称</label>

										<div class="col-lg-9">
											<input type="text" name="name" class="form-control" id="name"
												placeholder="权限菜单名称">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限菜单类型</label>

										<div class="col-lg-9">
											<select name="type" class="form-control" id="type">

												<option value="1">一级菜单</option>
												<option value="2" selected="selected">二级菜单</option>
												<option value="3">按钮</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">URL</label>

										<div class="col-lg-9">
											<input type="text" name="url" class="form-control" id="url"
												placeholder="URL">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">权限代码</label>

										<div class="col-lg-9">
											<input type="text" name="percode" class="form-control"
												id="percode" placeholder="权限代码">
											<p class="help-block"></p>
											<p class="tip-block small text-info">示例:action:view</p>
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">父菜单ID</label>

										<div class="col-lg-9">
											<!-- 	<input type="text" name="parentid" class="form-control"
												id="parentid" placeholder="父菜单ID"> -->
											<select name="parentid" class="form-control" id="parentid">
											</select>
											<p class="help-block"></p>

										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">排序号</label>

										<div class="col-lg-9">
											<input type="text" name="sortstring" class="form-control"
												id="sortstring" placeholder="排序号">
											<p class="help-block"></p>
										</div>
									</div>
									<div class="form-group">
										<label for="name" class="col-lg-3 control-label">是否可见</label>

										<div class="col-lg-9">

											<select name="available" class="form-control" id="available">

												<option value="1" selected="selected">可见</option>
												<option value="0">不可见</option>
											</select>
											<p class="help-block"></p>
										</div>
									</div>







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



		<script src="${ctx}/js/ztree/jquery.ztree.all.min.js"></script>
		<script src="${ctx}/vendor/privilege/js/permission.js"></script>
</body>
</html>