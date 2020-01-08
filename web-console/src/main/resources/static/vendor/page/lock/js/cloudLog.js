$(function () {
    Table();

    var picker1 = $('#datetimepicker14').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        todayHighlight: true
    });
    var picker2 = $('#datetimepicker15').datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        todayHighlight: true
    });


    picker1.on('changeDate', function (e) {
        $('#datetimepicker15').datetimepicker('setStartDate', e.date);
    });
    picker2.on('changeDate', function (e) {
        $('#datetimepicker14').datetimepicker('setEndDate', e.date);
    });


});


function Table() {
    // 初始化Table
    $('#table_list').bootstrapTable({
        url: '/manager/lock/cloudLogList', // 请求后台的URL（*）
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
                imei: $("#imei").val(),
                msg_type: $("#msg_type").val(),
                start: $("#datetimepicker14").val(),
                end: $("#datetimepicker15").val()
            };
            return param;
        },
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'id',
                visible: false
            }
            , {
                field: 'imei',
                title: '锁(IMEI)',
                align: 'center',
                valign: 'middle'
            }
            , {
                field: 'msgType',
                title: '消息类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'msgState',
                title: '消息状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value) {
                    if (value === "0") {
                        return "失败";
                    } else if (value === "1") {
                        return "成功";
                    } else if (value === "2") {
                        return "待发送";
                    }
                }
            }
            , {
                field: 'lockMsg',
                title: '锁体发包',
                align: 'center',
                valign: 'middle'
            }
            , {
                field: 'cloudMsg',
                title: '服务器发包',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'ctime',
                title: '日志时间',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                }
            }

        ]
    });
}


function lockQuery() {
    var opt = {
        url: "/manager/lock/cloudLogList"
    };
    $("#table_list").bootstrapTable('refresh', opt);
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