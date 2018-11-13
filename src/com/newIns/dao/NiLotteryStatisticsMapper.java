package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiLotteryStatistics;

/**
 * @author lj
 * @Description : 抽奖统计的dao层接口
 * @time : 2016年8月16日 下午3:57:25
 */
public interface NiLotteryStatisticsMapper {
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校抽奖统计
	 * @time : 2016年8月16日 下午3:58:54
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectSurveyWanxLotteryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:00:06
	 * @param sqnId
	 * @return int 
	 */
	public int selectSurveyWanxLotteryValidOrderNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:00:49
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryTakePartNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:01:36
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward3ReceiveNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:02:20
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward2ReceiveNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:02:46
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward1ReceiveNum(Integer sqnId);
	
	
	
	/**
	 * @author lj
	 * @Description :  根据条件查询测评玩校抽奖统计
	 * @time : 2016年8月16日 下午4:03:45
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectAssessWanxLotteryList(HashMap<String, Object> hashMap);
	
	
	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:04:47
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryValidOrderNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖参与人数统计 
	 * @time : 2016年8月16日 下午4:05:27
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryTakePartNum(Integer aqnId);
	
	
	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:06:09
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward3ReceiveNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:06:42
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward2ReceiveNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:07:06
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward1ReceiveNum(Integer aqnId);
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校抽奖统计
	 * @time : 2016年8月16日 下午4:11:02
	 * @param hashMap
	 * @return  List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectVoteWanxLotteryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:11:56
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryValidOrderNum(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:12:42
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryTakePartNum(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖三等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:13:33
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward3ReceiveNum(Integer vqnId);

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖二等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:14:04
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward2ReceiveNum(Integer vqnId);

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖一等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:14:23
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward1ReceiveNum(Integer vqnId);
}
