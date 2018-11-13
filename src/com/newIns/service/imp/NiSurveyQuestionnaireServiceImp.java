package com.newIns.service.imp;

import java.io.FileInputStream;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newIns.dao.NiSqnclassDictMapper;
import com.newIns.dao.survey.NiSurveyQuestionMapper;
import com.newIns.dao.survey.NiSurveyQuestionnaireMapper;
import com.newIns.dao.survey.SurveyQuestionnaireDao;
import com.newIns.model.NiSqnclassDict;
import com.newIns.model.page.Page;
import com.newIns.model.survery.NiSurveyQuestion;
import com.newIns.model.survery.NiSurveyQuestionnaire;
import com.newIns.model.survery.NiSurveyQuestionnaireVO;
import com.newIns.model.survery.SurveyQuestion;
import com.newIns.model.survery.SurveyQuestionOption;
import com.newIns.model.survery.SurveyQuestionWithOption;
import com.newIns.model.survery.SurveyQuestionnaire;
import com.newIns.service.LoadSurveyQuestionService;
import com.newIns.service.NiSurveyQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.ExcelTool;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

@Service
@Transactional
public class NiSurveyQuestionnaireServiceImp implements NiSurveyQuestionnaireService{
	private static Logger log = Logger.getLogger(NiSurveyQuestionnaireServiceImp.class); 
	@Resource
	private NiSurveyQuestionnaireMapper niSurveyQuestionnaireMapper;
	@Resource
	private NiSurveyQuestionMapper niSurveyQuestionMapper;
	@Autowired
	private SurveyQuestionnaireDao SvyQnDao;
	@Autowired
	private LoadSurveyQuestionService loadSqService;
	@Autowired
	private NiSqnclassDictMapper sqnclassDictMapper;
	
	public int insert(NiSurveyQuestionnaire record) {
		int insert = niSurveyQuestionnaireMapper.insert(record);
		return insert;
	}

	public Object loadSurveyQuestionnaire(int sqnId) {
		log.info("This is service loadSurveyQuestionnaire()");
		SurveyQuestionnaire svyQuestionnaire = SvyQnDao
				.loadSurveyQuestionnaire(sqnId);
		if (svyQuestionnaire != null) {
			svyQuestionnaire.setSuccess("true");
			// 将ni_survey_question表中某个问卷的所有问题信息读出
			List<SurveyQuestionWithOption> svyQuestionWithOptionList = loadSqService
					.loadSurveyQuestionWithOption(sqnId);
			List<SurveyQuestion> svyQuestionList = new ArrayList<SurveyQuestion>();// 对应接口的question列表

			for (int i = 0; i < svyQuestionWithOptionList.size(); i++) {
				List<SurveyQuestionOption> svyOptionList = new ArrayList<SurveyQuestionOption>();// 对应接口的问题选项列表
				SurveyQuestionWithOption sqWithOptionTem = svyQuestionWithOptionList
						.get(i);// 第i个问题的完整信息
				SurveyQuestion sqTem = new SurveyQuestion();
				int qType=sqWithOptionTem.getQuestionType();//获取这个题的问题类型，为了下面判断
				// 从完整信息中抽取question相关的信息，付给question对象
				sqTem.setSqId(sqWithOptionTem.getSqId());
				sqTem.setQuestionNum(sqWithOptionTem.getQuestionNum());
				sqTem.setSqTitle(sqWithOptionTem.getSqTitle());
				sqTem.setQuestionType(qType);
				sqTem.setRequired(sqWithOptionTem.getRequired());
				sqTem.setOptionNum(sqWithOptionTem.getOptionNum());
				sqTem.setIsSelfDefine(sqWithOptionTem.getIsSelfDefine());
				//正确答案如果非空转换为大写
				String correctAnswerStr = sqWithOptionTem.getCorrectAnswer();
				if (correctAnswerStr!=null&&!correctAnswerStr.equals("")){
					correctAnswerStr = correctAnswerStr.toUpperCase();
				}
				sqTem.setCorrectAnswer(correctAnswerStr);
				sqTem.setAnswerAnalysis(sqWithOptionTem.getAnswerAnalysis());
				for (int j = 1; j <= sqTem.getOptionNum(); j++) {
					SurveyQuestionOption sqOption = new SurveyQuestionOption();
					sqOption.setOptionDes(sqWithOptionTem
							.getOptionDesByIndex(j));
					sqOption.setOptionLink(sqWithOptionTem
							.getOptionLinkByIndex(j));
					sqOption.setOptionFeedback(sqWithOptionTem
							.getOptionFeedbackByIndex(j));
					sqOption.setOptionOrder(j);
					svyOptionList.add(j - 1, sqOption);
				}// 将选项付给相应的问题
				sqTem.setOptions(svyOptionList);// 将选项列表添加到问题属性
				svyQuestionList.add(i, sqTem);
			}
			svyQuestionnaire.setQuestions(svyQuestionList);// 将问题列表添加到问卷属性
			return svyQuestionnaire;
		} else {
			return AjaxResult.errorResult("找不到问卷呢");
		}
	}
	
	
	
