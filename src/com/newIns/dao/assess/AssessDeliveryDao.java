package com.newIns.dao.assess;

import java.util.List;
import java.util.Map;

import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessDeliveryWanxVo;

public interface AssessDeliveryDao {
	
	//定时任务，判断当前收集份数大于目标份数
	int updateStatuByTagNum();
	
	//定时任务，判断当前时间大于截至时间
	int updateStatuByeTime();

    int deleteByPrimaryKey(Integer deliveryid);

    int insert(AssessDelivery record);

    int insertSelective(AssessDelivery record);

    List<NiAssessDeliveryWanxVo> selectByQnid(Integer id);
    
    AssessDelivery selectByPrimaryKey(Integer deliveryid);
        
    //列表查询
    List<NiAssessDeliveryWanxVo> selectList(Map<String, Object> hashMap);
    
    int updateByPrimaryKeySelective(AssessDelivery record);

    int updateStatByMap(Map<String, Object> retMap);
    
    //生成命运表后改变投放表中的Lottery状态
    int updateLotteryReady(Integer deliveryId);

	void updateAssessResult_head_tail(AssessDelivery assessDelivery);
}