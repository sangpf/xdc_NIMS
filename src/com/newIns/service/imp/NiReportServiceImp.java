package com.newIns.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.NiReportMapper;
import com.newIns.model.report.NiReport;
import com.newIns.service.NiReportService;
import com.newIns.tools.FileUtil;

//import java.io.File;

/**
 * Created by lyr on 16/6/29.
 */
@Service
public class NiReportServiceImp implements NiReportService {
	@Resource
	private NiReportMapper niReportMapper;
	private static Logger log = Logger.getLogger(NiReportServiceImp.class);

	/**
	 * 分页查询
	 */
	public List<NiReport> selectList(HashMap<String, Object> hashMap) {
		List<NiReport> selectList = niReportMapper.selectList(hashMap);
		return selectList;
	}

	/**
	 * 更新报告状态
	 */
	public int updateReportStatus(HashMap<String, Object> retMap) {
		int updateByPrimaryKey = niReportMapper.updateReportStatus(retMap);
		return updateByPrimaryKey;
	}

	/**
	 * 批量删除
	 * 
	 * @param report
	 * @return
	 */
	@Override
	public int deleteReportByIds(HashMap<String, Object> retMap) {
		int delectByPrimaryKey = niReportMapper.deleteReportByIds(retMap);
		return delectByPrimaryKey;
	}

	/**
	 * 添加新报告
	 */
	@Override
	public int addReport(NiReport report) {
		int addReport = niReportMapper.insert(report);
		return addReport;
	}

	@Override
	public NiReport selectByPrimaryKey(Integer reportid) {
		NiReport niReport = niReportMapper.selectByPrimaryKey(reportid);
		return niReport;
	}

	@Override
	public List<NiReport> selectAllListByReportClassId() {
		List<NiReport> selectList = niReportMapper
				.selectAllListByReportClassId();
		return selectList;
	}

	@Override
	public NiReport getReportIdByReportInfo(NiReport report) {
		return niReportMapper.getReportIdByReportInfo(report);
	}

	@Override
	public Map<String, Object> uploadReportListPic(HttpServletRequest request,
			MultipartFile file, String reportId) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file,
				"html/report/" + formatDate + "/" + reportId);
		return uploadFile;
	}

	@Override
	public void updateImgUrl(int reportId, String reportImg) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportId", reportId);
		hashMap.put("reportImg", reportImg);
		niReportMapper.updateImgUrl(hashMap);
	}

	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> uploadReportZip(HttpServletRequest request,
			MultipartFile file, String reportId) throws Exception {
		String reportHtml = "";
		// 先将zip包上传至目录下
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		Map<String, Object> uploadFile = FileUtil.uploadFileRename(request,
				file, "zip");
		String zipFilePath = (String) uploadFile.get("realPath");
		// File filezip=(File) uploadFile.get("file");

		// 解压路径
		String relativePath = "/../data/res/static/html/report/" + formatDate
				+ "/" + reportId;
		String unzipFilePath = request.getSession().getServletContext()
				.getRealPath(relativePath);
		if (unzipFilePath == null) {
			unzipFilePath = request.getSession().getClass().getClassLoader()
					.getResource(relativePath).getPath();
		}
		// 解压

		List<String> fileNameList = FileUtil.unzip(zipFilePath, unzipFilePath);

		for (int i = 0; i <fileNameList.size(); i++) {
			if (fileNameList.get(i).contains(".html")) {
				reportHtml = fileNameList.get(i);
			}
		}

		// 将文件名固定取“template.html”为了解决苹果机压缩问题
		uploadFile.put("reportHtml", reportHtml);
		return uploadFile;
	}

	@Override
	public void updateReportUrl(int reportId, String reportUrl) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reportId", reportId);
		hashMap.put("reportUrl", reportUrl);
		niReportMapper.updateReportUrl(hashMap);
	}

	/**
	 * 编辑报告
	 */
	@Override
	public int editReport(NiReport report) {
		int addReport = niReportMapper.edit(report);
		return addReport;
	}

}
