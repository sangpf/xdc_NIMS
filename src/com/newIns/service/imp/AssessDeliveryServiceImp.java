package com.newIns.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.LotteryMapper;
import com.newIns.dao.NiAdInfoMapper;
import com.newIns.dao.NiLotteryDictMapper;
import com.newIns.dao.NiSuperWanxMapper;
import com.newIns.dao.assess.AssessDeliveryDao;
import com.newIns.dao.assess.NiAssessOrderMapper;
import com.newIns.dao.assess.NiAssessQuestionnaireMapper;
import com.newIns.dao.award.AwardMapper;
import com.newIns.dao.page.ChannelDao;
import com.newIns.model.Lottery;
import com.newIns.model.LotteryBasicInfo;
import com.newIns.model.NiAdInfo;
import com.newIns.model.NiLotteryDict;
import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.assess.NiAssessDeliveryWanxVo;
import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.model.assess.NiAssessWanx;
import com.newIns.model.award.NiAward;
import com.newIns.model.page.Channel;
import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxKey;
import com.newIns.service.AssessDeliveryService;
import com.newIns.service.NiAssessWanxService;
import com.newIns.service.NiDaily3updateWanxService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.DateUtil;
import com.newIns.tools.FileUtil;
import com.newIns.tools.LotteryGenerator;
import com.newIns.tools.OperateTool;
import com.newIns.tools.PatternUtils;
import com.newIns.tools.StrUtils;

/**
 * 测评问卷玩校投放
 * @author Sang
 *
 */
@Service
public class AssessDeliveryServiceImp implements AssessDeliveryService{
	private static Logger log = Logger.getLogger(AssessDeliveryServiceImp.class);
	@Resource
	private AssessDeliveryDao assessDeliveryWanxMapper;
	@Resource
	private NiAssessQuestionnaireMapper niAssessQuestionnaireMapper;
	@Resource
	private NiAdInfoMapper niAdInfoMapper;
	@Resource
	private NiAssessOrderMapper niAssessOrderMapper;
	@Resource
	private NiSuperWanxMapper niSuperWanxMapper;
	@Resource
	private AwardMapper awardMapper;
	@Resource
	private NiLotteryDictMapper niLotteryDictMapper;
	@Resource
	private LotteryMapper lotteryMapper;
	@Resource
	private NiDaily3updateWanxService niDaily3updateWanxService;
	@Resource
	private NiAssessWanxService niAssessWanxService;
	@Autowired
	private ChannelDao channelDao;

	/**
	 * 列表查询
	 */
	public List<NiAssessDeliveryWanxVo> selectList(Map<String, Object> hashMap) {
		List<NiAssessDeliveryWanxVo> selectList = this.assessDeliveryWanxMapper.selectList(hashMap);
		return selectList;
	}

