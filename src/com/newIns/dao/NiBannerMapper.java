/**
 * 
 */
package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiBanner;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiBannerMapper {
	List<NiBanner> loadBannerList();

	int postBannerByids(HashMap<String, Object> retMap);

	int revokeBannerByids(HashMap<String, Object> retMap);

	List<NiAdInfo> loadAdInfoList();
	
	void replaceBanner(HashMap<String, Object> hashmap);
}
