<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>开锁日志</title>

    <link rel="stylesheet" type="text/css" href="/vendor/page/lock/css/lockInfo.css">
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap-datepicker/css/bootstrap-datetimepicker.css">

    <script src="/vendor/bootstrap-datepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="/vendor/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</head>


<body>

<div class="col-lg-12 wzbj">
    <div style="padding-top: 9px; float: left; padding-right: 4px;">
        <img src="/img/zhuye.svg" style="height: 20px;">
    </div>
    <h5 style="margin-top: 12px;">
        首页&nbsp;><span style="color: #1cb09a;">&nbsp;开锁日志管理</span>
    </h5>
</div>

<div class="col-lg-12" id="deviceQuery">


    <div class="deviceCriteria">
        <h3>锁(IMEI)</h3>
        <div id="deviceNamesearch">
            <input type="text" placeholder="请输入IMEI" id="imei" autocomplete="off" maxlength="15"/>
        </div>
    </div>


    <div class="deviceCriteria" style="width: 38%;">
        <h3>日志时间</h3>
        <div class="container" style="margin-top: 38px;">
            <div class='col-md-3'>
                <div class="form-group">
                    <div class='input-group date'>
                        <input type='text' class="form-control" id='datetimepicker14'/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                </span>
                    </div>
                </div>
            </div>
            <div class='col-md-3'>
                <div class="form-group">
                    <div class='input-group date'>
                        <input type='text' class="form-control" id='datetimepicker15'/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                </span>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <a class="button button-primary button-rounded button-small" onclick="query()" style="margin-top: 40px;">
        <i class="fa fa-search fa-lg"></i>
        <span>查询</span>
    </a>


</div>


<div class="col-lg-12" id="deviceShow" style="overflow-y: scroll;">

    <div class="table-responsive" style="margin: 10px;">
        <table id="table_list"
               class="table table-bordered table-hover table-striped"
               style="table-layout: fixed;word-break: break-all;"></table>
    </div>

</div>


<script src="/vendor/page/lock/js/openLog.js"></script>

</body>
</html>