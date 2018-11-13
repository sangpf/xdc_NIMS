/**
 * 
 */
package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiCarousel;
import com.newIns.tools.AjaxResult;

/**
 * @Description
 * @author Guan
 * @time 2016年7月4日 下午4:03:11
 */

public interface NiCarouselService {
	List<NiCarousel> loadCarouselList();

	int postCarouselByids(HashMap<String, Object> retMap);

	int revokeCarouselByids(HashMap<String, Object> retMap);

	List<NiAdInfo> loadAdInfoList();

	void replaceCarousel(int carousel_Id, String adTitle, String adImg);
	
	AjaxResult moveUpCarousel(int carouselId,int showOrder,int lastCarouselId,int lastShowOrder);
	
	AjaxResult moveDownCarousel(int carouselId,int showOrder,int nextCarouselId,int nextShowOrder);

	void jumpEditCarorse(HttpServletRequest request, Model model);

	AjaxResult save_edit_Carorse(HttpServletRequest request, Model model);
}
