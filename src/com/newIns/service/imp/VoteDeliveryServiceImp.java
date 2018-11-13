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
import com.newIns.dao.award.AwardMapper;
import com.newIns.dao.award.NiAwardMapper;
import com.newIns.dao.page.ChannelDao;
import com.newIns.dao.vote.NiVoteOrderMapper;
import com.newIns.dao.vote.NiVoteQuestionnaireMapper;
import com.newIns.dao.vote.VoteDeliveryDao;
import com.newIns.model.Lottery;
import com.newIns.model.LotteryBasicInfo;
import com.newIns.model.NiAdInfo;
import com.newIns.model.NiLotteryDict;
import com.newIns.model.award.NiAward;
import com.newIns.model.page.Channel;
import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxKey;
import com.newIns.model.page.NiSuperWanx;
import com.newIns.model.survery.NiSurveyDeliveryWanxVO;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.service.NiDaily3updateWanxService;
import com.newIns.service.NiSuperWanxService;
import com.newIns.service.VoteDeliveryService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.DateUtil;
import com.newIns.tools.FileUtil;
import com.newIns.tools.LotteryGenerator;
import com.newIns.tools.OperateTool;
import com.newIns.tools.StrUtils;

@Service
public class VoteDeliveryServiceImp implements VoteDeliveryService{
	private static Logger log = Logger.getLogger(AssessDeliveryServiceImp.class);
	@Resource
	private VoteDeliveryDao niVoteDeliveryWanxMapper;
	@Resource
	private NiVoteQuestionnaireMapper niVoteQuestionnaireMapper;
	@Resource
	private NiSuperWanxMapper niSuperWanxMapper;
	@Resource
	private NiAdInfoMapper niAdInfoMapper;
	@Resource
	private NiVoteOrderMapper niVoteOrderMapper;
//	@Resource
//	private NiAwardMapper niAwardMapper;
	
	@Resource
	private AwardMapper awardMapper;
	@Resource
	private NiLotteryDictMapper niLotteryDictMapper;
	@Resource
	private LotteryMapper lotteryMapper;
	@Resource
	private NiDaily3updateWanxService niDaily3updateWanxService;
	@Resource
	private NiSuperWanxService niSuperWanxService;
	@Autowired
	private ChannelDao channelDao;
	
