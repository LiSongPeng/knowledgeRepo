package com.zh.framework.service;

import com.zh.framework.entity.Resource;
import com.zh.framework.entity.TreeGridData;
import com.zh.framework.mapper.ResourceMapper;
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

    public List<TreeGridData<Resource>> queryAsTree(){
        Map<String,Object> param=new HashMap<>();
        List<Resource> querylist= resourceMapper.query(param);
        List<TreeGridData<Resource>> totalList=new ArrayList<>();
        toTreeDataList(null,1,totalList);
        return totalList;
    }

    public List<Resource> querySearch(Map<String,Object> param){
        return resourceMapper.query(param);
    }

    private int toTreeDataList(String parentId,int level,List<TreeGridData<Resource>> totalList){
        List<Resource> list= resourceMapper.queryByPid(parentId);
        for(Resource res:list){
            TreeGridData<Resource> treedata= new TreeGridData<Resource>(res);
            treedata.setLevel(level);
            treedata.setExpanded(true);
            treedata.setLoaded(true);
            System.out.println(res.getsName()+"的level是："+level);
            treedata.setLeaf(toTreeDataList(res.getId(),level+1,totalList)==0);
            totalList.add(treedata);
        }
        return list.size();
    }
}
