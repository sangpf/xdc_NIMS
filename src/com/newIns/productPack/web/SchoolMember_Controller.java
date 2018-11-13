package com.newIns.productPack.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.productPack.service.SchoolMember_Service;
import com.newIns.tools.AjaxResult;

@RequestMapping("/platform")
@Controller
public class SchoolMember_Controller {
	
	@Autowired
	private SchoolMember_Service schoolMember_Service;
	
	//保存学校 产品包
	@ResponseBody
	@RequestMapping("/save_School_ProductPackage.do")
	public AjaxResult save_School_ProductPackage(HttpServletRequest request,Model model){
		
		return schoolMember_Service.save_School_ProductPackage(request,model);
	}
	
	// 跳转到 添加产品包页面
	@RequestMapping("/add_School_ProductPackage.do")
	public String add_School_ProductPackage(HttpServletRequest request,Model model){
		schoolMember_Service.add_School_ProductPackage(request,model);
		return "manager/productPack/SchoolMember/school_product_add";
	}
	
	// 查询学校 购买产品包
	@RequestMapping("/search_School_ProductPackage.do")
	public String search_School_ProductPackage(HttpServletRequest request,Model model){
		schoolMember_Service.search_School_ProductPackage(request,model);
		return "manager/productPack/SchoolMember/school_product_list";
	}
	
	// 列表查询
	@RequestMapping("/findAll_SchoolMember.do")
	public String findAll_SchoolMember(HttpServletRequest request,Model model){
		
		schoolMember_Service.findAll_SchoolMember(request,model);
		
		return "manager/productPack/SchoolMember/SchoolMember_list";
	}
	
	// 添加
	@RequestMapping("/add_SchoolMember.do")
	public String add_SchoolMember(HttpServletRequest request,Model model){
		
		schoolMember_Service.add_SchoolMember(request,model);
		
		return "manager/productPack/SchoolMember/SchoolMember_edit";
	}
	
	// 保存
	@ResponseBody
	@RequestMapping("/save_SchoolMember.do")
	public AjaxResult save_SchoolMember(HttpServletRequest request,Model model){
		
		return schoolMember_Service.save_SchoolMember(request,model);
	}
	
	// 删除
	@ResponseBody
	@RequestMapping("/dele_SchoolMember.do")
	public AjaxResult dele_SchoolMember(HttpServletRequest request,Model model){
		
		return schoolMember_Service.dele_SchoolMember(request,model);
	}
	
	// 根据学校名称查询
	@ResponseBody
	@RequestMapping("/searchSchool_byName.do")
	public AjaxResult searchSchool_byName(HttpServletRequest request,Model model){
		
		return schoolMember_Service.searchSchool_byName(request,model);
	}
}