	/**
	 * 根据主键查询
	 */
	public NiSurveyQuestionnaire selectByPrimaryKey(Integer sqnid) {
		NiSurveyQuestionnaire selectByPrimaryKey = niSurveyQuestionnaireMapper.selectByPrimaryKey(sqnid);
		return selectByPrimaryKey;
	}

	/**
	 * 根据 sqnId 修改其他属性
	 */
	public int updateByPrimaryKeySelective(NiSurveyQuestionnaire record) {
		int updateByPrimaryKeySelective = niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective;
	}

	public void listNiSurveyQuestionnaire(HttpServletRequest request,Model model) {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		log.info("========================>>分页查询调查问卷信息");
		String sqnpublish = request.getParameter("sqnpublish");   //查询分类
		String sqnNameOrpublisherName = request.getParameter("sqnNameOrpublisherName");
		String qnclassid = request.getParameter("qnclassid");
		String surQueryStats = request.getParameter("surQueryStats");

		if(StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrpublisherName)){
			//判断选择的是文件名称，还是调查机构名称
			if(sqnpublish.trim().equals("1")){
				//问卷名称
				hashMap.put("sqnName", sqnNameOrpublisherName.trim());
			}else if(sqnpublish.trim().equals("0")){
				//问卷id
				hashMap.put("sqnId", sqnNameOrpublisherName.trim());
			}
		}

		Integer qnclassId = StrUtils.changeToInt(qnclassid);
		Integer surQueryStatus = StrUtils.changeToInt(surQueryStats);
		
		hashMap.put("qnclassId", qnclassId);
		hashMap.put("staus", surQueryStatus);
		
		//查询问卷分类
		List<NiSqnclassDict> sqnclassDict_list = sqnclassDictMapper.selectList();
		
		List<NiSurveyQuestionnaireVO> selectList = niSurveyQuestionnaireMapper.selectList(hashMap);
		
