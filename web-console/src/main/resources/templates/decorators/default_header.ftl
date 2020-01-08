<#if request??>
    <#assign base=request.contextPath>
<#else >
    <#assign base="">
</#if>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand active" href="${base!}/"><span class="glyphicon glyphicon-home"
                                                                  aria-hidden="true">&nbsp;${Application.config.getPlatformName()?default("时间戳服务管理平台")}<#include "../common/version.ftl"/></span></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>&nbsp;
                </li>
            </ul>
            <div class="navbar-right">
                <form method="post" action="" class="navbar-form">
                    <label class="control-label"
                           id="account"><#if user_session??>欢迎回来，<a href="/user/welcome"><span
                            class="glyphicon glyphicon-user"
                            aria-hidden="true"></span>${user_session.getAccount()!}</#if>
                    </a></label>&nbsp;
                <#if user_session??>
                    <a href="/login/frontend/logout" class="btn btn-danger btn-sm">退 出</a>
                <#else >
                    <a href="/login/frontend/index" class="btn btn-success btn-sm">登 录</a>
                    &nbsp;&nbsp;<a href="/user/register">免费注册</a>
                </#if>
                </form>
            </div>
        </div>
    </div>
</nav>
<div class="jumbotron home">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
        <#if (carousels?size>0)>
            <#list carousels as carousel>
                <li data-target="#carousel-example-generic" data-slide-to="${carousel_index}"
                    <#if carousel.active=="yes">class="active"</#if>></li>
            </#list>
        <#else>
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </#if>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
        <#if (carousels?size>0)>
            <#list carousels as carousel>
                <div class="item <#if carousel.active=="yes">active</#if>">
                    <a href="${carousel.url!}" class="link-center" target="_blank"><img
                            src="/img/carousel/${carousel.name!}" alt=""></a>
                </div>
            </#list>
        <#else>
            <div class="item active">
                <img data-src="holder.js/1200x400/auto/#777:#555/text:First slide" alt="First slide">
            </div>
            <div class="item">
                <img data-src="holder.js/1200x400/auto/#666:#444/text:Second slide" alt="Second slide">
            </div>
            <div class="item">
                <img data-src="holder.js/1200x400/auto/#555:#333/text:Third slide" alt="Third slide">
            </div>
        </#if>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>