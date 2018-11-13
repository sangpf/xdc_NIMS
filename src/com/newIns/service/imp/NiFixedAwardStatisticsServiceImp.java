package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiFixedAwardStatisticsMapper;
import com.newIns.model.NiFixedAwardStatistics;
import com.newIns.service.NiFixedAwardStatisticsService;
/**
 * @author lj
 * @Description : 定奖统计的Service实现类
 * @time : 2016年8月11日 下午4:45:04
 */
@Service
public class NiFixedAwardStatisticsServiceImp implements
		NiFixedAwardStatisticsService {

	@Resource
	private NiFixedAwardStatisticsMapper niFixedAwardStatisticsMapper;

	/**
	 * @author lj
	 * @Description : 根据条件查询调查玩校定奖统计
	 * @time : 2016年8月11日 下午4:45:58
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> selectSurveyWanxFixedAwardList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiFixedAwardStatistics> niFixedAwardStatisticsList=niFixedAwardStatisticsMapper.selectSurveyWanxFixedAwardList(hashMap);
		return niFixedAwardStatisticsList;
	}

	
	

	/**
	 * @author lj
	 * @Description : 根据条件查询测评玩校定奖统计
	 * @time : 2016年8月11日 下午4:46:14
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectAssessWanxFixedAwardList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiFixedAwardStatistics> niFixedAwardStatisticsList=niFixedAwardStatisticsMapper.selectAssessWanxFixedAwardList(hashMap);
		return niFixedAwardStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 根据条件查询投票玩校报告统计
	 * @time : 2016年8月11日 下午4:46:31
	 * @param hashMap
	 * @return  List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> selectVoteWanxFixedAwardList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiFixedAwardStatistics> niFixedAwardStatisticsList=niFixedAwardStatisticsMapper.selectVoteWanxFixedAwardList(hashMap);
		return niFixedAwardStatisticsList;
	}




	/**
	 * @author lj
	 * @Description : 通过hashMap得到定奖统计列表
	 * @time : 2016年8月11日 下午5:10:20
	 * @param hashMap
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> getFixedAwardStatisticsList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiFixedAwardStatistics> niFixedAwardStatisticsList=new ArrayList<NiFixedAwardStatistics>();
		//调查玩校
		
		List<NiFixedAwardStatistics> niSurveyWanxFixedAwardStatisticsList=selectSurveyWanxFixedAwardList(hashMap);
		for (int i = 0; i < niSurveyWanxFixedAwardStatisticsList.size(); i++) {
			NiFixedAwardStatistics niFixedAwardStatistics=niSurveyWanxFixedAwardStatisticsList.get(i);
			//设置渠道和类型
			niFixedAwardStatistics.setChannel(1);
			niFixedAwardStatistics.setType(1);
			int sqnId=niFixedAwardStatistics.getQnId();
			//设置完成人数和领取人数
			niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectSurveyWanxFixedAwardFinishNum(sqnId));
			niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectSurveyWanxFixedAwardReceiveNum(sqnId));
		}
		niFixedAwardStatisticsList.addAll(niSurveyWanxFixedAwardStatisticsList);
		//测评玩校
		List<NiFixedAwardStatistics> niAssessWanxFixedAwardStatisticsList=selectAssessWanxFixedAwardList(hashMap);
		for (int i = 0; i < niAssessWanxFixedAwardStatisticsList.size(); i++) {
			NiFixedAwardStatistics niFixedAwardStatistics=niAssessWanxFixedAwardStatisticsList.get(i);
			//设置渠道和类型
			niFixedAwardStatistics.setChannel(1);
			niFixedAwardStatistics.setType(2);
			int aqnId=niFixedAwardStatistics.getQnId();
			//设置完成人数和领取人数
			niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectAssessWanxFixedAwardFinishNum(aqnId));
			niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectAssessWanxFixedAwardReceiveNum(aqnId));
		}
		niFixedAwardStatisticsList.addAll(niAssessWanxFixedAwardStatisticsList);
		//投票玩校
		List<NiFixedAwardStatistics> niVoteWanxFixedAwardStatisticsList=selectVoteWanxFixedAwardList(hashMap);
		for (int i = 0; i < niVoteWanxFixedAwardStatisticsList.size(); i++) {
			NiFixedAwardStatistics niFixedAwardStatistics=niVoteWanxFixedAwardStatisticsList.get(i);
			//设置渠道和类型
			niFixedAwardStatistics.setChannel(1);
			niFixedAwardStatistics.setType(3);
			int vqnId=niFixedAwardStatistics.getQnId();
			//设置完成人数和领取人数
			niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectVoteWanxFixedAwardFinishNum(vqnId));
			niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectVoteWanxFixedAwardReceiveNum(vqnId));
			
		}
		niFixedAwardStatisticsList.addAll(niVoteWanxFixedAwardStatisticsList);
		
		return niFixedAwardStatisticsList;
	}




	/**
	 * @author lj
	 * @Description : 导出定奖统计表格
	 * @time : 2016年8月11日 下午7:04:47
	 * @param deliveryId
	 * @param channel
	 * @param type
	 * @return List<NiFixedAwardStatistics>
	 */
	public List<NiFixedAwardStatistics> exportFixedAwardStatisticsList(
			String deliveryId_str, String channel_str, String type_str) {
		// TODO Auto-generated method stub
		List<NiFixedAwardStatistics> newNiFixedAwardStatisticsList=new ArrayList<NiFixedAwardStatistics>();
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
					//调查
					List<NiFixedAwardStatistics> niFixedAwardStatisticsList=selectSurveyWanxFixedAwardList(hashMap);
					for(NiFixedAwardStatistics niFixedAwardStatistics:niFixedAwardStatisticsList){
						//设置渠道和类型
						niFixedAwardStatistics.setChannel(1);
						niFixedAwardStatistics.setType(1);
						int sqnId=niFixedAwardStatistics.getQnId();
						//设置完成人数和领取人数
						niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectSurveyWanxFixedAwardFinishNum(sqnId));
						niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectSurveyWanxFixedAwardReceiveNum(sqnId));
					}
					newNiFixedAwardStatisticsList.addAll(niFixedAwardStatisticsList);
				}else if(type_int == 2){
					//测评
					List<NiFixedAwardStatistics> niFixedAwardStatisticsList=selectAssessWanxFixedAwardList(hashMap);
					for(NiFixedAwardStatistics niFixedAwardStatistics:niFixedAwardStatisticsList){
						//设置渠道和类型
						niFixedAwardStatistics.setChannel(1);
						niFixedAwardStatistics.setType(2);
						int aqnId=niFixedAwardStatistics.getQnId();
						//设置完成人数和领取人数
						niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectAssessWanxFixedAwardFinishNum(aqnId));
						niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectAssessWanxFixedAwardReceiveNum(aqnId));
					}
					newNiFixedAwardStatisticsList.addAll(niFixedAwardStatisticsList);
				}else if(type_int == 3){
					//投票
					List<NiFixedAwardStatistics> niFixedAwardStatisticsList=selectVoteWanxFixedAwardList(hashMap);
					for(NiFixedAwardStatistics niFixedAwardStatistics:niFixedAwardStatisticsList){
						//设置渠道和类型
						niFixedAwardStatistics.setChannel(1);
						niFixedAwardStatistics.setType(3);
						int vqnId=niFixedAwardStatistics.getQnId();
						//设置完成人数和领取人数
						niFixedAwardStatistics.setFinishNum(niFixedAwardStatisticsMapper.selectVoteWanxFixedAwardFinishNum(vqnId));
						niFixedAwardStatistics.setReceiveNum(niFixedAwardStatisticsMapper.selectVoteWanxFixedAwardReceiveNum(vqnId));
						
					}
					newNiFixedAwardStatisticsList.addAll(niFixedAwardStatisticsList);
				}
			}
		}
		return newNiFixedAwardStatisticsList;
	}



	/**
	 * @author lj
	 * @Description : 得到导出的定奖excel列
	 * @time : 2016年8月11日 下午8:18:01
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getExportFixedAwardStatisticsColumn() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
		fieldMap.put("deliveryId", "报告id");
		fieldMap.put("qnName", "问卷名称");
		fieldMap.put("channel_str", "渠道");
		fieldMap.put("status_str", "状态");
		fieldMap.put("type_str", "类型");
		fieldMap.put("finishNum", "完成人数");
		fieldMap.put("awardName", "奖品名称");
		fieldMap.put("awardId", "奖品ID");
		fieldMap.put("receiveNum", "领取人数");
		return fieldMap;
	}



	/**
	 * @author lj
	 * @Description : 导出表格编码转换
	 * @time : 2016年8月11日 下午8:35:23
	 * @param niFixedAwardStatisticsList
	 * @return List<NiFixedAwardStatistics> 
	 */
	public List<NiFixedAwardStatistics> codingConvert(
			List<NiFixedAwardStatistics> niFixedAwardStatisticsList) {
		// TODO Auto-generated method stub
		for(NiFixedAwardStatistics niFixedAwardStatistics:niFixedAwardStatisticsList){
			switch (niFixedAwardStatistics.getChannel()) {
			case 1:
				niFixedAwardStatistics.setChannel_str("玩校");
				break;
			case 2:
				niFixedAwardStatistics.setChannel_str("微信");
				break;
			case 3:
				niFixedAwardStatistics.setChannel_str("APP");
				break;

			default:
				break;
			}
			switch (niFixedAwardStatistics.getStatus()) {
			case 1:
				niFixedAwardStatistics.setStatus_str("待投放");
				break;
			case 2:
				niFixedAwardStatistics.setStatus_str("投放中");
				break;
			case 3:
				niFixedAwardStatistics.setStatus_str("暂停中");
				break;
			case 4:
				niFixedAwardStatistics.setStatus_str("人工终止");
				break;	
			case 5:
				niFixedAwardStatistics.setStatus_str("时间完成");
				break;
			case 6:
				niFixedAwardStatistics.setStatus_str("数量完成");
				break;
			default:
				break;
			}
			switch (niFixedAwardStatistics.getType()) {
			case 1:
				niFixedAwardStatistics.setType_str("调查");
				break;
			case 2:
				niFixedAwardStatistics.setType_str("测评");
				break;
			case 3:
				niFixedAwardStatistics.setType_str("投票");
				break;
			default:
				break;
			}
		}
		return niFixedAwardStatisticsList;
	}

}
