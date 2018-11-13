/**
 * 
 */
package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiCarousel;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:11:07
 */

public interface NiCarouselMapper {
	List<NiCarousel> loadCarouselList();

	int postCarouselByids(HashMap<String, Object> retMap);

	int revokeCarouselByids(HashMap<String, Object> retMap);

	List<NiAdInfo> loadAdInfoList();
	
	void replaceCarousel(HashMap<String, Object> hashmap);
	
	void moveUpCarousel(HashMap<String, Object> retMap);
	
	void moveDownCarousel(HashMap<String, Object> retMap);

	NiCarousel findById(Integer carouselId);

	void insert(NiCarousel niCarousel);

	void updateByEntity(NiCarousel niCarousel);
}
