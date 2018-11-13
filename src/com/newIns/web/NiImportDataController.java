package com.newIns.web;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.service.NiImportDataFromWanxService;

@Controller
@RequestMapping("/platform")
public class NiImportDataController {
	private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
	@Resource
	private NiImportDataFromWanxService niImportDataFromWanxService;

	class ImportDataThread extends Thread {

		@Override
		public void run() {
			niImportDataFromWanxService.start("wnx_dc_user");
		}

	}
	
	@RequestMapping("/niImportData.do")
	public ModelAndView niImportData() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/NiImportData");
		return modelAndView;
	}

	@RequestMapping("/processController.do")
	@ResponseBody
	public void processHandle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType(CONTENT_TYPE); // 设置服务器响应类型
		response.setHeader("Cache-Control", "no-cache"); // 页面不记录缓存
		String flag = request.getParameter("flag"); // 操作类型

		StringBuffer xml = new StringBuffer("<items>");
		if ("start".equals(flag)) { // 第一次创建
			try {
				new ImportDataThread().start();//启动新线程执行导入数据操作
			} catch (Exception e) {
				
			}
		} else {
			//主进程响应前端请求，返回进度值
			String proportion = niImportDataFromWanxService.getProgress() + "";// 获取数据导入进度
			String totalNum = niImportDataFromWanxService.getTotalNum() + "";
			String currentNum = niImportDataFromWanxService.getCurrentNum() + "";
			xml.append("<percent>").append(proportion).append("</percent>");
			xml.append("<total>").append(totalNum).append("</total>");
			xml.append("<current>").append(currentNum).append("</current>");
		}
		xml.append("</items>");
		PrintWriter out = response.getWriter();
		out.println(xml.toString()); // 返回生成的XML串
		out.flush(); // 输出流刷新
		out.close(); // 关闭输出流
	}

}
