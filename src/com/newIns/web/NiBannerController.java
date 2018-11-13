/**
 * 
 */
package com.newIns.web;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiBanner;
import com.newIns.service.NiBannerService;
import com.newIns.tools.AjaxResult;

/**
 * @Description 页面管理-广告管理——banner广告管理
 * @author Guan
 * @time 2016年7月4日 下午3:38:00
 */
@Controller
@RequestMapping("/platform")
public class NiBannerController {
	private static Logger log = Logger.getLogger(NiBannerController.class);
	@Resource
	private NiBannerService bannerService;

	@RequestMapping("/loadBannerList.do")
	public ModelAndView loadBannerList(HttpServletRequest request) {
		log.info("========================>>加载banner广告信息");

		List<NiBanner> bannerList = bannerService.loadBannerList();
		String contextPath = request.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiBanner");
		modelAndView.addObject("niBanner", bannerList);
		modelAndView.addObject("contextPath", contextPath);
		return modelAndView;
	}

	@RequestMapping("/postBanner.do")
	@ResponseBody
	public AjaxResult postBanner(HttpServletRequest request) {
		String adIds = request.getParameter("adId");
		String[] adId = adIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> adIdList = new ArrayList<Integer>();
		Timestamp pTime = new Timestamp(System.currentTimeMillis());
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < adId.length; i++) {
			adIdList.add(Integer.valueOf(adId[i]));
		}
		hashMap.put("state", 1);
		hashMap.put("adIds", adIdList);
		hashMap.put("pTime", pTime);
		hashMap.put("uTime", uTime);
		int postByPrimaryKeySelective = 0;
		try {
			postByPrimaryKeySelective = bannerService
					.postBannerByids(hashMap);
			log.info("===============================>>批量发布广告成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (postByPrimaryKeySelective > 0) {
			log.info("==========================>>更改状态成功!");
		}
		return AjaxResult.successResult("批量发布广告成功");
	}
	
	/**
	 *  批量撤销轮播广告
	 */
	@RequestMapping("/revokeBanner.do")
	@ResponseBody
	public AjaxResult revokeBanner(HttpServletRequest request) {
		String adIds = request.getParameter("adId");
		String[] adId = adIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> adIdList = new ArrayList<Integer>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < adId.length; i++) {
			adIdList.add(Integer.valueOf(adId[i]));
		}
		hashMap.put("state", 0);
		hashMap.put("adIds", adIdList);
		hashMap.put("uTime", uTime);
		int revokeByPrimaryKeySelective = 0;
		try {
			revokeByPrimaryKeySelective = bannerService
					.revokeBannerByids(hashMap);
			log.info("===============================>>批量撤销广告成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (revokeByPrimaryKeySelective > 0) {
			log.info("==========================>>撤销成功!");
		}
		return AjaxResult.successResult("撤销广告成功");
	}
	
	/**
	 * @Title: editBanner
	 * @Author: Guan
	 * @Description: 编辑banner广告
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月8日 上午11:26:59
	 */
	@RequestMapping("/editBanner.do")
	@ResponseBody
	public List<NiAdInfo> editCarousel(HttpServletRequest request) {
	//	String adPosDes = request.getParameter("adPosDes");
	//	ModelAndView modelAndView = new ModelAndView();
		log.info("===============================>>跳转成功");
		List<NiAdInfo> niAdInfoList = bannerService.loadAdInfoList();
	//	modelAndView.setViewName("manager/EditBanner");
	//	modelAndView.addObject("adPos", ad_Pos);
		//modelAndView.addObject("adPosDes", adPosDes);
		//modelAndView.addObject("adInfoList", niAdInfoList);
		return niAdInfoList;
	}
	
	/**
	 * @Title: replaceBanner
	 * @Author: Guan
	 * @Description: 替换Banner广告
	 * @param request
	 * @return ModelAndView
	 * @Time 2016年7月8日 下午4:31:35
	 */
	@RequestMapping("/replaceBanner.do")
	@ResponseBody
	public AjaxResult replaceBanner(HttpServletRequest request) {
		String adPosDes = request.getParameter("adPosDes");
		String adTitle = request.getParameter("adTitle");
		String adImg = request.getParameter("adImg");
		log.info("===============================>>收到参数");
		bannerService.replaceBanner(adPosDes,adTitle,adImg);
		return AjaxResult.successResult("success");
		
	}

}
