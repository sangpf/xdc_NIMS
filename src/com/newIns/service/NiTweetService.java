package com.newIns.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.tweet.NiTweet;
import com.newIns.tools.AjaxResult;

public interface NiTweetService {
	
	//查询所有
	List<NiTweet> findAll();
	
	/**
	 * 查询所有推文
	 */
	void findAllTweets(HttpServletRequest request,HttpServletResponse response,Model model);
	
	/**
	 * 保存推文
	 */
	AjaxResult saveNiTweet(HttpServletRequest request,HttpServletResponse response,MultipartFile file);
	
	/**
	 * 批量删除
	 */
	AjaxResult deleteTweetById(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 跳转到 编辑推文页面
	 */
	void editNiTweetJump(HttpServletRequest request,Model model);
}
