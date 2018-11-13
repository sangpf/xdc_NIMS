package com.newIns.service.imp;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.newIns.dao.NiDeliveryStatisticsMapper;
import com.newIns.data_processing.po.Download_AnswerDetail;
import com.newIns.data_processing.po.Download_AnswerDetail_user;
import com.newIns.model.NiDeliveryStatistics;
import com.newIns.model.NiDeliveryStatisticsCodingConvert;
import com.newIns.model.NiDeliveryStatisticsCodingConvert2;
import com.newIns.model.NiDeliveryStatisticsUserInfo;
import com.newIns.model.NiQuestionDescription;
import com.newIns.service.NiDeliveryStatisticsService;
import com.newIns.tools.ExcelException;
import com.newIns.tools.ExcelExportUtil;
import com.newIns.tools.StrUtils;

/**
 * @author lj
 * @Description : 投放统计的Service实现类
 * @time : 2016年8月2日 下午4:34:02
 */
@Service
public class NiDeliveryStatisticsServiceImp implements NiDeliveryStatisticsService {

	// 日志
	private static Logger log = Logger
			.getLogger(NiDeliveryStatisticsServiceImp.class);
	
	@Resource
	private NiDeliveryStatisticsMapper niDeliveryStatisticsMapper;

	/**
	 * @author lj
	 * @Description : 根据条件查询整个投放统计
	 * @time : 2016年8月3日 上午11:14:39
	 * @return List<NiDeliveryStatistics>
	 * @param hashmap
	 */
	public List<NiDeliveryStatistics> selectDeliveryStatisticsList(HashMap<String, Object> hashMap) {
		
		List<NiDeliveryStatistics> deliveryStatisticsList = new ArrayList<NiDeliveryStatistics>();
		// 投放统计搜索条件[投放渠道&问卷类型]
		String deliveryChannel = (String) hashMap.get("deliveryChannel");
		String selectDeliveryType = (String) hashMap.get("selectDeliveryType");
		
		// 考虑渠道和类型的组合情况[暂时不考虑微信和APP,后期迭代时直接在分情况考虑中加入微信和APP的List即可]
		if (StrUtils.isNotEmpty(deliveryChannel) && StrUtils.isEmpty(selectDeliveryType)) {
			// 输入渠道  , 没有问卷类型,三种问卷全部查询
			if (Integer.valueOf(deliveryChannel) == 1) { // 完美校园渠道
				// 调查
				deliveryStatisticsList.addAll(selectSurveyWanxDeliveryList(hashMap));
				// 测评
				deliveryStatisticsList.addAll(selectAccessWanxDeliveryList(hashMap));
				// 投票
				deliveryStatisticsList.addAll(selectVoteWanxDeliveryList(hashMap));
			}

		} else if (deliveryChannel == null && StrUtils.isNotEmpty(selectDeliveryType) ) {
			if (Integer.valueOf(selectDeliveryType) == 1) {
				// 调查
				deliveryStatisticsList.addAll(selectSurveyWanxDeliveryList(hashMap));
			} else if (Integer.valueOf(selectDeliveryType) == 2) {
				// 测评
				deliveryStatisticsList.addAll(selectAccessWanxDeliveryList(hashMap));
			} else if (Integer.valueOf(selectDeliveryType) == 3) {
				// 投票
				deliveryStatisticsList.addAll(selectVoteWanxDeliveryList(hashMap));
			}
		} else if (StrUtils.isNotEmpty(deliveryChannel) && StrUtils.isNotEmpty(selectDeliveryType)) {
			if (Integer.valueOf(deliveryChannel) == 1) {
				// 玩校渠道
				if (Integer.valueOf(selectDeliveryType) == 1) {
					// 调查
					deliveryStatisticsList.addAll(selectSurveyWanxDeliveryList(hashMap));
				} else if (Integer.valueOf(selectDeliveryType) == 2) {
					// 测评
					deliveryStatisticsList.addAll(selectAccessWanxDeliveryList(hashMap));
				} else if (Integer.valueOf(selectDeliveryType) == 3) {
					// 投票
					deliveryStatisticsList.addAll(selectVoteWanxDeliveryList(hashMap));
				}
			}
		} else if (StrUtils.isEmpty(deliveryChannel) && StrUtils.isEmpty(selectDeliveryType)) {
			log.info("------------>> 未输入投放渠道 , 未输入投放类型 : 查询全部 三种问卷类型 ... begin .... ");
			// 没有输入 渠道 和 类型 , 默认查询玩校渠道 , 三种问卷类型
			// 调查
			deliveryStatisticsList.addAll(selectSurveyWanxDeliveryList(hashMap));
			// 测评
			deliveryStatisticsList.addAll(selectAccessWanxDeliveryList(hashMap));
			// 投票
			deliveryStatisticsList.addAll(selectVoteWanxDeliveryList(hashMap));
		}
		return deliveryStatisticsList;
	}

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校投放统计
	 * @time : 2016年8月2日 下午4:35:30
	 * @param hashmap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectSurveyWanxDeliveryList(HashMap<String, Object> hashMap) {
		
		List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper
				.selectSurveyWanxDeliveryList(hashMap);
		
		// 对集合中的对象的其他属性赋值
		for (NiDeliveryStatistics niSurveyWanxDeliveryStatistics : niDeliveryStatisticsList) {
//			int sqnId = niSurveyWanxDeliveryStatistics.getQnId();
			int deliveryId = niSurveyWanxDeliveryStatistics.getDeliveryId();
			
			
			int commitNum = selectSurveyWanxDeliveryCommit(deliveryId);
			int awardNum = selectSurveyWanxDeliveryAward(deliveryId);
//			int finishNum = selectSurveyWanxDeliveryFinish(sqnId);
			
			niSurveyWanxDeliveryStatistics.setCommitNum(commitNum);
			
//			niSurveyWanxDeliveryStatistics.setFinishNum(finishNum);
			niSurveyWanxDeliveryStatistics.setAwardNum(awardNum);
			niSurveyWanxDeliveryStatistics.setType(1);
			niSurveyWanxDeliveryStatistics.setAnswerChannel(1);
			// 业务暂时不考虑答题人数answerNum[暂定为0]
			niSurveyWanxDeliveryStatistics.setAnswerNum(0);
		}
		return niDeliveryStatisticsList;
	}

	/**
	 * @author lj
	 * @Description : 根据投放id查询调查玩校投放统计
	 * @time : 2016年8月4日 上午10:52:00
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectSurveyWanxDeliveryByKey(Integer deliveryId) {
		// TODO Auto-generated method stub
		NiDeliveryStatistics selectSurveyWanxDelivery = niDeliveryStatisticsMapper
				.selectSurveyWanxDeliveryByKey(deliveryId);
		return selectSurveyWanxDelivery;
	}

	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找提交人数
	 * @time : 2016年8月2日 下午4:37:29
	 * @param int
	 * @return int
	 */
	public int selectSurveyWanxDeliveryCommit(Integer sqnId) {
		int commitNum = niDeliveryStatisticsMapper.selectSurveyWanxDeliveryCommit(sqnId);
		return commitNum;
	}

	/**
	 * @author lj
	 * @Description : 根据调查玩校投放id查找完成人数
	 * @param Integer
	 * @return int
	 * @time : 2016年8月2日 下午4:39:00
	 */
	public int selectSurveyWanxDeliveryFinish(Integer sqnId) {
		// TODO Auto-generated method stub
		int finishNum = niDeliveryStatisticsMapper
				.selectSurveyWanxDeliveryFinish(sqnId);
		return finishNum;
	}

