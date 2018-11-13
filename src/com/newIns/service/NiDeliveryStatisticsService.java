package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newIns.model.NiDeliveryStatistics;
import com.newIns.model.NiDeliveryStatisticsCodingConvert;
import com.newIns.model.NiDeliveryStatisticsCodingConvert2;
import com.newIns.model.NiDeliveryStatisticsUserInfo;

/**
 * @author lj
 * @Description : 投放统计的Service接口
 * @time : 2016年8月2日 下午4:26:36
 */
public interface NiDeliveryStatisticsService {
	


	/**
	 * @author lj
	 * @Description : 根据条件查询整个投放统计
	 * @time : 2016年8月3日 上午11:13:39
	 * @param hashMap
	 * @return List<NiDeliveryStatistics> 
	 */
	public List<NiDeliveryStatistics> selectDeliveryStatisticsList(HashMap<String, Object> hashMap);
	
	
	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校投放统计
	 * @time : 2016年8月2日 下午4:27:21
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectSurveyWanxDeliveryList(HashMap<String, Object> hashMap);
	
	
	
	

	/**
	 * @author lj
	 * @Description : 根据投放id查询调查玩校投放统计
	 * @time : 2016年8月4日 上午10:48:04
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectSurveyWanxDeliveryByKey(Integer deliveryId);
	
	
	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找提交人数
	 * @time : 2016年8月2日 下午4:29:23
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxDeliveryCommit(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找完成人数
	 * @time : 2016年8月2日 下午4:29:54
	 * @param Integer
	 * @return int
	 */
	public int selectSurveyWanxDeliveryFinish(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找奖励领取人数
	 * @time : 2016年8月2日 下午4:30:19
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxDeliveryAward(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校投放统计
	 * @time : 2016年8月3日 下午2:25:33
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectAccessWanxDeliveryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据投放id查询测评玩校投放统计
	 * @time : 2016年8月4日 上午10:48:37
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectAccessWanxDeliveryByKey(Integer deliveryId);
	
	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午2:25:53
	 * @param aqnId
	 * @return int
	 */
	public int selectAccessWanxDeliveryCommit(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午2:26:16
	 * @param Integer
	 * @return int
	 */
	public int selectAccessWanxDeliveryFinish(Integer aqnId);
	
	

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午2:26:41
	 * @param aqnId
	 * @return int
	 */
	public int selectAccessWanxDeliveryAward(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校投放统计
	 * @time : 2016年8月3日 下午3:12:48
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectVoteWanxDeliveryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据投放id查询投票玩校投放统计
	 * @time : 2016年8月4日 上午10:49:05
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectVoteWanxDeliveryByKey(Integer deliveryId);
	
	
	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午3:13:08
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryCommit(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午3:13:27
	 * @param Integer
	 * @return int
	 */
	public int selectVoteWanxDeliveryFinish(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午3:13:48
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryAward(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据投放id,投放渠道,投放类型得到一个投放list
	 * @time : 2016年8月4日 上午11:02:11
	 * @param deliveryId_str
	 * @param channel_str
	 * @param checkType_str
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> getDeliveryStatisticsByKey(String deliveryId_str,String channel_str,String checkType_str);
	

	/**
	 * @author lj
	 * @Description : 得到要导出的表格的列名
	 * @time : 2016年8月4日 下午1:42:57
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExcelColumnName();
	

	/**
	 * @author lj
	 * @Description : 得到要下载的投放统计List(纵表格式，便于统计)
	 * @time : 2016年8月4日 下午6:25:36
	 * @param deliveryId
	 * @param answerChannel
	 * @param type
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> getDownloadDeliveryStatisticsList(String deliveryId_str,String answerChannel_str,String type_str);

	/**
	 * @author lijz
	 * @Description : 得到要下载的投放统计List（横表格式，便于查看）
	 * @param deliveryId
	 * @param answerChannel
	 * @param type
	 * @return List<NiDeliveryStatistics>
	 */
	public Map<String, Object> getDownloadDeliveryStatisticsList2(String deliveryId_str,String answerChannel_str,String type_str);

	/**
	 * @author lj
	 * @Description : 根据userId得到NiDeliveryStatisticsUserInfo对象进而包装到NiDeliveryStatistics对象中
	 * @time : 2016年8月9日 下午2:52:50
	 * @param niDeliveryStatistics
	 * @param userId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics getDeliveryStatisticsUserInfoByUserId(NiDeliveryStatistics niDeliveryStatistics,Integer userId);
	
	/**
	 * @author lj
	 * @Description : 得到下载答题明细时表格的列名
	 * @time : 2016年8月8日 下午5:17:32
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getDownLoadColumnName();

	/**
	 * @author lijz
	 * @param questionQty 
	 * @Description : 得到下载答题明细时表格的列名（横表格式）
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getDownLoadColumnName2(Integer questionQty);
	

	/**
	 * @author lj
	 * @Description : 导出excel时的编码转换
	 * @time : 2016年8月8日 下午9:37:39
	 * @param niDeliveryStatisticsList
	 * @return List<NiDeliveryStatisticsCodingConvert>
	 */
	public List<NiDeliveryStatisticsCodingConvert> codingConvert(List<NiDeliveryStatistics> niDeliveryStatisticsList);
	/**
	 * @author lijz
	 * @Description : 导出excel时的编码转换(横表显示)
	 * @param niDeliveryStatisticsList
	 * @return List<NiDeliveryStatisticsCodingConvert>
	 */
	public List<NiDeliveryStatisticsCodingConvert2> codingConvert2(List<NiDeliveryStatistics> niDeliveryStatisticsList) throws Exception;

	public void download_AnswerDetail(HttpServletRequest request, HttpServletResponse response);


}
