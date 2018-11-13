package com.newIns.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.service.NiLotteryPreferDictService;
import com.newIns.tools.AjaxResult;

/**
 * 倾向抽奖
 * @author 11409
 *
 */
@RequestMapping("/platform")
@Controller
public class NiLotteryPreferDictController {
	@Autowired
	private NiLotteryPreferDictService niLotteryPreferDictService;
	
	/**
	 * 根据id查询
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/fineLotteryPreferDictById.do")
	@ResponseBody
	public AjaxResult fineOneById(HttpServletRequest request,HttpServletResponse response,Model model){
		return niLotteryPreferDictService.fineOneById(request, response, model);
	}
	
	/**
	 * 删除倾向抽奖规则
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/lotteryPreferPageDelte.do")
	@ResponseBody
	public AjaxResult lotteryPreferPageDelte(HttpServletRequest request,HttpServletResponse response,Model model){
		return niLotteryPreferDictService.lotteryPreferPageDelte(request, response, model);
	}
	
	/**
	 * 跳转到倾向抽奖列表页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/lotteryPreferPageList.do")
	public String lotteryPreferPage(HttpServletRequest request,HttpServletResponse response,Model model){
		//查询倾向所有抽奖信息,在页面列表显示
		niLotteryPreferDictService.lotteryPreferPage(request, response, model);
		
		return "manager/lottery/lotteryPreferList";
		
	}
	
	/**
	 * 添加倾向抽奖
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/lotteryPreferPageAdd.do")
	@ResponseBody
	public AjaxResult lotteryPreferPageAdd(HttpServletRequest request,HttpServletResponse response,Model model){
		 return niLotteryPreferDictService.lotteryPreferPageAdd(request, response, model);
	}
	
	
	/**
	 * 跳转到添加倾向抽奖页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/jumpTo_lotteryPreferAddPage.do")
	public String jumpTo_lotteryPreferAddPage(HttpServletRequest request,Model model){
		
		niLotteryPreferDictService.jumpTo_lotteryPreferAddPage(request, model);
		
		return "manager/lottery/lotteryPreferAdd";
	}
	
	/**
	 *  保存编辑
	 * @return
	 */
	@RequestMapping("/lotteryPreferEditSave.do")
	@ResponseBody
	public AjaxResult lotteryPreferEditSave(HttpServletRequest request,HttpServletResponse response,Model model){
		return niLotteryPreferDictService.lotteryPreferEditSave(request, response, model);
	}
	
}
