<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>租户管理</title>

    <link rel="stylesheet" type="text/css" href="/vendor/page/lock/css/lockInfo.css">
    <link rel="stylesheet" type="text/css" href="/vendor/page/residents/css/residents.css">

    <script src="/vendor/bootstrap-datepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="/vendor/bootstrap-datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</head>


<body>


<div class="col-lg-12 wzbj">
    <div style="padding-top: 9px; float: left; padding-right: 4px;">
        <img src="/img/zhuye.svg" style="height: 20px;">
    </div>
    <h5 style="margin-top: 12px;">
        首页&nbsp;><span style="color: #1cb09a;">&nbsp;租户管理</span>
    </h5>
</div>


<div class="col-lg-12" id="deviceQuery">


    <div class="deviceCriteria">
        <h3>租客姓名</h3>
        <div id="deviceNamesearch">
            <input type="text" placeholder="请输入租客姓名" id="name" autocomplete="off"/>
        </div>
    </div>


    <div class="deviceCriteria" style="width: 30%;">
        <h3>房间地址</h3>
        <div style="margin-top: 38px;">
            <select class="selectpicker" data-live-search="true" id="selectcommunity">
                <option value="">----请选择----</option>
            </select>
            <select class="selectpicker" id="selectbuilding">
                <option value="">----请选择----</option>
            </select>
            <select class="selectpicker" id="selectroom">
                <option value="">----请选择----</option>
            </select>

        </div>
    </div>


    <a class="button button-primary button-rounded button-small" onclick="Query()" style="margin-top: 40px;">
        <i class="fa fa-search fa-lg"></i>
        <span>查询</span>
    </a>

    <a class="button button-primary button-rounded button-small" id="btnAdd">
        <i class="fa fa-plus fa-lg"></i>
        <span>新增</span>
    </a>


</div>


<div class="col-lg-12" id="deviceShow" style="overflow-y: scroll;">

    <div class="table-responsive" style="margin: 10px;">
        <table id="table_list"
               class="table table-bordered table-hover table-striped"
        <#--style="table-layout: fixed;word-break: break-all;"-->></table>
    </div>

</div>


<div class="modal fade" id="myModal" data-backdrop="static"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close clearData" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span id="title">用户维护</span> <span id="message" data-original-title="xxx"
                                                       style="margin-left: 20px;"></span>
                </h4>

            </div>

            <form id="mform" class="form-horizontal" role="form"
                  action="" method="post">
                <input type="hidden" value="" id="residentsid">
                <input type="hidden" value="" id="leaseid">
                <input type="hidden" value="" id="room_id">
                <div class="modal-body">
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="form-group">
                                <label class="col-lg-3 control-label">租客姓名</label>
                                <div class="col-lg-3">
                                    <input type="text" name="residentsname" class="form-control" id="residentsname"
                                           autocomplete="off">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-lg-3 control-label">性别民族</label>

                                <div class="col-lg-3">
                                    <label>
                                        <select class="selectpicker" id="gender">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </label>
                                </div>

                                <div class="col-lg-3">
                                    <input type="text" name="nationality" class="form-control" id="nationality"
                                           placeholder="民族">
                                    <p class="help-block"></p>
                                </div>

                                <div class="col-lg-3">
                                    <input type="text" name="birth" class="form-control" id="birth"
                                           placeholder="出生日期">
                                    <p class="help-block"></p>
                                </div>

                            </div>


                            <div class="form-group">
                                <label class="col-lg-3 control-label">证件号码</label>

                                <div class="col-lg-9">
                                    <input type="text" name="id_crad" class="form-control" id="id_crad"
                                           autocomplete="off" maxlength="18">
                                    <p class="help-block"></p>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-lg-3 control-label">户籍地址</label>

                                <div class="col-lg-9">
                                    <input type="text" name="permanent_address" class="form-control"
                                           id="permanent_address">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">联系电话</label>

                                <div class="col-lg-9">
                                    <input type="text" name="telephone" class="form-control" id="telephone"
                                           maxlength="11">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">租住房间</label>

                                <div class="col-lg-9">
                                    <select class="selectpicker" data-live-search="true" id="selectcommunity1">
                                        <option value="">----请选择----</option>
                                    </select>
                                    <select class="selectpicker" id="selectbuilding1">
                                        <option value="">----请选择----</option>
                                    </select>
                                    <select class="selectpicker" id="selectroom1">
                                        <option value="">----请选择----</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-3 control-label">租约时间</label>

                                <div class="col-lg-9" style="margin-left: -15px;">

                                    <div class="container">
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" value=""
                                                           name="datetimepicker1" data-format="yyyy-MM-dd"
                                                           id='datetimepicker1' readonly/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                                                     </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class='col-md-1' style="width: 3%;">
                                            -
                                        </div>
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" value=""
                                                           name="datetimepicker2" data-format="yyyy-MM-dd"
                                                           id='datetimepicker2' readonly/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default clearData" data-dismiss="modal"
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


