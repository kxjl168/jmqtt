<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>住宅管理</title>

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
					<embed src="${ctx}/img/zhuye.svg" type="image/svg+xml"></embed>
				</div>
				<h1 class="page-header">
					首页&nbsp;><span>&nbsp;住宅管理</span>
				</h1>
			</div>
		</div>


		<div class="row">

			<div class="modal-body nopadding margin-top-5">
				<div class="row">

					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a href="#identifier1" data-toggle="tab">小区管理</a></li>
						<li class=""><a href="#identifier2" data-toggle="tab">楼栋管理</a></li>
						<li class=""><a href="#identifier3" data-toggle="tab">房间管理</a></li>

					</ul>

					<div class="tab-content">
						<div class="row tab-pane fade in  active" id="identifier1">

							<div class="  margin-top-5"><#include "community.ftl"/></div>
						</div>



						<div class="row tab-pane fade in  " id="identifier2">

							<div class="  margin-top-5"><#include "building.ftl"/></div>
						</div>
						<div class="row tab-pane fade in  " id="identifier3">

							<div class="  margin-top-5"><#include "room.ftl"/></div>
						</div>

					</div>
				</div>
			</div>

		</div>



	</div>


	<script src="${ctx}/vendor/page/community/js/area.js"></script>
	<script src="${ctx}/vendor/page/community/js/community.js"></script>
	
	<script src="${ctx}/vendor/page/community/js/building.js"></script>
	<script src="${ctx}/vendor/page/community/js/buildingRoomAction.js"></script>
	<script src="${ctx}/vendor/page/community/js/buildingSelect2.js"></script>

	<script src="${ctx}/vendor/page/community/js/room.js"></script>
	<script src="${ctx}/vendor/page/community/js/roomSelect2.js"></script>
</body>
</html>