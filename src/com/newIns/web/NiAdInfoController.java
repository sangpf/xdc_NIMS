package com.newIns.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.NiAdInfo;
import com.newIns.service.NiAdInfoService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;
@Controller
@RequestMapping("/platform")
public class NiAdInfoController {
	private static Logger log = Logger.getLogger(NiAdInfoController.class);
	
	@Resource
	private NiAdInfoService niAdInfoService;
	
	/**
	 *  轮播与adInfo中选择下拉框触发接口
	 */
	@RequestMapping("showAdInfo.do")
	public ModelAndView showAdInfo(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model){
		
		String id=httpServletRequest.getParameter("id");
		NiAdInfo niAdInfo=null;
		if(id!=null&&!("".equals(id))){
			niAdInfo=niAdInfoService.selectByPrimaryKey(Integer.valueOf(id));
		}
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("layout/Hello");
		modelAndView.addObject("niAdInfo", niAdInfo);
		return modelAndView;
	}
	
	/**
	 * 加载广告池列表
	 */
	@RequestMapping("/loadAdInfoList.do")
	public ModelAndView loadAdInfoList(HttpServletRequest request) {
		log.info("========================>>加载广告池信息");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String searchOption = request.getParameter("searchOption");
		String searchContent = request.getParameter("searchContent");
		String adType = request.getParameter("adType");
		String adStatus = request.getParameter("adStatus");

		if (searchOption != null && !searchOption.trim().equals("")) {
			// 判断选择的是广告名称，还是广告Id
			if (searchOption.trim().equals("1")) {
				// 问卷名称
				hashMap.put("adId", searchContent.trim());
			} else if (searchOption.trim().equals("2")) {
				// 发布机构名称
				hashMap.put("adTitle", searchContent.trim());
			}
		}
		if (adType != null && !adType.trim().equals("")) {
			// 问卷类型
			hashMap.put("adType", Integer.valueOf(adType));
		}

		if (adStatus != null && !adStatus.trim().equals("")) {
			// 问卷状态
			hashMap.put("adStatus", Integer.valueOf(adStatus));
		}
		
		List<NiAdInfo> adInfoList = niAdInfoService.loadAdInfoList(hashMap);
		String contextPath = request.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiAdPool");
		modelAndView.addObject("niAdInfo", adInfoList);
		modelAndView.addObject("contextPath", contextPath);
		return modelAndView;
	}
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/NiAdInfoAdd.do")
	public String addNiAdInfo() {

		return "manager/NiAdInfoAdd";
	}
	
