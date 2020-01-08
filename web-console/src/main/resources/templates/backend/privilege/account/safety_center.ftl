<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>账号安全</title>
    <link href="${ctx}/vendor/messenger/css/messenger.css" rel="stylesheet">
<#include "/common/messenger.script.ftl">
<#--邮箱域名提示-->
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#btnPP').click(function () {
                var answer = $('#answer').val();
                if (answer == '') {
                    alert('密码保护问题不能为空！');
                    return false;
                }

                $('#password_protection_form').submit();
            });

        });
    </script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">账号安全</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                密码安全设置
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="admin_account_form" action="/backend/admin/account/info/update"
                              class="form-horizontal" role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">登录密码</label>

                                <div class="col-lg-6">
                                    <a href="${ctx}/backend/admin/account/password/modify"
                                       class="btn btn-sm btn-default">修改
                                    </a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-3 control-label">邮箱验证</label>

                                <div class="col-lg-6">
                                    <input type="email" name="email" class="form-control" id="email"
                                           placeholder="电子邮箱" value="${user.email!}" list="input_list">
                                    <datalist id="input_list"></datalist>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="phone" class="col-lg-3 control-label">手机验证</label>

                                <div class="col-lg-4">
                                    <input type="text" name="cellphone" class="form-control" id="phone"
                                           placeholder="手机号码" value="${user.phone!}">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <input type="hidden" name="target" value="safety">
                                    <button type="submit" class="btn btn-primary">更新信息</button>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">
                密码找回设置
            </div>
            <div class="panel-body">
            <#if user.passwordSafety??>
            <#else >
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    您还没有设置密码保护问题，为了您的账户安全，请马上设置
                </div>
            </#if>
                <form id="password_safety_form" action="/backend/admin/account/safety"
                      class="form-horizontal" role="form" method="POST">
                    <div class="form-group">
                        <label for="password_questions" class="col-lg-3 control-label">选择一个密码问题</label>

                        <div class="col-lg-6">
                            <select id="password_questions" name="question" class="form-control">
                                <option value="">------ 请选择子项目 ------</option>
                                <option value="1">你爱人的姓名是</option>
                                <option value="2">你出生在哪里</option>
                                <option value="3">你的第一辆车是</option>
                                <option value="4">你最喜欢的运动是</option>
                                <option value="5">你最喜欢电影是</option>
                                <option value="6">你上的小学是</option>
                            </select>

                        <#if user.passwordSafety??>
                            <input type="hidden" name="ID" id="psm_id" value="${user.passwordSafety.ID?c}">
                            <script type="text/javascript">
                                var qid = ${user.passwordSafety.questionID?c};
                                $("#password_questions").find("option").eq(qid).attr("selected", "selected");
                            </script>
                        </#if>

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="answer" class="col-lg-3 control-label">设置密码问题答案</label>

                        <div class="col-lg-6">
                            <input type="text" name="answer" class="form-control" id="answer"
                                   placeholder="******" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-3 col-lg-8">

                            <button type="submit" id="btnPP" class="btn btn-success"> <#if user.passwordSafety??>
                                更新问题及答案<#else >
                                保存问题及答案</#if></button>
                        </div>
                    </div>
                </form>
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