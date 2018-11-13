package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.ExportQtnStatisticsCubeMapper;
import com.newIns.model.QuestionnaireStatisticsBaseCube;
import com.newIns.model.QuestionnaireStatisticsCube;
import com.newIns.service.ExportQtnStatisticsCubeService;
/**
 * 
 * @author wanq
 * @time 2016.10.20
 *
 */
@Service
public class ExportQtnStatisticsCubeServiceImp implements ExportQtnStatisticsCubeService{
	//1：调查；2：测评；3：投票	
	private static final int SURVEY_TYPE = 1;
	private static final int ASSESS_TYPE = 2;
	private static final int VOTE_TYPE = 3;
	
	@Resource
	private ExportQtnStatisticsCubeMapper exportQtnStatisticsCubeMapper;
	
	/**
	 * @Description将QuestionnaireStatisticsBaseCube转换为QuestionnaireStatisticsCube
	 * 也就是根据从数据库中查出来的数据添加上时段、星期这些字段
	 * @param baseCube
	 * @param qnType
	 * @return
	 */
	private QuestionnaireStatisticsCube transfer(QuestionnaireStatisticsBaseCube baseCube) {
		QuestionnaireStatisticsCube questionnaireStatisticsCube = new QuestionnaireStatisticsCube();
		int timeInterval = 0;
		int dayOfWeekInt = 0;
		if(baseCube.getTapTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(baseCube.getTapTime());
			timeInterval = cal.get(Calendar.HOUR_OF_DAY);//时段
			dayOfWeekInt = cal.get(Calendar.DAY_OF_WEEK);//注意：Calendar.SUNDAY = 1,Calendar.SATURDAY = 7
		}
		questionnaireStatisticsCube.setUserId(baseCube.getUserId());
		questionnaireStatisticsCube.setQnId(baseCube.getQnid());
		questionnaireStatisticsCube.setTapTime(baseCube.getTapTime());
		questionnaireStatisticsCube.setQnClassName(baseCube.getQnClassName());
		String gender = null;
		if(baseCube.getGender() == 1) {
			gender = "男";
		} else if(baseCube.getGender() == 2) {
			gender = "女";
		}
		questionnaireStatisticsCube.setGender(gender);
		questionnaireStatisticsCube.setProvince(baseCube.getProvince());
		questionnaireStatisticsCube.setRegionName(baseCube.getRegionName());
		questionnaireStatisticsCube.setSchoolName(baseCube.getSchoolName());
		//1：三更列表，2：超级列表，3：测一发，4：广告位，5：栏目外部等
		String tapSource = null;
		switch (baseCube.getTapSource()) {
				case 1:
					tapSource = "三更列表";
					break;
				case 2:
					tapSource = "超级列表";
					break;
				case 3:
					tapSource = "测一发";
					break;
				case 4:
					tapSource = "广告位";
					break;
				case 5:
					tapSource = "栏目外部等";
					break;
				default:
					break;
		}
		questionnaireStatisticsCube.setTapSource(tapSource);
		// 问卷类型，1：调查；2：测评；3：投票
		String qnType = null;
		switch (baseCube.getQnType()) {
			case 1:
				qnType = "调查";
				break;
			case 2:
				qnType = "测评";
				break;
			case 3:
				qnType = "投票";
				break;
			default:
				break;
		}
		questionnaireStatisticsCube.setQnType(qnType);
		// 事件类型，1：打开，2：提交
		String eventType = null;
		if(baseCube.getEventType() == 1) {
			eventType = "打开";
		} else if(baseCube.getEventType() == 2) {
			eventType = "提交";
		}
		String dayOfWeek = null;
		switch (dayOfWeekInt) {
			case 1:
				dayOfWeek = "日";
				break;
			case 2:
				dayOfWeek = "一";
				break;
			case 3:
				dayOfWeek = "二";
				break;
			case 4:
				dayOfWeek = "三";
				break;
			case 5:
				dayOfWeek = "四";
				break;
			case 6:
				dayOfWeek = "五";
				break;
			case 7:
				dayOfWeek = "六";
				break;
			default:
				break;
		}
		questionnaireStatisticsCube.setEventType(eventType);
		questionnaireStatisticsCube.setTimeLength(baseCube.getTimeLength());
		questionnaireStatisticsCube.setTimeInterval(timeInterval);
		questionnaireStatisticsCube.setDayOfWeek(dayOfWeek);
		return questionnaireStatisticsCube;
	}

