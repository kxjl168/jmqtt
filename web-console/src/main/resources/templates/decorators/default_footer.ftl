<#if request??>
    <#assign base=base>
<#else >
    <#assign base="">
</#if>
<div class="container">
    <footer class="footer col-lg-12 col-md-12" role="contentinfo">
        <div class="row">
            <div class="row about">
                <div class="col-lg-offset-1 col-lg-4">
                    <h4>服务热线</h4>
                    <ul class="list-unstyled">
                        <li>${(service.phone)!}</li>
                        <li>${(service.workingTime1)!}</li>
                        <li>${(service.workingTime2)!}</li>
                    </ul>
                </div>
                <div class="col-lg-4">
                    <h4>关于</h4>
                    <ul class="list-unstyled">
                    <#if aboutLinks??>
                        <#list aboutLinks as link>
                            <li><a href="${link.url!}" target="_blank">${link.name!}</a></li>
                        </#list>
                    </#if>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <h4>友情链接</h4>
                    <ul class="list-unstyled">
                    <#if siteLinks??>
                        <#list siteLinks as link>
                            <li><a href="${link.url!}" target="_blank">${link.name!}</a></li>
                        </#list>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row footer-bottom">
            <ul class="list-inline text-center">
                <li>${(copyright.copyright)!}</li>
            </ul>
        </div>
    </footer>
</div>