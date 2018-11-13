package com.newIns.dao.award;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.PostalInfo;
import com.newIns.model.award.AwardPay;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月19日 下午3:18:17
 */
public interface AwardPayMapper {
	
	//分页查询
	List<AwardPay> selectList(HashMap<String,Object> hashMap);
	
	//查看发放信息
	PostalInfo getAwardPostalInfo(HashMap<String,Object> hashMap);
	
	//发放奖品
	int payAward(HashMap<String,Object> hashMap);

}
