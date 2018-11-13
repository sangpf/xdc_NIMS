package com.newIns.productPack.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.productPack.service.ProductRecommend_Service;
import com.newIns.tools.AjaxResult;

/**
 * 推荐秘籍 
 * @author sang
 *
 */
@RequestMapping("/platform")
@Controller
public class ProductRecommend_Controller {
	@Autowired
	private ProductRecommend_Service productRecommend_Service;
	
	// 列表查询
	@RequestMapping("/list_ProductRecommend.do")
	public String list_ProductRecommend(HttpServletRequest request,Model model){
		
		productRecommend_Service.list_ProductRecommend(request,model);
		return "manager/productPack/ProductRecommend/list_ProductRecommend";
	}
	
	// 查询 目标 列表
	@RequestMapping("/list_ProductRecommend_target.do")
	public String list_ProductRecommend_target(HttpServletRequest request,Model model){
		productRecommend_Service.list_ProductRecommend_target(request,model);
		return "manager/productPack/ProductRecommend/list_ProductRecommend_target";
	}
	
	// 添加 原
	@RequestMapping("/add_ProductRecommend.do")
	public String add_ProductRecommend(HttpServletRequest request,Model model){
		
		productRecommend_Service.add_ProductRecommend(request,model);
		
		return "manager/productPack/ProductRecommend/new_ProductRecommend";
	}
	
	// 保存 源
	@ResponseBody
	@RequestMapping("/save_ProductRecommend.do")
	public AjaxResult save_ProductRecommend(HttpServletRequest request,Model model){
		
		return productRecommend_Service.save_ProductRecommend(request,model);
	}
	
	@ResponseBody
	@RequestMapping("/dele_ProductRecommend_Id_arr.do")
	public AjaxResult dele_ProductRecommend_Id_arr(HttpServletRequest request,Model model){
		
		return productRecommend_Service.dele_ProductRecommend_Id_arr(request,model);
	}
	
	@ResponseBody
	@RequestMapping("/move_Up_Down_productRecommendId.do")
	public AjaxResult move_Up_Down_productRecommendId(HttpServletRequest request,Model model){
		
		return productRecommend_Service.move_Up_Down_productRecommendId(request,model);
	}
	
}