	/**
	 * @author lj
	 * @return int
	 * @param sqnId
	 * @Description : 根据调查玩校投放id查找奖励领取人数
	 * @time : 2016年8月2日 下午4:40:41
	 */
	public int selectSurveyWanxDeliveryAward(Integer sqnId) {
		int awardNum = niDeliveryStatisticsMapper.selectSurveyWanxDeliveryAward(sqnId);
		return awardNum;
	}

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校投放统计
	 * @time : 2016年8月3日 下午2:28:04
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectAccessWanxDeliveryList(HashMap<String, Object> hashMap) {
		
		List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper
				.selectAccessWanxDeliveryList(hashMap);
		
		// 对集合中的对象的其他属性赋值
		for (NiDeliveryStatistics niAccessWanxDeliveryStatistics : niDeliveryStatisticsList) {
//			int aqnId = niAccessWanxDeliveryStatistics.getQnId();
//			int finishNum = selectAccessWanxDeliveryFinish(aqnId);
			int deliveryId = niAccessWanxDeliveryStatistics.getDeliveryId();
			
			int commitNum = selectAccessWanxDeliveryCommit(deliveryId);
			int awardNum = selectAccessWanxDeliveryAward(deliveryId);
			
			log.info("------------------->> commitNum :"+commitNum+", awardNum: "+awardNum);
			
			niAccessWanxDeliveryStatistics.setCommitNum(commitNum);
			niAccessWanxDeliveryStatistics.setAwardNum(awardNum);
			niAccessWanxDeliveryStatistics.setType(2);
//			niAccessWanxDeliveryStatistics.setFinishNum(finishNum);
//			niAccessWanxDeliveryStatistics.setAnswerChannel();
			// 业务暂时不考虑答题人数answerNum[暂定为0]
//			niAccessWanxDeliveryStatistics.setAnswerNum(0);
			
		}
		return niDeliveryStatisticsList;
	}

	/**
	 * @author lj
	 * @Description : 根据投放id查询测评玩校投放统计
	 * @time : 2016年8月4日 上午10:52:28
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectAccessWanxDeliveryByKey(Integer deliveryId) {
		// TODO Auto-generated method stub
		NiDeliveryStatistics selectAccessWanxDelivery = niDeliveryStatisticsMapper
				.selectAccessWanxDeliveryByKey(deliveryId);
		return selectAccessWanxDelivery;
	}

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午2:37:00
	 * @param aqnId
	 * @return int
	 */
	public int selectAccessWanxDeliveryCommit(Integer deliveryId) {
		int commitNum = niDeliveryStatisticsMapper.selectAccessWanxDeliveryCommit(deliveryId);
		log.info("---------------------->> 根据  deliveryId : "+deliveryId+" 查询订单表中总数  :" +commitNum);
		return commitNum;
	}

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午2:37:58
	 * @param Integer
	 * @return int
	 */
	public int selectAccessWanxDeliveryFinish(Integer aqnId) {
		int finishNum = niDeliveryStatisticsMapper.selectAccessWanxDeliveryFinish(aqnId);
		return finishNum;
	}

	/**
	 * @author lj
	 * @Description : 根据测评玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午2:38:19
	 * @param aqnId
	 * @return int
	 */
	public int selectAccessWanxDeliveryAward(Integer aqnId) {
		int awardNum = niDeliveryStatisticsMapper.selectAccessWanxDeliveryAward(aqnId);
		return awardNum;
	}

	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校投放统计
	 * @time : 2016年8月3日 下午3:14:40
	 * @param hashMap
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> selectVoteWanxDeliveryList(
			HashMap<String, Object> hashMap) {
		List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper.selectVoteWanxDeliveryList(hashMap);
		
		// 对集合中的对象的其他属性赋值
		for (NiDeliveryStatistics niVoteWanxDeliveryStatistics : niDeliveryStatisticsList) {
//			int vqnId = niVoteWanxDeliveryStatistics.getQnId();
			int deliveryId = niVoteWanxDeliveryStatistics.getDeliveryId();
			
//			int finishNum = selectVoteWanxDeliveryFinish(vqnId);
			int commitNum = selectVoteWanxDeliveryCommit(deliveryId);
			int awardNum = selectVoteWanxDeliveryAward(deliveryId);

			niVoteWanxDeliveryStatistics.setCommitNum(commitNum);
//			niVoteWanxDeliveryStatistics.setFinishNum(finishNum);
			niVoteWanxDeliveryStatistics.setAwardNum(awardNum);
			niVoteWanxDeliveryStatistics.setType(3);
			niVoteWanxDeliveryStatistics.setAnswerChannel(1);
			// 业务暂时不考虑答题人数answerNum[暂定为0]
			niVoteWanxDeliveryStatistics.setAnswerNum(0);
		}
		return niDeliveryStatisticsList;
	}

	/**
	 * @author lj
	 * @Description : 根据投放id查询投票玩校投放统计
	 * @time : 2016年8月4日 上午10:52:52
	 * @param deliveryId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics selectVoteWanxDeliveryByKey(Integer deliveryId) {
		// TODO Auto-generated method stub
		NiDeliveryStatistics selectVoteWanxDelivery = niDeliveryStatisticsMapper
				.selectVoteWanxDeliveryByKey(deliveryId);
		return selectVoteWanxDelivery;
	}

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找提交人数
	 * @time : 2016年8月3日 下午3:14:59
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryCommit(Integer vqnId) {
		int commitNum = niDeliveryStatisticsMapper.selectVoteWanxDeliveryCommit(vqnId);
		return commitNum;
	}

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找完成人数
	 * @time : 2016年8月3日 下午3:15:21
	 * @param Integer
	 * @return int
	 */
	public int selectVoteWanxDeliveryFinish(Integer vqnId) {
		// TODO Auto-generated method stub
		int finishNum = niDeliveryStatisticsMapper
				.selectVoteWanxDeliveryFinish(vqnId);
		return finishNum;
	}

