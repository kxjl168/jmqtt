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
                    <div class="huge">--</div>
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
                    <div class="huge">--</div>
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
                    <div class="huge">--</div>
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
                    <div class="huge">--</div>
                    <div>设备报警</div>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- /.row -->
<div class="row hide">
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
                 <#--    <#list selectYearListMap.regionYearList as regionYear>
                        <option>${regionYear}</option>
                    </#list> --#>
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
                      <#--    <#list selectYearListMap.regionYearList as regionYear>
                        <option>${regionYear}</option>
                    </#list> --#>
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