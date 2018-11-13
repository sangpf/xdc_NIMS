package com.newIns.productPack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiReportListMapper;
import com.newIns.dao.assess.AssessDeliveryDao;
import com.newIns.dao.survey.SurveyDeliveryDao;
import com.newIns.dao.tweet.NiTweetMapper;
import com.newIns.dao.vote.VoteDeliveryDao;
import com.newIns.model.assess.AssessDelivery;
import com.newIns.model.report.NiReport;
import com.newIns.model.survery.SurveyDelivery;
import com.newIns.model.tweet.NiTweet;
import com.newIns.model.vote.VoteDelivery;
import com.newIns.productPack.dao.ProductRecommend_Dao;
import com.newIns.productPack.pojo.ProductRecommend;
import com.newIns.productPack.pojo.vo.ProductRecommend_Vo;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

@Service
public class ProductRecommend_Service_Imp implements ProductRecommend_Service{

	@Autowired
	private ProductRecommend_Dao productRecommend_Dao;
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
	
	// 查询推荐秘籍列表
	public void list_ProductRecommend(HttpServletRequest request, Model model) {
		
		String sourceId_Str = request.getParameter("sourceId");
		String sourceType_Str = request.getParameter("sourceType");
		
		Integer sourceId = StrUtils.changeToInt(sourceId_Str);
		Integer sourceType = StrUtils.changeToInt(sourceType_Str);
		
		Map<String, Object> dataMap = new HashMap<>();
		
		if(sourceId != null && sourceType != null){
			dataMap.put("sourceId", sourceId);
			dataMap.put("sourceType", sourceType);
		}
		
		List<ProductRecommend_Vo> list_ProductRecommend_Vo = productRecommend_Dao.list_ProductRecommend(dataMap);
		
		model.addAttribute("list_ProductRecommend_Vo", list_ProductRecommend_Vo);
	}

	// 查询 目标 列表
	public void list_ProductRecommend_target(HttpServletRequest request,Model model) {
		
		String sourceId_Str = request.getParameter("sourceId");
		String sourceType_Str = request.getParameter("sourceType");
		String sourceTitle = request.getParameter("sourceTitle");
		
		Integer sourceId = StrUtils.changeToInt(sourceId_Str);
		Integer sourceType = StrUtils.changeToInt(sourceType_Str);
		
		Map<String, Object> dataMap = new HashMap<>();
		
		if(sourceId != null && sourceType != null){
			dataMap.put("sourceId", sourceId);
			dataMap.put("sourceType", sourceType);
		}
		
		List<ProductRecommend_Vo> list_ProductRecommend_Vo = productRecommend_Dao.list_ProductRecommend_target(dataMap);
		
		model.addAttribute("list_ProductRecommend_Vo", list_ProductRecommend_Vo);
		model.addAttribute("sourceId", sourceId);
		model.addAttribute("sourceType", sourceType);
		model.addAttribute("sourceTitle", sourceTitle);
		
	}

