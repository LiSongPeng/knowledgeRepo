package com.zh.framework.mapper;

import com.zh.framework.entity.ApprovalRecord;
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

    public void updateKnowledge(@Param("id") String id, @Param("kTitle") String kTitle, @Param("createUserId") String createUserId, @Param("kAnswer") String kAnswer);

    public void updateKnowledgeStatus(@Param("id") String id, @Param("status") String status);

    public void deleteKnowledge(@Param("id") String id);

    public void addKnowledge(Knowledge k);

    public void updateLastUseTime(@Param("time") Date time, @Param("id") String id);

    public List<Knowledge> queryAllKnowledge();

    public List<Knowledge> queryIndexableKnowledge();

    public List<Knowledge> querySomeKnowledge();

    public List<Knowledge> queryKnowledgeOrder(@Param("sidx")String sidx,@Param("sord") String sord );

    public void updateAppr(@Param("id") String id, @Param("kApprUserId") String kApprUserId, @Param("kApprMemo") String kApprMemo, @Param("kApprTime") Date kApprTime);

    public String queryUserNameById(@Param("userId") String userId);

    public List<Knowledge> search(@Param("seachKey") String seachKey,@Param("KeyValue") String KeyValue);

    public List<ApprovalRecord> queryAppr(@Param("kid") String kid);

    public void addAppr(@Param("id") String id,@Param("kid") String kid, @Param("aTime") Date aTime,@Param("bStatus") String bStatus, @Param("aStatus") String aStatus,@Param("ds") String ds);
   public List<Knowledge> queryByKtitle(@Param("kTitle") String kTitle);

}
