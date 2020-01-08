$(function () {
    Table();

    initValidate();

    $("#btnAdd").click(function () {
        $("#faction").html("新增");
        $("#lockid").val("");
        $("#myModal").modal();
    });

    $('#myModal').on('hide.bs.modal', function (e) {
        $("#mform").data('bootstrapValidator').destroy();
        $('#mform').data('bootstrapValidator', null);
        initValidate();
        $("#lockid").val("");
        $('#lockName').val('');
        $('#lockImei').val('');
        $('#cloud_type').val('1');
        $('#secret_key').val('');
    });

    $('#setKeyModel').on('hide.bs.modal', function (e) {
        $('#cnt').val('');
    });

    $("#btnSubmit").click(function () {
        $("#mform").data("bootstrapValidator").validate();
        var flag = $("#mform").data("bootstrapValidator").isValid();
        if (!flag) {
            return;
        }

        var id = $("#lockid").val();
        var lockName = $('#lockName').val();
        var lockImei = $('#lockImei').val();
        var cloud_type = $('#cloud_type').val();
        var secret_key = $('#secret_key').val();

        var openMode = "";


        var params = {
            "id": id,
            "name": lockName,
            "imei": lockImei,
            "cloud_type": cloud_type,
            "secret_key": secret_key,
            "openMode": openMode
        };

        var url = "";
        if (id === "") {
            url = '/manager/lock/addLock';
        } else {
            url = '/manager/lock/updateLock';
        }

        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    $('#myModal').modal("hide");
                    lockQuery();
                } else {
                    alert(response.message);
                }

            }
        });

    });


    $("#btnSubmit1").click(function () {

        var ilockId = $("#ilockId").val();
        var startTime = $("#datetimepicker14").val();
        var endTime = $("#datetimepicker15").val();
        var cnt = $('#cnt').val();
        var password = $('#passeWord').val();
        var passwordType = "2";
        if (password.length !== 6) {
            alert("密码长度不符")
        }


        var params = {
            "ilockId": ilockId,
            "password": password,
            "passwordType": passwordType,
            "cnt": cnt,
            "startTime": startTime,
            "endTime": endTime
        };

        var url = '/manager/lock/setSecretKey';


        $.ajax({
            type: "post",
            url: url,
            data: {params: JSON.stringify(params)},
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.bol) {
                    $('#setKeyModel').modal("hide");
                    alert("设置成功")
                } else {
                    alert(response.message);
                }

            }
        });

    });


});


function Table() {
    // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/manager/lock/lockList', // 请求后台的URL（*）
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
        sortName: 'imei', // 排序字段
        sortOrder: "desc", // 排序方式
        sidePagination: "server", // 分页方式：client 客户端分页，server 服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        queryParams: function queryParams(params) { // 设置查询参数
            var param = {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                name: $("#name").val(),
                imei: $("#imei").val()
            };
            return param;
        },
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'id',
                visible: false
            }, {
                field: 'name',
                title: '锁名称',
                align: 'center',
                valign: 'middle',
            }
            , {
                field: 'imei',
                title: '锁(IMEI)',
                align: 'center',
                valign: 'middle'
            }
            , {
                field: 'nbiotId',
                title: 'NBIOT-Id',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'cloudType',
                title: '云平台类型',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "1") {
                        return "中国移动";
                    } else if (value === "2") {
                        return "中国电信";
                    }
                }

            }
            , {
                field: 'secretKey',
                title: '加密秘钥',
                align: 'center',
                valign: 'middle'
            }
            , {
                field: 'signalIntensity',
                title: '信号强度',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'electricQuantity',
                title: '电量',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'state',
                title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "1") {
                        return "正常在线";
                    } else {
                        return "离线失效";
                    }
                }
            }, {
                field: 'community',
                title: '小区',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'building',
                title: '楼栋',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'room',
                title: '房间',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'utime',
                title: '更新时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                }
            }, {
                title: '操作',
                field: 'vehicleno',
                align: 'center',
                formatter: modifyAndDeleteButton,
                events: PersonnelInformationEvents
            }

        ]
    });
}

function modifyAndDeleteButton(value, row, index) {
    return ['<div class="">'
    + '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">修改</i> </button>&nbsp;'
    + '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>'
    + '</div>'].join("");
}

window.PersonnelInformationEvents = {
    "click #update": function (e, value, row, index) {
        $("#lockid").val(row.id);
        $('#lockName').val(row.name);
        $('#cloud_type').val(row.cloudType);
        $('#lockImei').val(row.imei);
        $('#secret_key').val(row.secretKey);
        $("#myModal").modal();
    },

    "click #delete": function (e, value, row, index) {
        var msg = "您真的确定要删除吗？";
        var url = "/manager/lock/deleteLock";
        var r = confirm(msg);
        if (r === true) {
            $.ajax({
                type: "post",
                url: url,
                data: {
                    "id": row.id
                },
                success: function (response) {
                    if (response.bol) {
                        // success("删除成功！");
                        lockQuery();
                    } else {
                        // error("删除失败！");
                    }
                }
            });
        }

    },


    "click #setKey": function (e, value, row, index) {
        $("#setKeyModel").modal();
        $("#ilockId").val(row.id);
    }


};

function lockQuery() {
    var opt = {
        url: "/manager/lock/lockList"
    };
    $("#table_list").bootstrapTable('refresh', opt);
}

function initValidate() {
    $("#mform").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

            lockName: {
                validators: {
                    notEmpty: {
                        message: '锁名称不能为空'
                    }
                }
            },
            lockImei: {
                validators: {
                    notEmpty: {
                        message: '锁(IMEI)不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /(\d{15})/g,
                        message: 'IMEI错误'
                    }

                }
            }

        }
    });

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