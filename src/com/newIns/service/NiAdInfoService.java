package com.newIns.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.model.NiAdInfo;
import com.newIns.tools.AjaxResult;

public interface NiAdInfoService {
	public NiAdInfo selectByPrimaryKey(Integer adid);

	/**
	 * @Title: loadAdInfoList
	 * @Author: Guan
	 * @Description: TODO 加载广告池列表
	 * @return List<NiAdInfo>
	 * @Time 2016年9月1日 上午11:59:54
	 */
	List<NiAdInfo> loadAdInfoList();

	// 筛选
	List<NiAdInfo> loadAdInfoList(HashMap<String, Object> retMap);

	// 批量删除
	int deleteAdInfoByIds(HashMap<String, Object> retMap);

	// 更改状态
	int changeAdInfoStatus(HashMap<String, Object> retMap);

	// 添加广告
	int addAdInfo(NiAdInfo adInfo);
	
	//编辑广告
	int editAdInfo(NiAdInfo adInfo);

	// 上传图片
	AjaxResult uploadPicture(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("uploadImgmes") MultipartFile file) throws Exception;
}
