package com.zh.framework.service;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.TreeGridData;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
public interface ResourceService {
    public List<TreeGridData<Resource>> queryAsTree();
    public List<Resource> querySearch(Map<String,Object> param);
}
