package com.newIns.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.LotteryMapper;
import com.newIns.dao.NiAdInfoMapper;
import com.newIns.dao.NiLotteryDictMapper;
import com.newIns.dao.award.AwardMapper;
import com.newIns.dao.award.NiAwardMapper;
import com.newIns.dao.page.ChannelDao;
import com.newIns.dao.survey.NiSurveyOrderMapper;
import com.newIns.dao.survey.NiSurveyQuestionnaireMapper;
import com.newIns.dao.survey.SurveyDeliveryDao;
import com.newIns.model.Lottery;
import com.newIns.model.LotteryBasicInfo;
import com.newIns.model.NiAdInfo;
import com.newIns.model.NiLotteryDict;
import com.newIns.model.award.NiAward;
import com.newIns.model.page.Channel;
import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.service.SurveyDeliveryService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.DateUtil;
import com.newIns.tools.FileUtil;
import com.newIns.tools.LotteryGenerator;
import com.newIns.tools.OperateTool;
import com.newIns.tools.StrUtils;
import com.newIns.web.delivery.SurveyDeliveryController;

@Service
public class SurveyDeliveryServiceImp implements SurveyDeliveryService{
	private static Logger log = Logger.getLogger(SurveyDeliveryController.class);
	@Resource
	private SurveyDeliveryDao niSurveyDeliveryWanxMapper;
	@Resource
	private NiAdInfoMapper niAdInfoMapper;
	@Resource
	private NiSurveyOrderMapper niSurveyOrderMapper;
	@Resource
	private NiSurveyQuestionnaireMapper niSurveyQuestionnaireMapper;
	
//	@Resource
//	private NiAwardMapper niAwardMapper;
	@Resource
	private AwardMapper awardMapper;
	@Resource
	private NiLotteryDictMapper niLotteryDictMapper;
	
	@Resource
	private LotteryMapper lotteryMapper;
	@Autowired
	private ChannelDao channelDao;
	/**
	 * 列表查询
	 */
	public List<NiSurveyDeliveryWanxVO> selectList(Map<String, Object> hashMap) {
		List<NiSurveyDeliveryWanxVO> selectList = niSurveyDeliveryWanxMapper.selectList(hashMap);
		return selectList;
	}

	//保存调查问卷投放表
	public int insert(SurveyDelivery record) {
		int insert = niSurveyDeliveryWanxMapper.insert(record);
		return insert;
	}

	@Override
	public SurveyDelivery selectByPrimaryKey(Integer deliveryid) {
		SurveyDelivery selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(deliveryid);
		return selectByPrimaryKey;
	}
	
	/**
	 * 运营管理 定时任务
	 * @param time  单位:小时
	 * @param num	增长目标
	 */
	@Transactional
	public Runnable niSurveyDeliveryWanxOperateSaveTool(final int sqnId,float time, final long num,final int deliveryid,
			final int trueOrderNum,final SurveyDeliveryDao niSurveyDeliveryWanxMapper,final SurveyDelivery niSurveyDeliveryWanx){
		//把时间拆分为多少分钟
				final int frequencyNum = (int) (time*6);   //次数
				//计算每次需要增长的平均数量
				final long averageNum = num/frequencyNum;
				//一分钟时间
//				final long timeInterval = 1000*60*10; //生产时  10分钟
				final long timeInterval = 1000*10;   //开发测试   10秒钟
				
				log.info("===========被拆分的次数 :"+frequencyNum+"==================>>每次需要增长的平均数量 ："+averageNum);
				//定义线程
				Runnable runnable = new Runnable() {
					public void run() {
//						HashMap<String, Object> operateMap = null;
						//变量 a
						long a = 0;
						//循环每分钟执行一次
						for(int i=1;i<=frequencyNum ;i++){
							try {
								Thread.sleep(timeInterval);
								long averageNumRandom = 0;
								while(averageNumRandom<averageNum-3 || averageNumRandom>averageNum+3){
									averageNumRandom = (long) (Math.random()*1000);
								}
								a += averageNumRandom;
								log.info("==================生成要累加的随机数为: "+a);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							//显示收集份数等于模拟份数+实际份数
							niSurveyDeliveryWanx.setCollectednum((int) (a+trueOrderNum));
							niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
							
//							operateMap = OperateTool.getOperateMap();
//							operateMap.put(""+deliveryid, a);
						}
						niSurveyDeliveryWanx.setCollectednum((int) (trueOrderNum+num));
						niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
						
//						operateMap.put(""+deliveryid, num);
					}
				};
				return runnable;
	}
	/**
	 * 启动定时任务
	 * @param time
	 * @param num
	 */
	public void niSurveyDeliveryWanxOperateSave(int sqnId,float time, long num,int deliveryid,int trueOrderNum){
		
		SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(deliveryid);
		
		Runnable runnable = niSurveyDeliveryWanxOperateSaveTool(sqnId,time, num, deliveryid, trueOrderNum,niSurveyDeliveryWanxMapper,niSurveyDeliveryWanx);
		Thread thread = new Thread(runnable);
		thread.start();
	}

	/**
	 * 根据主键选择性修改信息
	 */
	public int updateByPrimaryKeySelective(SurveyDelivery record) {
		int updateByPrimaryKeySelective = niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective;
	}

	/**
	 * 批量修改问卷状态
	 */
	public int updateStatuByIds(Map<String, Object> retMap) {
		int updateStatuByIds = niSurveyDeliveryWanxMapper.updateStatuByIds(retMap);
		return updateStatuByIds;
	}

	//保存设置
	@Transactional
	public AjaxResult niSurveyDeliveryWanxSetUPSave(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String advertisementId = request.getParameter("advertisementId");  //广告位
		String conditionId = request.getParameter("conditionId");  //答题时长
		String deliveryid = request.getParameter("deliveryid");    //投放管理id
		
		if(StrUtils.isNotEmpty(deliveryid)){
			SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			Integer advertisement_Id = null;
			if(advertisementId!=null && !advertisementId.trim().equals("")){
				advertisement_Id = Integer.valueOf(advertisementId);
				//判断广告id是否存在
				NiAdInfo selectByPrimaryKey = niAdInfoMapper.selectByPrimaryKey(advertisement_Id);
				if(selectByPrimaryKey==null){
					return AjaxResult.errorResult("输入的广告id不存在");
				}
			}
			Float condition_Id = null;
			if(conditionId!=null && !conditionId.trim().equals("")){
				condition_Id = Float.valueOf(conditionId);
			}
			
			niSurveyDeliveryWanx.setAdid(advertisement_Id);
			if(condition_Id!=null){
				niSurveyDeliveryWanx.setlTime((int) (condition_Id*60));
			}
			
			try {
				int updateByPrimaryKeySelective = niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
				if(updateByPrimaryKeySelective>0){
					log.info("=======================>>添加设置成功");
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "添加设置成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保持异常");
			}
			
		}else{
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}

	//跳转到设置
	public void niSurveyDeliveryWanxSetUPJump(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryid);
		//根据投放id查询信息,将数据显示到页面
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer delivery_id = Integer.valueOf(deliveryid);
			SurveyDelivery selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			if(selectByPrimaryKey!=null){
				model.addAttribute("niSurveyDeliveryWanx", selectByPrimaryKey);
			}
		}
		//查询所有能添加的广告信息
		List<NiAdInfo> selectList = niAdInfoMapper.selectList();
		if(selectList!=null){
			model.addAttribute("niAdInfoList", selectList);
		}
		
		
	}

