package com.newIns.service.imp.redeemCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newIns.dao.redeemCode.RedeemCodeMapper;
import com.newIns.model.redeemCode.RedeemCode;
import com.newIns.service.redeemCode.RedeemCodeService;
import com.newIns.tools.RandomCodeUtil;

@Service
public class RedeemCodeServiceImp implements RedeemCodeService{

	@Autowired
	private RedeemCodeMapper redeemCodeMapper;
	//新增数据
	public void InitializationRedeemCode() {
		
		
		for(int i=0 ; i<10000; i++){
			// 获取随机优惠码
			String generateNumber_Str = RandomCodeUtil.generateNumber_Str(6);
			
			RedeemCode redeemCode = new RedeemCode();
			
			redeemCode.setAwardPoolId(41);
			redeemCode.setUserId(00);
			redeemCode.setProvider("口粮");
			redeemCode.setPhoneNum(null);
			redeemCode.setGrantStatus(0);
			redeemCode.setRedeemCode(generateNumber_Str);
			
			redeemCodeMapper.insert(redeemCode);
			
		}
		
		
		
	}
	
}