	/**
	 * @author lj
	 * @Description : 根据投票玩校投放id查找奖励领取人数
	 * @time : 2016年8月3日 下午3:15:39
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxDeliveryAward(Integer vqnId) {
		int awardId = niDeliveryStatisticsMapper.selectVoteWanxDeliveryAward(vqnId);
		return awardId;
	}

	/**
	 * @author lj
	 * @Description : 根据投放id,投放渠道,投放类型得到一个投放list
	 * @time : 2016年8月4日 上午11:05:16
	 * @param deliveryId
	 * @param channel
	 * @param checkType
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> getDeliveryStatisticsByKey(
			String deliveryId_str, String channel_str, String checkType_str) {
		// TODO Auto-generated method stub
		String[] deliveryId_arr = deliveryId_str.split("!");
		String[] channel_arr = channel_str.split("!");
		String[] checkType_arr = checkType_str.split("!");
		List<NiDeliveryStatistics> niDeliveryStatisticsList = new ArrayList<NiDeliveryStatistics>();

		// 前端接收的参数字符串是逆序拼接的,所以采用每次减1的循环
		for (int i = deliveryId_arr.length - 1; i >= 0; i--) {
			if (channel_arr[i] != null && checkType_arr[i] != null) {
				if (Integer.valueOf(channel_arr[i]) == 1
						&& Integer.valueOf(checkType_arr[i]) == 1) {
					// 调查玩校
					int deliveryId = Integer.valueOf(deliveryId_arr[i]);

					NiDeliveryStatistics niDeliveryStatistics = selectSurveyWanxDeliveryByKey(deliveryId);
					// 补充对象中的其他属性进一步包装对象
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(1);
					int sqnId = niDeliveryStatistics.getQnId();
					niDeliveryStatistics
							.setCommitNum(selectSurveyWanxDeliveryCommit(sqnId));
					niDeliveryStatistics
							.setFinishNum(selectSurveyWanxDeliveryFinish(sqnId));
					niDeliveryStatistics
							.setAwardNum(selectSurveyWanxDeliveryAward(sqnId));
					// 业务暂时不考虑答题人数answerNum[暂定为0]
					niDeliveryStatistics.setAnswerNum(0);

					niDeliveryStatisticsList.add(niDeliveryStatistics);
				} else if (Integer.valueOf(channel_arr[i]) == 1
						&& Integer.valueOf(checkType_arr[i]) == 2) {
					// 测评玩校
					int deliveryId = Integer.valueOf(deliveryId_arr[i]);
					NiDeliveryStatistics niDeliveryStatistics = selectAccessWanxDeliveryByKey(deliveryId);
					// 补充对象中的其他属性进一步包装对象
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(2);
					int aqnId = niDeliveryStatistics.getQnId();
					niDeliveryStatistics
							.setCommitNum(selectAccessWanxDeliveryCommit(aqnId));
					niDeliveryStatistics
							.setFinishNum(selectAccessWanxDeliveryFinish(aqnId));
					niDeliveryStatistics
							.setAwardNum(selectAccessWanxDeliveryAward(aqnId));
					// 业务暂时不考虑答题人数answerNum[暂定为0]
					niDeliveryStatistics.setAnswerNum(0);

					niDeliveryStatisticsList.add(niDeliveryStatistics);
				} else if (Integer.valueOf(channel_arr[i]) == 1
						&& Integer.valueOf(checkType_arr[i]) == 3) {
					// 投票玩校
					int deliveryId = Integer.valueOf(deliveryId_arr[i]);
					NiDeliveryStatistics niDeliveryStatistics = selectVoteWanxDeliveryByKey(deliveryId);
					// 补充对象中的其他属性进一步包装对象
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(3);
					int vqnId = niDeliveryStatistics.getQnId();
					niDeliveryStatistics
							.setCommitNum(selectVoteWanxDeliveryCommit(vqnId));
					niDeliveryStatistics
							.setFinishNum(selectVoteWanxDeliveryFinish(vqnId));
					niDeliveryStatistics
							.setAwardNum(selectVoteWanxDeliveryAward(vqnId));
					// 业务暂时不考虑答题人数answerNum[暂定为0]
					niDeliveryStatistics.setAnswerNum(0);

					niDeliveryStatisticsList.add(niDeliveryStatistics);
				}
			}
		}

		return niDeliveryStatisticsList;
	}

	/**
	 * @author lj
	 * @Description : 得到要导出的表格的列名
	 * @time : 2016年8月4日 下午1:43:52
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExcelColumnName() {
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		fieldMap.put("deliveryId", "投放id");
		fieldMap.put("qnTitle", "问卷的名称");
		fieldMap.put("answerChannel", "问卷渠道");
		fieldMap.put("status", "投放状态");
		fieldMap.put("type", "问卷类型");
		fieldMap.put("answerNum", "答题人数");
		fieldMap.put("commitNum", "提交人数");
		fieldMap.put("finishNum", "完成人数");
		fieldMap.put("correctNum", "修正数");
		fieldMap.put("awardNum", "奖励领取人数");
		fieldMap.put("bTime", "投放开始时间");
		fieldMap.put("eTime", "投放结束时间");
		return fieldMap;
	}

	/**
	 * @author lj
	 * @Description : 得到要下载的投放统计List
	 * @time : 2016年8月4日 下午6:26:37
	 * @param deliveryId
	 * @param answerChannel
	 * @param type
	 * @return List<NiDeliveryStatistics>
	 */
	public List<NiDeliveryStatistics> getDownloadDeliveryStatisticsList(
			String deliveryId_str, String answerChannel_str, String type_str) {
		int deliveryId = 0;
		if (deliveryId_str != null) {
			deliveryId = Integer.valueOf(deliveryId_str);
		}
		// 创建一个新的list,用于存放分割完选择题答案之后存放
		List<NiDeliveryStatistics> newNiDeliveryStatisticsList = new ArrayList<NiDeliveryStatistics>();
		// 判断渠道和类型
		if (answerChannel_str != null && type_str != null) {
			int answerChannel = Integer.valueOf(answerChannel_str);
			int type = Integer.valueOf(type_str);
			if (answerChannel == 1 && type == 1) {
				// 玩校调查

				// 得到问卷相关信息[一个投放id对应一个问卷id]
				// status,answerNum;
				// commitNum,finishNum,correctNum,awardNum,qnTitle;
				
				long time0 = System.currentTimeMillis();
				
				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,sqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectSurveyWanxDeliveryByKey(deliveryId);
				
				long time1 = System.currentTimeMillis();
				log.info("<!-- 根据投放id查询调查玩校投放统计 -->  时间 : " + (time1 - time0));

				// 得到问卷id
				int sqnId = tempNiDeliveryStatistics.getQnId();

				int commitNum = niDeliveryStatisticsMapper
						.selectSurveyWanxDeliveryCommit(sqnId);
				
				long time2 = System.currentTimeMillis();
				log.info("<!-- 根据调查玩校投放id查找提交人数 -->  时间 : " + (time2 - time1));
				
				int finishNum = niDeliveryStatisticsMapper
						.selectSurveyWanxDeliveryFinish(sqnId);
				long time3 = System.currentTimeMillis();
				log.info("<!-- 根据调查玩校投放id查找完成人数 -->  时间 : " + (time3 - time2));
				
				
				int awardNum = niDeliveryStatisticsMapper
						.selectSurveyWanxDeliveryAward(sqnId);
				long time4 = System.currentTimeMillis();
				log.info("<!-- 根据调查玩校投放id查找奖励领取人数 -->  时间 : " + (time4 - time3));
				

				// 根据问卷id得到所有答此问卷的用户答题详情
				List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectSurveyWanxAnswerInfoBySqnId(sqnId);
				long time5 = System.currentTimeMillis();
				log.info("<!-- 根据问卷id得到所有答此问卷的用户答题详情 -->  时间 : " + (time5 - time4));
				

				// 分割选择题答案[二次封装成新的list]
				for (int i = 0; i < niDeliveryStatisticsList.size(); i++) {
					NiDeliveryStatistics niDeliveryStatistics = niDeliveryStatisticsList
							.get(i);
					String choice = niDeliveryStatistics.getChoice();
					if (choice != null && choice.trim().length() > 1) {
						// 多选
						// String []choice_arr= choice.trim().split("");
						char[] choice_arr = choice.toCharArray();
						for (int j = 0; j < choice_arr.length; j++) {
							NiDeliveryStatistics newNiDeliveryStatistics = new NiDeliveryStatistics();
							newNiDeliveryStatistics.setQnId(sqnId);
							newNiDeliveryStatistics
									.setUserId(niDeliveryStatistics.getUserId());
							newNiDeliveryStatistics.setqId(niDeliveryStatistics
									.getqId());
							newNiDeliveryStatistics.setChoice(choice_arr[j]
									+ "");
							newNiDeliveryStatisticsList
									.add(newNiDeliveryStatistics);
						}
					} else if (choice == null || choice.trim().length() == 1) {
						// 空或者单选
						newNiDeliveryStatisticsList.add(niDeliveryStatistics);
					}
				}
				long time6 = System.currentTimeMillis();
				log.info("<!-- for循环   分割选择题答案[二次封装成新的list] -->  时间 : " + (time6 - time5));

				int len = newNiDeliveryStatisticsList.size();

				log.info("=============>>对新的list进行其他属性的填充     list集合的长度为  :"+len);
				// 对新的list进行其他属性的填充
				for (int i = 0; i < len; i++) {
					NiDeliveryStatistics niDeliveryStatistics = newNiDeliveryStatisticsList
							.get(i);
					// 得到userId,sqId
					int userId = niDeliveryStatistics.getUserId();

					int sqId = niDeliveryStatistics.getqId();

//					<!-- 根据问题id查询问题信息  同名存储过程 -->
					NiQuestionDescription niQuestionDescription = niDeliveryStatisticsMapper
							.selectSurveyWanxSqTitle(sqId);
					
					if (niQuestionDescription != null) {
						// 根据sqId得到题目的title和optionDesc
						niDeliveryStatistics.setqTitle(niQuestionDescription
								.getqTitle());
						String choice = niDeliveryStatistics.getChoice();
						String optionDesc = null;
						if (choice != null && choice.length() == 1) {
							optionDesc = niQuestionDescription
									.getOptionByIndex(choice.charAt(0) - 'A' + 1);
						}
						niDeliveryStatistics.setOptionDesc(optionDesc);
					}
					// 判断是否有用户答过问卷
					if (userId != 0) {
						// 根据userId得到用户信息
						niDeliveryStatistics = getDeliveryStatisticsUserInfoByUserId(
								niDeliveryStatistics, userId);
					}
					// 填充问卷相关信息
					niDeliveryStatistics.setDeliveryId(deliveryId);
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(1);
					niDeliveryStatistics.setQnId(sqnId);
					niDeliveryStatistics.setStatus(tempNiDeliveryStatistics
							.getStatus());
					niDeliveryStatistics.setCorrectNum(tempNiDeliveryStatistics
							.getCorrectNum());
					niDeliveryStatistics.setQnTitle(tempNiDeliveryStatistics
							.getQnTitle());
					// answerNum答题人数暂不考虑,故先置为0
					niDeliveryStatistics.setAnswerNum(0);
					niDeliveryStatistics.setCommitNum(commitNum);
					niDeliveryStatistics.setFinishNum(finishNum);
					niDeliveryStatistics.setAwardNum(awardNum);
					log.info("===========>>总长度 :"+len+"----->已经执行进度  : 第 "+i +"轮");
				}
				long time7 = System.currentTimeMillis();
				log.info("<!-- for循环   对新的list进行其他属性的填充 -->  时间 : " + (time7 - time6));
//				<!-- for循环   对新的list进行其他属性的填充 -->  时间 : 51819  
				

			} else if (answerChannel == 1 && type == 2) {
				long time0 = System.currentTimeMillis();
				log.info("===============>>开始导出测评问卷答题明细数据");
				// 玩校测评
				// 得到问卷相关信息[一个投放id对应一个问卷id]
				// status,answerNum;
				// commitNum,finishNum,correctNum,awardNum,qnTitle;

				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,aqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectAccessWanxDeliveryByKey(deliveryId);
				
				long time1 = System.currentTimeMillis();
				log.info("===============>>根据投放id 查询 NiDeliveryStatistics   时间 : " +(time1 - time0));
				
				// 得到问卷id
				int aqnId = tempNiDeliveryStatistics.getQnId();

				int commitNum = niDeliveryStatisticsMapper
						.selectAccessWanxDeliveryCommit(aqnId);
				int finishNum = niDeliveryStatisticsMapper
						.selectAccessWanxDeliveryFinish(aqnId);
				int awardNum = niDeliveryStatisticsMapper
						.selectAccessWanxDeliveryAward(aqnId);
				
				long time2 = System.currentTimeMillis();
				log.info("===============>>根据测评问卷id 查询commitNum ,finishNum , awardNum   时间 : " +(time2 - time1));

				// 根据问卷id得到所有答此问卷的用户答题详情
				List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectAssessWanxAnswerInfoByAqnId(aqnId);
				
				long time3 = System.currentTimeMillis();
				log.info("===============>>根据问卷id得到所有答此问卷的用户答题详情   时间 : " +(time3 - time2));

				log.info("===============>> 查询出所有答此问卷的用户答题详情  的长度为  : " + niDeliveryStatisticsList.size());
				// 分割选择题答案[二次封装成新的list]
				for (int i = 0; i < niDeliveryStatisticsList.size(); i++) {
					if( i%1000 == 0){
						log.info("===============>>  查询出所有答此问卷的用户答题详情  的长度为  , 执行到的轮次  :"+i);
					}
					
					NiDeliveryStatistics niDeliveryStatistics = niDeliveryStatisticsList
							.get(i);
					String choice = niDeliveryStatistics.getChoice();
					if (choice != null && choice.trim().length() > 1) {
						// 多选
						//String[] choice_arr = choice.trim().split("");
						char[] choice_arr = choice.toCharArray();
						for (int j = 0; j < choice_arr.length; j++) {
							NiDeliveryStatistics newNiDeliveryStatistics = new NiDeliveryStatistics();
							newNiDeliveryStatistics.setQnId(aqnId);
							newNiDeliveryStatistics
									.setUserId(niDeliveryStatistics.getUserId());
							newNiDeliveryStatistics.setqId(niDeliveryStatistics
									.getqId());
							newNiDeliveryStatistics.setChoice(choice_arr[j]+"");
							newNiDeliveryStatisticsList
									.add(newNiDeliveryStatistics);
						}
					} else if (choice == null || choice.trim().length() == 1) {
						// 空或者单选
						newNiDeliveryStatisticsList.add(niDeliveryStatistics);
					}
					
				}
				
				long time4 = System.currentTimeMillis();
				log.info("===============>>分割选择题答案[二次封装成新的list]   时间 : " +(time4 - time3));

				int len = newNiDeliveryStatisticsList.size();

				log.info("===================>>对新的list进行其他属性的填充     集合长度"+len);
				// 对新的list进行其他属性的填充
				for (int i = 0; i < len; i++) {
					if( i%1000 == 0){
						log.info("===================>>对新的list进行其他属性的填充     执行到的轮次"+i);
					}
					
					NiDeliveryStatistics niDeliveryStatistics = newNiDeliveryStatisticsList.get(i);
					// 得到userId,aqId
					int userId = niDeliveryStatistics.getUserId();

					int aqId = niDeliveryStatistics.getqId();
					NiQuestionDescription niQuestionDescription = niDeliveryStatisticsMapper.selectAssessWanxAqTitle(aqId);
					if (niQuestionDescription != null) {
						// 根据aqId得到题目的title和选项描述optionDesc
						niDeliveryStatistics.setqTitle(niQuestionDescription.getqTitle());
						String choice = niDeliveryStatistics.getChoice();
						String optionDesc = null;
						if (choice != null && choice.length() == 1) {
							optionDesc = niQuestionDescription.getOptionByIndex(choice.charAt(0) - 'A' + 1);
						}
						niDeliveryStatistics.setOptionDesc(optionDesc);
					}
					// 判断是否有用户答过问卷
					if (userId != 0) {
						// 根据userId得到用户信息
						niDeliveryStatistics = getDeliveryStatisticsUserInfoByUserId(
								niDeliveryStatistics, userId);
					}
					// 填充问卷相关信息

					niDeliveryStatistics.setDeliveryId(deliveryId);
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(2);
					niDeliveryStatistics.setQnId(aqnId);
					niDeliveryStatistics.setStatus(tempNiDeliveryStatistics.getStatus());
					niDeliveryStatistics.setCorrectNum(tempNiDeliveryStatistics.getCorrectNum());
					niDeliveryStatistics.setQnTitle(tempNiDeliveryStatistics.getQnTitle());
					// answerNum答题人数暂不考虑,故先置为0
					niDeliveryStatistics.setAnswerNum(0);
					niDeliveryStatistics.setCommitNum(commitNum);
					niDeliveryStatistics.setFinishNum(finishNum);
					niDeliveryStatistics.setAwardNum(awardNum);
					
				}
				
				long time5 = System.currentTimeMillis();
				log.info("===============>>对新的list进行其他属性的填充  结束  共耗时 : " +(time5 - time4));

			} else if (answerChannel == 1 && type == 3) {
				// 玩校投票
				// 得到问卷相关信息[一个投放id对应一个问卷id]
				// status,answerNum;
				// commitNum,finishNum,correctNum,awardNum,qnTitle;

				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,vqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectVoteWanxDeliveryByKey(deliveryId);

				// 得到问卷id
				int vqnId = tempNiDeliveryStatistics.getQnId();

				int commitNum = niDeliveryStatisticsMapper
						.selectVoteWanxDeliveryCommit(vqnId);
				int finishNum = niDeliveryStatisticsMapper
						.selectVoteWanxDeliveryFinish(vqnId);
				int awardNum = niDeliveryStatisticsMapper
						.selectVoteWanxDeliveryAward(vqnId);

				// 根据问卷id得到所有答此问卷的用户答题详情
				List<NiDeliveryStatistics> niDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectVoteWanxAnswerInfoByVqnId(vqnId);

				// 分割选择题答案[二次封装成新的list]
				for (int i = 0; i < niDeliveryStatisticsList.size(); i++) {
					NiDeliveryStatistics niDeliveryStatistics = niDeliveryStatisticsList
							.get(i);
					String choice = niDeliveryStatistics.getChoice();

					if (choice != null && choice.trim().length() > 1) {
						// 多选
						//String[] choice_arr = choice.trim().split("");
						char[] choice_arr = choice.toCharArray();
						for (int j = 0; j < choice_arr.length; j++) {
							NiDeliveryStatistics newNiDeliveryStatistics = new NiDeliveryStatistics();
							newNiDeliveryStatistics.setQnId(vqnId);
							newNiDeliveryStatistics
									.setUserId(niDeliveryStatistics.getUserId());
							// 投票每个问卷只有一道题,故将问卷的每道题的id、题目设置和问卷的id、题目一样
							newNiDeliveryStatistics.setqId(vqnId);
							newNiDeliveryStatistics.setChoice(choice_arr[j]+"");
							newNiDeliveryStatisticsList
									.add(newNiDeliveryStatistics);
						}
					} else if (choice == null || choice.trim().length() == 1) {
						// 空或者单选
						niDeliveryStatistics.setqId(vqnId);
						newNiDeliveryStatisticsList.add(niDeliveryStatistics);
					}
				}

				int len = newNiDeliveryStatisticsList.size();

				// 对新的list进行其他属性的填充
				for (int i = 0; i < len; i++) {
					NiDeliveryStatistics niDeliveryStatistics = newNiDeliveryStatisticsList
							.get(i);
					// 得到userId
					int userId = niDeliveryStatistics.getUserId();
					NiQuestionDescription niQuestionDescription = niDeliveryStatisticsMapper
							.selectVoteWanxVqTitle(vqnId);
					if (niQuestionDescription != null) {
						// 根据vqnId得到题目的title[题目title和问卷]和optionDesc
						niDeliveryStatistics.setqTitle(niQuestionDescription
								.getqTitle());
						String choice = niDeliveryStatistics.getChoice();
						String optionDesc = null;
						if (choice != null && choice.length() == 1) {
							optionDesc = niQuestionDescription
									.getOptionByIndex(choice.charAt(0) - 'A' + 1);
						}
						niDeliveryStatistics.setOptionDesc(optionDesc);
					}
					// 判断是否有用户答过问卷
					if (userId != 0) {

						// 根据userId得到用户信息
						niDeliveryStatistics = getDeliveryStatisticsUserInfoByUserId(
								niDeliveryStatistics, userId);
					}
					// 填充问卷相关信息

					niDeliveryStatistics.setDeliveryId(deliveryId);
					niDeliveryStatistics.setAnswerChannel(1);
					niDeliveryStatistics.setType(3);
					niDeliveryStatistics.setQnId(vqnId);
					niDeliveryStatistics.setStatus(tempNiDeliveryStatistics
							.getStatus());
					niDeliveryStatistics.setCorrectNum(tempNiDeliveryStatistics
							.getCorrectNum());
					niDeliveryStatistics.setQnTitle(tempNiDeliveryStatistics
							.getQnTitle());
					// answerNum答题人数暂不考虑,故先置为0
					niDeliveryStatistics.setAnswerNum(0);
					niDeliveryStatistics.setCommitNum(commitNum);
					niDeliveryStatistics.setFinishNum(finishNum);
					niDeliveryStatistics.setAwardNum(awardNum);

				}

			}

		}
		// 返回list
		return newNiDeliveryStatisticsList;

	}

