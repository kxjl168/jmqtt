<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>中通国脉IOT管理平台</title>
    <link rel="icon" href="${ctx}/img/ztgm.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${ctx}/img/ztgm.ico" type="image/x-icon"/>

    <!-- Bootstrap core CSS -->

    <link href="${ctx}/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/js/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/js/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {
            font-family: 'Microsoft YaHei', Tahoma, Verdana, Arial, Helvetica, sans-serif;
        }

        #nav-link {
            color: #03739C;
            text-decoration: none;
            font-weight: bold;
        }

        .link-center {
            width: auto;
            display: table;
            margin-left: auto;
            margin-right: auto;
        }

    </style>
    <sitemesh:write property="head"/>
</head>

<body>
<!-- Header
================================================== -->
<#--<#include "default_header.ftl"/>-->

<!-- Main Content Aera
================================================== -->
<div class="container">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <!-- Body
================================================== -->
            <sitemesh:write property="body"/>
        </div>
    </div>
</div>
<#include "default_footer.ftl"/>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="${ctx}/js/holder.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${ctx}/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
