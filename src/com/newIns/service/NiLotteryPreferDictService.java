package com.newIns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.newIns.tools.AjaxResult;

/**
 * 倾向抽奖
 * @author 11409
 *
 */
public interface NiLotteryPreferDictService {
	
	/**
	 * 跳转到添加倾向抽奖页面
	 */
	void jumpTo_lotteryPreferAddPage(HttpServletRequest request,Model model);
	
	/**
	 *  保存编辑
	 */
	AjaxResult lotteryPreferEditSave(HttpServletRequest request,HttpServletResponse response,Model model);
	/**
	 * 根据id查询
	 */
	AjaxResult fineOneById(HttpServletRequest request,HttpServletResponse response,Model model);
	/**
	 * 删除倾向抽奖规则
	 */
	AjaxResult lotteryPreferPageDelte(HttpServletRequest request,HttpServletResponse response,Model model);
	
	/**
	 * 跳转到倾向抽奖页面
	 */
	void lotteryPreferPage(HttpServletRequest request,HttpServletResponse response,Model model);
	
	/**
	 * 添加倾向抽奖
	 */
	AjaxResult lotteryPreferPageAdd(HttpServletRequest request,HttpServletResponse response,Model model);
	
}
