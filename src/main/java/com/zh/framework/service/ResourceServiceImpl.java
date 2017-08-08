package com.zh.framework.service;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.Role;
import com.zh.framework.entity.TreeGridData;
import com.zh.framework.mapper.ResourceMapper;
import com.zh.framework.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Letg4 on 2017/9/6.
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public List<TreeGridData> queryAsTree() {
        Map<String, Object> param = new HashMap<>();
        List<Resource> querylist = resourceMapper.query(param);
        List<TreeGridData> totalList = new ArrayList<>();
        toTreeDataList(null, 1, totalList);
        return totalList;
    }

    public List<Resource> querySearch(Map<String, Object> param) {
        return resourceMapper.query(param);
    }

    public List<Resource> queryByUser(String userid) {
        return resourceMapper.queryByUser(userid);
    }

    @Override
    public List<String> getResources(String roleId) {
        List<Resource> list = resourceMapper.queryByRole(roleId);
        List<String> result = new ArrayList<>();
        for (Resource resource : list)
            result.add(resource.getId());
        return result;
    }

    private int toTreeDataList(String parentId, int level, List<TreeGridData> totalList) {
        List<Resource> list = resourceMapper.queryByPid(parentId);
        for (Resource res : list) {
            TreeGridData treedata = new TreeGridData(res);
            treedata.setLevel(level);
            treedata.setExpanded(true);
            treedata.setLoaded(true);
            totalList.add(treedata);
            treedata.setLeaf(toTreeDataList(res.getId(), level + 1, totalList) == 0);
        }
        return list.size();
    }
}
