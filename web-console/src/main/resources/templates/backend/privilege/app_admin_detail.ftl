<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>修改应用管理员</title>
    <link href="${ctx}/vendor/messenger/css/messenger.css" rel="stylesheet">

<#include "/common/messenger.script.ftl">
<#--密码强度样式-->
    <link rel="stylesheet" href="${ctx}/vendor/password/css/pwdStrength.css">
<#--显示密码-->
    <script src="${ctx}/vendor/password/js/password.js" type="text/javascript"></script>
<#--邮箱域名提示-->
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#id_reset').click(function () {
                if ($('#id_reset').is(':checked')) {
                    $('#password').removeAttr('readonly');
                } else {
                    $('#password').attr("readonly", "true");
                }
            });
        });
    </script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">修改应用管理员</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                &nbsp;
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="update_app_admin_form"
                              action="/backend/admin/app/${admin.ID?c}/update"
                              class="form-horizontal" role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">用户名</label>

                                <div class="col-lg-6">
                                    <p class="form-control-static">${admin.account!}</p>
                                    <input type="hidden" name="account" value="${admin.account!}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nick_name" class="col-lg-3 control-label">昵称</label>

                                <div class="col-lg-6">
                                    <input type="text" name="nickName" class="form-control" id="nick_name"
                                           placeholder="管理员昵称" value="${admin.nickName!}">
                                    <p class="help-block">请输入您的昵称，不超过16个汉字或32个字符</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-3 control-label">设置密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="password" class="form-control" id="password" readonly
                                           placeholder="设置管理员登录密码">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="id_reset" name="target" value="reset"> 重设
                                    </label>
                                    <p class="help-block">建议使用大、小字母加数字的组合</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-3 control-label">电子邮箱</label>

                                <div class="col-lg-6">
                                    <input type="text" name="email" class="form-control" id="email"
                                           value="${admin.email!}"
                                           placeholder="电子邮箱" list="input_list">
                                    <datalist id="input_list"></datalist>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="cellphone" class="col-lg-3 control-label">手机号码</label>

                                <div class="col-lg-4">
                                    <input type="text" name="cellphone" class="form-control" id="cellphone"
                                           value="${admin.phone!}"
                                           placeholder="手机号码">
                                </div>
                            </div>

                            <div class="form-group" id="role_part">
                                <label class="col-lg-3 control-label">分配应用</label>

                                <div class="col-lg-8" id="div_roles">

                                    <table id="apps_table"
                                           class="table table-bordered table-striped table-hover table-responsive">
                                        <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>应用Logo</th>
                                            <th>应用名称</th>
                                            <th>备注信息</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#list apps as app>
                                        <tr>
                                            <td><input type="checkbox" id="id_apps" name="apps"
                                                       value="${app.ID?c}" ${app.checked!}>
                                            </td>
                                            <td><img src="${app.logo!}" class="img-responsive img-rounded"></td>
                                            <td>${app.name!}</td>
                                            <td>${app.description!}</td>
                                        </tr>
                                        </#list>
                                        </tbody>
                                    </table>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <button type="submit" class="btn btn-primary btn-lg">保存信息</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#update_app_admin_form").bootstrapValidator({
            feedbackIcons: {
                /*input状态样式通过，刷新，非法三种图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitButtons: 'button[type="submit"]',//提交按钮
            fields: {
                nickName: {
                    validators: {
                        notEmpty: {//非空
                            message: '昵称不能为空'
                        },
                        stringLength: {//长度
                            min: 1,
                            max: 16,
                            message: '昵称长度不能超过16位'
                        },
                        regexp: {//字符正则匹配
                            regexp: /^[a-zA-Z0-9_.\u4e00-\u9fa5]+$/i,
                            message: '昵称由汉字、数字、字母、下划线或者小数点组成'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮件地址不能为空'
                        },
                        emailAddress: {
                            message: '请输入正确的邮件地址,如：123@qq.com'
                        }
                    }
                },
                cellphone: {
                    validators: {
                        notEmpty: {
                            message: '手机号码不能为空'
                        },
                        regexp: {
                            regexp: /^1[34578][0-9]{9}$/,
                            message: '请输入正确的11位手机号码，例如13123456789'
                        }
                    }
                }
            }
        });
        //显示密码
        $('#password').password().on('show.bs.password', function (e) {
        }).on('hide.bs.password', function (e) {
        });
        //显示隐藏help-block
        $("input").each(function (i) {
            $(this).focus(function () {
                $("input").eq(i).parent().find('p').css('display', 'none');
            }).blur(function () {
                $("input").eq(i).parent().find('p').css('display', 'block');
            });
        });
        $("#password").focus(function () {
            $(this).parent().parent().find('p').css('display', 'none');
        }).blur(function () {
            $(this).parent().parent().find('p').css('display', 'block');
        });
    });
</script>
</body>
</html>