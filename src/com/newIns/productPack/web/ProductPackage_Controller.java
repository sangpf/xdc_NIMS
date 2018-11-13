package com.newIns.productPack.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.productPack.service.ProductPackage_Service;
import com.newIns.tools.AjaxResult;

@RequestMapping("/platform")
@Controller
public class ProductPackage_Controller {
	
	@Autowired
	private ProductPackage_Service productPackage_Service;
	
	// 上下架
	@ResponseBody
	@RequestMapping("/update_ProductPackage_status.do")
	public AjaxResult update_ProductPackage_status(HttpServletRequest request,Model model){
		
		return productPackage_Service.update_ProductPackage_status(request,model);
	}
	
	// 列表查询
	@RequestMapping("/list_ProductPackage.do")
	public String list_ProductPackage(HttpServletRequest request,Model model){
		
		productPackage_Service.list_ProductPackage(request,model);
		return "manager/productPack/ProductPackage/ProductPackage_list";
	}
	
	// 跳转到添加
	@RequestMapping("/add_ProductPackage.do")
	public String add_ProductPackage(HttpServletRequest request,Model model){
		
		productPackage_Service.add_ProductPackage(request,model);
		return "manager/productPack/ProductPackage/ProductPackage_new";
	}
	
	// 保存
	@ResponseBody
	@RequestMapping("/save_ProductPackage.do")
	public AjaxResult save_ProductPackage(HttpServletRequest request,Model model,
				@RequestParam("picUrl") MultipartFile picUrl,
				@RequestParam("unlockPic") MultipartFile unlockPic,
				@RequestParam("unlockedPic") MultipartFile unlockedPic,
				@RequestParam("pendingOpenPic") MultipartFile pendingOpenPic 
			){
		
		return productPackage_Service.save_ProductPackage(request,model,picUrl,unlockPic,unlockedPic,pendingOpenPic);
	}
	
}
