$(function () {

    passValidate();

    $('#passwordModal').on('hide.bs.modal', function () {
        passRefresh();
        $("#pass_room_id").val("");
    });

    $('#passModal').on('hide.bs.modal', function () {
        $("#passform").data('bootstrapValidator').destroy();
        $("#password").val("");
        $("#cnt").val("");
        $('#datetimepicker3').removeAttr("disabled");
        $('#datetimepicker4').removeAttr("disabled");

        $("#password").next().removeClass("fa-eye").addClass("fa-eye-slash");
        $("#password").attr("type", "password");


        //时间清除错误
        $('#datetimepicker3').datetimepicker('update');
        $('#datetimepicker4').datetimepicker('update');

        passRefresh();
        passValidate();
    });


    $("#btnSubmitPass").click(function () {
        $("#passform").data("bootstrapValidator").validate();
        var flag = $("#passform").data("bootstrapValidator").isValid();
        if (!flag) {
            return;
        }

        var pass_room_id = $("#pass_room_id").val();
        var password = $("#password").val();
        var cnt = $("#cnt").val();


        var start_time = $("#datetimepicker3").val();
        var end_time = $("#datetimepicker4").val();


        var params = {
            "pass_room_id": pass_room_id,
            "password": password,
            "cnt": cnt,
            "start_time": start_time,
            "end_time": end_time,
        };

        var url = '/manager/password/addPassWord';

        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    passRefresh();
                    alert("操作成功");
                    $('#passModal').modal("hide");
                } else {
                    alert(response.message);
                }

            }
        });

    });


});


function setTime(type) {
    var start = new Date($("#passstartTime").text());
    var end = new Date($("#passEndTime").text());

    var picker1 = $('#datetimepicker3').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        todayHighlight: true,
        minView: 2,
        startDate: start,
        endDate: end
    });

    var picker2 = $('#datetimepicker4').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        todayHighlight: true,
        minView: 2,
        startDate: start,
        endDate: end
    });

    picker1.on('changeDate', function (e) {
        $('#datetimepicker4').datetimepicker('setStartDate', e.date);
    });
    picker2.on('changeDate', function (e) {
        $('#datetimepicker3').datetimepicker('setEndDate', e.date);
    });

    $('#datetimepicker3').datetimepicker('setDate', start);
    $('#datetimepicker4').datetimepicker('setDate', end);

    if (type === "0") {
        $("#datetimepicker3").attr("disabled", true);
        $("#datetimepicker4").attr("disabled", true);
    }
}

function applyPass(type) {
    if (type === "0") {
        $("#passtitle").html("申请密码");
        $("#cnt").val(0);
        $("#cntDiv").hide();
        setTime(type);
    } else if (type === "1") {
        $("#passtitle").html("申请临时密码");
        $("#cntDiv").show();
        setTime(type);
    }
    $("#passModal").modal();
}


function passTable(roomId) {
    // 初始化Table
    $('#pass_list').bootstrapTable({
        url: '/manager/password/passwordList', // 请求后台的URL（*）
        method: 'post', // 请求方式（*）
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        toolbar: '#toolbar', // 工具按钮用哪个容器
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',
        searchOnEnterKey: true,
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sortable: false, // 是否启用排序
        // sortName: 'id', // 排序字段
        // sortOrder: "desc", // 排序方式
        sidePagination: "server", // 分页方式：client 客户端分页，server 服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 5, // 每页的记录行数（*）
        // pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        queryParams: function queryParams(params) { // 设置查询参数
            var param = {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                room_id: roomId,
            };
            return param;
        },
        uniqueId: "password_id", // 每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'password_id',
                visible: false
            }, {
                field: 'password_type',
                title: '密码类型',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "1") {
                        return "指纹密码";
                    } else if (value === "2") {
                        return "数字密码";
                    } else if (value === "3") {
                        return "身份证密码";
                    } else if (value === "4") {
                        return "MF卡密码";
                    } else {
                        return "保留";
                    }
                }
            }, {
                field: 'password_state',
                title: '密码状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "0") {
                        return "停用";
                    } else if (value === "1") {
                        return "启用";
                    }
                }
            }, {
                field: 'cnt',
                title: '使用次数',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "0") {
                        return "无限制";
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'use_cnt',
                title: '已使用次数',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'start_time',
                title: '开始时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd");
                }
            }, {
                field: 'end_time',
                title: '结束时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd");
                }
            }, {
                title: '操作',
                field: 'vehicleno',
                align: 'center',
                formatter: passbutton,
                events: passinformationEvents
            }

        ]
    });
}

function passbutton(value, row, index) {
    if (row.password_state === "0") {
        return ['<div class="">'
        + '<button id = "enable" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-off">启用</i> </button>&nbsp;'
        + '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
        + '</div>'].join("");
    } else if (row.password_state === "1") {
        return ['<div class="">'
        + '<button id = "disable" type = "button" class = "btn btn-warning"><i class="glyphicon glyphicon-off">停用</i> </button>&nbsp;'
        + '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
        + '</div>'].join("");
    }
}

window.passinformationEvents = {
    "click #enable": function (e, value, row, index) {
        var params = {
            "password_id": row.password_id,
            "lock_id": row.lock_id,
            "type": "1"
        };
        var url = '/manager/password/controlPassWord';
        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    passRefresh();
                    alert("操作成功");
                } else {
                    alert(response.message);
                }

            }
        });

    },

    "click #disable": function (e, value, row, index) {
        var params = {
            "password_id": row.password_id,
            "lock_id": row.lock_id,
            "type": "0"
        };
        var url = '/manager/password/controlPassWord';
        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    passRefresh();
                    alert("操作成功");
                } else {
                    alert(response.message);
                }

            }
        });

    },

    "click #delete": function (e, value, row, index) {
        var params = {
            "password_id": row.password_id,
            "lock_id": row.lock_id,
            "type": "2"
        };
        var url = '/manager/password/controlPassWord';
        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    passRefresh();
                    alert("操作成功");
                } else {
                    alert(response.message);
                }

            }
        });
        passRefresh();
    },


};

function passRefresh() {
    var opt = {
        url: "/manager/password/passwordList"
    };
    $("#pass_list").bootstrapTable('refresh', opt);
}


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function passValidate() {
    $("#passform").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /[0-9]\d*/,
                        message: '密码为纯数字'
                    }, stringLength: {
                        min: 6,
                        message: '密码长度为6位'
                    }
                }
            },
            cnt: {
                validators: {
                    notEmpty: {
                        message: '有效次数不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /[1-9]\d*/,
                        message: '有效次数为纯数字'
                    }, stringLength: {
                        max: 2,
                        message: '有效次数为6位'
                    }
                }
            }
        }
    });
}