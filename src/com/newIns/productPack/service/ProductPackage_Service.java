package com.newIns.productPack.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.tools.AjaxResult;

public interface ProductPackage_Service {

	void list_ProductPackage(HttpServletRequest request, Model model);
	
	AjaxResult save_ProductPackage(HttpServletRequest request,Model model,
			MultipartFile picUrl, MultipartFile unlockPic, MultipartFile unlockedPic,MultipartFile pendingOpenPic);
	
	void add_ProductPackage(HttpServletRequest request, Model model);

	AjaxResult update_ProductPackage_status(HttpServletRequest request,
			Model model);
	
	
}
