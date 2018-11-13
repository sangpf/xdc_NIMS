/**
 * 
 */
package com.newIns.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiCarousel;
import com.newIns.service.NiCarouselService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;

/**
 * @Description 页面管理-广告管理_轮播图广告管理
 * @author Guan
 * @time 2016年7月4日 下午3:38:00
 */
@Controller
@RequestMapping("/platform")
public class NiCarouselController {
	private static Logger log = Logger.getLogger(NiCarouselController.class);
	@Resource
	private NiCarouselService carouselService;
	
	/**
	 * 保存轮播图
	 */
	@RequestMapping("/save_edit_Carorse.do")
	@ResponseBody
	public AjaxResult save_edit_Carorse(HttpServletRequest request,Model model){
		return carouselService.save_edit_Carorse(request,model);
		
	}
	
	// 跳转到编辑轮播图页面
	@RequestMapping("/jumpEditCarorse.do")
	public String jumpEditCarorse(HttpServletRequest request,Model model){
		
		carouselService.jumpEditCarorse(request,model);
		
		return "manager/carousel/newCarousel";
	}

	/**  加载轮播图列表
	 */
	@RequestMapping("/loadCarouselList.do")
	public String loadCarouselList(HttpServletRequest request,Model model) {
		log.info("========================>>加载首页轮播广告信息");

		List<NiCarousel> carouselList = carouselService.loadCarouselList();
		
		model.addAttribute("carouselList", carouselList);
		
		return "manager/carousel/listCarousel";
	}

	/**
	 *  批量发布轮播广告
	 */
	@RequestMapping("/postCarousel.do")
	@ResponseBody
	public AjaxResult postCarousel(HttpServletRequest request) {
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
			postByPrimaryKeySelective = carouselService
					.postCarouselByids(hashMap);
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
	 * @Title: revokeCarousel
	 * @Author: Guan
	 * @Description: 批量撤销轮播广告
	 * @param request
	 * @return String
	 * @Time 2016年7月5日 下午7:15:50
	 */
	@RequestMapping("/revokeCarousel.do")
	@ResponseBody
	public AjaxResult revokeCarousel(HttpServletRequest request) {
		String adIds = request.getParameter("adId");
		String[] adId = adIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		ArrayList<Integer> adIdList = new ArrayList<Integer>();
		for (int i = 0; i < adId.length; i++) {
			adIdList.add(Integer.valueOf(adId[i]));
		}
		hashMap.put("state", 0);
		hashMap.put("adIds", adIdList);
		hashMap.put("uTime", uTime);
		int revokeByPrimaryKeySelective = 0;
		try {
			revokeByPrimaryKeySelective = carouselService
					.revokeCarouselByids(hashMap);
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
	 * @Title: editCarousel
	 * @Author: Guan
	 * @Description: 编辑轮播广告
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月8日 上午11:26:59
	 */
	@RequestMapping("/editCarousel.do")
	@ResponseBody
	public List<NiAdInfo> editCarousel(HttpServletRequest request) {
	//	String carouselId = request.getParameter("carouselId");
	//	int carousel_Id = Integer.parseInt(carouselId);
		//ModelAndView modelAndView = new ModelAndView();
		log.info("===============================>>跳转成功");
		List<NiAdInfo> niAdInfoList = carouselService.loadAdInfoList();//加载广告池信息
		//modelAndView.setViewName("manager/EditCarousel");
		//modelAndView.addObject("editCarousel", carousel_Id);
	//	modelAndView.addObject("adInfoList", niAdInfoList);
		return niAdInfoList;
	}

	/**
	 * @Title: replaceCarousel
	 * @Author: Guan
	 * @Description: 替换轮播广告
	 * @param request
	 * @return ModelAndView
	 * @Time 2016年7月8日 下午4:31:35
	 */
	@RequestMapping("/replaceCarousel.do")
	@ResponseBody
	public AjaxResult replaceCarousel(HttpServletRequest request) {
		String carouselId = request.getParameter("carouselId");
		int carousel_Id = Integer.parseInt(carouselId);
		
		String adTitle = request.getParameter("adTitle");
		String adImg = request.getParameter("adImg");
		
		
		try {
			carouselService.replaceCarousel(carousel_Id,adTitle,adImg);
			
			return AjaxResult.successResult();
		} catch (Exception e) {
			e.printStackTrace();
			
			return AjaxResult.errorResult();
		}
		
		
	}
	
	
	/**
	 * 上传广告图片
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/uploadAdPicture.do")
	@ResponseBody
	public AjaxResult uploadPicture(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile file,Model model) throws Exception{
		AjaxResult ajaxResult = new AjaxResult();
		
		//拼接保存路径
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/ad/adpool");
		
		log.info(uploadFile);
		String realPath = (String) uploadFile.get("realPath");
		log.info("=========================>>realPath:"+realPath);
		String url = (String) uploadFile.get("url");
		String contextPath = request.getContextPath();
		ajaxResult.put("url", contextPath+url);
		log.info("===================>> url 路径为 :" + contextPath+url);
		
		//获取文件的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		log.info("===================>> 文件的存储路径 :" + jdbcUrl);
		ajaxResult.put("jdbcUrl", jdbcUrl);
		
		return ajaxResult;
	}
	
	
	/**
	 * @Title: moveUpCarousel  
	 * @Author: Guan
	 * @Description: 上移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年7月29日 下午2:37:46
	 */
	@RequestMapping("/moveUpCarousel.do")
	@ResponseBody
	public AjaxResult moveUpCarousel(HttpServletRequest request) {
		String carouselId = request.getParameter("carouselId");
		int carousel_Id = Integer.valueOf(carouselId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastCarouselId = request.getParameter("lastCarouselId");
		int last_CarouselId = Integer.valueOf(lastCarouselId);
		String lastShowOrder = request.getParameter("lastShowOrder");
		int last_ShowOrder = Integer.valueOf(lastShowOrder);
		AjaxResult result = carouselService.moveUpCarousel(carousel_Id,show_Order,last_CarouselId,last_ShowOrder);
		return result;
	}
	/**
	 * @Title: moveDownCarousel  
	 * @Author: Guan
	 * @Description: 下移
	 * @param request
	 * @return AjaxResult
	 * @Time 2016年8月1日 下午12:54:48
	 */
	@RequestMapping("/moveDownCarousel.do")
	@ResponseBody
	public AjaxResult moveDownCarousel(HttpServletRequest request) {
		String carouselId = request.getParameter("carouselId");
		int carousel_Id = Integer.valueOf(carouselId);
		String showOrder = request.getParameter("showOrder");
		int show_Order = Integer.valueOf(showOrder);
		String lastCarouselId = request.getParameter("nextCarouselId");
		int next_CarouselId = Integer.valueOf(lastCarouselId);
		String lastShowOrder = request.getParameter("nextShowOrder");
		int next_ShowOrder = Integer.valueOf(lastShowOrder);
		AjaxResult result = carouselService.moveDownCarousel(carousel_Id,show_Order,next_CarouselId,next_ShowOrder);
		return result;
	}
}
