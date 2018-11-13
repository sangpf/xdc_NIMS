package com.newIns.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.award.AwardPool;
import com.newIns.service.AwardPoolService;
import com.newIns.tools.AjaxResult;

/**
 * @Description  Orz!_逢凶化吉_招财进宝_永无bug！
 * @author MaNia_chAng
 * @time 2016年11月29日 上午10:15:12
 */
@Controller
@RequestMapping("/platform")
public class AwardPoolController {
	 private static Logger log = Logger.getLogger(AwardController.class);
	 
		@Resource
		private AwardPoolService awardPoolService;
		/**
		 * 奖池列表
		 */
	 @RequestMapping("/awardPool.do")
	 public ModelAndView awardPool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		log.info("========================>>奖池管理页面");
		List<AwardPool> listret=new ArrayList<AwardPool>();
		listret=awardPoolService.selectList();
	
         ModelAndView modelAndView = new ModelAndView();
         modelAndView.setViewName("manager/AwardPool");
         modelAndView.addObject("awardpoolList", listret);

        return modelAndView;
     }
	 
		/**
		 * 添加奖池
		 */
		@RequestMapping("/addawardpool.do")
		@ResponseBody
		 public AjaxResult addawardpool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
				log.info("========================>>奖池添加页面");
				AjaxResult ajaxResult = new AjaxResult();
				//List<AwardPool> listret=new ArrayList<AwardPool>();
				
				String awardpoolname = request.getParameter("awardpoolname");
				String provider = request.getParameter("provider");
				String equalMoney = request.getParameter("equalMoney");
				String totalNum = request.getParameter("totalNum");
				String awardType = request.getParameter("awardType");
				String awardLink = request.getParameter("awardLink");
			
				String btime = request.getParameter("btime");
				String etime = request.getParameter("etime");
				String awardDes = request.getParameter("awardDes");
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				


				AwardPool awardpool=new AwardPool();
			    awardpool.setAwardPoolName(awardpoolname);
			    awardpool.setProvider(provider);
			    awardpool.setEqualMoney(Float.valueOf(equalMoney));
			    awardpool.setTotalNum(Integer.valueOf(totalNum));
			    awardpool.setAwardType(Integer.valueOf(awardType));
			    awardpool.setAwardLink(awardLink);
			    try{
				    awardpool.setValidBTime(simpleDateFormat.parse(btime));
				    awardpool.setValidETime(simpleDateFormat.parse(etime));
			    }
			    catch (ParseException e) {
			    	   e.printStackTrace();
			    }

			    awardpool.setAwardDes(awardDes);
		    
			    try {
					//添加奖池
			    	awardPoolService.addAwardPool(awardpool);
					
						log.info("=============================>>添加奖池成功");
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "添加奖池成功!");
					
				} catch (Exception e) {
					e.printStackTrace();
					log.info("======================>>添加奖池信息出现异常");
					ajaxResult.put("success", false);
					ajaxResult.put("msg", "添加奖池失败!");
				}
		     


		        return ajaxResult;
		    }
		
		/**
		 * 条件查询
		 */
		@RequestMapping("/searchawardPool.do")
		
		 public ModelAndView searchawardPool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
				log.info("========================>>奖池添加页面");
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				List<AwardPool> listret=new ArrayList<AwardPool>();
				String selectdetail = request.getParameter("selectdetail");
				String searchContent = request.getParameter("searchContent");
				String selectprice = request.getParameter("selectprice");
				String selectstatus = request.getParameter("selectstatus");
				String selectawardType = request.getParameter("selectawardType");
				String reservation = request.getParameter("reservation");
				String starTime = "";
				String endTime  = "";
				if(reservation!=null && !reservation.trim().equals("")){
					String[] split = reservation.split("-");
					starTime = split[0];
					endTime  = split[1];
				}
				int lprice;
				int hprice;
				if(selectprice!=null && !selectprice.trim().equals("")){
					String[] split = selectprice.split("-");
					lprice =Integer.valueOf(split[0]) ;
					hprice  = Integer.valueOf(split[1]) ;
					hashMap.put("lprice", lprice);
					hashMap.put("hprice", hprice);
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String starTime1 = "";
				String endTime1 = "";
				if(starTime!=null && !starTime.trim().equals("")){
					Date date = new Date(starTime);
					Date date2 = new Date(endTime);
					starTime1 = format.format(date);
					endTime1 = format.format(date2);
					hashMap.put("starTime", starTime1);
					hashMap.put("endTime", endTime1);
				}
				if(selectdetail!=null && selectdetail.length()>0 && searchContent!=null &&searchContent.length()>0){
					hashMap.put(selectdetail, searchContent);
				}
				if(selectstatus!=null && !selectstatus.trim().equals("")){
					//分类
					hashMap.put("status", Integer.valueOf(selectstatus));
				}
				if(selectawardType!=null && !selectawardType.trim().equals("")){
					//分类
					hashMap.put("awardType", Integer.valueOf(selectawardType));
				}
				
				List<AwardPool> awardpoolList=new ArrayList<AwardPool>();
				
				 try {
						//查询奖池
					 awardpoolList=awardPoolService.searchAwardByCondition(hashMap);
					 
						
							log.info("=============================>>按条件查询奖池成功");
							
						
					} catch (Exception e) {
						e.printStackTrace();
						log.info("======================>>按条件查询奖池失败");
						
					}
			    
			 
		     
			   // List<NiSurveyQuestionnaire> selectList = niSurveyQuestionnaireService.selectList(hashMap);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("manager/AwardPool");
				modelAndView.addObject("awardpoolList", awardpoolList);
				return modelAndView;

		      
		    }
		
		/**
		 * 
		 * 批量上架
		 */
		@ResponseBody
		@RequestMapping("/updateawardpool.do")
		 public AjaxResult updateawardpool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
				log.info("========================>>奖池上架");
				
				String awardPoolId = request.getParameter("awardPoolId");
				
				try{
				awardPoolService.updatestatusAwardPool(2,awardPoolId);
				
				}catch(Exception e){
					e.printStackTrace();
					log.info("======================>>上架奖品失败");
					return AjaxResult.errorResult("上架失败");
				}

		        return AjaxResult.successResult("上架成功");
		    }
		/**
		 * 批量下架
		 */
		@ResponseBody
		@RequestMapping("/updateawardpool1.do")
		 public AjaxResult updateawardpool1(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
				log.info("========================>>奖池下架");
				
				String awardPoolId = request.getParameter("awardPoolId");
				
				try{
				awardPoolService.updatestatusAwardPool(1,awardPoolId);
				
				}catch(Exception e){
					e.printStackTrace();
					log.info("======================>>下架奖品失败");
					return AjaxResult.errorResult("下架失败");
				}
		         


		        return AjaxResult.successResult("下架成功");
		    }
		/**
		 * 批量失效
		 */
		@ResponseBody
		@RequestMapping("/updateawardpool2.do")
		 public AjaxResult updateawardpool2(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
				log.info("========================>>奖池失效");
				
				String awardPoolId = request.getParameter("awardPoolId");
				
				try{
				awardPoolService.updatestatusAwardPool(3,awardPoolId);
				
				}catch(Exception e){
					e.printStackTrace();
					log.info("======================>>奖品失效失败");
					return AjaxResult.errorResult("失效失败");
				}
		         


		        return AjaxResult.successResult("奖品已失效");
		    }
		
		/**
		 * 删除奖池
		 */
		@ResponseBody
		@RequestMapping("/deleteAwardPool.do")
		 public AjaxResult deleteAwardPool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
			log.info("========================>>奖池删除");
			
			String awardPoolId = request.getParameter("awardPoolId");
			
			try{
			awardPoolService.deleteAwardPool(awardPoolId);
			
			}catch(Exception e){
				e.printStackTrace();
				log.info("======================>>奖池删除失败");
				return AjaxResult.errorResult("奖池删除失败……");
			}
	         


	        return AjaxResult.successResult("被你删掉惹(￣ˇ￣)");
	    }
		
		
		/**
		 * 查询单个奖池信息
		 */
		@ResponseBody
		@RequestMapping("/getAwardPoolInfo.do")
		 public AwardPool getAwardPoolInfo(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
			log.info("========================>>查询单个奖池信息");
			
			String awardPoolId = request.getParameter("awardPoolId");
			
			AwardPool awardpool = new AwardPool();	         	       
			
			awardpool=awardPoolService.getAwardPoolInfo(Integer.valueOf(awardPoolId));
	    	
			System.out.println("awardId is" +awardpool.getAwardPoolId());
			
/*	         ModelAndView modelAndView = new ModelAndView();
	         modelAndView.setViewName("manager/AwardPool");
	         modelAndView.addObject("awardpool", awardpool);
	         modelAndView.addObject("isEdit", 1);*/


	        return awardpool;
	    }
		
		
		@ResponseBody
		@RequestMapping("/editAwardPool.do")
		public AjaxResult editAwardPool(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{			
			log.info("========================>>奖池编辑");
			AjaxResult ajaxResult = new AjaxResult();
			//List<AwardPool> listret=new ArrayList<AwardPool>();
			String awardPoolId = request.getParameter("awardPoolId");
			String awardpoolname = request.getParameter("awardpoolname");
			String provider = request.getParameter("provider");
			String equalMoney = request.getParameter("equalMoney");
			String totalNum = request.getParameter("totalNum");
			String awardType = request.getParameter("awardType");
			String awardLink = request.getParameter("awardLink");
		
			String btime = request.getParameter("btime");
			String etime = request.getParameter("etime");
			String awardDes = request.getParameter("awardDes");
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			


			AwardPool awardpool=new AwardPool();
			awardpool.setAwardPoolId(Integer.valueOf(awardPoolId));
		    awardpool.setAwardPoolName(awardpoolname);
		    awardpool.setProvider(provider);
		    awardpool.setEqualMoney(Float.valueOf(equalMoney));
		    awardpool.setTotalNum(Integer.valueOf(totalNum));
		    awardpool.setAwardType(Integer.valueOf(awardType));
		    awardpool.setAwardLink(awardLink);
		    try{
			    awardpool.setValidBTime(simpleDateFormat.parse(btime));
			    awardpool.setValidETime(simpleDateFormat.parse(etime));
		    }
		    catch (ParseException e) {
		    	   e.printStackTrace();
		    }

		    awardpool.setAwardDes(awardDes);
	    
		    try {
				//添加奖池
		    	awardPoolService.editAwardPool(awardpool);
				
					log.info("=============================>奖池编辑成功");
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "奖池编辑成功!");
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("======================>>编辑奖池信息出现异常");
				ajaxResult.put("success", false);
				ajaxResult.put("msg", "奖池编辑失败!");
			}
	     


	        return ajaxResult;
		}

}