	//跳转到运营调整
	public void niSurveyDeliveryWanxOperateJump(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//查询出真实问卷显示数量
		int trueOrderNum = 0;
		if(StrUtils.isNotEmpty(deliveryid)){
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(Integer.valueOf(deliveryid));
			if(findSurQueBy_DelId!=null){
				Integer sqnId = findSurQueBy_DelId.getSqnid();
				
				trueOrderNum = niSurveyOrderMapper.selectCountNumbysqnId(Integer.valueOf(sqnId));
			}
			
			SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			if(niSurveyDeliveryWanx!=null){
				model.addAttribute("niSurveyDeliveryWanx", niSurveyDeliveryWanx);
			}
		}
		model.addAttribute("trueOrderNum", trueOrderNum);
		model.addAttribute("deliveryid", deliveryid);
	}

	//跳转到信息
	public void niSurveyDeliveryWanxMESJUMP(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer delivery_id = Integer.valueOf(deliveryid);
			SurveyDelivery selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			if(selectByPrimaryKey!=null){
				Date btime = selectByPrimaryKey.getBtime();
				Date etime = selectByPrimaryKey.getEtime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String btime_str = "";
				String etime_str = "";
				if(btime!=null && etime!=null){
					 btime_str = simpleDateFormat.format(btime);
					 etime_str = simpleDateFormat.format(etime);
				}
				model.addAttribute("niSurveyDeliveryWanx", selectByPrimaryKey);
				model.addAttribute("btime_str", btime_str);
				model.addAttribute("etime_str", etime_str);
			}
			
			//根据投放id 查询调查问卷信息
			
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(delivery_id);
			if(findSurQueBy_DelId!=null){
				model.addAttribute("niSurveyQuestionnaire", findSurQueBy_DelId);
				model.addAttribute("sqnId", findSurQueBy_DelId.getSqnid());
			}
			model.addAttribute("deliveryid", deliveryid);
			
		}
		
		//查询定奖 在页面进行展示
//		List<NiAward> selectList = niAwardMapper.selectList();
		List<NiAward> findAll = awardMapper.findAll();
		
