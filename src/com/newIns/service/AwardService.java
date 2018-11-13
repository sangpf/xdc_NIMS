package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.award.Award;


/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月12日 上午11:35:52
 */
public interface AwardService {
    //分页查询
	List<Award> selectList(HashMap<String, Object> hashMap);
    
	//批量删除
	int deleteAwardByIds(HashMap<String, Object> retMap);
	
	//查询奖品信息
	Award searchAwardById(int awardPoolId);
	
	//添加奖品
	int addAward(Award award);
	
	//查询编辑信息
	Award searchEditInfo(int awardId);
	
	//保存编辑信息
	int updateAward(Award award);
	  


}
