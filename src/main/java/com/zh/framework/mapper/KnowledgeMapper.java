package com.zh.framework.mapper;

import com.zh.framework.entity.Knowledge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午9:59
 */
@Repository
public interface KnowledgeMapper {
    public List<Knowledge> queryKnowledgesAndSort(@Param("orderBy") String orderBy, @Param("order") int order);

    public Knowledge queryKnowledgeById(String id);

    public void updateUseCount(@Param("count") int count, @Param("id") String id);

    //public void updateKnowledge(@Param("k")Knowledge k);

    public void updateKnowledge(@Param("id")String id,@Param("kTitle")String kTitle,@Param("createUserId")String createUserId,@Param("kAnswer")String kAnswer);

    public void updateKnowledgeStatus(@Param("id") String id, @Param("status") String status);

    public void deleteKnowledge(@Param("id")String id);

    public void addKnowledge(Knowledge k);

    public void updateLastUseTime(@Param("time") Date time, @Param("id") String id);

    public List<Knowledge> queryAllKnowledge();

    public List<Knowledge> querySomeKnowledge();

    public void updateAppr(@Param("id")String id,@Param("kApprUserId")String kApprUserId,@Param("kApprMemo")String kApprMemo,@Param("kApprTime")Date kApprTime);
}
