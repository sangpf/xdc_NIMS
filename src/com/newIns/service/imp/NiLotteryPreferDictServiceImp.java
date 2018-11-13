package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiLotteryPreferDictMapper;
import com.newIns.dao.award.NiAwardDao;
import com.newIns.model.NiLotteryPreferDict;
import com.newIns.model.award.NiAward;
import com.newIns.service.NiLotteryPreferDictService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

/**
 * 倾向抽奖
 * @author 11409
 *
 */
@Service
public class NiLotteryPreferDictServiceImp implements NiLotteryPreferDictService{
	@Autowired
	private NiLotteryPreferDictMapper niLotteryPreferDictMapper;
	@Autowired
	private NiAwardDao niAwardMapper;

	//跳转到倾向抽奖页面
	public void lotteryPreferPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<NiLotteryPreferDict> findList = niLotteryPreferDictMapper.findList();
		
		model.addAttribute("niLotteryPreferDict_List", findList);
		
	}

	//添加倾向抽奖信息
	public AjaxResult lotteryPreferPageAdd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String ruleName_add = request.getParameter("ruleName_add");
		
		String histAwardId_add = request.getParameter("histAwardId_add");
		String highRate_add = request.getParameter("highRate_add");
		String highBegin_add = request.getParameter("highBegin_add");
		String highEnd_add = request.getParameter("highEnd_add");
		String highAwardTotal_add = request.getParameter("highAwardTotal_add");  //奖品总数
		
		String midAwardId_add = request.getParameter("midAwardId_add");
		String midRate_add = request.getParameter("midRate_add");
		String midBegin_add = request.getParameter("midBegin_add");
		String midEnd_add = request.getParameter("midEnd_add");
		String midAwardTotal_add = request.getParameter("midAwardTotal_add");  //奖品总数
		
		String lowAwardId_add = request.getParameter("lowAwardId_add");
		String lowRate_add = request.getParameter("lowRate_add");
		String lowBegin_add = request.getParameter("lowBegin_add");
		String lowEnd_add = request.getParameter("lowEnd_add");
		String lowAwardTotal_add = request.getParameter("lowAwardTotal_add");  //奖品总数
		
		String comment_add = request.getParameter("comment_add");
		
		NiLotteryPreferDict niLotteryPreferDict = new NiLotteryPreferDict();
		niLotteryPreferDict.setRuleName(ruleName_add);
		niLotteryPreferDict.setComment(comment_add);
		
		try {
			if(StrUtils.isNotEmpty(histAwardId_add)){
				//高区间奖品id
				Integer histAwardId = Integer.valueOf(histAwardId_add);
				//根据奖品id查询奖励信息
				NiAward niAward = niAwardMapper.findById(histAwardId);
				if(niAward!=null){
					niLotteryPreferDict.setHighAwardName(niAward.getAwardName());
				}else{
					return AjaxResult.errorCode("高区间奖品id不存在", "001");
				}
				
				niLotteryPreferDict.setHighAwardId(histAwardId);
			}
			if(StrUtils.isNotEmpty(highRate_add)){
				niLotteryPreferDict.setHighRate(Float.valueOf(highRate_add));
			}
			if(StrUtils.isNotEmpty(highBegin_add)){
				niLotteryPreferDict.setHighBegin(Integer.valueOf(highBegin_add));
			}
			if(StrUtils.isNotEmpty(highEnd_add)){
				niLotteryPreferDict.setHighEnd(Integer.valueOf(highEnd_add));
			}
			if(StrUtils.isNotEmpty(highAwardTotal_add)){
				niLotteryPreferDict.setHighAwardTotal(Integer.valueOf(highAwardTotal_add));
				niLotteryPreferDict.setHighAwardLeft(Integer.valueOf(highAwardTotal_add)); //初始化剩余库存数
			}
			
			if(StrUtils.isNotEmpty(midAwardId_add)){
				Integer midAwardId = Integer.valueOf(midAwardId_add);
				
				//根据奖品id查询奖励信息
				NiAward niAward = niAwardMapper.findById(midAwardId);
				if(niAward!=null){
					niLotteryPreferDict.setMidAwardName(niAward.getAwardName());
				}else{
					return AjaxResult.errorCode("中区间奖品id不存在", "001");
				}
				
				niLotteryPreferDict.setMidAwardId(midAwardId);
			}
			if(StrUtils.isNotEmpty(midRate_add)){
				niLotteryPreferDict.setMidRate(Float.valueOf(midRate_add));
			}
			if(StrUtils.isNotEmpty(midBegin_add)){
				niLotteryPreferDict.setMidBegin(Integer.valueOf(midBegin_add));
			}
			if(StrUtils.isNotEmpty(midEnd_add)){
				niLotteryPreferDict.setMidEnd(Integer.valueOf(midEnd_add));
			}
			if(StrUtils.isNotEmpty(midAwardTotal_add)){
				niLotteryPreferDict.setMidAwardTotal(Integer.valueOf(midAwardTotal_add));
				niLotteryPreferDict.setMidAwardLeft(Integer.valueOf(midAwardTotal_add));
			}
			
			
			if(StrUtils.isNotEmpty(lowAwardId_add)){
				Integer lowAwardId = Integer.valueOf(lowAwardId_add);
				
				//根据奖品id查询奖励信息
				NiAward niAward = niAwardMapper.findById(lowAwardId);
				if(niAward!=null){
					niLotteryPreferDict.setLowAwardName(niAward.getAwardName());
				}else{
					return AjaxResult.errorCode("低区间奖品id不存在", "001");
				}
				
				niLotteryPreferDict.setLowAwardId(lowAwardId);
			}
			if(StrUtils.isNotEmpty(lowRate_add)){
				niLotteryPreferDict.setLowRate(Float.valueOf(lowRate_add));
			}
			if(StrUtils.isNotEmpty(lowBegin_add)){
				niLotteryPreferDict.setLowBegin(Integer.valueOf(lowBegin_add));
			}
			if(StrUtils.isNotEmpty(lowEnd_add)){
				niLotteryPreferDict.setLowEnd(Integer.valueOf(lowEnd_add));
			}
			if(StrUtils.isNotEmpty(lowAwardTotal_add)){
				niLotteryPreferDict.setLowAwardTotal(Integer.valueOf(lowAwardTotal_add));
				niLotteryPreferDict.setLowAwardLeft(Integer.valueOf(lowAwardTotal_add));  //初始化库存数
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return AjaxResult.errorResult("数据格式录入有误");
		}
		
		int insert = 0;
		try {
			insert = niLotteryPreferDictMapper.insert(niLotteryPreferDict);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("新增失败");
		}
		
		if(insert>0){
			return AjaxResult.successResult("新增成功");
		}else{
			return AjaxResult.errorResult("新增失败");
		}
		
	}

	//删除倾向抽奖规则
	public AjaxResult lotteryPreferPageDelte(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String checkLotteryId_arr = request.getParameter("checkLotteryId_arr");
		
		List<Integer> lotteryIdList = new ArrayList<Integer>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		if(StrUtils.isNotEmpty(checkLotteryId_arr)){
			String[] id_arr = checkLotteryId_arr.split("\\|");
			for(String lotteryId_Str : id_arr){
				if(StrUtils.isNotEmpty(lotteryId_Str)){
					Integer lotteryId = Integer.valueOf(lotteryId_Str);
					lotteryIdList.add(lotteryId);
				}
			}
		}
		retMap.put("lotteryIdList", lotteryIdList);
		
		try {
			niLotteryPreferDictMapper.deleteByIdList(retMap);
			return AjaxResult.successResult("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("操作失败");
		}
		
	}

	//根据id查询
	public AjaxResult fineOneById(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		AjaxResult ajaxResult = new AjaxResult();
		
		String evaluateId_Str = request.getParameter("evaluateId");
		
		if(StrUtils.isNotEmpty(evaluateId_Str)){
			Integer evaluateId = Integer.valueOf(evaluateId_Str);
			NiLotteryPreferDict niLotteryPreferDict = niLotteryPreferDictMapper.findOneById(evaluateId);
			if(niLotteryPreferDict!=null){
				ajaxResult.put("niLotteryPreferDict", niLotteryPreferDict);
			}
			
		}
		
		return ajaxResult;
	}

	//保存编辑
	public AjaxResult lotteryPreferEditSave(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String evaluateId_edit = request.getParameter("evaluateId_edit");
		String ruleName_add = request.getParameter("ruleName_add");
		
		String histAwardId_add = request.getParameter("histAwardId_add");
		String highRate_add = request.getParameter("highRate_add");
		String highBegin_add = request.getParameter("highBegin_add");
		String highEnd_add = request.getParameter("highEnd_add");
		String highAwardTotal_edit = request.getParameter("highAwardTotal_edit"); //奖品总数
		
		String midAwardId_add = request.getParameter("midAwardId_add");
		String midRate_add = request.getParameter("midRate_add");
		String midBegin_add = request.getParameter("midBegin_add");
		String midEnd_add = request.getParameter("midEnd_add");
		String midAwardTotal_edit = request.getParameter("midAwardTotal_edit"); //奖品总数
		
		String lowAwardId_add = request.getParameter("lowAwardId_add");
		String lowRate_add = request.getParameter("lowRate_add");
		String lowBegin_add = request.getParameter("lowBegin_add");
		String lowEnd_add = request.getParameter("lowEnd_add");
		String lowAwardTotal_edit = request.getParameter("lowAwardTotal_edit"); //奖品总数
		
		String comment_edit = request.getParameter("comment_edit");
		
		if(StrUtils.isNotEmpty(evaluateId_edit)){
			Integer evaluateId = Integer.valueOf(evaluateId_edit);
			
			NiLotteryPreferDict niLotteryPreferDict = new NiLotteryPreferDict();
			
			niLotteryPreferDict.setEvaluateId(evaluateId);
			niLotteryPreferDict.setRuleName(ruleName_add);
			niLotteryPreferDict.setComment(comment_edit);
			
			try {
				if(StrUtils.isNotEmpty(histAwardId_add)){
					
					//高区间奖品id
					Integer histAwardId = Integer.valueOf(histAwardId_add);
					//根据奖品id查询奖励信息
					NiAward niAward = niAwardMapper.findById(histAwardId);
					if(niAward!=null){
						niLotteryPreferDict.setHighAwardName(niAward.getAwardName());
					}else{
						return AjaxResult.errorCode("高区间奖品id不存在", "001");
					}
					
					niLotteryPreferDict.setHighAwardId(histAwardId);
				}
				if(StrUtils.isNotEmpty(highRate_add)){
					niLotteryPreferDict.setHighRate(Float.valueOf(highRate_add));
				}
				if(StrUtils.isNotEmpty(highBegin_add)){
					niLotteryPreferDict.setHighBegin(Integer.valueOf(highBegin_add));
				}
				if(StrUtils.isNotEmpty(highEnd_add)){
					niLotteryPreferDict.setHighEnd(Integer.valueOf(highEnd_add));
				}
				if(StrUtils.isNotEmpty(highAwardTotal_edit)){
					niLotteryPreferDict.setHighAwardTotal(Integer.valueOf(highAwardTotal_edit));
				}
				
				if(StrUtils.isNotEmpty(midAwardId_add)){
					
					Integer midAwardId = Integer.valueOf(midAwardId_add);
					
					//根据奖品id查询奖励信息
					NiAward niAward = niAwardMapper.findById(midAwardId);
					if(niAward!=null){
						niLotteryPreferDict.setMidAwardName(niAward.getAwardName());
					}else{
						return AjaxResult.errorCode("中区间奖品id不存在", "001");
					}
					
					niLotteryPreferDict.setMidAwardId(midAwardId);
				}
				if(StrUtils.isNotEmpty(midRate_add)){
					niLotteryPreferDict.setMidRate(Float.valueOf(midRate_add));
				}
				if(StrUtils.isNotEmpty(midBegin_add)){
					niLotteryPreferDict.setMidBegin(Integer.valueOf(midBegin_add));
				}
				if(StrUtils.isNotEmpty(midEnd_add)){
					niLotteryPreferDict.setMidEnd(Integer.valueOf(midEnd_add));
				}
				if(StrUtils.isNotEmpty(midAwardTotal_edit)){
					niLotteryPreferDict.setMidAwardTotal(Integer.valueOf(midAwardTotal_edit));
				}
				
				if(StrUtils.isNotEmpty(lowAwardId_add)){
					
					Integer lowAwardId = Integer.valueOf(lowAwardId_add);
					
					//根据奖品id查询奖励信息
					NiAward niAward = niAwardMapper.findById(lowAwardId);
					if(niAward!=null){
						niLotteryPreferDict.setLowAwardName(niAward.getAwardName());
					}else{
						return AjaxResult.errorCode("低区间奖品id不存在", "001");
					}
					
					niLotteryPreferDict.setLowAwardId(lowAwardId);
				}
				if(StrUtils.isNotEmpty(lowRate_add)){
					niLotteryPreferDict.setLowRate(Float.valueOf(lowRate_add));
				}
				if(StrUtils.isNotEmpty(lowBegin_add)){
					niLotteryPreferDict.setLowBegin(Integer.valueOf(lowBegin_add));
				}
				if(StrUtils.isNotEmpty(lowEnd_add)){
					niLotteryPreferDict.setLowEnd(Integer.valueOf(lowEnd_add));
				}
				if(StrUtils.isNotEmpty(lowAwardTotal_edit)){
					niLotteryPreferDict.setLowAwardTotal(Integer.valueOf(lowAwardTotal_edit));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return AjaxResult.errorResult("数据格式录入有误");
			}
			
			try {
				niLotteryPreferDictMapper.updateByPrimaryKeySelective(niLotteryPreferDict);
				return AjaxResult.successResult("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("出现异常");
			}
			
		}else{
			return AjaxResult.errorResult("出现错误");
		}
		
	}

	/**
	 * 跳转到添加倾向抽奖页面
	 */
	public void jumpTo_lotteryPreferAddPage(HttpServletRequest request,
			Model model) {
		//查询所有奖品信息
		List<NiAward> niAwardList = niAwardMapper.findAll();
		
		model.addAttribute("niAwardList", niAwardList);
		
	}
	
	
	
}
