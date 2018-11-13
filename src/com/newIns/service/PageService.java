package com.newIns.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.newIns.tools.AjaxResult;

public interface PageService {
	
	void listPage(HttpServletRequest request,Model model);
	
	void addPageJump(HttpServletRequest request,Model model);
	
	AjaxResult savePage(HttpServletRequest request,Model model);

	//批量发布
	AjaxResult changePageStatus(HttpServletRequest request, Model model,Integer status);

	// 是否置顶
	AjaxResult changeTopStatus(HttpServletRequest request, Model model);

	//上移
	AjaxResult moveUpPage(HttpServletRequest request, Model model);

	//
	AjaxResult moveDownPage(HttpServletRequest request, Model model);

	// 保存定时任务
	AjaxResult saveTimerPage(HttpServletRequest request, Model model);
	
	
	
	
}
