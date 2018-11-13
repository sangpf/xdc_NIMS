package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiLotteryStatisticsMapper;
import com.newIns.model.NiLotteryStatistics;
import com.newIns.service.NiLotteryStatisticsService;
/**
 * @author lj
 * @Description : 抽奖统计的Service实现类
 * @time : 2016年8月16日 下午4:29:11
 */
@Service
public class NiLotteryStatisticsServiceImp implements
		NiLotteryStatisticsService {
	
	@Resource
	private NiLotteryStatisticsMapper niLotteryStatisticsMapper;
	

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校抽奖统计
	 * @time : 2016年8月16日 下午4:50:28
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectSurveyWanxLotteryList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiLotteryStatistics> niLotteryStatisticsList=niLotteryStatisticsMapper.selectSurveyWanxLotteryList(hashMap);
		//填充其它属性
		for(NiLotteryStatistics niLotteryStatistics:niLotteryStatisticsList){
			int sqnId=niLotteryStatistics.getQnId();
			niLotteryStatistics.setChannel(1);
			niLotteryStatistics.setChannelStr("玩校");
			niLotteryStatistics.setType(1);
			niLotteryStatistics.setTypeStr("调查");
			niLotteryStatistics.setValidOrderNum(selectSurveyWanxLotteryValidOrderNum(sqnId));
			niLotteryStatistics.setTakePartNum(selectSurveyWanxLotteryTakePartNum(sqnId));
			niLotteryStatistics.setAward3ReceiveNum(selectSurveyWanxLotteryAward3ReceiveNum(sqnId));
			niLotteryStatistics.setAward2ReceiveNum(selectSurveyWanxLotteryAward2ReceiveNum(sqnId));
			niLotteryStatistics.setAward1ReceiveNum(selectSurveyWanxLotteryAward1ReceiveNum(sqnId));
		}
		return niLotteryStatisticsList;
	}
	

	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:30:54
	 * @param sqnId
	 * @return int 
	 */
	public int selectSurveyWanxLotteryValidOrderNum(Integer sqnId) {
		// TODO Auto-generated method stub
		int surveyWanxLotteryValidOrderNum=niLotteryStatisticsMapper.selectSurveyWanxLotteryValidOrderNum(sqnId);
		return surveyWanxLotteryValidOrderNum;
	}


	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:32:36
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryTakePartNum(Integer sqnId) {
		// TODO Auto-generated method stub
		int surveyWanxLotteryTakePartNum=niLotteryStatisticsMapper.selectSurveyWanxLotteryTakePartNum(sqnId);
		return surveyWanxLotteryTakePartNum;
	}


	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:37:50
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward3ReceiveNum(Integer sqnId) {
		// TODO Auto-generated method stub
		int surveyWanxLotteryAward3ReceiveNum=niLotteryStatisticsMapper.selectSurveyWanxLotteryAward3ReceiveNum(sqnId);
		return surveyWanxLotteryAward3ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:38:49
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward2ReceiveNum(Integer sqnId) {
		// TODO Auto-generated method stub
		int surveyWanxLotteryAward2ReceiveNum=niLotteryStatisticsMapper.selectSurveyWanxLotteryAward2ReceiveNum(sqnId);
		return surveyWanxLotteryAward2ReceiveNum;
	}
	
	/**
	 * @author lj
	 * @Description : 调查玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:39:30
	 * @param sqnId
	 * @return int
	 */
	public int selectSurveyWanxLotteryAward1ReceiveNum(Integer sqnId) {
		// TODO Auto-generated method stub
		int surveyWanxLotteryAward1ReceiveNum=niLotteryStatisticsMapper.selectSurveyWanxLotteryAward1ReceiveNum(sqnId);
		return surveyWanxLotteryAward1ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description :  根据条件查询测评玩校抽奖统计
	 * @time : 2016年8月16日 下午5:14:17
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectAssessWanxLotteryList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiLotteryStatistics> niLotteryStatisticsList=niLotteryStatisticsMapper.selectAssessWanxLotteryList(hashMap);
		
		//填充其它属性
		for(NiLotteryStatistics niLotteryStatistics:niLotteryStatisticsList){
			int aqnId=niLotteryStatistics.getQnId();
			niLotteryStatistics.setChannel(1);
			niLotteryStatistics.setChannelStr("玩校");
			niLotteryStatistics.setType(2);
			niLotteryStatistics.setTypeStr("测评");
			niLotteryStatistics.setValidOrderNum(selectAssessWanxLotteryValidOrderNum(aqnId));
			niLotteryStatistics.setTakePartNum(selectAssessWanxLotteryTakePartNum(aqnId));
			niLotteryStatistics.setAward3ReceiveNum(selectAssessWanxLotteryAward3ReceiveNum(aqnId));
			niLotteryStatistics.setAward2ReceiveNum(selectAssessWanxLotteryAward2ReceiveNum(aqnId));
			niLotteryStatistics.setAward1ReceiveNum(selectAssessWanxLotteryAward1ReceiveNum(aqnId));
		}
		return niLotteryStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:40:33
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryValidOrderNum(Integer aqnId) {
		// TODO Auto-generated method stub
		int assessWanxLotteryValidOrderNum=niLotteryStatisticsMapper.selectAssessWanxLotteryValidOrderNum(aqnId);
		return assessWanxLotteryValidOrderNum;
	}


	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖参与人数统计 
	 * @time : 2016年8月16日 下午4:41:35
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryTakePartNum(Integer aqnId) {
		// TODO Auto-generated method stub
		int assessWanxLotteryTakePartNum=niLotteryStatisticsMapper.selectAssessWanxLotteryTakePartNum(aqnId);
		return assessWanxLotteryTakePartNum;
	}


	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖三等奖领取人数统计
	 * @time : 2016年8月16日 下午4:42:34
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward3ReceiveNum(Integer aqnId) {
		// TODO Auto-generated method stub
		int assessWanxLotteryAward3ReceiveNum=niLotteryStatisticsMapper.selectAssessWanxLotteryAward3ReceiveNum(aqnId);
		return assessWanxLotteryAward3ReceiveNum;
	}

	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖二等奖领取人数统计
	 * @time : 2016年8月16日 下午4:43:16
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward2ReceiveNum(Integer aqnId) {
		// TODO Auto-generated method stub
		int assessWanxLotteryAward2ReceiveNum=niLotteryStatisticsMapper.selectAssessWanxLotteryAward2ReceiveNum(aqnId);
		return assessWanxLotteryAward2ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 测评玩校投放抽奖一等奖领取人数统计
	 * @time : 2016年8月16日 下午4:44:04
	 * @param aqnId
	 * @return int
	 */
	public int selectAssessWanxLotteryAward1ReceiveNum(Integer aqnId) {
		// TODO Auto-generated method stub
		int assessWanxLotteryAward1ReceiveNum=niLotteryStatisticsMapper.selectAssessWanxLotteryAward1ReceiveNum(aqnId);
		return assessWanxLotteryAward1ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校抽奖统计
	 * @time : 2016年8月16日 下午5:18:05
	 * @param hashMap
	 * @return  List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> selectVoteWanxLotteryList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiLotteryStatistics> niLotteryStatisticsList=niLotteryStatisticsMapper.selectVoteWanxLotteryList(hashMap);
		
		//填充其它属性
		for(NiLotteryStatistics niLotteryStatistics: niLotteryStatisticsList){
			int vqnId=niLotteryStatistics.getQnId();
			niLotteryStatistics.setChannel(1);
			niLotteryStatistics.setChannelStr("玩校");
			niLotteryStatistics.setType(3);
			niLotteryStatistics.setTypeStr("投票");
			niLotteryStatistics.setValidOrderNum(selectVoteWanxLotteryValidOrderNum(vqnId));
			niLotteryStatistics.setTakePartNum(selectVoteWanxLotteryTakePartNum(vqnId));
			niLotteryStatistics.setAward3ReceiveNum(selectVoteWanxLotteryAward3ReceiveNum(vqnId));
			niLotteryStatistics.setAward2ReceiveNum(selectVoteWanxLotteryAward2ReceiveNum(vqnId));
			niLotteryStatistics.setAward1ReceiveNum(selectVoteWanxLotteryAward1ReceiveNum(vqnId));
		}
		return niLotteryStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖有效订单数统计
	 * @time : 2016年8月16日 下午4:44:52
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryValidOrderNum(Integer vqnId) {
		// TODO Auto-generated method stub
		int voteWanxLotteryValidOrderNum=niLotteryStatisticsMapper.selectVoteWanxLotteryValidOrderNum(vqnId);
		return voteWanxLotteryValidOrderNum;
	}


	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖参与人数统计
	 * @time : 2016年8月16日 下午4:46:02
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryTakePartNum(Integer vqnId) {
		// TODO Auto-generated method stub
		int voteWanxLotteryTakePartNum=niLotteryStatisticsMapper.selectVoteWanxLotteryTakePartNum(vqnId);
		return voteWanxLotteryTakePartNum;
	}
	

	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖三等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:46:51
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward3ReceiveNum(Integer vqnId) {
		// TODO Auto-generated method stub
		int voteWanxLotteryAward3ReceiveNum=niLotteryStatisticsMapper.selectVoteWanxLotteryAward3ReceiveNum(vqnId);
		return voteWanxLotteryAward3ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖二等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:48:05
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward2ReceiveNum(Integer vqnId) {
		// TODO Auto-generated method stub
		int voteWanxLotteryAward2ReceiveNum=niLotteryStatisticsMapper.selectVoteWanxLotteryAward2ReceiveNum(vqnId);
		return voteWanxLotteryAward2ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 投票玩校投放抽奖一等奖领取人数统计 
	 * @time : 2016年8月16日 下午4:49:11
	 * @param vqnId
	 * @return int
	 */
	public int selectVoteWanxLotteryAward1ReceiveNum(Integer vqnId) {
		// TODO Auto-generated method stub
		int voteWanxLotteryAward1ReceiveNum=niLotteryStatisticsMapper.selectVoteWanxLotteryAward1ReceiveNum(vqnId);
		return voteWanxLotteryAward1ReceiveNum;
	}


	/**
	 * @author lj
	 * @Description : 根据条件得到抽奖统计List
	 * @time : 2016年8月16日 下午5:25:06
	 * @param hashMap
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> getLotteryStatisticsList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiLotteryStatistics> niLotteryStatisticsList=new ArrayList<NiLotteryStatistics>();
		niLotteryStatisticsList.addAll(selectSurveyWanxLotteryList(hashMap));
		niLotteryStatisticsList.addAll(selectAssessWanxLotteryList(hashMap));
		niLotteryStatisticsList.addAll(selectVoteWanxLotteryList(hashMap));
		return niLotteryStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 导出抽奖统计表格
	 * @time : 2016年8月16日 下午6:06:40
	 * @param deliveryId
	 * @param channel
	 * @param type
	 * @return List<NiLotteryStatistics> 
	 */
	public List<NiLotteryStatistics> exportLotteryStatisticsSheet(
			String deliveryId_str, String channel_str, String type_str) {
		// TODO Auto-generated method stub
		List<NiLotteryStatistics> niLotteryStatisticsList=new ArrayList<NiLotteryStatistics>();
		String []deliveryId_arr=deliveryId_str.split("!");
		String []channel_arr=channel_str.split("!");
		String []type_arr=type_str.split("!");
		//前端接收的参数字符串是逆序拼接的,所以采用每次减1的循环
		for(int i=deliveryId_arr.length-1;i>=0;i--){
			String deliveryId=deliveryId_arr[i];
			String channel=channel_arr[i];
			String type=type_arr[i];
			int channel_int=0;
			int type_int=0;
			HashMap<String, Object> hashMap=new HashMap<String, Object>();
			if(deliveryId != null && !("".equals(deliveryId))){
				hashMap.put("deliveryId", Integer.valueOf(deliveryId));
			}
			if(channel != null && !("".equals(channel))){
				channel_int=Integer.valueOf(channel);
			}
			if(type != null && !("".equals(type))){
				type_int=Integer.valueOf(type);
			}
			if(channel_int == 1){
				//玩校渠道
				if(type_int == 1){
					//调查类型
					niLotteryStatisticsList.addAll(selectSurveyWanxLotteryList(hashMap));
				}else if(type_int == 2){
					//测评类型
					niLotteryStatisticsList.addAll(selectAssessWanxLotteryList(hashMap));
				}else if(type_int == 3){
					//投票类型
					niLotteryStatisticsList.addAll(selectVoteWanxLotteryList(hashMap));
				}
				
			}
		}
		return niLotteryStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 导出抽奖统计时的编码转换
	 * @time : 2016年8月16日 下午7:03:46
	 * @param niLotteryStatisticsList
	 * @return List<NiLotteryStatistics>
	 */
	public List<NiLotteryStatistics> codingConvert(
			List<NiLotteryStatistics> niLotteryStatisticsList) {
		// TODO Auto-generated method stub
		for(NiLotteryStatistics niLotteryStatistics:niLotteryStatisticsList){
			switch (niLotteryStatistics.getStatus()) {
			//1待投放；2投放中；3暂停中；4人工终止；5时间完成；6数量完成
			case 1:
				niLotteryStatistics.setStatusStr("待投放");
				break;
			case 2:
				niLotteryStatistics.setStatusStr("投放中");
				break;
			case 3:
				niLotteryStatistics.setStatusStr("暂停中");
				break;
			case 4:
				niLotteryStatistics.setStatusStr("人工终止");
				break;
			case 5:
				niLotteryStatistics.setStatusStr("时间完成");
				break;
			case 6:
				niLotteryStatistics.setStatusStr("数量完成");
				break;
			default:
				break;
			}
		}
		return niLotteryStatisticsList;
	}



	/**
	 * @author lj
	 * @Description : 得到导出的抽奖统计excel列
	 * @time : 2016年8月16日 下午7:04:06
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExportLotteryStatisticsColumn() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
		fieldMap.put("deliveryId", "报告id");
		fieldMap.put("qnName", "问卷名称");
		fieldMap.put("channelStr", "渠道");
		fieldMap.put("statusStr", "状态");
		fieldMap.put("typeStr", "类型");
		fieldMap.put("validOrderNum", "有效订单数");
		fieldMap.put("takePartNum", "参与抽奖人数");
		fieldMap.put("award3Name", "三等奖名称");
		fieldMap.put("award3Id", "三等奖奖品ID");
		fieldMap.put("award3ReceiveNum", "领取人数");
		fieldMap.put("award2Name", "二等奖名称");
		fieldMap.put("award2Id", "二等奖奖品ID");
		fieldMap.put("award2ReceiveNum", "领取人数");
		fieldMap.put("award1Name", "一等奖名称");
		fieldMap.put("award1Id", "一等奖奖品ID");
		fieldMap.put("award1ReceiveNum", "领取人数");
		return fieldMap;
	}

}
