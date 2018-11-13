package com.newIns.productPack.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.newIns.tools.AjaxResult;

public interface ProductRecommend_Service {

	void list_ProductRecommend(HttpServletRequest request, Model model);

	void list_ProductRecommend_target(HttpServletRequest request, Model model);

	AjaxResult save_ProductRecommend(HttpServletRequest request, Model model);

	void add_ProductRecommend(HttpServletRequest request, Model model);
	
	AjaxResult dele_ProductRecommend_Id_arr(HttpServletRequest request,
			Model model);

	AjaxResult move_Up_Down_productRecommendId(HttpServletRequest request,
			Model model);

}
