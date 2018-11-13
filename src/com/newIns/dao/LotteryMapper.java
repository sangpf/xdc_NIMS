package com.newIns.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.newIns.model.Lottery;
import com.newIns.model.award.Award;

/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月14日 下午3:48:10
 */
public interface LotteryMapper {

	Lottery findLotteryByDelId(Integer id);
	
	//分页查询
	List<Lottery> selectList(HashMap<String,Object> hashMap);
	
	//添加抽奖组合
	int addLottery(Lottery lottery);
	
	//批量删除抽奖组合
	int deleteLotteryByIds(HashMap<String, Object> retMap);
	
	//查询奖品信息
	Award searchAwardById(int awardId);
	
	//查询抽奖组合编辑信息
	Lottery searchEditInfo(int lotteryId);
	
	//保存编辑信息
	int updateLottery(Lottery lottery);
	
	//动态创建表保存中奖名单
	int generateLottery(@Param("retMap") Map<String,Object> retMap);
	void insert_generateLottery(@Param("retMap") Map<String, Object> retMap);
	
	//删除自动生成的抽奖表
	void deleteTab(@Param("tabMap") Map<String, Object> tabMap);
	
}
