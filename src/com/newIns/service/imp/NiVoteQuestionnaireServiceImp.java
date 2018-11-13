package com.newIns.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.vote.NiVoteQuestionnaireMapper;
import com.newIns.model.survery.NiSurveyQuestion;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.model.vo.NiVoteQuestionnaireOptionVo;
import com.newIns.model.vote.NiVoteQuestionnaire;
import com.newIns.model.vote.NiVoteQuestionnaireVO;
import com.newIns.service.NiVoteQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.ExcelTool;
import com.newIns.tools.FileUtil;
import com.newIns.tools.MD5Utils;
import com.newIns.tools.StrUtils;
import com.newIns.web.content.NiSurveyQuestionnaireController;

@Service
@Transactional
public class NiVoteQuestionnaireServiceImp implements NiVoteQuestionnaireService{
	private static Logger log = Logger.getLogger(NiSurveyQuestionnaireController.class);
	
	@Resource
	private NiVoteQuestionnaireMapper niVoteQuestionnaireMapper;
	
	// 上传 投票问卷
	public AjaxResult NiVoteQuestionnaireSave(HttpServletRequest request,
			MultipartFile file, MultipartFile pic) {
		
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		// 上传 excel文件
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, file, "qn/aqn/"+nowDate);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		// 读取模版中内容, 创建测评问卷
		String filePath = (String) uploadFile.get("realPath");
		
		AjaxResult assExcelData_new = getVotExcelData_new(filePath,pic, ".xlsx",request);
		