	/**
	 *  批量删除
	 */
	@RequestMapping("/deleteAdInfo.do")
	@ResponseBody
	public AjaxResult deleteAdInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String adIds = request.getParameter("adId");
		String[] adIdArray = adIds.split("!");
		ArrayList<Integer> adIdList = new ArrayList<Integer>();
		for (int i = 0; i < adIdArray.length; i++) {
			adIdList.add(Integer.valueOf(adIdArray[i]));
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("adIds", adIdList);
		int deleteByPrimaryKeySelective = 0;
		try {
			deleteByPrimaryKeySelective = niAdInfoService
					.deleteAdInfoByIds(hashMap);
			log.info("===============================>>批量删除报告");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (deleteByPrimaryKeySelective > 0) {
			log.info("===============================>>批量删除报告成功");
		}

		return AjaxResult.successResult("批量删除广告成功");
	}

	/**
	 * 改广告状态，启用/撤销
	 */
	@RequestMapping("/changeAdInfoStatus.do")
	@ResponseBody
	public AjaxResult changeAdInfoStatus(HttpServletRequest request) {
		String adStatusStr = request.getParameter("adStatus");
		int adStatus = Integer.valueOf(adStatusStr);
		String adIds = request.getParameter("adId");
		String[] adId = adIds.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> adIdList = new ArrayList<Integer>();
		for (int i = 0; i < adId.length; i++) {
			adIdList.add(Integer.valueOf(adId[i]));
		}
		hashMap.put("adStatus", adStatus);
		hashMap.put("adIds", adIdList);
		int changeByPrimaryKeySelective = 0;
		try {
			changeByPrimaryKeySelective = niAdInfoService
					.changeAdInfoStatus(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (changeByPrimaryKeySelective > 0) {
			log.info("==========================>>更改状态成功!");
			return AjaxResult.successResult("更改状态成功!");
		}
		else return AjaxResult.errorResult("更改状态失败。。");
	}

	

	/**
	 * 添加广告
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addNiAdInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addAdInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile picfile) throws Exception {
		String adImg = "";
		AjaxResult uploadResult = new AjaxResult();
		try{
		//上传广告图片
		uploadResult = niAdInfoService.uploadPicture(request, response, picfile);		
		}
		catch (Exception e){
			log.info("==========================>>上传广告图片失败");
			return AjaxResult.successResult("上传广告图片失败");
		}
		
		// 接收新建广告相关参数
		String adTitle = request.getParameter("adTitle");
		String adLink = request.getParameter("adLink");
		String adTypeStr = request.getParameter("adType");
		String replaceAdId_str = request.getParameter("replaceAdId");
		
		Integer replaceAdId = StrUtils.changeToInt(replaceAdId_str);
		
		adImg = (String) uploadResult.get("jdbcUrl");
		int adType = 0;
		if (adTypeStr != null) {
			adType = Integer.parseInt(adTypeStr);
		}
		
		
		if(adType == 1){  // 商业  必须有替换id 
			
			if(replaceAdId == null){
				return AjaxResult.errorResult("商业广告必须有替换 Id");
			}
			
		}else if(adType == 2){  // 普通
			
			if(replaceAdId != null){
				return AjaxResult.errorResult("普通广告不能有替换 Id");
			}
			
		}
		
		
		NiAdInfo adInfo = new NiAdInfo();
		adInfo.setAdTitle(adTitle);
		adInfo.setAdStatus(1);
		adInfo.setAdLink(adLink);
		adInfo.setAdType(adType);
		adInfo.setAdImg(adImg);
		adInfo.setReplaceAdId(replaceAdId);

		// 广告创建时间
		Timestamp cTime = new Timestamp(System.currentTimeMillis());
		adInfo.setAdCTime(cTime);
		// 在数据库中生成一条记录
		int addAdInfo = 0;
		try {
			addAdInfo = niAdInfoService.addAdInfo(adInfo);
		} catch (Exception e) {
			//System.out.println(e);
			log.info("==========================>>添加失败");
			return AjaxResult.errorResult("创建广告失败");
		}

		if (addAdInfo > 0) {
			log.info("==========================>>添加成功!");
		}
		return AjaxResult.successResult("添加成功");
	}
	
	/**
	 * 跳转到编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editNiAdInfo.do")
	public ModelAndView editAdInfo(HttpServletRequest request) {
		String adId = request.getParameter("adId");

		NiAdInfo niAdInfo = niAdInfoService.selectByPrimaryKey(Integer.parseInt(adId));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiAdInfoAdd");
		modelAndView.addObject("ad", niAdInfo);
		modelAndView.addObject("isEdit", 1);
		return modelAndView;

	}
	
	

	/**
	 * 编辑广告
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/didEditAdInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult didEditAdInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile picfile) throws Exception {
		String adImg = "";
		AjaxResult uploadResult = new AjaxResult();
		try{
		//上传广告图片
		uploadResult = niAdInfoService.uploadPicture(request, response, picfile);		
		}
		catch (Exception e){
			log.info("==========================>>上传广告图片失败");
			return AjaxResult.successResult("上传广告图片失败");
		}
		
		// 接收新建广告相关参数
		String adIdStr = request.getParameter("adId");
		String adTitle = request.getParameter("adTitle");
		String adLink = request.getParameter("adLink");
		String adTypeStr = request.getParameter("adType");
		String replaceAdId_Str = request.getParameter("replaceAdId");
		
		Integer replaceAdId = StrUtils.changeToInt(replaceAdId_Str);
		
		adImg = (String) uploadResult.get("jdbcUrl");
		int adType = 0;
		if (adTypeStr != null) {
			adType = Integer.parseInt(adTypeStr);
		}
		int adId = 0;
		if (adIdStr != null) {
			adId = Integer.parseInt(adIdStr);
		}
		
		if(adType == 1){  // 商业  必须有替换id 
			
			if(replaceAdId == null){
				return AjaxResult.errorResult("商业广告必须有替换 Id");
			}
			
		}else if(adType == 2){  // 普通
			
			if(replaceAdId != null){
				return AjaxResult.errorResult("普通广告不能有替换 Id");
			}
			
		}
		
		NiAdInfo adInfo = new NiAdInfo();
		adInfo.setAdId(adId);
		adInfo.setAdTitle(adTitle);
		adInfo.setAdStatus(1);
		adInfo.setAdLink(adLink);
		adInfo.setAdType(adType);
		adInfo.setAdImg(adImg);
		adInfo.setReplaceAdId(replaceAdId);
		
		// 广告创建时间
		//Timestamp cTime = new Timestamp(System.currentTimeMillis());
		//adInfo.setAdCTime(cTime);
		// 更新广告信息
		int editAdInfo = 0;
		try {
			editAdInfo = niAdInfoService.editAdInfo(adInfo);
		} catch (Exception e) {
			//System.out.println(e);
			log.info("==========================>>编辑失败");
			return AjaxResult.errorResult("编辑广告失败");
		}

		if (editAdInfo > 0) {
			log.info("==========================>>编辑成功!");
		}
		return AjaxResult.successResult("编辑成功");
	}
	
	
}
