package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.Lottery;
import com.newIns.model.award.Award;

/**@Description  抽奖管理对应Service
 * @author MaNia_chAng
 * @time 2016年7月14日 下午3:42:45
 */
public interface LotteryService {
	
	//分页查询
	List<Lottery> selectList(HashMap<String, Object> hashMap);
	
	//添加抽奖组合
	int addLottery(Lottery lottery);
	
	//批量删除抽奖组合
	int deleteLotteryByIds(HashMap<String, Object> retMap);
	
	//查询奖品信息
	Award searchAwardById(int awardId);
	
	//查询编辑信息
	Lottery searchEditInfo(int lotteryId);
	
	//保存编辑信息
	int updateLottery(Lottery lottery);

}
