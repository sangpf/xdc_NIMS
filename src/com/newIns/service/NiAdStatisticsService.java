package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiAdStatistics;

/**
 * @Description 广告统计对应的Service接口
 * @author lj
 * @time 2016年7月27日 下午5:46:42
 */
public interface NiAdStatisticsService {
	
	/**
	 * @Description 根据搜索条件查询广告列表
	 * @author lj
	 * @param hashMap
	 * @return List<NiAdStatistics>
	 * @time 2016年7月27日 下午5:50:11
	 */
	public List<NiAdStatistics> selectList(HashMap<String, Object> hashMap);
}
