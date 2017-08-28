package com.zh.framework.entity;

/**
 * created by lihuibo on 17-8-28 上午11:55
 */
public class KnowledgeIndex {
    private String id;
    private String kTitle;
    private String kAnswer;
    private String kUseCount;

    public KnowledgeIndex() {
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

    public String getkUseCount() {
        return kUseCount;
    }

    public void setkUseCount(String kUseCount) {
        this.kUseCount = kUseCount;
    }
}
