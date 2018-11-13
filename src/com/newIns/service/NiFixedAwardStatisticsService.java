package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.newIns.model.NiFixedAwardStatistics;

/**
 * @author lj
 * @Description : 定奖统计的Service层接口
 * @time : 2016年8月11日 下午4:42:19
 */
public interface NiFixedAwardStatisticsService {
	
	
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校定奖统计
	 * @time : 2016年8月11日 下午4:43:01
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> selectSurveyWanxFixedAwardList(HashMap<String, Object> hashMap);
	
	


	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校定奖统计
	 * @time : 2016年8月11日 下午4:43:48
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectAssessWanxFixedAwardList(HashMap<String, Object> hashMap);
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校报告统计
	 * @time : 2016年8月11日 下午4:43:58
	 * @param hashMap
	 * @return  List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectVoteWanxFixedAwardList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 通过hashMap得到定奖统计列表
	 * @time : 2016年8月11日 下午5:09:19
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> getFixedAwardStatisticsList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 导出定奖统计表格
	 * @time : 2016年8月11日 下午7:03:58
	 * @param deliveryId
	 * @param channel
	 * @param type
	 * @return List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> exportFixedAwardStatisticsList(String deliveryId_str,String channel_str,String type_str);
	
	

	/**
	 * @author lj
	 * @Description : 得到导出的定奖excel列
	 * @time : 2016年8月11日 下午8:18:01
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExportFixedAwardStatisticsColumn();
		
	

	/**
	 * @author lj
	 * @Description : 导出表格编码转换
	 * @time : 2016年8月11日 下午8:34:38
	 * @param niFixedAwardStatisticsList
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> codingConvert(List<NiFixedAwardStatistics> niFixedAwardStatisticsList);
	
}
