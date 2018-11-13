package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiReportStatisticsMapper;
import com.newIns.model.report.NiReportStatistics;
import com.newIns.model.report.NiReportStatisticsCodingConvert;
import com.newIns.service.NiReportStatisticsService;
/**
 * @author lj
 * @Description : 报告统计的Service实现类
 * @time : 2016年8月10日 下午1:04:46
 */
@Service
public class NiReportStatisticsServiceImp implements NiReportStatisticsService {

	@Resource
	private NiReportStatisticsMapper niReportStatisticsMapper;
	
	/**
	 * @author lj
	 * @Description : 根据条件得到报告统计的list
	 * @time : 2016年8月10日 下午1:05:36
	 * @param hashMap
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getReportStatisticsList(HashMap<String, Object> hashMap) {
		List<NiReportStatistics> niReportStatisticsList=new ArrayList<NiReportStatistics>();
		List<NiReportStatistics> newNiReportStatisticsList=new ArrayList<NiReportStatistics>();
		String channel=(String) hashMap.get("channel");
		String qnType=(String) hashMap.get("qnType");
		//判断渠道和类型
		if(channel == null){
			if(qnType == null){
				//加入所有渠道和类型
				//玩校调查
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectSurveyWanxReportList(hashMap));
				//玩校测评
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectAccessWanxReportList(hashMap));
				//玩校投票
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectVoteWanxReportList(hashMap));
			}else if(qnType != null && !("".equals(qnType))){
				if(Integer.valueOf(qnType)==1){
					//调查
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectSurveyWanxReportList(hashMap));
				}else if(Integer.valueOf(qnType)==2){
					//测评
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectAccessWanxReportList(hashMap));
				}else if(Integer.valueOf(qnType)==3){
					//投票
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectVoteWanxReportList(hashMap));
				}
			}
			newNiReportStatisticsList=getDifferenetChannelList(niReportStatisticsList);
		}else if(channel != null && !("".equals(channel))){
			//考虑类型分类
			if(qnType == null){
				//玩校调查
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectSurveyWanxReportList(hashMap));
				//玩校测评
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectAccessWanxReportList(hashMap));
				//玩校投票
				niReportStatisticsList.addAll(niReportStatisticsMapper.selectVoteWanxReportList(hashMap));
			//	newNiReportStatisticsList=getDifferenetChannelListByChannel(niReportStatisticsList, Integer.valueOf(channel));
			}else if(qnType != null && !("".equals(qnType))){
				if(Integer.valueOf(qnType)==1){
					//调查
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectSurveyWanxReportList(hashMap));
					
				}else if(Integer.valueOf(qnType)==2){
					//测评
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectAccessWanxReportList(hashMap));
				}else if(Integer.valueOf(qnType)==3){
					//投票
					niReportStatisticsList.addAll(niReportStatisticsMapper.selectVoteWanxReportList(hashMap));
				}
				
			}
			newNiReportStatisticsList=getDifferenetChannelListByChannel(niReportStatisticsList, Integer.valueOf(channel));
		}
		
		
		for(NiReportStatistics niReportStatistics:newNiReportStatisticsList){
			//分享次数暂时不做
			niReportStatistics.setShareNum(0);
		}
		
		
		
		return newNiReportStatisticsList;
		
		
	}

	

	/**
	 * @author lj
	 * @Description : 不选择渠道时得到不同渠道的list
	 * @time : 2016年8月10日 下午5:21:55
	 * @param niReportStatisticsList
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getDifferenetChannelList(
			List<NiReportStatistics> niReportStatisticsList) {
		// TODO Auto-generated method stub
		List<NiReportStatistics> newNiReportStatisticsList=new ArrayList<NiReportStatistics>();
		//对niReportStatisticsList进行二次封装
		for (int i = 0; i < niReportStatisticsList.size(); i++) {
			
			NiReportStatistics niReportStatistics=niReportStatisticsList.get(i);
			for (int j = 0; j < 3; j++) {
				//三个渠道//玩校//微信//APP
				NiReportStatistics newNiReportStatistics=new NiReportStatistics();
				newNiReportStatistics.setAppViewNum(niReportStatistics.getAppViewNum());
				newNiReportStatistics.setBaseViewNum(niReportStatistics.getBaseViewNum());
				newNiReportStatistics.setChannel(j+1);
				newNiReportStatistics.setQnName(niReportStatistics.getQnName());
				newNiReportStatistics.setQnType(niReportStatistics.getQnType());
				newNiReportStatistics.setReportId(niReportStatistics.getReportId());
				newNiReportStatistics.setReportStatus(niReportStatistics.getReportStatus());
				newNiReportStatistics.setWanxViewNum(niReportStatistics.getWanxViewNum());
				newNiReportStatistics.setWeixViewNum(niReportStatistics.getWeixViewNum());
				//根据不同渠道设置修正值
				if(j == 0){
					//玩校渠道
					newNiReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getWanxViewNum());
					newNiReportStatistics.setReadNum(niReportStatistics.getWanxViewNum());
				}else if(j == 1){
					//微信渠道
					newNiReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getWeixViewNum());
					newNiReportStatistics.setReadNum(niReportStatistics.getWeixViewNum());
				}else if(j == 2){
					//APP渠道
					newNiReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getAppViewNum());
					newNiReportStatistics.setReadNum(niReportStatistics.getAppViewNum());
				}
				
				
				//加入list
				newNiReportStatisticsList.add(newNiReportStatistics);
				
			}
		}
		
		return newNiReportStatisticsList;
	}


	

	/**
	 * @author lj
	 * @Description : 根据不同的渠道得到对应的list
	 * @time : 2016年8月10日 下午6:20:21
	 * @param niReportStatisticsList
	 * @param channel
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getDifferenetChannelListByChannel(
			List<NiReportStatistics> niReportStatisticsList, int channel) {
		
		// TODO Auto-generated method stub
		if(channel == 1){
			//玩校渠道
			//设置渠道和修正数
			for(NiReportStatistics niReportStatistics:niReportStatisticsList){
				niReportStatistics.setChannel(1);
				niReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getWanxViewNum());
				niReportStatistics.setReadNum(niReportStatistics.getWanxViewNum());
			}
		}else if(channel == 2){
			//微信渠道
			//设置渠道和修正数
			for(NiReportStatistics niReportStatistics:niReportStatisticsList){
				niReportStatistics.setChannel(2);
				niReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getWeixViewNum());
				niReportStatistics.setReadNum(niReportStatistics.getWeixViewNum());
			}
		}else if(channel == 3){
			//APP渠道
			//设置渠道和修正数
			for(NiReportStatistics niReportStatistics:niReportStatisticsList){
				niReportStatistics.setChannel(3);
				niReportStatistics.setCorrectNum(niReportStatistics.getBaseViewNum()+niReportStatistics.getAppViewNum());
				niReportStatistics.setReadNum(niReportStatistics.getAppViewNum());
			}
		}
		return niReportStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 得到要导出的报告统计列表
	 * @time : 2016年8月10日 下午8:02:36
	 * @param reportId_arr
	 * @param channel_arr
	 * @param type_arr
	 * @return List<NiReportStatistics>
	 */
	public List<NiReportStatistics> getExportReportStatisticsList(
			String reportId_str, String channel_str, String type_str) {
		List<NiReportStatistics> newNiReportStatisticsList=new ArrayList<NiReportStatistics>();
		String []reportId_arr=reportId_str.split("!");
		String []channel_arr=channel_str.split("!");
		String []type_arr=type_str.split("!");
		//前端接收的参数字符串是逆序拼接的,所以采用每次减1的循环
		for(int i=reportId_arr.length-1;i>=0;i--){
			String reportId=reportId_arr[i];
			String channel=channel_arr[i];
			String type=type_arr[i];
			HashMap<String, Object> hashMap=new HashMap<String, Object>();
			if(reportId != null && !("".equals(reportId))){
				hashMap.put("reportId", Integer.valueOf(reportId));
			}
			hashMap.put("channel", channel);
			hashMap.put("qnType", type);
			List<NiReportStatistics> niReportStatisticsList=getReportStatisticsList(hashMap);
			newNiReportStatisticsList.addAll(niReportStatisticsList);
		}
		return newNiReportStatisticsList;
	}
	


