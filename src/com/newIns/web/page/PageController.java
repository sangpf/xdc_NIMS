package com.newIns.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.service.PageService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

@Controller
@RequestMapping("/light")
public class PageController {
	
	@Autowired
	private PageService pageService;
	
	// 保存定时器
	@RequestMapping("/saveTimerPage.do")
	@ResponseBody
	public AjaxResult saveTimerPage(HttpServletRequest request,Model model){
		return pageService.saveTimerPage(request,model);
	}
	
	// 定时器
	@RequestMapping("/jumpTimerPage.do")
	public String jumpTimerPage(HttpServletRequest request,Model model){
		String itemId = request.getParameter("itemId");
		model.addAttribute("itemId", itemId);
		return "manager/timerPage/timerPage";
	}
	
	//下移
	@RequestMapping("/moveDownPage.do")
	@ResponseBody
	public AjaxResult moveDownPage(HttpServletRequest request,Model model){
		
		return pageService.moveDownPage(request, model);
	}
	
	//上移
	@RequestMapping("/moveUpPage.do")
	@ResponseBody
	public AjaxResult moveUpPage(HttpServletRequest request,Model model){
		
		return pageService.moveUpPage(request, model);
	}
	
	// 置顶
	@RequestMapping("/setTopPage.do")
	@ResponseBody
	public AjaxResult setTopPage(HttpServletRequest request,Model model){
		
		AjaxResult changeTopStatus = pageService.changeTopStatus(request, model);
		return changeTopStatus;
	}
	
	// 批量修改状态
	@RequestMapping("/changePageStatus.do")
	@ResponseBody
	public AjaxResult changePageStatus(HttpServletRequest request,Model model){
		
		String status_str = request.getParameter("status");
		
		Integer status = null;
		if(StrUtils.isNotEmpty(status_str)){
			status = Integer.valueOf(status_str);
		}
		
		AjaxResult ajaxResult = pageService.changePageStatus(request,model,status);
		
		return ajaxResult;
	}
	
	// 保存页面
	@RequestMapping("/savePage.do")
	@ResponseBody
	public AjaxResult savePage(HttpServletRequest request,Model model){
		
		AjaxResult savePage = pageService.savePage(request, model);
		return savePage;
	}
	
	/**
	 * 跳转到添加
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPageJump.do")
	public String addPageJump(HttpServletRequest request,Model model){
		
		pageService.addPageJump(request, model);
		
		return "manager/page/addpage";
	}
	
	/**
	 * 列表查询
	 */
	@RequestMapping("/listPage.do")
	public String listPage(HttpServletRequest request,HttpServletResponse response,Model model){
		
		pageService.listPage(request,model);
		
		return "manager/page/pagelist";
		
	}
	
}
