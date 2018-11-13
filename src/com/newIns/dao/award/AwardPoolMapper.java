package com.newIns.dao.award;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.award.AwardPool;

/**@Description  奖品管理对应dao
 * @author 范晓文
 * @time 2016年7月15日 上午10:48:54
 */

public interface AwardPoolMapper {
	
	//查询总奖池信息
	public List<AwardPool> selectList();
	//添加奖池信息
	public void addAwardPool(AwardPool awardpool);
	
	//按条件查询奖池信息
	public List<AwardPool> searchAwardByCondition(HashMap<String,Object> hashMap);
	
	//更新奖池信息
	 public void updatestatusAwardPool(HashMap<String,Object> hashMap);
	
	//根据id批量删除
	public void deleteAwardPool(HashMap<String,Object> hashMap);
	
	//查询奖池信息
	public AwardPool getAwardPoolInfo(int awardPoolId);
	
	//编辑奖池信息
	public void editAwardPool(AwardPool awardPool);
	

	

	

}
