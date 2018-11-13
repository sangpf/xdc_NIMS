package com.newIns.dao.award;

import java.util.List;

import com.newIns.model.award.NiAward;

/**
 * 奖品接口
 * @author 11409
 *
 */
public interface NiAwardDao {
	
	//查询一个
	NiAward findById(Integer id);
	
	//查询所有奖品信息
	List<NiAward> findAll();
	
	
}
