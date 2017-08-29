package com.zh.framework.mapper;

import com.zh.framework.entity.Knowledge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午9:59
 */
@Repository
public interface KnowledgeMapper {
    public List<Knowledge> queryKnowledgesAndSort(String orderBy);

    public Knowledge queryKnowledgeById(String id);

    public void updateUseCount(@Param("count") int count, @Param("id") String id);

    public void updateKnowledge(Knowledge k);

    public void updateKnowledgeStatus(@Param("id") String id, @Param("status") String status);

    public void deleteKnowledge(String id);

    public void addKnowledge(Knowledge k);
}
