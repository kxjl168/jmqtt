<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>自动生成管理</title>

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
                实体类自动生成管理&nbsp;><span>&nbsp;表列表</span>
            </h1>
        </div>
    </div>


    <div class="row modal-body">


<div class="row">
		
	
		
		<div class="queryclass">
		
			



				<form class="form-inline">

					<div class="form-group">
						<label for="name" class="lb_text col-xs-5 control-label">表名称:</label>

						<div class="col-xs-7">
							<input id="q_name" type="text" name="q_name"
								class="form-control inputtxt" placeholder=""
								aria-controls="dataTables-example">
						</div>
					</div>



				</form>


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
				<div class="col-xs-5" style="margin-top: 16px;">数据库表</div>
				<div class="col-xs-1 col-xs-push-6" style="padding-top: 10px;">


					<button type="button" class="hide btn btn-default" id="btnAdd_item">新增</button>
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
    
    	<#include "formcol.ftl">

    <script src="${ctx}/js/ztree/jquery.ztree.all.min.js"></script>
    <script src="${ctx}/vendor/Generator/generator.js"></script>
    <script src="${ctx}/vendor/Generator/config.js"></script>

</body>
</html>