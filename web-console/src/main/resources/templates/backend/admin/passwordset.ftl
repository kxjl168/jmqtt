<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>密码管理</title>

    <link rel="stylesheet" type="text/css" href="/css/iot.css">

    <link rel="stylesheet" type="text/css" href="/vendor/page/admin/css/user2.css">

</head>

<body>


<div class="row">

    <div class="col-lg-12 wzbj">
        <div style="padding-top: 9px; float: left; padding-right: 4px;">
            <embed src="/img/zhuye.svg" type="image/svg+xml"></embed>
        </div>
        <h1 class="page-header">
            首页&nbsp;><span>&nbsp;密码修改</span>
        </h1>
    </div>
</div>

<div style="color: #333333;">

    <div class="mainbody">

    <form id="userForm" class="form-horizontal" role="form" action="/manager/admin/password/update" method="post">
        <input type="hidden" value="${telephone}" id="telephone" name="telephone">
        <div class="modal-body">
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-4">


                    <div class="form-group">
                        <label for="telephone" class="col-lg-3 control-label">原密码</label>

                        <div class="col-lg-9 passwordType">
                            <input type="password" name="originalPassword" class="form-control" id="originalPassword"
                                   placeholder="原密码"
                             >
                            <i class="fa fa-eye-slash"></i>
                            <p class="help-block"></p>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="name" class="col-lg-3 control-label">新密码</label>

                        <div class="col-lg-9 passwordType">
                            <input type="password" name="newPassword" class="form-control" id="newPassword" maxlength="16" placeholder="密码由6-16位大小写英文字母加数字组成！"
                                   placeholder="新密码"   onchange="checkPassWord($(this))" >
                            <i class="fa fa-eye-slash"></i>
                            <p class="help-block"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password1" class="col-lg-3 control-label">确认密码</label>

                        <div class="col-lg-9 passwordType">
                            <input type="password" name="confirmPassword" class="form-control" id="confirmPassword"
                                   placeholder="密码由6-16位大小写英文字母加数字组成！" maxlength="16"
                                   onchange="checkPassWord($(this))">
                            <i class="fa fa-eye-slash"></i>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-3">  </div>
                        <div class="col-lg-9">
                        <button type="button" class="btn btn-primary" id="btnSubmit">
                            提交更改
                        </button>
                        </div>
                    </div>

                </div>
                <div class="col-lg-3"></div>
            </div>
        </div>

    </form>
    </div>
</div>


<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/js/md5.js"></script>
<script src="/vendor/page/admin/js/user.js"></script>
</body>
</html>