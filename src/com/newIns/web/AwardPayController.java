package com.newIns.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newIns.model.PostalInfo;
import com.newIns.model.award.AwardPay;
import com.newIns.model.award.AwardPayAndPostal;
import com.newIns.service.AwardPayService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.ExcelException;
import com.newIns.tools.ExcelExportUtil;
import com.newIns.tools.StrUtils;


/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午2:38:38
 */
@Controller
@RequestMapping("platform")
public class AwardPayController {
	private static Logger log = Logger.getLogger(AwardPayController.class);
	
	@Resource
	private AwardPayService awardPayService;
	
	/**
	 * 分页查询奖品发放列表
	 */
	@RequestMapping("/awardPayList.do")
	public String awardPayList(HttpServletRequest request,Model model){
		log.info("========================>>分页查询奖品发放列表");
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	String searchOption = request.getParameter("searchOption");  //输入框类型   1 问卷名称   ,  2奖品名称
		String searchContent = request.getParameter("searchContent");   //输入框查询条件
		String awardGetStatus = request.getParameter("awardGetStatus"); //领取状态   
		String awardPayStatus = request.getParameter("awardPayStatus"); //发放状态
		String awardType=request.getParameter("awardType");  	//奖品类型  1：实物；2：外链；3：玩校积分 ; 4静态优惠码 (兑换码) ; 5 现金红包
		
		if(searchOption!=null && !searchOption.trim().equals("")){
			//判断选择的是问卷名称还是奖品名称
			if(searchOption.trim().equals("1")){
				//问卷名称
				hashMap.put("qnName", searchContent.trim());
			}else if(searchOption.trim().equals("2")){
				//奖品名称
				hashMap.put("awardName", searchContent.trim());
			}			
		}
		
		if(awardGetStatus!=null && !awardGetStatus.trim().equals("")){
			hashMap.put("awardGetStatus", Integer.valueOf(awardGetStatus.trim()));
		}
		
		if(awardPayStatus!=null && !awardPayStatus.trim().equals("")){
			hashMap.put("awardPayStatus", Integer.valueOf(awardPayStatus.trim()));
		}
		if(awardType!=null && !awardType.trim().equals("")){
			hashMap.put("awardType", Integer.valueOf(awardType));
		}else{
			hashMap.put("awardType", null);
		}
//		//默认只查询实物类型奖励
//		if(awardType==null || awardType.trim().equals("")){
//			hashMap.put("awardType", 1);
//		}
		
		List<AwardPay> awardPayList = awardPayService.selectList(hashMap);
		model.addAttribute("awardPayList",awardPayList);
		model.addAttribute("searchContent",searchContent);
		return "manager/AwardPayList";
		
	}
	
	/**
	 * 跳转到一个空白的发放列表页面  默认查询未发放的投放
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 */
	@RequestMapping("/awardPayListJump.do")
	public String awardPayListJump(HttpServletRequest request,HttpServletResponse response,Model model){
		return "manager/AwardPayList";
		
	}
	
	/**
	 *  查看奖品发放信息
	 */
	@RequestMapping("/getAwardPostalInfo.do")
	@ResponseBody
	public PostalInfo getAwardPostalInfo(HttpServletRequest request,HttpServletResponse response,Model model) 
			throws UnsupportedEncodingException{
		
		log.info("========================>>查看奖品发放信息");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String userId_str = request.getParameter("userId");
		String awardId_str = request.getParameter("awardId");
		
		Integer userId = StrUtils.changeToInt(userId_str);
		Integer awardId = StrUtils.changeToInt(awardId_str);
		
		hashMap.put("userId", userId);
		hashMap.put("awardId", awardId);
		PostalInfo postalInfo = awardPayService.getAwardPostalInfo(hashMap);
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/AwardPostalInfo");
		modelAndView.addObject("postalInfo",postalInfo);*/
		return postalInfo;		
	}
	
	/*
	 * @Title:getAwardPostalInfo
	 * @Author:lijz
	 * @Description:重载查看奖励发放信息，用于导出Excel
	 */
	public PostalInfo getAwardPostalInfo(int userId, int awardId){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("userId", userId);
		hashMap.put("awardId", awardId);	
		return awardPayService.getAwardPostalInfo(hashMap);
	}
	
	
	/**
	 * 发放奖品
	 */
	@RequestMapping("/payAward.do")
	@ResponseBody
	public AjaxResult payAward(HttpServletRequest request,HttpServletResponse response){
		String orderId = request.getParameter("orderId");
		String qnType = request.getParameter("qnType");				
			
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderId", orderId);
		hashMap.put("qnType", qnType);
		
		int updatePayStatus = 0;
		try {
			updatePayStatus = awardPayService.payAward(hashMap);
			log.info("===============================>>发放奖品");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(updatePayStatus>0){
			log.info("===============================>>发放成功");
		}
			
		return AjaxResult.successResult("发放成功");
		}	
	
	/**
	 * @Title:exportSheet
	 * @author lijz
	 * @Description : 导出奖励信息到excel中
	 */
//	@ResponseBody
	@RequestMapping("exportAwardPaySheet.do")
	public void exportSheet(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("awardType", Integer.parseInt(request.getParameter("awardType")));	//根据参数选择导出信息类型
		String searchContent_str = request.getParameter("searchContent");   //页面查询条件  (问卷名称 或者奖励名称)
		
		if(StrUtils.isNotEmpty(searchContent_str)){
			try {
				searchContent_str = new String(searchContent_str.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			hashMap.put("qnName", searchContent_str);
		}

		List<AwardPay> awardPayList = awardPayService.selectList(hashMap);
//		if(awardPayList.size()<=0){
//			return AjaxResult.errorResult("无数据,导出失败");
//		}
		
		//--------拼接奖品发放信息和邮寄信息并导出Excel---begin----
		List<AwardPayAndPostal> awardPayAndPostalList = new ArrayList<AwardPayAndPostal>();
		Iterator<AwardPay> it = awardPayList.iterator();
		while(it.hasNext()){
			AwardPay awardPay = it.next();
			PostalInfo postal;
			if(awardPay!=null){
				postal = getAwardPostalInfo(awardPay.getUserId(),awardPay.getAwardId());
				AwardPayAndPostal awardPayAndPostal = awardPayService.getAwardPayAndPostalExcel(awardPay, postal);
				awardPayAndPostalList.add(awardPayAndPostal);
			}
		}
		
		LinkedHashMap<String, String> fieldMap=awardPayService.getExcelColumnName();
		//导出
		log.info("========================>>开始将报告统计列表导出到Excel");
		
		try {
			ExcelExportUtil.listToExcel(awardPayAndPostalList, fieldMap, "报告统计列表", response);
			log.info("========================>>报告统计列表导出Excel成功");
//			return AjaxResult.successResult("导出Excel成功");
		} catch(ExcelException e){
			log.info("========================>>无数据,导出Excel失败");
//			return AjaxResult.errorResult("无数据,导出失败");
		}catch (Exception e) {
			e.printStackTrace();
			log.info("========================>>报告统计列表导出Excel失败");
//			return AjaxResult.errorResult("导出Excel失败");
		}

	}
	
}