		model.addAttribute("niSqnclassDict_list", sqnclassDict_list);
		model.addAttribute("niSurveyQuestionnaires", selectList);
		
	}

	/**
	 * 读取调查问卷模板
	 */
	@Transactional
	public AjaxResult readinvestigationExcel(String fileName) throws Exception {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		AjaxResult ajaxResult = new AjaxResult();

		//读取excel文件
		StringBuffer sb = ExcelTool.getExcelData(fileName, ".xlsx");

		String string = "";
		if(sb!=null){
			string = sb.toString();
		}
		//截取字符串，得到每一页的内容
		String[] strings = string.split("\\$#");
		log.info("截取的页数的长度====================>>"+strings.length);
		String excel0 = strings[0];  //第一页中内容
		//得到第一页中具体内容
		String[] strs = excel0.split("\\|");
		
		//得到题目数量
		Integer qNum = Integer.valueOf(strs[2]);
		
		NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
		try {
			//调查问卷标题
			String sqnName = strs[0];  
			if(sqnName.trim().equals("")){
				return AjaxResult.errorResult("失败!问卷标题不能为空");
			}
			
			//问卷简介
			String sqnSummary = strs[1];
			if(sqnName.trim().equals("")){
				return AjaxResult.errorResult("失败!问卷简介不能为空");
			}
			
			//题目数量
			Integer questionQty = null;
			if(!strs[2].trim().equals("")){
				questionQty = Integer.valueOf(strs[2]); 
			}else{
				return AjaxResult.errorResult("失败!题目数量不能为空");
			}
			
			String picPath = strs[3];  // 问卷标题图
			
			//调查问卷种类
			Integer sqnCategory = null;
			String sqn_Category = strs[4];
			if(StrUtils.isNotEmpty(sqn_Category)){
				sqnCategory = Integer.valueOf(sqn_Category);
			}else{
				return AjaxResult.errorResult("失败!问卷种类不能为空");
			}
			
			//核心题号
			Integer keyQuestionNum = null;
			if(!strs[5].trim().equals("")){
				keyQuestionNum = Integer.valueOf(strs[5]);
			}
			
			
			String publisherName = strs[6]; //发布者名称
			String perface = strs[7];  //卷首语
			
			//建议回收数量
			Integer	recommandQty = null;
			if(!strs[8].trim().equals("")){
				recommandQty = Integer.valueOf(strs[8]);
			}
				
			// 有效性验证方式
			Integer validation = null;
			if(!strs[9].trim().equals("")){
				 validation = Integer.valueOf(strs[9]); 
			}
			
			//问卷分类ID
			Integer sqnClassId = null;
			if(!strs[10].trim().equals("")){
				sqnClassId = Integer.valueOf(strs[10]); 
			}else{
				return AjaxResult.errorResult("失败!问卷分类不能为空");
			}
			
			Integer tag1Id = null;
			if(!strs[11].trim().equals("")){
				 tag1Id = Integer.valueOf(strs[11]); //标签1
			}
			
			Integer tag2Id = null;
			if(!strs[13].trim().equals("")){
				 tag2Id = Integer.valueOf(strs[13]); //标签2
			}
			
			Integer tag3Id = null;
			if(!strs[15].trim().equals("")){
				tag3Id = Integer.valueOf(strs[15]); //标签3
			}
			
			Integer tag4Id = null;
			if(!strs[17].trim().equals("")){
				 tag4Id = Integer.valueOf(strs[17]); //标签4
			}
			
			Integer tag5Id = null;
			if(!strs[19].trim().equals("")){
				 tag5Id = Integer.valueOf(strs[19]); //便签5
			}
			String epilogue = strs[21];  //结语
			
			if(strs[22].trim().equals("")){
				return AjaxResult.errorResult("请录入问卷编辑人!");
			}
			String editor = strs[22]; //问卷编辑人
			String comment = "";
			if(strs.length>23){
				 comment = strs[23]; //问卷备注 publisherId
			}
			
			niSurveyQuestionnaire.setSqnname(sqnName);
			niSurveyQuestionnaire.setSqnsummary(sqnSummary);
			niSurveyQuestionnaire.setQuestionQty(questionQty);
			niSurveyQuestionnaire.setPicpath(picPath);
			niSurveyQuestionnaire.setPublishername(publisherName);
			niSurveyQuestionnaire.setPerface(perface);
			niSurveyQuestionnaire.setRecommandqty(recommandQty);
			niSurveyQuestionnaire.setValidation(validation);
			niSurveyQuestionnaire.setSqnclassid(sqnClassId);
			niSurveyQuestionnaire.setSqnCategory(sqnCategory);  //调查问卷种类
			niSurveyQuestionnaire.setKeyQuestionNum(keyQuestionNum); //核心题号
			niSurveyQuestionnaire.setTag1id(tag1Id);
			niSurveyQuestionnaire.setTag2id(tag2Id);
			niSurveyQuestionnaire.setTag3id(tag3Id);
			niSurveyQuestionnaire.setTag4id(tag4Id);
			niSurveyQuestionnaire.setTag5id(tag5Id);
			niSurveyQuestionnaire.setEpilogue(epilogue);
			niSurveyQuestionnaire.setEditor(editor);
			niSurveyQuestionnaire.setComment(comment);
			niSurveyQuestionnaire.setCtime(new Date());  //创建时间
			niSurveyQuestionnaire.setStaus(1);  //草稿状态
			
			int insert0 = niSurveyQuestionnaireMapper.insert(niSurveyQuestionnaire);
			if(insert0>0){
				log.info("====================>>创建问卷表成功");
			}else{
				log.info("====================>>创建问卷表失败");
				return AjaxResult.errorResult("保存问卷内容失败");
			}
			//循环出每一页中的内容，即为每一题中的内容
			for(int i =1 ;i<qNum+1;i++){	  
				Class<?> clz = Class.forName("com.newIns.model.survery.NiSurveyQuestion");
				
				NiSurveyQuestion niSurveyQuestion = (NiSurveyQuestion) clz.newInstance();
				
				//获取每一页中的问卷题
				String exceli = strings[i];
				String[] strsi = exceli.split("\\|");
				
				Integer questionNum2 = Integer.valueOf(strsi[0]);  //题号
				Integer viewOrder = Integer.valueOf(strsi[1]);  //组织顺序
				//选项数量
				Integer optionNum = 0;  //默认0个选项
				if(StrUtils.isNotEmpty(strsi[2])){
					optionNum = Integer.valueOf(strsi[2]);
				}
				//问题类型
				Integer questionType = null;  
				if(StrUtils.isNotEmpty(strsi[3])){
					questionType = Integer.valueOf(strsi[3]);
				}
				
				String sqTitle = strsi[4];   //提干
				//是否必答
				Integer required = null;
				if(StrUtils.isNotEmpty(strsi[5])){
					required = Integer.valueOf(strsi[5]);
				}
				//是否允许自定义
				Integer isSelfDefine = null;
				if(StrUtils.isNotEmpty(strsi[6])){
					isSelfDefine = Integer.valueOf(strsi[6]);
				}
				String correctAnswer = strsi[7];  //正确答案
				
				String answerAnalysis = strsi[8];  //正确答案解析
				
				String titleImgUrl = strsi[9];   //题图
				String comment2 = strsi[10];   //备注

				
				int des = 11; //选项描述
				int tag = 12; //选项标签id
				int tagdes = 13; //标签描述
				int img = 14; //选项图
				int lin = 15;  //跳转
				int eed = 16;  //反馈
				int com = 17;  //选项备注
				int sco = 18; //选项分值
				
				for(int n=1;n<=optionNum; n++){
					
					String optionDes = strsi[des];  //选项描述
					
					Integer optionTagId = null;
					if(!strsi[tag].trim().equals("")){
						optionTagId = Integer.valueOf(strsi[tag]);   //选项标签id
					}
					
					Integer optionLink = null;
					if(!strsi[lin].trim().equals("")){
						try {
							optionLink = Integer.valueOf(strsi[lin]);  //选项1标签跳转
						} catch (Exception e) {
							e.printStackTrace();
							return AjaxResult.errorResult("选项跳转输入有误");
						}
					}
					
					String option1eedback = strsi[eed];  //选项反馈
					
					String optionScore_Str = strsi[sco];  //选项分值
					Integer optionScore = null;
					if(StrUtils.isNotEmpty(optionScore_Str)){
						optionScore = Integer.valueOf(optionScore_Str);
					}
					
					des = des +8;
					tag = tag +8;
					tagdes = tagdes+8;
					img = img + 8;
					lin = lin +8;
					eed = eed +8;
					com = com +8;
					sco = sco +8;
					
					Method desM = clz.getMethod("setOption"+n+"des", String.class);
					Method tagM = clz.getMethod("setOption"+n+"tagid", Integer.class);
					Method linM = clz.getMethod("setOption"+n+"link", Integer.class);
					Method feeM = clz.getMethod("setOption"+n+"feedback", String.class);
					Method scoM = clz.getMethod("setOption"+n+"PreferValue", Integer.class);
					
					desM.invoke(niSurveyQuestion, optionDes);
					tagM.invoke(niSurveyQuestion, optionTagId);
					linM.invoke(niSurveyQuestion, optionLink);
					feeM.invoke(niSurveyQuestion, option1eedback);
					scoM.invoke(niSurveyQuestion, optionScore);
					
				}
				
				Integer sqnid = niSurveyQuestionnaire.getSqnid();
				if(sqnid!=null){
					ajaxResult.put("sqnid", sqnid);
				}
				
				niSurveyQuestion.setSqnid(sqnid);			//调查问卷id
				niSurveyQuestion.setQuestionnum(questionNum2);
				niSurveyQuestion.setVieworder(viewOrder);
				niSurveyQuestion.setOptionnum(optionNum);
				niSurveyQuestion.setQuestiontype(questionType);
				niSurveyQuestion.setSqtitle(sqTitle);
				niSurveyQuestion.setRequired(required);
				niSurveyQuestion.setIsselfdefine(isSelfDefine);
				niSurveyQuestion.setCorrectanswer(correctAnswer);  //正确答案
				niSurveyQuestion.setAnswerAnalysis(answerAnalysis);  //正确答案解析
				
				niSurveyQuestion.setqImgUrl(titleImgUrl);  //提干图片
				niSurveyQuestion.setComment(comment2);
				
				niSurveyQuestionMapper.insert(niSurveyQuestion);
				
				//将刚添加的调查问卷表的信息查询
				NiSurveyQuestionnaire surveyQuestionnaire = niSurveyQuestionnaireMapper.selectByPrimaryKey(sqnid);
				
				//将该问卷表中的问题信息返回
				List<NiSurveyQuestion> niSurveyQuestions = niSurveyQuestionMapper.selectBySurveyQuestionnaireId(sqnid);
				
				hashMap.put("surveyQuestionnaire", surveyQuestionnaire);
				hashMap.put("niSurveyQuestions", niSurveyQuestions);
			}
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "上传成功!");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("===============>>录入的题目不符合要求,请再检查一遍!");
			return AjaxResult.errorResult("失败! 请检查问卷!");
		}
		return ajaxResult;
	}

	/**
	 * 批量修改问卷的状态
	 */
	public int updateStateByids(HashMap<String, Object> retMap) {
		int updateStateByids = niSurveyQuestionnaireMapper.updateStateByids(retMap);
		return updateStateByids;
	}

	/**
	 * 批量撤销问卷
	 */
	public int revokeStateByids(HashMap<String, Object> retMap) {
		int revokeStateByids = niSurveyQuestionnaireMapper.revokeStateByids(retMap);
		return revokeStateByids;
	}
	
	// 删除问卷
	public AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request) {
		
		String parameter = request.getParameter("sqnId_list");
		String[] sqnId_arr = parameter.split("!");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		
		for(int i=0;i<sqnId_arr.length;i++){
			sqnIds.add(Integer.valueOf(sqnId_arr[i]));
		}
		hashMap.put("sqnId_list", sqnIds);
		
		try {
			
			niSurveyQuestionnaireMapper.delete_Questionnaire_ByIdS_list(hashMap);
			niSurveyQuestionnaireMapper.delete_Question_ByIdS_list(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}

	
	public List<NiSurveyQuestionnaire> selectByNameLevelSubject( Page page) {  
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("page", page);             //MAP的话加这一句就OK  
//	  return userMapper.selectByNameLevelSubject(map);  
	  return niSurveyQuestionnaireMapper.selectByNameLevelSubject(map);
	}

	//投放管理添加问卷筛选
	public List<NiSurveyQuestionnaire> select_niSurveyQue_Dev() {
		
		List<NiSurveyQuestionnaire> select_niSurveyQue_Dev = niSurveyQuestionnaireMapper.select_niSurveyQue_Dev();
		
		return select_niSurveyQue_Dev;
	}

	//预览问卷
	public AjaxResult lookNiSurveyQuestionnaire(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		String sqnId = request.getParameter("sqnId");
		Integer sqn_Id = null;
		if(StrUtils.isNotEmpty(sqnId)){
			sqn_Id = Integer.valueOf(sqnId);
		}
		if(sqn_Id!=null){
			try {
				NiSurveyQuestionnaire selectByPrimaryKey = niSurveyQuestionnaireMapper.selectByPrimaryKey(sqn_Id);
				if(selectByPrimaryKey!=null){
					ajaxResult.put("niSurveyQuestionnaire", selectByPrimaryKey);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("出现异常!");
			}
			
		}
		
		return ajaxResult;
	}

	//保存编辑后的问卷
	@Transactional
	public AjaxResult editSurveyQuestionnaireSave(HttpServletRequest request) {
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
		 NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
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
			 niSurveyQuestionnaire.setSqnid(sqnId);
			 niSurveyQuestionnaire.setSqnname(qe_sqnname);
			 niSurveyQuestionnaire.setEditor(qe_editor);
			 niSurveyQuestionnaire.setRecommandqty(recommandQty);
			 niSurveyQuestionnaire.setSqnclassid(sqnClassId);
			 niSurveyQuestionnaire.setSqnsummary(qe_Profile);
			 niSurveyQuestionnaire.setPicpath(jdbcUrl);
			 niSurveyQuestionnaire.setPublishername(qe_publisherName);
			 
			 try {
				int updateByPrimaryKeySelective = niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
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

	//根据问卷id 查询问卷及问题内容
	public void findOneNiSurveyQuestionnaire(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {
		String sqnid = request.getParameter("sqnId");
		
		if(StrUtils.isNotEmpty(sqnid)){
			//查询问卷信息
			NiSurveyQuestionnaire niSurveyQuestionnaire = niSurveyQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(sqnid));
			if(niSurveyQuestionnaire!=null){
				model.addAttribute("niSurveyQuestionnaire", niSurveyQuestionnaire);
			}
			
			//封装所有数据的集合
			List<Object> arrayList = new ArrayList<Object>();

			//查询问题集合
			List<NiSurveyQuestion> niSurveyQuestionList = niSurveyQuestionMapper.selectBySurveyQuestionnaireId(Integer.valueOf(sqnid));
			if(niSurveyQuestionList.size()>0){
				
				//封装每个问题中所有数据
				for(int i=0; i <niSurveyQuestionList.size();i++){
					NiSurveyQuestion niSurveyQuestion = niSurveyQuestionList.get(i);
					Map<String, Object> queMap = new HashMap<String, Object>();
					
					if(niSurveyQuestion!=null){
						//问题id
						Integer sqid = niSurveyQuestion.getSqid();
						//显示题号
						Integer questionnum = niSurveyQuestion.getQuestionnum();
						//提干
						String sqtitle = niSurveyQuestion.getSqtitle();
						//提干图片
						String titleimgurl = niSurveyQuestion.getqImgUrl();
						//获取选项数量
						Integer optionnum = niSurveyQuestion.getOptionnum();  //选项数量
						//题目类型
						Integer questiontype = niSurveyQuestion.getQuestiontype(); 
						//多选限制 最低 最高
						Integer optMinNum = niSurveyQuestion.getOptMinNum();
						Integer optMaxNum = niSurveyQuestion.getOptMaxNum();
						
						List<Object> optionList = new ArrayList<Object>();
						//遍历取出每个选项描述
						for(int n=1;n<=optionnum;n++){
							
							String optionDesByIndex = niSurveyQuestion.getOptionDesByIndex(n);
							
							optionList.add(optionDesByIndex);
							
						}
						
						//封装每个问题
						queMap.put("sqid", sqid);
						queMap.put("questionnum", questionnum);
						queMap.put("optionnum", optionnum);
						queMap.put("sqtitle", sqtitle);
						queMap.put("optionList", optionList);
						queMap.put("titleimgurl", titleimgurl);
						
						queMap.put("questiontype", questiontype);
						queMap.put("optMinNum", optMinNum);
						queMap.put("optMaxNum", optMaxNum);
						
						
					}
					
					//将所有的问题添加到list集合中
					arrayList.add(queMap);
					
				}
				model.addAttribute("arrayList", arrayList);
				
			}
		}
	}

	//修改问卷内容信息
	@Transactional
	public AjaxResult updateNiSurveyQuestionnaire_edit(
			HttpServletRequest request, HttpServletResponse response,MultipartFile file,
			MultipartFile[] titlePic_arr) throws Exception{
		AjaxResult ajaxResult = new AjaxResult();
		
		//获取问卷内容信息
		String qe_sqnid = request.getParameter("qe_sqnid"); //问卷id
		String qe_sqnname = request.getParameter("qe_sqnname"); //问卷名称
		String qe_publisherName = request.getParameter("qe_publisherName"); //发布者
		String qe_editor = request.getParameter("qe_editor"); //编辑人
		String qe_collectNum = request.getParameter("qe_collectNum"); //建议回收数量
//		String qe_Classification = request.getParameter("qe_Classification"); //问卷分类
		String qe_Profile = request.getParameter("qe_Profile"); //问卷简介
		String showType_str = request.getParameter("showType"); // 页面显示方式  
		
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		//上传问卷标题图
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/sqn/"+formatDate+"/"+qe_sqnid);
		//获取图片的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");  
		
		//跟新问卷信息
		if(StrUtils.isEmpty(qe_sqnid)){
			return AjaxResult.errorResult("保存失败");
		}
		NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
		niSurveyQuestionnaire.setSqnid(Integer.valueOf(qe_sqnid));
		niSurveyQuestionnaire.setSqnname(qe_sqnname);
		niSurveyQuestionnaire.setPublishername(qe_publisherName);
		niSurveyQuestionnaire.setEditor(qe_editor);
		if(StrUtils.isNotEmpty(qe_collectNum)){
			niSurveyQuestionnaire.setRecommandqty(Integer.valueOf(qe_collectNum));
		}
		niSurveyQuestionnaire.setSqnsummary(qe_Profile);
		niSurveyQuestionnaire.setShowType(Integer.valueOf(showType_str));  //设置显示方式
		niSurveyQuestionnaire.setPicpath(jdbcUrl);
		
		try {
			niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
		} catch (Exception e1) {
			e1.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		
		//获取问题中 提干,选项信息
		String[] sqTitle_arr = request.getParameterValues("sqTitle");  //问题提干
		String[] questionType_edit_arr = request.getParameterValues("questionType_edit");  //问题类型
		
		String[] sqid_arr = request.getParameterValues("sqid");
		String[] optionnum_arr = request.getParameterValues("optionnum");
		String[] optionDes_arr = request.getParameterValues("optionDes");
		
		String[] optMinNum_arr = request.getParameterValues("optMinNum");
		String[] optMaxNum_arr = request.getParameterValues("optMaxNum");
		
		//检测一个问题是否对应一个提干
		if(sqid_arr.length != sqTitle_arr.length){
			return AjaxResult.errorResult("出现异常,题目数与提干数不匹配");
		}
		
		int m = 0;
		//遍历问卷id集合,提干集合
		for(int i=0;i<sqid_arr.length;i++){
			//通过反射获取问题对象				  
			Class<?> clz = Class.forName("com.newIns.model.survery.NiSurveyQuestion");
			NiSurveyQuestion niSurveyQuestion = (NiSurveyQuestion) clz.newInstance();
			
			String sqId = sqid_arr[i];
			String sqTitle = sqTitle_arr[i];
			String questionType_edit = questionType_edit_arr[i]; //问题类型
			
			String optMinNum_str = optMinNum_arr[i];
			String optMaxNum_str = optMaxNum_arr[i];
			
			MultipartFile titlePic = titlePic_arr[i];
			
			//上传问题提干图
			Map<String, Object> titleImgUpload = FileUtil.uploadFile(request, titlePic, "img/qn/sqn/"+formatDate+"/"+qe_sqnid);
			String titleJdbcUrl = (String) titleImgUpload.get("jdbcUrl");  
			
			//设置主键,提干
			if(StrUtils.isNotEmpty(sqId)){
				niSurveyQuestion.setSqid(Integer.valueOf(sqId));
			}
			niSurveyQuestion.setSqtitle(sqTitle);
			
			if(StrUtils.isNotEmpty(questionType_edit)){
				niSurveyQuestion.setQuestiontype(Integer.valueOf(questionType_edit));  //设置问题类型
			}
			
			niSurveyQuestion.setqImgUrl(titleJdbcUrl);
			//------------选项数量限制
			Integer optMinNum = null;
			if(StrUtils.isNotEmpty(optMinNum_str)){
				try {
					optMinNum = Integer.valueOf(optMinNum_str);
				} catch (Exception e) {
					return AjaxResult.errorResult("选项数限制录入有误");
				}
				niSurveyQuestion.setOptMinNum(optMinNum);
			}
			Integer optMaxNum = null;
			if(StrUtils.isNotEmpty(optMaxNum_str)){
				try {
					optMaxNum = Integer.valueOf(optMaxNum_str);
				} catch (Exception e) {
					return AjaxResult.errorResult("选项数限制录入有误");
				}
				niSurveyQuestion.setOptMaxNum(optMaxNum);
			}
			
			
			//选项数量
			Integer optionNum = null;
			//设置选项
			String optionnum = optionnum_arr[i];
			if(StrUtils.isNotEmpty(optionnum)){
				//选项数量
				optionNum = Integer.valueOf(optionnum);
				//遍历每个选项
				int y = 0;
				for(int n=m;n<optionNum+m;n++){
					String optionDes = optionDes_arr[n];
					
					Method desMethod = clz.getMethod("setOption"+(y+1)+"des", String.class);
					y++;
					
					desMethod.invoke(niSurveyQuestion, optionDes);
					
				}
				
				//遍历完第一题的选项后,下次遍历,从第二题选项开始遍历
				m = m + optionNum;
				
			}
			
			//---------多选限制条件校验
			if((optMaxNum != null) && (optMaxNum > optionNum) ){
				return AjaxResult.errorResult("最多选项设置已经超出了选项数量");
			}else if( (optMinNum!=null) && (optMinNum < 0) ){
				return AjaxResult.errorResult("最少选项设置错误");
			}else if((optMaxNum!=null) && (optMinNum!=null) && (optMaxNum < optMinNum)){
				return AjaxResult.errorResult("最少选项设置不能大于最多选项设置");
			}
				
			
			//执行更新
			try {
				niSurveyQuestionMapper.updateByPrimaryKeySelective(niSurveyQuestion);
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "保存成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保存失败");
			}
			
		}
		
		return ajaxResult;
	}

	//预览问卷跳转
	public void niSurveyQuestionnaireContent(HttpServletRequest request,
			Model model) {
		
		String sqnId = request.getParameter("sqnId");
		model.addAttribute("sqnId", sqnId);
		
		if(StrUtils.isNotEmpty(sqnId)){
			
			NiSurveyQuestionnaire niSurveyQuestionnaire = niSurveyQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(sqnId));
			if(niSurveyQuestionnaire!=null){
				String picpath = niSurveyQuestionnaire.getPicpath();
				model.addAttribute("picPath", picpath);
			}
		}
	}

	// 上传问卷
	public AjaxResult saveNiSurveyQuestionnaire(MultipartFile file,MultipartFile pic, HttpServletRequest request) {
		
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
		
		AjaxResult assExcelData_new = getAssExcelData_new(filePath,pic, ".xlsx",request);
		
		return assExcelData_new;
	}

	private AjaxResult getAssExcelData_new(String fileName,MultipartFile pic,String fileFileName,HttpServletRequest request) {
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
		
		Integer queNum = 0;
		Sheet sheet_0 = null;  // 第一 sheet页
		//获取 问卷题目数量
		try {
			sheet_0 = wb.getSheetAt(0);
			Row row2 = sheet_0.getRow(2);
			Cell cell2 = row2.getCell(1);     // 获取题目数量
			queNum = (int) cell2.getNumericCellValue();
			
			log.info("----------------->> 录入题目数量"+queNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//-------------------------读取问卷简介信息---begin---------------
		NiSurveyQuestionnaire niSurveyQuestionnaire = new NiSurveyQuestionnaire();
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
				
				if(x == 22){
					if(StrUtils.isEmpty(cellValue)){
						return AjaxResult.errorCode("问卷编辑人未录入!", 0);  // 设置0 作为标记, 一遍删除的时候 出现 问卷id 为 nudifined
					}
				}
				
				if(x == 0){
					niSurveyQuestionnaire.setSqnname(cellValue); // 问卷标题
				}else if(x == 1){
					niSurveyQuestionnaire.setSqnsummary(cellValue); // 问卷简介
				}else if(x == 2){
					niSurveyQuestionnaire.setQuestionQty(Integer.valueOf(cellValue));//题目数量
				}else if(x == 4){
					niSurveyQuestionnaire.setSqnCategory(Integer.valueOf(cellValue));  //调查问卷种类
				}else if(x == 5){
					
					if(StrUtils.isNotEmpty(cellValue)){
						niSurveyQuestionnaire.setKeyQuestionNum(Integer.valueOf(cellValue));//该问卷哪个题是核心题，需要统计结果，对应sqnCategory=2
					}
					
				}else if(x == 6){
					niSurveyQuestionnaire.setPublishername(cellValue);
				}else if(x == 7){
					niSurveyQuestionnaire.setPerface(cellValue); // 卷首语
				}else if(x == 8){
					if(StrUtils.isEmpty(cellValue)){
						cellValue = 100000+"";
					}
					niSurveyQuestionnaire.setRecommandqty(Integer.valueOf(cellValue));  //建议回收数量
				}else if(x == 10){
					niSurveyQuestionnaire.setSqnclassid(Integer.valueOf(cellValue)); // 问卷分类
				}else if(x == 22){
					niSurveyQuestionnaire.setEditor(cellValue); // 编辑人
				}
				
			}
		}
		niSurveyQuestionnaire.setStaus(1);
		niSurveyQuestionnaire.setCtime(new Date());
		
		Integer sqnId = null;
		try {
			niSurveyQuestionnaireMapper.insert(niSurveyQuestionnaire);
			
			sqnId = niSurveyQuestionnaire.getSqnid();
			
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorCode("第一页问卷简介部分录入有误,请检查,本次上传问卷将被删除", sqnId);
		}
		
		
		
		// ------------------------上传问卷 内图片-------------
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, pic, "img/qn/aqn/"+formatDate+"/"+sqnId);
		} catch (IllegalStateException | IOException e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传问卷内图失败, 本次上传问卷将被删除", sqnId);
		}
		
		String jdbcUrl_pic = (String) uploadFile.get("jdbcUrl");
		
		niSurveyQuestionnaire.setPicpath(jdbcUrl_pic);
		
		try {
			niSurveyQuestionnaireMapper.updateByPrimaryKeySelective(niSurveyQuestionnaire);
		} catch (Exception e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传问卷内图失败, 本次上传问卷将被删除", sqnId);
		}
		
		//-------------------------读取问题内容--------begin--------------
		Class<?> forName_NiSurveyQuestion = null;
		Constructor<?> constructor_NiSurveyQuestion = null;
		try {
			forName_NiSurveyQuestion = Class.forName("com.newIns.model.survery.NiSurveyQuestion");
			constructor_NiSurveyQuestion = forName_NiSurveyQuestion.getConstructor();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for(int m =1; m <(queNum+1); m++){
			Sheet sheet = wb.getSheetAt(m);
			
			NiSurveyQuestion niSurveyQuestion = null;
			try {
				niSurveyQuestion = (NiSurveyQuestion) constructor_NiSurveyQuestion.newInstance();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			Integer questiontype = null; // 问题类型  
			
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
					
					if(y == 1){
						niSurveyQuestion.setQuestionnum(Integer.valueOf(cellValue));
					}else if(y == 3){
						niSurveyQuestion.setVieworder(Integer.valueOf(cellValue));
					}else if(y == 5){
						niSurveyQuestion.setOptionnum(Integer.valueOf(cellValue));
					}else if(y == 7){   // 问题类型
						niSurveyQuestion.setQuestiontype(Integer.valueOf(cellValue));
						questiontype = Integer.valueOf(cellValue);
					}else if(y == 9){
						niSurveyQuestion.setSqtitle(cellValue);
					}else if(y == 11){
						niSurveyQuestion.setRequired(Integer.valueOf(cellValue));
					}else if(y == 13){
						niSurveyQuestion.setIsselfdefine(Integer.valueOf(cellValue));
					}else if(y == 15){
						niSurveyQuestion.setCorrectanswer(cellValue);
					}else if(y == 17){
						niSurveyQuestion.setAnswerAnalysis(cellValue);
					}
					
				}

			}
			
			// 判断问题类型
			if(questiontype == 1 || (questiontype == 2)){
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
									Method method_setOptiondes = forName_NiSurveyQuestion.getMethod("setOption"+x+"des",String.class);
//									Method method_setOptionscore = forName_NiSurveyQuestion.getMethod("setOption"+x+"score",Integer.class);
									
									if(y == 1){
										method_setOptiondes.invoke(niSurveyQuestion, cellValue);
									}
//									if(y == 15){
//										method_setOptionscore.invoke(niSurveyQuestion, Integer.valueOf(cellValue));
//									}
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
			}
			
			niSurveyQuestion.setSqnid(sqnId);
			try {
				niSurveyQuestionMapper.insert(niSurveyQuestion);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("测评第"+m+"问题录入有误,请检查, 本次上传问卷将被删除", sqnId);
			}
			
		}
		
		//-------------------------读取问题内容--------end--------------
		return AjaxResult.successResult("问卷上传成功");
		
	}

}
