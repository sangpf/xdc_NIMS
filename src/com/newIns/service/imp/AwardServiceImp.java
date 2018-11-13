package com.newIns.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.award.AwardMapper;
import com.newIns.model.award.Award;
import com.newIns.service.AwardService;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月12日 上午11:38:29
 */
@Service
public class AwardServiceImp implements AwardService{
	@Resource
	private AwardMapper awardMapper;

	/**
	 * 分页查询
	 */
	public List<Award> selectList(HashMap<String,Object> hashMap){
		List<Award> awardList = awardMapper.selectList(hashMap);
		return awardList;
		
	}
	
	/**
	 * 批量删除奖品
	 */
	public int deleteAwardByIds(HashMap<String,Object> hashMap){
		int delectByPrimaryKey = awardMapper.deleteAwardByIds(hashMap);
		return delectByPrimaryKey;
	}
	
	/**
	 * 查询奖品信息
	 */
	public Award searchAwardById(int awardPoolId){
		Award award = awardMapper.searchAwardById(awardPoolId);
		return award;
	}
	
	/**
	 * 添加奖品
	 */
	public int addAward(Award award){
		int addAward = awardMapper.addAward(award);
		return addAward;
	}
	
	/**
	 * 查询编辑信息
	 */
	public Award searchEditInfo(int awardId){
		Award award = awardMapper.searchEditInfo(awardId);
		return award;
	}
	
	/**
	 * 保存编辑信息
	 */
	public int updateAward(Award award){
		int updateAward = awardMapper.updateAward(award);
		return updateAward;
	}


}