	/*
	 * @author: lijz	
	 * @name:getDownloadDeliveryStatisticsList2
	 * @Description : 得到所选问卷的答题信息导出（横表），需要再进行变换
	 * 
	 */
	public Map<String, Object> getDownloadDeliveryStatisticsList2(String deliveryId_str,String answerChannel_str, String type_str){
		
		HashMap<String, Object> dateMap = new HashMap<>();
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_str);
		Integer answerChannel = StrUtils.changeToInt(answerChannel_str);
		Integer type = StrUtils.changeToInt(type_str);
		
		int questionQty = 30;
		// 创建一个新的list,用于存放分割完选择题答案之后存放
		List<NiDeliveryStatistics> newNiDeliveryStatisticsList = new ArrayList<NiDeliveryStatistics>();
		// 判断渠道和类型
		
		if(answerChannel == 1){
			if(type == 1){
				// 玩校调查
				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,sqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectSurveyWanxDeliveryByKey(deliveryId);
				// 得到问卷id
				int sqnId = tempNiDeliveryStatistics.getQnId();
				questionQty = tempNiDeliveryStatistics.getQuestionQty();
				
				// 根据问卷id得到所有答此问卷的用户答题详情
				newNiDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectSurveyWanxAnswerAndUserInfoBySqnId(sqnId);
				
			}else if(type == 2){
				// 玩校测评
				// 得到问卷相关信息[一个投放id对应一个问卷id]
				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,aqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectAccessWanxDeliveryByKey(deliveryId);
				log.info("----------------测评问卷导出 ,-->>根据投放id查询投放信息和问卷信息 ... end ... ");
				
				// 得到问卷id
				int aqnId = tempNiDeliveryStatistics.getQnId();
				questionQty = tempNiDeliveryStatistics.getQuestionQty();  // 问题数量
				
				// 根据问卷id得到所有答此问卷的用户答题详情
				newNiDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectAssessWanxAnswerAndUserInfoByAqnId(aqnId);
				log.info("------------测评问卷导出 , 根据问卷id得到所有答此问卷的用户答题详情 ... end ... ");
				
			}else if(type == 3){
				// 玩校投票
				// 得到问卷相关信息[一个投放id对应一个问卷id]
				// tempNiDeliveryStatistics中有deliveryId,status,correctNum,qnTitle,vqnId信息
				NiDeliveryStatistics tempNiDeliveryStatistics = niDeliveryStatisticsMapper
						.selectVoteWanxDeliveryByKey(deliveryId);

				// 得到问卷id
				int vqnId = tempNiDeliveryStatistics.getQnId();
				questionQty = 1;
				
				// 根据问卷id得到所有答此问卷的用户答题详情
				newNiDeliveryStatisticsList = niDeliveryStatisticsMapper
						.selectVoteWanxAnswerAndUserInfoByVqnId(vqnId);
				
			}else{
				log.info("-------------------->> 导出问卷答题明细, 恒表, 无此问卷类型");
			}
		}else{
			log.info("------------------------>> 非玩校问卷类型, 答题信息导出恒表, 暂时未提供该功能");
		}
		
