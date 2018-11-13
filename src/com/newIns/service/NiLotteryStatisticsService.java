package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lj
 * @Description : 抽奖统计的Service接口
 * @time : 2016年8月16日 下午4:28:51
 */
import com.newIns.model.NiLotteryStatistics;

/**
 * @author lj
 * @Description : 抽奖统计的Service接口
 * @time : 2016年8月16日 下午4:24:21
 */
public interface NiLotteryStatisticsService {

	

	/**
	 * @author lj
	 * @Description : 根据条件得到抽奖统计List
	 * @time : 2016年8月16日 下午5:24:21
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> getLotteryStatisticsList(HashMap<String, Object> hashMap);
	
	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校抽奖统计
	 * @time : 2016年8月16日 下午4:25:08
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectSurveyWanxLotteryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:25:20
	 * @param sqnId
	 * @return int 
	 */
	public int selectSurveyWanxLotteryValidOrderNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:25:34
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryTakePartNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:25:48
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward3ReceiveNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:25:58
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward2ReceiveNum(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:26:07
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward1ReceiveNum(Integer sqnId);
	
	

	/**
	 * @author lj
	 * @Description :  根据条件查询测评玩校抽奖统计
	 * @time : 2016年8月16日 下午4:26:17
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectAssessWanxLotteryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:26:26
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryValidOrderNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖参与人数统计 
	 * @time : 2016年8月16日 下午4:26:34
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryTakePartNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:26:43
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward3ReceiveNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:26:51
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward2ReceiveNum(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:27:02
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward1ReceiveNum(Integer aqnId);
	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校抽奖统计
	 * @time : 2016年8月16日 下午4:27:11
	 * @param hashMap
	 * @return  List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectVoteWanxLotteryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:27:25
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryValidOrderNum(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:27:34
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryTakePartNum(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖三等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:27:45
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward3ReceiveNum(Integer vqnId);

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖二等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:27:59
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward2ReceiveNum(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖一等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:28:10
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward1ReceiveNum(Integer vqnId);

	/**
	 * @author lj
	 * @Description : 导出抽奖统计表格
	 * @time : 2016年8月16日 下午6:05:43
	 * @param deliveryId
	 * @param channel
	 * @param type
	 * @return List<NiLotteryStatistics> 
	 */
	public List<NiLotteryStatistics> exportLotteryStatisticsSheet(String deliveryId_str,String channel_str,String type_str);
	
	

	/**
	 * @author lj
	 * @Description : 导出抽奖统计时的编码转换
	 * @time : 2016年8月16日 下午6:58:32
	 * @param niLotteryStatisticsList
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> codingConvert(List<NiLotteryStatistics> niLotteryStatisticsList);
	


	/**
	 * @author lj
	 * @Description : 得到导出的抽奖统计excel列
	 * @time : 2016年8月16日 下午7:00:06
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExportLotteryStatisticsColumn();
}
