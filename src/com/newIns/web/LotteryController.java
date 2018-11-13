package com.newIns.web;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.Lottery;
import com.newIns.model.award.Award;
import com.newIns.service.AwardService;
import com.newIns.service.LotteryService;
import com.newIns.tools.AjaxResult;

/**@Description  抽奖管理对应controller
 * @author MaNia_chAng
 * @time 2016年7月13日 下午6:47:19
 */
@Controller
@RequestMapping("/platform")
public class LotteryController {
	private static Logger log = Logger.getLogger(LotteryController.class);
	
	@Resource
	private LotteryService lotteryService;
	@Resource
	private AwardService awardService;
	
	//页面跳转
	/**
	 * @Title: lotteryAddPage  
	 * @Author: MaNia_chAng
	 * @Description: 跳转到添加页面
	 * @return String
	 * @Time 2016年7月18日 上午10:26:22
	 */
	@RequestMapping("/lotteryAdd.do")
    public ModelAndView lotteryAddPage(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("status", 2);
		List<Award> awardList = awardService.selectList(hashMap);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("manager/LotteryAdd");
	    modelAndView.addObject("awardList", awardList);
        return modelAndView;
    }
	

	/**
	 * 查询抽奖组合信息
	 */
	@RequestMapping("/lotteryList.do")
	 public ModelAndView lotteryList(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		log.info("========================>>分页查询抽奖组合信息");
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	String searchOption = request.getParameter("searchOption");
		String searchContent = request.getParameter("searchContent");
		
		if(searchOption!=null && !searchOption.trim().equals("")){
			//判断选择的是抽奖ID还是抽奖名称
			if(searchOption.trim().equals("1")){
				//抽奖ID
				hashMap.put("lotteryId", searchContent.trim());
			}else if(searchOption.trim().equals("2")){
				//奖品提供者
				hashMap.put("lotteryName", searchContent.trim());
			}			
		}
		
		List<Lottery> lotteryList = lotteryService.selectList(hashMap);
		 ModelAndView modelAndView = new ModelAndView();
	     modelAndView.setViewName("manager/LotteryList");
	     modelAndView.addObject("lotteryList", lotteryList);
	     return modelAndView;
		
	} 
	
	/**
	 *  添加抽奖组合
	 */
	@RequestMapping("/addLottery.do")
	@ResponseBody
	public AjaxResult addLottery(HttpServletRequest request,HttpServletResponse response){
		log.info("========================>>添加抽奖组合");
		String lotteryName = request.getParameter("lotteryName");
		String award1IdStr = request.getParameter("award1Id");
		String award1RateStr = request.getParameter("award1Rate");
		String award2IdStr = request.getParameter("award2Id");
		String award2RateStr = request.getParameter("award2Rate");
		String award3IdStr = request.getParameter("award3Id");
		String award3RateStr = request.getParameter("award3Rate");
		String comment = request.getParameter("comment");
		
		Lottery lottery = new Lottery();
		lottery.setLotteryName(lotteryName);
		lottery.setComment(comment);
		lottery.setRankNum(1);

		
		 
		if(award1IdStr!=null && !award1IdStr.trim().equals("")){
			int award1Id = Integer.valueOf(award1IdStr.trim());
			lottery.setAward1Id(award1Id);
		}
		
		
		if(award1RateStr!=null && !award1RateStr.trim().equals("")){
			//double award1Rate = Double.parseDouble(award1RateStr.trim());
			lottery.setAward1Rate(award1RateStr);
		}
		
		if(award2IdStr!=null && !award2IdStr.trim().equals("")){
			int award2Id = Integer.valueOf(award2IdStr.trim());
			lottery.setAward2Id(award2Id);
			lottery.setRankNum(2);
		}
				
		if(award2RateStr!=null && !award2RateStr.trim().equals("")){
			//double award2Rate = Double.parseDouble(award2RateStr.trim());
			lottery.setAward2Rate(award2RateStr);
		}

		if(award3IdStr!=null && !award3IdStr.trim().equals("")){
			int award3Id = Integer.valueOf(award3IdStr.trim());
			lottery.setAward3Id(award3Id);
			lottery.setRankNum(3);
		}
		
		 
		if(award3RateStr!=null && !award3RateStr.trim().equals("")){
			//double award3Rate = Double.parseDouble(award3RateStr.trim());
			lottery.setAward3Rate(award3RateStr);
		}
		
		int addLottery = 0;
		try{
			addLottery = lotteryService.addLottery(lottery);
		}
		catch(Exception e){
			return AjaxResult.errorResult("添加失败");
		}
		
		if(addLottery>0)
			 log.info("==========================>>添加成功!");
		
		return AjaxResult.successResult("添加成功");	 
		
	}
	
