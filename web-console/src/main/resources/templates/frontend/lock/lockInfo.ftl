<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>智能锁管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/page/lock/css/lockInfo.css">

</head>


<body>

<div class="col-lg-12 wzbj">
    <div style="padding-top: 9px; float: left; padding-right: 4px;">
        <img src="/img/zhuye.svg" style="height: 20px;">
    </div>
    <h5 style="margin-top: 12px;">
        首页&nbsp;><span style="color: #1cb09a;">&nbsp;智能锁管理</span>
    </h5>
</div>


<div class="col-lg-12" id="deviceQuery">
    <div class="deviceCriteria">
        <h3>锁名称</h3>
        <div id="deviceNamesearch">
            <input type="text" placeholder="请输入设备名称" id="name" autocomplete="off"/>
        </div>
    </div>
    <div class="deviceCriteria" style="width: 45%;">
        <h3>锁(IMEI)</h3>
        <div id="deviceNamesearch">
            <input type="text" placeholder="请输入IMEI" id="imei" autocomplete="off"/>

            <a class="button button-primary button-rounded button-small" id="btnAdd">
                <i class="fa fa-plus fa-lg"></i>
                <span>新增</span>
            </a>

            <a class="button button-primary button-rounded button-small" onclick="lockQuery()">
                <i class="fa fa-search fa-lg"></i>
                <span>查询</span>
            </a>

        </div>
    </div>
</div>


<div class="col-lg-12" id="deviceShow">

    <div class="table-responsive" style="margin: 10px;">
        <table id="table_list"
               class="table table-bordered table-hover table-striped"
              ></table>
    </div>

</div>


<div class="modal fade" id="myModal" data-backdrop="static"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    锁<span id="faction">编辑</span> <span id="message" data-original-title="xxx"
                                                        style="margin-left: 20px;"></span>
                </h4>

            </div>

            <form id="mform" class="form-horizontal" role="form"
                  action="" method="post">
                <input type="hidden" value="" id="lockid" name="id">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <label class="col-lg-3 control-label">锁名称</label>

                                <div class="col-lg-9">
                                    <input type="text" name="lockName" class="form-control" id="lockName"
                                           placeholder="锁名称">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">锁(IMEI)</label>

                                <div class="col-lg-9">
                                    <input type="text" name="lockImei" class="form-control" id="lockImei"
                                           placeholder="锁ID(IMEI)" maxlength="15">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="telephone" class="col-lg-3 control-label">云平台类型</label>

                                <div class="col-lg-9">
                                    <label>
                                        <select class="selectpicker" id="cloud_type">
                                            <option value="1">移动</option>
                                            <option value="2">电信</option>
                                        </select>
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">加密秘钥</label>

                                <div class="col-lg-9">
                                    <input type="text" name="secret_key" class="form-control" id="secret_key"
                                           placeholder="加密秘钥" maxlength="4"
                                           onkeyup="this.value=this.value.replace(/[^a-zA-Z]/g,'')"
                                           style="text-transform: uppercase;">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            id="close">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="btnSubmit">
                        提交更改
                    </button>
                </div>
            </form>


        </div>
    </div>
</div>


<div class="modal fade" id="setKeyModel" data-backdrop="static"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    设置密码<span id="message" data-original-title="xxx"
                              style="margin-left: 20px;"></span>
                </h4>

            </div>

            <form id="mform" class="form-horizontal" role="form"
                  action="" method="post">
                <input type="hidden" value="" id="ilockId" name="id">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <label class="col-lg-3 control-label">密码有效期</label>

                                <div class="col-lg-9">
                                    <div class="container">
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" id='datetimepicker14'/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" id='datetimepicker15'/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">密码</label>

                                <div class="col-lg-9">
                                    <input type="text" name="cnt" class="form-control" id="passeWord"
                                           placeholder="密码" maxlength="6" oninput="value=value.replace(/[^\d]/g,'')">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">有效次数</label>

                                <div class="col-lg-9">
                                    <input type="text" name="cnt" class="form-control" id="cnt"
                                           placeholder="有效次数" maxlength="2" oninput="value=value.replace(/[^\d]/g,'')">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            id="close">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="btnSubmit1">
                        提交更改
                    </button>
                </div>
            </form>


        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker14').datetimepicker({locale: 'zh-cn'});
        $('#datetimepicker15').datetimepicker({locale: 'zh-cn'});
        $("#datetimepicker14").on("dp.change", function (e) {
            $('#datetimepicker15').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker15").on("dp.change", function (e) {
            $('#datetimepicker14').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>

<script src="/vendor/page/lock/js/lockInfo.js"></script>
</body>
</html>