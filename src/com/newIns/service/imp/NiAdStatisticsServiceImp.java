package com.newIns.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.newIns.dao.NiAdStatisticsMapper;
import com.newIns.model.NiAdStatistics;
import com.newIns.service.NiAdStatisticsService;


/**
 * @Description 广告统计对应的Service实现类
 * @author lj
 * @time 2016年7月27日 下午5:52:18
 */
@Service
public class NiAdStatisticsServiceImp implements NiAdStatisticsService {
	
	@Resource
	private NiAdStatisticsMapper niAdStatisticsMapper;
	
	/**
	 * @Description 根据搜索条件查询广告列表
	 * @author lj
	 * @param hashMap
	 * @return List<NiAdStatistics>
	 * @time 2016年7月27日 下午5:54:30
	 */
	public List<NiAdStatistics> selectList(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		List<NiAdStatistics> niAdStatisticsList=niAdStatisticsMapper.selectList(hashMap);
		return niAdStatisticsList;
	}

}
