<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>中通国脉IOT管理平台</title>
    <link rel="icon" href="${ctx}/img/ztgm.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${ctx}/img/ztgm.ico" type="image/x-icon"/>

<#--bootstrap-->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrapValidator/css/bootstrapValidator.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-table/css/bootstrap-table.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-treeview/css/bootstrap-treeview.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-select/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-buttons/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-datepicker/css/bootstrap-datetimepicker.css">
    
    
    
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/bootstrap-xeditor/bootstrap-editable.css">

<#--图标码-->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/font-awesome/css/font-awesome.css">

<#--页面整体样式css-->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/blueSkin/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/blueSkin/style-responsive.css">

<#--地区选择-->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/distpicker/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/distpicker/css/normalize.css">

<#--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/select2/css/select2.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/select2/css/select2.bootstrap.css">

<#-- sweetalert -->
    <link rel="stylesheet" type="text/css" href="${ctx}/vendor/sweetalert/css/sweetalert2.min.css"/>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/tooltip.css">
 <link rel="stylesheet" type="text/css" href="${ctx}/css/iot.css"> 

    <script src="${ctx}/vendor/jquery/jquery.js"></script>

    <script src="${ctx}/vendor/jquery/jquery-migrate-3.0.1.min.js"></script>

    <script src="${ctx}/vendor/jquery/jquery.plugin.js"></script>

    <sitemesh:write property="head"/>

</head>
<body style="width: 100%; height:100%;overflow-x: auto;position: absolute">

<section id="container">

    <header class="header black-bg">
        <a href="${ctx}/manager/admin/index.action" class="logo"><img src="${ctx}/img/blueSkin/logo.png"
                                                                style="height: 50px;margin-top: -10px;"/></a>

        <div class="top-menu">
            <ul class="nav pull-right top-menu">
             <li class="refreshBtn">
                    <a  href="javascript:void(0);" onclick="refreshMenu();" title="重新加载菜单" style="margin-top: 20px;"><i class="fa fa-refresh"></i></a>
                </li>
                <li>
                    <a href="${ctx}/logout.action" style="margin-top: 20px;"><img src="${ctx}/img/blueSkin/tuichu.png"
                                                                            style="width: 20px;height: 18px;margin-top: -4px;"/></a>
                </li>
            </ul>
        </div>
    </header>


    <#include "admin_left_navi.ftl"/>

    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <sitemesh:write property="body"/>
            </div>
        </section>
    </section>
</section>


<script src="${ctx}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/vendor/jquery/jquery.dcjqaccordion.2.7.js"></script>
<script src="${ctx}/vendor/jquery/jquery.scrollTo.min.js"></script>
<script src="${ctx}/vendor/jquery/jquery.nicescroll.js"></script>
<script src="${ctx}/vendor/jquery/jquery.sparkline.js"></script>
<script src="${ctx}/vendor/blueSkin/common-scripts.js"></script>
<script src="${ctx}/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>
<script src="${ctx}/vendor/bootstrap-table/js/bootstrap-table.js"></script>
<script src="${ctx}/vendor/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/vendor/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<script src="${ctx}/vendor/bootstrap-datepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/vendor/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="${ctx}/vendor/bootstrap-treeview/js/bootstrap-treeview.min.js"></script>
<script src="${ctx}/vendor/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${ctx}/vendor/bootstrap-select/locales/defaults-zh_CN.min.js"></script>

<script src="${ctx}/vendor/distpicker/js/distpicker.data.js"></script>
<script src="${ctx}/vendor/distpicker/js/distpicker.js"></script>


<script src="${ctx}/vendor/sweetalert/js/sweetalert2.min.js"></script>

<script src="${ctx}/vendor/bootstrap-xeditor/bootstrap-editable.js"></script>
<script src="${ctx}/vendor/bootstrap-xeditor/x-editor.js"></script>




<!-- <script src="${ctx}/vendor/select2/js/select2.full.js"></script>
<script src="${ctx}/vendor/select2/js/i18n/zh-CN.js"></script>

 -->
<script src="${ctx}/js/iot.js"></script>


</body>
</html>