	//列表查询
	@Transactional
	public void niVoteDeliveryWanxList(HttpServletRequest request, Model model) {
		//根据问卷名称，出题人获取测评问卷id
		String sqnpublish = request.getParameter("sqnpublish");  //问卷名称，出题人标记
		String sqnNameOrEditor = request.getParameter("sqnNameOrEditor");  //问卷名称，出题人
		String adclassid = request.getParameter("adclassid");     //广告
		String checkStats = request.getParameter("checkStats");   //被选中的投放状态    
		
		String channel_str = request.getParameter("channel");
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channel_str)){
			channelId = Integer.valueOf(channel_str);
		}
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		if(StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrEditor)){
			if(sqnpublish.trim().equals("1")){
				//出题人
				hashMap.put("editor", sqnNameOrEditor);
			}else if(sqnpublish.trim().equals("0")){
				//问卷名称
				hashMap.put("vqnName", sqnNameOrEditor);
			}else if(sqnpublish.trim().equals("2")){
				//投放id
				hashMap.put("delId", sqnNameOrEditor);
			}
		}
		if(adclassid!=null){
			if(adclassid.trim().equals("1")){  
				//有报告
				hashMap.put("adId", 1);
			}else if(adclassid.trim().equals("2")){
				//无报告
				hashMap.put("adId", 2);
			}else if(adclassid.trim().equals("0")){
				//不限
				hashMap.put("adId", 0);
			}
		}
		ArrayList<Integer> statList = new ArrayList<Integer>();
		if(checkStats!=null && !checkStats.trim().equals("")){
			String[] split = checkStats.split("\\|");
			for(int i=0;i<split.length;i++){
				String stau = split[i];
				statList.add(Integer.valueOf(stau));
			}
		}
		hashMap.put("statList", statList);
		hashMap.put("channelId", channelId);
		
		List<VoteDelivery> selectList = niVoteDeliveryWanxMapper.selectList(hashMap);
		model.addAttribute("niVoteDeliveryWanxs", selectList);
		
	}
	
	/**
	 * 修改状态工具类
	 * @param request
	 * @param statu
	 * @return
	 */
	public AjaxResult updateStatusTool(HttpServletRequest request,Integer statu){
		AjaxResult ajaxResult = new AjaxResult();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String deliveryId_list = request.getParameter("deliveryId_list");
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
		}
		//设置修改的状态
		hashMap.put("t_stat", statu);
		//设置操作时间
		hashMap.put("u_Time", new Date());
		//批量操作状态
		try {
			int updateStatByMap = niVoteDeliveryWanxMapper.updateStatByMap(hashMap);
			if(updateStatByMap>0){
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("出现异常");
		}
		return ajaxResult;
	}

	//投放
	@Transactional
	public AjaxResult niVoteDeliveryWanxPut(HttpServletRequest request,
			Model model) {
		AjaxResult updateStatusTool = updateStatusTool(request, 2);
		return updateStatusTool;
	}

	//撤回
	public AjaxResult niVoteDeliveryWanxWithdrawn(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryId_list = request.getParameter("deliveryId_list");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		//三更
		NiDaily3updateWanxKey niDaily3updateWanxKey = new NiDaily3updateWanxKey();
		
		if(StrUtils.isNotEmpty(deliveryId_list)){
			String[] deliveryId_arr = deliveryId_list.split("\\|");
			for(int i=0;i<deliveryId_arr.length;i++){
				String deliveryId_str = deliveryId_arr[i];
				if(StrUtils.isNotEmpty(deliveryId_str)){
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
					//检查 超级问卷列表
					NiSuperWanx selectByPrimaryKey2 = niSuperWanxService.selectByDeliveryId(deliveryId);
					if(selectByPrimaryKey2!=null){
						return AjaxResult.errorResult("投放id:"+deliveryId+"已经发布到超级问卷列表,无法撤回!");
					}
					
					//拼接id集合
					arrayList.add(deliveryId);
				}
			}
			//执行修改
			hashMap.put("t_stat", 1);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = niVoteDeliveryWanxMapper.updateStatByMap(hashMap);
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
	public AjaxResult niVoteDeliveryWanxStop(HttpServletRequest request,
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
					Integer delivery_id = Integer.valueOf(deliveryId_str);
					
					//校验  三更列表
					niDaily3updateWanxKey.setDeliveryid(delivery_id);
					niDaily3updateWanxKey.setQntype(1);  // 调查
					NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
					if(selectByPrimaryKey!=null){
						Integer pagestatus = selectByPrimaryKey.getPagestatus();
						if(pagestatus!=1){
							//在三更页面中不是待发布状态
							return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到三更列表页面中,无法暂停!");
						}
					}
					//检查 超级问卷列表
					NiSuperWanx selectByPrimaryKey2 = niSuperWanxService.selectByDeliveryId(delivery_id);
					if(selectByPrimaryKey2!=null){
						return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到超级问卷列表,无法暂停!");
					}
					
					//拼接id集合
					arrayList.add(delivery_id);
				}
			}
			//执行修改
			hashMap.put("t_stat", 3);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = niVoteDeliveryWanxMapper.updateStatByMap(hashMap);
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
	public AjaxResult niVoteDeliveryWanxRegeneration(
			HttpServletRequest request, Model model) {
		AjaxResult updateStatusTool = updateStatusTool(request, 2);
		return updateStatusTool;
	}

	//终止
	@Transactional
	public AjaxResult niVoteDeliveryWanxOver(HttpServletRequest request,
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
					Integer delivery_id = Integer.valueOf(deliveryId_str);
					
					//校验  三更列表
					niDaily3updateWanxKey.setDeliveryid(delivery_id);
					niDaily3updateWanxKey.setQntype(1);  // 调查
					NiDaily3updateWanx selectByPrimaryKey = niDaily3updateWanxService.selectByPrimaryKey(niDaily3updateWanxKey);
					if(selectByPrimaryKey!=null){
						Integer pagestatus = selectByPrimaryKey.getPagestatus();
						if(pagestatus!=1){
							//在三更页面中不是待发布状态
							return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到三更列表页面中,无法终止!");
						}
					}
					//检查 超级问卷列表
					NiSuperWanx selectByPrimaryKey2 = niSuperWanxService.selectByDeliveryId(delivery_id);
					if(selectByPrimaryKey2!=null){
						return AjaxResult.errorResult("投放id:"+delivery_id+"已经发布到超级问卷列表,无法终止!");
					}
					
					//拼接id集合
					arrayList.add(delivery_id);
				}
			}
			//执行修改
			hashMap.put("t_stat", 4);
			hashMap.put("id_list", arrayList);
			try {
				int updateStatByMap = niVoteDeliveryWanxMapper.updateStatByMap(hashMap);
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

	//添加问卷
	@Transactional
	public AjaxResult niVoteDeliveryWanxSaveQue(HttpServletRequest request,Model model) {
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
			NiVoteQuestionnaire selectByPrimaryKey = niVoteQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(sqnId));
			if(selectByPrimaryKey!=null){
				//如果输入的问卷存在
				Integer staus = selectByPrimaryKey.getStatus();
				if(staus != 2){
					return AjaxResult.errorResult("必须定稿状态才能添加!");
				}
			}else{
				//问卷不存在
				return AjaxResult.errorResult("输入的问卷不存在,请重新输入!");
			}
			
			VoteDelivery niVoteDeliveryWanx = null;
			//判断是新增问卷,还是修改问卷
			if(deliveryId!=null && !deliveryId.trim().equals("")){
				//投放已经添加,修改问卷
				ajaxResult.put("deliveryid", deliveryId);
				
				niVoteDeliveryWanx = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
				niVoteDeliveryWanx.setVqnid(Integer.valueOf(sqnId));
				niVoteDeliveryWanx.setChannelId(channelId);
				
				try {
					int updateByPrimaryKeySelective = niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
					
					if(updateByPrimaryKeySelective>0){
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "修改问卷成功");
					}else{
						return AjaxResult.errorResult("修改问卷失败!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("修改问卷失败!");
				}
				
			}else{
				//投放未添加,创建新的投放,添加问卷
				
				niVoteDeliveryWanx = new VoteDelivery();
				log.info("======================>>投放未添加,创建新的投放,添加问卷!");
				niVoteDeliveryWanx.setVqnid(Integer.valueOf(sqnId));
				niVoteDeliveryWanx.setStatus(1);
				niVoteDeliveryWanx.setChannelId(channelId);
				
				try {
					
					niVoteDeliveryWanxMapper.insert(niVoteDeliveryWanx);
					log.info("===========================>>新建投放管理调查问卷id添加成功");
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "添加问卷成功");
					//返回新添加的投放id
					int deliveryid = niVoteDeliveryWanx.getDeliveryid();
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

	// 跳转到信息添加页面
	public void niVoteDeliveryWanxSaveMESJump(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//根据投放id查询问卷信息
		if(StrUtils.isNotEmpty(deliveryid)){
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			
			if(findVoteQueByDelId!=null){
				model.addAttribute("niVoteQuestionnaire", findVoteQueByDelId);
			}
		}
		
		//查询定奖 在页面进行展示
//		List<NiAward> selectList = niAwardMapper.selectList();
		List<NiAward> findAll = awardMapper.findAll();
		if(findAll!=null && findAll.size()>0){
			model.addAttribute("NiAward_list", findAll);
		}
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			model.addAttribute("deliveryid", deliveryid);
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			if(selectByPrimaryKey!=null){
				Date btime = selectByPrimaryKey.getBtime();
				Date etime = selectByPrimaryKey.getEtime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String btime_str = "";
				if(btime!=null ){
					btime_str = simpleDateFormat.format(btime);
				}
				String etime_str = "";
				if(etime!=null){
					etime_str = simpleDateFormat.format(etime);
				}
				model.addAttribute("btime_str", btime_str);
				model.addAttribute("etime_str", etime_str);
				model.addAttribute("niVoteDeliveryWanx", selectByPrimaryKey);
			}
		}
		
	}

	//保存信息
	public AjaxResult niVoteDeliveryWanxSaveMES(HttpServletRequest request,
			Model model,MultipartFile file) {
		AjaxResult ajaxResult = new AjaxResult();
		String datepicker = request.getParameter("datepicker");   //发布时间
		String datepicker1 = request.getParameter("datepicker1");   //截至时间
		String collectnum = request.getParameter("collectnum");   //计划采集份数
		String showdes = request.getParameter("showdes");   //外简介
		String showTitle = request.getParameter("showTitle");
		String tag1str = request.getParameter("tag1str");   //标签
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
		
		String awardid = request.getParameter("awardid");
		String lotteryid = request.getParameter("lotteryid");
		
		String resultMsg = request.getParameter("resultMsg"); // 答题后结语
		
		String titleTag = request.getParameter("titleTag"); // 标题标签
		
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formatDate = sdf.format(new Date());
		
		if(StrUtils.isNotEmpty(deliveryid)){
			
			VoteDelivery niVoteDeliveryWanx = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			//当前投放的 抽奖id
			Integer lottery_id = null;
			if(niVoteDeliveryWanx!=null){
				lottery_id = niVoteDeliveryWanx.getLotteryid();
			}
			
			//根据投放id  查询问卷信息
			Integer aqnId = null;
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			if(findVoteQueByDelId!=null){
				aqnId = findVoteQueByDelId.getVqnid();
			}
			
			//上传图片
			String jdbcUrl = "";
			try {
				Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/vqn/"+formatDate+"/"+aqnId);
				if(uploadFile!=null){
					String url = (String) uploadFile.get("url");
					
					String contextPath = request.getContextPath();
					ajaxResult.put("url", contextPath+url);
					
					//获取文件的存储路径
					jdbcUrl = (String) uploadFile.get("jdbcUrl");
					ajaxResult.put("jdbcUrl", jdbcUrl);
					
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Date datepickerdate = null;
			if(datepicker!=null && !datepicker.trim().equals("")){
				datepickerdate = DateUtil.toUtilDateFromStrDateByFormat(datepicker, "yyyy-MM-dd");
			}
			Date datepicker1date = null;
			if(datepicker1!=null && !datepicker1.trim().equals("")){
				datepicker1date = DateUtil.toUtilDateFromStrDateByFormat(datepicker1, "yyyy-MM-dd");
			}
			
			//第一种情况   输入两种奖励
			if((awardid != null && !awardid.trim().equals("") && !awardid.trim().equals("0")) && (lotteryid != null && !lotteryid.trim().equals("")) ){
				return AjaxResult.errorResult("请勿选择两种奖励");
			}
			if(StrUtils.isNotEmpty(awardid)){
				// 定奖
				//查询该投放是否已经有抽奖
				if(lottery_id!=null){
					if(!awardid.trim().equals("0")){
						return AjaxResult.errorResult("已经配置抽奖,请勿改动奖品信息");
					}
				}
				
				Integer valueOf = Integer.valueOf(awardid);
				niVoteDeliveryWanx.setAward1id(valueOf);
			}
			if(StrUtils.isNotEmpty(lotteryid)){
				//抽奖
				Integer valueOf = Integer.valueOf(lotteryid);
				NiLotteryDict selectByPrimaryKey = niLotteryDictMapper.selectByPrimaryKey(valueOf);
				if(selectByPrimaryKey == null){
					return AjaxResult.errorResult("输入的抽奖id不存在奖池中");
				}else{
					//存在
					niVoteDeliveryWanx.setLotteryid(valueOf);
				}
			}
			
			niVoteDeliveryWanx.setBtime(datepickerdate);
			niVoteDeliveryWanx.setEtime(datepicker1date);
			if(StrUtils.isNotEmpty(collectnum)){
				try {
					niVoteDeliveryWanx.setCollectnum(Integer.valueOf(collectnum));
				} catch (NumberFormatException e) {
//					e.printStackTrace();
					return AjaxResult.errorResult("计划采集份数有误");
				}
			}else{
				//默认值 10000
//				niVoteDeliveryWanx.setCollectnum(10000);
				return AjaxResult.errorResult("请录入计划采集份数");
			}
			niVoteDeliveryWanx.setShowdes(showdes);
			niVoteDeliveryWanx.setShowtitle(showTitle);
			niVoteDeliveryWanx.setTag1str(tag1str);
			niVoteDeliveryWanx.setTag2str(tag2str);
			niVoteDeliveryWanx.setTag3str(tag3str);
			niVoteDeliveryWanx.setTag4str(tag4str);
			niVoteDeliveryWanx.setTag5str(tag5str);
			
			niVoteDeliveryWanx.setRelatedStr1(relatedStr1);
			niVoteDeliveryWanx.setRelatedStr2(relatedStr2);
			niVoteDeliveryWanx.setRelatedStr3(relatedStr3);
			niVoteDeliveryWanx.setRelatedUrl1(relatedUrl1);
			niVoteDeliveryWanx.setRelatedUrl2(relatedUrl2);
			niVoteDeliveryWanx.setRelatedUrl3(relatedUrl3);
			
			niVoteDeliveryWanx.setResultMsg(resultMsg);  //答题后结语
			niVoteDeliveryWanx.setTitleTag(titleTag); // 标题标签
			
			niVoteDeliveryWanx.setImg(jdbcUrl);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String birthday= formatter.format(new Date());
			
			String birthday_old = niVoteDeliveryWanx.getBirthday();
			
			if(StrUtils.isEmpty(birthday_old)){
				
				niVoteDeliveryWanx.setBirthday(birthday);
				
			}
			
			
			
			try {
				int updateByPrimaryKey = niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
				if(updateByPrimaryKey>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "创建问卷投放信息成功");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				return AjaxResult.errorResult("操作失败");
			}
			
			//当前投放 没有抽奖的时候才能生成命运表
			if(lottery_id == null){
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
							lotteryBasicInfo.setQnType(3);
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
						int updateLotteryStatus = niVoteDeliveryWanxMapper.updateLotteryReady(Integer.valueOf(deliveryid.trim()));
						if(updateLotteryStatus>0){
							log.info("===============抽奖信息就绪！==============");
						}
					}		

				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("创建问卷投放信息失败");
				}
			}	
			
		}else{
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}

	//跳转设置页面
	public void niVoteDeliveryWanxSetUpJump(HttpServletRequest request,
			Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		if(deliveryid!=null && !deliveryid.trim().equals("")){
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			model.addAttribute("niVoteDeliveryWanx", selectByPrimaryKey);
		}
		
		//查询所有能添加的广告信息
		List<NiAdInfo> selectList = niAdInfoMapper.selectList();
		if(selectList!=null){
			model.addAttribute("niAdInfoList", selectList);
		}
		
		model.addAttribute("deliveryid", deliveryid);
		
	}

	//保持设置
	@Transactional
	public AjaxResult niVoteDeliveryWanxSetUpSave(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String conditionId = request.getParameter("conditionId");  //答题时间
		String advertisementId = request.getParameter("advertisementId"); //广告id
		String deliveryid = request.getParameter("deliveryid");
		
		if(StrUtils.isNotEmpty(deliveryid)){
			VoteDelivery niVoteDeliveryWanx = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			if(niVoteDeliveryWanx!=null){
				Float lTime = null;
				if(conditionId!=null && !conditionId.trim().equals("")){
					lTime = Float.valueOf(conditionId);
					niVoteDeliveryWanx.setlTime((long) (lTime*60));
				}
				Integer advId = null;
				if(advertisementId!=null && !advertisementId.trim().equals("")){
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
						niVoteDeliveryWanx.setAdid(advId);
					}else {
						//添加的广告id不存在
						return AjaxResult.errorResult("选择的广告不存在");
					}
				}
				try {
					int updateByPrimaryKey = niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
					
					if(updateByPrimaryKey>0){
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "添加设置成功");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("添加设置失败");
				}
				
			}else{
				return AjaxResult.errorResult("操作失败");
			}
		}else{
			return AjaxResult.errorResult("操作失败");
		}
		return ajaxResult;
	}

	//跳转到数据运营页面
	public void niVoteDeliveryWanxOperateJump(HttpServletRequest request,
			Model model) {
		String deliveryId = request.getParameter("deliveryid");
//		String sqnId = request.getParameter("sqnId");
		model.addAttribute("deliveryid", deliveryId);
//		model.addAttribute("sqnId", sqnId);
		
		int selectCountNumbysqnId = 0;
//		if(sqnId!=null && !sqnId.trim().equals("")){
//			selectCountNumbysqnId = niVoteOrderMapper.selectCountNumbysqnId(Integer.valueOf(sqnId));
//		}
		
		
		if(deliveryId!=null && !deliveryId.trim().equals("")){
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryId));
			
			if(selectByPrimaryKey!=null){
				
				Integer vqnid = selectByPrimaryKey.getVqnid();
				
				if(vqnid!=null){
					selectCountNumbysqnId = niVoteOrderMapper.selectCountNumbysqnId(vqnid);
					model.addAttribute("realCount", selectCountNumbysqnId);
				}
				
				model.addAttribute("niVoteDeliveryWanx", selectByPrimaryKey);
			}
		}
		
	}

	//运营调整
	public void niVoteDeliveryWanxOperateSave(HttpServletRequest request,Model model) {
		String deliveryid = request.getParameter("deliveryid");
		//记录真实数据
		int realCount = 0;
		if(StrUtils.isNotEmpty(deliveryid)){
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			if(findVoteQueByDelId!=null){
				
				Integer vqnid = findVoteQueByDelId.getVqnid();
				
				realCount = niVoteOrderMapper.selectCountNumbysqnId(vqnid);
			}
			
			//查询当前投放
			VoteDelivery niVoteDeliveryWanx = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			
			Runnable runnable = niAssessDeliveryWanxOperateSaveTool(request,realCount,niVoteDeliveryWanx);
			Thread thread = new Thread(runnable);
			thread.start();
		}
		

	}
	/**
	 * 运营设置工具
	 * @param request
	 */
	public Runnable niAssessDeliveryWanxOperateSaveTool(HttpServletRequest request,
			final int realCount,final VoteDelivery niVoteDeliveryWanx){
		
		String operateTime = request.getParameter("publisherTime");    //时间 ： 小时
		String operateNum = request.getParameter("publisherNum");     //目标增长数
		//获取时间和目标增长数，将时间化为分钟数，计算需要增长多少次，每一次大概增长多少数值
		Float opTime = null;
		if(operateTime!=null && !operateTime.trim().equals("")){
			 opTime = Float.valueOf(operateTime);
		}
		Integer opeNum = null;   //目标数值
		if(operateNum!=null && !operateNum.trim().equals("")){
			 opeNum = Integer.valueOf(operateNum);
		}
		//目标增长数
		final int fnaCount = opeNum;
		//要循环次数为
		final int count = (int) (opTime*6);
		//大约每次需要增长
		final int alwayNum = opeNum/count;
		//沉睡时间
//		final long timeInterval = 1000*60*10;  //10分钟
		final long timeInterval = 1000*10;  //10 秒钟
 		
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
					niVoteDeliveryWanx.setCollectednum((int) (a+realCount));
					niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
					
//					operateMap = OperateTool.getOperateMap();
//					operateMap.put(deliveryId+"", a);
				}
				niVoteDeliveryWanx.setCollectednum((int) (fnaCount+realCount));
				niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
				
