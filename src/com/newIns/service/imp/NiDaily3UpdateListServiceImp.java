/**
 * 
 */
package com.newIns.service.imp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiDaily3UpdateListMapper;
import com.newIns.dao.NiDaily3updateWanxMapper;
import com.newIns.dao.NiReportListMapper;
import com.newIns.dao.NiSuperListMapper;
import com.newIns.dao.assess.AssessDeliveryDao;
import com.newIns.dao.survey.SurveyDeliveryDao;
import com.newIns.dao.tweet.NiTweetMapper;
import com.newIns.dao.vote.VoteDeliveryDao;
import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.niDailyUpdateWanx.Daily3update_CategoryList;
import com.newIns.model.niDailyUpdateWanx.NiDailyUpdateWanx;
import com.newIns.model.niDailyUpdateWanx.NiDailyUpdateWanx_VO_01;
import com.newIns.model.page.NiDaily3UpdateListItem;
import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxKey;
import com.newIns.model.page.NiSuperListItem;
import com.newIns.model.page.NiSuperWanx;
import com.newIns.model.report.NiReport;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.model.tweet.NiTweet;
import com.newIns.model.vo.SuperListCategory;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.service.NiDaily3UpdateListService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;
import com.newIns.web.page.NiDaily3UpdateListController;
import com.newIns.web.page.NiSuperListController;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiDaily3UpdateListServiceImp implements NiDaily3UpdateListService {
	@Resource
	private NiSuperListMapper niSuperMapper;
	@Resource
	private NiDaily3UpdateListMapper niDaily3UpdateMapper;
	@Autowired
	private NiDaily3updateWanxMapper niDaily3updateWanxMapper;
	@Resource
	private NiReportListMapper niReportListMapper;
	@Resource
	private NiTweetMapper niTweetMapper;
	@Resource
	private SurveyDeliveryDao niSurveyDeliveryWanxMapper;
	@Resource
	private AssessDeliveryDao niAssessDeliveryWanxMapper;
	@Resource
	private VoteDeliveryDao niVoteDeliveryWanxMapper;
	
	public List<NiDailyUpdateWanx_VO_01> loadDaily3UpdateList(HashMap<String, Object> retMap) {
		return niDaily3UpdateMapper.loadDaily3UpdateList(retMap);
	}

	/**
	 * @Title: postDaily3UpdateByids
	 * @Author: Guan
	 * @Description: 批量发布超级调查问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int postDaily3UpdateByids(HashMap<String, Object> retMap) {
		int updateStateByids = niDaily3UpdateMapper.postDaily3UpdateByids(retMap);
		return updateStateByids;
	}

	/**
	 * @Title: revokeDaily3UpdateByids
	 * @Author: Guan
	 * @Description: 批量撤销超级调查问卷
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int revokeDaily3UpdateByids(HashMap<String, Object> retMap) {
		int updateStateByids = niDaily3UpdateMapper.revokeDaily3UpdateByids(retMap);
		return updateStateByids;
	}

	/**
	 * 批量删除问卷
	 */
	public int deleteDaily3UpdateByIds(HashMap<String, Object> retMap) {
		int deleteDaily3UpdateByIds = niDaily3UpdateMapper.deleteDaily3UpdateByIds(retMap);
		return deleteDaily3UpdateByIds;
	}

	/**
	 * 置顶超级调查问卷
	 */
	public void topDaily3Update(HashMap<String, Object> retMap) {
		niDaily3UpdateMapper.topDaily3Update(retMap);
	}

	/**
	 * 取消置顶
	 */
	public void topCancelDaily3Update(HashMap<String, Object> retMap) {
		niDaily3UpdateMapper.topCancelDaily3Update(retMap);
	}

	/**
	 * 根据deliveryId从投放表中查询问卷题目
	 */
	public NiDaily3UpdateListItem d3searchQnTitleById(HashMap<String, Object> retMap) {
		return niDaily3UpdateMapper.d3searchQnTitleById(retMap);
	}

	/**
	 * 查询超级调查列表中是否已有该投放
	 */
	public NiDaily3UpdateListItem searchDaily3UpdateDeliveryFromList(int deliveryId,int qnType) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		return niDaily3UpdateMapper.searchDaily3UpdateDeliveryFromList(hashMap);
	}

	/**
	 * 将投放添加至列表整体功能service
	 */
	public AjaxResult addDaily3UpdateDelivery(int deliveryId,int qnType) {
		NiDaily3UpdateListItem daily3updateDelivery = searchDaily3UpdateDeliveryFromList(deliveryId,qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		// 根据投放Id和问卷类型判断该投放是否在超级调查列表中
		// 如果不在，则添加一条新的投放
		if (daily3updateDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiDaily3UpdateListItem daily3updateArg = d3searchQnTitleById(hashMap);
			if (daily3updateArg != null) {
				// 获取当前系统时间
				Timestamp uTime = new Timestamp(System.currentTimeMillis());
				daily3updateArg.setuTime(uTime);
				daily3updateArg.setDeliveryId(deliveryId);
				daily3updateArg.setQnType(qnType);
				niDaily3UpdateMapper.addDaily3UpdateDeliveryToList(daily3updateArg);
				return AjaxResult.successResult("添加成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}

		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}

	public AjaxResult replaceDaily3UpdateDelivery(int deliveryId, int showOrder,int qnType) {
		NiDaily3UpdateListItem daily3updateDelivery = searchDaily3UpdateDeliveryFromList(deliveryId,qnType);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("qnType", qnType);
		hashMap.put("deliveryId", deliveryId);
		// 根据投放Id判断该投放是否在超级调查列表中
		// 如果不在，则替换一条新的投放
		if (daily3updateDelivery == null) {
			// 根据投放id判断投放表中是否有这条投放
			// 如果有，则成功添加一条记录
			NiDaily3UpdateListItem daily3updateArg = d3searchQnTitleById(hashMap);
			if (daily3updateArg != null) {
				Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
				daily3updateArg.setuTime(uTime);
				daily3updateArg.setDeliveryId(deliveryId);
				daily3updateArg.setShowOrder(showOrder);
				daily3updateArg.setQnType(qnType);
				niDaily3UpdateMapper.replaceDaily3UpdateDelivery(daily3updateArg);
				return AjaxResult.successResult("替换成功");
			} else {
				return AjaxResult.successResult("请输入正确投放id");
			}
		} else {
			return AjaxResult.successResult("该投放在列表中已存在");
		}
	}
	
	
	public AjaxResult moveUpDaily3Update(String itemId,String showOrder,String lastItemId,String lastShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		
		hashMap.put("itemId", itemId);
		hashMap.put("showOrder", lastShowOrder);
		hashMap2.put("itemId", lastItemId);
		hashMap2.put("showOrder", showOrder);
		try {
			
//			niDaily3UpdateMapper.moveUpDaily3Update(hashMap);
			niDaily3UpdateMapper.updateShowOrderByItemId(hashMap);
			niDaily3UpdateMapper.updateShowOrderByItemId(hashMap2);
			return AjaxResult.successResult("上移成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("失败");
		}
		
	}
	
	//下移
	public AjaxResult moveDownDaily3Update(String itemId,String showOrder,String nextitemId,String nextShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		
		hashMap.put("itemId", itemId);
		hashMap.put("showOrder", nextShowOrder);
		hashMap2.put("itemId", nextitemId);
		hashMap2.put("showOrder", showOrder);
		
		try {
			niDaily3UpdateMapper.updateShowOrderByItemId(hashMap);
			niDaily3UpdateMapper.updateShowOrderByItemId(hashMap2);
			return AjaxResult.successResult("下移成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("失败");
		}
		
		
	}

	//保存定时任务
	public AjaxResult daily3UpdateTimerSave(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult();
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
		
		NiDaily3updateWanx niDaily3updateWanx = new NiDaily3updateWanx();
		
		niDaily3updateWanx.setItemId(itemId);
		niDaily3updateWanx.setTimer(parse);
		
		try {
			niDaily3updateWanxMapper.updateStateById_timer(niDaily3updateWanx);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		return ajaxResult;
	}

	/**
	 * 跳转到打开定时器时间页面
	 */
	public void jumpDaily3UpdateTimerPage(HttpServletRequest request,
			Model model) {
		
		String itemId_Str = request.getParameter("itemId");
		
		model.addAttribute("itemId", itemId_Str);
		
		
	}

	public AjaxResult addDaily3UpdateDelivery(HttpServletRequest request,Model model) {

		String itemType_Str = request.getParameter("qnType");  //原投放类型     扩展为内容类型
		String itemId_Str = request.getParameter("deliveryId");  //原投放id  扩展为内容id
		String superListCategory_Str = request.getParameter("superListCategory");
		
		// 判断在三更列表是否已经存在
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("itemType", itemType_Str);
		retMap.put("itemId", itemId_Str);
		retMap.put("qnListCategory", superListCategory_Str);
		
		//判断 在超级列表页面是否已经存在
		NiDaily3updateWanx niDaily3updateWanx = niDaily3UpdateMapper.selectByPrimaryKey(retMap);
		if(niDaily3updateWanx!=null){
			return AjaxResult.errorResult("无法保存 ! 内容已经被添加");
		}
		
		// 如果是投放, 判断投放是否存在, 并且状态是投放中
		// 该内容未投放到三更页面上  , 那么可能是投放,报告, 文章 , 如果是投放判断状态是否为投放中 ,如果是报告和文章则不需判断状态
		// 如果是投放  
		// 根据投放id, 内容类型  查询投放信息
		Integer itemId = StrUtils.changeToInt(itemId_Str);
		
		if(itemType_Str.trim().equals("1")){
			// 调查
			SurveyDelivery selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return AjaxResult.errorResult("未查询到该投放");
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("投放状态错误,必须是投放中");
				}
			}
			
		}else if(itemType_Str.trim().equals("2")){
			// 测评
			AssessDelivery selectByPrimaryKey = niAssessDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return AjaxResult.errorResult("未查询到该投放");
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("投放状态错误,必须是投放中");
				}
			}
		}else if(itemType_Str.trim().equals("3")){
			// 投票
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return AjaxResult.errorResult("未查询到该投放");
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return AjaxResult.errorResult("投放状态错误,必须是投放中");
				}
			}
			
		}else if(itemType_Str.trim().equals("4")){
			//如果是报告  
			NiReport findByReportId = niReportListMapper.findByReportId(Integer.valueOf(itemId_Str));
			if(findByReportId == null){
				return AjaxResult.errorResult("失败,未查询到该报告信息");
			}
			
		}else if(itemType_Str.trim().equals("5")){
			// 如果是文章
			NiTweet findOneById = niTweetMapper.findOneById(Integer.valueOf(itemId_Str));
			if(findOneById == null){
				return AjaxResult.errorResult("无法保存 ! 未查询到该推文");
			}
		}
		
		//----------------------执行保存-----------------------
		
		NiDaily3UpdateListItem niDaily3UpdateListItem = new NiDaily3UpdateListItem();
		
		Integer itemType = StrUtils.changeToInt(itemType_Str);
		
		// 注释  : 如果 qnType 为4 , 则代表报告,  为5代表文章  , 响应的deliveryId 代表投放id,或者报告id,文章id
		niDaily3UpdateListItem.setQnType(itemType);
		niDaily3UpdateListItem.setDeliveryId(itemId);
		niDaily3UpdateListItem.setQnListCategory(superListCategory_Str);
		
		try {
			
			niDaily3UpdateMapper.addDaily3UpdateDeliveryToList_new(niDaily3UpdateListItem);
			
			return AjaxResult.successResult("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}

		
	}

	//编辑前跳转页面
	public void editDaily3UpdateList(HttpServletRequest request, Model model) {
		String itemId_str = request.getParameter("itemId");
		Integer itemId = null;
		if(StrUtils.isNotEmpty(itemId_str)){
			itemId = Integer.valueOf(itemId_str);
		}
		
		NiDailyUpdateWanx_VO_01 findDaily3UpdatebyItemId = niDaily3UpdateMapper.findDaily3UpdatebyItemId(itemId);
		
		Integer qnType = findDaily3UpdatebyItemId.getQnType();
		Integer deliveryId = findDaily3UpdatebyItemId.getDeliveryId();
		Integer reportId = findDaily3UpdatebyItemId.getReportId();
		Integer tweetId = findDaily3UpdatebyItemId.getTweetId();
		String qnListCategory = findDaily3UpdatebyItemId.getQnListCategory();
		
		if(qnType == 1){
			//调查
			model.addAttribute("contentType", 1);
			model.addAttribute("contentId", deliveryId);
			model.addAttribute("listCategory", qnListCategory);
			
		}else if(qnType == 2){
			//测评
			model.addAttribute("contentType", 2);
			model.addAttribute("contentId", deliveryId);
			model.addAttribute("listCategory", qnListCategory);
		}else if(qnType == 3){
			//投票
			model.addAttribute("contentType", 3);
			model.addAttribute("contentId", deliveryId);
			model.addAttribute("listCategory", qnListCategory);
		}else if(reportId != null){
			//报告
			model.addAttribute("contentType", 4);
			model.addAttribute("contentId", reportId);
			model.addAttribute("listCategory", qnListCategory);
		}else if(tweetId != null){
			//文章
			model.addAttribute("contentType", 5);
			model.addAttribute("contentId", tweetId);
			model.addAttribute("listCategory", qnListCategory);
		}
		
		
		model.addAttribute("itemId", itemId_str);
		//查询所有栏目名称
		List<Daily3update_CategoryList> daily3update_CategoryList = NiDaily3UpdateListController.getDaily3update_CategoryList();
		model.addAttribute("daily3update_CategoryList", daily3update_CategoryList);
	}

	//编辑三更  保存
	public AjaxResult editDaily3UpdateDelivery(HttpServletRequest request,Model model) {
		String itemId_str = request.getParameter("itemId");
		String contentType_str = request.getParameter("contentType");
		String contentId_str = request.getParameter("contentId");
		String superListCategory_str = request.getParameter("superListCategory");
		
		Integer itemId = null;
		if(StrUtils.isNotEmpty(itemId_str)){
			itemId = Integer.valueOf(itemId_str);
		}
		Integer contentId = null;
		if(StrUtils.isNotEmpty(contentId_str)){
			contentId = Integer.valueOf(contentId_str);
		}
		Integer contentType = null;
		if(StrUtils.isNotEmpty(contentType_str)){
			contentType = Integer.valueOf(contentType_str);
		}
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("itemId", itemId_str);
		
		NiDailyUpdateWanx_VO_01 niDailyUpdateWanx_VO = niDaily3UpdateMapper.findDaily3UpdatebyItemId(itemId);
		
		Integer deliveryId = null;
		Integer reportId = null;
		Integer tweetId = null;
		if(niDailyUpdateWanx_VO != null){
			deliveryId = niDailyUpdateWanx_VO.getDeliveryId();
			reportId = niDailyUpdateWanx_VO.getReportId();
			tweetId = niDailyUpdateWanx_VO.getTweetId();
		}
		
		if(contentType_str.trim().equals("1")){
			//调查 
			//判断是否是原来的数据, 还是其他的内容
				if(contentId == deliveryId){
					// 原来的数据
				}else{
					//新的数据
					//判断在三更中是否已经存在  
					hashMap.put("contentType", contentType);
					hashMap.put("contentId", contentId);
					hashMap.put("superListCategory", superListCategory_str);
					List<NiDailyUpdateWanx_VO_01> NiDailyUpdateWanx_VO_list = niDaily3UpdateMapper.findDaily3UpdateByMap(hashMap);
					if(NiDailyUpdateWanx_VO_list.size()>0){
						return AjaxResult.errorResult("投放已经被添加,请重新选择");
					}
					
					//判断投放是否存在  状态是否是投放中
					SurveyDelivery niSurveyDeliveryWanx = niSurveyDeliveryWanxMapper.selectByPrimaryKey(contentId);
					if(niSurveyDeliveryWanx == null){
						return AjaxResult.errorResult("投放不存在");
					}else{
						Integer status = niSurveyDeliveryWanx.getStatus();
						if(status != 2){
							return AjaxResult.errorResult("投放必须是投放中状态");
						}else{
							//执行编辑功能
							try{
								niDaily3UpdateMapper.updateDaily3UpdateByMap(hashMap);
								return AjaxResult.successResult("编辑成功");
							}catch(Exception e){
								e.printStackTrace();
								return AjaxResult.errorResult("编辑失败");
							}
						}
					}
				}
			
		}else if(contentType_str.trim().equals("2")){
			//测评
			//判断是否是原来的数据, 还是其他的内容
			if(contentId == deliveryId){
				// 原来的数据
			}else{
				//新的数据
				//判断在三更中是否已经存在  
				hashMap.put("contentType", contentType);
				hashMap.put("contentId", contentId);
				hashMap.put("superListCategory", superListCategory_str);
				List<NiDailyUpdateWanx_VO_01> NiDailyUpdateWanx_VO_list = niDaily3UpdateMapper.findDaily3UpdateByMap(hashMap);
				if(NiDailyUpdateWanx_VO_list.size()>0){
					return AjaxResult.errorResult("投放已经被添加,请重新选择");
				}
				
				//判断投放是否存在  状态是否是投放中
				AssessDelivery niAssessDeliveryWanx = niAssessDeliveryWanxMapper.selectByPrimaryKey(contentId);
				if(niAssessDeliveryWanx == null){
					return AjaxResult.errorResult("投放不存在");
				}else{
					Integer status = niAssessDeliveryWanx.getStatus();
					if(status != 2){
						return AjaxResult.errorResult("投放必须是投放中状态");
					}else{
						//执行编辑功能
						try{
							niDaily3UpdateMapper.updateDaily3UpdateByMap(hashMap);
							return AjaxResult.successResult("编辑成功");
						}catch(Exception e){
							e.printStackTrace();
							return AjaxResult.errorResult("编辑失败");
						}
					}
				}
				
			}
			
		}else if(contentType_str.trim().equals("3")){
			//投票
			//判断是否是原来的数据, 还是其他的内容
			if(contentId == deliveryId){
				// 原来的数据
			}else{
				//新的数据
				//判断在三更中是否已经存在  
				hashMap.put("contentType", contentType);
				hashMap.put("contentId", contentId);
				hashMap.put("superListCategory", superListCategory_str);
				List<NiDailyUpdateWanx_VO_01> NiDailyUpdateWanx_VO_list = niDaily3UpdateMapper.findDaily3UpdateByMap(hashMap);
				if(NiDailyUpdateWanx_VO_list.size()>0){
					return AjaxResult.errorResult("投放已经被添加,请重新选择");
				}
				
				//判断投放是否存在  状态是否是投放中
				VoteDelivery niVoteDeliveryWanx = niVoteDeliveryWanxMapper.selectByPrimaryKey(contentId);
				if(niVoteDeliveryWanx == null){
					return AjaxResult.errorResult("投放不存在");
				}else{
					Integer status = niVoteDeliveryWanx.getStatus();
					if(status != 2){
						return AjaxResult.errorResult("投放必须是投放中状态");
					}else{
						//执行编辑功能
						try{
							niDaily3UpdateMapper.updateDaily3UpdateByMap(hashMap);
							return AjaxResult.successResult("编辑成功");
						}catch(Exception e){
							e.printStackTrace();
							return AjaxResult.errorResult("编辑失败");
						}
					}
				}
				
			}
			
		}else if(contentType_str.trim().equals("4")){
			//报告
			if(contentId == reportId){
				//原来的数据
			}else{
				//新的数据
				//判断在三更中是否已经存在
				hashMap.put("contentId", contentId);
				hashMap.put("superListCategory", superListCategory_str);
				List<NiDailyUpdateWanx_VO_01> findDaily3UpdatebyReport = niDaily3UpdateMapper.findDaily3UpdatebyReport(hashMap);
				
				if(findDaily3UpdatebyReport.size()>0){
					return AjaxResult.errorResult("投放已经被添加,请重新选择");
				}else{
					//执行编辑报告
					try{
						niDaily3UpdateMapper.updateDaily3UpdatebyReport(hashMap);
						return AjaxResult.successResult("编辑成功");
					}catch(Exception e){
						e.printStackTrace();
						return AjaxResult.errorResult("编辑失败");
					}
					
				}
				
			}
			
		}else if(contentType_str.trim().equals("5")){
			//文章
			if(contentId == tweetId){
				//原来的数据
			}else{
				//新的数据
				//判断在三更中是否已经存在
				hashMap.put("contentId", contentId);
				hashMap.put("superListCategory", superListCategory_str);
				List<NiDailyUpdateWanx_VO_01> findDaily3UpdatebyTweet = niDaily3UpdateMapper.findDaily3UpdatebyTweet(hashMap);
				
				if(findDaily3UpdatebyTweet.size()>0){
					return AjaxResult.errorResult("投放已经被添加,请重新选择");
				}else{
					//执行编辑功能
					try{
						niDaily3UpdateMapper.updateDaily3UpdatebyTweet(hashMap);
						return AjaxResult.successResult("编辑成功");
					}catch(Exception e){
						e.printStackTrace();
						return AjaxResult.errorResult("编辑失败");
					}
					
				}
				
			}
		}
		
		return AjaxResult.successResult("编辑成功");
	}

}
