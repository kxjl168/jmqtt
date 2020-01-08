<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>日志管理</title>

<link rel="stylesheet" type="text/css" href="${ctx}/css/iot.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/vendor/page/user/css/user2.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/vendor/page/community/css/common.css">


</head>

<body>


	<div>

		<input id="val2" value="<#if httppath??>${httppath}</#if>" name=""
			val2"" type="hidden" />


		<div class="row">

			<div class="col-lg-12 wzbj">
				<div style="padding-top: 9px; float: left; padding-right: 4px;">
					<embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					首页&nbsp;><span>&nbsp;日志管理</span>
				</h1>
			</div>
		</div>


		<div class="row">

			<div class="modal-body nopadding margin-top-5">
				<div class="row">

					<#include "sysOperLog.ftl"/>
				</div>
			</div>

		</div>



	</div>


	<script src="${ctx}/vendor/page/sysOperLog/js/sysOperLog.js"></script>
	
</body>
</html>