//				operateMap.put(deliveryId+"", fnaCount);
			}
		};
		
		return runnable;
	}

	// 显示运营调整数据
	public AjaxResult niVoteDeliveryWanxOperateShow(HttpServletRequest request,
			Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		
		if(StrUtils.isNotEmpty(deliveryid)){
			//查询实时 投票 订单数
			Integer collectnum = null;
			if(deliveryid!=null && !deliveryid.trim().equals("")){
				VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
				if(selectByPrimaryKey!=null){
					collectnum = selectByPrimaryKey.getCollectednum();
				}
			}
			//真实订单数
			int selectCountNumbysqnId = 0;
			
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			if(findVoteQueByDelId!=null){
				Integer vqnid = findVoteQueByDelId.getVqnid();
				
				selectCountNumbysqnId = niVoteOrderMapper.selectCountNumbysqnId(vqnid);
			}
			
			//模拟增长数   显示收集份数 - 真实收集份数
			int momi = 0;
			if(collectnum!=null && collectnum>selectCountNumbysqnId){
				momi = collectnum - selectCountNumbysqnId;
			}
			
			ajaxResult.put("moniNum", momi);
			ajaxResult.put("collectednum", collectnum);
		}
		return ajaxResult;
	}

	@Override
	public List<VoteDelivery> selectByQnId(Integer id) {
		List<VoteDelivery> selectByQnId = niVoteDeliveryWanxMapper.selectByQnId(id);
		return selectByQnId;
	}

	//保存编辑
	@Transactional
	public AjaxResult niVoteDeliveryWanxEditorSave(HttpServletRequest request,Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		 String deliveryid = request.getParameter("deliveryid_show");
		 String collectNum = request.getParameter("collectNum_show");
		 String showTitle = request.getParameter("showTitle_show");
		 String begintime = request.getParameter("begintime_show");
		 String endtime = request.getParameter("endtime_show");
		 String jdbcUrl = request.getParameter("imgmes");
		 
		 String sqnId = request.getParameter("sqnId");
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
		 
		 //更新问卷信息
		 NiVoteQuestionnaire niVoteQuestionnaire = new NiVoteQuestionnaire();
		 Integer sqn_Id = null;
		 if(StrUtils.isNotEmpty(sqnId)){
			 sqn_Id = Integer.valueOf(sqnId);
		 }
		 if(sqn_Id!=null){
			 niVoteQuestionnaire.setVqnid(sqn_Id);
			 niVoteQuestionnaire.setVqnname(sqnname_show);
			 niVoteQuestionnaire.setEditor(editor_show);
			 
			 try {
				niVoteQuestionnaireMapper.updateByPrimaryKeySelective(niVoteQuestionnaire);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("==================>>编辑投放时问卷信息修改失败!");
				return AjaxResult.errorResult("出现异常!");
			}
		 }
		 
		 //封装查询条件
		 VoteDelivery niVoteDeliveryWanx = new VoteDelivery();
		if(StrUtils.isNotEmpty(deliveryid)){
			Integer deliveryId = Integer.valueOf(deliveryid);
			niVoteDeliveryWanx.setDeliveryid(deliveryId);
		}
		if(StrUtils.isNotEmpty(collectNum)){
			Integer collect_Num = Integer.valueOf(collectNum);
			niVoteDeliveryWanx.setCollectnum(collect_Num);
		}
		niVoteDeliveryWanx.setShowtitle(showTitle);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date beginTime = null;
		Date endTime = null;
		try {
			beginTime = sdf.parse(begintime);
			endTime = sdf.parse(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		niVoteDeliveryWanx.setBtime(beginTime);
		niVoteDeliveryWanx.setEtime(endTime);
		niVoteDeliveryWanx.setImg(jdbcUrl);
		niVoteDeliveryWanx.setcTime(new Date());
		
		niVoteDeliveryWanx.setTag1str(tag1Str_show);
		niVoteDeliveryWanx.setTag2str(tag2Str_show);
		niVoteDeliveryWanx.setTag3str(tag3Str_show);
		niVoteDeliveryWanx.setTag4str(tag4Str_show);
		niVoteDeliveryWanx.setTag5str(tag5Str_show);
		niVoteDeliveryWanx.setShowdes(showDes_show);
		niVoteDeliveryWanx.setAdid(ad_Id);
		
		//更新数据
		try {
			int updateByPrimaryKeySelective = niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
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

	//编辑
	public VoteDelivery niVoteDeliveryWanxEditor(
			HttpServletRequest request, Model model) {
		String deliveryid = request.getParameter("deliveryid");
		VoteDelivery selectByPrimaryKey = null;
		if(StrUtils.isNotEmpty(deliveryid)){
			
			Integer delivery_id = Integer.valueOf(deliveryid);
			selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(delivery_id);
		}
		return selectByPrimaryKey;
	}

	//弹出运营调整模态框
	public AjaxResult niVoteDeliveryWanxOperateModal(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String vqnId = request.getParameter("vqnId");
		//查询出真实问卷显示数量
		int trueOrderNum = 0;
		if(StrUtils.isNotEmpty(vqnId)){
			trueOrderNum = niVoteOrderMapper.selectCountNumbysqnId(Integer.valueOf(vqnId));
		}
		
		if(deliveryid!=null){
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
			if(selectByPrimaryKey!=null){
				ajaxResult.put("niVoteDeliveryWanx", selectByPrimaryKey);
			}
		}
		ajaxResult.put("trueOrderNum", trueOrderNum);
		ajaxResult.put("deliveryid", deliveryid);
		ajaxResult.put("vqnId", vqnId);
		ajaxResult.put("success", true);
		return ajaxResult;
	}


	//请求显示运营调整数据
	public AjaxResult findVoteShowOrderNum(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String deliveryid = request.getParameter("deliveryid");
		String vqnId = request.getParameter("vqnId");
		
		Integer vqn_Id = null;
		if(StrUtils.isNotEmpty(vqnId)){
			vqn_Id = Integer.valueOf(vqnId);
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
		VoteDelivery selectByPrimaryKey = null;
		if(StrUtils.isNotEmpty(deliveryid)){
		    selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(Integer.valueOf(deliveryid));
		}
		Integer collectednum = null;
		if(selectByPrimaryKey!=null){
			collectednum = selectByPrimaryKey.getCollectednum();
		}
		ajaxResult.put("collectednum", collectednum);
		log.info("=====================>>请求显示收集分数: 显示收集份数 : "+collectednum);
		
		//真实收集份数
		int realCount = niVoteOrderMapper.selectCountNumbysqnId(vqn_Id);
		if(collectednum!=null){
			if((collectednum-realCount)>0){
				ajaxResult.put("simulationNum",collectednum-realCount);
			}else{
				ajaxResult.put("simulationNum",0 );
			}
		}
		
		return ajaxResult;
	}

	 //定时根据开关修改显示收集份数
	public AjaxResult AdjustVoteDeliveryWanxOperate(HttpServletRequest request) {
		String bj = request.getParameter("bj");
		String deliveryid = request.getParameter("deliveryid");
		String vqnId = request.getParameter("vqnId");
		
		//查询真实数据
		int trueOrderNum = 0;
		if(StrUtils.isNotEmpty(vqnId)){
			trueOrderNum = niVoteOrderMapper.selectCountNumbysqnId(Integer.valueOf(vqnId));
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
		VoteDelivery niVoteDeliveryWanx = new VoteDelivery();
		niVoteDeliveryWanx.setDeliveryid(delivery_id);
		if(StrUtils.isNotEmpty(bj)){
			if(bj.trim().equals("1")){
				//真实数据
				niVoteDeliveryWanx.setCollectednum(trueOrderNum);
				niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
				
			}else{
				//调整数据
				niVoteDeliveryWanx.setCollectednum(operate_Num);
				niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(niVoteDeliveryWanx);
			}
			
		}
		return null;
	}

	//编辑跳转问卷
	public void niVoteDeliveryWanxAdd_Edit(HttpServletRequest request,Model model) {
		String deliveryid = request.getParameter("deliveryid");
		
		//查询所有定稿问卷
		List<NiVoteQuestionnaire> select_niVote_Dev = niVoteQuestionnaireMapper.findVoteQueByStat2();
		if(select_niVote_Dev!=null && select_niVote_Dev.size()>0){
			model.addAttribute("select_niVote_Dev", select_niVote_Dev);
		}
		
		//根据投放id查询投票问卷
		Integer vqnid = null;
		if(StrUtils.isNotEmpty(deliveryid)){
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			if(findVoteQueByDelId!=null){
				vqnid = findVoteQueByDelId.getVqnid();
			}
		}
		
		model.addAttribute("sqnId", vqnid);
		model.addAttribute("deliveryid", deliveryid);
		
	}

	//跳转到添加问卷页面
	public void niVoteDeliveryWanxAdd(HttpServletRequest request, Model model) {

		String deliveryid = request.getParameter("deliveryid");
		
		//查询所有定稿的问卷
		List<NiVoteQuestionnaire> select_niVote_Dev = niVoteQuestionnaireMapper.findVoteQueByStat2();
		
		if(select_niVote_Dev!=null && select_niVote_Dev.size()>0){
			model.addAttribute("select_niVote_Dev", select_niVote_Dev);
		}
		
		//根据投放id 查询投票问卷
		if(StrUtils.isNotEmpty(deliveryid)){
			NiVoteQuestionnaire findVoteQueByDelId = niVoteQuestionnaireMapper.findVoteQueByDelId(Integer.valueOf(deliveryid));
			if(findVoteQueByDelId!=null){
				Integer vqnid = findVoteQueByDelId.getVqnid();
				model.addAttribute("vqnId", vqnid);
			}
		}
		
		model.addAttribute("deliveryid", deliveryid);
		
		// 查询所有得渠道 
		List<Channel> listChannel = channelDao.listChannel();
		model.addAttribute("listChannel", listChannel);
		
	}

	//根据问卷id 问卷名称 查询问卷
	public void searchVoteByIdOrName(HttpServletRequest request, Model model) {
		String searchIdorName = request.getParameter("searchIdorName");
		model.addAttribute("searchIdorName", searchIdorName);
		
		List<NiVoteQuestionnaire> newList = new ArrayList<NiVoteQuestionnaire>();
		
		if(StrUtils.isNotEmpty(searchIdorName)){
			//根据名称查询
			List<NiVoteQuestionnaire> selectByVoteQueName = niVoteQuestionnaireMapper.selectByVoteQueName(searchIdorName);
			if(selectByVoteQueName.size()>0){
				model.addAttribute("niVoteQuestionnaireList", selectByVoteQueName);
				
			}else{
				Integer newVoteId = null;
				//根据问卷id 查询
				try {
					newVoteId = Integer.valueOf(searchIdorName);
				} catch (NumberFormatException e) {
				}
				NiVoteQuestionnaire selectByPrimaryKey = niVoteQuestionnaireMapper.selectByPrimaryKey(newVoteId);
				if(selectByPrimaryKey!=null){
					newList.add(selectByPrimaryKey);
				}
				
				model.addAttribute("niVoteQuestionnaireList", newList);
				
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
		
		
		List<VoteDelivery> selectList = niVoteDeliveryWanxMapper.selectList(hashMap);
		
		model.addAttribute("selectList", selectList);
		
		String deliveryid_str = request.getParameter("deliveryid");
		model.addAttribute("deliveryid", deliveryid_str);
		
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
		VoteDelivery voteDelivery_temp = niVoteDeliveryWanxMapper.selectByPrimaryKey(TemplateDeliveryId);
		
		// 查询出 当前投放信息
		VoteDelivery voteDelivery = niVoteDeliveryWanxMapper.selectByPrimaryKey(SurveyDeliveryId);
		
		// 替换成当前得 投放 id, 渠道id 
		voteDelivery_temp.setDeliveryid(voteDelivery.getDeliveryid());
		voteDelivery_temp.setChannelId(voteDelivery.getChannelId());
		voteDelivery_temp.setStatus(1);
		
		// 执行编辑
		try {
			niVoteDeliveryWanxMapper.updateByPrimaryKeySelective(voteDelivery_temp);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	
}
