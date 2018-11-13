package com.newIns.dao;

import java.util.List;
import java.util.Map;

import com.newIns.model.NiLotteryPreferDict;


/**
 * 倾向抽奖接口
 * @author 11409
 *
 */
public interface NiLotteryPreferDictMapper {
	
	//列表查询  全部
	List<NiLotteryPreferDict> findList();
	
	//添加
	int insert(NiLotteryPreferDict recode);

	//根据id集合删除
	void deleteByIdList(Map<String, Object> retMap);

	//根据id查询
	NiLotteryPreferDict findOneById(Integer evaluateId);

	//根据id修改
	void updateByPrimaryKeySelective(NiLotteryPreferDict niLotteryPreferDict);
	
	
	
}
