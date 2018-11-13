package com.newIns.productPack.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import com.newIns.productPack.dao.ProductManage_Dao;
import com.newIns.productPack.dao.ProductPackage_Dao;
import com.newIns.productPack.pojo.ProductManage;
import com.newIns.productPack.pojo.ProductPackage;
import com.newIns.productPack.pojo.vo.ProductManage_Vo;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

@Service
public class ProductManage_Service_Imp implements ProductManage_Service {

	@Autowired
	private ProductManage_Dao ProductManage_Dao;
	@Autowired
	private ProductPackage_Dao productPackage_Dao;
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
	
	// 查询产品包下所有的内容
	public void select_product_manage(HttpServletRequest request, Model model) {
		String productId_Str = request.getParameter("productId");
		String columId_Str = request.getParameter("columId");
		
		Integer productId = StrUtils.changeToInt(productId_Str);
		Integer columId = StrUtils.changeToInt(columId_Str);
		
		if(columId == null){
			columId = 1;
		}
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("productId", productId);
		dataMap.put("columId", columId);
		
		// 查询产品包 id 和 栏目名称 查询内容列表
		List<ProductManage_Vo> list_ProductManage_Vo = ProductManage_Dao.select_product_manageList_map(dataMap);
		
		model.addAttribute("list_ProductManage_Vo", list_ProductManage_Vo);
		
		//查询产品包信息
		ProductPackage ProductPackage = productPackage_Dao.select_key(productId);
		model.addAttribute("ProductPackage", ProductPackage);
		model.addAttribute("columId", columId);
	}

	// 打开 产品包 添加内容页面
	public void add_ProductManage(HttpServletRequest request, Model model) {
		
		String packageId_str = request.getParameter("productPackageId");
		
		Integer packageId = StrUtils.changeToInt(packageId_str);
		// 查询产品包信息 
		ProductPackage productPackage = productPackage_Dao.select_key(packageId);
		
		model.addAttribute("productPackage", productPackage);
		
		
		
	}

	// 保持产品包下的 内容
	public AjaxResult save_productManage(HttpServletRequest request, Model model) {
		
		String packageId_Str = request.getParameter("packageId");
		String itemType_Str = request.getParameter("itemType");
		String itemId_Str = request.getParameter("itemId");
		String columId_Str = request.getParameter("columId");
		
		Integer packageId = StrUtils.changeToInt(packageId_Str);
		Integer itemType = StrUtils.changeToInt(itemType_Str);
		Integer itemId = StrUtils.changeToInt(itemId_Str);
		Integer columId = StrUtils.changeToInt(columId_Str);
		
		// 先判断 是否已经在产品包内容下添加
		ProductManage productManage = new ProductManage();
		
		productManage.setProductId(packageId);
		productManage.setItemType(itemType);
		productManage.setItemId(itemId);
		productManage.setColumId(columId);

		ProductManage select_productManage = ProductManage_Dao.select_productManage_model(productManage);
		
		if(select_productManage != null){
			return AjaxResult.errorResult("已经添加过");
		}
		
		// 判断内容是否存在
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
		
		// 执行添加
		try {
			productManage.setState(1); // 使用中
			
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("productId", packageId);
			dataMap.put("columId", columId);
			
			// showOrder全部 + 1
			ProductManage_Dao.increasing_showOrder_map(dataMap);
			
			ProductManage_Dao.insert(productManage);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	// 批量删除 产品包下的 内容
	public AjaxResult dele_ProductManage_Id_arr(HttpServletRequest request,Model model) {
		
		
		
		
		return null;
	}

	@Transactional
	public AjaxResult moveUp_productManage(HttpServletRequest request,Model model) {
		
		String productManageId_Str = request.getParameter("productManageId");
		String showOrder_Str = request.getParameter("showOrder");
		String last_productManageId_Str = request.getParameter("last_productManageId");
		String last_showOrder_Str = request.getParameter("last_showOrder");
		
		Integer productManageId = StrUtils.changeToInt(productManageId_Str);
		Integer showOrder = StrUtils.changeToInt(showOrder_Str);
		Integer last_productManageId = StrUtils.changeToInt(last_productManageId_Str);
		Integer last_showOrder = StrUtils.changeToInt(last_showOrder_Str);
		
		ProductManage productManage = new ProductManage();
		productManage.setId(productManageId);
		productManage.setShowOrder(last_showOrder);
		
		ProductManage last_productManage = new ProductManage();
		last_productManage.setId(last_productManageId);
		last_productManage.setShowOrder(showOrder);
		
		try {
			ProductManage_Dao.update_model(productManage);
			ProductManage_Dao.update_model(last_productManage);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
		
	}
	
}
