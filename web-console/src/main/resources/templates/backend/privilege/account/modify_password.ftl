<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>修改登录密码</title>
    <link href="${ctx}/vendor/messenger/css/messenger.css" rel="stylesheet">
<#include "/common/messenger.script.ftl">
<#--密码强度样式-->
    <link rel="stylesheet" href="${ctx}/vendor/password/css/pwdStrength.css">
<#--显示密码-->
    <script src="${ctx}/vendor/password/js/password.js" type="text/javascript"></script>
<#--密码强度js-->
    <script src="${ctx}/vendor/password/js/pwdStrength.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">修改登录密码</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                &nbsp;
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="modify_password_form"
                              action="/backend/admin/account/password/modify"
                              class="form-horizontal" role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">输入旧密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="oldPassword" class="form-control" id="oldPassword"
                                           placeholder="您的旧密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-3 control-label">输入新密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="您的新密码">
                                    <p class="help-block">建议使用大、小字母加数字的组合</p>
                                    <div id="strong">
                                        <div class="progress-bar" role="progressbar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="confirm_password" class="col-lg-3 control-label">确认新密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="confirm_password" class="form-control"
                                           id="confirm_password"
                                           placeholder="请再次输入新密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <button type="submit" class="btn btn-primary btn-lg">修改密码</button>
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
        $("#password").focus(function () {
            $(this).parent().parent().find('p').css('display', 'none');
        }).blur(function () {
            $(this).parent().parent().find('p').css('display', 'block');
        });

        $("#modify_password_form").bootstrapValidator({
            feedbackIcons: {
                /*input状态样式通过，刷新，非法三种图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitButtons: 'button[type="submit"]',//提交按钮
            fields: {
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度须在6到18之间'
                        },
//                        different: {//不能和用户名相同
//                            field: 'account',//需要进行比较的input name值
//                            message: '不能和用户名相同'
//                        },
                        callback: {
                            message: '密码强度太低',
                            callback: function () {
                                var $power = $(".progress-bar").text();
                                console.log($power);
                                if ($power == "" || $power == "极弱") {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    }
                },
                confirm_password: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {//相同
                            field: 'password',
                            message: '两次密码不一致'
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