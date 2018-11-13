package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.award.AwardPool;


/**@Description  
 * @author 范晓文
 * @time 2016年7月15日 上午14:35:52
 */
public interface AwardPoolService {
	//查询总奖池信息
		public List<AwardPool> selectList();
		//添加奖池信息
		public void addAwardPool(AwardPool awardpool);
		
		//按条件查询奖池信息
	    public List<AwardPool> searchAwardByCondition(HashMap<String, Object> hashMap);
 
	 	//更新奖池状态
	    public void updatestatusAwardPool(int status,String str);
		
	    //根据id批量删除
		public void deleteAwardPool(String awardPoolId);
		
		//查询单个奖池信息
		public AwardPool getAwardPoolInfo(int awardPoolId);
		
		//编辑奖池
		public void editAwardPool(AwardPool awardPool);
	  


}
