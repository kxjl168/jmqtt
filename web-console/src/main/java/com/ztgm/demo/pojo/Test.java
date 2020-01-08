package com.ztgm.demo.pojo;

public class Test {
    /**
     * 
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 平台根url
     */
    private String rootPath;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 图标 class
     */
    private String icons;

    /**
     * 上下线 1：上线，0 :离线
     */
    private String enable;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 平台根url
     * @return root_path 平台根url
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * 平台根url
     * @param rootPath 平台根url
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath == null ? null : rootPath.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 图标 class
     * @return icons 图标 class
     */
    public String getIcons() {
        return icons;
    }

    /**
     * 图标 class
     * @param icons 图标 class
     */
    public void setIcons(String icons) {
        this.icons = icons == null ? null : icons.trim();
    }

    /**
     * 上下线 1：上线，0 :离线
     * @return enable 上下线 1：上线，0 :离线
     */
    public String getEnable() {
        return enable;
    }

    /**
     * 上下线 1：上线，0 :离线
     * @param enable 上下线 1：上线，0 :离线
     */
    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }
}