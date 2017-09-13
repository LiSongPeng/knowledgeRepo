package com.zh.framework.entity;



import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * created by lihuibo on 17-8-28 上午9:56
 */
public class Knowledge {
    public static final String INSERT_WAITING = "录入待审批";
    public static final String UPDATE_WAITING = "编辑待审批";
    public static final String APPROVED = "通过";
    public static final String UNAPPROVED = "不通过";
    public static final String DELETE_WAITING = "删除待审批";
    private String id;
    private String kTitle;
    private String kAnswer;
    private int kUseCount;
    private Date kUserTimeLast;
    private String kApprStatus;
    private String kApprUserId;
    private Date kApprTime;
    private String kApprMemo;
    private String createUserId;
    private Date createTime;

    public Knowledge() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getkTitle() {
        return kTitle;
    }

    public void setkTitle(String kTitle) {
        this.kTitle = kTitle;
    }

    public String getkAnswer() {
        return kAnswer;
    }

    public void setkAnswer(String kAnswer) {
        this.kAnswer = kAnswer;
    }

    public int getkUseCount() {
        return kUseCount;
    }

    public void setkUseCount(int kUseCount) {
        this.kUseCount = kUseCount;
    }

    public Date getkUserTimeLast() {
        return kUserTimeLast;
    }

    public void setkUserTimeLast(Date kUserTimeLast) {
        this.kUserTimeLast = kUserTimeLast;
    }

    public String getkApprStatus() {
        return kApprStatus;
    }

    public void setkApprStatus(String kApprStatus) {
        this.kApprStatus = kApprStatus;
    }

    public String getkApprUserId() {
        return kApprUserId;
    }

    public void setkApprUserId(String kApprUserId) {
        this.kApprUserId = kApprUserId;
    }

    public Date getkApprTime() {
        return kApprTime;
    }

    public void setkApprTime(Date kApprTime) {
        this.kApprTime = kApprTime;
    }

    public String getkApprMemo() {
        return kApprMemo;
    }

    public void setkApprMemo(String kApprMemo) {
        this.kApprMemo = kApprMemo;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Knowledge knowledge = (Knowledge) o;

        if (kUseCount != knowledge.kUseCount) return false;
        if (!id.equals(knowledge.id)) return false;
        if (!kTitle.equals(knowledge.kTitle)) return false;
        if (!kAnswer.equals(knowledge.kAnswer)) return false;
        if (!kUserTimeLast.equals(knowledge.kUserTimeLast)) return false;
        if (!kApprStatus.equals(knowledge.kApprStatus)) return false;
        if (kApprUserId != null ? !kApprUserId.equals(knowledge.kApprUserId) : knowledge.kApprUserId != null)
            return false;
        if (kApprTime != null ? !kApprTime.equals(knowledge.kApprTime) : knowledge.kApprTime != null) return false;
        if (kApprMemo != null ? !kApprMemo.equals(knowledge.kApprMemo) : knowledge.kApprMemo != null) return false;
        if (!createUserId.equals(knowledge.createUserId)) return false;
        return createTime.equals(knowledge.createTime);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + kTitle.hashCode();
        result = 31 * result + kAnswer.hashCode();
        result = 31 * result + kUseCount;
        result = 31 * result + kUserTimeLast.hashCode();
        result = 31 * result + kApprStatus.hashCode();
        result = 31 * result + (kApprUserId != null ? kApprUserId.hashCode() : 0);
        result = 31 * result + (kApprTime != null ? kApprTime.hashCode() : 0);
        result = 31 * result + (kApprMemo != null ? kApprMemo.hashCode() : 0);
        result = 31 * result + createUserId.hashCode();
        result = 31 * result + createTime.hashCode();
        return result;
    }
}
