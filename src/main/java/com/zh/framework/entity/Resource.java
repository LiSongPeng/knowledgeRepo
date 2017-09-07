package com.zh.framework.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Letg4 on 2017/8/30.
 */
public class Resource {
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

    public Resource(){}

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

}
