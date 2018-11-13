package com.newIns.productPack.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.newIns.tools.AjaxResult;

public interface ProductManage_Service {

	void select_product_manage(HttpServletRequest request, Model model);

	void add_ProductManage(HttpServletRequest request, Model model);
	
	AjaxResult save_productManage(HttpServletRequest request, Model model);

	AjaxResult dele_ProductManage_Id_arr(HttpServletRequest request, Model model);

	AjaxResult moveUp_productManage(HttpServletRequest request, Model model);
	
}
