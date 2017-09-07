package com.zh.framework.entity;

/**
 * Created by Letg4 on 2017/9/6.
 */
public class TreeGridData<T> {
    private int level;
    private String parentid;
    private boolean isLeaf;
    private boolean loaded;
    private boolean expanded;
    private T treedata;

    public T getTreedata() {
        return treedata;
    }

    public void setTreedata(T treedata) {
        this.treedata = treedata;
    }

    public TreeGridData(T treedata) {
        this.treedata = treedata;
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
