<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>账号信息</title>
    <link href="${ctx}/vendor/messenger/css/messenger.css" rel="stylesheet">
<#include "/common/messenger.script.ftl">
<#--邮箱域名提示-->
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">账号信息</h1>
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
                        <form id="admin_account_form" action="/backend/admin/account/info/update"
                              class="form-horizontal" role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">用户名</label>

                                <div class="col-lg-6">
                                    <p class="form-control-static">${user.account!}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nickName" class="col-lg-3 control-label">昵称</label>

                                <div class="col-lg-4">
                                    <input type="text" name="nickName" class="form-control" id="nickName"
                                           placeholder="您的昵称" value="${(user.nickName)!''}">
                                    <p class="help-block"></p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="col-lg-3 control-label">电子邮箱</label>

                                <div class="col-lg-6">
                                    <input type="text" name="email" class="form-control" id="email"
                                           placeholder="电子邮箱" value="${user.email!}" list="input_list">
                                    <datalist id="input_list"></datalist>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cellphone" class="col-lg-3 control-label">手机号码</label>

                                <div class="col-lg-4">
                                    <input type="text" name="cellphone" class="form-control" id="cellphone"
                                           placeholder="手机号码" value="${user.phone!}">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <button type="submit" class="btn btn-primary btn-lg">更新信息</button>
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
        $("#admin_account_form").bootstrapValidator({
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
        //显示隐藏help-block
        $("input").each(function (i) {
            $(this).focus(function () {
                $("input").eq(i).parent().find('p').css('display', 'none');
            }).blur(function () {
                $("input").eq(i).parent().find('p').css('display', 'block');
            });
        });
    });
</script>
</body>
</html>