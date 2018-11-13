package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiAdStatistics;


/**
 * @Description 广告统计对应的dao接口
 * @author lj
 * @time 2016年7月27日 下午5:38:12
 */
public interface NiAdStatisticsMapper {
	
	/**
	 * @Description [广告统计]根据条件返回的广告列表
	 * @author lj
	 * @param hashMap
	 * @return List<NiAdStatistics>
	 * @time 2016年7月27日 下午5:43:20
	 */
	public List<NiAdStatistics> selectList(HashMap<String, Object> hashMap); 
}
