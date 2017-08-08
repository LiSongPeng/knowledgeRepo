package com.zh.framework.entity;

import java.util.Date;

/**
 * Created by Letg4 on 2017/9/6.
 */
public class TreeGridData {
    private int level;
    private String parentid;
    private boolean isLeaf;
    private boolean loaded;
    private boolean expanded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsParentId() {
        return sParentId;
    }

    public void setsParentId(String sParentId) {
        this.sParentId = sParentId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsType() {
        return sType;
    }

    public void setsType(int sType) {
        this.sType = sType;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getsIcon() {
        return sIcon;
    }

    public void setsIcon(String sIcon) {
        this.sIcon = sIcon;
    }

    public int getsIndex() {
        return sIndex;
    }

    public void setsIndex(int sIndex) {
        this.sIndex = sIndex;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String id;
    private String sParentId;
    private String sName;
    private int sType;
    private String sUrl;
    private String sIcon;
    private int sIndex;
    private int deleteStatus;
    private User createUser;
    private Date createTime;

    public TreeGridData(Resource resource) {
        this.id=resource.getId();
        this.sParentId=resource.getsParentId();
        this.sName=resource.getsName();
        this.sType=resource.getsType();
        this.sUrl=resource.getsUrl();
        this.sIcon=resource.getsIcon();
        this.sIndex=resource.getsIndex();
        this.deleteStatus=resource.getDeleteStatus();
        this.createUser=resource.getCreateUser();
        this.createTime=resource.getCreateTime();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


}
