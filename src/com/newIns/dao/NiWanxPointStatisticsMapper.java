package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiWanxPointStatistics;

/**
 * @author lj
 * @Description : 玩校积分统计的dao层
 * @time : 2016年8月25日 下午5:29:38
 */
public interface NiWanxPointStatisticsMapper {
	

	/**
	 * @author lj
	 * @Description : 根据发放奖励的方式得到玩校积分信息表
	 * @time : 2016年8月25日 下午5:37:11
	 * @param hashmap
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> selectWanxPointInfo(HashMap<String, Object> hashMap);
}
