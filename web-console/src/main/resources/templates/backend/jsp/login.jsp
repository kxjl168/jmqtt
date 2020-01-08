<%--
 * @(#)
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>亨运通后台管理系统</title>
    <meta name="keywords" content="亨运通后台管理系统">
    <meta name="description" content="亨运通后台管理系统">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/login.css" rel="stylesheet">
    <style>

    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body>
<div class="logo_box">
<h1><span><img src="${ctx}/static/img/logo.png"></span>  亨运通后台管理</h1>
<form id="signupForm" action="${ctx}/login/signIn" method="post">
    <style type="text/css">
        :-webkit-autofill {
            -webkit-text-fill-color: #090909 !important;
            transition: background-color 5000s ease-in-out 0s;
        }


    </style>
    <div class="input_outer">
        <span class="fa fa-user fa-2x"></span>
        <input type="text" autocomplete="off" name="username" class="text" style=""  value="" placeholder="请输入用户名"/>
    </div>
    <div class="input_outer">
        <span class="fa fa-lock fa-2x"></span>
        <input type="password" autocomplete="off" name="password" class="text" style="" value="" placeholder="请输入密码"/>
        <c:if test="${msg != null}">
            <label id="password-error" class="error" for="password"><i class="fa fa-times-circle"></i> ${msg}</label>
        </c:if>
    </div>
    <div class="mb2"><button type="submit" class="act-but submit" >登录</button></div>
    <%--<input name="savesid" value="0" id="check-box" class="checkbox" type="checkbox"><span>记住用户名</span>--%>
</form>
<%--<a href="http://www.17sucai.com/preview/735710/2017-05-09/1/demo.html#" class="login-fgetpwd" style="color: #FFFFFF">忘记密码？</a>


<div class="sas">
    <a href="http://www.17sucai.com/preview/735710/2017-05-09/1/demo.html#">还没注册账号！</a>
</div>--%>

</div>
<%--<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            &lt;%&ndash;<div class="signin-info">
                <div class="logopanel m-b">
                    <h1>BootDo</h1>
                </div>
                <div class="m-b"></div>
                <h3>
                    欢迎使用 <strong>BootDo管理系统</strong>
                </h3>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                        springBoot
                    </li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                        thymeleaf
                    </li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                        bootstrap
                    </li>

                </ul>

            </div>&ndash;%&gt;
        </div>
        <div class="col-sm-5">
            <form id="signupForm" action="${ctx}/login/signIn" method="post">
                <h3 class="text-center">用户登录</h3>
                <p class="m-t-md text-center">欢迎登录吉汽出行后台管理系统</p>
                <div class="input_outer">
                    <span class="u_user"></span>
                    <input type="text" name="username" class="form-control uname"
                           value="" placeholder="请输入用户名"/>
                </div>
               &lt;%&ndash; <input type="text" name="username" class="form-control uname"
                       value="" placeholder="请输入用户名"/>&ndash;%&gt;
                <input type="password" name="password"
                       class="form-control pword m-b" value="" placeholder="请输入密码"/>
                <button class="btn btn-login btn-block">登录</button>
                <!--按钮模块-->
                <div class="outside-login">
                    <div class="outside-login-tit">
                        <span>

                        </span>
                    </div>
                    <div>
                        <c:if test="${msg != null}">
                            <i class='fa fa-times-circle' style="color: red;float:left;">${msg}</i>
                        </c:if>
                    </div>
                    <br>
                    <div class="outside-login-cot">
                        &lt;%&ndash;<a class="outside-login-btn wxoa actived oschina J-btnSwitchLoginType" target="_Blank"
                           href="">
                            <em><i class="fa fa-home"></i></em>
                            <span>oschina主页</span>
                        </a>
                        <a class="outside-login-btn wxoa actived my J-btnSwitchLoginType" target="_Blank"
                           href="">
                            <em><i class="fa fa-git-square"></i></em>
                            <span>码云仓库</span>
                        </a>
                        <a class="outside-login-btn wxoa actived git J-btnSwitchLoginType" target="_Blank"
                           href="">
                            <em><i class="fa fa-github"></i></em>
                            <span>GitHub仓库</span>
                        </a>&ndash;%&gt;
                    </div>
                </div>

            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">&copy; 2018 All Rights Reserved. 亨运通
        </div>
    </div>
</div>--%>
<%--<script th:inline="javascript"> var ctx = [[@{/}]] ; </script>--%>
<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4" th:src="@{/js/jquery.min.js?v=2.1.4}"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.6" th:src="@{/js/bootstrap.min.js?v=3.3.6}"></script>

<!-- 自定义js -->
<script src="${ctx}/static/js/content.js?v=1.0.0" th:src="@{/js/content.js?v=1.0.0}"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${ctx}/static/js/ajax-util.js"></script>
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js" th:src="@{/js/plugins/validate/jquery.validate.min.js}"></script>
<script src="${ctx}/static/js/plugins/validate/messages_zh.min.js" th:src="@{/js/plugins/validate/messages_zh.min.js}"></script>
<script src="${ctx}/static/js/plugins/layer/layer.min.js" th:src="@{/js/plugins/layer/layer.min.js}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        validateRule();
    });
    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#signupForm").validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: icon + "请输入您的用户名",
                },
                password: {
                    required: icon + "请输入您的密码",
                }
            }
        })
    }
</script>

</body>
</html>
