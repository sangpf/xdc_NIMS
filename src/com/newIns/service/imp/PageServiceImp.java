package com.newIns.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiReportMapper;
import com.newIns.dao.assess.AssessDeliveryDao;
import com.newIns.dao.page.ChannelDao;
import com.newIns.dao.page.ColumnDao;
import com.newIns.dao.page.PageDao;
import com.newIns.dao.survey.SurveyDeliveryDao;
import com.newIns.dao.tweet.NiTweetMapper;
import com.newIns.dao.vote.VoteDeliveryDao;
import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.page.Channel;
import com.newIns.model.page.Column;
import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.Page;
import com.newIns.model.page.PageVo;
import com.newIns.model.report.NiReport;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.model.tweet.NiTweet;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.service.PageService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

@Service
public class PageServiceImp implements PageService {

	@Autowired
	private PageDao pageDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private ColumnDao columnDao;
	@Autowired
	private SurveyDeliveryDao surveyDeliveryDao;
	@Autowired
	private AssessDeliveryDao assessDeliveryDao;
	@Autowired
	private VoteDeliveryDao voteDeliveryDao;
	@Autowired
	private NiReportMapper niReportMapper;
	@Autowired
	private NiTweetMapper niTweetMapper;
	
	// 列表查询
	public void listPage(HttpServletRequest request,Model model) {

		String channelId_str = request.getParameter("channelId");
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		Integer chanId = StrUtils.changeToInt(channelId_str);
		hashMap.put("chanId", chanId);
		
		List<PageVo> listPage = pageDao.listPage(hashMap);
		
		Iterator<PageVo> iterator = listPage.iterator();
		
		while(iterator.hasNext()){
			PageVo pageVo = iterator.next();
			
			Integer qnType = pageVo.getQnType();
			Integer deliveryId = pageVo.getDeliveryId();
			Integer reportId = pageVo.getReportId();
			Integer tweetId = pageVo.getTweetId();
			
			if(qnType != null){
				
				if(qnType == 1){
					pageVo.setContentId(deliveryId);
					pageVo.setContentType(1);
				}else if(qnType == 2){
					pageVo.setContentId(deliveryId);
					pageVo.setContentType(2);
				}else if(qnType == 3){
					pageVo.setContentId(deliveryId);
					pageVo.setContentType(3);
				}
				
			}
			
			if(reportId != null){
				pageVo.setContentId(reportId);
				pageVo.setContentType(4);
			}else if(tweetId != null){
				pageVo.setContentId(tweetId);
				pageVo.setContentType(5);
			}
			
			Integer channelId = pageVo.getChannelId();
			Integer columnId = pageVo.getColumnId();
			
			Channel channel = channelDao.findById(channelId);
			if(channel!=null){
				String title = channel.getTitle();
				pageVo.setChannelTitle(title);
			}
			
			Column column = columnDao.findById(columnId);
			if(column != null){
				pageVo.setColumnTitle(column.getTitle());
			}
			
		}
		
		model.addAttribute("listPage", listPage);
		
	}

	// 跳转到添加页面
	public void addPageJump(HttpServletRequest request, Model model) {
		
		// 加载栏目 
		List<Column> listColumn = columnDao.listColumn();
		// 加载 渠道
		List<Channel> listChannel = channelDao.listChannel();
		
		model.addAttribute("listColumn", listColumn);
		model.addAttribute("listChannel", listChannel);
		
	}

	// 保存页面
	public AjaxResult savePage(HttpServletRequest request, Model model) {
		// 保存前 先判断该 条内容是否已经 发布到相应得页面上
		String qnType_str = request.getParameter("qnType");   // 内容类型
		String itemId_str = request.getParameter("itemId");  
		String columnId_str = request.getParameter("columnId");  // 栏目id
		String channelId_str = request.getParameter("channelId");  //渠道id
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		Integer qnType = null;
		if(StrUtils.isNotEmpty(qnType_str)){
			qnType = Integer.valueOf(qnType_str);
		}
		Integer itemId = null;
		if(StrUtils.isNotEmpty(itemId_str)){
			itemId = Integer.valueOf(itemId_str);
		}
		Integer columnId = null;
		if(StrUtils.isNotEmpty(columnId_str)){
			columnId = Integer.valueOf(columnId_str);
		}
		Integer channelId = null;
		if(StrUtils.isNotEmpty(channelId_str)){
			channelId = Integer.valueOf(channelId_str);
		}
		
		hashMap.put("itemId", itemId);
		hashMap.put("columnId", columnId);
		hashMap.put("channelId", channelId);
		
		List<Page> listPage = new ArrayList<Page>();
		
		SurveyDelivery surveyDelivery = null;
		AssessDelivery assessDelivery = null;
		VoteDelivery voteDelivery = null;
		NiReport niReport = null;
		NiTweet niTweet = null;
		
		switch(qnType_str){
		case "1":
			surveyDelivery = surveyDeliveryDao.selectByPrimaryKey(itemId);
			listPage  = pageDao.findSurveyPage(hashMap);
			if(surveyDelivery == null){
				return AjaxResult.errorResult("调查投放不存在");
			}else{
				Integer status = surveyDelivery.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("调查投放状态必须为投放中");
				}
			}
			break;
		case "2":
			assessDelivery = assessDeliveryDao.selectByPrimaryKey(itemId);
			listPage  = pageDao.findAssessPage(hashMap);
			if(assessDelivery == null){
				return AjaxResult.errorResult("测评投放不存在");
			}else{
				Integer status = assessDelivery.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("测评投放状态必须为投放中");
				}
			}
			break;
		case "3":
			voteDelivery = voteDeliveryDao.selectByPrimaryKey(itemId);
			listPage  = pageDao.findVotePage(hashMap);
			