		if(findAll!=null && findAll.size()>0){
			model.addAttribute("NiAward_list", findAll);
		}

	}
	
	

	// 保存信息
	@Transactional
	public AjaxResult niSurveyDeliveryWanxSaveMES(HttpServletRequest request,
			HttpServletResponse response, Model model,MultipartFile picFile) {
		AjaxResult ajaxResult = new AjaxResult();
		
		String btime = request.getParameter("datepicker");
		String etime = request.getParameter("datepicker1");
		String collectnum = request.getParameter("collectnum");
		String showdes = request.getParameter("showdes");
		String tag1str = request.getParameter("tag1str");
		String tag2str = request.getParameter("tag2str");
		String tag3str = request.getParameter("tag3str");
		String tag4str = request.getParameter("tag4str");
		String tag5str = request.getParameter("tag5str");
		
		String relatedStr1 = request.getParameter("relatedStr1");
		String relatedUrl1 = request.getParameter("relatedUrl1");
		String relatedStr2 = request.getParameter("relatedStr2");
		String relatedUrl2 = request.getParameter("relatedUrl2");
		String relatedStr3 = request.getParameter("relatedStr3");
		String relatedUrl3 = request.getParameter("relatedUrl3");
		
		String deliveryid = request.getParameter("deliveryid");
		String showTitle = request.getParameter("showTitle");
		
		String resultMsg_str = request.getParameter("resultMsg"); //答题后结语
		
		String titleTag_str = request.getParameter("titleTag"); //标题标签
		
		String awardid = request.getParameter("awardid");   //固定奖励id
		String lotteryid = request.getParameter("lotteryid");  //随机抽奖id
		String preLotteryid_str = request.getParameter("preLotteryid");  //倾向抽奖id
		
		if(StrUtils.isEmpty(collectnum)){
			return AjaxResult.errorResult("请录入计划采集份数");
		}
		
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer deliveryId = Integer.valueOf(deliveryid);
			//当前投放信息
			SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(deliveryId);
			
			if(niSurveyDeliveryWanx!=null){
				//根据投放查询调查问卷id
				Integer sqnid = null;
				NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(deliveryId);
				if(findSurQueBy_DelId!=null){
					sqnid = findSurQueBy_DelId.getSqnid();
				}
				
				String jdbcUrl = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String formatDate = sdf.format(new Date());
				
				try {
					Map<String, Object> uploadFile = FileUtil.uploadFile(request, picFile, "img/qn/sqn/"+formatDate+"/"+sqnid);
					//获取文件的存储路径
					jdbcUrl = (String) uploadFile.get("jdbcUrl");
					log.info("===================>> 文件的存储路径 :" + jdbcUrl);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				//---------------------设置奖励规则开始---------------------
				
				//第一种情况   输入两种奖励
				if((StrUtils.isNotEmpty(awardid) && !awardid.trim().equals("0")) && StrUtils.isNotEmpty(lotteryid) ){
					return AjaxResult.errorResult("请勿选择多种奖励");
				}else if((StrUtils.isNotEmpty(awardid) && !awardid.trim().equals("0")) && StrUtils.isNotEmpty(preLotteryid_str)){
					return AjaxResult.errorResult("请勿选择多种奖励");
				}else if(StrUtils.isNotEmpty(lotteryid) && StrUtils.isNotEmpty(preLotteryid_str)){
					return AjaxResult.errorResult("请勿选择多种奖励");
				}else if((StrUtils.isNotEmpty(awardid) && !awardid.trim().equals("0")) && StrUtils.isNotEmpty(lotteryid) && StrUtils.isNotEmpty(preLotteryid_str)){
					return AjaxResult.errorResult("请勿选择多种奖励");
				}
				
				
				//对原来投放的奖励规则进行判断--------------
				int award1id_old = niSurveyDeliveryWanx.getAward1id();
				Integer lotteryid_old = niSurveyDeliveryWanx.getLotteryid();
				Integer evaluateId_old = niSurveyDeliveryWanx.getEvaluateId();  
				// 1, 如果原先设置的为定奖 
				if(award1id_old != 0 && lotteryid_old == null && evaluateId_old == null ){
					//如果此次选择了其他奖励,则提示不可选择
					if(StrUtils.isNotEmpty(lotteryid)){
						return AjaxResult.errorResult("奖励规则不能从定奖修改为随机抽奖");
					}
					if(StrUtils.isNotEmpty(preLotteryid_str)){
						return AjaxResult.errorResult("奖励规则不能从定奖修改为倾向抽奖");
					}
					//此次不能将定将删除 ,防止设置为其他奖励
					if(StrUtils.isEmpty(awardid) || awardid.trim().equals("0")){
						return AjaxResult.errorResult("定奖规则不能修改");
					}
					
					// 那么此次修改只能修改为定奖
					Integer awardid_new = null;
					if(StrUtils.isNotEmpty(awardid)){
						awardid_new = Integer.valueOf(awardid);
					}
					niSurveyDeliveryWanx.setAward1id(awardid_new);
					
				}
				
				//页面传递的抽奖id
				Integer lotteryid_new = null;  
				if(StrUtils.isNotEmpty(lotteryid)){
					lotteryid_new = Integer.valueOf(lotteryid);
				}
				
				// 获取投放创建日期 
				Date oldBirthday = niSurveyDeliveryWanx.getBirthday();
				String newBirthday = new SimpleDateFormat("yyyyMMdd").format(oldBirthday);
				
				// 2, 如果原先设置的为随机抽奖------------------>>
				if(lotteryid_old!=null && award1id_old == 0 && evaluateId_old == null){
					//此次将不能设置为 定奖,倾向抽奖
					if(StrUtils.isNotEmpty(awardid) && !awardid.trim().equals("0")){
						return AjaxResult.errorResult("随机抽奖规则不能修改为定奖");
					}
					if(StrUtils.isNotEmpty(preLotteryid_str)){
						return AjaxResult.errorResult("随机抽奖规则不能修改为倾向抽奖");
					}
					if(StrUtils.isEmpty(lotteryid) || (StrUtils.isNotEmpty(lotteryid) && !(lotteryid.trim().equals(lotteryid_old+""))) ){
						return AjaxResult.errorResult("随机抽奖规则不能修改");
					}
					
				}
				// 3, 如果原先设置的为倾向抽奖
				if(evaluateId_old!=null && award1id_old == 0 && lotteryid_old == null){
					//则不能修改为定奖 ,随机抽奖
					if(StrUtils.isNotEmpty(awardid) && !awardid.trim().equals("0")){
						return AjaxResult.errorResult("倾向抽奖规则不能修改为定奖");
					}
					if(StrUtils.isNotEmpty(lotteryid)){
						return AjaxResult.errorResult("倾向抽奖规则不能修改为随机抽奖");
					}
					//倾向抽奖规则不能取消 , 否则可以设置为其他种类奖励
					if(StrUtils.isEmpty(preLotteryid_str)){
						return AjaxResult.errorResult("倾向抽奖规则不能修改");
					}
					
					Integer preLotteryId = null;
					if(StrUtils.isNotEmpty(preLotteryid_str)){
						preLotteryId = Integer.valueOf(preLotteryid_str);
					}
					niSurveyDeliveryWanx.setEvaluateId(preLotteryId);
					
				}
				
				// 4, 如果原先设置的为没有奖励 , 即第一次添加奖励---------------->>
				if(award1id_old == 0 && lotteryid_old == null && evaluateId_old == null){
					if(StrUtils.isNotEmpty(preLotteryid_str)){
						niSurveyDeliveryWanx.setEvaluateId(Integer.valueOf(preLotteryid_str));
						
					}else if(StrUtils.isNotEmpty(awardid)){
						niSurveyDeliveryWanx.setAward1id(Integer.valueOf(awardid));
						
					}
					if(StrUtils.isNotEmpty(lotteryid)){
							//根据抽奖 id查询抽奖字典表
							NiLotteryDict niLotteryDict = niLotteryDictMapper.selectByPrimaryKey(lotteryid_new);
							if(niLotteryDict == null){
								return AjaxResult.errorResult("输入的抽奖id不存在奖池中");
							}
							try {
							//生成命运表
								//根据lotteryId抽奖id 查询各等级中奖的概率
								Lottery lottery = lotteryMapper.searchEditInfo(lotteryid_new);
								double award1Rate=0;
								if(lottery.getAward1Rate()!=null && !lottery.getAward1Rate().trim().equals("")){
									award1Rate = Double.valueOf(lottery.getAward1Rate().trim());
								}
								double award2Rate=0;
								if(lottery.getAward2Rate()!=null && !lottery.getAward2Rate().trim().equals("")){
									award2Rate = Double.valueOf(lottery.getAward2Rate().trim());
								}
								double award3Rate=0;
								if(lottery.getAward3Rate()!=null && !lottery.getAward3Rate().trim().equals("")){
									award3Rate = Double.valueOf(lottery.getAward3Rate().trim());
								}
								double[] awardRate={award3Rate,award2Rate,award1Rate};
								//调用生成中奖名单算法，初始化map存放随机生成的中奖信息，key为sequenceNum,value为awardRank
								Map<Integer,Integer> lotteryMap = new HashMap<Integer,Integer>();
								LotteryGenerator lotteryGenerator=new LotteryGenerator();
								
								//计划采集份数
								Integer collectnumId = null;
								if(StrUtils.isNotEmpty(collectnum)){
									collectnumId = Integer.valueOf(collectnum);
								}
								lotteryMap = lotteryGenerator.generateLottery(awardRate, collectnumId);	
								
								//获取系统日期，拼出表名
								String tableName="ni_lottery_"+newBirthday;
								
								List<LotteryBasicInfo> lotteryBasicInfoList = new ArrayList<LotteryBasicInfo>();
								for (Map.Entry<Integer, Integer> entry : lotteryMap.entrySet()){			
									LotteryBasicInfo lotteryBasicInfo = new LotteryBasicInfo();
									lotteryBasicInfo.setDeliveryId(deliveryId);
									lotteryBasicInfo.setQnType(1);
									lotteryBasicInfo.setChannel(1);
									lotteryBasicInfo.setLotteryId(lotteryid_new);
									lotteryBasicInfo.setSequenceNum(entry.getKey());
									lotteryBasicInfo.setAwardRank(entry.getValue());
									lotteryBasicInfoList.add(lotteryBasicInfo);					
								}
								Map<String,Object> retMap = new HashMap<String,Object>();
								retMap.put("tableName", tableName);
								retMap.put("lotteryBasicInfoList", lotteryBasicInfoList);
								
								try {
									lotteryMapper.generateLottery(retMap);
									lotteryMapper.insert_generateLottery(retMap);
								} catch (Exception e) {
									e.printStackTrace();
									return AjaxResult.errorResult("随机抽奖生成命运表失败");
								}
								
								log.info("===============生成命运表成功=================");
								int updateLotteryStatus = niSurveyDeliveryWanxMapper.updateLotteryReady(deliveryId);
								if(updateLotteryStatus>0){
									log.info("===============抽奖信息就绪！==============");
								}
							} catch (NumberFormatException e) {
								e.printStackTrace();
								return AjaxResult.errorResult("修改奖励规则失败!");
							}
						
							niSurveyDeliveryWanx.setLotteryid(lotteryid_new);
					}
				}
			
			   if(StrUtils.isNotEmpty(collectnum)){
					boolean matches = collectnum.matches("[1-9][0-9]*");
					if(!matches){
						return AjaxResult.errorResult("计划采集份数格式不正确");
					}
					niSurveyDeliveryWanx.setCollectnum(Integer.valueOf(collectnum));
				}else{
					//如果没有输入用户采集份数,默认为10000
					niSurveyDeliveryWanx.setCollectnum(10000);
				}
				if(StrUtils.isNotEmpty(btime)){
					
					Date newBtime = DateUtil.toUtilDateFromStrDateByFormat(btime, "yyyy-MM-dd");
					
					niSurveyDeliveryWanx.setBtime(newBtime);
					
				}
				if(StrUtils.isNotEmpty(etime)){
					Date newEtime = DateUtil.toUtilDateFromStrDateByFormat(etime, "yyyy-MM-dd");
					niSurveyDeliveryWanx.setEtime(newEtime);
				}
				
				niSurveyDeliveryWanx.setDeliveryid(deliveryId);
				
				niSurveyDeliveryWanx.setShowdes(showdes);
				niSurveyDeliveryWanx.setShowtitle(showTitle);
				niSurveyDeliveryWanx.setResultMsg(resultMsg_str);
				niSurveyDeliveryWanx.setImg(jdbcUrl);
				niSurveyDeliveryWanx.setTag1str(tag1str);
				niSurveyDeliveryWanx.setTag2str(tag2str);
				niSurveyDeliveryWanx.setTag3str(tag3str);
				niSurveyDeliveryWanx.setTag4str(tag4str);
				niSurveyDeliveryWanx.setTag5str(tag5str);
				
				niSurveyDeliveryWanx.setRelatedStr1(relatedStr1);
				niSurveyDeliveryWanx.setRelatedStr2(relatedStr2);
				niSurveyDeliveryWanx.setRelatedStr3(relatedStr3);
				niSurveyDeliveryWanx.setRelatedUrl1(relatedUrl1);
				niSurveyDeliveryWanx.setRelatedUrl2(relatedUrl2);
				niSurveyDeliveryWanx.setRelatedUrl3(relatedUrl3);
				
//				niSurveyDeliveryWanx.setStatus(1);    //设置投放状态   
				
				niSurveyDeliveryWanx.setuTime(new Date());
				niSurveyDeliveryWanx.setTitleTag(titleTag_str);  
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				SimpleDateFormat formatter = simpleDateFormat;
//				String birthday= formatter.format(new Date());
				//判断当前是否有birthday , 如果无则跟新为当前时间, 如果有则不更新
//				if(StrUtils.isEmpty(birthday_Old)){
//					niSurveyDeliveryWanx.setBirthday(birthday);
//				}
				
				try {
					//修改投放管理信息内容
					int updateByPrimaryKeySelective = niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
					if(updateByPrimaryKeySelective>0){
						log.info("=============================>>添加信息成功");
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "添加信息成功");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("======================>>添加投放问卷信息出现异常");
					return AjaxResult.errorResult("添加投放问卷信息出现异常");
				}
				ajaxResult.put("deliveryid", deliveryid);
				
				
			}
		}else{
			return AjaxResult.errorResult("出现异常");
		}
		
		return ajaxResult;
	}

	@Override
	public List<NiSurveyDeliveryWanxVO> selectListByqnId(Integer id) {
		List<NiSurveyDeliveryWanxVO> selectListByqnId = niSurveyDeliveryWanxMapper.selectListByqnId(id);
		return selectListByqnId;
	}

	//编辑投放管理
	public SurveyDelivery niSurveyDeliveryWanxEditor(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		SurveyDelivery selectByPrimaryKey = null;
		if(StrUtils.isNotEmpty(deliveryid)){
			
			Integer delivery_id = Integer.valueOf(deliveryid);
			selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			
		}
		return selectByPrimaryKey;
	}

	//保存编辑
	@Transactional
	public AjaxResult niSurveyDeliveryWanxEditorSave(HttpServletRequest request, Model model){
		AjaxResult ajaxResult = new AjaxResult();
		 String deliveryid = request.getParameter("deliveryid_show");
		 String sqnId = request.getParameter("sqnId");
		 
		 String collectNum = request.getParameter("collectNum_show");
		 String showTitle = request.getParameter("showTitle_show");
		 String begintime = request.getParameter("begintime_show");
		 String endtime = request.getParameter("endtime_show");
		 String jdbcUrl = request.getParameter("imgmes");
		 
		 String sqnname_show = request.getParameter("sqnname_show");
		 String editor_show = request.getParameter("editor_show");
		 String tag1Str_show = request.getParameter("tag1Str_show");
		 String tag2Str_show = request.getParameter("tag2Str_show");
		 String tag3Str_show = request.getParameter("tag3Str_show");
		 String tag4Str_show = request.getParameter("tag4Str_show");
		 String tag5Str_show = request.getParameter("tag5Str_show");
		 String showDes_show = request.getParameter("showDes_show");
		 
		 String adId = request.getParameter("adId");
		 
		 //判断广告id
		 Integer ad_Id = null;
		 if(StrUtils.isNotEmpty(adId)){
			 ad_Id = Integer.valueOf(adId);
		 }
		 if(ad_Id!=null){
			 //查询该广告id是否存在
			 NiAdInfo selectByPrimaryKey = niAdInfoMapper.selectByPrimaryKey(ad_Id);
			 if(selectByPrimaryKey==null){
				 return AjaxResult.errorResult("添加的广告id不存在");
			 }
		 }
		 
		 //根据id修改问卷名称和编辑人
		 NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
		 Integer sqn_Id = null;
		 if(StrUtils.isNotEmpty(sqnId)){
			 sqn_Id = Integer.valueOf(sqnId);
		 }
		 if(sqn_Id!=null){
			 niSurveyQuestionnaire.setSqnid(sqn_Id);
			 niSurveyQuestionnaire.setSqnname(sqnname_show);
			 niSurveyQuestionnaire.setEditor(editor_show);
			 
			 try {
				niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("==================>>保存编辑时问卷信息修改失败!");
				return AjaxResult.errorResult("出现异常!");
			}
		 }
		 
		//封装查询条件
		SurveyDelivery niSurveyDeliveryWanx = new SurveyDelivery();
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer deliveryId = Integer.valueOf(deliveryid);
			niSurveyDeliveryWanx.setDeliveryid(deliveryId);
		}
		if(StrUtils.isNotEmpty(collectNum)){
			Integer collect_Num = Integer.valueOf(collectNum);
			niSurveyDeliveryWanx.setCollectnum(collect_Num);
		}
		niSurveyDeliveryWanx.setShowtitle(showTitle);
		
