package com.newIns.web;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.model.award.Award;
import com.newIns.model.award.AwardPool;
import com.newIns.service.AwardPoolService;
import com.newIns.service.AwardService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;

/**@Description  奖品管理Controller
 * @author MaNia_chAng
 * @time 2016年7月11日 下午6:35:26
 */
@Controller
@RequestMapping("/platform")
public class AwardController {
	 private static Logger log = Logger.getLogger(AwardController.class);
	 
	@Resource
	private AwardService awardService;
	@Resource
	private AwardPoolService awardPoolService;
	
	 /**
	  * 跳转到添加奖品页面
	  */
	@RequestMapping("/awardAdd.do")	   
	 public ModelAndView awardAddPage(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("status", 2);
		List<AwardPool> awardPoolList = awardPoolService.searchAwardByCondition(hashMap);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("manager/AwardAdd");
	    modelAndView.addObject("awardPoolList", awardPoolList);

	        return modelAndView;
	    }
	 
	 /**
	  *  查询奖品信息
	  */
	 @RequestMapping("/awardList.do")
	 public String awardList(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
			log.info("========================>>分页查询奖品信息");
	    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    	String searchOption = request.getParameter("searchOption");
			String searchContent = request.getParameter("searchContent");
			String price = request.getParameter("price");
			String awardType = request.getParameter("awardType");
			String status = request.getParameter("status");
			
			if(searchOption!=null && !searchOption.trim().equals("")){
				//判断选择的是奖品名称，奖品ID,还是提供者。
				if(searchOption.trim().equals("1")){
					//奖品名称
					hashMap.put("awardName", searchContent.trim());
				}else if(searchOption.trim().equals("2")){
					//奖品提供者
					hashMap.put("provider", searchContent.trim());
				}
				else if(searchOption.trim().equals("3")){
					//奖品ID
					hashMap.put("awardId", Integer.valueOf(searchContent.trim()));
				}
			}
			if(price!=null && !price.trim().equals("")){
				//奖品价格
				hashMap.put("price", Integer.valueOf(price));
			}
			if(awardType!=null && !awardType.trim().equals("")){
				//奖品类型
				hashMap.put("awardType", awardType.trim());
			}
			if(status==null || status.trim().equals("")){
				hashMap.put("status", 2);
			}
			if(status!=null && !status.trim().equals("")){
				//奖池状态
				hashMap.put("status", status);
			}
	    	
			List<Award> awardList = awardService.selectList(hashMap);
			
			model.addAttribute("awardList", awardList);
			
	        return "manager/award/list_award";
	    }
	/**
	 * 批量删除奖品
	 */
	@RequestMapping("/deleteAward.do")
	@ResponseBody
	public AjaxResult deleteAward(HttpServletRequest request,HttpServletResponse response){
		String awardIds = request.getParameter("awardId");
		String[] awardIdArray = awardIds.split("!");
		ArrayList<Integer> awardIdList = new ArrayList<Integer>();
		for(int i=0;i<awardIdArray.length;i++){
			awardIdList.add(Integer.valueOf(awardIdArray[i]));
		}
			
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("awardIdList", awardIdList);
		int deleteByPrimaryKeySelective = 0;
		try {
			deleteByPrimaryKeySelective = awardService.deleteAwardByIds(hashMap);
			log.info("===============================>>批量删除奖品");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(deleteByPrimaryKeySelective>0){
			log.info("===============================>>批量删除奖品成功");
		}
			
		return AjaxResult.successResult("批量删除奖品成功");
		}	 
	
	/**
	 * @Description: 根据奖品类别ID查询奖品信息
	 */

	@RequestMapping(value ="/searchAwardPool.do",method = RequestMethod.POST) 
	@ResponseBody
	public Award searchAwardById(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		log.info("========================>>查询奖品信息");
		String awardPoolIdStr = request.getParameter("awardPoolId");
		int awardPoolId=0;
		if(awardPoolIdStr!=null && !awardPoolIdStr.trim().equals("")){
			awardPoolId = Integer.valueOf(awardPoolIdStr.trim());
		}
		Award award = awardService.searchAwardById(awardPoolId);
		
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/AwardAdd");
	    modelAndView.addObject("award", award);
*/

	   return award;
		
	}
	/**
	 * @Description: 添加奖品
	 */
	@RequestMapping("/addAward.do")
	@ResponseBody
	public AjaxResult addAward(HttpServletRequest request,HttpServletResponse response,@RequestParam("uploadImage") MultipartFile file,Model model) throws Exception{
		log.info("===========================>>上传图片");

		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/award");
		log.info(uploadFile);
		
		//获取文件的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		log.info("===================>> 文件的存储路径 :" + jdbcUrl);
		
		log.info("========================>>添加奖品");
		String awardPoolIdStr = request.getParameter("awardPoolId");
		String awardNumStr = request.getParameter("awardNum");
		String awardName = request.getParameter("awardName");
		String comment = request.getParameter("comment");
		int awardPoolId = 0;
		if(awardPoolIdStr!=null && !awardPoolIdStr.trim().equals("")){
			awardPoolId = Integer.valueOf(awardPoolIdStr.trim());
		}
		
		int awardNum = 0;
		if(awardNumStr!=null && !awardNumStr.trim().equals("")){
			awardNum = Integer.valueOf(awardNumStr.trim());
		}
		Award award = new Award();
		award.setAwardName(awardName);
		award.setAwardPoolId(awardPoolId);
		award.setAwardNum(awardNum);
		award.setAwardPic(jdbcUrl);
		award.setComment(comment);
		if("".equals(comment)){
			award.setComment("恭喜中奖！");
		}
		
		int addAward = 0;
		try{
			addAward = awardService.addAward(award);
		}
		catch(Exception e){
			return AjaxResult.errorResult("失败");
		}
		
		if(addAward>0)
			 log.info("==========================>>添加成功!");
		
		return AjaxResult.successResult("添加成功");	 
		
	}
	
	/**
	 * @Description: 跳转到编辑页面
	 */
	@RequestMapping(value ="/toAwardEditPage.do")
	public String toEditPage(HttpServletRequest request,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		log.info("========================>>跳转到编辑页面");
		String awardIdStr = request.getParameter("awardId");
		int awardId=0;
		if(awardIdStr!=null && !awardIdStr.trim().equals("")){
			awardId = Integer.valueOf(awardIdStr.trim());
		}
		Award award = awardService.searchEditInfo(awardId);
		
		model.addAttribute("award", award);

	   return "manager/award/new_award";
	}
	
	/**
	 * @Description: 保存奖品编辑信息
	 */
	@RequestMapping("/updateAward.do")
	@ResponseBody
	public AjaxResult updateAward(HttpServletRequest request,@RequestParam("uploadImage") 
			MultipartFile file,Model model) throws Exception{

		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/award");
		log.info(uploadFile);
		
		//获取文件的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		log.info("===================>> 文件的存储路径 :" + jdbcUrl);
		
		String awardPoolIdStr = request.getParameter("awardPoolId");
		String awardIdStr = request.getParameter("awardId");
		String awardNumStr = request.getParameter("awardNum");
		String awardName = request.getParameter("awardName");
		String comment= request.getParameter("comment");
		int awardPoolId = 0;
		if(awardPoolIdStr!=null && !awardPoolIdStr.trim().equals("")){
			awardPoolId = Integer.valueOf(awardPoolIdStr.trim());
		}
		
		int awardNum = 0;
		if(awardNumStr!=null && !awardNumStr.trim().equals("")){
			awardNum = Integer.valueOf(awardNumStr.trim());
		}
		int awardId =0;
		if(awardIdStr!=null && !awardIdStr.trim().equals("")){
			awardId = Integer.valueOf(awardIdStr.trim());
		}
		Award award = new Award();
		award.setAwardName(awardName);
		award.setAwardPoolId(awardPoolId);
		award.setAwardNum(awardNum);
		award.setAwardId(awardId);
		award.setAwardPic(jdbcUrl);
		award.setComment(comment);
		if("".equals(comment)){
			award.setComment("恭喜中奖！");
		}
		try{
			awardService.updateAward(award);
		}catch(Exception e){
			return AjaxResult.errorResult("失败");
		}
		
		return AjaxResult.successResult("编辑成功");	 
		
	}
	
	
}
