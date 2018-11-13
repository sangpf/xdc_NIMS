package com.newIns.productPack.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.productPack.service.ProductManage_Service;
import com.newIns.tools.AjaxResult;

@RequestMapping("/platform")
@Controller
public class ProductManage_Controller {
	@Autowired
	private ProductManage_Service productManage_Service;
	
	// 查询产品包下 所有内容列表
	@RequestMapping("/select_product_manage.do")
	public String select_product_manage(HttpServletRequest request,Model model){
		
		productManage_Service.select_product_manage(request,model);
		
		return "manager/productPack/ProductManage/ProductManage_list";
	}

	// 上移
	@ResponseBody
	@RequestMapping("/move_Up_Down_productManage.do")
	public AjaxResult moveUp_productManage(HttpServletRequest request,Model model){
		
		return productManage_Service.moveUp_productManage(request,model);
	}
	
	// 添加产品包 下的内容
	@RequestMapping("/add_ProductManage.do")
	public String add_ProductManage(HttpServletRequest request,Model model){
		
		productManage_Service.add_ProductManage(request,model);
		return "manager/productPack/ProductManage/ProductManage_new";
	}
	
	// 保持产品包 下的内容
	@Transactional
	@ResponseBody
	@RequestMapping("/save_productManage.do")
	public AjaxResult save_productManage(HttpServletRequest request,Model model){
		
		return productManage_Service.save_productManage(request,model);
	}
	
	// 批量删除产品包 下的内容
	@ResponseBody
	@RequestMapping("/dele_ProductManage_Id_arr.do")
	public AjaxResult dele_ProductManage_Id_arr(HttpServletRequest request,Model model){
		
		return productManage_Service.dele_ProductManage_Id_arr(request,model);
	}
	
}