	/**
	 * @Description 获取Cube表表项对应的对象列表
	 * @param deliveryId 投递Id
	 * @param type 问卷类型 1：调查；2：测评；3：投票	
	 * @param channel 渠道 1：玩校；2：微信；3：APP
	 */
	@Override
	public List<QuestionnaireStatisticsCube> getQuestionnaireStatisticsCubeList(int deliveryId,int type,int channel) {
		List<QuestionnaireStatisticsCube> resultList = new ArrayList<QuestionnaireStatisticsCube>();
		switch (type) {
		//获取调查问卷打开事件和点击事件cube表项
			case SURVEY_TYPE:
				
				List<QuestionnaireStatisticsBaseCube> tempSurveyOpenCubeList = exportQtnStatisticsCubeMapper.selectSurveyOpenStatisticsCube(deliveryId);
				
				for(int i = 0; i<tempSurveyOpenCubeList.size(); i++) {
					resultList.add(transfer(tempSurveyOpenCubeList.get(i)));
				}
				
				List<QuestionnaireStatisticsBaseCube> tempSurveyTapCubeList = exportQtnStatisticsCubeMapper.selectSurveyTapStatisticsCube(deliveryId);
				
				for(int i = 0;i<tempSurveyTapCubeList.size();i++) {
					resultList.add(transfer(tempSurveyTapCubeList.get(i)));
				}
				break;
				//获取测评问卷打开事件和点击事件cube表项
			case ASSESS_TYPE:
				
				List<QuestionnaireStatisticsBaseCube> tempAssessOpenCubeList = exportQtnStatisticsCubeMapper.selectAssessOpenStatisticsCube(deliveryId);
				
				for(int i = 0;i<tempAssessOpenCubeList.size();i++) {
					resultList.add(transfer(tempAssessOpenCubeList.get(i)));
					System.out.println();
				}
				
				List<QuestionnaireStatisticsBaseCube> tempAssessTapCubeList = exportQtnStatisticsCubeMapper.selectAssessTapStatisticsCube(deliveryId);
				
				for(int i = 0;i<tempAssessTapCubeList.size();i++) {
					resultList.add(transfer(tempAssessTapCubeList.get(i)));
				}
				break;
				//获取投票问卷打开事件和点击事件cube表项
			case VOTE_TYPE:
				List<QuestionnaireStatisticsBaseCube> tempVoteOpenCubeList = exportQtnStatisticsCubeMapper.selectVoteOpenStatisticsCube(deliveryId);
				
				for(int i = 0;i<tempVoteOpenCubeList.size();i++) {
					resultList.add(transfer(tempVoteOpenCubeList.get(i)));
				}
				
				List<QuestionnaireStatisticsBaseCube> tempVoteTapCubeList = exportQtnStatisticsCubeMapper.selectVoteTapStatisticsCube(deliveryId);
				
				for(int i = 0;i<tempVoteTapCubeList.size();i++) {
					resultList.add(transfer(tempVoteTapCubeList.get(i)));
				}
				break;
			default:
				break;
		}
		return resultList;
	}

	/**
	 * @Description : 获取excel表标题行各列字段
	 */
	@Override
	public LinkedHashMap<String, String> getDownLoadColumnName() {
		LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
		fieldMap.put("userId", "用户id");
		fieldMap.put("qnId", "问卷id");
		fieldMap.put("qnType", "问卷类型");
		fieldMap.put("eventType", "事件类型");
		fieldMap.put("tapTime", "时间");
		fieldMap.put("timeInterval", "时段");
		fieldMap.put("timeLength", "时长(秒)");
		fieldMap.put("dayOfWeek", "星期");
		fieldMap.put("qnClassName", "问卷分类");
		fieldMap.put("gender", "性别");
		fieldMap.put("province", "省份");
		fieldMap.put("regionName", "城市");
		fieldMap.put("schoolName", "学校");
		fieldMap.put("tapSource", "来源");

		return fieldMap;
	}
	
	
	
}