	/**
	 * 保存测评问卷投放管理
	 */
	@Transactional
	public AjaxResult NiAssessDeliveryWanxSave(HttpServletRequest request,Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String aqnId = request.getParameter("sqnId");
		String deliveryId = request.getParameter("deliveryid");
		
		String channelId_str = request.getParameter("channelId");
		
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channelId_str)){
			channelId = Integer.valueOf(channelId_str);
		}
		
		if(StrUtils.isNotEmpty(aqnId)){
			//判断输入的问卷id是否存在
			NiAssessQuestionnaire selectByPrimaryKey = niAssessQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(aqnId));
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
			
			AssessDelivery niAssessDeliveryWanx = null;
			//判断是新增问卷,还是修改问卷
			if(StrUtils.isNotEmpty(deliveryId)){
				//查询该投放的信息
				niAssessDeliveryWanx = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
				//投放已经添加,修改问卷
				ajaxResult.put("deliveryid", deliveryId);
				log.info("======================>>投放已经添加,修改问卷!");
				niAssessDeliveryWanx.setAqnId(Integer.valueOf(aqnId));
				niAssessDeliveryWanx.setChannelId(channelId);
				
				try {
					int updateByPrimaryKeySelective = assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
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
				
			}else{
				niAssessDeliveryWanx = new AssessDelivery();
				//投放未添加,创建新的投放,添加问卷
				log.info("======================>>投放未添加,创建新的投放,添加问卷!");
				niAssessDeliveryWanx.setAqnId(Integer.valueOf(aqnId));
				niAssessDeliveryWanx.setChannelId(channelId);
				niAssessDeliveryWanx.setStatus(1);
				
				try {
					assessDeliveryWanxMapper.insert(niAssessDeliveryWanx);
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "添加问卷成功");
					//返回新添加的投放id
					int deliveryid = niAssessDeliveryWanx.getDeliveryId();
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
	 * 跳转到信息添加页面
	 */
	public void NiAssessDeliveryWanxMESJump(HttpServletRequest request,Model model) {
		
		String deliveryid = request.getParameter("deliveryid");
		
		if(StrUtils.isNotEmpty(deliveryid)){
			model.addAttribute("deliveryid", deliveryid);
			
			Integer delivery_id = Integer.valueOf(deliveryid);
			
			AssessDelivery selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			
			if(selectByPrimaryKey!=null){
				
				Date btime = selectByPrimaryKey.getbTime();
				Date etime = selectByPrimaryKey.geteTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if(btime!=null && etime!=null){
					String b_time = simpleDateFormat.format(btime);
					String e_time = simpleDateFormat.format(etime);
					model.addAttribute("b_time", b_time);
					model.addAttribute("e_time", e_time);
				}
				model.addAttribute("niAssessDeliveryWanx", selectByPrimaryKey);
			}
			
			//根据投放id查询问卷信息
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(delivery_id);
			if(findAssQue_byDelId!=null){
				//返回页面
				model.addAttribute("niAssessQuestionnaire", findAssQue_byDelId);
				
			}
		}
		
		//查询定奖 在页面进行展示
		List<NiAward> findAll = awardMapper.findAll();
		
		if(findAll!=null && findAll.size()>0){
			model.addAttribute("NiAward_list", findAll);
		}

	}

	/**
	 * 保存 测评投放 信息
	 */
	public AjaxResult NiAssessDeliveryWanxMESSave(HttpServletRequest request,Model model,
		 MultipartFile file ) {
		AjaxResult ajaxResult = new AjaxResult();
		String datepicker = request.getParameter("datepicker");   //发布时间
		String datepicker1 = request.getParameter("datepicker1");   //截至时间
		String collectnum = request.getParameter("collectnum");   //计划采集份数
		String showdes = request.getParameter("showdes");   //外简介
		String showTitle = request.getParameter("showTitle");
		String tag1str = request.getParameter("tag1Str");   //标签
		String tag2str = request.getParameter("tag2Str");
		String tag3str = request.getParameter("tag3Str");
		String tag4str = request.getParameter("tag4Str");
		String tag5str = request.getParameter("tag5Str");
		String deliveryid = request.getParameter("deliveryid");
		
		String relatedStr1 = request.getParameter("relatedStr1");
		String relatedUrl1 = request.getParameter("relatedUrl1");
		String relatedStr2 = request.getParameter("relatedStr2");
		String relatedUrl2 = request.getParameter("relatedUrl2");
		String relatedStr3 = request.getParameter("relatedStr3");
		String relatedUrl3 = request.getParameter("relatedUrl3");
		
		
		String awardid = request.getParameter("awardid");
		String lotteryid = request.getParameter("lotteryid");
		
		String resultMsg = request.getParameter("resultMsg");
		
		String titleTag = request.getParameter("titleTag"); //标题标签
		
		if(StrUtils.isNotEmpty(deliveryid)){
			
			//根据投放id查询测评问卷信息
			Integer aqnId = null;
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryid));
			if(findAssQue_byDelId!=null){
				aqnId = findAssQue_byDelId.getAqnid();
			}
			
			//查询当前投放信息
			AssessDelivery niAssessDeliveryWanx = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			//拼接时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String formatDate = sdf.format(new Date());
			
			//图片存储到数据库的路径
			String jdbcUrl = "";
			try {
				Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/aqn/"+formatDate+"/"+aqnId);
				if(uploadFile!=null){		
					//获取文件的存储路径
					jdbcUrl = (String) uploadFile.get("jdbcUrl");
					ajaxResult.put("jdbcUrl", jdbcUrl);
					
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Date datepickerdate = null;
			if(StrUtils.isNotEmpty(datepicker)){
				 datepickerdate = DateUtil.toUtilDateFromStrDateByFormat(datepicker, "yyyy-MM-dd");
			}
			Date datepicker1date = null;
			if(StrUtils.isNotEmpty(datepicker1)){
				datepicker1date = DateUtil.toUtilDateFromStrDateByFormat(datepicker1, "yyyy-MM-dd");
			}
			
			//当前投放的抽奖id
			Integer lotteryid2 = niAssessDeliveryWanx.getLotteryId();
			//第一种情况   输入两种奖励
			if((awardid != null && !awardid.trim().equals("") && !awardid.trim().equals("0")) && (lotteryid != null && !lotteryid.trim().equals("")) ){
				return AjaxResult.errorResult("请勿选择两种奖励");
			}
			if(StrUtils.isNotEmpty(awardid)){
				//如果当前投放已经保存了抽奖 , 则不能保存定奖
				if(lotteryid2!=null){
					if(!awardid.trim().equals("0")){
						return AjaxResult.errorResult("抽奖规则已定,不要轻易改动");
					}
				}
				// 定奖
				Integer valueOf = Integer.valueOf(awardid);
				niAssessDeliveryWanx.setAward1Id(valueOf);
				
			}
			if(StrUtils.isNotEmpty(lotteryid)){
				//抽奖
				Integer valueOf = Integer.valueOf(lotteryid);
				NiLotteryDict selectByPrimaryKey = niLotteryDictMapper.selectByPrimaryKey(valueOf);
				if(selectByPrimaryKey == null){
					return AjaxResult.errorResult("输入的抽奖id不存在奖池中");
				}else{
					//存在
					niAssessDeliveryWanx.setLotteryId(valueOf);
				}
			}
			
			niAssessDeliveryWanx.setbTime(datepickerdate);
			niAssessDeliveryWanx.seteTime(datepicker1date);
			if(StrUtils.isNotEmpty(collectnum)){
				try {
					niAssessDeliveryWanx.setCollectNum(Integer.valueOf(collectnum));
				} catch (NumberFormatException e) {
//					e.printStackTrace();
					return AjaxResult.errorResult("计划采集份数有误");
				}
			}else{
				//如果未设置采集份数,默认为10000
//				niAssessDeliveryWanx.setCollectnum(10000);
				return AjaxResult.errorResult("请录入计划采集份数");
			}
			niAssessDeliveryWanx.setShowDes(showdes);
			niAssessDeliveryWanx.setShowTitle(showTitle);
			niAssessDeliveryWanx.setTag1Str(tag1str);
			niAssessDeliveryWanx.setTag2Str(tag2str);
			niAssessDeliveryWanx.setTag3Str(tag3str);
			niAssessDeliveryWanx.setTag4Str(tag4str);
			niAssessDeliveryWanx.setTag5Str(tag5str);
			
			niAssessDeliveryWanx.setRelatedStr1(relatedStr1);
			niAssessDeliveryWanx.setRelatedStr2(relatedStr2);
			niAssessDeliveryWanx.setRelatedStr3(relatedStr3);
			niAssessDeliveryWanx.setRelatedUrl1(relatedUrl1);
			niAssessDeliveryWanx.setRelatedUrl2(relatedUrl2);
			niAssessDeliveryWanx.setRelatedUrl3(relatedUrl3);		
			
			niAssessDeliveryWanx.setResultMsg(resultMsg);  // 答题后结果提示语
			niAssessDeliveryWanx.setTitleTag(titleTag);
			
			niAssessDeliveryWanx.setImg(jdbcUrl);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String birthday= formatter.format(new Date());
			
			String birthday_old = niAssessDeliveryWanx.getBirthday();
			if(StrUtils.isEmpty(birthday_old)){
				
				niAssessDeliveryWanx.setBirthday(birthday);
				
			}
			
			
			//判断该投放有没有抽奖id,如果没有抽奖,才可以生产命运表
			if(lotteryid2==null){
				try {
					//生成命运表
					if(lotteryid != null && !lotteryid.trim().equals("")){
						//根据lotteryId查询各等级中奖的概率
						Lottery lottery = lotteryMapper.searchEditInfo(Integer.valueOf(lotteryid));
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
						lotteryMap = lotteryGenerator.generateLottery(awardRate, Integer.valueOf(collectnum.trim()));
						//获取系统日期，拼出表名
						Date dt=new Date();
						SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
						String date=f.format(dt);
						date=date.replace("-", "");
						String tableName="ni_lottery_"+date;
						
						List<LotteryBasicInfo> lotteryBasicInfoList = new ArrayList<LotteryBasicInfo>();
						for (Map.Entry<Integer, Integer> entry : lotteryMap.entrySet()){					
							LotteryBasicInfo lotteryBasicInfo = new LotteryBasicInfo();
							lotteryBasicInfo.setDeliveryId(Integer.valueOf(deliveryid.trim()));
							lotteryBasicInfo.setQnType(2);
							lotteryBasicInfo.setChannel(1);
							lotteryBasicInfo.setLotteryId(Integer.valueOf(lotteryid.trim()));
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
						}
						
						log.info("===============生成命运表成功=================");
						int updateLotteryStatus = assessDeliveryWanxMapper.updateLotteryReady(Integer.valueOf(deliveryid.trim()));
						if(updateLotteryStatus>0){
							log.info("===============抽奖信息就绪！==============");
						}
					}		

				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("保存失败");
				}
			}
			
			try {
				int updateByPrimaryKey = assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
				if(updateByPrimaryKey>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保存失败");
			}
			
		}
		return ajaxResult;
	}

	/**
	 * 保存设置
	 */
	@Transactional
	public AjaxResult niAssessDeliveryWanxSetSave(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String conditionId = request.getParameter("conditionId");  //答题时间
		String advertisementId = request.getParameter("advertisementId"); //广告id
		String deliveryid = request.getParameter("deliveryid");
		
		AssessDelivery niAssessDeliveryWanx = null;
		//查询当前投放
		if(StrUtils.isNotEmpty(deliveryid)){
			niAssessDeliveryWanx = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
		}else{
			return AjaxResult.errorResult("出现异常");
		}
		
		if(niAssessDeliveryWanx!=null){
			Float lTime = null;
			if(StrUtils.isNotEmpty(conditionId)){
				 try {
					lTime = Float.valueOf(conditionId);
					niAssessDeliveryWanx.setlTime((int) (lTime*60));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return AjaxResult.errorResult("答题时间设置有误");
				}
			}
			Integer advId = null;
			if(StrUtils.isNotEmpty(advertisementId)){
				 try {
					advId = Integer.valueOf(advertisementId);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return AjaxResult.errorResult("广告id设置有误");
				}
			}
			if(advId!=null){
				//判断该广告是否存在
				NiAdInfo selectByPrimaryKey = niAdInfoMapper.selectByPrimaryKey(advId);
				if(selectByPrimaryKey!=null){
					//添加的广告id存在
					niAssessDeliveryWanx.setAdId(advId);
				}else {
					//添加的广告id不存在
					return AjaxResult.errorResult("选择的广告不存在");
				}
			}
			try {
				int updateByPrimaryKey = assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
				if(updateByPrimaryKey>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "添加设置成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("添加设置失败");
			}
		}else{
			return AjaxResult.errorResult("出现异常");
		}
		return ajaxResult;
	}

	/**
	 * 运营调整页面
	 */
	public void niAssessDeliveryWanxOperateJump(HttpServletRequest request,
			Model model) {
		String deliveryId = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryId);
		
		int selectCountNumbysqnId = 0;
		
		if(StrUtils.isNotEmpty(deliveryId)){
			AssessDelivery selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
			if(selectByPrimaryKey!=null){
				model.addAttribute("niAssessDeliveryWanx", selectByPrimaryKey);
			}
			
			//根据投放id 查询测评问卷信息
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryId));
			if(findAssQue_byDelId!=null){
				Integer aqnid = findAssQue_byDelId.getAqnid();
				
				selectCountNumbysqnId = niAssessOrderMapper.selectCountNumbysqnId(Integer.valueOf(aqnid));
			}
			
			model.addAttribute("realCount", selectCountNumbysqnId);
			
		}
		
	}
	
	/**
	 * 运营设置工具
	 * @param request
	 */
	public Runnable niAssessDeliveryWanxOperateSaveTool(HttpServletRequest request,final int realCount,
			final AssessDelivery niAssessDeliveryWanx){
		
		String operateTime = request.getParameter("publisherTime");    //时间 ： 小时
		final String operateNum = request.getParameter("publisherNum");     //目标增长数
		
		//获取时间和目标增长数，将时间化为分钟数，计算需要增长多少次，每一次大概增长多少数值
		Float opTime = null;
		if(operateTime!=null && !operateTime.trim().equals("")){
			opTime = Float.valueOf(operateTime);
		}
		Integer opeNum = null;   //目标数值
		if(StrUtils.isNotEmpty(operateNum)){
			 opeNum = Integer.valueOf(operateNum);
		}
		
		//目标增长数
		final int fnaCount = opeNum;
		//要循环次数为
		final int count = (int) (opTime*6);
		//大约每次需要增长
		final int alwayNum = opeNum/count;
		//一分钟时间
		final long timeInterval = 1000*60*10; //生产时  10分钟
//		final long timeInterval = 1000*10;   //开发测试   10秒钟
		
		Runnable runnable = new Runnable() {
			public void run() {
				//变量纪录累加的值
				long a = 0;
				for (int i =1;i<=count;i++) {
					try {
						Thread.sleep(timeInterval);
						long averageNumRandom = 0;//设置一个值，每次增加的数
						while(averageNumRandom<alwayNum-3 || averageNumRandom>alwayNum+3){
							averageNumRandom = (long) (Math.random()*1000);
						}
						a = a+averageNumRandom;
						log.info("==================生成要累加的随机数为: "+a);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					niAssessDeliveryWanx.setCollectedNum((int) (a+realCount));
					assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
					
//					operateMap = OperateTool.getOperateMap();
//					operateMap.put(""+deliveryId, a);
				}
				niAssessDeliveryWanx.setCollectedNum((int) (fnaCount+realCount));
				assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
//				operateMap.put(""+deliveryId, fnaCount);
			}
		};
		
		return runnable;
	}

	/**
	 * 保存运营设置
	 */
	public void niAssessDeliveryWanxOperateSave(HttpServletRequest request, Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//记录真实数据
		int realCount = 0;
		//当前投放信息
		AssessDelivery selectByPrimaryKey = null;
		//根据投放id 查询问卷信息
		if(StrUtils.isNotEmpty(deliveryid)){
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryid));
			if(findAssQue_byDelId!=null){
				Integer aqnid = findAssQue_byDelId.getAqnid();
				realCount = niAssessOrderMapper.selectCountNumbysqnId(aqnid);
			}else{
				
			}
			
			//查询投放记录信息
			selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			Runnable runnable = niAssessDeliveryWanxOperateSaveTool(request,realCount,selectByPrimaryKey);
			Thread thread = new Thread(runnable);
			thread.start();
			
		}

	}

	/**
	 * 请求显示测评问卷数据显示
	 */
	@Transactional
	public AjaxResult niAssessDeliveryWanxOperateShow(
			HttpServletRequest request, Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId = request.getParameter("deliveryid");
		
		if(StrUtils.isNotEmpty(deliveryId)){
			
			AssessDelivery selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
			Integer collectednum = null;
			if(selectByPrimaryKey!=null){
				collectednum = selectByPrimaryKey.getCollectedNum();
			}
			int realOrderCount = 0;
			
			//根据投放id 查询问卷
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryId));
			if(findAssQue_byDelId!=null){
				Integer aqnid = findAssQue_byDelId.getAqnid();
				
				realOrderCount = niAssessOrderMapper.selectCountNumbysqnId(Integer.valueOf(aqnid));
				
			}
			
			//模拟增长的数值
			int moniNum = 0;
			if(collectednum!=null){
				if(collectednum>=realOrderCount){
					moniNum = collectednum - realOrderCount;
				}
			}
			//显示收集份数
			ajaxResult.put("collectednum", collectednum);
			ajaxResult.put("moniNum", moniNum);
		}
		
		return ajaxResult;
	}

	//投放
	@Transactional
	public AjaxResult niAssessDeliveryWanxPut(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if(deliveryId_list!=null && !deliveryId_list.trim().equals("")){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				String deliveryId = deliveryId_arr[i];
				if(deliveryId!=null && !deliveryId.trim().equals("")){
					Integer deId = Integer.valueOf(deliveryId);
					arrayList.add(deId);
				}
			}
			hashMap.put("id_list", arrayList);
			hashMap.put("t_stat", 2);
		}
		hashMap.put("uTime", new Date());
		//批量发布
		try {
			int updateStatByMap = assessDeliveryWanxMapper.updateStatByMap(hashMap);
			if(updateStatByMap>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "投放成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("投放失败");
		}
		
		return ajaxResult;
	}

	//撤回
	@Transactional
	public AjaxResult niAssessDeliveryWanxWithdrawn(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(deliveryId_list!=null && !deliveryId_list.trim().equals("")){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				
				String deliveryId_str = deliveryId_arr[i];
				if(deliveryId_str!=null && !deliveryId_str.trim().equals("")){
					Integer deliveryId = Integer.valueOf(deliveryId_str);
					
					//校验  三更列表
					niDaily3updateWanxKey.setDeliveryid(deliveryId);
					niDaily3updateWanxKey.setQntype(1);  // 调查
					
					NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
					if(selectByPrimaryKey!=null){
						Integer pagestatus = selectByPrimaryKey.getPagestatus();
						if(pagestatus!=1){
							//在三更页面中不是待发布状态
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到三更列表页面中,无法撤回!");
						}
					}
					// 检查 测一发页面
					NiAssessWanx selectByPrimaryKey2 = niAssessWanxService.selectByPrimaryKey(deliveryId);
					if(selectByPrimaryKey2!=null){
						Integer pagestatus = selectByPrimaryKey2.getPagestatus();
						if(pagestatus != 1){
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到测一发页面中,无法撤回!");
						}
					}
					
					//拼接id集合
					arrayList.add(deliveryId);
				}
			}
			//执行修改
			hashMap.put("t_stat", 1);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = assessDeliveryWanxMapper.updateStatByMap(hashMap);
				if(updateStatByMap>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "撤回成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("撤回失败");
			}
		}
		
		
		return ajaxResult;
	}

	//暂停	
	@Transactional
	public AjaxResult niAssessDeliveryWanxStop(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(deliveryId_list!=null && !deliveryId_list.trim().equals("")){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				
				String deliveryId_str = deliveryId_arr[i];
				if(deliveryId_str!=null && !deliveryId_str.trim().equals("")){
					Integer deliveryId = Integer.valueOf(deliveryId_str);
					
					NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
					if(selectByPrimaryKey!=null){
						Integer pagestatus = selectByPrimaryKey.getPagestatus();
						if(pagestatus!=1){
							//在三更页面中不是待发布状态
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到三更列表页面中,无法暂停!");
						}
					}
					// 检查 测一发页面
					NiAssessWanx selectByPrimaryKey2 = niAssessWanxService.selectByPrimaryKey(deliveryId);
					if(selectByPrimaryKey2!=null){
						Integer pagestatus = selectByPrimaryKey2.getPagestatus();
						if(pagestatus != 1){
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到测一发页面中,无法暂停!");
						}
					}
					
					//拼接id集合
					arrayList.add(deliveryId);
				}
			}
			//执行修改
			hashMap.put("t_stat", 3);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = assessDeliveryWanxMapper.updateStatByMap(hashMap);
				if(updateStatByMap>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "暂停成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("暂停失败");
			}
		}
		
		return ajaxResult;
		
	}

	//恢复
	@Transactional
	public AjaxResult niAssessDeliveryWanxRegeneration(
			HttpServletRequest request, Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		if(deliveryId_list!=null && !deliveryId_list.trim().equals("")){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				String deliveryId = deliveryId_arr[i];
				if(deliveryId!=null && !deliveryId.trim().equals("")){
					Integer deId = Integer.valueOf(deliveryId);
					arrayList.add(deId);
				}
			}
			hashMap.put("id_list", arrayList);
			hashMap.put("t_stat", 2);
		}
		//批量发布
		try {
			int updateStatByMap = assessDeliveryWanxMapper.updateStatByMap(hashMap);
			if(updateStatByMap>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "恢复成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("恢复失败");
		}
		
		return ajaxResult;
	}

	//终止
	@Transactional
	public AjaxResult niAssessDeliveryWanxOver(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(deliveryId_list!=null && !deliveryId_list.trim().equals("")){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				String deliveryId_str = deliveryId_arr[i];
				if(deliveryId_str!=null && !deliveryId_str.trim().equals("")){
					Integer deliveryId = Integer.valueOf(deliveryId_str);
					
					//检查三更列表
					NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
					if(selectByPrimaryKey!=null){
						Integer pagestatus = selectByPrimaryKey.getPagestatus();
						if(pagestatus!=1){
							//在三更页面中不是待发布状态
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到三更列表页面中,无法终止!");
						}
					}
					// 检查 测一发页面
					NiAssessWanx selectByPrimaryKey2 = niAssessWanxService.selectByPrimaryKey(deliveryId);
					if(selectByPrimaryKey2!=null){
						Integer pagestatus = selectByPrimaryKey2.getPagestatus();
						if(pagestatus != 1){
							return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到测一发页面中,无法终止!");
						}
					}
					//拼接id集合
					arrayList.add(deliveryId);
				}
			}
			//执行修改
			hashMap.put("t_stat", 4);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = assessDeliveryWanxMapper.updateStatByMap(hashMap);
				if(updateStatByMap>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "终止成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("终止失败");
			}
		}
		
		return ajaxResult;
	}
	
	/**
	 * 定时任务
	 * @return
	 */
	public Runnable timeToolAutomatic(HttpServletRequest request){
		// 单位为秒  
		final long timeInterval = 1000*60;
		Runnable runnable = new Runnable() {
			
			public void run() {
				
				while(true){
					try {
						Thread.sleep(timeInterval);
						//判断当前时间是否已经到 截止时间 , 实际采集份数是否已经达到目标采集数量
						//每分钟 执行 判断时间，修改时间完成状态
						assessDeliveryWanxMapper.updateStatuByeTime();
						
						//每分钟执行，实时查询实际交易订单数，判断数量完成
						assessDeliveryWanxMapper.updateStatuByTagNum();
						
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		return runnable;
	}
	
	//自动定时任务
	public AjaxResult niAssessDeliveryWanxTimeTool(HttpServletRequest request,Model model) {
		
		Runnable timeToolAutomatic = timeToolAutomatic(request);
		Thread thread = new Thread(timeToolAutomatic);
		thread.start();
		
		return null;
	}

	//跳转到设置
	public void niAssessDeliveryWanxSetJump(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		//返回当前投放记录,用于数据回显
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer delivery_id = Integer.valueOf(deliveryid);
			AssessDelivery selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			if(selectByPrimaryKey!=null){
				model.addAttribute("niAssessDeliveryWanx", selectByPrimaryKey);
			}
		}
		//查询所有能添加的广告信息
		List<NiAdInfo> selectList = niAdInfoMapper.selectList();
		if(selectList!=null){
			model.addAttribute("niAdInfoList", selectList);
		}
		
		model.addAttribute("deliveryid", deliveryid);
	}

	@Override
	public List<NiAssessDeliveryWanxVo> selectByQnid(Integer id) {
		
		List<NiAssessDeliveryWanxVo> selectByQnid = assessDeliveryWanxMapper.selectByQnid(id);
		return selectByQnid;
	}

	/**
	 * 编辑页面跳转业务
	 */
	public AssessDelivery niAssessDeliveryWanxEditor(
			HttpServletRequest request, Model model) {
			String deliveryid = request.getParameter("deliveryid");
			AssessDelivery selectByPrimaryKey = null;
			if(StrUtils.isNotEmpty(deliveryid)){
				
				Integer delivery_id = Integer.valueOf(deliveryid);
				selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
			}
			return selectByPrimaryKey;
	}

	//保存编辑
	@Transactional
	public AjaxResult niAssessDeliveryWanxEditorSave(HttpServletRequest request,Model model) {
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
		 
		 //根据id修改问卷信息
		 NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		 Integer sqn_Id = null;
		 if(StrUtils.isNotEmpty(sqnId)){
			 sqn_Id = Integer.valueOf(sqnId);
		 }
		 if(sqn_Id!=null){
			 niAssessQuestionnaire.setAqnid(sqn_Id);
			 niAssessQuestionnaire.setAqnname(sqnname_show);
			 niAssessQuestionnaire.setEditor(editor_show);
			 try {
				niAssessQuestionnaireMapper.updateByPrimaryKeySelective(niAssessQuestionnaire);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("==================>>编辑测评问卷时修改问卷信息失败!");
				return AjaxResult.errorResult("出现异常!");
			}
		 }
		 
		 //封装查询条件
		 AssessDelivery niAssessDeliveryWanx = new AssessDelivery();
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer deliveryId = Integer.valueOf(deliveryid);
			niAssessDeliveryWanx.setDeliveryId(deliveryId);
		}
		if(StrUtils.isNotEmpty(collectNum)){
			Integer collect_Num = Integer.valueOf(collectNum);
			niAssessDeliveryWanx.setCollectNum(collect_Num);
		}
		niAssessDeliveryWanx.setShowTitle(showTitle);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = sdf.parse(begintime);
			endTime = sdf.parse(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		niAssessDeliveryWanx.setbTime(beginTime);
		niAssessDeliveryWanx.seteTime(endTime);
		niAssessDeliveryWanx.setcTime(new Date());
		niAssessDeliveryWanx.setImg(jdbcUrl);
		
		niAssessDeliveryWanx.setTag1Str(tag1Str_show);
		niAssessDeliveryWanx.setTag2Str(tag2Str_show);
		niAssessDeliveryWanx.setTag3Str(tag3Str_show);
		niAssessDeliveryWanx.setTag4Str(tag4Str_show);
		niAssessDeliveryWanx.setTag5Str(tag5Str_show);
		niAssessDeliveryWanx.setShowDes(showDes_show);
		niAssessDeliveryWanx.setAdId(ad_Id);
		//更新数据
		try {
			int updateByPrimaryKeySelective = assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
			if(updateByPrimaryKeySelective>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "编辑投放信息成功");
			}else{
				return AjaxResult.errorResult("出现异常!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("出现异常!");
		}
		
		return ajaxResult;
	}

	//运营调整模态框
	public AjaxResult niAssessDeliveryWanxOperateModal(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String aqnId = request.getParameter("aqnId");
		
		//查询出真实问卷显示数量
		int trueOrderNum = 0;
		if(aqnId!=null && !aqnId.trim().equals("")){
			trueOrderNum = niAssessOrderMapper.selectCountNumbysqnId(Integer.valueOf(aqnId));
		}
		
		if(deliveryid!=null){
			AssessDelivery niAssessDeliveryWanx = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			if(niAssessDeliveryWanx!=null){
				ajaxResult.put("niAssessDeliveryWanx", niAssessDeliveryWanx);
			}
		}
		ajaxResult.put("trueOrderNum", trueOrderNum);
		ajaxResult.put("deliveryid", deliveryid);
		ajaxResult.put("aqnId", aqnId);
		ajaxResult.put("success", true);
		return ajaxResult;
	}

	//请求显示收集份数
	public AjaxResult findNiAssShowOrderNum(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String aqnId = request.getParameter("aqnId");
		
		Integer aqn_Id = null;
		if(StrUtils.isNotEmpty(aqnId)){
			aqn_Id = Integer.valueOf(aqnId);
		}
		//查询出模拟增长的数据
/*		HashMap<String, Object> operateMap = OperateTool.getOperateMap();
		Object object = operateMap.get(deliveryid+"");
		String operateData = "";
		if(object!=null){
			operateData = object.toString();
		}
		log.info("==================>>查询出模拟增长数据 : "+ operateData);
		//模拟增长数
		ajaxResult.put("simulationNum",operateData );  */
		
		//查询该投放信息
		AssessDelivery selectByPrimaryKey = null;
		if(StrUtils.isNotEmpty(deliveryid)){
			selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
		}
		Integer collectednum = null;
		if(selectByPrimaryKey!=null){
			collectednum = selectByPrimaryKey.getCollectedNum();
		}
		ajaxResult.put("collectednum", collectednum);
		//真实收集份数
		int realCount = niAssessOrderMapper.selectCountNumbysqnId(aqn_Id);
		if(collectednum!=null){
			if((collectednum-realCount)>0){
				ajaxResult.put("simulationNum",collectednum-realCount );
			}else{
				ajaxResult.put("simulationNum",0 );
			}
		}

		log.info("=====================>>请求显示收集分数: 显示收集份数 : "+collectednum);
		return ajaxResult;
	}

	//根据真实数据 或者运营数据事实调整显示收集份数
	public AjaxResult AdjustAssesDeliveryWanxOperate(HttpServletRequest request) {
		String bj = request.getParameter("bj");
		String deliveryid = request.getParameter("deliveryid");
		String aqnId = request.getParameter("aqnId");
		
		//查询真实数据
		int trueOrderNum = 0;
		if(StrUtils.isNotEmpty(aqnId)){
			trueOrderNum = niAssessOrderMapper.selectCountNumbysqnId(Integer.valueOf(aqnId));
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
		AssessDelivery niAssessDeliveryWanx = new AssessDelivery();
		
		niAssessDeliveryWanx.setDeliveryId(delivery_id);
		if(StrUtils.isNotEmpty(bj)){
			if(bj.trim().equals("1")){
				//真实数据
				niAssessDeliveryWanx.setCollectedNum(trueOrderNum);
				assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
			}else{
				//调整数据
				niAssessDeliveryWanx.setCollectedNum(operate_Num);
				assessDeliveryWanxMapper.updateByPrimaryKeySelective(niAssessDeliveryWanx);
			}
			
		}

		return null;
	}

	/**
	 * 编辑测评投放  跳转到选择问卷页面
	 */
	public void niAssessDeliveryWanxQue_Edit(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//查询所有处于定稿状态的测评问卷
		List<NiAssessQuestionnaire> findNiAssQueByStat2 = niAssessQuestionnaireMapper.findNiAssQueByStat2();
		
		if(findNiAssQueByStat2.size()>0){
			model.addAttribute("niAssessQuestionnaire_list", findNiAssQueByStat2);
		}
		
		//根据投放id 查询测评问卷
		if(StrUtils.isNotEmpty(deliveryid)){
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryid));
			if(findAssQue_byDelId!=null){
				Integer aqnId = findAssQue_byDelId.getAqnid();
				model.addAttribute("aqnId", aqnId);
			}
			
		}
		
		
		model.addAttribute("deliveryid", deliveryid);
		
	}

	//跳转到添加问卷
	public void NiAssessDeliveryWanxNewJump(HttpServletRequest request,Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		// 所有定稿测评 
		List<NiAssessQuestionnaire> select_niAssQue_Dev = niAssessQuestionnaireMapper.findNiAssQueByStat2();
		
		if(select_niAssQue_Dev!=null && select_niAssQue_Dev.size()>0){
			model.addAttribute("select_niAssQue_Dev", select_niAssQue_Dev);
		}
		
		//根据投放id 查询测评问卷
		if(StrUtils.isNotEmpty(deliveryid)){
			NiAssessQuestionnaire findAssQue_byDelId = niAssessQuestionnaireMapper.findAssQue_byDelId(Integer.valueOf(deliveryid));
			if(findAssQue_byDelId!=null){
				Integer sqnId = findAssQue_byDelId.getAqnid();
				
				model.addAttribute("sqnId", sqnId);
			}
		}
		
		model.addAttribute("deliveryid", deliveryid);
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		model.addAttribute("listChannel", listChannel);
		
	}

	//根据问卷id 或者问卷名称查询测评问卷
	public void searchAssByIdOrName(HttpServletRequest request, Model model) {
		String searchIdorName = request.getParameter("searchIdorName");
		model.addAttribute("searchIdorName", searchIdorName);
		
		List<NiAssessQuestionnaire> newList = new ArrayList<NiAssessQuestionnaire>();
		
		if(StrUtils.isNotEmpty(searchIdorName)){
			//根据问卷名称查询
			List<NiAssessQuestionnaire> niAssessQuestionnaireList = niAssessQuestionnaireMapper.selectByAssessName(searchIdorName);
			if(niAssessQuestionnaireList.size()>0){
				model.addAttribute("niAssessQuestionnaireList", niAssessQuestionnaireList);
				
			}else{
				Integer new_aqnId = null;
				//根据问卷名称查询结果为空, 根据id查询
				try {
					new_aqnId = Integer.valueOf(searchIdorName);
				} catch (NumberFormatException e) {
				}
				
				NiAssessQuestionnaire selectByPrimaryKey = niAssessQuestionnaireMapper.selectByPrimaryKey(new_aqnId);
				if(selectByPrimaryKey!=null){
					newList.add(selectByPrimaryKey);
				}
				model.addAttribute("niAssessQuestionnaireList", newList);
				
			}
			
		}
		
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
		AssessDelivery assessDelivery_temp = assessDeliveryWanxMapper.selectByPrimaryKey(TemplateDeliveryId);
		
		// 查询出 当前投放信息
		AssessDelivery assessDelivery = assessDeliveryWanxMapper.selectByPrimaryKey(SurveyDeliveryId);
		
		// 替换成当前得 投放 id, 渠道id 
		assessDelivery_temp.setDeliveryId(assessDelivery.getDeliveryId());
		assessDelivery_temp.setChannelId(assessDelivery.getChannelId());
		assessDelivery_temp.setStatus(1);
		
		// 执行编辑
		try {
			assessDeliveryWanxMapper.updateByPrimaryKeySelective(assessDelivery_temp);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	// 选择模版
	public void choseTemplatePage(HttpServletRequest request, Model model) {
		HashMap<String, Object> hashMap = new HashMap<>();
		// 查询出所有的 调查 投放 信息 , 封装成list集合, 
		
		List<NiAssessDeliveryWanxVo> selectList = assessDeliveryWanxMapper.selectList(hashMap);
		
		model.addAttribute("selectList", selectList);
		
		String deliveryid = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryid);
		
	}

	// 付费测评 页面
	public void assessDelivery_PaySetPage(HttpServletRequest request,Model model) {
		
		String deliveryId_str = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryId_str);
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_str);
		
		if(deliveryId != null){
			// 查询 投放信息 
			AssessDelivery assessDelivery = assessDeliveryWanxMapper.selectByPrimaryKey(deliveryId);
			
			model.addAttribute("assessDelivery", assessDelivery);
		}
	}

	// 付费测评保存
	public AjaxResult assessDelivery_PaySet_Save(HttpServletRequest request,Model model) {

		String deliveryId_str = request.getParameter("deliveryId");
		String price_str = request.getParameter("price");
		String discountInfo = request.getParameter("discountInfo");
		String payTag1 = request.getParameter("payTag1");
		String payTag2 = request.getParameter("payTag2");
		String payTag3 = request.getParameter("payTag3");
		
		String cornerflag_str = request.getParameter("cornerFlag");
		
		Integer cornerflag = StrUtils.changeToInt(cornerflag_str);
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_str);
		if(deliveryId != null){
			
			boolean checkPirce = PatternUtils.checkPirce(price_str);
			if(!checkPirce){
				return AjaxResult.errorResult("价格录入有误");
			}
			
			Double price = Double.valueOf(price_str);
			
			AssessDelivery assessDelivery = new AssessDelivery();
			
			assessDelivery.setDeliveryId(deliveryId);
			assessDelivery.setPrice(price);
			assessDelivery.setDiscountInfo(discountInfo);
			assessDelivery.setPayTag1(payTag1);
			assessDelivery.setPayTag2(payTag2);
			assessDelivery.setPayTag3(payTag3);
			assessDelivery.setCornerFlag(cornerflag);
			
			try {
				assessDeliveryWanxMapper.updateByPrimaryKeySelective(assessDelivery);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保持失败");
			}
			
		}else{
			return AjaxResult.errorResult("保存失败");
		}
		return AjaxResult.successResult();
	}

	// 测评结果 头和尾保存
	public AjaxResult saveAssessResult_head_tail(HttpServletRequest request,
			Model model) {
		
		String deliveryId_str = request.getParameter("deliveryId");
		String headBox_content = request.getParameter("headBox_content");
		String tailBox_content = request.getParameter("tailBox_content");
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_str);
		if(deliveryId == null){
			return AjaxResult.errorResult("失败,投放id丢失");
		}
		
		AssessDelivery assessDelivery = new AssessDelivery();
		
		assessDelivery.setDeliveryId(deliveryId);
		assessDelivery.setHeadResultDetail(headBox_content);
		assessDelivery.setTailResultDetail(tailBox_content);
		
		try {
			assessDeliveryWanxMapper.updateAssessResult_head_tail(assessDelivery);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("编辑测评结果失败");
		}
		
		return AjaxResult.successResult("成功");
	}
	
	public void assessDelivery_ResultPage(HttpServletRequest request,
			Model model) {
		
		// 查询投放信息 , 返回到页面
		String deliveryId_Str = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryId_Str);
		
		Integer deliveryId = StrUtils.changeToInt(deliveryId_Str);
		
		AssessDelivery selectByPrimaryKey = assessDeliveryWanxMapper.selectByPrimaryKey(deliveryId);
		
		Integer aqnId = selectByPrimaryKey.getAqnId();
		
		NiAssessQuestionnaire assessQuestionnaire = niAssessQuestionnaireMapper.selectByPrimaryKey(aqnId);
		Integer resutShowType = assessQuestionnaire.getResutShowType();
		
		model.addAttribute("assessDelivery", selectByPrimaryKey);
		model.addAttribute("resutShowType", resutShowType);
		
	}


}
