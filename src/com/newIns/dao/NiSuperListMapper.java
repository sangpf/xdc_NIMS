/**
 * 
 */
package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.page.NiSuperListItem;
import com.newIns.model.page.NiSuperWanx;

/**
 * @Description 测一发列表dao层
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiSuperListMapper {
	
	/**
	 * @Title: loadSuperList
	 * @Author: Guan
	 * @Description: 加载测一发列表
	 * @return List<NiSuperListItem>
	 * @Time 2016年7月15日 下午1:30:20
	 */
	List<NiSuperListItem> loadSuperList(HashMap<String, Object> retMap);

	/**
	 * @Title: postSuperByids
	 * @Author: Guan
	 * @Description: 发布
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:42
	 */
	int postSuperByids(HashMap<String, Object> retMap);

	/**
	 * @Title: revokeSuperByids
	 * @Author: Guan
	 * @Description: 撤销
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:52
	 */
	int revokeSuperByids_super(HashMap<String, Object> retMap);

	/**
	 * @Title: topSuper
	 * @Author: Guan
	 * @Description: 置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:05
	 */
	void topSuper(HashMap<String, Object> retMap);

	/**
	 * @Title: topCancelSuper
	 * @Author: Guan
	 * @Description: 取消置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:16
	 */
	void topCancelSuper(HashMap<String, Object> retMap);

	/**
	 * 根据投放id查询问卷标题
	 */
	NiSuperListItem searchQnTitleById(HashMap<String, Object> retMap);

	/**
	 * @Title: searchSuperDeliveryFromList
	 * @Author: Guan
	 * @Description: 根据投放id查询投放表中对应的投放
	 * @param deliveryId
	 * @return NiSuperListItem
	 * @Time 2016年7月15日 下午1:36:25
	 */
	NiSuperListItem searchSuperDeliveryFromList(HashMap<String, Object> retMap);

	/**
	 * @Title: addSuperDeliveryToList
	 * @Author: Guan
	 * @Description: 将超级调查投放添加至列表
	 * @param superArg
	 *            void
	 * @Time 2016年7月15日 下午1:36:58
	 */
	void addSuperDeliveryToList(NiSuperListItem superArg);
	/**
	 * @Title: replaceSuperDelivery  
	 * @Author: Guan
	 * @Description: 在相同showorder位置替换一条投放
	 * @param superArg void
	 * @Time 2016年7月15日 下午1:38:37
	 */
	void replaceSuperDelivery(NiSuperListItem superArg);
	/**
	 * @Title: deleteSuperByIds  
	 * @Author: Guan
	 * @Description: 批量删除
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午2:33:47
	 */
	int deleteSuperByIds(HashMap<String, Object> retMap);
	
	void moveUpSuper(HashMap<String, Object> retMap);
	
	void moveDownSuper(HashMap<String, Object> retMap);

	//定时检测 待发布页面 
	void updateStateByTimer();
	
	//根据 投放id 问卷类型 设置页面状态为待发布,发布时间
	void updateStateById_timer(NiSuperWanx niSuperWanx);
}
