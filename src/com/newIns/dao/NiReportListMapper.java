/**
 * 
 */
package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.report.NiReport;
import com.newIns.model.report.NiReportListItem;

/**
 * @Description 测一发列表dao层
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiReportListMapper {
	/**
	 * @Title: loadReportList
	 * @Author: Guan
	 * @Description: 加载测一发列表
	 * @return List<NiReportListItem>
	 * @Time 2016年7月15日 下午1:30:20
	 */
	List<NiReportListItem> loadReportList(HashMap<String, Object> retMap);

	/**
	 * @Title: postReportByids
	 * @Author: Guan
	 * @Description: 发布
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:42
	 */
	int postReportByids(HashMap<String, Object> retMap);

	/**
	 * @Title: revokeReportByids
	 * @Author: Guan
	 * @Description: 撤销
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午1:30:52
	 */
	int revokeReportByids(HashMap<String, Object> retMap);

	/**
	 * @Title: topReport
	 * @Author: Guan
	 * @Description: 置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:05
	 */
	void topReport(HashMap<String, Object> retMap);

	/**
	 * @Title: topCancelReport
	 * @Author: Guan
	 * @Description: 取消置顶
	 * @param deliveryId
	 *            void
	 * @Time 2016年7月15日 下午1:31:16
	 */
	void topCancelReport(HashMap<String, Object> retMap);

	/**
	 * @Title: searchReportTitleById
	 * @Author: Guan
	 * @Description: 根据投放id查询问卷标题
	 * @param deliveryId
	 * @return NiReportListItem
	 * @Time 2016年7月15日 下午1:35:05
	 */
	NiReportListItem searchReportTitleById(int deliveryId);

	/**
	 * @Title: searchReportFromList
	 * @Author: Guan
	 * @Description: 根据投放id查询投放表中对应的投放
	 * @param deliveryId
	 * @return NiReportListItem
	 * @Time 2016年7月15日 下午1:36:25
	 */
	NiReportListItem searchReportFromList(int deliveryId);

	/**
	 * @Title: addReportToList
	 * @Author: Guan
	 * @Description: 将测评投放添加至列表
	 * @param reportArg
	 *            void
	 * @Time 2016年7月15日 下午1:36:58
	 */
	void addReportToList(NiReportListItem reportArg);
	/**
	 * @Title: replaceReport  
	 * @Author: Guan
	 * @Description: 在相同showorder位置替换一条投放
	 * @param reportArg void
	 * @Time 2016年7月15日 下午1:38:37
	 */
	void replaceReport(NiReportListItem reportArg);
	/**
	 * @Title: deleteReportByIds  
	 * @Author: Guan
	 * @Description: 批量删除
	 * @param retMap
	 * @return int
	 * @Time 2016年7月15日 下午2:33:47
	 */
	int deleteReportByIds(HashMap<String, Object> retMap);
	
	void moveUpReport(HashMap<String, Object> retMap);
	
	void moveDownReport(HashMap<String, Object> retMap);

	//根据报告id查询
	NiReport findByReportId(Integer id);
}
