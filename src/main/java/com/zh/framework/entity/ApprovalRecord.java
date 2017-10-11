package com.zh.framework.entity;

import java.util.Date;

public class ApprovalRecord {

    public static final String INSERT_WAITING = "录入待审批";
    public static final String UPDATE_WAITING = "编辑待审批";
    public static final String APPROVED = "通过";
    public static final String UNAPPROVED = "不通过";
    public static final String DELETE_WAITING = "删除待审批";
    private String id;
    private String kid;
    private Date aTime;
    private String bStatus;
    private String aStatus;
    private String ds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public Date getaTime() {
        return aTime;
    }

    public void setaTime(Date aTime) {
        this.aTime = aTime;
    }

    public String getbStatus() {
        return bStatus;
    }

    public void setbStatus(String bStatus) {
        this.bStatus = bStatus;
    }

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
