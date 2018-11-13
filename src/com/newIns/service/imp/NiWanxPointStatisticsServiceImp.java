package com.newIns.service.imp;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiWanxPointStatisticsMapper;
import com.newIns.model.NiWanxPointStatistics;
import com.newIns.service.NiWanxPointStatisticsService;
/**
 * @author lj
 * @Description : 玩校积分统计的Service实现类
 * @time : 2016年8月26日 上午11:27:29
 */
@Service
public class NiWanxPointStatisticsServiceImp implements
		NiWanxPointStatisticsService {

	@Resource
	private NiWanxPointStatisticsMapper niWanxPointStatisticsMapper;
	
	/**
	 * @author lj
	 * @Description : 得到玩校积分统计List
	 * @time : 2016年8月26日 上午11:27:19
	 * @param hashMap
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> getWanxPointStatisticsList(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiWanxPointStatistics> niWanxPointStatisticsList=new ArrayList<NiWanxPointStatistics>();
		//定奖
		hashMap.put("awardMethod", 1);
		List<NiWanxPointStatistics> niWanxPointStatisticsList1=niWanxPointStatisticsMapper.selectWanxPointInfo(hashMap);
		
		//抽奖
		hashMap.put("awardMethod", 2);
		List<NiWanxPointStatistics> niWanxPointStatisticsList2=niWanxPointStatisticsMapper.selectWanxPointInfo(hashMap);
	
		//活动
		hashMap.put("awardMethod", 3);
		List<NiWanxPointStatistics> niWanxPointStatisticsList3=niWanxPointStatisticsMapper.selectWanxPointInfo(hashMap);
		
		
		//定奖,抽奖,活动的list日期可能都不相同,因此构造日期——list的hashMap用于添加新数据和去重
		HashMap<Date, Object> hashMap2=new HashMap<Date, Object>();
		
		//遍历定奖积分list
		for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList1){
			int fixedPoint=niWanxPointStatistics.getAwardNum();
			if(fixedPoint != 0){
				niWanxPointStatistics.setFixedPoint(fixedPoint);
			}else{
				niWanxPointStatistics.setFixedPoint(0);
			}
			//将抽奖和活动都置为0
			niWanxPointStatistics.setLotteryPoint(0);
			niWanxPointStatistics.setActivityPoint(0);
			
			hashMap2.put(niWanxPointStatistics.getAwardGetTime(), niWanxPointStatistics);
			niWanxPointStatisticsList.add(niWanxPointStatistics);
		}
		
		
		//遍历抽奖积分list
		for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList2){
			
			Date awardGetTime = niWanxPointStatistics.getAwardGetTime();
			if(hashMap2.containsKey(awardGetTime)){
				//如果hashmap2中已经有该时间,将与该时间关联的对象的抽奖积分和置为awardNum
				((NiWanxPointStatistics)hashMap2.get(awardGetTime)).setLotteryPoint(niWanxPointStatistics.getAwardNum());
			}else{
				//没有该时间[1:将niWanxPointStatistics对象的抽奖积分和置为awardNum;2:放入hashMap;3:添加到结果list中]
				niWanxPointStatistics.setLotteryPoint(niWanxPointStatistics.getAwardNum());
				//将定奖和活动置为0
				niWanxPointStatistics.setFixedPoint(0);
				niWanxPointStatistics.setActivityPoint(0);
				
				hashMap2.put(awardGetTime, niWanxPointStatistics);
				
				niWanxPointStatisticsList.add(niWanxPointStatistics);
			}
			
		}
		
		
		//遍历活动积分list
		for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList3){
			Date awardGetTime = niWanxPointStatistics.getAwardGetTime();
			if(hashMap2.containsKey(awardGetTime)){
				//如果hashmap2中已经有该时间,将与该时间关联的对象的活动积分和置为awardNum
				((NiWanxPointStatistics)hashMap2.get(awardGetTime)).setActivityPoint(niWanxPointStatistics.getAwardNum());
			}else{
				//没有该时间[1:将niWanxPointStatistics对象的活动积分和置为awardNum;2:放入hashMap;3:添加到结果list中]
				niWanxPointStatistics.setActivityPoint(niWanxPointStatistics.getAwardNum());
				//将定奖和抽奖置为0
				niWanxPointStatistics.setFixedPoint(0);
				niWanxPointStatistics.setLotteryPoint(0);
				
				hashMap2.put(awardGetTime, niWanxPointStatistics);
				
				niWanxPointStatisticsList.add(niWanxPointStatistics);
				
			}
		}
		//计算总积分以及转换日期格式
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList){
			String format = dateFormat.format(niWanxPointStatistics.getAwardGetTime());
			niWanxPointStatistics.setDate(format);
			niWanxPointStatistics.setSumPoint(niWanxPointStatistics.getFixedPoint()+niWanxPointStatistics.getLotteryPoint()+niWanxPointStatistics.getActivityPoint());
		}
		
		return niWanxPointStatisticsList;

	}


	/**
	 * @author lj
	 * @Description : 得到导出玩校积分统计列表
	 * @time : 2016年8月26日 下午9:05:32
	 * @param date
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> getExportWanxPointStatisticsList(
			String date) {
		List<NiWanxPointStatistics> resultNiWanxPointStatisticsList=new ArrayList<NiWanxPointStatistics>();
		
		String []dateArr=date.split("!");
		
		// TODO Auto-generated method stub
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		//获取玩校积分统计全部对象
		List<NiWanxPointStatistics> niWanxPointStatisticsList = getWanxPointStatisticsList(hashMap);
		
		
		//string转date
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//前端接收的参数字符串是逆序拼接的,所以采用每次减1的循环
		//遍历当前要导出列表中的日期,如果在全部对象的list中找到该日期对应的对象,则加入结果list
		for(int i=dateArr.length-1;i>=0;i--){
			Date parseDate=null;
			try {
				parseDate = dateFormat.parse(dateArr[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList){
				if(niWanxPointStatistics.getAwardGetTime().equals(parseDate)){
					resultNiWanxPointStatisticsList.add(niWanxPointStatistics);
					//找到一个对应的对象跳出本次循环
					break;
				}
			}
		}
		
		
		
		return resultNiWanxPointStatisticsList;
	}


	/**
	 * @author lj
	 * @Description : 得到导出玩校积分统计列表的列名
	 * @time : 2016年8月26日 下午9:22:23
	 * @return LinkedHashMap<String, String> 
	 */
	public LinkedHashMap<String, String> getExcelColumnName() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
		
		fieldMap.put("date", "时间");
		fieldMap.put("qnChannelStr", "渠道");
		fieldMap.put("sumPoint", "总积分");
		fieldMap.put("fixedPoint", "定奖");
		fieldMap.put("lotteryPoint", "抽奖");
		fieldMap.put("activityPoint", "活动");
		
		return fieldMap;
	}



	/**
	 * @author lj
	 * @Description : 玩校积分导出list编码转换
	 * @time : 2016年8月26日 下午9:52:18
	 * @param niWanxPointStatisticsList
	 * @return List<NiWanxPointStatistics>
	 */
	public List<NiWanxPointStatistics> codingConvertWanxPointStatisticsList(
			List<NiWanxPointStatistics> niWanxPointStatisticsList) {
		// TODO Auto-generated method stub
		//将渠道代码转换为渠道[1玩校,2微信,3APP]
		for(NiWanxPointStatistics niWanxPointStatistics:niWanxPointStatisticsList){
			switch (niWanxPointStatistics.getQnChannel()) {
			case 1:
				niWanxPointStatistics.setQnChannelStr("玩校");
				break;
			case 2:
				niWanxPointStatistics.setQnChannelStr("微信");
				break;
			case 3:
				niWanxPointStatistics.setQnChannelStr("APP");
				break;
			default:
				break;
			}
		}
		return niWanxPointStatisticsList;
		
	}

}
