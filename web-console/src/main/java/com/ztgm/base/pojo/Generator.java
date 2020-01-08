package com.ztgm.base.pojo;

import lombok.Data;

@Data
public class Generator {
    private String tableName;

    private String tableDisplayName;//表备注，显示名称
    
    
    private String tableSchema;//schema 
    
    private boolean create;


    private String Title;
    private boolean createPojo;
    private boolean createController;
    private boolean createPageAndJs;
    private boolean createService;

    public Generator() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isCreatePojo() {
        return createPojo;
    }

    public void setCreatePojo(boolean createPojo) {
        this.createPojo = createPojo;
    }

    public boolean isCreateController() {
        return createController;
    }

    public void setCreateController(boolean createController) {
        this.createController = createController;
    }

    public boolean isCreatePageAndJs() {
        return createPageAndJs;
    }

    public void setCreatePageAndJs(boolean createPageAndJs) {
        this.createPageAndJs = createPageAndJs;
    }

    public boolean isCreateService() {
        return createService;
    }

    public void setCreateService(boolean createService) {
        this.createService = createService;
    }
}