		return assExcelData_new;
		
	}
	
	private AjaxResult getVotExcelData_new(String fileName, MultipartFile pic,
			String fileFileName, HttpServletRequest request) {
		
		// 创建输入流
		// 获取Excel文件对象
		Workbook wb = null;
		InputStream in = null;
		try {
			in = new FileInputStream(fileName); 
			wb = ExcelTool.openWorkbook(in, fileName, fileFileName);
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Sheet sheet_0 = null;  // 第一 sheet页
		//获取 问卷题目数量
		try {
			sheet_0 = wb.getSheetAt(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Class<?> forName_NiVoteQuestionnaire = null;
		Constructor<?> constructor_NiSurveyQuestion = null;
		try {
			forName_NiVoteQuestionnaire = Class.forName("com.newIns.model.vote.NiVoteQuestionnaire");
			constructor_NiSurveyQuestion = forName_NiVoteQuestionnaire.getConstructor();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		NiVoteQuestionnaire niVoteQuestionnaire = null;
		try {
			niVoteQuestionnaire = (NiVoteQuestionnaire) constructor_NiSurveyQuestion.newInstance();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// 获取 问卷描述信息
		for(int x=0;x<23;x++){
			Row row0 = sheet_0.getRow(x);
			Cell cell = row0.getCell(1);
			String cellValue = "";
			if (null != cell) {
				// 以下是判断数据的类型
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					cellValue = cell.getNumericCellValue() + "";
					cellValue = cellValue.substring(0, cellValue.length()-2);
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串
					cellValue = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					cellValue = "";
					break;
				}
				
				if(x == 20){
					if(StrUtils.isEmpty(cellValue)){
						return AjaxResult.errorCode("问卷编辑人未录入!", 0);  // 设置0 作为标记, 一遍删除的时候 出现 问卷id 为 nudifined
					}
				}
				
				if(x == 0){
					niVoteQuestionnaire.setVqnname(cellValue); // 问卷标题
				}else if(x == 1){
					niVoteQuestionnaire.setVqnsummary(cellValue);// 问卷简介
				}else if(x == 4){
					niVoteQuestionnaire.setPublishername(cellValue);  // 发布机构名称
				}else if(x == 8){
					niVoteQuestionnaire.setVqnclassid(Integer.valueOf(cellValue));  //问卷分类
				}else if(x == 20){
					niVoteQuestionnaire.setEditor(cellValue); // 编辑人
				}
				
			}
		}
		niVoteQuestionnaire.setStatus(1);
		niVoteQuestionnaire.setCtime(new Date());
		
		// 第一页  问题内容
			Sheet sheet = wb.getSheetAt(1);
			
			Row row_0 = sheet.getRow(0);
			for(int y=0 ; y <= 50; y++){
				Cell cell = row_0.getCell(y);
				
				String cellValue = "";
				if(cell != null){
					switch(cell.getCellType()){
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						cellValue = cellValue.substring(0, cellValue.length()-2);
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;
					}
					
					if(y == 5){
						niVoteQuestionnaire.setOptionnum(Integer.valueOf(cellValue));  // 选项数量
					}else if(y == 7){   // 问题类型
						niVoteQuestionnaire.setQuestiontype(Integer.valueOf(cellValue));
					}else if(y == 9){
						niVoteQuestionnaire.setVqtitle(cellValue);  // 提干
					}else if(y == 11){
						niVoteQuestionnaire.setRequired(Integer.valueOf(cellValue));
					}else if(y == 13){
						niVoteQuestionnaire.setIsselfdefine(Integer.valueOf(cellValue));
					}else if(y == 15){
						niVoteQuestionnaire.setCorrectanswer(cellValue);
					}
					
				}

			}
			
			// 判断问题类型
				// 遍历选项
				boolean flag = false;
				for(int x = 1; x <=50 ; x++){
					Row row = sheet.getRow(x);
					
					if(row == null){
						break;
					}else{
						
						for(int y = 0; y <= 11; y++){
							Cell cell = row.getCell(y);
							
							String cellValue = "";
							if(cell != null){
								switch(cell.getCellType()){
								case HSSFCell.CELL_TYPE_NUMERIC: // 数字
									cellValue = cell.getNumericCellValue() + "";
									cellValue = cellValue.substring(0, cellValue.length()-2);
									break;
								case HSSFCell.CELL_TYPE_STRING: // 字符串
									cellValue = cell.getStringCellValue();
									break;
								case HSSFCell.CELL_TYPE_BLANK: // 空值
									cellValue = "";
									break;
								}
								
								if(y == 1){
									if(StrUtils.isEmpty(cellValue)){
										flag = true;
										break;
									}
								}
								
								try {
									Method method_setOptiondes = forName_NiVoteQuestionnaire.getMethod("setOption"+x+"des",String.class);
									
									if(y == 1){
										method_setOptiondes.invoke(niVoteQuestionnaire, cellValue);
									}
									
								}catch (Exception e) {
									e.printStackTrace();
								}
								
							}
						}
					}

					if(flag){
						break;
					}
				}
			
			try {
				niVoteQuestionnaireMapper.insert(niVoteQuestionnaire);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		Integer vqnId = niVoteQuestionnaire.getVqnid();
		
		// ------------------------上传问卷 内图片-------------
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, pic, "img/qn/aqn/"+formatDate+"/"+vqnId);
		} catch (IllegalStateException | IOException e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传问卷内图失败", vqnId);
		}
		
		String jdbcUrl_pic = (String) uploadFile.get("jdbcUrl");
		
		niVoteQuestionnaire.setPicpath(jdbcUrl_pic);
		
		try {
			niVoteQuestionnaireMapper.updateByPrimaryKeySelective(niVoteQuestionnaire);
		} catch (Exception e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传问卷内图失败, 本次上传问卷将被删除", vqnId);
		}
		
		//-------------------------读取问题内容--------end--------------
		return AjaxResult.successResult("问卷上传成功");
		
	}
	
	/**
	 * 列表查询投票问卷
	 */
	public List<NiVoteQuestionnaireVO> selectList(HashMap<String, Object> hashMap) {
		List<NiVoteQuestionnaireVO> selectList = niVoteQuestionnaireMapper.selectList(hashMap);
		return selectList;
	}
	//批量发布
	public int releaseNiVoteQuestionnaire(HashMap<String, Object> retMap) {
		int updateNiVoteQuestionnaire = niVoteQuestionnaireMapper.updateNiVoteQuestionnaire(retMap);
		return updateNiVoteQuestionnaire;
	}
	@Override
	public int revokeNiVoteQuestionnaire(HashMap<String, Object> retMap) {
		int updateNiVoteQuestionnaire = niVoteQuestionnaireMapper.updateNiVoteQuestionnaire(retMap);
		return updateNiVoteQuestionnaire;
	}
	@Override
	public int deleteNiVoteQuestionnaire(HashMap<String, Object> retMap) {
//		int updateNiVoteQuestionnaire = niVoteQuestionnaireMapper.updateNiVoteQuestionnaire(retMap);
		int updateNiVoteQuestionnaire = niVoteQuestionnaireMapper.deleteStateByids(retMap);
		return updateNiVoteQuestionnaire;
	}

	@Override
	public NiVoteQuestionnaire selectByPrimaryKey(Integer vqnid) {
		NiVoteQuestionnaire selectByPrimaryKey = niVoteQuestionnaireMapper.selectByPrimaryKey(vqnid);
		return selectByPrimaryKey;
	}

	@Override
	public int updateByPrimaryKeySelective(NiVoteQuestionnaire record) {
		int updateByPrimaryKeySelective = niVoteQuestionnaireMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective;
	}

	//投放添加问卷筛选
	public List<NiVoteQuestionnaire> select_niVote_Dev() {
		List<NiVoteQuestionnaire> select_niVote_Dev = niVoteQuestionnaireMapper.select_niVote_Dev();
		return select_niVote_Dev;
	}

	@Transactional
	public AjaxResult editNiVoteQuestionnaireSave(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		 String sqnId_show = request.getParameter("sqnId_show"); //问卷id
		 String qe_sqnname = request.getParameter("qe_sqnname");   //问卷名称
		 String qe_editor = request.getParameter("qe_editor");// 出题人
		 String qe_publisherName = request.getParameter("qe_publisherName");// 发布机构
		 String qe_collectNum = request.getParameter("qe_collectNum"); // 建议回收数量
		 String qe_Classification = request.getParameter("qe_Classification");// 问卷分类
		 String qe_Profile = request.getParameter("qe_Profile");// 问卷简介
		 String jdbcUrl = request.getParameter("jdbcUrl");//图片上传数据库路径
		 
		//封装数据
		 NiVoteQuestionnaire niVoteQuestionnaire = new NiVoteQuestionnaire();
		 
		 Integer sqnId = null;
		 if(StrUtils.isNotEmpty(sqnId_show)){
			 sqnId = Integer.valueOf(sqnId_show);
		 }
		 Integer recommandQty = null;
		 if(StrUtils.isNotEmpty(qe_collectNum)){
			 recommandQty = Integer.valueOf(qe_collectNum);
		 }
		 Integer sqnClassId =null;
		 if(StrUtils.isNotEmpty(qe_Classification)){
			 sqnClassId = Integer.valueOf(qe_Classification);
		 }
		 
		 if(sqnId!=null){
			 niVoteQuestionnaire.setVqnid(sqnId);
			 niVoteQuestionnaire.setVqnname(qe_sqnname);
			 niVoteQuestionnaire.setEditor(qe_editor);
			 niVoteQuestionnaire.setRecommandqty(recommandQty);
			 niVoteQuestionnaire.setVqnclassid(sqnClassId);
			 niVoteQuestionnaire.setVqnsummary(qe_Profile);
			 niVoteQuestionnaire.setPicpath(jdbcUrl);
			 niVoteQuestionnaire.setPublishername(qe_publisherName);
			 
			 try {
				 
				int updateByPrimaryKeySelective = niVoteQuestionnaireMapper.updateByPrimaryKeySelective(niVoteQuestionnaire);
				if(updateByPrimaryKeySelective>0){
					ajaxResult.put("success", true);
					ajaxResult.put("msg", "编辑成功");
				}else{
					return AjaxResult.errorResult("编辑失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("编辑失败");
			}
		 }
		return ajaxResult;
	}

	//查询问卷信息
	public void findOneNiVoteQuestionnaire(HttpServletRequest request,Model model) {
		
		String sqnid = request.getParameter("vqnId");
		
		if(StrUtils.isNotEmpty(sqnid)){
			//查询问卷信息
			NiVoteQuestionnaire niVoteQuestionnaire = niVoteQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(sqnid));
			if(niVoteQuestionnaire!=null){
				model.addAttribute("niVoteQuestionnaire", niVoteQuestionnaire);
				
				//获取选项数量
				Integer optionnum = niVoteQuestionnaire.getOptionnum();
				
				List<NiVoteQuestionnaireOptionVo> optionList = new ArrayList<NiVoteQuestionnaireOptionVo>();
//				Map<String, Object> optMap = new HashMap<String, Object>();
				//遍历取出每个选项描述
				for(int n=1;n<=optionnum;n++){
					
					NiVoteQuestionnaireOptionVo niVoteQuestionnaireOptionVo = new NiVoteQuestionnaireOptionVo();
					
					
					String optionDesByIndex = niVoteQuestionnaire.getOptionDesByIndex(n);
					String optionPicByIndex = niVoteQuestionnaire.getOptionPicByIndex(n);
					
					niVoteQuestionnaireOptionVo.setOptionDes(optionDesByIndex);
					niVoteQuestionnaireOptionVo.setOptionPic(optionPicByIndex);
					niVoteQuestionnaireOptionVo.setOptionFlag("option"+n);
					
//					optMap.put("optionDes", optionDesByIndex);
//					optMap.put("optionPic", optionPicByIndex);
					
					optionList.add(niVoteQuestionnaireOptionVo);
					
				}
				
				model.addAttribute("optionList", optionList);
				
			}
			
//			//封装所有数据的集合
//			List<Object> arrayList = new ArrayList<Object>();
//			
//			//查询问题集合
//			//封装每个问题中所有数据
//				Map<String, Object> queMap = new HashMap<String, Object>();
//				
//				if(niSurveyQuestion!=null){
//					//问题id
//					Integer sqid = niSurveyQuestion.getSqid();
//					//显示题号
//					Integer questionnum = niSurveyQuestion.getQuestionnum();
//					//提干
//					String sqtitle = niSurveyQuestion.getSqtitle();
//					//获取选项数量
//					Integer optionnum = niSurveyQuestion.getOptionnum();  //选项数量
//					
//					
//					List<Object> optionList = new ArrayList<Object>();
//					//遍历取出每个选项描述
//					for(int n=1;n<=optionnum;n++){
//						
//						String optionDesByIndex = niSurveyQuestion.getOptionDesByIndex(n);
//						
//						optionList.add(optionDesByIndex);
//						
//					}
//					
//					//封装每个问题
//					queMap.put("sqid", sqid);
//					queMap.put("questionnum", questionnum);
//					queMap.put("optionnum", optionnum);
//					queMap.put("sqtitle", sqtitle);
//					queMap.put("optionList", optionList);
//					
//					
//				}
//				
//				//将所有的问题添加到list集合中
//				arrayList.add(queMap);
				
			}
				
		}

	//保存编辑后的问卷
	public AjaxResult updateNiVoteQuestionnaire_edit(MultipartFile file,MultipartFile[] optPics,MultipartFile titleImg,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxResult ajaxResult = new AjaxResult();
		
		//获取问卷内容信息
		String qe_sqnid = request.getParameter("qe_sqnid"); //问卷id
		String qe_sqnname = request.getParameter("qe_sqnname"); //问卷名称
		String qe_publisherName = request.getParameter("qe_publisherName"); //发布者
		String qe_editor = request.getParameter("qe_editor"); //编辑人
		String qe_collectNum = request.getParameter("qe_collectNum"); //建议回收数量
		String qe_Profile = request.getParameter("qe_Profile"); //问卷简介
		
		String optMinNum_str = request.getParameter("optMinNum");
		String optMaxNum_str = request.getParameter("optMaxNum");
		
		if(StrUtils.isEmpty(qe_sqnid)){
			return AjaxResult.errorResult("保存失败");
		}
		
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		//上传问卷标题图, 获取标题图的存储路径
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/vqn/"+formatDate+"/"+qe_sqnid);
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");
		
		//上传问题提干图
		Map<String, Object> titlePic = FileUtil.uploadFile(request, titleImg, "img/qn/vqn/"+formatDate+"/"+qe_sqnid);
		String titlePicUrl = (String) titlePic.get("jdbcUrl");
		
		Class<?> clz = Class.forName("com.newIns.model.vote.NiVoteQuestionnaire");
		NiVoteQuestionnaire niVoteQuestionnaire = (NiVoteQuestionnaire) clz.newInstance();
		
		niVoteQuestionnaire.setVqnid(Integer.valueOf(qe_sqnid));
		niVoteQuestionnaire.setVqnname(qe_sqnname);
		niVoteQuestionnaire.setPublishername(qe_publisherName);
		niVoteQuestionnaire.setEditor(qe_editor);
		
		if(StrUtils.isNotEmpty(qe_collectNum)){
			niVoteQuestionnaire.setRecommandqty(Integer.valueOf(qe_collectNum));
		}
		niVoteQuestionnaire.setVqnsummary(qe_Profile);
		niVoteQuestionnaire.setPicpath(jdbcUrl);
		//保存问题提干图
		niVoteQuestionnaire.setqImgUrl(titlePicUrl);
		
		//获取问题提干
		String sqTitle = request.getParameter("sqTitle");
		niVoteQuestionnaire.setVqtitle(sqTitle);
		
		String questionType_edit_arr = request.getParameter("questionType_edit");  //获取问题类型
		if(StrUtils.isNotEmpty(questionType_edit_arr)){
			niVoteQuestionnaire.setQuestiontype(Integer.valueOf(questionType_edit_arr));
		}
		
		//选项数量
		String qe_optionnum = request.getParameter("optionnum");
		
		
		//获取所有的选项
		String[] optionDes_arr = request.getParameterValues("optionDes");
		
		Integer optionnum = null;
		if(StrUtils.isNotEmpty(qe_optionnum)){
			//选项数量
			optionnum = Integer.valueOf(qe_optionnum);
			
			int y = 1;
			for(int n=0;n<optionnum;n++){
				String optionDes = optionDes_arr[n];  //选项描述
				
				//上传选项图
				MultipartFile multipartFile = optPics[n];
				Map<String, Object> uploadFile2 = FileUtil.uploadFile(request, multipartFile, "img/questionnaire/vqn/"+formatDate+"/"+qe_sqnid);
				String optPicUrl = (String) uploadFile2.get("jdbcUrl");  //选项图存储路径
				
				Method desMethod = clz.getMethod("setOption"+y+"des", String.class);
				Method picMethod = clz.getMethod("setOption"+y+"Pic", String.class);
				y++;
				
				desMethod.invoke(niVoteQuestionnaire, optionDes);
				picMethod.invoke(niVoteQuestionnaire, optPicUrl);
			}
			
		}
		
		//多选数量限制
			Integer optMinNum = null;
			if(StrUtils.isNotEmpty(optMinNum_str)){
				try {
					optMinNum = Integer.valueOf(optMinNum_str);
				} catch (Exception e) {
//						e.printStackTrace();
					return AjaxResult.errorResult("最少选项设置错误");
				}
				niVoteQuestionnaire.setOptMinNum(optMinNum);
			}
			Integer optMaxNum = null;
			if(StrUtils.isNotEmpty(optMaxNum_str)){
				try {
					optMaxNum = Integer.valueOf(optMaxNum_str);
				} catch (Exception e) {
//						e.printStackTrace();
					return AjaxResult.errorResult("最多选项设置错误");
				}
				niVoteQuestionnaire.setOptMaxNum(optMaxNum);
			}
			//---------多选限制条件校验
			if((optMaxNum != null) && (optMaxNum > optionnum) ){
				return AjaxResult.errorResult("最多选项设置已经超出了选项数量");
			}else if( (optMinNum!=null) && (optMinNum < 0) ){
				return AjaxResult.errorResult("最少选项设置错误");
			}else if((optMaxNum!=null) && (optMinNum!=null) && (optMaxNum < optMinNum)){
				return AjaxResult.errorResult("最少选项设置不能大于最多选项设置");
			}
			
		
		try {
			niVoteQuestionnaireMapper.updateByPrimaryKeySelective(niVoteQuestionnaire);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
		} catch (Exception e1) {
			e1.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		return ajaxResult;
	}

	@Override
	public void niVoteQue_View_jump(HttpServletRequest request, Model model) {
		String vqnId = request.getParameter("vqnId");
		model.addAttribute("vqnId", vqnId);
		
		if(StrUtils.isNotEmpty(vqnId)){
			NiVoteQuestionnaire niVoteQuestionnaire = niVoteQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(vqnId));
			if(niVoteQuestionnaire!=null){
				String picpath = niVoteQuestionnaire.getPicpath();
				model.addAttribute("picPath", picpath);
			}
			
		}
		
		
	}

}

