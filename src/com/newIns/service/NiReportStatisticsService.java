package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newIns.model.report.NiReportStatistics;
import com.newIns.model.report.NiReportStatisticsCodingConvert;

/**
 * @author lj
 * @Description : 报告统计的Service接口
 * @time : 2016年8月10日 下午12:59:47
 */
public interface NiReportStatisticsService {
	

	/**
	 * @author lj
	 * @Description : 根据条件得到报告统计的list
	 * @time : 2016年8月10日 下午1:03:26
	 * @param hashMap
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getReportStatisticsList(HashMap<String, Object> hashMap);
	
	

	/**
	 * @author lj
	 * @Description : 不选择渠道时得到不同渠道的list
	 * @time : 2016年8月10日 下午5:20:58
	 * @param niReportStatisticsList
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getDifferenetChannelList(List<NiReportStatistics> niReportStatisticsList);
	
	

	/**
	 * @author lj
	 * @Description : 根据不同的渠道得到对应的list
	 * @time : 2016年8月10日 下午6:19:27
	 * @param niReportStatisticsList
	 * @param channel
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getDifferenetChannelListByChannel(List<NiReportStatistics> niReportStatisticsList,int channel);
	

	/**
	 * @author lj
	 * @Description : 得到要导出的报告统计列表
	 * @time : 2016年8月10日 下午8:01:33
	 * @param reportId_arr
	 * @param channel_arr
	 * @param type_arr
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getExportReportStatisticsList(String reportId_str,String channel_str,String type_str);

	/**
	 * @author lj
	 * @Description : 得到报告统计导出列表的列名
	 * @time : 2016年8月10日 下午7:28:15
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getReportStatisticsExcelColumnName();
	
	

	/**
	 * @author lj
	 * @Description : 导出报告统计到excel时的编码转换
	 * @time : 2016年8月10日 下午8:52:59
	 * @param niReportStatisticsList
	 * @return List<NiReportStatisticsCodingConvert>
	 */
	public List<NiReportStatisticsCodingConvert> codingConvert(List<NiReportStatistics> niReportStatisticsList);
	
}