	/**
	 * 批量删除抽奖组合
	 */
	@RequestMapping("/deleteLottery.do")
	@ResponseBody
	public AjaxResult deleteAward(HttpServletRequest request,HttpServletResponse response){
		String lotteryIds = request.getParameter("lotteryId");
		String[] lotteryIdArray = lotteryIds.split("!");
		ArrayList<Integer> lotteryIdList = new ArrayList<Integer>();
		for(int i=0;i<lotteryIdArray.length;i++){
			lotteryIdList.add(Integer.valueOf(lotteryIdArray[i]));
		}
			
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("lotteryIdList", lotteryIdList);
		int deleteByPrimaryKeySelective = 0;
		try {
			deleteByPrimaryKeySelective = lotteryService.deleteLotteryByIds(hashMap);
			log.info("===============================>>批量删除抽奖组合");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(deleteByPrimaryKeySelective>0){
			log.info("===============================>>批量删除抽奖组合成功");
		}
			
		return AjaxResult.successResult("批量删除抽奖组合成功");
		}
	
	@RequestMapping(value ="/searchAward.do",method = RequestMethod.POST) 
	@ResponseBody
	public Award searchAwardById(HttpServletRequest request) throws UnsupportedEncodingException{
		log.info("========================>>查询奖品信息");
		String awardStr = request.getParameter("awardId");
		int awardId=0;
		if(awardStr!=null && !awardStr.trim().equals("")){
			awardId = Integer.valueOf(awardStr.trim());
		}
		Award award = lotteryService.searchAwardById(awardId);
		
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/LotteryAdd");
	    modelAndView.addObject("award", award);*/


	   return award;
		
	}
	
	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/toLotteryEditPage.do")
	@ResponseBody
	 public Lottery toLotteryEditPage(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		log.info("========================>>跳转到抽奖组合编辑页面");
		
		String lotteryIdStr = request.getParameter("lotteryId");
		int lotteryId=0;
		if(lotteryIdStr!=null && !lotteryIdStr.trim().equals("")){
			lotteryId=Integer.valueOf(lotteryIdStr.trim());
			
		}
		Lottery lottery = lotteryService.searchEditInfo(lotteryId);
/*		 ModelAndView modelAndView = new ModelAndView();
	     modelAndView.setViewName("manager/LotteryEdit");
	     modelAndView.addObject("lottery", lottery);*/
	     return lottery;
		
	} 
	
	/**
	 * 保存抽奖组合编辑信息
	 */
	@RequestMapping("/updateLottery.do")
	@ResponseBody
	public AjaxResult updatelottery(HttpServletRequest request,HttpServletResponse response){
		log.info("========================>>保存抽奖组合编辑信息");
		String lotteryIdStr = request.getParameter("lotteryId");
		String lotteryName = request.getParameter("lotteryName");
		String award1IdStr = request.getParameter("award1Id");
		String award1RateStr = request.getParameter("award1Rate");
		String award2IdStr = request.getParameter("award2Id");
		String award2RateStr = request.getParameter("award2Rate");
		String award3IdStr = request.getParameter("award3Id");
		String award3RateStr = request.getParameter("award3Rate");
		String comment = request.getParameter("comment");

		
		Lottery lottery = new Lottery();
		lottery.setLotteryName(lotteryName);
		lottery.setComment(comment);
		lottery.setLotteryId(Integer.valueOf(lotteryIdStr.trim()));
		lottery.setRankNum(1);

		
		 
		if(award1IdStr!=null && !award1IdStr.trim().equals("")){
			int award1Id = Integer.valueOf(award1IdStr.trim());
			lottery.setAward1Id(award1Id);
		}
		
		
		if(award1RateStr!=null && !award1RateStr.trim().equals("")){
			//double award1Rate = Double.parseDouble(award1RateStr.trim());
			lottery.setAward1Rate(award1RateStr);
		}
		
		if(award2IdStr!=null && !award2IdStr.trim().equals("")){
			int award2Id = Integer.valueOf(award2IdStr.trim());
			lottery.setAward2Id(award2Id);
			lottery.setRankNum(2);
		}
				
		if(award2RateStr!=null && !award2RateStr.trim().equals("")){
			//double award2Rate = Double.parseDouble(award2RateStr.trim());
			lottery.setAward2Rate(award2RateStr);
		}

		if(award3IdStr!=null && !award3IdStr.trim().equals("")){
			int award3Id = Integer.valueOf(award3IdStr.trim());
			lottery.setAward3Id(award3Id);
			lottery.setRankNum(3);
		}
		
		 
		if(award3RateStr!=null && !award3RateStr.trim().equals("")){
			//double award3Rate = Double.parseDouble(award3RateStr.trim());
			lottery.setAward3Rate(award3RateStr);
		}
		int updateLottery = 0;
		try {
			updateLottery = lotteryService.updateLottery(lottery);
		} catch (Exception e) {
			return AjaxResult.errorResult("失败");
		}
		if(updateLottery>0){
			log.info("===============================>>编辑成功");
		}
			
		return AjaxResult.successResult("编辑成功");
		}

}