	/**
	 * @author lj
	 * @Description : 得到报告统计导出列表的列名
	 * @time : 2016年8月10日 下午7:29:08
	 * @return LinkedHashMap<String, String>
	 */
	public LinkedHashMap<String, String> getReportStatisticsExcelColumnName() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
		fieldMap.put("reportId", "报告id");
		fieldMap.put("qnName", "问卷名称");
		fieldMap.put("channel", "渠道");
		fieldMap.put("reportStatus", "报告状态");
		fieldMap.put("qnType", "类型");
		fieldMap.put("readNum", "阅读次数");
		fieldMap.put("correctNum", "阅读修正数");
		fieldMap.put("shareNum", "分享次数");
		
		return fieldMap;
	}



	/**
	 * @author lj
	 * @Description : 导出报告统计到excel时的编码转换
	 * @time : 2016年8月10日 下午8:54:14
	 * @param niReportStatisticsList
	 * @return List<NiReportStatisticsCodingConvert>
	 */
	public List<NiReportStatisticsCodingConvert> codingConvert(
			List<NiReportStatistics> niReportStatisticsList) {
		List<NiReportStatisticsCodingConvert> niReportStatisticsCodingConvertsList=new ArrayList<NiReportStatisticsCodingConvert>();
		for (int i = 0; i < niReportStatisticsList.size(); i++) {
			NiReportStatisticsCodingConvert niReportStatisticsCodingConvert=new NiReportStatisticsCodingConvert();
			NiReportStatistics niReportStatistics=niReportStatisticsList.get(i);
			niReportStatisticsCodingConvert.setReportId(niReportStatistics.getReportId());
			niReportStatisticsCodingConvert.setQnName(niReportStatistics.getQnName());
			switch (niReportStatistics.getChannel()) {
			case 1:
				niReportStatisticsCodingConvert.setChannel("玩校");
				break;
			case 2:
				niReportStatisticsCodingConvert.setChannel("微信");
				break;
			case 3:
				niReportStatisticsCodingConvert.setChannel("APP");
				break;
			default:
				break;
			}
			switch (niReportStatistics.getReportStatus()) {
			case 1:
				niReportStatisticsCodingConvert.setReportStatus("草稿");
				break;
			case 2:
				niReportStatisticsCodingConvert.setReportStatus("完成");
				break;
			case 3:
				niReportStatisticsCodingConvert.setReportStatus("发布");
				break;
			case 4:
				niReportStatisticsCodingConvert.setReportStatus("废弃");
				break;
			default:
				break;
			}
			switch (niReportStatistics.getQnType()) {
			case 1:
				niReportStatisticsCodingConvert.setQnType("调查");
				break;
			case 2:
				niReportStatisticsCodingConvert.setQnType("测评");
				break;
			case 3:
				niReportStatisticsCodingConvert.setQnType("投票");
				break;
			default:
				break;
			}
			niReportStatisticsCodingConvert.setReadNum(niReportStatistics.getReadNum());
			niReportStatisticsCodingConvert.setCorrectNum(niReportStatistics.getCorrectNum());
			switch (niReportStatistics.getShareNum()) {
			case 0:
				niReportStatisticsCodingConvert.setShareNum("未实现该功能");
				break;

			default:
				break;
			}
			
			niReportStatisticsCodingConvertsList.add(niReportStatisticsCodingConvert);
			
		}
		
		
		
		return niReportStatisticsCodingConvertsList;
	}





}
