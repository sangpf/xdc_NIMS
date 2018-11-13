/**
 * 
 */
package com.newIns.dao.assess;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessListItem;
import com.newIns.model.assess.NiAssessWanx;

/**
 * @Description 测一发列表dao层
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiAssessListMapper {
	/**
	 *  加载测一发列表
	 */
	List<NiAssessListItem> loadAssessList(HashMap<String, Object> retMap);

	/**
	 */
	int postAssessByids(HashMap<String, Object> retMap);

	int revokeAssessByids(HashMap<String, Object> retMap);

	void topAssess(HashMap<String, Object> retMap);

	void topCancelAssess(HashMap<String, Object> retMap);

	NiAssessListItem searchQnTitleById(int deliveryId);

	NiAssessListItem searchAssessDeliveryFromList(HashMap map);

	void addAssessDeliveryToList(NiAssessListItem assessArg);
	/**
	 * @Title: replaceAssessDelivery  
	 * @Author: Guan
	 * @Description: 在相同showorder位置替换一条投放
	 * @param assessArg void
	 * @Time 2016年7月15日 下午1:38:37
	 */
	void replaceAssessDelivery(NiAssessListItem assessArg);
	/**
	 * @Title: deleteAssessByIds  
	 * @Author: Guan
	 * @Description: 批量删除
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午2:33:47
	 */
	int deleteAssessByIds(HashMap<String, Object> retMap);

	void moveUpAssess(HashMap<String, Object> retMap);
	
	void moveDownAssess(HashMap<String, Object> retMap);

	//保存定时任务  测评页面发布
	void updateStateById_timer(NiAssessWanx niAssessWanx);
	
	//定时检测 页面发布状态
	void updateStateByTimer();

	List<AssessDelivery> select_assessDelivey_key(Integer deliveryId);

	List<AssessDelivery> select_assessDelivey_likeName(
			String deliveryId_Name_str);
	
	
}
