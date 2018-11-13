package com.newIns.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.service.NiTweetService;
import com.newIns.tools.AjaxResult;

/**
 * 推文
 * @author 11409
 *
 */
@Controller
@RequestMapping("/platform")
public class NiTweetController {
	
	@Autowired
	private NiTweetService niTweetService;
	
	/**
	 * 查询所有推文  条件查询
	 * @return
	 */
	@RequestMapping("/findAllTweets.do")
	public String findAllTweets(HttpServletRequest request,HttpServletResponse response,Model model){
		
		niTweetService.findAllTweets(request, response, model);
		return "manager/tweet/NiTweetList";
	}
	
	/**
	 * 跳转到添加推文页面
	 * @return
	 */
	@RequestMapping("/addNiTweetJump.do")
	public String addNiTweetJump(){
		return "manager/tweet/addNiTweet";
	}
	
	/**
	 * 跳转到 编辑推文页面
	 * @return
	 */
	@RequestMapping("/editNiTweetJump.do")
	public String editNiTweetJump(HttpServletRequest request,Model model){
		niTweetService.editNiTweetJump(request, model);
		return "manager/tweet/addNiTweet";
	}
	
	/**
	 * 保存推文
	 * @return
	 */
	@RequestMapping("/saveNiTweet.do")
	@ResponseBody
	public AjaxResult saveNiTweet(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile file){
		return niTweetService.saveNiTweet(request, response,file);
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTweetById.do")
	public AjaxResult deleteTweetById(HttpServletRequest request,HttpServletResponse response){
		return niTweetService.deleteTweetById(request, response);
	}
	
}