		if(newNiDeliveryStatisticsList!=null){
			log.info("======================>> 答题信息导出（横表） 查看模式  共有记录数为 :"+newNiDeliveryStatisticsList.size());
		}
		
		dateMap.put("questionQty", questionQty);
		dateMap.put("newNiDeliveryStatisticsList", newNiDeliveryStatisticsList);
		
		return dateMap;
	}
	
	/**
	 * @author lj
	 * @Description : 得到下载答题明细时表格的列名
	 * @time : 2016年8月8日 下午5:18:39
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getDownLoadColumnName() {
		// deliveryId,answerChannel,status,type,qnId,answerNum;
		// commitNum,finishNum,correctNum,awardNum;
		// qnTitle;

		// qId;
		// choice,qTitle;

		// userName,idCard,phone,email,wanxNickname,schoolName,regionName,userSn,wanxAccount;
		// userId,gender,wanxUserId,schoolId,regionId,role,bindCard,bindStudent,wanxScore,userCTime;

		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		fieldMap.put("deliveryId", "投放id");
		fieldMap.put("qnId", "问卷id");
		fieldMap.put("qnTitle", "问卷名称");
		fieldMap.put("answerChannel", "问卷渠道");
		// fieldMap.put("type", "问卷类型");
		// fieldMap.put("status", "投放状态");
		// fieldMap.put("answerNum", "答题人数");
		// fieldMap.put("commitNum", "提交人数");
		fieldMap.put("finishNum", "完成人数");
		// fieldMap.put("correctNum", "修正数");
		// fieldMap.put("awardNum", "奖励领取人数");

		fieldMap.put("userId", "用户id");
		// fieldMap.put("userName", "姓名");
		fieldMap.put("qId", "所答题目id");
		fieldMap.put("qTitle", "所答题目名称");
		fieldMap.put("choice", "所选答案");
		fieldMap.put("optionDesc", "答案描述");
		// 用户其他信息
		fieldMap.put("gender", "性别");
		// fieldMap.put("idCard", "身份证号");
		// fieldMap.put("phone", "手机号");
		// fieldMap.put("email", "电子邮箱");
		// fieldMap.put("userCTime", "用户来源时间");
		// fieldMap.put("wanxUserId", "玩校userid");
		// fieldMap.put("wanxNickname", "玩校昵称");
		// fieldMap.put("schoolId", "学校id");
		fieldMap.put("schoolName", "学校名称");
		// fieldMap.put("regionId", "地区id");
		fieldMap.put("regionName", "地区名称");
		fieldMap.put("province", "省份");
		fieldMap.put("regionCode", "地区码");
		// fieldMap.put("userSn", "学工号");
		fieldMap.put("role", "角色");
		// fieldMap.put("bindCard", "是否绑定一卡通");
		// fieldMap.put("bindStudent", "是否绑定学生");
		// fieldMap.put("wanxScore", "玩校积分");
		// fieldMap.put("wanxAccount", "玩校账户");

		return fieldMap;

	}

	
	/**
	 * @author lijz
	 * @Description : 得到下载答题明细时表格的列名(横表格式)
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getDownLoadColumnName2(Integer questionQty) {
		
		log.info("----------------->>得到下载答题明细时表格的列名(横表格式) 。。。。  begin ... ");
		
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		fieldMap.put("userId", "用户id");
		fieldMap.put("userName", "姓名");
		fieldMap.put("gender", "性别");
		fieldMap.put("schoolName", "学校");
		fieldMap.put("province", "省份");
		fieldMap.put("regionName", "地区名称");
		fieldMap.put("regionCode", "地区码");
		fieldMap.put("role", "用户角色");
		
		for(int i=1 ; i<=questionQty ; i++){
			
			fieldMap.put("question"+i, "第"+i+"题");
			
		}
		
		log.info("----------------->>得到下载答题明细时表格的列名(横表格式) 。。。。  end ... ");
		return fieldMap;

	}
	
	/**
	 * @author lj
	 * @Description : 导出excel时的编码转换
	 * @time : 2016年8月8日 下午9:38:39
	 * @param niDeliveryStatisticsList
	 * @return List<NiDeliveryStatisticsCodingConvert>
	 */
	public List<NiDeliveryStatisticsCodingConvert> codingConvert( List<NiDeliveryStatistics> niDeliveryStatisticsList) {
		
		List<NiDeliveryStatisticsCodingConvert> niDeliveryStatisticsCodingConvertsList = new ArrayList<NiDeliveryStatisticsCodingConvert>();
		
		// deliveryId,qnTitle,answerChannel,status,type,answerNum,commitNum,finishNum,correctNum,awardNum;
		// answerChannel->玩校,微信,APP->1,2,3
		// status->1待投放；2投放中；3暂停中；4人工终止；5时间完成；6数量完成
		// type->调查,测评,投票->1,2,3
		for (int i = 0; i < niDeliveryStatisticsList.size(); i++) {
			NiDeliveryStatisticsCodingConvert niDeliveryStatisticsCodingConvert = new NiDeliveryStatisticsCodingConvert();
			NiDeliveryStatistics niDeliveryStatistics = niDeliveryStatisticsList
					.get(i);
			niDeliveryStatisticsCodingConvert.setDeliveryId(String
					.valueOf(niDeliveryStatistics.getDeliveryId()));
			niDeliveryStatisticsCodingConvert.setQnId(String
					.valueOf(niDeliveryStatistics.getQnId()));
			niDeliveryStatisticsCodingConvert.setQnTitle(niDeliveryStatistics
					.getQnTitle());
			switch (niDeliveryStatistics.getAnswerChannel()) {
			case 1:
				niDeliveryStatisticsCodingConvert.setAnswerChannel("玩校");
				break;
			case 2:
				niDeliveryStatisticsCodingConvert.setAnswerChannel("微信");
				break;
			case 3:
				niDeliveryStatisticsCodingConvert.setAnswerChannel("APP");
				break;
			default:
				break;
			}
			
			/***************************************************************/
			 switch (niDeliveryStatistics.getStatus()) {
			 case 1:
			 niDeliveryStatisticsCodingConvert.setStatus("待投放");
			 break;
			 case 2:
			 niDeliveryStatisticsCodingConvert.setStatus("投放中");
			 break;
			 case 3:
			 niDeliveryStatisticsCodingConvert.setStatus("暂停中");
			 break;
			 case 4:
			 niDeliveryStatisticsCodingConvert.setStatus("人工终止");
			 break;
			 case 5:
			 niDeliveryStatisticsCodingConvert.setStatus("时间完成");
			 break;
			 case 6:
			 niDeliveryStatisticsCodingConvert.setStatus("数量完成");
			 break;
			 default:
			 break;
			 }
			 switch (niDeliveryStatistics.getType()) {
			 case 1:
			 niDeliveryStatisticsCodingConvert.setType("调查");
			 break;
			 case 2:
			 niDeliveryStatisticsCodingConvert.setType("测评");
			 break;
			 case 3:
			 niDeliveryStatisticsCodingConvert.setType("投票");
			 break;
			 default:
			 break;
			 }
//			 答题人数未实现该功能
			 switch (niDeliveryStatistics.getAnswerNum()) {
			 case 0:
			 niDeliveryStatisticsCodingConvert.setAnswerNum("未实现该功能");
			 break;
			 default:
			 break;
			 }
			/**************************************************************************/

			niDeliveryStatisticsCodingConvert.setCommitNum(String.valueOf(niDeliveryStatistics.getCommitNum()));
			niDeliveryStatisticsCodingConvert.setFinishNum(String
					.valueOf(niDeliveryStatistics.getFinishNum()));
			 niDeliveryStatisticsCodingConvert.setCorrectNum(String.valueOf(niDeliveryStatistics.getCorrectNum()));
			 niDeliveryStatisticsCodingConvert.setAwardNum(String.valueOf(niDeliveryStatistics.getAwardNum()));

			niDeliveryStatisticsCodingConvert.setqId(String
					.valueOf(niDeliveryStatistics.getqId()));
			niDeliveryStatisticsCodingConvert.setChoice(niDeliveryStatistics
					.getChoice());
			niDeliveryStatisticsCodingConvert.setqTitle(niDeliveryStatistics
					.getqTitle());

			// 用户所有相关信息
			// userName,idCard,phone,email,wanxNickname,schoolName,regionName,userSn,wanxAccount;
			// userId,gender,wanxUserId,schoolId,regionId,role,bindCard,bindStudent,wanxScore;
			// userCTime;
			// gender->1男,2女;role->1学生；2教职工；3商户；4游客 (玩校_role);bindCard->0未绑定，1绑定
			// (玩校_bind_card)
			// bindStudent->0未绑定，1绑定 (玩校_bind_stu)

			niDeliveryStatisticsCodingConvert.setUserId(String
					.valueOf(niDeliveryStatistics.getUserId()));
			Timestamp userCTime = niDeliveryStatistics.getUserCTime();
			if (userCTime != null) {
				// niDeliveryStatisticsCodingConvert.setUserCTime(dateFormat.format(userCTime));
			}

			switch (niDeliveryStatistics.getGender()) {
			case 1:
				niDeliveryStatisticsCodingConvert.setGender("男");
				break;
			case 2:
				niDeliveryStatisticsCodingConvert.setGender("女");
				break;
			default:
				break;
			}
			// niDeliveryStatisticsCodingConvert.setUserName(niDeliveryStatistics.getUserName());
			// niDeliveryStatisticsCodingConvert.setIdCard(String.valueOf(niDeliveryStatistics.getIdCard()));
			// niDeliveryStatisticsCodingConvert.setPhone(niDeliveryStatistics.getPhone());
			// niDeliveryStatisticsCodingConvert.setEmail(niDeliveryStatistics.getEmail());
			// niDeliveryStatisticsCodingConvert.setWanxNickname(niDeliveryStatistics.getWanxNickname());
			niDeliveryStatisticsCodingConvert
					.setSchoolName(niDeliveryStatistics.getSchoolName());
			niDeliveryStatisticsCodingConvert
					.setRegionName(niDeliveryStatistics.getRegionName());
			// niDeliveryStatisticsCodingConvert.setUserSn(niDeliveryStatistics.getUserSn());
			// niDeliveryStatisticsCodingConvert.setWanxAccount(niDeliveryStatistics.getWanxAccount());
			// niDeliveryStatisticsCodingConvert.setWanxUserId(String.valueOf(niDeliveryStatistics.getWanxUserId()));
			// niDeliveryStatisticsCodingConvert.setSchoolId(String.valueOf(niDeliveryStatistics.getSchoolId()));
			// niDeliveryStatisticsCodingConvert.setRegionId(String.valueOf(niDeliveryStatistics.getRegionId()));
			// niDeliveryStatisticsCodingConvert.setWanxScore(String.valueOf(niDeliveryStatistics.getWanxScore()));
			switch (niDeliveryStatistics.getRole()) {
			// 1学生；2教职工；3商户；4游客
			case 1:
				niDeliveryStatisticsCodingConvert.setRole("学生");
				break;
			case 2:
				niDeliveryStatisticsCodingConvert.setRole("教职工");
				break;
			case 3:
				niDeliveryStatisticsCodingConvert.setRole("商户");
				break;
			case 4:
				niDeliveryStatisticsCodingConvert.setRole("游客");
				break;
			default:
				break;
			}
			// bindCard->0未绑定，1绑定
			// switch (niDeliveryStatistics.getBindCard()) {
			// case 0:
			// niDeliveryStatisticsCodingConvert.setBindCard("未绑定");
			// break;
			// case 1:
			// niDeliveryStatisticsCodingConvert.setBindCard("绑定");
			// break;
			// default:
			// break;
			// }
			// bindStudent->0未绑定，1绑定
			// switch (niDeliveryStatistics.getBindStudent()) {
			// case 0:
			// niDeliveryStatisticsCodingConvert.setBindStudent("未绑定");
			// break;
			// case 1:
			// niDeliveryStatisticsCodingConvert.setBindStudent("绑定");
			// break;
			// default:
			// break;
			// }
			niDeliveryStatisticsCodingConvert
					.setRegionCode(niDeliveryStatistics.getRegionCode());
			niDeliveryStatisticsCodingConvert.setProvince(niDeliveryStatistics
					.getProvince());
			niDeliveryStatisticsCodingConvert.setOptionDesc(niDeliveryStatistics.getOptionDesc());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(niDeliveryStatistics.getbTime() != null) {
				niDeliveryStatisticsCodingConvert.setbTime(format.format(niDeliveryStatistics.getbTime()));
			}
			if(niDeliveryStatistics.geteTime() != null) {
				niDeliveryStatisticsCodingConvert.seteTime(format.format(niDeliveryStatistics.geteTime()));
			}
			
			// 加入list
			niDeliveryStatisticsCodingConvertsList.add(niDeliveryStatisticsCodingConvert);
		}
		return niDeliveryStatisticsCodingConvertsList;
	}

	/**
	 * @author lijz
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @Description : 导出答题详情(横表)excel时的格式
	 */	
	public List<NiDeliveryStatisticsCodingConvert2> codingConvert2(List<NiDeliveryStatistics> niDeliveryStatisticsList) throws Exception{
		
		log.info("-------------------->> 封装导出答题明细   (横表)的 格式 .... begin .... ");
		
		List<NiDeliveryStatisticsCodingConvert2> DeliveryStatisticsList = new ArrayList<NiDeliveryStatisticsCodingConvert2>();
		//按照userId和questionNum将list排序
		Collections.sort(niDeliveryStatisticsList,new Comparator<NiDeliveryStatistics>(){
			@Override
			public int compare(NiDeliveryStatistics a, NiDeliveryStatistics b){
				if(a.getUserId()>b.getUserId())	return 1;
				else if(a.getUserId()<b.getUserId()) return -1;
				else {
					if(a.getQuestionNum()>b.getQuestionNum())	return 1;
					else if(a.getQuestionNum()<b.getQuestionNum())	return -1;
					else return 0;
				}
			}
		});
		
		int lastUserId = -1;	//初始化一个userId
		NiDeliveryStatisticsCodingConvert2 tmp = null;	//初始化一个结果
		int len = niDeliveryStatisticsList.size();
		
		for(int i=0;i<len;i++){
			NiDeliveryStatistics cur = niDeliveryStatisticsList.get(i);
			
			if(cur.getUserId()!=lastUserId){	//当ID变更时，说明上一个user的信息已录完,加入到结果中
				lastUserId = cur.getUserId();
				if(tmp!=null)	DeliveryStatisticsList.add(tmp);
				tmp = new NiDeliveryStatisticsCodingConvert2();	//下一个user的信息
				tmp.setUserId(lastUserId);
				tmp.setUserName(cur.getUserName());
				if(cur.getGender()==1)	tmp.setGender("男");
				else if(cur.getGender()==2)	tmp.setGender("女");
				tmp.setSchoolName(cur.getSchoolName());
				tmp.setRegionName(cur.getRegionName());
				tmp.setProvince(cur.getProvince());
				tmp.setRegionCode(cur.getRegionCode());
				switch (cur.getRole()) {
				// 1学生；2教职工；3商户；4游客
				case 1:
					tmp.setRole("学生");
					break;
				case 2:
					tmp.setRole("教职工");
					break;
				case 3:
					tmp.setRole("商户");
					break;
				case 4:
					tmp.setRole("游客");
					break;
				default:
					break;
				}
			}
			//组装答案显示字符串
			String answer = new String();
			if(cur.getChoice()!=null) answer += cur.getChoice()+" ";
			if(cur.getSelfDefine()!=null) answer += cur.getSelfDefine();
			//获取题号
			int QuestionNum = 1;
			if(cur.getQuestionNum()!=0)	QuestionNum = cur.getQuestionNum();
			//通过反射调用对应的set方法
			tmp.getClass().getMethod("setQuestion"+QuestionNum, String.class).invoke(tmp, answer);
		}
		
		if(tmp!=null)	DeliveryStatisticsList.add(tmp);

		if(DeliveryStatisticsList != null){
			log.info("-------------------->> 封装导出答题明细   (横表)的 格式 .... end ....共有记录数 : "+DeliveryStatisticsList.size());
		}
		
		return DeliveryStatisticsList;
	}
	

	
	
	/**
	 * @author lj
	 * @Description :
	 *              根据userId得到NiDeliveryStatisticsUserInfo对象进而包装到NiDeliveryStatistics对象中
	 * @time : 2016年8月9日 下午2:56:33
	 * @param niDeliveryStatistics
	 * @param userId
	 * @return NiDeliveryStatistics
	 */
	public NiDeliveryStatistics getDeliveryStatisticsUserInfoByUserId(
			NiDeliveryStatistics niDeliveryStatistics, Integer userId) {
		
		//<!-- 根据userId查询用户相关信息  同名存储过程 -->
		NiDeliveryStatisticsUserInfo niDeliveryStatisticsUserInfo = (NiDeliveryStatisticsUserInfo) niDeliveryStatisticsMapper
				.selectUserInfoByUserId(userId);

		// 判断是否有对应的用户
		if (niDeliveryStatisticsUserInfo != null) {

			// 包装用户信息
			// userName,idCard,phone,email,wanxNickname,schoolName,regionName,userSn,wanxAccount;
			// gender,wanxUserId,schoolId,regionId,role,bindCard,bindStudent,wanxScore;
			// userCTime

			niDeliveryStatistics.setUserName(niDeliveryStatisticsUserInfo
					.getUserName());
			niDeliveryStatistics.setIdCard(niDeliveryStatisticsUserInfo
					.getIdCard());
			niDeliveryStatistics.setPhone(niDeliveryStatisticsUserInfo
					.getPhone());
			niDeliveryStatistics.setEmail(niDeliveryStatisticsUserInfo
					.getEmail());
			niDeliveryStatistics.setWanxNickname(niDeliveryStatisticsUserInfo
					.getWanxNickname());
			niDeliveryStatistics.setSchoolName(niDeliveryStatisticsUserInfo
					.getSchoolName());
			niDeliveryStatistics.setRegionName(niDeliveryStatisticsUserInfo
					.getRegionName());
			niDeliveryStatistics.setUserSn(niDeliveryStatisticsUserInfo
					.getUserSn());
			niDeliveryStatistics.setWanxAccount(niDeliveryStatisticsUserInfo
					.getWanxAccount());
			niDeliveryStatistics.setGender(niDeliveryStatisticsUserInfo
					.getGender());
			niDeliveryStatistics.setWanxUserId(niDeliveryStatisticsUserInfo
					.getWanxUserId());
			niDeliveryStatistics.setSchoolId(niDeliveryStatisticsUserInfo
					.getSchoolId());
			niDeliveryStatistics.setRegionId(niDeliveryStatisticsUserInfo
					.getRegionId());
			niDeliveryStatistics
					.setRole(niDeliveryStatisticsUserInfo.getRole());
			niDeliveryStatistics.setBindCard(niDeliveryStatisticsUserInfo
					.getBindCard());
			niDeliveryStatistics.setBindStudent(niDeliveryStatisticsUserInfo
					.getBindStudent());
			niDeliveryStatistics.setWanxScore(niDeliveryStatisticsUserInfo
					.getWanxScore());
			niDeliveryStatistics.setUserCTime(niDeliveryStatisticsUserInfo
					.getUserCTime());
			niDeliveryStatistics.setRegionCode(niDeliveryStatisticsUserInfo
					.getRegionCode());
			niDeliveryStatistics.setProvince(niDeliveryStatisticsUserInfo
					.getProvince());
		}
		return niDeliveryStatistics;
	}

	@Override
	public void download_AnswerDetail(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("==========================>> 导出用户答题信息到 excel  , begin ... ");
		
		String deliveryId_str = request.getParameter("deliveryId");
		String answerChannel_str = request.getParameter("answerChannel");
		String type_str = request.getParameter("type");
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_str);
		Integer answerChannel = StrUtils.changeToInt(answerChannel_str);
		Integer type = StrUtils.changeToInt(type_str);
		
		Integer questionQty = null;  // 题目数量
		// 查询出答题数据信息
		List<Download_AnswerDetail> download_AnswerDetail_list = null; 
		
		if(type == 1){
			try {
				download_AnswerDetail_list = niDeliveryStatisticsMapper.download_AnswerDetail_survey(deliveryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			questionQty = download_AnswerDetail_list.get(0).getQuestionQty();
			
		}else if(type == 2){
			
			try {
				download_AnswerDetail_list = niDeliveryStatisticsMapper.download_AnswerDetail_assess(deliveryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			questionQty = download_AnswerDetail_list.get(0).getQuestionQty();
			
		}else if(type == 3){
			try {
				download_AnswerDetail_list = niDeliveryStatisticsMapper.download_AnswerDetail_vote(deliveryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			questionQty = 1;
		}else{
			log.info("========================>> 暂不支持该类型问卷答题信息导出");
		}
		
		// 将答题信息重新封装 , 每个用户用一个对象封装
		// 使用比较器将用户排序
		Collections.sort(download_AnswerDetail_list, new Comparator<Download_AnswerDetail>() {
			
			public int compare(Download_AnswerDetail o1,
					Download_AnswerDetail o2) {
				if(o1.getUserId() > o2.getUserId()){
					return 1;
				}else if(o1.getUserId() < o2.getUserId()){
					return -1;
				}else{
					if(o1.getQuestionNum() > o2.getQuestionNum()){
						return 1;
					}else{
						return -1;
					}
				}
			}
		});
		// 定义最终返回 list
		List<Download_AnswerDetail_user> Download_AnswerDetail_user_List = new ArrayList<>();
		
		Iterator<Download_AnswerDetail> iterator = download_AnswerDetail_list.iterator();
		
		// 封装返回数据到对象中
		Download_AnswerDetail_user download_AnswerDetail_user = null;
		
		Integer last_userId = null;
		
		while(iterator.hasNext()){
			Download_AnswerDetail cur_download_AnswerDetail = iterator.next();
			
			Integer cur_userId = cur_download_AnswerDetail.getUserId();
			
			if( !cur_userId.equals(last_userId)){
				// 如果当前用户id 不等于最后标记的用户id, 上一个用户信息已录完, 加入新list中
				if(download_AnswerDetail_user != null){
					Download_AnswerDetail_user_List.add(download_AnswerDetail_user);
				}
				last_userId = cur_userId;
				// 新用户
				download_AnswerDetail_user = new Download_AnswerDetail_user();
				
				download_AnswerDetail_user.setUserId(cur_userId);
				download_AnswerDetail_user.setWanxUserId(cur_download_AnswerDetail.getWanxUserId());
				download_AnswerDetail_user.setWanxNickname(cur_download_AnswerDetail.getWanxNickname());
				download_AnswerDetail_user.setShowTitle(cur_download_AnswerDetail.getShowTitle());
				download_AnswerDetail_user.setQnId(cur_download_AnswerDetail.getQnId());
				download_AnswerDetail_user.setqId(cur_download_AnswerDetail.getqId());
				download_AnswerDetail_user.setQuestionType(cur_download_AnswerDetail.getQuestionType());
				download_AnswerDetail_user.setqTitle(cur_download_AnswerDetail.getqTitle());
				download_AnswerDetail_user.setGender(cur_download_AnswerDetail.getGender());
				download_AnswerDetail_user.setSchoolName(cur_download_AnswerDetail.getSchoolName());
				download_AnswerDetail_user.setRegionName(cur_download_AnswerDetail.getRegionName());
				
			}
			
			//组装答案显示字符串
			String answer = new String();
			if(cur_download_AnswerDetail.getChoice()!=null) answer += cur_download_AnswerDetail.getChoice()+" ";
			//获取题号
			int QuestionNum = 1;
			if(cur_download_AnswerDetail.getQuestionNum()!=0)	QuestionNum = cur_download_AnswerDetail.getQuestionNum();
			//通过反射调用对应的set方法
			try {
				download_AnswerDetail_user.getClass().getMethod("setQuestion"+QuestionNum, String.class).invoke(download_AnswerDetail_user, answer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(download_AnswerDetail_user != null){
			Download_AnswerDetail_user_List.add(download_AnswerDetail_user);
		}
		
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		fieldMap.put("userId", "用户ID");
		fieldMap.put("wanxUserId", "玩校用户ID");
		fieldMap.put("qnId", "问卷");
		fieldMap.put("qId", "问题id");
		fieldMap.put("gender", "性别");
//		fieldMap.put("questionNum", "题号");
		fieldMap.put("questionType", "问题类型");
		
		fieldMap.put("wanxNickname", "玩校昵称");
//		fieldMap.put("choice", "用户选项");
		fieldMap.put("schoolName", "学校名称");
		fieldMap.put("regionName", "地区");
		fieldMap.put("showTitle", "标题");
		fieldMap.put("qTitle", "问题标题");
		
		for(int i=1 ; i<=questionQty ; i++){
			
			fieldMap.put("question"+i, "第"+i+"题");
		}
		
		log.info("=====================>> 将要导出到 excel中的用户答题记录数据 : "+Download_AnswerDetail_user_List.size());
		
		try {
			if(Download_AnswerDetail_user_List != null && Download_AnswerDetail_user_List.size()>0){
			  
			  ExcelExportUtil.listToExcel(Download_AnswerDetail_user_List,fieldMap, "投放统计答题明细", response);
			}
		} catch (ExcelException e) {
			e.printStackTrace();
			log.info("----------------------------------->>导出失败");
		}
		
		
	}


}
