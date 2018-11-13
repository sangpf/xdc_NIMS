package com.newIns.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.newIns.model.PostalInfo;
import com.newIns.model.award.AwardPay;
import com.newIns.model.award.AwardPayAndPostal;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午3:07:28
 */
public interface AwardPayService {
	//分页查询奖品发放列表
	List<AwardPay> selectList(HashMap<String,Object> hashMap);
	
	//查看奖品发放信息
	PostalInfo getAwardPostalInfo(HashMap<String,Object> hashMap);
	
	//发放奖品
	int payAward(HashMap<String,Object> hashMap);
	
	/*
	 * @Author:lijz
	 * @Description:拼接奖品发放信息和邮寄信息并导出Excel
	 */
	AwardPayAndPostal getAwardPayAndPostalExcel(AwardPay awardPay, PostalInfo postal);
	
	/*
	 * 
	 * @Author:lijz
	 * @Description:通过奖品发放信息的变量名获得对应Excel的列名
	 */
	LinkedHashMap<String, String> getExcelColumnName();

}
