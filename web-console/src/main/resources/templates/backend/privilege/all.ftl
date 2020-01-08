<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>所有${module!}<#if module=='system'>系统<#else >应用</#if>管理员</title>
    <link href="${ctx}/vendor/datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/vendor/messenger/css/messenger.css" rel="stylesheet">
<#include "../../common/messenger.script.ftl">
    <script type="text/javascript" src="${ctx}/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/vendor/datatable/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/vendor/datatable/js/dataTables.bootstrap.min.js"></script>

</head>
<body>
<script type="text/javascript">
    $(document).ready(function () {
        var admins_table = $('#admins_table').DataTable({
            'processing': true,
            'serverSide': true,
            "ajax": {
                url: "/backend/admin/${module!}/all",
                type: 'post'
            },
            'ordering': false,
            "lengthMenu": [10, 25, 50, 100],
            "columns": [
                {"data": null},
                {"data": "account"},
                {"data": "nickName"},
                {"data": "email"},
                {"data": "phone"},
                {
                    "data": "status",
                    "render": function (data) {
                        var xv;
                        switch (data) {
                            case 0:
                                xv = '<a href="#" class="btn btn-success btn-circle" title="待审核"><i class="fa fa-edit"></i></a>';
                                break;
                            case 101:
                                xv = '<a href="#" class="btn btn-warning btn-circle" title="被拒绝"><i class="fa fa-warning"></i></a>';
                                break;
                            case 120:
                                xv = '<a href="#" class="btn btn-warning btn-circle " title="挂起"><i class="glyphicon glyphicon-pause"></i></a>';
                                break;
                            case 122:
                                xv = '<a href="#" class="btn btn-success btn-circle " title="申请挂起"><i class="glyphicon glyphicon-pause"></i></a>';
                                break;
                            case 130:
                                xv = '<a href="#" class="btn btn-danger btn-circle " title="已注销"><i class="fa fa-times"></i></a>';
                                break;
                            case 132:
                                xv = '<a href="#" class="btn btn-success btn-circle " title="申请注销"><i class="fa fa-times"></i></a>';
                                break;
                            default:
                                xv = '<a href="#" class="btn btn-info btn-circle" title="正常"><i class="fa fa-check"></i></a>';
                        }
                        return xv;
                    }
                },
                {
                    "data": "role",
                    "render": function (data) {
                        var xv;
                        switch (data) {
                            case 4:
                                xv = '区域管理员';
                                break;
                            case 16:
                                xv = '系统管理员';
                                break;
                            case 8:
                                xv = '审计管理员';
                                break;
                            case 24:
                                xv = '系统管理员 | 审计管理员';
                                break;
                            case 122:
                                xv = '<a href="#" class="btn btn-success btn-circle " title="申请挂起"><i class="glyphicon glyphicon-pause"></i></a>';
                                break;
                            case 130:
                                xv = '<a href="#" class="btn btn-danger btn-circle " title="已注销"><i class="fa fa-times"></i></a>';
                                break;
                            case 132:
                                xv = '<a href="#" class="btn btn-success btn-circle " title="申请注销"><i class="fa fa-times"></i></a>';
                                break;
                            default:
                                xv = '<a href="#" class="btn btn-info btn-circle" title="正常"><i class="fa fa-check"></i></a>';
                        }
                        return xv;
                    }
                },
                {
                    "data": "id",
                    "render": function (data) {
                        return '<a href="${ctx}/backend/admin/${module!}/' + data + '/detail" class="btn btn-sm btn-primary">修改</a> ' +
                                '<button onclick="showDeleteModal(' + data + ')" class="btn btn-sm btn-danger">删除</button>';
                    }
                }
            ],
            'language': {
                'search': "关键词查询:",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sLengthMenu": "每页显示条数: _MENU_",
                "oPaginate": {
                    "sFirst": "首页",
                    "sLast": "末页",
                    "sNext": "下页",
                    "sPrevious": "上页"
                },
            },
            //增加序列号
            "fnDrawCallback": function () {
                var api = this.api();
                var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
                api.column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            "initComplete": function () {
                var api = this.api();
                $('#admins_table_filter').find('input').off('.DT').on('keyup.DT', function (e) {
                    if (e.keyCode == 13) {
                        api.search(this.value).draw();
                    }
                });
            }
        });
    });

    function showDeleteModal(adminID) {
        $('#deleteAdminForm').attr("action", "/backend/admin/${module!}/" + adminID + "/delete");
        $('#delete_modal').modal({backdrop: true, keyboard: true, show: true});
    }

</script>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"><#if module=='system'>系统<#else >区域</#if>管理员</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">

        <div class="panel panel-primary">
            <div class="panel-heading">
            <#if module=='system'>系统<#else >区域</#if>管理员
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-lg-12">
                        <table id="admins_table"
                               class="table table-striped table-bordered table-hover table-responsive">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>管理员用户名</th>
                                <th>昵称</th>
                                <th>电子邮箱</th>
                                <th>手机号码</th>
                                <th>状态</th>
                                <th>角色</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                        </table>

                        <div class="modal fade" id="delete_modal" tabindex="-1" role="dialog"
                             aria-labelledby="AuditModelLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close"><span
                                                aria-hidden="true">&times;</span></button>

                                        <h4 class="modal-title text-center" id="AuditModelLabel">
                                            删除管理员</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>您确定将该管理员删除吗？</p>
                                    </div>
                                    <div class="modal-footer">
                                        <form id="deleteAdminForm" action="" method="POST">
                                            <input type="hidden" id="action" name="action" value="delete">

                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                再想一下
                                            </button>
                                            <button type="submit" class="btn btn-danger">
                                                删除
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <a href="${ctx}/backend/admin/${module!}/add" class="btn btn-success btn-lg">添加管理员
                    (+)</a>
            </div>

        </div>
    </div>
</div>
</body>
</html>