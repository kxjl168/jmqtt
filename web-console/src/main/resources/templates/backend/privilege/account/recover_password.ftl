<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>密码找回</title>
<#--邮箱域名提示-->
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">密码找回</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                密码找回
            </div>
            <div class="panel-body">
                <form id="forget_password_form" action="/backend/admin/account/password/recover"
                      class="form-horizontal" method="POST">
                    <div class="form-group">
                        <label for="account" class="col-lg-3 control-label">请输入您的用户名</label>

                        <div class="col-lg-6">
                            <input type="text" name="account" class="form-control" id="account"
                                   placeholder="您的用户名" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-lg-3 control-label">请输入您的电子邮箱</label>

                        <div class="col-lg-6">
                            <input type="text" name="email" class="form-control" id="email"
                                   placeholder="电子邮箱" list="input_list">
                            <datalist id="input_list"></datalist>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password_questions" class="col-lg-3 control-label">请选择您设定的密码问题</label>

                        <div class="col-lg-6">
                            <select id="password_questions" name="question" class="form-control">
                                <option value="">------ 请选择密码提示问题 ------</option>
                                <option value="1">你爱人的姓名是</option>
                                <option value="2">你出生在哪里</option>
                                <option value="3">你的第一辆车是</option>
                                <option value="4">你最喜欢的运动是</option>
                                <option value="5">你最喜欢电影是</option>
                                <option value="6">你上的小学是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="answer" class="col-lg-3 control-label">密码问题答案</label>

                        <div class="col-lg-6">
                            <input type="text" name="answer" class="form-control" id="answer"
                                   placeholder="密码问题答案" value="">
                            <div class="help-block">
                                请输入您设置的密码答案
                            </div>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-3 col-lg-8">
                            <button type="submit" class="btn btn-success">找回密码</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
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
        //表单验证
        $("#register_form").bootstrapValidator({
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
                            url: '/user/account/validate',//请求地址
                            delay: 1000,//每隔1秒发送ajax请求，减轻服务器压力
                            type: 'POST',//请求方式，默认get
                            message: '用户名已存在'//若json返回false则提示该信息
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮件地址不能为空'
                        },
                        emailAddress: {
                            message: '请输入正确的邮件地址,如：xx@xx.com'
                        }
                    }
                },
                answer: {
                    validators: {
                        notEmpty: {
                            message: '密码问题答案不能为空'
                        }
                    }
                }
            }
        });
        //显示密码
        $('#password').password().on('show.bs.password', function (e) {
        }).on('hide.bs.password', function (e) {
        });
    });
</script>

</body>
</html>