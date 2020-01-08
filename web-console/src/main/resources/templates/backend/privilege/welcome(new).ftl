<!DOCTYPE html>
<!-- saved from url=(0078)https://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/tables.html -->
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <link href="${ctx}/vendor/bootstrap/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="${ctx}/vendor/picture/css/iconfont.css" rel="stylesheet"/>
    <style>


        a {
            text-decoration: none;
            underline: none;
        }

        a:hover {
            text-decoration: none;
            cursor: pointer;
        }

        body {
            font-family: "微软雅黑";
        }

        #wrapper {
            background: #323232;
        }

        /* 头部样式*/
        .navbar-static-top {
            background: #1cb09a;
        }

        .dropdown a {
            color: #FFFFFF;
        }

        .nav > li > a:hover, .nav > li > a:focus {
            background-color: #10a18b;
        }

        .nav .open > a, .nav .open > a:focus, .nav .open > a:hover {
            background-color: #10a18b;
        }

        .navbar-default {
            border-color: #008c77;
        }

        /* 左边样式*/
        .navbar-brand {
            padding: 2px 0px 0px 10px;
        }

        .navbar-collapse {
            background: #323232;
            height: 1080px;
        }

        .navbar-collapse ul {
            margin-left: 0;
        }

        .sidebar {
            margin-top: 56px;
        }

        .sidebar ul li {
            border-bottom: 1px solid #323232;
        }

        #side-menu li a {
            color: #d2d2d2;
        }

        #side-menu li:hover a {
            color: #FFFFFF;
        }

        #side-menu li a {
            color: #FFFFFF;
        }

        .sidebar ul li a.active {
            background-color: #1cb09a;
        }

        .collapse {
            background: #242424;
        }

        .navbar-collapse {
            height: 100%;
        }

        /* 中间上部分位置样式*/
        .row {
            margin: 0px;
            max-width: 100%;
        }

        .panel {
            padding: 0;
        }

        #page-wrapper {
            background-color: #e9e9e9;
            padding: 0px;
        }

        h1 {
            font-size: 14px;
            color: #888888;
            display: inline-block;
            text-align: center;
        }

        .page-header {
            margin: 14px 0;
            border-bottom: 0;
            padding-bottom: 0;
            color: #333333;

        }

        .panel-default > .panel-heading {
            background-color: #d8d8d8;
        }

        .page-header span {
            color: #1cb09a;
        }

        .wzbj {
            width: 100%;
            height: 42px;
            background: #e4e3e3;
            margin-bottom: 12px;
            border-bottom: 1px solid #dddddd;
        }

        embed {
            width: 22px;
            height: 22px;
            vertical-align: middle;
        }

        .thumbnail {
            height: 150px;
            padding: 0;
            margin-bottom: 0;
        }

        .text-success {
            color: #333333;
            float: left;
            /*margin-top: 95px;*/
            margin-left: 20px;
            line-height: 40px;
        }

        .ys {
            font-size: 38px;
            border-radius: 50%;
            line-height: 40px;
            border: 1px solid #DDDDDD;
            padding: 4px;
            background: #FFFFFF;
            color: #666666;
        }

        .ys2 {
            font-size: 18px;
            border-radius: 8px;
            line-height: 40px;
        }

        .ys3 {
            font-size: 18px;
            border-radius: 8px;
            line-height: 40px;
        }

        .tubiao {
            margin-left: 15px;
            margin-right: 15px;
            display: block;
            float: left;
        }

        .opencolor {
            color: #fb6e56;
            border: 1px solid #fb6e56;
        }

        .lista .caption {
            padding: 0;
        }

        .lista {
            height: 80px;
        }

        .lista > .switch {
            margin-right: 15px;
        }

        .row .row {
            margin: 0;
        }

        .icon {
            font-size: 30px;
            line-height: 40px;
            float: right;
            padding-left: 8px;
            color: #999999;
        }

        .opentext {
            margin-right: 8px;
            font-size: 14px;
            color: #666666;
        }

        .panel {
            border: 0;
        }

        .panel-height {
            height: 120px;
            border-radius: 3px;
            color: #FFFFFF;
        }

        .panel-primary1 {
            background: #37cac0;
        }

        .panel-green1 {
            background: #00a0e9;
        }

        .panel-yellow1 {
            background: #f3b100;
        }

        .panel-red1 {
            background: #fb6e56;
        }

        .panel-primary > .panel-heading {
            height: 120px;
        }

        .panel-padding {
            padding: 15px 15px;
        }

        .img-middle {
            line-height: 84px;
            width: 25%;
            float: left;
        }

        /* 表格样式*/
        .table > tbody > tr > td {
            height: 40px;
        }

        .table > tbody > tr > td {
            padding: 0px 8px;
            vertical-align: middle;
        }

        .table-striped > tbody > tr:nth-of-type(odd) {
            background-color: #FFFFFF;
        }

        .table-striped > tbody > tr:nth-of-type(even) {
            background-color: #f9f9f9;
        }

        .table-hover > tbody > tr:hover {
            background-color: #dbf8f4;
        }

        .table > thead > tr > th {
            vertical-align: middle;
        }

        /* banner样式*/
        .panel-h {
            height: 350px;
        }

        tbody > tr {
            cursor: pointer;
        }

        .odd > td:first-child, .odd > td:last-child {
            text-align: center;
        }

        .more {
            float: right;
            font-size: 12px;
            line-height: 24px;
            color: #666666;
        }

        .more:hover {
            color: #1cb09a;
        }

        .lianjie {
            cursor: pointer;
        }

        /* 表格样式*/
        .table > tbody > tr > td {
            height: 40px;
        }

        .table > tbody > tr > td {
            padding: 0px 8px;
            vertical-align: middle;
        }

        .table-striped > tbody > tr:nth-of-type(odd) {
            background-color: #FFFFFF;
        }

        .table-striped > tbody > tr:nth-of-type(even) {
            background-color: #f9f9f9;
        }

        .table-hover > tbody > tr:hover {
            background-color: #dbf8f4;
        }

        .table > thead > tr > th {
            vertical-align: middle;
        }

        /* 最顶部右侧退出板块*/
        .tx {
            width: 70px;
        }

        .bannerheight {
            height: 56px;
        }

        .navbar-right {
            line-height: 56px;
        }

        .yjxx {
            width: 20px;
            height: 16px;
            border-radius: 3px;
            background: #fb6e56;
            position: absolute;
            left: 15px;
            top: 10px;
            text-align: center;
            line-height: 14px;
        }

        .yjxx span {
            color: #FFFFFF;
            font-size: 12px;
        }

        .dropdown-menu > li > a:hover {
            background-color: #dbf8f4;
        }

        .dropdown-toggle img {
            margin-right: 10px;
            height: 40px;
            margin-top: -3px;
            padding-left: 4px;
        }

        .yjbk {
            width: 40px;
            padding-left: 20px;
            margin-right: 15px;

        }

        .dropdown-toggle a:hover {
            width: 50px;
        }

        .navbar-top-links li a {
            padding: 0px;
        }

        .namexg > .form-control:focus {
            box-shadow: none;
            border: 1px solid #13d1be;
            background: #ffffff;

        }

        .namexg > .form-control {
            width: 80px;
            height: 32px;
            border-radius: 4px;
            border: 1px solid #CCCCCC;
        }

        .namexg > .cj {
            width: 300px;
        }

        .namexg {
            margin-top: 15px;

        }

        .gjc {

            height: 50px;

        }

        .nameadd {
            margin-top: -6px;
        }

        .name01 {
            float: left;
            line-height: 36px;
        }

        .bootstrap-select > .btn-default {
            color: #666666;
            background-color: #ffffff;
            font-size: 14px;
            border-color: #cccccc;
        }

        .bootstrap-select.form-control:not([class*="col-"]) {
            width: 340px;
            border: 0;
        }

        .bootstrap-select > .btn {
            height: 38px;

        }

        .dropdown-menu > .active > a {
            background-color: #1cb09a;
        }

        .dropdown-menu > li > a:hover {
            background-color: #dbf8f4;
        }

        .dropdown-menu > li.selected > a:hover {
            background-color: #13d1be;
        }

        .form-control:focus {
            background: #fafafa;
            border-color: #13d1be;
        }

        .form-control:focus {
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0), 0 0 8px rgba(102, 175, 233, 0);
        }

        .bootstrap-select.form-control:not([class*="col-"]) {
            width: 80px;
            border: 0;
        }

        .bootstrap-select.btn-group .dropdown-menu {
            width: 80px;
        }

        .glyphicon-ok:before {
            content: "\e013";
            margin-right: -9px;
        }

        .bootstrap-select > .btn {
            height: 32px;
        }

        .dropdown-menu > li > a {
            display: block;
            padding: 3px 12px;
        }

        .open > .dropdown-toggle.btn-default {
            background: #FFFFFF;
        }

        .open > .dropdown-toggle.btn-default:focus {
            background: #FFFFFF;
            border-color: #13d1be;
        }

        .bs-searchbox {
            display: none;
        }

        .tbl {
            table-layout: fixed
        }

        .td {
            overflow: hidden;
            text-overflow: ellipsis
        }

        /*弹出框*/
        .modal-header{
            padding: 8px;
            background: #1cb09a;
            border-top-left-radius:4px;
            border-top-right-radius:4px;
        }
        .modal-content{
            border: 1px solid rgba(0,0,0,.05);
        }
        .modal-title{
            color: #ffffff;
            font-size:18px;
            padding:4px;
        }
        .modal-header .close{
            margin: 4px 0px 0px 0px;
        }
        .modal-dialog{
            width: 540px;
            margin: 200px auto;
        }
        label{
            cursor: default;
        }
        .btnclose{
            float: right;
            line-height: 1;
            filter: alpha(opacity=50);
            opacity: .5;
            margin-top: 5px;
        }
        .btnclose i{
            color: #FFFFFF;
            font-size: 18px;
        }
        .btnclose:hover{
            opacity: 1.0;
        }
        .name02{
            float: left;
            line-height: 33px;
            margin-right: 15px;
        }
        .xxnr{
            float: left;
            position: absolute;
            padding-top: 5px;
            padding-left: 10px;
            line-height: 24px;
            width:420px;
        }
    </style>
