package com.newIns.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

/**
 * 上传图片类
 * @author Sang
 *
 */
@Controller
@RequestMapping("/platform")
public class UploadPicController {
	private static Logger log = Logger.getLogger(UploadPicController.class); 
	/**
	 * 上传调查问卷 标题图
	 * @param request
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/uploadNiSurveyQuestionnairePic.do",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadNiSurveyQuestionnairePic(HttpServletRequest request,
			@RequestParam("uploadImgmes") MultipartFile file) throws IllegalStateException, IOException{
		AjaxResult ajaxResult = new AjaxResult();
		//拼接保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formatDate = sdf.format(new Date());
		String sqnId = request.getParameter("sqnId");
		if(StrUtils.isEmpty(sqnId)){
			return AjaxResult.errorResult("未选择问卷,请重新选择!");
		}
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/sqn/"+formatDate+"/"+sqnId);
		
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
	
	private void showZip(HttpServletResponse response, InputStream inputStream)throws IOException {
		
//		new ZipFile(file);
		
		ZipInputStream zis=new ZipInputStream(inputStream);
		
		ZipEntry zipEntry=zis.getNextEntry();
		
		while (zipEntry!=null) {
			
			String name = zipEntry.getName();
			
			if (name.contains("xlsx")) {//也可以建个map先把文件读进去，以后用的时候再取
			InputStreamReader isr = new InputStreamReader(zis);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			while ((str = br.readLine()) != null) {
					response.getWriter().println(str);
				}
			}
			
			if(name.contains("jpg")){
				System.out.println("这是个图片");
			}
			
			zipEntry=null;
			zipEntry=zis.getNextEntry();
		}
		
	}
	
	@RequestMapping("/readZip.do")
	public void readZip(HttpServletResponse response,HttpServletRequest request,@RequestParam("exampleInputFile") MultipartFile file)
			throws FileUploadException, IOException{

		//上传压缩包
		Map<String, Object> uploadZip = FileUtil.uploadZip(file, "Surv", request);
		
		//获取压缩包在服务器上的路径
		String zipFilePath = (String) uploadZip.get("realPath");
		
		//解压后的路径
	   String relativePath = "/../data/unzip/";
       String targetPath = request.getSession().getServletContext().getRealPath(relativePath);
       
       if(targetPath==null){
    	   targetPath = request.getSession().getClass().getClassLoader().getResource(relativePath).getPath();
       }
       
       List<String> zipFileRealPathList = FileUtil.zipFileRealPathList(zipFilePath, targetPath);
       
       for(int i=0; i<zipFileRealPathList.size();i++){
    	   String realPath = zipFileRealPathList.get(i);
    	   System.out.println("解压文件的路径: "+realPath);
       }
      
	}
}
