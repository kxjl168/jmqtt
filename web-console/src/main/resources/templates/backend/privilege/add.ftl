<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>添加管理员</title>

<#--复选框样式-->
    <link href="${ctx}/vendor/util/skins/flat/blue.css" rel="stylesheet">
    <script src="${ctx}/vendor/util/js/icheck.min.js"></script>
<#--密码强度样式-->
    <link rel="stylesheet" href="${ctx}/vendor/password/css/pwdStrength.css">
<#--显示密码-->
    <script src="${ctx}/vendor/password/js/password.js" type="text/javascript"></script>
<#--密码强度js-->
    <script src="${ctx}/vendor/password/js/pwdStrength.js" type="text/javascript"></script>
<#--邮箱域名提示-->
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">添加管理员</h1>
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
                        <form id="add_admin_form" action="/backend/admin/system/add"
                              class="form-horizontal" role="form" method="POST">
                            <div class="form-group">
                                <label for="account" class="col-lg-3 control-label">用户名</label>

                                <div class="col-lg-6">
                                    <input type="text" name="account" class="form-control" id="account"
                                           placeholder="设置管理员账户名和登录名">
                                    <p class="help-block">请输入管理员账号名，不超过16个汉字或32个字符</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="nick_name" class="col-lg-3 control-label">昵称</label>

                                <div class="col-lg-6">
                                    <input type="text" name="nickName" class="form-control" id="nick_name"
                                           placeholder="管理员昵称">
                                    <p class="help-block">请输入您的昵称，不超过16个汉字或32个字符</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-3 control-label">设置密码</label>

                                <div class="col-lg-4">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="设置管理员登录密码">
                                    <p class="help-block">建议使用大、小字母加数字的组合</p>
                                    <div id="strong">
                                        <div class="progress-bar" role="progressbar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-3 control-label">电子邮箱</label>

                                <div class="col-lg-6">
                                    <input type="text" name="email" class="form-control" id="email"
                                           placeholder="电子邮箱" list="input_list">
                                    <datalist id="input_list"></datalist>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="cellphone" class="col-lg-3 control-label">手机号码</label>

                                <div class="col-lg-4">
                                    <input type="text" name="cellphone" class="form-control" id="cellphone"
                                           placeholder="手机号码">
                                </div>
                            </div>

                            <div class="form-group" id="role_part">
                                <label class="col-lg-3 control-label">分配管理员角色</label>

                                <div class="col-lg-8" id="div_roles">

                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="operator_admin" name="roles" value="16"> 系统管理员
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="audit_admin" name="roles" value="8"> 审计管理员
                                    </label>

                                    <p class="help-block">至少分配一个管理员角色</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <input type="hidden" name="token" value="${token!}">
                                    <button type="submit" class="btn btn-primary btn-lg">立即添加</button>
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
        $("#add_admin_form").bootstrapValidator({
            feedbackIcons: {
                /*input状态样式通过，刷新，非法三种图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            submitButtons: 'button[type="submit"]',//提交按钮
            fields: {
                account: {//验证账户
                    validators: {
                        notEmpty: {//非空
                            message: '用户名不能为空'
                        },
                        stringLength: {//长度
                            min: 1,
                            max: 16,
                            message: '用户名长度不能超过16位'
                        },
                        regexp: {//字符正则匹配
                            regexp: /^[a-zA-Z0-9_.\u4e00-\u9fa5]+$/i,
                            message: '用户名由汉字、数字、字母、下划线或者小数点组成'
                        },
                        remote: {//后台验证是否存在，返回json数据，正确：{"valid",true}
                            url: '/backend/admin/account/validate',//请求地址
                            delay: 1000,//每隔1秒发送ajax请求，减轻服务器压力
                            type: 'POST',//请求方式，默认get
                            message: '用户名已存在'//若json返回false则提示该信息
                        }
                    }
                },
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
                        different: {//不能和用户名相同
                            field: 'account',//需要进行比较的input name值
                            message: '不能和用户名相同'
                        },
                        callback: {
                            message: '密码强度太低',
                            callback: function () {
                                var $power = $(".progress-bar").text();
                                if ($power == "" || $power == "极弱") {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
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
                },
                roles: {
                    validators: {
                        notEmpty: {//至少分配一个管理员角色
                            message: ' '
                        }
                    }
                }
            }
        });
        //设置复选框样式
        $(':checkbox').iCheck({
            checkboxClass: 'icheckbox_flat-blue'
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