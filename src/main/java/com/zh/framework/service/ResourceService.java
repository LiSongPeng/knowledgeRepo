package com.zh.framework.service;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.TreeGridData;

import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
public interface ResourceService {
    public List<TreeGridData> queryAsTree();
    public List<Resource> querySearch(Map<String,Object> param);
    public List<Resource> queryByUser(String userid);
    int checkRepeat(String column,String value);
    List<String> getResources(String roleId);
}
