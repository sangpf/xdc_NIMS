/**
 * 
 */
package com.newIns.service.imp;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.NiAdInfoMapper;
import com.newIns.dao.NiCarouselMapper;
import com.newIns.dao.page.ChannelDao;
import com.newIns.model.NiAdInfo;
import com.newIns.model.NiCarousel;
import com.newIns.model.page.Channel;
import com.newIns.service.NiCarouselService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.StrUtils;

/**@Description  
 * @author Guan
 * @time 2016年7月4日 下午4:09:40
 */
@Service
public class NiCarouselServiceImp implements NiCarouselService {
	@Resource
	private NiCarouselMapper niCarouselMapper ;
	@Autowired
	private NiAdInfoMapper niAdInfoMapper;
	@Autowired
	private ChannelDao channelDao;
	
	public List<NiCarousel> loadCarouselList(){
		return niCarouselMapper.loadCarouselList();
	}
	/**
	 */
	public int postCarouselByids(HashMap<String, Object> retMap) {
		int updateStateByids = niCarouselMapper.postCarouselByids(retMap);
		return updateStateByids;
	}
	
	/**
	 * @Description: TODO 批量撤销轮播图广告
	 */
	public int revokeCarouselByids(HashMap<String, Object> retMap) {
		int updateStateByids = niCarouselMapper.revokeCarouselByids(retMap);
		return updateStateByids;
	}
	/**
	 * 加载广告池所有广告列表
	 */
	public List<NiAdInfo> loadAdInfoList(){
		return niCarouselMapper.loadAdInfoList();
	}
	
	public void replaceCarousel(int carousel_Id, String adTitle, String adImg){
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());// 获取当前系统时间
		hashMap.put("carouselId",carousel_Id );
		hashMap.put("adTitle", adTitle);
		hashMap.put("adImg", adImg);
		hashMap.put("uTime", uTime);
		
		niCarouselMapper.replaceCarousel(hashMap);
		
	}
	
	public AjaxResult moveUpCarousel(int carouselId,int showOrder,int lastCarouselId,int lastShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("carouselId", carouselId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("lastCarouselId", lastCarouselId);
		hashMap.put("lastShowOrder", lastShowOrder);
		hashMap.put("uTime", uTime);
		niCarouselMapper.moveUpCarousel(hashMap);
		return AjaxResult.successResult("上移成功");
		
	}
	
	public AjaxResult moveDownCarousel(int carouselId,int showOrder,int nextCarouselId,int nextShowOrder){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Timestamp uTime = new Timestamp(System.currentTimeMillis());
		hashMap.put("carouselId", carouselId);
		hashMap.put("showOrder", showOrder);
		hashMap.put("nextCarouselId", nextCarouselId);
		hashMap.put("nextShowOrder", nextShowOrder);
		hashMap.put("uTime", uTime);
		niCarouselMapper.moveDownCarousel(hashMap);
		return AjaxResult.successResult("下移成功");
		
	}

	// 跳转到编辑轮播图页面
	public void jumpEditCarorse(HttpServletRequest request, Model model) {
		// 获取 主键id
		String carouselId_str = request.getParameter("carouselId");
		
		Integer carouselId = null;
		if(StrUtils.isNotEmpty(carouselId_str)){
			carouselId = Integer.valueOf(carouselId_str);
		}
		
		// 查询当前轮播图所有信息, 存入域对象中
		NiCarousel findById = niCarouselMapper.findById(carouselId);
		model.addAttribute("niCarousel", findById);
		
		// 查询所有的广告信息 , 在页面下拉展示
		List<NiAdInfo> selectList = niAdInfoMapper.selectList();
		model.addAttribute("adInfoList", selectList);
		
		// 查询所有渠道
		List<Channel> listChannel = channelDao.listChannel();
		model.addAttribute("listChannel", listChannel);
		
	}

	//保存轮播图
	public AjaxResult save_edit_Carorse(HttpServletRequest request, Model model) {
		
		String carouselId_str = request.getParameter("carouselId");
		String adId_str = request.getParameter("adId");
		String channelId_str = request.getParameter("channelId");
		
		Integer carouselId = StrUtils.changeToInt(carouselId_str);
		Integer adId = StrUtils.changeToInt(adId_str);
		Integer channelId = StrUtils.changeToInt(channelId_str);
		
		// 可能是编辑,或者新增
		NiCarousel niCarousel = new NiCarousel();
		niCarousel.setCarouselId(carouselId);
		niCarousel.setChannelId(channelId);
		niCarousel.setAdId(adId);
		
		// 从请求中获取轮播图id 
		if(carouselId == null){
			// 如果id为空, 新增
			
			try {
				niCarouselMapper.insert(niCarousel);
				return AjaxResult.successResult("新增成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("新增失败");
			}
			
		}else{
			// 如果id不为空, 编辑
			
			try {
				niCarouselMapper.updateByEntity(niCarousel);
				return AjaxResult.successResult("编辑成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("编辑失败");
			}
			
		}
		
	}
}