	// 保存 源
	public AjaxResult save_ProductRecommend(HttpServletRequest request,Model model) {
		
		String sourceId_Str = request.getParameter("sourceId");
		String sourceType_Str = request.getParameter("sourceType");
		
		String targetId_Str = request.getParameter("targetId");
		String targetType_Str = request.getParameter("targetType");
		
		Integer sourceId = StrUtils.changeToInt(sourceId_Str);
		Integer sourceType = StrUtils.changeToInt(sourceType_Str);
		Integer targetId = StrUtils.changeToInt(targetId_Str);
		Integer targetType = StrUtils.changeToInt(targetType_Str);
		
		ProductRecommend productRecommend = new ProductRecommend();
		
		productRecommend.setSourceId(sourceId);
		productRecommend.setSourceType(sourceType);
		productRecommend.setTargetId(targetId);
		productRecommend.setTargetType(targetType);
		
		List<ProductRecommend> list_ProductRecommend_model = productRecommend_Dao.list_ProductRecommend_model(productRecommend);
		
		if(list_ProductRecommend_model != null && list_ProductRecommend_model.size()>0){
			return AjaxResult.errorResult("问卷已经被添加,可返回列表查看");
		}
		
		// 查询 内容是否存在
		boolean checkIsAdd_Item = checkIsAdd_Item(sourceType_Str, sourceId);
		if(!checkIsAdd_Item){
			return AjaxResult.errorResult("添加的原内容不存在");
		}
		boolean checkIsAdd_target = checkIsAdd_Item(targetType_Str, targetId);
		if(!checkIsAdd_target){
			return AjaxResult.errorResult("添加的目标内容不存在");
		}
		
//		// 更新 showOrder
		try {
			productRecommend_Dao.update_ShowOrder_Source(productRecommend);
			productRecommend_Dao.insert_ProductRecommend(productRecommend);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}
	
	/**
	 * 判断 五种内容类型是否存在
	 * @param itemType
	 * @param itemId
	 */
	public boolean checkIsAdd_Item(String itemType_Str,Integer itemId){
		
		if(itemType_Str.trim().equals("1")){
			// 调查
			SurveyDelivery selectByPrimaryKey = niSurveyDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return false;
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return false;
				}
			}
			
		}else if(itemType_Str.trim().equals("2")){
			// 测评
			AssessDelivery selectByPrimaryKey = niAssessDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return false;
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return false;
				}
			}
		}else if(itemType_Str.trim().equals("3")){
			// 投票
			VoteDelivery selectByPrimaryKey = niVoteDeliveryWanxMapper.selectByPrimaryKey(itemId);
			if(selectByPrimaryKey == null){
				return false;
			}else{
				Integer status = selectByPrimaryKey.getStatus();
				if(status != 2){
					return false;
				}
			}
			
		}else if(itemType_Str.trim().equals("4")){
			//如果是报告  
			NiReport findByReportId = niReportListMapper.findByReportId(itemId);
			if(findByReportId == null){
				return false;
			}
			
		}else if(itemType_Str.trim().equals("5")){
			// 如果是文章
			NiTweet findOneById = niTweetMapper.findOneById(itemId);
			if(findOneById == null){
				return false;
			}
		}
		return true;
	}

	
	public void add_ProductRecommend(HttpServletRequest request, Model model) {
		
		String sourceId = request.getParameter("sourceId");
		String sourceType = request.getParameter("sourceType");
		
		model.addAttribute("sourceId", sourceId);
		model.addAttribute("sourceType", sourceType);
		
	}

	public AjaxResult dele_ProductRecommend_Id_arr(HttpServletRequest request,Model model) {
		
		String productRecommend_Id_arr_Str = request.getParameter("productRecommend_Id_arr");
		
		if(StrUtils.isEmpty(productRecommend_Id_arr_Str)){
			return AjaxResult.errorResult("选择为空");
		}
		
		String[] productRecommend_Id_arr = productRecommend_Id_arr_Str.split("\\|");
		
		Map<String, Object> hashMap = new HashMap<>();
		List<Integer> arrayList = new ArrayList<>();
		
		for(int i =0; i < productRecommend_Id_arr.length ; i++ ){
			
			String productRecommend_Id_Str = productRecommend_Id_arr[i];
			
			Integer productRecommend_Id = StrUtils.changeToInt(productRecommend_Id_Str);
			arrayList.add(productRecommend_Id);
		}
		
		hashMap.put("productRecommend_Id_list", arrayList);
		
		try {
			productRecommend_Dao.delete_productRecommend_Id_list(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	public AjaxResult move_Up_Down_productRecommendId(HttpServletRequest request, Model model) {
		
		String productRecommendId_Str = request.getParameter("productRecommendId");
		String showOrder_Str = request.getParameter("showOrder");
		String last_productRecommendId_Str = request.getParameter("last_productRecommendId");
		String last_showOrder_Str = request.getParameter("last_showOrder");
		
		Integer productRecommendId = StrUtils.changeToInt(productRecommendId_Str);
		Integer showOrder = StrUtils.changeToInt(showOrder_Str);
		Integer last_productRecommendId = StrUtils.changeToInt(last_productRecommendId_Str);
		Integer last_showOrder = StrUtils.changeToInt(last_showOrder_Str);
		
		ProductRecommend productRecommend = new ProductRecommend();
		
		productRecommend.setId(productRecommendId);
		productRecommend.setShowOrder(last_showOrder);
		
		ProductRecommend productRecommend_2 = new ProductRecommend();
		productRecommend_2.setId(last_productRecommendId);
		productRecommend_2.setShowOrder(showOrder);
		
		try {
			productRecommend_Dao.update_model(productRecommend);
			productRecommend_Dao.update_model(productRecommend_2);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}
	
}
