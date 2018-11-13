/**
 * 
 */
package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.niDailyUpdateWanx.NiDailyUpdateWanx_VO_01;
import com.newIns.model.page.NiDaily3UpdateListItem;
import com.newIns.model.page.NiDaily3updateWanx;

/**
 * @Description 测一发列表dao层
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiDaily3UpdateListMapper {
	/**
	 * @Title: loadDaily3UpdateList
	 * @Author: Guan
	 * @Description: 加载测一发列表
	 * @return List<NiDaily3UpdateListItem>
	 * @Time 2016年7月15日 下午1:30:20
	 */
	List<NiDailyUpdateWanx_VO_01> loadDaily3UpdateList(HashMap<String, Object> retMap);

	/**
	 * @Title: postDaily3UpdateByids
	 * @Author: Guan
	 * @Description: 发布
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:42
	 */
	int postDaily3UpdateByids(HashMap<String, Object> retMap);

	/**
	 * @Title: revokeDaily3UpdateByids
	 * @Author: Guan
	 * @Description: 撤销
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:52
	 */
	int revokeDaily3UpdateByids(HashMap<String, Object> retMap);

	/**
	 * @Title: topDaily3Update
	 * @Author: Guan
	 * @Description: 置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:05
	 */
	void topDaily3Update(HashMap<String, Object> retMap);

	/**
	 * @Title: topCancelDaily3Update
	 * @Author: Guan
	 * @Description: 取消置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:16
	 */
	void topCancelDaily3Update(HashMap<String, Object> retMap);

	/**
	 * @Title: searchQnTitleById
	 * @Author: Guan
	 * @Description: 根据投放id查询问卷标题
	 * @param deliveryId
	 * @return NiDaily3UpdateListItem
	 * @Time 2016年7月15日 下午1:35:05
	 */
	NiDaily3UpdateListItem d3searchQnTitleById(HashMap<String, Object> retMap);

	/**
	 * @Title: searchDaily3UpdateDeliveryFromList
	 * @Author: Guan
	 * @Description: 根据投放id查询投放表中对应的投放
	 * @param deliveryId
	 * @return NiDaily3UpdateListItem
	 * @Time 2016年7月15日 下午1:36:25
	 */
	NiDaily3UpdateListItem searchDaily3UpdateDeliveryFromList(HashMap<String, Object> retMap);

	/**
	 * @Title: addDaily3UpdateDeliveryToList
	 * @Author: Guan
	 * @Description: 将超级调查投放添加至列表
	 * @param daily3updateArg
	 *            void
	 * @Time 2016年7月15日 下午1:36:58
	 */
	void addDaily3UpdateDeliveryToList(NiDaily3UpdateListItem daily3updateArg);
	void addDaily3UpdateDeliveryToList_new(NiDaily3UpdateListItem daily3updateArg);
	/**
	 * @Title: replaceDaily3UpdateDelivery  
	 * @Author: Guan
	 * @Description: 在相同showorder位置替换一条投放
	 * @param daily3updateArg void
	 * @Time 2016年7月15日 下午1:38:37
	 */
	void replaceDaily3UpdateDelivery(NiDaily3UpdateListItem daily3updateArg);
	/**
	 * @Title: deleteDaily3UpdateByIds  
	 * @Author: Guan
	 * @Description: 批量删除
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午2:33:47
	 */
	int deleteDaily3UpdateByIds(HashMap<String, Object> retMap);
	
	void moveUpDaily3Update(HashMap<String, Object> retMap);
	
	void moveDownDaily3Update(HashMap<String, Object> retMap);

	//联合主键查询
	NiDaily3updateWanx selectByPrimaryKey(HashMap<String, Object> retMap);

	//添加三更
	void insertDaily3UpdateDeliveryToList(NiDaily3UpdateListItem niDaily3UpdateListItem);

	//根据itemId修改showOrder
	void updateShowOrderByItemId(HashMap<String, Object> hashMap);

	//根据itemId查询
	NiDailyUpdateWanx_VO_01 findDaily3UpdatebyItemId(Integer itemId);

	//根据投放id  类型  栏目名称 查询
	List<NiDailyUpdateWanx_VO_01> findDaily3UpdateByMap(
			HashMap<String, Object> hashMap);

	//根据 报告查询 三更 是否存在
	List<NiDailyUpdateWanx_VO_01> findDaily3UpdatebyReport(HashMap<String, Object> hashMap);

	//查询推文是否在三更中存在
	List<NiDailyUpdateWanx_VO_01> findDaily3UpdatebyTweet(
			HashMap<String, Object> hashMap);

	//根据文章编辑三更
	void updateDaily3UpdatebyTweet(HashMap<String, Object> hashMap);

	//根据报告编辑
	void updateDaily3UpdatebyReport(HashMap<String, Object> hashMap);

	//根据投放编辑三更
	void updateDaily3UpdateByMap(HashMap<String, Object> hashMap);
	
	
}
