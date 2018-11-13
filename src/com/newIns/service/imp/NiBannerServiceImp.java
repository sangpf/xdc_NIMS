/**
 * 
 */
package com.newIns.service.imp;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiBannerMapper;
import com.newIns.model.NiAdInfo;
import com.newIns.model.NiBanner;
import com.newIns.service.NiBannerService;

/**@Description  
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiBannerServiceImp implements NiBannerService {
	@Resource
	private NiBannerMapper niBannerMapper ;
	public List<NiBanner> loadBannerList(){
		return niBannerMapper.loadBannerList();
	}
	/**
	 * @Title:   postBannerByids
	 * @Author: Guan
	 * @Description: TODO
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int postBannerByids(HashMap<String, Object> retMap) {
		int updateStateByids = niBannerMapper.postBannerByids(retMap);
		return updateStateByids;
	}
	
	/**
	 * @Title: revokeBannerByids  
	 * @Author: Guan
	 * @Description: TODO
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int revokeBannerByids(HashMap<String, Object> retMap) {
		int updateStateByids = niBannerMapper.revokeBannerByids(retMap);
		return updateStateByids;
	}
	
	/**
	 * 加载广告池所有广告列表
	 */
	public List<NiAdInfo> loadAdInfoList(){
		return niBannerMapper.loadAdInfoList();
	}
	
	public void replaceBanner(String adPosDes, String adTitle, String adImg){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
		hashMap.put("adPosDes",adPosDes );
		hashMap.put("adTitle", adTitle);
		hashMap.put("adImg", adImg);
		hashMap.put("uTime", uTime);
		niBannerMapper.replaceBanner(hashMap);
	}
}