<div class="modal fade" id="passwordModal" data-backdrop="static"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 65%!important;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close clearData" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span>密码管理</span> <span id="message" data-original-title="xxx"
                                            style="margin-left: 20px;"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">


                        <div class="form-group">
                            <label class="col-lg-1" style="font-weight:bold;">租客姓名:</label>
                            <div class="col-lg-5">
                                <span id="passName"></span>
                                <p class="help-block"></p>
                            </div>
                            <label class="col-lg-1" style="font-weight:bold;">手机号码:</label>
                            <div class="col-lg-5">
                                <span id="passTel"></span>
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-1" style="font-weight:bold;">居住地址:</label>
                            <div class="col-lg-11">
                                <span id="passCommunity"></span>
                                <span id="passBuilding" style="margin-left: 20px;"></span>
                                <span id="passRoom" style="margin-left: 20px;"></span>
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-1" style="font-weight:bold;">租约时间:</label>
                            <div class="col-lg-11">
                                <span id="passstartTime"></span>
                                <span style="margin-left: 20px;"> 至 </span>
                                <span id="passEndTime" style="margin-left: 20px;"></span>
                                <p class="help-block"></p>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-12" style="margin-top: 8px;margin-left: 12px;">
                                <button type="button" class="btn btn-primary" onclick="applyPass('0')">申请密码</button>
                                <button type="button" class="btn btn-primary" onclick="applyPass('1')"
                                        style="margin-left: 15px;">申请临时密码
                                </button>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-12">
                                <div class="table-responsive" style="margin: 10px;">
                                    <table id="pass_list"
                                           class="table table-bordered table-hover table-striped"
                                           style="table-layout: fixed;word-break: break-all;"></table>
                                </div>
                                <p class="help-block"></p>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="passModal" data-backdrop="static"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close clearData" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <span id="passtitle">申请密码</span> <span id="message" data-original-title="xxx"
                                                           style="margin-left: 20px;"></span>
                </h4>
            </div>

            <form id="passform" class="form-horizontal" role="form"
                  action="" method="post">
                <input type="hidden" value="" id="pass_room_id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12">

                            <div class="form-group">
                                <label class="col-lg-2 control-label"
                                       style="font-weight:bold;">有效时间:</label>
                                <div class="col-lg-10" style="margin-left: -15px;">
                                    <div class="container">
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" value=""
                                                           name="datetimepicker3" data-format="yyyy-MM-dd"
                                                           id='datetimepicker3' readonly/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                                                     </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class='col-md-1' style="width: 3%;text-align: center;">
                                            <span>-</span>
                                        </div>
                                        <div class='col-md-2'>
                                            <div class="form-group">
                                                <div class='input-group date'>
                                                    <input type='text' class="form-control" value=""
                                                           name="datetimepicker4" data-format="yyyy-MM-dd"
                                                           id='datetimepicker4' readonly/>
                                                    <span class="input-group-addon"><span
                                                            class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="help-block"></p>
                                </div>
                            </div>


                            <div class="form-group" id="passwordDiv">
                                <label class="col-lg-3 control-label" style="font-weight:bold;">申请密码</label>
                                <div class="col-lg-9 passwordType">
                                    <input type="password" name="password" class="form-control" id="password"
                                           autocomplete="off" placeholder="请输入您的密码"
                                           maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                                    <i class="fa fa-eye-slash"></i>
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group" id="cntDiv">
                                <label class="col-lg-3 control-label" style="font-weight:bold;">有效次数</label>
                                <div class="col-lg-9">
                                    <input type="text" name="cnt" class="form-control" id="cnt"
                                           autocomplete="off" placeholder="请输入有效次数"
                                           maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                                    <p class="help-block"></p>
                                </div>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default clearData" data-dismiss="modal"
                    >关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="btnSubmitPass">
                        提交更改
                    </button>
                </div>
            </form>


        </div>
    </div>
</div>


<script src="/vendor/page/residents/js/residents.js"></script>
<script src="/vendor/page/residents/js/lease.js"></script>
<script src="/vendor/page/residents/js/password.js"></script>


</body>
</html>