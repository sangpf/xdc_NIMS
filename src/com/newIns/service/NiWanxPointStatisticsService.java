package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.newIns.model.NiWanxPointStatistics;

/**
 * @author lj
 * @Description : 玩校积分统计的Service接口
 * @time : 2016年8月25日 下午5:44:52
 */
public interface NiWanxPointStatisticsService {

	/**
	 * @author lj
	 * @Description : 得到玩校积分统计List
	 * @time : 2016年8月25日 下午7:32:24
	 * @param hashMap
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> getWanxPointStatisticsList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 得到导出玩校积分统计列表
	 * @time : 2016年8月26日 下午9:04:41
	 * @param date
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> getExportWanxPointStatisticsList(String date);
	

	/**
	 * @author lj
	 * @Description : 得到导出玩校积分统计列表的列名
	 * @time : 2016年8月26日 下午9:21:29
	 * @return LinkedHashMap<String, String> 
	 */
	public LinkedHashMap<String, String> getExcelColumnName();
	
	

	/**
	 * @author lj
	 * @Description : 玩校积分导出list编码转换
	 * @time : 2016年8月26日 下午9:50:32
	 * @param niWanxPointStatisticsList
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> codingConvertWanxPointStatisticsList(List<NiWanxPointStatistics> niWanxPointStatisticsList);
}
