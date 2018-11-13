package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.report.NiReportStatistics;

/**
 * @author lj
 * @Description : 报告统计的dao层接口
 * @time : 2016年8月10日 下午12:01:41
 */
public interface NiReportStatisticsMapper {


	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校报告统计 
	 * @time : 2016年8月10日 下午12:04:21
	 * @param hashMap
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> selectSurveyWanxReportList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校报告统计
	 * @time : 2016年8月10日 下午12:53:34
	 * @param hashMap
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> selectAccessWanxReportList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校报告统计
	 * @time : 2016年8月10日 下午12:54:24
	 * @param hashMap
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> selectVoteWanxReportList(HashMap<String, Object> hashMap);
	
}
