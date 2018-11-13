package com.newIns.dao.vote;

import com.newIns.model.vote.NiVoteDeliveryWanxExample;
import com.newIns.model.vote.VoteDelivery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface VoteDeliveryDao {
	
	//定时任务，判断当前收集份数大于目标份数
	int updateStatuByTagNum();
	
	//定时任务，判断当前时间大于截至时间
	int updateStatuByeTime();
	
	//批量修改状态
    int updateStatByMap(Map<String, Object> retMap);
	
	//列表查询
	List<VoteDelivery> selectList(Map<String, Object> retMap);
	
    int countByExample(NiVoteDeliveryWanxExample example);

    int deleteByExample(NiVoteDeliveryWanxExample example);

    int deleteByPrimaryKey(Integer deliveryid);

    int insert(VoteDelivery record);

    int insertSelective(VoteDelivery record);

    List<VoteDelivery> selectByExample(NiVoteDeliveryWanxExample example);
    
    List<VoteDelivery> selectByQnId(Integer id);

    VoteDelivery selectByPrimaryKey(Integer deliveryid);

    int updateByExampleSelective(@Param("record") VoteDelivery record, @Param("example") NiVoteDeliveryWanxExample example);

    int updateByExample(@Param("record") VoteDelivery record, @Param("example") NiVoteDeliveryWanxExample example);

    int updateByPrimaryKeySelective(VoteDelivery record);
    //生成命运表后改变投放表中的Lottery状态
    int updateLotteryReady(Integer deliveryId);

//    int updateByPrimaryKey(NiVoteDeliveryWanx record);
}