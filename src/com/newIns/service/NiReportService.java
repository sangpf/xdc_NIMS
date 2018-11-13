package com.newIns.service;

import com.newIns.model.report.NiReport;
import com.newIns.tools.AjaxResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by lyr on 16/6/29.
 */
public interface NiReportService {
	// 分页查询
	List<NiReport> selectList(HashMap<String, Object> hashMap);

	// 添加报告
	int addReport(NiReport report);
	
	//批量更新状态
    
	int updateReportStatus(HashMap<String,Object> retMap);
	

	// 批量删除
	int deleteReportByIds(HashMap<String, Object> retMap);

	// 根据新添加的report信息获得reportId
	NiReport getReportIdByReportInfo(NiReport report);

	List<NiReport> selectAllListByReportClassId();

	NiReport selectByPrimaryKey(Integer reportid);

	/**
	 * @Title: upLoadReportListPic
	 * @Author: Guan
	 * @Description: 上传报告列表图片
	 * @param file
	 * @param reportId
	 *            void
	 * @Time 2016年8月10日 下午3:34:08
	 */
	Map<String, Object> uploadReportListPic(HttpServletRequest request,
			MultipartFile file, String reportId) throws Exception;

	/**
	 * @Title: updateImgUrl
	 * @Author: Guan
	 * @Description: 将reportImg写入表中
	 * @param reportId
	 * @param ImgJdbcUrl
	 *            void
	 * @Time 2016年8月10日 下午8:02:57
	 */
	void updateImgUrl(int reportId, String ImgJdbcUrl);

	/**
	 * @Title: uploadReportZip
	 * @Author: Guan
	 * @Description: TODO
	 * @param request
	 * @param file
	 * @param reportId
	 * @throws Exception
	 *             void
	 * @Time 2016年8月10日 下午8:19:28
	 */
	Map<String, Object> uploadReportZip(HttpServletRequest request, MultipartFile file,
			String reportId) throws Exception;
	/**
	 * @Title: updateReportUrl  
	 * @Author: Guan
	 * @Description: TODO上传zip包后将报告html路径存入表中
	 * @param reportId
	 * @param reportUrl void
	 * @Time 2016年8月12日 下午4:59:43
	 */
	void updateReportUrl(int reportId, String reportUrl);
	
	//编辑报告
	int editReport(NiReport report);

}
