package com.newIns.service.imp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.award.AwardPayMapper;
import com.newIns.model.PostalInfo;
import com.newIns.model.award.AwardPay;
import com.newIns.model.award.AwardPayAndPostal;
import com.newIns.service.AwardPayService;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午3:12:11
 */
@Service
public class AwardPayServiceImp implements AwardPayService{
	@Resource
	private AwardPayMapper awardPayMapper;
	

	/**
	 * 分页查询奖品发放信息
	 */
	public List<AwardPay> selectList(HashMap<String,Object> hashMap){
		List<AwardPay> awardPayList = awardPayMapper.selectList(hashMap);
		return awardPayList;
		
	}
	
	/**
	 * 查看发放信息
	 */
	public PostalInfo getAwardPostalInfo(HashMap<String,Object> hashMap){
		PostalInfo postalInfo = awardPayMapper.getAwardPostalInfo(hashMap);
		return postalInfo;
	}
	
	/**
	 * 发放奖品
	 */
	public int payAward(HashMap<String,Object> hashMap){
		int updatePayStatus = awardPayMapper.payAward(hashMap);
		return updatePayStatus;
	}
	/*
	 * 拼接得到Excel导出信息
	 */
	public AwardPayAndPostal getAwardPayAndPostalExcel(AwardPay awardPay, PostalInfo postal){
		AwardPayAndPostal awardPayAndPostal = new AwardPayAndPostal();
		awardPayAndPostal.setUserId(awardPay.getUserId());
		awardPayAndPostal.setQnName(awardPay.getQnName());
		awardPayAndPostal.setAwardName(awardPay.getAwardName());
		awardPayAndPostal.setAwardId(awardPay.getAwardId());
		awardPayAndPostal.setAwardGetTime(awardPay.getAwardGetTime());
		switch(awardPay.getAwardGetStatus()){
			case 1: awardPayAndPostal.setAwardGetStatus("未领取");break;
			case 2: awardPayAndPostal.setAwardGetStatus("已领取");break;
			case 3: awardPayAndPostal.setAwardGetStatus("放弃");
		}
		switch(awardPay.getAwardPayStatus()){
			case 1: awardPayAndPostal.setAwardPayStatus("未发放");break;
			case 2: awardPayAndPostal.setAwardPayStatus("已发放");break;
		}
		if(postal!=null){
			awardPayAndPostal.setRecipientName(postal.getRecipientName());
			awardPayAndPostal.setRecipientPhone(postal.getRecipientPhone());
			awardPayAndPostal.setProvince(postal.getProvince());
			awardPayAndPostal.setMailAddress(postal.getMailAddress());
			awardPayAndPostal.setEmail(postal.getEmail());
		}
		
		return awardPayAndPostal;	
	}
	
	/*
	 * 获取Excel对应的列名
	 * 
	 */
	public LinkedHashMap<String, String> getExcelColumnName(){
		LinkedHashMap<String, String> fieldmap = new LinkedHashMap<String, String>();
		fieldmap.put("userId", "用户ID");
		fieldmap.put("qnName","问卷名称");
		fieldmap.put("awardName","奖品名称");
		fieldmap.put("awardId", "奖品ID");
		fieldmap.put("awardGetTime", "获奖时间");
		fieldmap.put("awardGetStatus", "领取状态");
		fieldmap.put("awardPayStatus","发放状态");
		fieldmap.put("recipientName","收件人姓名");
		fieldmap.put("recipientPhone","收件人电话");
		fieldmap.put("province","所在省份");
		fieldmap.put("mailAddress","邮寄地址");
		fieldmap.put("email","邮箱");
		return fieldmap;
	}
}