			if(voteDelivery == null){
				return AjaxResult.errorResult("投票投放不存在");
			}else{
				Integer status = voteDelivery.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("投票投放状态必须为投放中");
				}
			}
			break;
		case "4":
			niReport = niReportMapper.selectByPrimaryKey(itemId);
			listPage  = pageDao.findReportPage(hashMap);
			if(niReport == null){
				return AjaxResult.errorResult("报告不存在");
			}else{
				Integer reportStatus = niReport.getReportStatus();
				if(reportStatus != 2){
					return AjaxResult.errorResult("报告状态必须为定稿");
				}
			}
			break;
		case "5":
			niTweet = niTweetMapper.findOneById(itemId);
			listPage  = pageDao.findTweetPage(hashMap);
			
			if(niTweet == null){
				return AjaxResult.errorResult("推文不存在");
			}
			break;
		}
		
		if(listPage!= null && listPage.size()>0){
			return AjaxResult.errorResult("已经被添加,请重新选择");
		}
		
		Map<String, Object> insertMap = new HashMap<String, Object>();
		
		// 新增一条记录 到通用页面
		
		insertMap.put("itemId", itemId);
		insertMap.put("uTime", new Date());
		insertMap.put("qnType", qnType);
		insertMap.put("columnId", columnId);
		insertMap.put("channelId", channelId);
		
		try {
			pageDao.insertPage(insertMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("添加失败");
		}
		
		
		return AjaxResult.successResult("保存成功");
	}

	// 批量修改状态
	public AjaxResult changePageStatus(HttpServletRequest request, Model model,Integer status) {
		
		String itemId_str = request.getParameter("itemId");
		
		String[] split = itemId_str.split("!");

		HashMap<String, Object> hashMap = new HashMap<>();
		
		List<Integer> arrayList = new ArrayList<>();

		for(String itemIdStr : split){
			if(StrUtils.isNotEmpty(itemIdStr)){
				Integer itemId = Integer.valueOf(itemIdStr);
				arrayList.add(itemId);
			}
		}
		hashMap.put("itemList", arrayList);
		
		// 判断是 发布, 撤销, 删除
		if(status == 1){  // 发布
			hashMap.put("status", 3);
			
		}else if(status ==2){  // 撤销
			hashMap.put("status", 1);
			
		}else if(status == 3){  // 废弃
			hashMap.put("status", 4);
		}
		
		try {
			pageDao.changePageStatus(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("失败");
		}
		
		return AjaxResult.successResult("成功");
	}

	// 置顶
	public AjaxResult changeTopStatus(HttpServletRequest request, Model model) {
		String itemId_str = request.getParameter("itemId");
		String isTop_str = request.getParameter("isTop");
		
		Map<String, Object> hashMap = new HashMap<>();
		
		Integer itemId = null;
		if(StrUtils.isNotEmpty(itemId_str)){
			itemId = Integer.valueOf(itemId_str);
		}
		
		if(isTop_str.trim().equals("1")){
			hashMap.put("isTop", 2);
		}else{
			hashMap.put("isTop", 1);
		}
		hashMap.put("itemId", itemId);
		
		try {
			pageDao.changeTopStatus(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("失败");
		}
		
		return AjaxResult.successResult("成功");
	}

	// 上移
	public AjaxResult moveUpPage(HttpServletRequest request, Model model) {
		
		String itemId = request.getParameter("itemId");
		String showOrder = request.getParameter("showOrder");
		String lastItemId = request.getParameter("lastItemId");
		String lastShowOrder = request.getParameter("lastShowOrder");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		
		hashMap.put("itemId", itemId);
		hashMap.put("showOrder", lastShowOrder);
		hashMap2.put("itemId", lastItemId);
		hashMap2.put("showOrder", showOrder);
		
		
		try {
			pageDao.changeShowOrderStatus(hashMap);
			pageDao.changeShowOrderStatus(hashMap2);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	// 下移
	public AjaxResult moveDownPage(HttpServletRequest request, Model model) {
		
		String itemId = request.getParameter("itemId");
		String showOrder = request.getParameter("showOrder");
		String nextitemId = request.getParameter("nextitemId");
		String nextShowOrder = request.getParameter("nextShowOrder");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		
		hashMap.put("itemId", itemId);
		hashMap.put("showOrder", nextShowOrder);
		hashMap2.put("itemId", nextitemId);
		hashMap2.put("showOrder", showOrder);
		
		try {
			pageDao.changeShowOrderStatus(hashMap);
			pageDao.changeShowOrderStatus(hashMap2);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	// 保存定时任务
	public AjaxResult saveTimerPage(HttpServletRequest request, Model model) {

		String timer_dateStr = request.getParameter("timer_dateStr");
		
		String itemId_Str = request.getParameter("itemId");
		
		if(StrUtils.isEmpty(itemId_Str) || StrUtils.isEmpty(timer_dateStr)){
			return AjaxResult.errorResult("保存失败");
		}
		
		Integer itemId = Integer.valueOf(itemId_Str);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(timer_dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> hashMap = new HashMap<>();
		
		hashMap.put("itemId", itemId);
		hashMap.put("timer", parse);
		
		try {
			pageDao.setTimer(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		return AjaxResult.successResult();
		
	}
	
	
	
	
	
}
