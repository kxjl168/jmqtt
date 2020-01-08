<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>密码找回成功</title>
<#--邮箱域名提示-->
<#--显示密码-->
    <script src="${ctx}/vendor/password/js/password.js" type="text/javascript"></script>
    <script src="${ctx}/vendor/util/js/emailHint.js" type="text/javascript"></script>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">密码找回成功</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                密码找回
            </div>
            <div class="panel-body">
                <form id="forget_password_form">
                    <div class="form-group">
                        <label for="account" class="col-lg-3 control-label">请记住您的新密码</label>

                        <div class="col-lg-4">
                            <input type="password" name="password" class="form-control" id="password"
                                   value="${randomPassword!}">
                            <p class="help-block">提示：点击右侧&nbsp;<i class="glyphicon glyphicon-eye-open  "></i>&nbsp;图标查看密码
                            </p>
                            <p class="help-block">为了您的账号安全，请立即登录并修改密码</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-3 col-lg-8">
                            <a href="${ctx}/backend/admin/account/password/modify"
                               class="btn btn-success">立即登录修改密码</a>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#password').password().on('show.bs.password', function (e) {
    }).on('hide.bs.password', function (e) {
    });
</script>

</body>
</html>