/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiBanner;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiBannerService {
	List<NiBanner> loadBannerList();

	int postBannerByids(HashMap<String, Object> retMap);

	int revokeBannerByids(HashMap<String, Object> retMap);

	List<NiAdInfo> loadAdInfoList();

	void replaceBanner(String adPosDes, String adTitle, String adImg);
}
