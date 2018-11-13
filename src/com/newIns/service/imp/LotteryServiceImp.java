package com.newIns.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.LotteryMapper;
import com.newIns.model.Lottery;
import com.newIns.model.award.Award;
import com.newIns.service.LotteryService;




/**@Description  
 * @author MaNia_chAng
 * @time 2016年7月14日 下午3:45:01
 */
@Service
public class LotteryServiceImp implements LotteryService{
	@Resource
	private LotteryMapper lotteryMapper;
	
	/**
	 * 分页查询
	 */
	public List<Lottery> selectList(HashMap<String,Object> hashMap){
		List<Lottery> lotteryList = lotteryMapper.selectList(hashMap);
		Lottery lotteryItem = null;
		for(int i=0;i<lotteryList.size();i++){
			lotteryItem=lotteryList.get(i);
			String award1RateStr =lotteryItem.getAward1Rate();
			String award2RateStr =lotteryItem.getAward2Rate();
			String award3RateStr =lotteryItem.getAward3Rate();
			if(Double.valueOf(award1RateStr)>=1){				
				String award1Rate = award1RateStr.substring(0,award1RateStr.indexOf("."));		
				lotteryItem.setAward1Rate(Integer.parseInt(award1Rate)+"个");
			}
			else{
				double award1Rate = Double.valueOf(award1RateStr)*100;				
				lotteryItem.setAward1Rate(award1Rate+"%");
			}
			if(award2RateStr!=null&&Double.valueOf(award2RateStr)>=1){
				String award2Rate = award2RateStr.substring(0,award2RateStr.indexOf("."));	
				lotteryItem.setAward2Rate(award2Rate+"个");
			}
			else if(award2RateStr!=null){
				double award2Rate = Double.valueOf(award2RateStr)*100;
				lotteryItem.setAward2Rate(award2Rate+"%");
			}
			if(award3RateStr!=null&&Double.valueOf(award3RateStr)>=1){
				String award3Rate = award3RateStr.substring(0,award3RateStr.indexOf("."));	
				lotteryItem.setAward3Rate(award3Rate+"个");
			}
			else if(award3RateStr!=null){
				double award3Rate = Double.valueOf(award3RateStr)*100;
				lotteryItem.setAward3Rate(award3Rate+"%");
			}
		}
		return lotteryList;
	}
	
	/**
	 * 添加抽奖组合
	 */
	public int addLottery(Lottery lottery){
		int addAward = lotteryMapper.addLottery(lottery);
		return addAward;
	}
	
	/**
	 * 批量删除抽奖组合
	 */
	public int deleteLotteryByIds(HashMap<String, Object> retMap){
		int deleteByPrimaryKeySelective = lotteryMapper.deleteLotteryByIds(retMap);
		return deleteByPrimaryKeySelective;
	}
	
	/**
	 * 查询奖品信息
	 */
	public Award searchAwardById(int awardId){
		Award award = lotteryMapper.searchAwardById(awardId);
		return award;
	}
	
	/**
	 * 查询编辑信息
	 */
	public Lottery searchEditInfo(int lotteryId){
		Lottery lottery = lotteryMapper.searchEditInfo(lotteryId);
		return lottery;
	}
	
	/**
	 * 保存编辑信息
	 */
	public int updateLottery(Lottery lottery){
		int updateLottery =lotteryMapper.updateLottery(lottery);
		return updateLottery;
	}

}
