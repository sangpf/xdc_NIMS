package com.newIns.dao.award;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.award.Award;
import com.newIns.model.award.NiAward;

/**  奖品管理对应dao
 */
public interface AwardMapper {
	
	List<NiAward> findAll();
	
	//分页查询奖品信息
	List<Award> selectList(HashMap<String,Object> hashMap);
	
	//删除奖品
	int deleteAwardByIds(HashMap<String,Object> hashMap);
	
	//查询奖品信息
	Award searchAwardById(int awardPoolId);
	
	//添加奖品
	int addAward(Award award);
	
	//查询编辑信息
	Award searchEditInfo(int awardId);
	
	//保存编辑信息
	int updateAward(Award award);
	
}
