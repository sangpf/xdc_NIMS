package com.newIns.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.newIns.data_processing.po.Download_AnswerDetail;
import com.newIns.model.NiDeliveryStatistics;
import com.newIns.model.NiDeliveryStatisticsUserInfo;
import com.newIns.model.NiQuestionDescription;

/**
 * @author lj
 * @Description : 投放统计的dao接口
 * @time : 2016年8月2日 下午4:17:37
 */
public interface NiDeliveryStatisticsMapper {

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校投放统计
	 * @time : 2016年8月2日 下午4:20:52
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectSurveyWanxDeliveryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据调查问卷id得到所有答此问卷的用户答题详情
	 * @time : 2016年8月8日 下午2:55:26
	 * @param sqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectSurveyWanxAnswerInfoBySqnId(Integer sqnId);

	/**
	 * @author lijz
	 * @Description : 根据调查问卷id得到所有答此问卷的用户答题详情以及用户资料（用于横表显示）
	 * @param sqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectSurveyWanxAnswerAndUserInfoBySqnId(Integer sqnId);

	/**
	 * @author lj modifided by wanq 2016/10/24
	 * @Description : 根据调查题目id得到题目名称和选项描述
	 * @time : 2016年8月8日 下午2:58:48
	 * @param sqId
	 * @return NiQuestionDescription
	 */
	public NiQuestionDescription selectSurveyWanxSqTitle(Integer sqId);
	
	
	/**
	 * @author lj
	 * @Description : 根据投放id查询调查玩校投放统计
	 * @time : 2016年8月4日 上午10:42:34
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectSurveyWanxDeliveryByKey(@Param("deliveryId") Integer deliveryId);
	
	
	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找提交人数
	 * @time : 2016年8月2日 下午4:22:36
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxDeliveryCommit(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找完成人数
	 * @time : 2016年8月2日 下午4:24:41
	 * @param Integer
	 * @return int
	 */
	public int selectSurveyWanxDeliveryFinish(Integer sqnId);
	
	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找奖励领取人数
	 * @time : 2016年8月2日 下午4:25:32
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxDeliveryAward(Integer sqnId);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校投放统计
	 * @time : 2016年8月3日 下午2:21:40
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectAccessWanxDeliveryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据测评问卷id得到所有答此问卷的用户答题详情
	 * @time : 2016年8月9日 上午10:26:09
	 * @param aqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectAssessWanxAnswerInfoByAqnId(Integer aqnId);
	
	/**
	 * @author lijz
	 * @Description : 根据测评问卷id得到所有答此问卷的用户答题详情以及用户资料（用于横表显示）
	 * @time : 2016年8月9日 上午10:26:09
	 * @param aqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectAssessWanxAnswerAndUserInfoByAqnId(Integer aqnId);
	

	/**
	 * @author lj modified by wanq
	 * @Description : 根据测评题目id得到题目名称和选项描述
	 * @time : 2016年8月9日 上午10:27:13
	 * @param aqId
	 * @return NiQuestionDescription
	 */
	public NiQuestionDescription selectAssessWanxAqTitle(Integer aqId);
	
	
	/**
	 * @author lj
	 * @Description : 根据投放id查询测评玩校投放统计
	 * @time : 2016年8月4日 上午10:44:45
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectAccessWanxDeliveryByKey(@Param("deliveryId") Integer deliveryId);

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午2:22:56
	 * @param aqnId
	 * @return int
	 */
	int selectAccessWanxDeliveryCommit(Integer deliveryId);
	

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午2:23:41
	 * @param Integer
	 * @return int
	 */
	public int selectAccessWanxDeliveryFinish(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午2:24:32
	 * @param aqnId
	 * @return int
	 */
	public int selectAccessWanxDeliveryAward(Integer aqnId);
	

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校投放统计
	 * @time : 2016年8月3日 下午3:08:42
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectVoteWanxDeliveryList(HashMap<String, Object> hashMap);
	

	/**
	 * @author lj
	 * @Description : 根据投放id查询投票玩校投放统计
	 * @time : 2016年8月4日 上午10:46:23
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectVoteWanxDeliveryByKey(@Param("deliveryId") Integer deliveryId);

	

	/**
	 * @author lj
	 * @Description : 根据投票问卷id得到所有答此问卷的用户答题详情
	 * @time : 2016年8月9日 下午1:38:43
	 * @param vqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectVoteWanxAnswerInfoByVqnId(Integer vqnId);
	
	/**
	 * @author lijz
	 * @Description : 根据投票问卷id得到所有答此问卷的用户答题详情以及用户资料（用于横表显示）
	 * @param vqnId
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectVoteWanxAnswerAndUserInfoByVqnId(Integer vqnId);	
	
	/**
	 * @author lj modified by wanq
	 * @Description : 根据投票问卷id得到题目名称和选项描述[一个投票问卷对应一个题目]
	 * @time : 2016年8月9日 下午1:40:18
	 * @param vqnId
	 * @return NiQuestionDescription
	 */
	public NiQuestionDescription selectVoteWanxVqTitle(Integer vqnId);
	
	
	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午3:09:58
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryCommit(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午3:11:21
	 * @param Integer
	 * @return int
	 */
	public int selectVoteWanxDeliveryFinish(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午3:12:04
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryAward(Integer vqnId);
	

	/**
	 * @author lj
	 * @Description : 根据userId查询用户相关信息
	 * @time : 2016年8月8日 上午11:25:31
	 * @param userId
	 * @return NiDeliveryStatisticsUserInfo
	 */
	public NiDeliveryStatisticsUserInfo selectUserInfoByUserId(@Param("userId") Integer userId);
	
	public NiQuestionDescription selectSQuestionDesByQId(int sqId);
	
	public NiQuestionDescription selectAQuestionDesByQId(int aqId);
	
	public NiQuestionDescription selectVQuestionDesByQId(int vqnId);

	List<Download_AnswerDetail> download_AnswerDetail_survey(Integer deliveryId);

	public List<Download_AnswerDetail> download_AnswerDetail_assess(
			Integer deliveryId);

	public List<Download_AnswerDetail> download_AnswerDetail_vote(
			Integer deliveryId);
	
}
