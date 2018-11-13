package com.newIns.dao.survey;

import com.newIns.model.survery.NiSurveyDeliveryWanxExample;
import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.SurveyDelivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SurveyDeliveryDao {
	
	//定时任务，判断当前收集份数大于目标份数
	int updateStatuByTagNum();
	
	//定时任务，判断当前时间大于截至时间
	int updateStatuByeTime();
	
    int countByExample(NiSurveyDeliveryWanxExample example);

    int deleteByExample(NiSurveyDeliveryWanxExample example);

    int deleteByPrimaryKey(Integer deliveryid);

    int insert(SurveyDelivery record);

    int insertSelective(SurveyDelivery record);

    List<SurveyDelivery> selectByExample(NiSurveyDeliveryWanxExample example);

    SurveyDelivery selectByPrimaryKey(Integer deliveryid);
    
    List<NiSurveyDeliveryWanxVO> selectList(Map<String,Object> hashMap);
    
    List<NiSurveyDeliveryWanxVO> selectListByqnId(Integer id);

    int updateByExampleSelective(@Param("record") SurveyDelivery record, @Param("example") NiSurveyDeliveryWanxExample example);

    int updateByExample(@Param("record") SurveyDelivery record, @Param("example") NiSurveyDeliveryWanxExample example);

    int updateByPrimaryKeySelective(SurveyDelivery record);
    
    int updateStatuByIds(Map<String, Object> retMap);
    
    //生成命运表后改变投放表中的Lottery状态
    int updateLotteryReady(Integer deliveryId);
    
	List<NiSurveyDeliveryWanxVO> screenSurveyTemplatePage(HashMap<Object, Object> hashMap);

//    int updateByPrimaryKey(NiSurveyDeliveryWanx record);
}