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

    <link href="${ctx}/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--bootstrapValidator的验证css-->
    <link href="${ctx}/vendor/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">

    <script type="text/javascript" src="${ctx}/vendor/jquery/jquery.min.js"></script>
    <script src="${ctx}/js/html5shiv.js"></script>
    <script src="${ctx}/js/respond.min.js"></script>
    
    <script>
    /**
     * 获取当前项目名称
     */
    function getRPath() {
        return getRootPath();//.ctx;
    }
    /**
     * 获取当前项目名称
     */
    function getRootPath() {
        //获取路径
        var pathName = window.document.location.pathname;
        //截取，得到项目名称
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return projectName
    }

    </script>
    
    
    <![endif]-->
    <sitemesh:write property="head"/>
</head>
<body>
<div class="container">
<sitemesh:write property="body"/>
</div>

<script type="text/javascript" src="${ctx}/vendor/bootstrap/js/bootstrap.js"></script>
<!--bootstrapValidator的验证js-->
<script type="text/javascript" src="${ctx}/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>
</body>
</html>