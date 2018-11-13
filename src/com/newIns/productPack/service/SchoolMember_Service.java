package com.newIns.productPack.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.newIns.tools.AjaxResult;

public interface SchoolMember_Service {

	void findAll_SchoolMember(HttpServletRequest request, Model model);

	void add_SchoolMember(HttpServletRequest request, Model model);

	AjaxResult save_SchoolMember(HttpServletRequest request, Model model);

	AjaxResult dele_SchoolMember(HttpServletRequest request, Model model);

	AjaxResult searchSchool_byName(HttpServletRequest request, Model model);

	void search_School_ProductPackage(HttpServletRequest request, Model model);

	void add_School_ProductPackage(HttpServletRequest request, Model model);

	AjaxResult save_School_ProductPackage(HttpServletRequest request,
			Model model);
	
	
	
	
}
