package com.zh.framework.util;

import com.github.pagehelper.PageInfo;
import com.zh.framework.entity.PageBean;

import java.util.Map;

/**
 * Created by Letg4 on 2017/9/4.
 */
public class PageInfoConvertor<T> {

    private PageInfo<T> pageInfo;

    public PageInfoConvertor(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageBean<T> toPageBean(){
        PageBean<T> pb=new PageBean<>();
        pb.setTotalPages(this.pageInfo.getPages());
        pb.setPageSize(this.pageInfo.getPageSize());
        pb.setTotalCounts(this.pageInfo.getTotal());
        pb.setCurrentPage(this.pageInfo.getPageNum());
        pb.setContent(this.pageInfo.getList());
        return pb;
    }
}
