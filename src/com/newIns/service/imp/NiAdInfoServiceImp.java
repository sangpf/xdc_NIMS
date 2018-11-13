package com.newIns.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.NiAdInfoMapper;
import com.newIns.model.NiAdInfo;
import com.newIns.service.NiAdInfoService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
@Service
public class NiAdInfoServiceImp implements NiAdInfoService {
	private static Logger log = Logger.getLogger(NiAdInfoServiceImp.class);
	@Resource
	private NiAdInfoMapper niAdInfoMapper;
	@Override
	public NiAdInfo selectByPrimaryKey(Integer adid) {
		
		NiAdInfo niAdInfo=niAdInfoMapper.selectByPrimaryKey(adid);
		return niAdInfo;
	}
	 /**
     * 批量删除
     * @param report
     * @return
     */
    @Override
    public int deleteAdInfoByIds(HashMap<String, Object> retMap) {
        int delectByPrimaryKey = niAdInfoMapper.deleteAdInfoByIds(retMap);
        return delectByPrimaryKey;
    }

	/**
	 * @Title:   changeAdInfoStatus
	 * @Author: Guan
	 * @Description: TODO 更改广告状态，启用/撤销
	 * @param retMap
	 * @return int
	 * @Time 2016年7月6日 下午1:55:40
	 */
	public int changeAdInfoStatus(HashMap<String, Object> retMap) {
		int changeStateByids = niAdInfoMapper.changeAdInfoStatus(retMap);
		return changeStateByids;
	}
	
	
	/**
	 * 加载广告池所有广告列表
	 */
	public List<NiAdInfo> loadAdInfoList(){
		return niAdInfoMapper.selectList();
	}
	/**
	 * 加载广告池所有广告列表（筛选）
	 */
	public List<NiAdInfo> loadAdInfoList(HashMap<String, Object> retMap){
		return niAdInfoMapper.searchList(retMap);
	}
	
    /**
     * 添加新广告
     */
    @Override
    public int addAdInfo(NiAdInfo adInfo) {
        int addAdInfo = niAdInfoMapper.insert(adInfo);
        return addAdInfo;
    }
    
    /**
     * 编辑广告
     */
    @Override
    public int editAdInfo(NiAdInfo adInfo) {
        int addAdInfo = niAdInfoMapper.updateByPrimaryKeySelective(adInfo);
        return addAdInfo;
    }
    
    /**
     * 上传广告图图片
     */
	public AjaxResult uploadPicture(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile file) throws Exception{
		AjaxResult ajaxResult = new AjaxResult();
		
		//拼接保存路径
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/ad/adpool");
		
		log.info(uploadFile);
		String realPath = (String) uploadFile.get("realPath");
		log.info("=========================>>realPath:"+realPath);
		String url = (String) uploadFile.get("url");
		String contextPath = request.getContextPath();
		ajaxResult.put("url", contextPath+url);
		log.info("===================>> url 路径为 :" + contextPath+url);
		
		//获取文件的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		log.info("===================>> 文件的存储路径 :" + jdbcUrl);
		ajaxResult.put("jdbcUrl", jdbcUrl);
		
		return ajaxResult;
	}

}