//		Date beginTime = null;
//		Date endTime = null;
//		try {
//			beginTime = ThreadLocalDateUtil.parse(begintime);
//			endTime = ThreadLocalDateUtil.parse(endtime);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
		
		Date beginTime = DateUtil.toUtilDateFromStrDateByFormat(begintime, "dd/MM/yyyy");
		Date endTime = DateUtil.toUtilDateFromStrDateByFormat(endtime, "dd/MM/yyyy");
		
		niSurveyDeliveryWanx.setBtime(beginTime);
		niSurveyDeliveryWanx.setEtime(endTime);
		niSurveyDeliveryWanx.setuTime(new Date());
		niSurveyDeliveryWanx.setImg(jdbcUrl);
		niSurveyDeliveryWanx.setTag1str(tag1Str_show);
		niSurveyDeliveryWanx.setTag2str(tag2Str_show);
		niSurveyDeliveryWanx.setTag3str(tag3Str_show);
		niSurveyDeliveryWanx.setTag4str(tag4Str_show);
		niSurveyDeliveryWanx.setTag5str(tag5Str_show);
		
		niSurveyDeliveryWanx.setShowdes(showDes_show);
		niSurveyDeliveryWanx.setAdid(ad_Id);
		
		//更新数据
		try {
			int updateByPrimaryKeySelective = niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "编辑投放信息成功!");
			}else{
				return AjaxResult.errorResult("编辑失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("出现异常");
		}
		return ajaxResult;
	}

	//保存调查问卷  可能是编辑或者新增投放
	@Transactional
	public AjaxResult niSurveyDeliveryWanxSaveQue(HttpServletRequest request,Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String sqnId = request.getParameter("sqnId");
		String deliveryId = request.getParameter("deliveryid");
		
		String channelId_str = request.getParameter("channelId");
		
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channelId_str)){
			channelId = Integer.valueOf(channelId_str);
		}
		
		if(StrUtils.isNotEmpty(sqnId)){
			//判断输入的问卷id是否存在
			NiSurveyQuestionnaire selectByPrimaryKey = niSurveyQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(sqnId));
			if(selectByPrimaryKey!=null){
				//如果输入的问卷存在
				Integer staus = selectByPrimaryKey.getStaus();
				if(staus != 2){
					return AjaxResult.errorResult("必须定稿状态才能添加!");
				}
			}else{
				//问卷不存在
				return AjaxResult.errorResult("输入的问卷不存在,请重新输入!");
			}
			
			SurveyDelivery niSurveyDeliveryWanx = null;
			//判断是新增问卷,还是修改问卷
			if(StrUtils.isNotEmpty(deliveryId)){
				
				//投放已经添加,修改问卷
				
				niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
				niSurveyDeliveryWanx.setSqnid(Integer.valueOf(sqnId));  // 重设置 问卷id
				niSurveyDeliveryWanx.setChannelId(channelId);   // 重设 渠道id
				
				try {
					int updateByPrimaryKeySelective = niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
					if(updateByPrimaryKeySelective>0){
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "重新修改问卷成功");
					}else{
						return AjaxResult.errorResult("重新修改问卷失败!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("重新修改问卷失败!");
				}
				
				ajaxResult.put("deliveryid", deliveryId);
				
			}else{
				//投放未添加,创建新的投放,添加问卷
				niSurveyDeliveryWanx = new SurveyDelivery();
				
				log.info("======================>>投放未添加,创建新的投放,添加问卷!");
				niSurveyDeliveryWanx.setSqnid(Integer.valueOf(sqnId));
				niSurveyDeliveryWanx.setChannelId(channelId);  //
				niSurveyDeliveryWanx.setStatus(1);   //新增状态待投放 1
				
				niSurveyDeliveryWanx.setBirthday(new Date()); //设置birthday
				
				try {
					niSurveyDeliveryWanxMapper.insert(niSurveyDeliveryWanx);
					
					log.info("===========================>>新建投放管理调查问卷id添加成功");
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "添加问卷成功");
					//返回新添加的投放id
					int deliveryid = niSurveyDeliveryWanx.getDeliveryid();
					ajaxResult.put("deliveryid", deliveryid);
					
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult.put("success", false);
					ajaxResult.put("msg", "新建投放失败");
					ajaxResult.put("deliveryid", "");
					return ajaxResult;
				}

			}
		}else{
			ajaxResult.put("success", false);
			ajaxResult.put("msg", "问卷 Id 为空");
			ajaxResult.put("deliveryid", "");
			return ajaxResult;
		}
		return ajaxResult;
	}

	/**
	 * 运营调整模态框
	 */
	public AjaxResult niSurveyDeliveryWanxOperateModal(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String sqnId = request.getParameter("sqnId");
		//查询出真实问卷显示数量
		int trueOrderNum = 0;
		if(sqnId!=null && !sqnId.trim().equals("")){
			trueOrderNum = niSurveyOrderMapper.selectCountNumbysqnId(Integer.valueOf(sqnId));
		}
		
		if(deliveryid!=null){
			SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			if(niSurveyDeliveryWanx!=null){
				ajaxResult.put("niSurveyDeliveryWanx", niSurveyDeliveryWanx);
			}
		}
		ajaxResult.put("trueOrderNum", trueOrderNum);
		ajaxResult.put("deliveryid", deliveryid);
		ajaxResult.put("sqnId", sqnId);
		ajaxResult.put("success", true);
		return ajaxResult;
	}

	/**
	 * 根据真实数据 或者运营数据事实调整显示收集份数
	 */
	public AjaxResult AdjustSurveyDeliveryWanxOperate(HttpServletRequest request, HttpServletResponse response,Model model) {
		String bj = request.getParameter("bj");
		String deliveryid = request.getParameter("deliveryid");
		String sqnId = request.getParameter("sqnId");
		
		//查询真实数据
		int trueOrderNum = 0;
		if(StrUtils.isNotEmpty(sqnId)){
			trueOrderNum = niSurveyOrderMapper.selectCountNumbysqnId(Integer.valueOf(sqnId));
		}
		
		//查询当前投放的运营数据
		String operateNum = "";
		HashMap<String, Object> operateMap = OperateTool.getOperateMap();
		Object object = operateMap.get(deliveryid+"");
		if(object!=null){
			operateNum = object.toString();
		}
		Integer operate_Num = 0;
		if(StrUtils.isNotEmpty(operateNum)){
			operate_Num = Integer.valueOf(operateNum);
		}
		
		Integer delivery_id = null;
		if(StrUtils.isNotEmpty(deliveryid)){
			delivery_id = Integer.valueOf(deliveryid);
		}
		//封装数据
		SurveyDelivery niSurveyDeliveryWanx = new SurveyDelivery();
		niSurveyDeliveryWanx.setDeliveryid(delivery_id);
		if(StrUtils.isNotEmpty(bj)){
			if(bj.trim().equals("1")){
				//真实数据
				niSurveyDeliveryWanx.setCollectednum(trueOrderNum);
				niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
			}else{
				//调整数据
				niSurveyDeliveryWanx.setCollectednum(operate_Num);
				niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(niSurveyDeliveryWanx);
			}
			
		}

		return null;
	}

	/**
	 * 编辑投放,打开新的页面
	 */
	public void niSurveyDeliveryWanxEditorNewPage(HttpServletRequest request,
			Model model) {
		
		String deliveryid = request.getParameter("deliveryid");
		
		//查询所有 未被投放,并且处于定稿状态的调查问卷
		List<NiSurveyQuestionnaire> select_niSurveyQue_Dev = niSurveyQuestionnaireMapper.findSurQueByStau2();
		if(select_niSurveyQue_Dev!=null){
			model.addAttribute("select_niSurveyQue_Dev", select_niSurveyQue_Dev);
		}
		
		//根据该投放查询问卷信息
		if(StrUtils.isNotEmpty(deliveryid)){
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(Integer.valueOf(deliveryid));
			if(findSurQueBy_DelId!=null){
				Integer sqnId = findSurQueBy_DelId.getSqnid();
				model.addAttribute("sqnId", sqnId);
			}
		}
		model.addAttribute("deliveryid", deliveryid);
		
	}

	@Override
	public void niSurveyDeliveryWanxAddQue(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//查询所有 未被投放,并且处于定稿状态的调查问卷
//		List<NiSurveyQuestionnaire> select_niSurveyQue_Dev = niSurveyQuestionnaireService.select_niSurveyQue_Dev();
		//所有定稿
		List<NiSurveyQuestionnaire> select_niSurveyQue_Dev = niSurveyQuestionnaireMapper.findSurQueByStau2();
		
		if(select_niSurveyQue_Dev!=null){
			model.addAttribute("select_niSurveyQue_Dev", select_niSurveyQue_Dev);
		}
		
		if(StrUtils.isNotEmpty(deliveryid)){
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(Integer.valueOf(deliveryid));
			if(findSurQueBy_DelId!=null){
				Integer sqnId = findSurQueBy_DelId.getSqnid();
				model.addAttribute("sqnId", sqnId);
			}
		}
		
		model.addAttribute("deliveryid", deliveryid);
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		
		model.addAttribute("listChannel", listChannel);
		
	}

	//运营管理
	public AjaxResult niSurveyDeliveryWanxOperateSave(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String publisherTime = request.getParameter("publisherTime");   //时间
		String publisherNum = request.getParameter("publisherNum");    //目标增长数量
		String deliveryid = request.getParameter("deliveryid");
		String trueOrderNum = request.getParameter("trueOrderNum");   //真实数据
		
		if(StrUtils.isNotEmpty(deliveryid)){
			Float valueOf = null;
			if(StrUtils.isNotEmpty(publisherTime)){
				valueOf = Float.valueOf(publisherTime);
			}
			Long publisherNum2 = null;
			if(StrUtils.isNotEmpty(publisherNum)){
				 publisherNum2 = Long.valueOf(publisherNum);
			}
			Integer deliveryid2 = null;
			if(StrUtils.isNotEmpty(deliveryid)){
				 deliveryid2 = Integer.valueOf(deliveryid);
			}
			Integer trueOrderNum2 = null;
			if(StrUtils.isNotEmpty(trueOrderNum)){
				 trueOrderNum2 = Integer.valueOf(trueOrderNum);
			}
			Integer sqnid = null;
			//根据投放id 查询问卷信息
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(Integer.valueOf(deliveryid));
			if(findSurQueBy_DelId!=null){
				sqnid = findSurQueBy_DelId.getSqnid();
			}
			
			try {
				niSurveyDeliveryWanxOperateSave(sqnid,valueOf, publisherNum2, deliveryid2,trueOrderNum2);
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "设置运营管理成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("设置运营管理失败");
			}
			
		}else{
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}

	//请求显示收集份数
	public AjaxResult findShowOrderNum(HttpServletRequest request,HttpServletResponse response, Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		
		//查询出模拟增长的数据
		if(StrUtils.isNotEmpty(deliveryid)){
			
			//查询出真实问卷显示数量
			int trueOrderNum = 0;
			
			//根据投放id 查询问卷信息
			NiSurveyQuestionnaire findSurQueBy_DelId = niSurveyQuestionnaireMapper.findSurQueBy_DelId(Integer.valueOf(deliveryid));
			if(findSurQueBy_DelId!=null){
				Integer sqnid = findSurQueBy_DelId.getSqnid();
				trueOrderNum = niSurveyOrderMapper.selectCountNumbysqnId(Integer.valueOf(sqnid));
			}
			
			//查询该投放信息
			SurveyDelivery selectByPrimaryKey = null;
			if(StrUtils.isNotEmpty(deliveryid)){
				selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			}
			//显示收集份数
			Integer collectednum = null;
			if(selectByPrimaryKey!=null){
				collectednum = selectByPrimaryKey.getCollectednum();
			}
			ajaxResult.put("collectednum", collectednum);
			//模拟增长数量  == 显示收集份数 - 真实收集份数
			int simulationNum = 0;
			if(collectednum!=null){
				simulationNum = collectednum-trueOrderNum;
				if(simulationNum>=0){
					ajaxResult.put("simulationNum", simulationNum);
				}else{
					ajaxResult.put("simulationNum", 0);
				}
			}
		}
		return ajaxResult;
	}

	
	public void searchQueByIdOrName(HttpServletRequest request,Model model) {
		
		String qn_id_name_new = request.getParameter("searchIdorName");
		model.addAttribute("searchIdorName", qn_id_name_new);
		
		List<NiSurveyQuestionnaire> arrayList = new ArrayList<NiSurveyQuestionnaire>();
		
		if(StrUtils.isNotEmpty(qn_id_name_new)){
			
			//当作问卷名称搜索
			List<NiSurveyQuestionnaire> niSurveyQuestionnaireList = niSurveyQuestionnaireMapper.selectBySurveyQuestionnaireName(qn_id_name_new);
			if(niSurveyQuestionnaireList.size()>0){
				model.addAttribute("niSurveyQuestionnaireList", niSurveyQuestionnaireList);
			}else{
				//根据问卷名称查询为空  , 根据问卷id查询
				Integer sqnId = null;
				try {
					sqnId = Integer.valueOf(qn_id_name_new);
				} catch (NumberFormatException e) {
					log.info("输入查询数据不是数字");
				}
				NiSurveyQuestionnaire niSurveyQuestionnaire = niSurveyQuestionnaireMapper.selectByPrimaryKey(sqnId);
				if(niSurveyQuestionnaire!=null){
					arrayList.add(niSurveyQuestionnaire);
				}
				
				model.addAttribute("niSurveyQuestionnaireList", arrayList);
				
			}
			
		}
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		
		model.addAttribute("listChannel", listChannel);
		
	}

	// 选择模版
	public void choseTemplatePage(HttpServletRequest request, Model model) {
		
		HashMap<String, Object> hashMap = new HashMap<>();
		// 查询出所有的 调查 投放 信息 , 封装成list集合, 
		
		List<NiSurveyDeliveryWanxVO> selectList = niSurveyDeliveryWanxMapper.selectList(hashMap);
		
		model.addAttribute("selectList", selectList);
		
		String deliveryId = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryId);
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		model.addAttribute("listChannel", listChannel);
		
	}

	// 保存模版
	public AjaxResult saveTemplatePage(HttpServletRequest request, Model model) {
		  
		String TemplateDeliveryId_str = request.getParameter("TemplateDeliveryId");  // 模版 id
		String SurveyDeliveryId_str = request.getParameter("SurveyDeliveryId");  // 当前投放id
		
		Integer TemplateDeliveryId = null;
		if(StrUtils.isNotEmpty(TemplateDeliveryId_str)){
			TemplateDeliveryId = Integer.valueOf(TemplateDeliveryId_str);
		}
		
		Integer SurveyDeliveryId = null;
		if(StrUtils.isNotEmpty(SurveyDeliveryId_str)){
			SurveyDeliveryId = Integer.valueOf(SurveyDeliveryId_str);
		}
		
		// 查询出模版投放信息  
		SurveyDelivery surveyDelivery_temp = niSurveyDeliveryWanxMapper.selectByPrimaryKey(TemplateDeliveryId);
		
		// 查询出 当前投放信息
		SurveyDelivery surveyDelivery = niSurveyDeliveryWanxMapper.selectByPrimaryKey(SurveyDeliveryId);
		
		
		// 替换成当前得 投放 id, 渠道id 
		surveyDelivery_temp.setDeliveryid(surveyDelivery.getDeliveryid());
		surveyDelivery_temp.setChannelId(surveyDelivery.getChannelId());
		surveyDelivery_temp.setStatus(1);
		
		// 执行编辑
		try {
			niSurveyDeliveryWanxMapper.updateByPrimaryKeySelective(surveyDelivery_temp);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	// 筛选模版
	public void screenSurveyTemplatePage(HttpServletRequest request, Model model) {
		String TemplateDeliveryId_str = request.getParameter("choseSurveyTemplateDevId");  // 模版 id
		String SurveyDeliveryId_str = request.getParameter("SurveyDeliveryId");  // 当前投放id
		String choseChannelId_str = request.getParameter("choseChannelId");   //渠道id
		
		HashMap<Object, Object> hashMap = new HashMap<>();
		
		hashMap.put("templateDeliveryId", TemplateDeliveryId_str);
		hashMap.put("choseChannelId", choseChannelId_str);
		
		List<NiSurveyDeliveryWanxVO> surveyTemplateList = niSurveyDeliveryWanxMapper.screenSurveyTemplatePage(hashMap);
		
		
		model.addAttribute("selectList", surveyTemplateList);
		model.addAttribute("deliveryid", SurveyDeliveryId_str);
		model.addAttribute("choseSurveyTemplateDevId", TemplateDeliveryId_str);
		model.addAttribute("choseChannelId", choseChannelId_str);
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		model.addAttribute("listChannel", listChannel);
	}
}
