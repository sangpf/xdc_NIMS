package com.newIns.service.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.tweet.NiTweetMapper;
import com.newIns.model.tweet.NiTweet;
import com.newIns.service.NiTweetService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.DateUtil;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

/**
 * 推文
 * @author 11409
 *
 */
@Service
public class NiTweetServiceImp implements NiTweetService{
	
	@Autowired
	private NiTweetMapper niTweetMapper;
	/**
	 * 查询所有 推文
	 */
	public List<NiTweet> findAll() {
		return niTweetMapper.findAll();
	}


	//保存推文
	public AjaxResult saveNiTweet(HttpServletRequest request,HttpServletResponse response,
			MultipartFile file){
		
		String tweetId_par = request.getParameter("tweetId");
		String tweetTitle_par = request.getParameter("tweetTitle");
		String tweetUrl_par = request.getParameter("tweetUrl");
		
		String author_str = request.getParameter("author");
		String pTime_str = request.getParameter("pTime");
		String tag_str = request.getParameter("tag");
		
		//上传图片
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, file, "img/manage/article/");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//获取图片的存储路径
		String picUrl = (String) uploadFile.get("jdbcUrl"); 
		
		NiTweet niTweet = new NiTweet();
		niTweet.setTweetTitle(tweetTitle_par);
		niTweet.setTweetUrl(tweetUrl_par);
		niTweet.setPicUrl(picUrl);
		niTweet.setAuthor(author_str);
		
		if(StrUtils.isNotEmpty(pTime_str)){
			Date pTime = DateUtil.toUtilDateFromStrDateByFormat(pTime_str, "yyyy-MM-dd");
			niTweet.setpTime(pTime);
		}
		niTweet.setTag(tag_str);
		
		//判断是新增还是编辑
		if(StrUtils.isNotEmpty(tweetId_par)){
			//编辑
			Integer tweetId = Integer.valueOf(tweetId_par);
			niTweet.setTweetId(tweetId);
			try {
				niTweetMapper.updateById(niTweet);
				return AjaxResult.successResult("编辑成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("编辑失败");
			}
			
		}else{
			//新增
			try {
				niTweetMapper.saveTweet(niTweet);
				return AjaxResult.successResult("新增成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("新增失败");
			}
			
		}
		
	}

	//批量删除
	public AjaxResult deleteTweetById(HttpServletRequest request,HttpServletResponse response) {
		String tweetId_str = request.getParameter("tweetId");
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		List<Integer> tweetIds = new ArrayList<Integer>();
		if(StrUtils.isNotEmpty(tweetId_str)){
			String[] split = tweetId_str.split("!");
			for(String id_str:split){
				Integer id = Integer.valueOf(id_str);
				tweetIds.add(id);
			}
		}
		
		retMap.put("tweetIdList", tweetIds);
		
		try {
			niTweetMapper.deleteTweetByIds(retMap);
			return AjaxResult.successResult("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("删除失败");
		}
		
	}


	// 跳转到编辑页面
	public void editNiTweetJump(HttpServletRequest request, Model model) {
		String tweetId_str = request.getParameter("tweetId");
		if(StrUtils.isNotEmpty(tweetId_str)){
			Integer tweetId = Integer.valueOf(tweetId_str);
			
			NiTweet niTweet = niTweetMapper.findOneById(tweetId);
			
			if(niTweet!=null){
				Date pTime = niTweet.getpTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if(pTime!=null){
					String pTime_Str = simpleDateFormat.format(pTime);
					model.addAttribute("pTime_Str", pTime_Str);
				}
				model.addAttribute("niTweet", niTweet);
			}
		}
	}


	// 根据条件查询  查询所有推文
	public void findAllTweets(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		String searchType_Str = request.getParameter("searchType"); //查询分类
		String tweetIdOrName_Str = request.getParameter("tweetIdOrName");   //id 名称
		
		List<NiTweet> niTweet_List = null;
		//判断是否输入查询条件
		if(StrUtils.isEmpty(tweetIdOrName_Str)){
			niTweet_List = niTweetMapper.findAll();
		}else{
			// 输入查询条件
			if(searchType_Str.trim().equals("0")){
				//输入id查询
				Integer tweetId = Integer.valueOf(tweetIdOrName_Str);
				niTweet_List = niTweetMapper.findListById(tweetId);
			}else{
				//根据名称模糊查询
				niTweet_List = niTweetMapper.findByName(tweetIdOrName_Str);
			}
		}
		model.addAttribute("niTweets", niTweet_List);
		
	}
	
	
	
}
