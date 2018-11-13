package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiFixedAwardStatistics;

/**
 * @author lj
 * @Description : 定奖统计的dao层接口
 * @time : 2016年8月11日 下午4:32:08
 */
public interface NiFixedAwardStatisticsMapper {
	
	
	
	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校定奖统计
	 * @time : 2016年8月11日 下午4:33:39
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> selectSurveyWanxFixedAwardList(HashMap<String, Object> hashMap);
	
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放定奖完成人数统计
	 * @time : 2016年8月11日 下午4:34:45
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxFixedAwardFinishNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放定奖领取人数统计
	 * @time : 2016年8月11日 下午4:35:27
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxFixedAwardReceiveNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校定奖统计
	 * @time : 2016年8月11日 下午4:36:17
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectAssessWanxFixedAwardList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放定奖完成人数统计
	 * @time : 2016年8月11日 下午4:37:04
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxFixedAwardFinishNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放定奖领取人数统计
	 * @time : 2016年8月11日 下午4:38:53
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxFixedAwardReceiveNum(Integer aqnId);
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校报告统计
	 * @time : 2016年8月11日 下午4:39:44
	 * @param hashMap
	 * @return  List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectVoteWanxFixedAwardList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放定奖完成人数统计
	 * @time : 2016年8月11日 下午4:40:27
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxFixedAwardFinishNum(Integer vqnId);
	
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放定奖领取人数统计
	 * @time : 2016年8月11日 下午4:41:02
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxFixedAwardReceiveNum(Integer vqnId);
}
