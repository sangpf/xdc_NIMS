package com.newIns.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.award.AwardPoolMapper;
import com.newIns.model.award.AwardPool;
import com.newIns.service.AwardPoolService;
/**
 * 
 * 
 * @Description: 奖金池的服务
 * @author 范晓文
 * @date 2016年7月15日 下午2:52:35
 */

@Service(value="awardPoolService")
public class AwardPoolServiceImp implements AwardPoolService{
	@Resource
	private AwardPoolMapper awardPoolMapper;
	
	@Override
	public List<AwardPool> selectList() {
		List<AwardPool> listret=new ArrayList<AwardPool>();
		listret=awardPoolMapper.selectList();
		// TODO Auto-generated method stub
		return listret;
	}

	@Override
	public void addAwardPool(AwardPool awardpool) {
		// TODO Auto-generated method stub
		awardPoolMapper.addAwardPool(awardpool);
		
	}

	@Override
	public List<AwardPool> searchAwardByCondition(
			HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		
		return awardPoolMapper.searchAwardByCondition(hashMap);
	}

	@Override
	public void updatestatusAwardPool(int status,String str) {
		String[] awardPoolId = str.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> awardPoolIds = new ArrayList<Integer>();
		for(int i=0;i<awardPoolId.length;i++){
			awardPoolIds.add(Integer.valueOf(awardPoolId[i]));
		}
		hashMap.put("awardPoolIds", awardPoolIds);
		hashMap.put("status", status);
		awardPoolMapper.updatestatusAwardPool(hashMap);
	}
	
	@Override
	public void deleteAwardPool(String str) {
		String[] awardPoolId = str.split("!");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> awardPoolIds = new ArrayList<Integer>();
		for(int i=0;i<awardPoolId.length;i++){
			awardPoolIds.add(Integer.valueOf(awardPoolId[i]));
		}
		hashMap.put("awardPoolIds", awardPoolIds);
		awardPoolMapper.deleteAwardPool(hashMap);
	}
	
	@Override
	public AwardPool getAwardPoolInfo(int awardPoolId){
		return awardPoolMapper.getAwardPoolInfo(awardPoolId);
		
	}
	
	@Override
	public void editAwardPool(AwardPool awardpool) {
		// TODO Auto-generated method stub
		awardPoolMapper.editAwardPool(awardpool);
		
	}
	

	

}
