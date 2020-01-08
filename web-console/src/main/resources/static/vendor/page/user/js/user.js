$(function () {
    PersonnelInformationTable();


    $("#btnAdd").click(function () {
        $("#id").val("");
        $("#telephone").removeAttr("readonly");
        $("#userForm")[0].reset();
        $("#myModal").modal();
    });


    $("#btnSubmit").click(function () {
        var userId = $.trim($("#id").val());

        //用户信息插入
        var telephone = $.trim($("#telephone").val());
        if ("" === telephone) {
            showPopover($("#telephone"), "请输入手机号");
            return;
        }

        var password = $.trim($("#password1").val());
        if ("" === password) {
            showPopover($("#password1"), "请输入密码");
            return;
        }

        var hash = md5(password);

        var hash1 = md5(hash + telephone);

        var data = $("#userForm").serialize();

        data = data + "&" + "password=" + hash1;
        $.ajax({
            type: "post",
            url: '/manager/user/save.action',
            data: data,
            async: false,
            dataType: "json",
            success: function (response) {
                if (response.result) {
                    window.location.href = "/manager/user/manager.action";
                } else {
                    showPopover($("#message"), response.message);
                }
            }
        });
    });

    $("#btnQry").click(function () {
        var telephone = $("#qryTelephone").val();
        $("#pageForm").find("#telephone").val(telephone);
        $("#pageForm").submit();
    });
});


function showPopover(target, msg) {
    target.attr("data-original-title", msg);
    $('[data-toggle="tooltip"]').tooltip();
    target.tooltip('show');
    target.focus();

    //2秒后消失提示框
    var id = setTimeout(
        function () {
            target.attr("data-original-title", "");
            target.tooltip('hide');
        }, 2000
    );
}


function PersonnelInformationTable() {
    //初始化Table
    $('#table_PersonnelInformation').bootstrapTable({
        url: '/manager/user/userList',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        contentType:'application/x-www-form-urlencoded',
        toolbar: '#toolbar',                //工具按钮用哪个容器
        showHeader: true,
        searchAlign: 'left',
        buttonsAlign: 'left',
        searchOnEnterKey: true,
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParamsType:'nolimit',
        queryParams: function(params) {
            if($("#search_telephone").val()!="") params.telephone=$("#search_telephone").val();
            return params;
        },
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortName: 'roleName',             //排序字段
        sortOrder: "asc",                  //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'id',
                visible: false
            },  {
                field: 'telephone',
                title: '手机号码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'nickname',
                title: '昵称',
                align: 'center',
                valign: 'middle'
            },  {
                field: 'roleName',
                title: '角色',
                align: 'center',
                valign: 'middle'
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

    return [
        '<div class="btn-group">' +
        '<button id = "update" type = "button" class = "btn btn-primary"><i class="glyphicon glyphicon-pencil">修改</i> </button>' +
        // '<button id = "delete" type = "button" class = "btn btn-danger"><i class="glyphicon glyphicon-trash">删除</i> </button>' +
        '</div>'
    ].join("");
}


window.PersonnelInformationEvents = {
    "click #update": function (e, value, row, index) {
        $.ajax({
            type: "post",
            url: '/manager/user/getUser',
            data: {id: row.id},
            async: false,
            dataType: "json",
            success: function (response) {
                //管理员
                if(response.id=="1"){
                    $("#role").attr("disabled",true);
                }else{
                    $("#role").removeAttr("disabled");
                }
                $("#id").val(response.id);
                $("#userRoleId").val(response.userRoleId);
                $("#telephone").val(response.telephone);
                $("#role option").removeAttr("selected");
                $("#role").find("option[value='" + response.roleId + "']").attr("selected", true);
                $("#nickname").val(response.nickname);
                $("#myModal").modal();
            }
        });

    },

    "click #delete": function (e, value, row, index) {
        var msg = "您真的确定要删除吗？";
        var url = "/manager/user/deleteUser";
        if (true === confirm(msg)) {
            $.ajax({
                type: "post",
                url: url,
                data: {"id": row.id},
                success: function (response) {
                    if (response.bol) {
                        alert("删除成功！");
                    } else {
                        alert("删除失败！");
                    }
                }
            });
        }
        PersonnelInformationRefresh();
    }
};

function PersonnelInformationRefresh() {
    // var opt = {
    //     url: "/manager/user/UserList",
    //     silent: true
    // };
    $("#table_PersonnelInformation").bootstrapTable('refresh');
}
function doSearch() {
    PersonnelInformationRefresh();
}

function checkPassWord(obj) {
    var reg = new RegExp(/^(?![^a-z]+$)(?![^A-Z]+$)(?!\D+$)/);
    var bol = reg.test(obj.val());
    if (!bol) {
        alert("密码必须由6-16位大小写英文字母加数字组成！");
        obj.val("");
    }
}

function checkTel(obj) {
    var reg = new RegExp(/(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/);
    var bol = reg.test(obj.val());
    if (!bol) {
        alert("请输入有效的手机号码！");
        obj.val("");
    }
}