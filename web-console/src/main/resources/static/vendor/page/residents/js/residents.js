$(function () {
    Table();

    selectcommunity();

    $('#selectcommunity').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        clearroom();
        clearbuilding();
        selectbuilding();
        Query();
    });

    $('#selectbuilding').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        clearroom();
        selectroom();
        Query();
    });

    $('#selectroom').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        Query();
    });

    $(".clearData").click(function () {
        cleardata();
    });


});


function selectcommunity() {
    $.ajax({
        type: "post",
        async: false,
        url: "/manager/community/selectcommunity",
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectcommunity').append(option);
            }
            $('#selectcommunity').selectpicker('refresh');
        }
    });
}

function selectbuilding() {
    var communityid = $('#selectcommunity').val();
    $.ajax({
        type: "post",
        async: false,
        url: "/manager/building/selectbuilding",
        data: {
            "communityid": communityid
        },
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectbuilding').append(option);
            }
            $('#selectbuilding').selectpicker('refresh');
        }
    });
}

function selectroom() {
    var buildingid = $('#selectbuilding').val();
    $.ajax({
        type: "post",
        async: false,
        url: "/manager/room/selectroom",
        data: {
            "buildingid": buildingid
        },
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var data = response[i];
                var option = $("<option  value='" + data.id + "'>" + data.name + "</option>");
                $('#selectroom').append(option);
            }
            $('#selectroom').selectpicker('refresh');
        }
    });
}

function clearbuilding() {
    $("#selectbuilding").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectbuilding").append(option);
    $('#selectbuilding').selectpicker('refresh');
}

function clearroom() {
    $("#selectroom").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectroom").append(option);
    $('#selectroom').selectpicker('refresh');
}


function Table() {
    // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/manager/residents/residentsList', // 请求后台的URL（*）
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
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 25], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        queryParams: function queryParams(params) { // 设置查询参数
            var param = {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                name: $("#name").val(),
                community: $("#selectcommunity").val(),
                building: $("#selectbuilding").val(),
                room: $("#selectroom").val(),
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
                title: '租客姓名',
                align: 'center',
                valign: 'middle'
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
                field: 'start_time',
                title: '租约开始时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd");
                }
            }, {
                field: 'end_time',
                title: '租约结束时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd");
                }
            }, {
                field: 'is_lease',
                title: '租约状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "0") {
                        return "停租";
                    } else if (value === "1") {
                        return "正常";
                    }
                }
            }, {
                title: '操作',
                field: 'vehicleno',
                align: 'center',
                formatter: button,
                events: informationEvents
            }

        ]
    });
}

function button(value, row, index) {
    return ['<div class="">'
    + '<button id = "update" type = "button" class = "btn btn-info"><i class="glyphicon glyphicon-pencil">租户维护</i> </button>&nbsp;'
    + '<button id = "managementKey" type = "button" class = "btn btn-default"><i class="glyphicon glyphicon-pencil">密码管理</i> </button>&nbsp;'
    + '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">停租租户</i> </button>'
    + '</div>'].join("");
}


window.informationEvents = {
    "click #update": function (e, value, row, index) {
        $("#residentsid").val(row.id);
        $("#leaseid").val(row.leaseid);
        $("#room_id").val(row.room_id);
        $('#residentsname').val(row.name);
        $('#nationality').val(row.nationality);
        $('#gender').val(row.gender);
        $('#birth').val(row.birth);
        $('#id_crad').val(row.id_crad);
        $('#permanent_address').val(row.permanent_address);
        $('#telephone').val(row.telephone);
        $('#password').val(row.password);


        $('#selectcommunity1').selectpicker('val', row.communityid);
        selectbuilding1();
        $('#selectbuilding1').selectpicker('val', row.buildingid);

        selectroom1();
        var option = $("<option  value='" + row.roomid + "'>" + row.room + "</option>");
        $('#selectroom1').append(option);
        $('#selectroom1').selectpicker('refresh');
        $('#selectroom1').selectpicker('val', row.roomid);


        var start_time = new Date(row.start_time);
        $("#datetimepicker1").datetimepicker("setDate", start_time);
        var end_time = new Date(row.end_time);
        $("#datetimepicker2").datetimepicker("setDate", end_time);

        $("#title").html("租户维护");
        $("#myModal").modal();
    },

    "click #delete": function (e, value, row, index) {
        var msg = "您真的确定要停租吗？";
        var url = "/manager/lease/deleteLease";
        var r = confirm(msg);
        if (r === true) {
            var params = {
                "leaseid": row.leaseid,
                "room_id": row.room_id,
            };
            $.ajax({
                type: "post",
                url: url,
                data: {params: JSON.stringify(params)},
                success: function (response) {
                    if (response.bol) {
                        // success("删除成功！");
                        Query();
                    } else {
                        // error("删除失败！");
                    }
                }
            });
            Query();
        }

    },

    "click #managementKey": function (e, value, row, index) {
        $("#pass_room_id").val(row.room_id);
        $("#passName").text(row.name);
        $("#passTel").text(row.telephone);
        $("#passCommunity").text(row.community);
        $("#passBuilding").text(row.building);
        $("#passRoom").text(row.room);
        $("#passstartTime").text(new Date(row.start_time).Format("yyyy-MM-dd"));
        $("#passEndTime").text(new Date(row.end_time).Format("yyyy-MM-dd"));
        passTable(row.room_id);
        $("#passwordModal").modal();
    },


};

function Query() {
    var opt = {
        url: "/manager/residents/residentsList"
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

            name: {
                validators: {
                    notEmpty: {
                        message: '租客姓名不能为空'
                    }
                }
            },
            id_crad: {
                validators: {
                    notEmpty: {
                        message: '证件号码不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /\d{17}[\d|x]|\d{15}/,
                        message: '证件号码错误'
                    }
                }
            },
            telephone: {
                validators: {
                    notEmpty: {
                        message: '电话不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^1\d{10}$/,
                        message: '电话号码错误'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        /* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /[1-9]\d*/,
                        message: '密码为纯数字'
                    }, stringLength: {
                        min: 6,
                        message: '密码长度为6位'
                    }
                }
            }
        }
    });
}

function cleardata() {
    $("#mform").data('bootstrapValidator').destroy();
    $("#residentsid").val("");
    $("#leaseid").val("");
    $("#room_id").val("");
    $('#residentsname').val('');
    $('#nationality').val('');
    $('#birth').val('');
    $('#id_crad').val('');
    $('#permanent_address').val('');
    $('#telephone').val('');
    $('#password').val('');

    //时间清除错误
    $('#datetimepicker1').datetimepicker('setStartDate', null);
    $('#datetimepicker2').datetimepicker('setStartDate', null);


    clearroom1();
    clearbuilding1();
    $("#selectcommunity1").empty();
    var option = $("<option  value=''>" + "----请选择---" + "</option>");
    $("#selectcommunity1").append(option);
    $('#selectcommunity1').selectpicker('refresh');
    initValidate();
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