</head>

<body>

<div class="row">

    <div class="col-lg-12 wzbj">

        <div style="padding-top: 9px;float: left; padding-right: 4px;">
            <embed src="${ctx}/img/welcome/zhuye.svg" type="image/svg+xml"></embed>
        </div>
        <h1 class="page-header">用户首页&nbsp;</h1>

    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- /.row -->
<div class="row">
    <div class="col-lg-3 col-md-6 lianjie" onclick="javascript:window.location.href = '/manager/device/deviceList'">
        <div class="panel panel-primary1 panel-height">
            <div class="row panel-padding">
                <div class="img-middle">
                    <img src="${ctx}/img/welcome/sbgl.png"/>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${hrefCount.deviceManager}</div>
                    <div>设备管理</div>
                </div>
            </div>

        </div>
    </div>
    <div class="col-lg-3 col-md-6 lianjie" onclick="javascript:window.location.href = '/manager/scene/findSceneDevice'">
        <div class="panel panel-green1 panel-height">

            <div class="row panel-padding">
                <div class="img-middle">
                    <img src="${ctx}/img/welcome/cjgl.png"/>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${hrefCount.sceneManager}</div>
                    <div>场景管理</div>
                </div>
            </div>
        </div>


    </div>
    <div class="col-lg-3 col-md-6 lianjie"
         onclick="javascript:window.location.href = '/manager/region/findRegionDevice'">
        <div class="panel panel-yellow1 panel-height">

            <div class="row panel-padding">
                <div class="img-middle">
                    <img src="${ctx}/img/welcome/qygl.png"/>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${hrefCount.regionManager}</div>
                    <div>区域管理</div>
                </div>
            </div>

        </div>
    </div>
    <div class="col-lg-3 col-md-6 lianjie" onclick="">
        <div class="panel panel-red1 panel-height">

            <div class="row panel-padding">
                <div class="img-middle">
                    <img src="${ctx}/img/welcome/yjgl.png"/>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${hrefCount.deviceWarning}</div>
                    <div>设备报警</div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default panel-h">
            <div class="panel-heading">
                <span class="header">设备占出比</span>
            </div>
            <div id="chartsb" style="width:760px; height: 280px; margin: 0 auto; margin-top: 20px;">
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default panel-h">
            <div class="panel-heading">
                <span class="header">消息通知</span>
                <a class="more" href="">MORE>></a>
            </div>
            <div class="col-sm-12" style="padding-top: 15px;">
                <table width="100%"
                       class="tbl table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
                       id="dataTables-example" role="grid" aria-describedby="dataTables-example_info"
                       style="height: 280px; overflow-y: hidden;">
                    <thead>
                    <tr role="row">
                        <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                            aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending"
                            style="width:4%;text-align: center;">序号
                        </th>
                        <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                            aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending"
                            style="width:12%;">主题
                        </th>
                        <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                            aria-label="Browser: activate to sort column ascending" style="width: 30%;">内容
                        </th>
                        <th tabindex="0" aria-controls="dataTables-example" rowspan="1" colspan="1"
                            aria-label="Platform(s): activate to sort column ascending"
                            style="width: 8%;text-align: center;">时间
                        </th>
                    </tr>
                    </thead>
                    <tbody id="noticeList">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default panel-h">
            <div class="panel-heading">
                <span class="header">区域设备使用统计</span>
                <div style=" float: right;" class="namexg gjc nameadd">
                    <label class="name01">年度：
                    </label>
                    <select class="selectpicker show-tick form-control" data-live-search="true"
                            onchange="getRegionLogCountList(this.value)">
                    <#list selectYearListMap.regionYearList as regionYear>
                        <option>${regionYear}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div id="chartqy" style="width:760px; height: 300px;margin: 0 auto;  margin-top: 20px;">
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default panel-h">
            <div class="panel-heading">
                <span class="header">场景设备使用统计</span>
                <div style=" float: right;" class="namexg gjc nameadd">
                    <label class="name01">年度：
                    </label>
                    <select class="selectpicker show-tick form-control" data-live-search="true"
                            onchange="getSceneLogCountList(this.value)">
                        <#list selectYearListMap.sceneYearList as sceneYear>
                            <option>${sceneYear}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div id="chartcj" style="width:760px; height: 300px;margin: 0 auto;  margin-top: 20px;">
            </div>
        </div>
    </div>
    <!-- /.row -->
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a class="btnclose" data-dismiss="modal" aria-hidden="true">
                    <i class="iconfont">&#xe62b;</i>
                </a>
                <h4 class="modal-title" id="myModalLabel">
                    查看详细
                </h4>
            </div>
            <div class="modal-body">
                <div  style="padding-left: 20px;" class="namexg gjc nameadd">
                    <label class="name02">主题:
                    </label>
                    <span class="xxnr"></span>
                </div>
                <div  style="padding-left: 20px;" class="namexg gjc nameadd">
                    <label class="name02">时间:
                    </label>
                    <span class="xxnr"></span>
                </div>
                <div  style="padding-left: 20px;" class="namexg gjc nameadd">
                    <label class="name02">内容:
                    </label>
                    <span class="xxnr"></span>
                </div>

            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

<script src="${ctx}/vendor/jquery/jquery.min.js"></script>
<script src="${ctx}/vendor/echarts/js/echarts.js"></script>
<script src="${ctx}/vendor/bootstrap/js/bootstrap-select.js"></script>
<script src="${ctx}/vendor/welcome/showECharts.js"></script>
<script src="${ctx}/vendor/welcome/getEChartsInformation.js"></script>
<script src="${ctx}/vendor/welcome/getNoticeList.js"></script>

</body>
</html>