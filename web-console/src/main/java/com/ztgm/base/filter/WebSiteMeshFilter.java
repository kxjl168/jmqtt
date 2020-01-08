package com.ztgm.base.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/manager/*", "/decorators/blueSkin/admin_index.ftl");
        
        
        //builder.addDecoratorPath("/manager/test", "/decorators/blueSkin/admin_index.ftl");
        
        
        builder.addDecoratorPath("/privilege/*", "/decorators/blueSkin/admin_index.ftl");
        builder.addDecoratorPath("/generator/*", "/decorators/blueSkin/admin_index.ftl");
        
        
        builder.addDecoratorPath("/ologin/*","/decorators/blueSkin/admin_index.ftl");
        builder.addDecoratorPath("/login.action", "/decorators/login_index.ftl");
        builder.addDecoratorPath("/", "/decorators/default_index.ftl");
        builder .addExcludedPath("/img/*")
                
                .addExcludedPath("/manager/community/map")
                .addExcludedPath("/manager/building/map")
               // .addExcludedPath("/manager/test*")
                
                .addExcludedPath("/interface/*")
                .addExcludedPath("/FileSvr/*")
                .addExcludedPath("/upload/*")
                
                .addExcludedPath("/js/*")
                .addExcludedPath("/vendor/*")
                .addExcludedPath("/lock/*");

    }
}
