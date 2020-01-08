    <script src="/vendor/jquery/jquery.js"></script>


<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendor/jquery/jquery.dcjqaccordion.2.7.js"></script>
<script src="/vendor/jquery/jquery.scrollTo.min.js"></script>
<script src="/vendor/jquery/jquery.nicescroll.js"></script>
<script src="/vendor/jquery/jquery.sparkline.js"></script>
<script src="/vendor/blueSkin/common-scripts.js"></script>
<script src="/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>
<script src="/vendor/bootstrap-table/js/bootstrap-table.js"></script>
<script src="/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
<script src="/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/vendor/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/vendor/bootstrap-treeview/js/bootstrap-treeview.min.js"></script>
<script src="/vendor/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="/vendor/bootstrap-select/locales/defaults-zh_CN.min.js"></script>

<script src="/vendor/distpicker/js/distpicker.data.js"></script>
<script src="/vendor/distpicker/js/distpicker.js"></script>

<script src="/vendor/sweetalert/js/sweetalert2.min.js"></script>

    <script src="/js/iot.js"></script>


<link rel="stylesheet"
	href="/vendor/sweetalert/css/sweetalert2.min.css" />



	<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<link rel="stylesheet"
	href="https://cache.amap.com/lbs/static/AMap.TransferRender1120.css" />
	<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<link rel="stylesheet"
	href="https://cache.amap.com/lbs/static/AMap.TransferRender1120.css" />
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.4.4&key=bd7c5e997b2ed26801eb5f9018c36962&plugin=AMap.ToolBar,AMap.Transfer,AMap.Geocoder,AMap.MouseTool,AMap.CitySearch"ֵ></script>
<%--
           <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.4&key=cd84e0b5879385c6443f2ae323714935ֵ"></script>
           <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.4&key=bd7c5e997b2ed26801eb5f9018c36962"></script>
    --%>
<script type="text/javascript"
	src="https://cache.amap.com/lbs/static/TransferRender1230.js"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<div class="col-lg-12">
		<div id="container" class="kmap"></div>
</div>


<script>
var map = new AMap.Map("container", {
	resizeEnable : true,
	zoom : 13
});


var clickEventListener = map.on('click', function(e) {
	var x= e.lnglat.getLng();
	var y= e.lnglat.getLat();
parent.setxy_c(x,y);
	
});
</script>