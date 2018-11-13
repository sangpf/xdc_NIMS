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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.NiAqnclassDictMapper;
import com.newIns.dao.assess.AssessModelBigFiveDao;
import com.newIns.dao.assess.AssessModelMBTIDao;
import com.newIns.dao.assess.AssessModelMultiRelationDao;
import com.newIns.dao.assess.AssessQuestionnaireDao;
import com.newIns.dao.assess.Assess_model_multi_combination_Dao;
import com.newIns.dao.assess.NiAssessModelMultiSumMapper;
import com.newIns.dao.assess.NiAssessModelSimpleMapper;
import com.newIns.dao.assess.NiAssessQuestionMapper;
import com.newIns.dao.assess.NiAssessQuestionnaireMapper;
import com.newIns.dao.user.AuthorDao;
import com.newIns.model.NiAqnclassDict;
import com.newIns.model.assess.AssessModelBigFive;
import com.newIns.model.assess.AssessModelMBTI;
import com.newIns.model.assess.AssessModelMbtiCombination;
import com.newIns.model.assess.AssessModelMultiRelation;
import com.newIns.model.assess.AssessQuestion;
import com.newIns.model.assess.AssessQuestionOption;
import com.newIns.model.assess.AssessQuestionWithOption;
import com.newIns.model.assess.AssessQuestionnaire;
import com.newIns.model.assess.AssessResult;
import com.newIns.model.assess.Assess_model_multi_combination;
import com.newIns.model.assess.Assess_model_multi_combination_relation;
import com.newIns.model.assess.NiAssessModelMultiSum;
import com.newIns.model.assess.NiAssessModelSimple;
import com.newIns.model.assess.NiAssessQuestion;
import com.newIns.model.assess.NiAssessQuestionnaire;
import com.newIns.model.assess.NiAssessQuestionnaireVO;
import com.newIns.model.user.Author;
import com.newIns.service.LoadAssessQuestionService;
import com.newIns.service.NiAssessQuestionnaireService;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.ExcelTool;
import com.newIns.tools.FileUtil;
import com.newIns.tools.RandomCodeUtil;
import com.newIns.tools.StrUtils;

@Service
@Transactional
public class NiAssessQuestionnaireServiceImp implements NiAssessQuestionnaireService{
	private static Logger log = Logger.getLogger(NiAssessQuestionnaireServiceImp.class); 
	@Resource
	private NiAssessQuestionnaireMapper niAssessQuestionnaireMapper;
	@Resource
	private NiAssessQuestionMapper niAssessQuestionMapper;
	@Resource
	private NiAssessModelSimpleMapper niAssessModelSimpleMapper;
	@Autowired
	private AssessQuestionnaireDao assQnDao;
	@Autowired
	private LoadAssessQuestionService loadSqService;
	@Autowired
	private NiAssessModelMultiSumMapper niAssessModelMultiSumMapper;
	@Autowired
	private AssessModelMultiRelationDao assessModelMultiRelationDao;
	@Autowired
	private AssessModelBigFiveDao AssessModelBigFiveDao;
	@Autowired
	private AssessModelMBTIDao assessModelMBTIDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private NiAqnclassDictMapper aqnclassDictMapper;
	@Autowired
	private Assess_model_multi_combination_Dao assess_model_multi_combination_Dao;
	
	//预览
	public Object loadAssessQuestionnaire(int aqnId) {
		log.info("This is service loadAssessQuestionnaire()");

		AssessQuestionnaire assQuestionnaire = assQnDao.loadAssessQuestionnaire(aqnId);
			assQuestionnaire.setSuccess("true");
			// 将ni_Assess_question表中某个问卷的所有问题信息读出
			List<AssessQuestionWithOption> assQuestionWithOptionList = loadSqService.loadAssessQuestionWithOption(aqnId);
			List<AssessQuestion> assQuestionList = new ArrayList<AssessQuestion>();// 对应接口的question列表

			for (int i = 0; i < assQuestionWithOptionList.size(); i++) {
				List<AssessQuestionOption> assOptionList = new ArrayList<AssessQuestionOption>();// 对应接口的问题选项列表
				AssessQuestionWithOption SqWithOptionTem = assQuestionWithOptionList
						.get(i);// 第i个问题的完整信息
				AssessQuestion SqTem = new AssessQuestion();
				// 从完整信息中抽取question相关的信息，付给question对象
				SqTem.setAqId(SqWithOptionTem.getAqId());
				SqTem.setQuestionNum(SqWithOptionTem.getQuestionNum());
				SqTem.setAqTitle(SqWithOptionTem.getAqTitle());
				SqTem.setQuestionType(SqWithOptionTem.getQuestionType());
				SqTem.setRequired(SqWithOptionTem.getRequired());
				SqTem.setOptionNum(SqWithOptionTem.getOptionNum());
				SqTem.setIsSelfDefine(SqWithOptionTem.getIsSelfDefine());
				SqTem.setCorrectAnswer(SqWithOptionTem.getCorrectAnswer());
				for (int j = 1; j <= SqTem.getOptionNum(); j++) {
					AssessQuestionOption SqOption = new AssessQuestionOption();
					SqOption.setOptionDes(SqWithOptionTem
							.getOptionDesByIndex(j));
					SqOption.setOptionLink(SqWithOptionTem
							.getOptionLinkByIndex(j));
					SqOption.setOptionFeedback(SqWithOptionTem
							.getOptionFeedbackByIndex(j));
					SqOption.setOptionOrder(j);
					assOptionList.add(j - 1, SqOption);
				}// 将选项付给相应的问题
				SqTem.setOptions(assOptionList);// 将选项列表添加到问题属性
				assQuestionList.add(i, SqTem);
			}
			assQuestionnaire.setQuestions(assQuestionList);// 将问题列表添加到问卷属性
			return assQuestionnaire;
	}
	
	
	
	//保存问卷
	public int insert(NiAssessQuestionnaire record) {
		// TODO Auto-generated method stub
		return 0;
	}

	//根据主键查询
	public NiAssessQuestionnaire selectByPrimaryKey(Integer sqnid) {
		NiAssessQuestionnaire selectByPrimaryKey = niAssessQuestionnaireMapper.selectByPrimaryKey(sqnid);
		return selectByPrimaryKey;
	}

	//列表查询
	public List<NiAssessQuestionnaireVO> selectList(
			HashMap<String, Object> hashMap) {
		List<NiAssessQuestionnaireVO> selectList = niAssessQuestionnaireMapper.selectList(hashMap);
		return selectList;
	}

	/**
	 * 读取测评问卷
	 */
	@Transactional
	public AjaxResult readinvestigationExcel(String fileName) throws Exception {
		AjaxResult ajaxResult = new AjaxResult();
		StringBuffer sb = ExcelTool.getAssExcelData(fileName, ".xlsx");
		String string = sb.toString();
		//截取字符串得到每页内容
		String[] strings = string.split("\\$#");
		log.info("截取的页数的长度====================>>"+strings.length);
		String excel0 = strings[0];  //第一页中内容
		//得到第一页中具体内容
		String[] strs = excel0.split("\\|");
		//得到题目数量
		Integer qNum = Integer.valueOf(strs[2]);
		
		NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		NiAssessQuestion niAssessQuestion = new NiAssessQuestion();
		
		//反射获取简单测评问卷对象-----------
		Class<?> forName = Class.forName("com.newIns.model.NiAssessModelSimple");
		NiAssessModelSimple niAssessModelSimple = (NiAssessModelSimple) forName.newInstance();
		
		//反射获取 测评多维度总分模型-----------com.newIns.model.NiAssessModelMultiSum
		Class<?> forName_NiAssessModelMultiSum = Class.forName("com.newIns.model.NiAssessModelMultiSum");
		NiAssessModelMultiSum niAssessModelMultiSum = (NiAssessModelMultiSum) forName_NiAssessModelMultiSum.newInstance();
		
		Integer aqnid = 0;
		try {
			//保存问卷表
			String aqnName = strs[0];  //测评问卷标题
			if(StrUtils.isEmpty(aqnName)){
				return AjaxResult.errorResult("失败 !问卷标题不能为空");
			}
			
			String sqnSummary = strs[1];//问卷简介
			if(StrUtils.isEmpty(sqnSummary)){
				return AjaxResult.errorResult("失败 !问卷简介不能为空");
			}
			
			Integer questionNum = null;
			if(!strs[2].trim().equals("")){
				questionNum = Integer.valueOf(strs[2]); //题目数量
			}else{
				return AjaxResult.errorResult("失败 !题目数量不能为空");
			}
			
			String picPath = strs[3];  // 问卷标题图
			//测评模型   aqnCategory
			String aqnCategory_Str = strs[4];
			Integer aqnCategory = null;
			if(StrUtils.isEmpty(aqnCategory_Str)){
				return AjaxResult.errorResult("请录入测评问卷模型");
			}else{
				aqnCategory = Integer.valueOf(aqnCategory_Str);
			}
			
			String publisherName = strs[5]; //出题人
			String perface = strs[6];  //卷首语
			
			Integer recommandQty = null;
			if(!strs[7].trim().equals("")){
				recommandQty = Integer.valueOf(strs[7]); //建议回收数量
			}
			
			Integer validation = null;
			if(!strs[8].trim().equals("")){
				validation = Integer.valueOf(strs[8]); // 有效性验证方式
			}
			
			Integer sqnClassId = null;
			if(!strs[9].trim().equals("")){
				sqnClassId = Integer.valueOf(strs[9]); //问卷分类ID
			}else{
				return AjaxResult.errorResult("失败 !问卷分类不能为空");
			}
			
			Integer tag1Id = null;
			if(!strs[10].trim().equals("")){
				 tag1Id = Integer.valueOf(strs[10]);
			}
			Integer tag2Id = null;
			if(!strs[12].trim().equals("")){
				tag2Id = Integer.valueOf(strs[12]);
			}
			Integer tag3Id = null;
			if(!strs[14].trim().equals("")){
				tag3Id = Integer.valueOf(strs[14]);
			}
			Integer tag4Id = null;
			if(!strs[16].trim().equals("")){
				tag4Id = Integer.valueOf(strs[16]);
			}
			Integer tag5Id = null;
			if(!strs[18].trim().equals("")){
				tag5Id = Integer.valueOf(strs[18]);
			}
			String epilogue = strs[20];  //结语
			
			String editor = strs[21]; //问卷编辑人
			if(StrUtils.isEmpty(editor)){
				return AjaxResult.errorResult("失败 !编辑人不能为空");
			}
			
			String comment = "";
			if(strs.length>22){
				 comment = strs[22]; //问卷备注 publisherId
			}
			
			
			niAssessQuestionnaire.setAqnname(aqnName);
			niAssessQuestionnaire.setAqnsummary(sqnSummary);
			niAssessQuestionnaire.setQuestionQty(questionNum);
			niAssessQuestionnaire.setPicpath(picPath);
			niAssessQuestionnaire.setPublishername(publisherName);
			niAssessQuestionnaire.setPerface(perface);
			niAssessQuestionnaire.setRecommandqty(recommandQty);
			niAssessQuestionnaire.setValidation(validation);
			niAssessQuestionnaire.setAqnclassid(sqnClassId);
			niAssessQuestionnaire.setTag1id(tag1Id);
			niAssessQuestionnaire.setTag2id(tag2Id);
			niAssessQuestionnaire.setTag3id(tag3Id);
			niAssessQuestionnaire.setTag4id(tag4Id);
			niAssessQuestionnaire.setTag5id(tag5Id);
			niAssessQuestionnaire.setEpilogue(epilogue);
			niAssessQuestionnaire.setEditor(editor);
			niAssessQuestionnaire.setComment(comment);
			niAssessQuestionnaire.setCtime(new Date());  //创建时间
			niAssessQuestionnaire.setStaus(1); //草稿状态
			//设置测评纬度模型
			niAssessQuestionnaire.setAqnCategory(aqnCategory);
			
			int insert = niAssessQuestionnaireMapper.insert(niAssessQuestionnaire);
			if(insert>0){
				log.info("添加测评问卷成功");
				aqnid = niAssessQuestionnaire.getAqnid();   //返回添加的测评问卷id
				ajaxResult.put("aqnId", aqnid);
				
				//简单测评模型的区间数
				Integer qujianCount = 0;
				String qujian = strs[23];  // 区间数量
				if(StrUtils.isNotEmpty(qujian)){
					qujianCount = Integer.valueOf(qujian);
				}
				
				if(aqnCategory_Str.trim().equals("0")){
					//简单测评模型
					//添加测评简单模型 ----------------------------
					int biginId = 24;
					int endId = 25;
					int resSum = 26;
					int resDel = 27;
					for(int i=0;i<qujianCount;i++){
						String randomCode = RandomCodeUtil.randomCode(8);
						Integer randomCodeid = 0;
						if(StrUtils.isNotEmpty(randomCode)){
							randomCodeid = Integer.valueOf(randomCode);
						}
						
						//开始
						String intervalBegin = strs[biginId].substring(0, strs[biginId].length()-2);
						Integer intervalBeginid = 0;
						if(StrUtils.isNotEmpty(intervalBegin)){
							intervalBeginid = Integer.valueOf(intervalBegin);
						}
						
						//结束
						String intervalEnd = strs[endId].substring(0, strs[endId].length()-2);
						Integer intervalEndid = 0;
						if(StrUtils.isNotEmpty(intervalEnd)){
							intervalEndid = Integer.valueOf(intervalEnd);
						}
						
						String resultSummary = strs[resSum];  //概述
						String resultDetail = strs[resDel];   //详情
						
						biginId += 4;
						endId   += 4;
						resSum  += 4;
						resDel  += 4;
						
						Method setAqnid = forName.getMethod("setAqnid", Integer.class);
						Method setIntervalid = forName.getMethod("setIntervalid", Integer.class);
						Method setIntervalbegin = forName.getMethod("setIntervalbegin", Integer.class);
						Method setIntervalend = forName.getMethod("setIntervalend", Integer.class);
						Method setResultsummary = forName.getMethod("setResultsummary", String.class);
						Method setResultdetail = forName.getMethod("setResultdetail");
						
						setAqnid.invoke(niAssessModelSimple, aqnid);
						setIntervalid.invoke(niAssessModelSimple, randomCodeid);
						setIntervalbegin.invoke(niAssessModelSimple, intervalBeginid);
						setIntervalend.invoke(niAssessModelSimple, intervalEndid);
						setResultsummary.invoke(niAssessModelSimple, resultSummary);
						setResultdetail.invoke(niAssessModelSimple, resultDetail);
						
						try {
							niAssessModelSimpleMapper.insert(niAssessModelSimple);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("======================>>保存简单测评模型失败!");
						}
						
					}
				}else if(aqnCategory_Str.trim().equals("1")){
					//多维度测评模型
					//多维度加总型模型-------------aqnCategory_Weizhi 代表纬度数量栏目的位置------------aqnCategory
					int aqnCategory_Weizhi = 24+(qujianCount*4);
					String aqnCategory_Number = strs[aqnCategory_Weizhi];  
					//多维度数量
					Integer aqnCategoryCount = 0;
					if(StrUtils.isNotEmpty(aqnCategory_Number)){
						aqnCategoryCount = Integer.valueOf(aqnCategory_Number);
					}
					
					int dimension_Weizhi = aqnCategory_Weizhi + 1;
					int resultSummary_Weizhi = aqnCategory_Weizhi + 2;
					int resultDetail_Weizhi = aqnCategory_Weizhi + 3;
					
					for(int i=0;i<aqnCategoryCount;i++){
//						String randomCode = RandomCodeUtil.randomCode(8);
//						Integer randomCodeid = 0;
//						if(StrUtils.isNotEmpty(randomCode)){
//							randomCodeid = Integer.valueOf(randomCode);
//						}
						
						String dimension_Str = strs[dimension_Weizhi];
						String resultSummary_Str = strs[resultSummary_Weizhi];
						String resultDetail_Str = strs[resultDetail_Weizhi];
						
						dimension_Weizhi += 3;
						resultSummary_Weizhi += 3;
						resultDetail_Weizhi += 3;
						
						Method setDimension = forName_NiAssessModelMultiSum.getMethod("setDimension", Integer.class);
						Method setResultSummary = forName_NiAssessModelMultiSum.getMethod("setResultSummary", String.class);
						Method setResultDetail = forName_NiAssessModelMultiSum.getMethod("setResultDetail", String.class);
						
						niAssessModelMultiSum.setAqnId(aqnid);
//						niAssessModelMultiSum.setDimension(randomCodeid);
						Integer dimension_Int = null;
						if(StrUtils.isNotEmpty(dimension_Str)){
							String dimension_New = dimension_Str.substring(0, dimension_Str.length()-2);
							dimension_Int = Integer.valueOf(dimension_New);
						}
						setDimension.invoke(niAssessModelMultiSum, dimension_Int);
						setResultSummary.invoke(niAssessModelMultiSum, resultSummary_Str);
						setResultDetail.invoke(niAssessModelMultiSum, resultDetail_Str);
						
						try {
							niAssessModelMultiSumMapper.insert(niAssessModelMultiSum);
						} catch (Exception e) {
							e.printStackTrace();
							return AjaxResult.errorResult("保存多维度总分模型失败!");
						}
					}
					
				}else{
					return AjaxResult.errorResult("请录入测评模型分类");
				}
				
				//添加测评问题
				for(int i =1 ;i<qNum+1;i++){  //qNum代表题目数量
					Class<?> clz = Class.forName("com.newIns.model.NiAssessQuestion");
					niAssessQuestion = (NiAssessQuestion) clz.newInstance();
					
					String exceli = strings[i];
					String[] strsi = exceli.split("\\|");
					
					Integer questionNum2 = Integer.valueOf(strsi[0]);
					Integer viewOrder = Integer.valueOf(strsi[1]);
					Integer optionNum = Integer.valueOf(strsi[2]);
					Integer questionType = Integer.valueOf(strsi[3]);
					String sqTitle = strsi[4];
					Integer required = Integer.valueOf(strsi[5]);
					Integer isSelfDefine = Integer.valueOf(strsi[6]);
					String correctAnswer = strsi[7];
					String titleImgUrl = strsi[8];
					String comment2 = strsi[9];
					String dimension_Str =strsi[10];  //题目所属纬度
					
					int desN = 11;
					int tagN = 12;
					int linN = 15;
					int feeN = 16;
					int scoN = 18;
					for(int n =1;n<=optionNum;n++){
						String optionDes = strsi[desN];  //选项1描述
						Integer optionTagId = null;
						if(!strsi[tagN].trim().equals("")){
							optionTagId = Integer.valueOf(strsi[tagN]);
						}
						Integer optionLink = null;
						if(!strsi[linN].trim().equals("")){
							optionLink = Integer.valueOf(strsi[linN]);  //选项1标签跳转
						}
						String optionFeedback = strsi[feeN];  //选项1反馈
						Integer optionScore = null;
						if(!strsi[scoN].trim().equals("")){
							optionScore = Integer.valueOf(strsi[scoN]);  //选项1分值
						}
						desN = desN+8;
						tagN = tagN+8;
						linN = linN+8;
						feeN = feeN+8;
						scoN = scoN+8;
						
						Method desM = clz.getMethod("setOption"+n+"des", String.class);
						Method tagM = clz.getMethod("setOption"+n+"tagid", Integer.class);
						Method linM = clz.getMethod("setOption"+n+"link", Integer.class);
						Method feeM = clz.getMethod("setOption"+n+"feedback", String.class);
						Method scoM = clz.getMethod("setOption"+n+"score", Integer.class);
						
						desM.invoke(niAssessQuestion, optionDes);
						tagM.invoke(niAssessQuestion, optionTagId);
						linM.invoke(niAssessQuestion, optionLink);
						feeM.invoke(niAssessQuestion, optionFeedback);
						scoM.invoke(niAssessQuestion, optionScore);
					}

					niAssessQuestion.setAqnid(aqnid);
					niAssessQuestion.setQuestionnum(questionNum2);
					niAssessQuestion.setVieworder(viewOrder);
					niAssessQuestion.setOptionnum(optionNum);
					niAssessQuestion.setQuestiontype(questionType);
					niAssessQuestion.setAqtitle(sqTitle);
					niAssessQuestion.setRequired(required);
					niAssessQuestion.setIsselfdefine(isSelfDefine);
					niAssessQuestion.setCorrectanswer(correctAnswer);
					niAssessQuestion.setqImgUrl(titleImgUrl);
					niAssessQuestion.setComment(comment2);
					
					//设置题目纬度
					if(StrUtils.isNotEmpty(dimension_Str)){
						niAssessQuestion.setDimension(Integer.valueOf(dimension_Str));
					}else{
						niAssessQuestion.setDimension(0);
					}
					
					int insert1 = niAssessQuestionMapper.insert(niAssessQuestion);
					if(insert1>0){
						log.info("================》》题目添加成功");
						ajaxResult.put("success", true);
						ajaxResult.put("msg", "上传成功!");
					}else{
						log.info("==================>>题目添加失败");
					}
				}
			}else{
				log.info("添加测评问卷失败");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("问卷录入有误,请检查");
		} 
		
		return ajaxResult;
	}

	//批量发布
	public int releaseNiAssessQuestionnaire(HashMap<String, Object> retMap) {
		int releaseNiAssessQuestionnaire = niAssessQuestionnaireMapper.releaseNiAssessQuestionnaire(retMap);
		return releaseNiAssessQuestionnaire;
	}

	//批量撤销
	public int revokeStateByids(HashMap<String, Object> retMap) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateByPrimaryKeySelective(NiAssessQuestionnaire record) {
		int updateByPrimaryKeySelective = niAssessQuestionnaireMapper.updateByPrimaryKeySelective(record);
		return updateByPrimaryKeySelective;
	}

	@Override
	public List<NiAssessQuestionnaire> select_niAssQue_Dev() {
		List<NiAssessQuestionnaire> select_niAssQue_Dev = niAssessQuestionnaireMapper.select_niAssQue_Dev();
		return select_niAssQue_Dev;
	}

	//保存编辑后的问卷
	@Transactional
	public AjaxResult editSurveyQuestionnaireSave(HttpServletRequest request) {
		AjaxResult ajaxResult = new AjaxResult();
		 String sqnId_show = request.getParameter("aqnId_show"); //问卷id
		 String qe_sqnname = request.getParameter("qe_sqnname");   //问卷名称
		 String qe_editor = request.getParameter("qe_editor");// 出题人
		 String qe_publisherName = request.getParameter("qe_publisherName");// 发布机构
		 String qe_collectNum = request.getParameter("qe_collectNum"); // 建议回收数量
		 String qe_Classification = request.getParameter("qe_Classification");// 问卷分类
		 String qe_Profile = request.getParameter("qe_Profile");// 问卷简介
		 String jdbcUrl = request.getParameter("jdbcUrl");//图片上传数据库路径
		 
		//封装数据
		 NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		 
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
			 niAssessQuestionnaire.setAqnid(sqnId);
			 niAssessQuestionnaire.setAqnname(qe_sqnname);
			 niAssessQuestionnaire.setEditor(qe_editor);
			 niAssessQuestionnaire.setRecommandqty(recommandQty);
			 niAssessQuestionnaire.setAqnclassid(sqnClassId);
			 niAssessQuestionnaire.setAqnsummary(qe_Profile);
			 niAssessQuestionnaire.setPicpath(jdbcUrl);
			 niAssessQuestionnaire.setPublishername(qe_publisherName);
			 
			 try {
				int updateByPrimaryKeySelective = niAssessQuestionnaireMapper.updateByPrimaryKeySelective(niAssessQuestionnaire);
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

	//问卷内容编辑, 问题内容编辑
	public void findOneNiAssessQuestionnaire(HttpServletRequest request,HttpServletResponse esponse,Model model) {
		
		String aqnId_str = request.getParameter("aqnId");
		model.addAttribute("aqnId", aqnId_str);
		
		if(StrUtils.isNotEmpty(aqnId_str)){
			Integer aqnId = Integer.valueOf(aqnId_str);
			//根据主键查询问卷信息
			NiAssessQuestionnaire niAssessQuestionnaire = niAssessQuestionnaireMapper.selectByPrimaryKey(aqnId);
			if(niAssessQuestionnaire!=null){
				model.addAttribute("niAssessQuestionnaire", niAssessQuestionnaire);
				
				// 查询 作者表 作者姓名
				Integer authorId = niAssessQuestionnaire.getAuthorId();
				
				List<Author> author_List = authorDao.author_List(new HashMap<String,Object>());
				
				model.addAttribute("authorId", authorId);
				model.addAttribute("author_List", author_List);
			}
			
			//封装所有数据的集合
			List<Object> arrayList = new ArrayList<Object>();
			
			//根据问卷id查询管理问题集合
			List<NiAssessQuestion> niAssessQuestionList = niAssessQuestionMapper.selectByAssessQuestionnaireId(aqnId);
			if(niAssessQuestionList.size()>0){
				//遍历获取每个问题
				for(int i=0;i<niAssessQuestionList.size();i++){
					NiAssessQuestion niAssessQuestion = niAssessQuestionList.get(i);
					Map<String, Object> queMap = new HashMap<String, Object>();
					
					//获取每个问题信息
					if(niAssessQuestion!=null){
						//问题id
						Integer aqid = niAssessQuestion.getAqid();
						//显示题号
						Integer questionnum = niAssessQuestion.getQuestionnum();
						//提干
						String aqtitle = niAssessQuestion.getAqtitle();
						//提干图片
						String titleimgurl = niAssessQuestion.getqImgUrl();
						//选项数量
						Integer optionnum = niAssessQuestion.getOptionnum();
						
						
						List<Object> optionList = new ArrayList<Object>();
						//遍历获取每个题目选项描述内容
						for(int n=1;n<=optionnum;n++){
							
							String optionDesByIndex = niAssessQuestion.getOptionDesByIndex(n);
							
							optionList.add(optionDesByIndex);
							
						}
						
						//封装每个问题
						queMap.put("aqid", aqid);
						queMap.put("questionnum", questionnum);
						queMap.put("optionnum", optionnum);
						queMap.put("aqtitle", aqtitle);
						queMap.put("optionList", optionList);
						queMap.put("titleimgurl", titleimgurl);
						
					}
					
					//将所有的问题添加到list集合中
					arrayList.add(queMap);
					
				}
				model.addAttribute("arrayList", arrayList);
				
			}
			
//			//根据测评问卷id 查询简单测评模型
//			List<NiAssessModelSimple> NiAssessModelSimple_list = niAssessModelSimpleMapper.selectByAqnId(Integer.valueOf(aqnId));
//			model.addAttribute("niAssessModelSimple_list", NiAssessModelSimple_list);
//			
//			//根据测评问卷id , 查询多维度测评模型
//			List<NiAssessModelMultiSum> NiAssessModelMultiSum_list = niAssessModelMultiSumMapper.findByAqnId(Integer.valueOf(aqnId));
//			model.addAttribute("niAssessModelMultiSum_list", NiAssessModelMultiSum_list);
			
		}
		
	}

	//保存编辑后的问卷
	public AjaxResult updateNiAssessQuestionnaire_edit(MultipartFile file,MultipartFile[] titlePic_arr,
			HttpServletRequest request) throws Exception {
		
		AjaxResult ajaxResult = new AjaxResult();
		
		//获取问卷内容信息
		String qe_sqnid = request.getParameter("qe_sqnid"); //问卷id
		String qe_sqnname = request.getParameter("qe_sqnname"); //问卷名称
		String qe_publisherName = request.getParameter("qe_publisherName"); //发布者
		String qe_editor = request.getParameter("qe_editor"); //编辑人
		String qe_collectNum = request.getParameter("qe_collectNum"); //建议回收数量
		String qe_Profile = request.getParameter("qe_Profile"); //问卷简介
		String authorId_str = request.getParameter("authorId"); //作者
		String subtitle = request.getParameter("subtitle"); //作者
		String showType_str = request.getParameter("showType"); // 显示方式
		String resutShowType_str = request.getParameter("resutShowType"); // 显示方式
		
		Integer showType = StrUtils.changeToInt(showType_str);
		Integer resutShowType = StrUtils.changeToInt(resutShowType_str);
		Integer authorId = StrUtils.changeToInt(authorId_str);
		
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		//上传问卷标题图
		Map<String, Object> uploadFile = FileUtil.uploadFile(request, file, "img/qn/aqn/"+formatDate+"/"+qe_sqnid);
		//获取图片的存储路径
		String jdbcUrl = (String) uploadFile.get("jdbcUrl");  
		
		//跟新问卷信息
		if(StrUtils.isEmpty(qe_sqnid)){
			return AjaxResult.errorResult("保存失败,问卷ID为空");
		}
		NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		niAssessQuestionnaire.setAqnid(Integer.valueOf(qe_sqnid));
		niAssessQuestionnaire.setAqnname(qe_sqnname);
		niAssessQuestionnaire.setPublishername(qe_publisherName);
		niAssessQuestionnaire.setEditor(qe_editor);
		
		if(StrUtils.isNotEmpty(qe_collectNum)){
			niAssessQuestionnaire.setRecommandqty(Integer.valueOf(qe_collectNum));
		}
		niAssessQuestionnaire.setAqnsummary(qe_Profile);
		niAssessQuestionnaire.setPicpath(jdbcUrl);
		niAssessQuestionnaire.setAuthorId(authorId);
		niAssessQuestionnaire.setSubtitle(subtitle);
		niAssessQuestionnaire.setShowType(showType);
		niAssessQuestionnaire.setResutShowType(resutShowType);
		
		try {
			niAssessQuestionnaireMapper.updateByPrimaryKeySelective(niAssessQuestionnaire);
			ajaxResult.put("success", true);
			ajaxResult.put("msg", "保存成功");
		} catch (Exception e1) {
			e1.printStackTrace();
			return AjaxResult.errorResult("保存失败");
		}
		
		//获取问题中 提干,选项信息
		String[] sqTitle_arr = request.getParameterValues("sqTitle");
		String[] sqid_arr = request.getParameterValues("sqid");
		String[] optionnum_arr = request.getParameterValues("optionnum");
		String[] optionDes_arr = request.getParameterValues("optionDes");
		
		//检测一个问题是否对应一个提干
		if(sqid_arr.length != sqTitle_arr.length){
			return AjaxResult.errorResult("出现异常,题目数与提干数不匹配");
		}
		
		int m = 0;
		//遍历问卷id集合,提干集合
		for(int i=0;i<sqid_arr.length;i++){
			//通过反射获取问题对象
			Class<?> clz = Class.forName("com.newIns.model.assess.NiAssessQuestion");
			NiAssessQuestion niAssessQuestion = (NiAssessQuestion) clz.newInstance();
			
			String sqId = sqid_arr[i];
			String sqTitle = sqTitle_arr[i];
			MultipartFile titlePicUpload = titlePic_arr[i];
			
			//上传问题提干图片
			Map<String, Object> titlePicUploadMap = FileUtil.uploadFile(request, titlePicUpload, "img/qn/aqn/"+formatDate+"/"+qe_sqnid);
			String titlePicJdbc = (String) titlePicUploadMap.get("jdbcUrl");
			
			//设置主键,提干
			if(StrUtils.isNotEmpty(sqId)){
				niAssessQuestion.setAqid(Integer.valueOf(sqId));
			}
			niAssessQuestion.setAqtitle(sqTitle);
			niAssessQuestion.setqImgUrl(titlePicJdbc);
			
			//设置选项
			String optionnum = optionnum_arr[i];
			if(StrUtils.isNotEmpty(optionnum)){
				//选项数量
				Integer optionNum = Integer.valueOf(optionnum);
				//遍历每个选项
				int y = 1;
				for(int n=m;n<optionNum+m;n++){
					String optionDes = optionDes_arr[n];
					
					Method desMethod = clz.getMethod("setOption"+y+"des", String.class);
					y++;
					
					desMethod.invoke(niAssessQuestion, optionDes);
					
				}
				
				//遍历完第一题的选项后,下次遍历,从第二题选项开始遍历
				m = m + optionNum;
				
			}
			
			//执行更新
			try {
				niAssessQuestionMapper.updateByPrimaryKeySelective(niAssessQuestion);
				ajaxResult.put("success", true);
				ajaxResult.put("msg", "保存成功");
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保存失败");
			}
			
		}
		return ajaxResult;
	}

	//预览问卷 查询问卷图片
	public void niAssesQue_View_Jump(HttpServletRequest request, Model model) {
		
		String aqnId = request.getParameter("aqnId");
		
		if(StrUtils.isNotEmpty(aqnId)){
			
			NiAssessQuestionnaire niAssessQuestionnaire = niAssessQuestionnaireMapper.selectByPrimaryKey(Integer.valueOf(aqnId));
			if(niAssessQuestionnaire!=null){
				String picpath = niAssessQuestionnaire.getPicpath();
				model.addAttribute("picPath", picpath);
			}
			
		}
		
		model.addAttribute("aqnId", aqnId);
		
	}

	// 页面录入保存测评问卷信息
	public AjaxResult savePageInputniAssessQuestionnaire(
			HttpServletRequest request, MultipartFile picFile) {
		
		String aqnName = request.getParameter("aqnName");
		String aqnSummary = request.getParameter("aqnSummary");
		String questionNum_Str = request.getParameter("questionNum");
		String aqnCategory_Str = request.getParameter("aqnCategory");
		String publisherName = request.getParameter("publisherName");
		String aqnClassId_Str = request.getParameter("aqnClassId");
		String editor = request.getParameter("editor");
		
		NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
		
		Integer questionNum = null;
		if(StrUtils.isNotEmpty(questionNum_Str)){
			questionNum = Integer.valueOf(questionNum_Str);
		}
		
		Integer aqnClassId = null;
		if(StrUtils.isNotEmpty(aqnClassId_Str)){
			aqnClassId = Integer.valueOf(aqnClassId_Str);
		}
		
		Integer aqnCategory = null;
		if(StrUtils.isNotEmpty(aqnCategory_Str)){
			aqnCategory = Integer.valueOf(aqnCategory_Str);
		}
		
		niAssessQuestionnaire.setAqnname(aqnName);  //问卷名称
		niAssessQuestionnaire.setAqnsummary(aqnSummary);  //问卷简介
		niAssessQuestionnaire.setQuestionQty(questionNum);// 题目数量
//		niAssessQuestionnaire.setPicpath(picPath);     //标题图
		niAssessQuestionnaire.setPublishername(publisherName); //发布者名称
//		niAssessQuestionnaire.setPerface(perface); 	 //卷首语
//		niAssessQuestionnaire.setRecommandqty(recommandQty); //建议回收数量
//		niAssessQuestionnaire.setValidation(validation); // 有效性验证方式
		niAssessQuestionnaire.setAqnclassid(aqnClassId); // 问卷分类id
//		niAssessQuestionnaire.setTag1id(tag1Id);   //问卷标签
//		niAssessQuestionnaire.setTag2id(tag2Id);
//		niAssessQuestionnaire.setTag3id(tag3Id);
//		niAssessQuestionnaire.setTag4id(tag4Id);
//		niAssessQuestionnaire.setTag5id(tag5Id);
//		niAssessQuestionnaire.setEpilogue(epilogue);  //结语
		niAssessQuestionnaire.setEditor(editor);   //编辑人
//		niAssessQuestionnaire.setComment(comment);  //备注
		niAssessQuestionnaire.setCtime(new Date());  //创建时间
		niAssessQuestionnaire.setStaus(1); //草稿状态
		//设置测评纬度模型
		niAssessQuestionnaire.setAqnCategory(aqnCategory);
		
		int insert = 0;
		try {
			insert = niAssessQuestionnaireMapper.insert(niAssessQuestionnaire);
		} catch (Exception e1) {
			e1.printStackTrace();
			return AjaxResult.errorResult("保存问卷失败");
		}
		
		Integer aqnId = null;
		if(insert>0){
			//添加成功
			aqnId = niAssessQuestionnaire.getAqnid();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String formatDate = sdf.format(new Date());
			//上传问卷图片
			try {
				FileUtil.uploadFile(request, picFile, "img/qn/sqn/"+formatDate+"/"+aqnId);
			} catch (IOException e) {
				e.printStackTrace();
				return AjaxResult.errorResult("保存图片失败");
			}
			return AjaxResult.successResult("保存成功");
		}else{
			return AjaxResult.errorResult("保存问卷失败");
		}
		
	}

    // 读取测评 新模版 
	@Transactional
	public AjaxResult getAssExcelData_new(HttpServletRequest request,String fileName,MultipartFile pic,String fileFileName){
		
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
		
		int assessType = 0;  // 测评模版类型
		
		//-------------------------读取问卷简介信息---begin---------------
		NiAssessQuestionnaire niAssessQuestionnaire = new NiAssessQuestionnaire();
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
				
				if(x == 4){
					if(StrUtils.isEmpty(cellValue)){
						return AjaxResult.errorCode("测评类型未录入!", 0);
					}else{
						assessType = Integer.valueOf(cellValue);
					}
				}else if(x == 21){
					if(StrUtils.isEmpty(cellValue)){
						return AjaxResult.errorCode("问卷编辑人未录入!", 0);
					}
				}
				
				if(x == 0){
					niAssessQuestionnaire.setAqnname(cellValue);  // 问卷标题
				}else if(x == 1){
					niAssessQuestionnaire.setAqnsummary(cellValue); // 问卷简介
				}else if(x == 2){
					niAssessQuestionnaire.setQuestionQty(Integer.valueOf(cellValue)); //题目数量
				}else if(x == 4){
					niAssessQuestionnaire.setAqnCategory(Integer.valueOf(cellValue)); // 测评类型
				}else if(x == 5){
					niAssessQuestionnaire.setPublishername(cellValue);
				}else if(x == 9){
					niAssessQuestionnaire.setAqnclassid(Integer.valueOf(cellValue)); // 问卷分类
				}else if(x == 21){
					niAssessQuestionnaire.setEditor(cellValue);  // 编辑人
				}
				
			}
		}
		niAssessQuestionnaire.setStaus(1);
		niAssessQuestionnaire.setCtime(new Date());
		niAssessQuestionnaire.setFilepath(fileName);
		niAssessQuestionnaire.setShowType(1);
		niAssessQuestionnaire.setResutShowType(1);
		
		Integer aqnId = null;
		try {
			niAssessQuestionnaireMapper.insert(niAssessQuestionnaire);
			
			aqnId = niAssessQuestionnaire.getAqnid();  // 新增的问卷id
			
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorCode("测评第一页问卷简介部分录入有误,请检查", aqnId);
		}
		
		
		//-------------------------读取问卷简介信息---end---------------
		
		//--------------------上传 问卷图片----------begin----
		String formatDate = new SimpleDateFormat("yyyyMM").format(new Date());
		
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, pic, "img/qn/aqn/"+formatDate+"/"+aqnId);
		} catch (IllegalStateException | IOException e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传图片失败", aqnId);
		}
		
		String jdbcUrl_pic = (String) uploadFile.get("jdbcUrl");
		
		niAssessQuestionnaire.setPicpath(jdbcUrl_pic);
		
		try {
			niAssessQuestionnaireMapper.updateByPrimaryKeySelective(niAssessQuestionnaire);
		} catch (Exception e2) {
			e2.printStackTrace();
			return AjaxResult.errorCode("上传图片失败", aqnId);
		}
		
		//--------------------- 读取测评模型-----------------begin-------------
		// 根据测评模版类型 读取相应的数据 
//		住：0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 4, 多维度组合输出模型
		if(assessType == 0){
			// 判断简单测评模型 行数是否正确
			Row row_40 = sheet_0.getRow(40);
			if(row_40 != null){
				Cell cell = row_40.getCell(0);
				if(cell != null){
					String stringCellValue = cell.getStringCellValue();
					if(!stringCellValue.trim().equals("区间编号")){
						return AjaxResult.errorCode("第一页:简单测评模型行数录入有误,从40行开始", aqnId);
					}
					
				}else{
					return AjaxResult.errorCode("第一页:简单测评模型行数录入有误,从40行开始", aqnId);
				}
				
			}else{
				return AjaxResult.errorCode("第一页:简单测评模型行数录入有误,从40行开始", aqnId);
			}
			
			
			// 通过字节码文件对象 获取 构造方法
			Constructor<?> constructor_NiAssessModelSimple = null;
			try {
				// 通过反射获取字节码文件对象
				Class<?> forName_NiAssessModelSimple = Class.forName("com.newIns.model.assess.NiAssessModelSimple");
				constructor_NiAssessModelSimple = forName_NiAssessModelSimple.getConstructor();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			//循环遍历 简单测评模型
			boolean flag = false;
			for(int x=41 ;x< 99 ;x++){
				
				NiAssessModelSimple niAssessModelSimple = null;
				try {
					niAssessModelSimple = (NiAssessModelSimple) constructor_NiAssessModelSimple.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				Row row = sheet_0.getRow(x);
				if(row == null){
					break;
				}else{
					
					for(int y=1;y<=3;y++){
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
							// 如果遍历到的下线为 null , 结束内外循环
							if( y == 1 && StrUtils.isEmpty(cellValue)){
								flag = true;
								break;
							}
							
							if( y == 1){ // 上限
								niAssessModelSimple.setIntervalbegin(Integer.valueOf(cellValue));
							}else if( y == 2){  //下限
								niAssessModelSimple.setIntervalend(Integer.valueOf(cellValue));
							}else if( y == 3){  //概览描述
								niAssessModelSimple.setResultsummary(cellValue);
							}
//								else if( y == 4){
//								niAssessModelSimple.setResultdetail("");    
//								// 注销原因 : 详细测评模型不从excel模板录入，保持的时候默认为空
//								}
							
						}else{
							// cell为null, 结束当前行
							flag = true;
							break;
						}
					}
				}
				// 控制外循环 , 控制行
				if(flag){
					break;
				}
				
				niAssessModelSimple.setAqnid(aqnId);
				niAssessModelSimple.setIntervalid(RandomCodeUtil.randomInt(8));
				// 执行保存 
				try {
					niAssessModelSimpleMapper.insert(niAssessModelSimple);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorCode("简单测评模型录入有误,请检查", aqnId);
				}
				
			}
			
		}else if(assessType == 1){
			// 判断多维度测评模型行数是否错误
			Row row_60 = sheet_0.getRow(60);
			if(row_60 != null){
				Cell cell = row_60.getCell(0);
				
				String stringCellValue = "";
				if(cell != null){
					switch(cell.getCellType()){
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						stringCellValue = cell.getNumericCellValue() + "";
						stringCellValue = stringCellValue.substring(0, stringCellValue.length()-2);
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						stringCellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						stringCellValue = "";
						break;
					}
					
					if(!stringCellValue.trim().equals("维度编号")){
						return AjaxResult.errorCode("多维度测评模型,行数录入有误", aqnId);
					}
					
				}else{
					return AjaxResult.errorCode("多维度测评模型,行数录入有误", aqnId);
				}
				
				
			}else{
				return AjaxResult.errorCode("多维度测评模型,行数录入有误", aqnId);
			}
			
			//多维度加总型模型
			//反射获取 测评多维度总分模型-----------com.newIns.model.NiAssessModelMultiSum
			Constructor<?> constructor_NiAssessModelMultiSum = null;
			try {
				Class<?> forName_NiAssessModelMultiSum = Class.forName("com.newIns.model.assess.NiAssessModelMultiSum");
				constructor_NiAssessModelMultiSum = forName_NiAssessModelMultiSum.getConstructor();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			boolean flag = false;
			// 循环多维度模型 
			for(int x=61 ; x<= 99; x++){
				NiAssessModelMultiSum niAssessModelMultiSum = null;
				try {
					niAssessModelMultiSum = (NiAssessModelMultiSum) constructor_NiAssessModelMultiSum.newInstance();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				Row row = sheet_0.getRow(x);

				if(row == null){
					//行结束, 结束外循环
					break;
				}else{
					
					for(int y=0 ; y<= 3;y++){
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
							
							if(StrUtils.isEmpty(cellValue)){
								flag = true;
								break;
							}
							
							if(y == 0){
								niAssessModelMultiSum.setDimension(Integer.valueOf(cellValue));
							}else if(y == 1){
								niAssessModelMultiSum.setResultSummary(cellValue);
							}else if(y == 2){
								//niAssessModelMultiSum.setResultDetail(cellValue);
							}else if(y == 3){
								niAssessModelMultiSum.setPriority(Integer.valueOf(cellValue));  // 优先级
							}
							
						}else{
							flag = true;
							break;
						}
						
					}
				}
				
				if(flag){
					break;
				}
				
				niAssessModelMultiSum.setAqnId(aqnId);
				try {
					niAssessModelMultiSumMapper.insert(niAssessModelMultiSum);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorCode("多维度加总型模型录入有误", aqnId);
				}
			}
			
			
			Constructor<?> constructor_AssessModelMultiRelation = null;
			try {
				Class<?> forName_AssessModelMultiRelation = Class.forName("com.newIns.model.assess.AssessModelMultiRelation");
				constructor_AssessModelMultiRelation = forName_AssessModelMultiRelation.getConstructor();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			boolean flag2 = false;
			// 循环 关联输出模型
			for(int x=61 ; x <= 99 ;x++){
				
				AssessModelMultiRelation assessModelMultiRelation = null;
				try {
					assessModelMultiRelation = (AssessModelMultiRelation) constructor_AssessModelMultiRelation.newInstance();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				Row row = sheet_0.getRow(x);

				if(row == null){
					break;
				}else{
					
					for(int y=4; y<=6; y++){
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
							
							if(StrUtils.isEmpty(cellValue)){
								flag2 = true;
								break;
							}
							
							
							if(y == 4){  // 关联输出
								assessModelMultiRelation.setRelation(cellValue);
							}else if(y == 5){
								assessModelMultiRelation.setResultSummary(cellValue);
							}else if(y == 6){
								//assessModelMultiRelation.setResultDetail(cellValue);
							}
							
						}else{
							flag = true;
							break;
						}
					}
				}
				
				
				if(flag2){
					break;
				}
				
				assessModelMultiRelation.setAqnId(aqnId);
				try {
					assessModelMultiRelationDao.insert(assessModelMultiRelation);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorCode("多维度加总型模型_关联输出录入有误", aqnId);
				}
			}
			
			
		}else if(assessType ==2){
			// 判断大五模型行数
			Row row_80 = sheet_0.getRow(80);
			if(row_80 == null){
				return AjaxResult.errorCode("大五模型行数录入有误", aqnId);
			}else{
				Cell cell = row_80.getCell(0);
				if( cell == null){
					return AjaxResult.errorCode("大五模型行数录入有误", aqnId);
				}else{
					String cellValue = "";
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
					
					if(!cellValue.trim().equals("维度编号")){
						return AjaxResult.errorCode("大五模型行数录入有误", aqnId);
					}
				}
			}
			
			
			// 大五模型 
			Constructor<?> constructor_AssessModelBigFive = null;
			try {
				Class<?> forName_AssessModelBigFive = Class.forName("com.newIns.model.assess.AssessModelBigFive");
				constructor_AssessModelBigFive = forName_AssessModelBigFive.getConstructor();
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			boolean flag = false;
			for(int x = 81; x <= 999; x++){
				
				AssessModelBigFive assessModelBigFive = null;
				try {
					assessModelBigFive = (AssessModelBigFive) constructor_AssessModelBigFive.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				Row row = sheet_0.getRow(x);
				if(row == null){
					break;
				}else{
					
					for(int y = 0 ; y <= 5; y++){
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
							
							if(StrUtils.isEmpty(cellValue)){
								flag = true;
								break;
							}
							
							if(y == 0){
								assessModelBigFive.setDimension(Integer.valueOf(cellValue));
							}else if(y == 1){
								// 区间编号
								assessModelBigFive.setSection(RandomCodeUtil.randomInt(8));
							}else if(y == 2){
								assessModelBigFive.setLowNum(Integer.valueOf(cellValue));
							}else if(y == 3){
								assessModelBigFive.setUpperNum(Integer.valueOf(cellValue));
							}else if(y == 4){
								assessModelBigFive.setResultSummary(cellValue);
							}else if(y == 5){
								//assessModelBigFive.setResultDetail(cellValue);
							}
							
						}else{
							flag = true;
							break;
						}
						
					}
					
				}
				
				if(flag){
					break;
				}
				
				assessModelBigFive.setAqnId(aqnId);
				try {
					AssessModelBigFiveDao.insert(assessModelBigFive);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorCode("大五模型录入有误", aqnId);
				}
			}
			
		}else if(assessType ==3){
			// MBTI模型
			// 判断开始行数
			Row row_100 = sheet_0.getRow(100);
			if(row_100 != null){
				Cell cell = row_100.getCell(0);
				
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
					if(!cellValue.trim().equals("一级维度编号")){
						return AjaxResult.errorCode("MBTI模型开始行数有误", aqnId);
					}
					
				}else{
					return AjaxResult.errorCode("MBTI模型开始行数有误", aqnId);
				}
			}else{
				return AjaxResult.errorCode("MBTI模型开始行数有误", aqnId);
			}
			
			Constructor<?> constructor_AssessModelMBTI = null;
			try {
				Class<?> forName_AssessModelMBTI = Class.forName("com.newIns.model.assess.AssessModelMBTI");
				constructor_AssessModelMBTI = forName_AssessModelMBTI.getConstructor();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
			boolean flag = false;
			for(int x = 101; x <= 200 ; x++){
				
				AssessModelMBTI assessModelMBTI = null;
				try {
					assessModelMBTI = (AssessModelMBTI) constructor_AssessModelMBTI.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Row row = sheet_0.getRow(x);
				
				if(row == null){
					break;
				}else{
					
					for(int y = 0 ; y <= 2 ; y++){
						
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
							
							if(StrUtils.isEmpty(cellValue)){
								flag = true;
								break;
							}
							
							if(y == 0){
								assessModelMBTI.setDimension(Integer.valueOf(cellValue));
							}else if(y == 1){
								assessModelMBTI.setLeftSecond(cellValue);
							}else if(y == 2){
								assessModelMBTI.setRightSecond(cellValue);
							}
						}else{
							flag = true;
							break;
						}
					}
				}
				
			if(flag){
				break;
			}
			
			assessModelMBTI.setAqnId(aqnId);
			try {
				assessModelMBTIDao.insert(assessModelMBTI);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("MBTI模型录入有误", aqnId);
			}
		  }
			
			//  MBTI 组合输出
			Constructor<?> constructor_AssessModelMbtiCombination=null;
			try {
				Class<?> forName_AssessModelMbtiCombination = Class.forName("com.newIns.model.assess.AssessModelMbtiCombination");
				constructor_AssessModelMbtiCombination = forName_AssessModelMbtiCombination.getConstructor();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			boolean flag2 = false;
			for(int x = 101 ; x <= 200 ; x++){
				AssessModelMbtiCombination assessModelMbtiCombination = null;
				try {
					assessModelMbtiCombination = (AssessModelMbtiCombination) constructor_AssessModelMbtiCombination.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Row row = sheet_0.getRow(x);

				if(row == null){
					break;
				}else{
					
					for(int y = 3; y <= 5 ; y++){
						
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
							
							if(StrUtils.isEmpty(cellValue)){
								flag2 = true;
								break;
							}
							
							if(y == 3){
								assessModelMbtiCombination.setDimensionStr(cellValue);
							}else if(y == 4){
								assessModelMbtiCombination.setResultSummary(cellValue);
							}else if(y == 5){
								//assessModelMbtiCombination.setResultDetail(cellValue);
							}
							
						}else{
							flag2 = true;
							break;
						}
					}
				}
				
			if(flag2){
				break;
			}
			assessModelMbtiCombination.setAqnId(aqnId);
			// 新增
			try {
				assessModelMBTIDao.insertMbtiCombination(assessModelMbtiCombination);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("MBTI模型_输出模型录入有误", aqnId);
			}
		  }
		}else if(assessType == 4){
			// 多维度组合输出模型
			// 判断开始行数
			Row row_120 = sheet_0.getRow(120);
			if(row_120 != null){
				Cell cell = row_120.getCell(0);
				
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
					if(!cellValue.trim().equals("维度编号")){
						return AjaxResult.errorCode("excel模版,多维度组合输出模型开始行数有误", aqnId);
					}
					
				}else{
					return AjaxResult.errorCode("excel模版,多维度组合输出模型开始行数有误", aqnId);
				}
			}else{
				return AjaxResult.errorCode("excel模版,多维度组合输出模型开始行数有误", aqnId);
			}
			
			// --------------- 录入 多维度组合输出模型 begin  ----------
			boolean flag = false;
			for(int x = 121; x <= 200 ; x++){
				
				Assess_model_multi_combination assess_model_multi_combination = new Assess_model_multi_combination();
				
				Row row = sheet_0.getRow(x);
				
				if(row == null){
					break;
				}else{
					
					for(int y = 0 ; y <= 3 ; y++){
						
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
							// 如果没录入维度编号 , 结束内外循环
							if(y == 0 && StrUtils.isEmpty(cellValue)){
								flag = true;
								break;
							}
							
							if(y == 0){
								assess_model_multi_combination.setDimension(Integer.valueOf(cellValue));
							}else if(y == 1){
								assess_model_multi_combination.setSection(cellValue);
							}else if(y == 2){
								assess_model_multi_combination.setLowNum(Integer.valueOf(cellValue));
							}else if(y == 3){
								assess_model_multi_combination.setUpperNum(Integer.valueOf(cellValue));
							}
						}else{
							flag = true;
							break;
						}
					}
				}
				
			if(flag){
				break;
			}
			
			assess_model_multi_combination.setAqnId(aqnId);
			try {
				assess_model_multi_combination_Dao.insert(assess_model_multi_combination);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("多维度组合输出模型录入有误", aqnId);
			}
		  }
			
			// --------------- 录入 多维度组合输出模型 end ----------
			
			// --------------- 录入 多维度组合输出模型  管理关系  begin ----------
			boolean flag2 = false;
			for(int x = 121; x <= 200 ; x++){
				Assess_model_multi_combination_relation assess_model_multi_combination_relation = new Assess_model_multi_combination_relation();
				Row row = sheet_0.getRow(x);
				
				if(row == null){
					break;
				}else{
					for(int y = 4 ; y <= 6 ; y++){
						
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
							// 如果没录入管理关系 , 结束内外循环
							if(y == 4 && StrUtils.isEmpty(cellValue)){
								flag2 = true;
								break;
							}
							
							if(y == 4){
								assess_model_multi_combination_relation.setDimension_combination(cellValue);
							}else if(y == 5){
								assess_model_multi_combination_relation.setResultSummary(cellValue);
							}else if(y == 6){
								assess_model_multi_combination_relation.setResultDetail("");
							}
							
						}else{
							flag2 = true;
							break;
						}
					}
				}
			
			if(flag2){
				break;
			}
			
			assess_model_multi_combination_relation.setAqnId(aqnId);
			try {
				assess_model_multi_combination_Dao.insert_Assess_model_multi_combination_relation(assess_model_multi_combination_relation);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("多维度组合输出模型关联输出录入有误", aqnId);
			}
		  }
			
			// --------------- 录入 多维度组合输出模型  管理关系  end ----------
		
		}else{
			return AjaxResult.errorCode("测评模型录入错误!", aqnId);
		}
		//--------------------- 读取测评模型-----------------end-------------
		
		
		//-------------------------读取问题内容--------begin--------------
		Class<?> forName_NiAssessQuestion = null;
		Constructor<?> constructor_NiAssessQuestion = null;
		try {
			forName_NiAssessQuestion = Class.forName("com.newIns.model.assess.NiAssessQuestion");
			constructor_NiAssessQuestion = forName_NiAssessQuestion.getConstructor();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		for(int m =1; m <(queNum+1); m++){
			Sheet sheet = wb.getSheetAt(m);
			
			NiAssessQuestion niAssessQuestion = null;
			try {
				niAssessQuestion = (NiAssessQuestion) constructor_NiAssessQuestion.newInstance();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			
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
						niAssessQuestion.setQuestionnum(Integer.valueOf(cellValue));
					}else if(y == 3){
						niAssessQuestion.setVieworder(Integer.valueOf(cellValue));
					}else if(y == 5){
						niAssessQuestion.setOptionnum(Integer.valueOf(cellValue));
					}else if(y == 7){
						niAssessQuestion.setQuestiontype(Integer.valueOf(cellValue));
					}else if(y == 9){
						niAssessQuestion.setAqtitle(cellValue);
					}else if(y == 11){
						niAssessQuestion.setRequired(Integer.valueOf(cellValue));
					}else if(y == 13){
						niAssessQuestion.setIsselfdefine(Integer.valueOf(cellValue));
					}else if(y == 15){
						niAssessQuestion.setCorrectanswer(cellValue);
					}else if(y == 17){
						niAssessQuestion.setDimension(Integer.valueOf(cellValue));
					}else if(y == 19){
						niAssessQuestion.setDimensionStr(cellValue);
					}
					
				}

			}
			
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
								Method method_setOptiondes = forName_NiAssessQuestion.getMethod("setOption"+x+"des",String.class);
								Method method_setOptionscore = forName_NiAssessQuestion.getMethod("setOption"+x+"score",Integer.class);
								
								if(y == 1){
									method_setOptiondes.invoke(niAssessQuestion, cellValue);
								}
								if(y == 11){
									method_setOptionscore.invoke(niAssessQuestion, Integer.valueOf(cellValue));
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
			
			niAssessQuestion.setAqnid(aqnId);
			try {
				niAssessQuestionMapper.insert(niAssessQuestion);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorCode("测评第"+m+"问题录入有误,请检查", aqnId);
			}
			
		}
		
		//-------------------------读取问题内容--------end--------------
		
		return AjaxResult.successResult("导入问卷成功");
	
	}

	@Transactional
	public AjaxResult deleteSurveyQuestionnaire(HttpServletRequest request) {
		
		String sqnid_str = request.getParameter("sqnId_list");
		String[] sqnId_arr = sqnid_str.split("!");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<Integer> sqnIds = new ArrayList<Integer>();
		
		for(int i=0;i<sqnId_arr.length;i++){
			sqnIds.add(Integer.valueOf(sqnId_arr[i]));
		}
		hashMap.put("sqnId_list", sqnIds);
		try {
			
			niAssessQuestionnaireMapper.delete_AssessQuestionnaireByids(hashMap);
			log.info("===============================>>批量删除问卷");
			
			niAssessQuestionnaireMapper.delete_QuestionByIds(hashMap);
			
			return AjaxResult.successResult("删除问卷成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult("删除失败");
		}
		
	}

	// 跳转到 测评结果页
	public void editAssessResult(HttpServletRequest request,Model model) {
		
		String aqnId_str = request.getParameter("aqnId");
		String aqnCategory_str = request.getParameter("aqnCategory");
		String dimension_str = request.getParameter("dimension");  // 维度编号
		String dimensionStr = request.getParameter("dimensionStr"); // 组合维度
		String section_str = request.getParameter("section");  // 区间编号   
		
		Integer section = null;
		if(StrUtils.isNotEmpty(section_str)){
			section = Integer.valueOf(section_str);
		}
		Integer dimension = null;
		if(StrUtils.isNotEmpty(dimension_str)){
			dimension = Integer.valueOf(dimension_str);
		}
		
		String resultDetail = "";

		if(StrUtils.isNotEmpty(aqnCategory_str) && StrUtils.isNotEmpty(aqnId_str)){
			Integer aqnCategory = Integer.valueOf(aqnCategory_str);
			Integer aqnId = Integer.valueOf(aqnId_str);
			
			
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("aqnId", aqnId);
			
			// 查询测评结果 详细信息
			// 0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 
			if(aqnCategory == 0){
				dataMap.put("section", section);
				// 查询简单测评模型表  
				List<NiAssessModelSimple> selectAssessResult = niAssessModelSimpleMapper.selectDetailResult(dataMap);
				if(selectAssessResult != null && selectAssessResult.size()>0){
					NiAssessModelSimple niAssessModelSimple = selectAssessResult.get(0);
					if(niAssessModelSimple != null){
						resultDetail = niAssessModelSimple.getResultdetail();
					}
				}
				
				
			}else if(aqnCategory == 1){
				// 多维度 
				if(dimension != null){
					dataMap.put("dimension", dimension);
					
					List<NiAssessModelMultiSum> listNiAssessModelMultiSum = niAssessModelMultiSumMapper.selectDetailResult(dataMap);
					if(listNiAssessModelMultiSum!=null && listNiAssessModelMultiSum.size()>0){
						NiAssessModelMultiSum niAssessModelMultiSum = listNiAssessModelMultiSum.get(0);
						if(niAssessModelMultiSum != null){
							resultDetail = niAssessModelMultiSum.getResultDetail();
						}
					}
					
				}else if(StrUtils.isNotEmpty(dimensionStr)){
					// 多维度关联
					dataMap.put("dimensionStr", dimensionStr);
					
					List<AssessModelMultiRelation> listAssessModelMultiRelation = niAssessModelMultiSumMapper.selectDetailRelationResult(dataMap);
					if(listAssessModelMultiRelation!=null && listAssessModelMultiRelation.size()>0){
						AssessModelMultiRelation assessModelMultiRelation = listAssessModelMultiRelation.get(0);
						if(assessModelMultiRelation != null){
							resultDetail = assessModelMultiRelation.getResultDetail();
						}
					}
				}
				
			}else if(aqnCategory == 2){
				// 2 大五模型
				dataMap.put("dimension", dimension);
				dataMap.put("section", section);
				
				List<AssessModelBigFive> listAssessModelBigFive = AssessModelBigFiveDao.selectDetailResult(dataMap);
				if(listAssessModelBigFive!=null && listAssessModelBigFive.size()>0){
					AssessModelBigFive assessModelBigFive = listAssessModelBigFive.get(0);
					if(assessModelBigFive != null){
						resultDetail = assessModelBigFive.getResultDetail();
					}
				}
				
			}else if(aqnCategory == 3){
				// 3 MBTI模型 
				dataMap.put("dimensionStr", dimensionStr);
				
				List<AssessModelMbtiCombination> listAssessModelMbtiCombination = assessModelMBTIDao.selectDetailRelationResult(dataMap);
				
				if(listAssessModelMbtiCombination != null && listAssessModelMbtiCombination.size()>0){
					AssessModelMbtiCombination assessModelMbtiCombination = listAssessModelMbtiCombination.get(0);
					if(assessModelMbtiCombination != null){
						resultDetail = assessModelMbtiCombination.getResultDetail();
					}
				}
				
			}else if(aqnCategory == 4){
				// 查询测评结果 返回到页面
				dataMap.put("dimension_combination", dimensionStr);
				List<Assess_model_multi_combination_relation> assess_model_multi_combination_relation_list = 
						assess_model_multi_combination_Dao.getAssess_model_multi_combination_relation_list(dataMap);
				
				if(assess_model_multi_combination_relation_list != null && assess_model_multi_combination_relation_list.size()>0){
					Assess_model_multi_combination_relation assess_model_multi_combination_relation = assess_model_multi_combination_relation_list.get(0);
					resultDetail = assess_model_multi_combination_relation.getResultDetail();
				}
				
			}else{
				log.info("============================>> 编辑测评结果页  问卷类型错误");
			}
			
		}else{
			log.info("==========================>>查询测评结果时出错, 问卷id, 或问卷类型缺失");
		}
		
		model.addAttribute("aqnId", aqnId_str);
		model.addAttribute("dimension", dimension);
		model.addAttribute("dimensionStr", dimensionStr);
		model.addAttribute("section", section);
		model.addAttribute("resultDetail", resultDetail);
		model.addAttribute("aqnCategory", aqnCategory_str);
		
	}

	// 结果页 列表
	public void editAssessResult_list(HttpServletRequest request, Model model) {
		// 查询测评问卷类型 
		String aqnId_str = request.getParameter("aqnId");
		model.addAttribute("aqnId", aqnId_str);
		
		if(StrUtils.isNotEmpty(aqnId_str)){
			
			Integer aqnId = Integer.valueOf(aqnId_str);
			
			NiAssessQuestionnaire assessQuestionnaire = niAssessQuestionnaireMapper.selectByPrimaryKey(aqnId);
			
			// 根据不同的类型 , 查询测评结果
			if(assessQuestionnaire != null){
				// 测评类型
				Integer aqnCategory = assessQuestionnaire.getAqnCategory();
				Integer resutShowType = assessQuestionnaire.getResutShowType();
				
				List<AssessResult> AssessResult_list = null;
				
				// 住：0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 4, 多维度组合输出模型
				if(aqnCategory == 0){
					// 查询简单测评模型表
					AssessResult_list = niAssessModelSimpleMapper.selectAssessResult(aqnId);
					
				}else if(aqnCategory == 1){
					// 多维度
					AssessResult_list = niAssessModelMultiSumMapper.selectAssessResult(aqnId);
					List<AssessResult> AssessResult_list_1 = assessModelMultiRelationDao.selectAssessResult(aqnId);
					
					AssessResult_list.addAll(AssessResult_list_1);
					
				}else if(aqnCategory == 2){
					
					AssessResult_list = AssessModelBigFiveDao.selectAssessResult(aqnId);
					
				}else if(aqnCategory == 3){
					
					AssessResult_list = assessModelMBTIDao.selectAssessResult(aqnId);
					
				}else if(aqnCategory == 4){
					
					AssessResult_list = assess_model_multi_combination_Dao.selectAssessResult(aqnId);
					
				}else{
					log.info("============================>> 编辑测评结果页  问卷类型错误");
				}
				
				model.addAttribute("AssessResult_list", AssessResult_list);
				model.addAttribute("aqnCategory", aqnCategory);
				model.addAttribute("resutShowType", resutShowType);
			}
			
		}else{
			log.info("============================>> 编辑测评结果页 问卷id为空 ");
		}
	}

	// 保存 测评结果编辑 
	public AjaxResult saveAssessResult(HttpServletRequest request, Model model) {
		
		String aqnId_str = request.getParameter("aqnId");
		String dimension_str = request.getParameter("dimension");
		String dimensionStr_str = request.getParameter("dimensionStr");
		String section_str = request.getParameter("section");
		String resultDetail_str = request.getParameter("resultDetail");
		String aqnCategory_str = request.getParameter("aqnCategory");
		
		Integer dimension = null;
		if(StrUtils.isNotEmpty(dimension_str)){
			dimension = Integer.valueOf(dimension_str);
		}
		
		Integer section = null;
		if(StrUtils.isNotEmpty(section_str)){
			section = Integer.valueOf(section_str);
		}
		
		Map<String, Object> dataMap = new HashMap<>();
		// 判断 测评类型 
		if(StrUtils.isNotEmpty(aqnCategory_str) && StrUtils.isNotEmpty(aqnId_str)){
			Integer aqnId = Integer.valueOf(aqnId_str);
			Integer aqnCategory = Integer.valueOf(aqnCategory_str);
			
			dataMap.put("aqnId", aqnId);
			dataMap.put("resultDetail", resultDetail_str);
			
			// 0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 
			if(aqnCategory == 0){
				// 查询简单测评模型表
				dataMap.put("section", section);
				
				try {
					niAssessModelSimpleMapper.updateDetailResult(dataMap);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult();
				}
				
			}else if(aqnCategory == 1){
				
				if(dimension != null){
					dataMap.put("dimension", dimension);

					try {
						niAssessModelMultiSumMapper.updateDetailResult(dataMap);
					} catch (Exception e) {
						e.printStackTrace();
						return AjaxResult.errorResult();
					}
					
				}else if(StrUtils.isNotEmpty(dimensionStr_str)){
					dataMap.put("dimensionStr", dimensionStr_str);
					
					try {
						niAssessModelMultiSumMapper.updateDetailRelationResult(dataMap);
					} catch (Exception e) {
						e.printStackTrace();
						return AjaxResult.errorResult();
					}
				}
				
			}else if(aqnCategory == 2){
				dataMap.put("section", section);
				dataMap.put("dimension", dimension);
				
				try {
					AssessModelBigFiveDao.updateDetailResult(dataMap);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult();
				}
				
			}else if(aqnCategory == 3){
				dataMap.put("dimensionStr", dimensionStr_str);
				
				try {
					assessModelMBTIDao.udpateDetailRelationResult(dataMap);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult();
				}
				
			}else if(aqnCategory == 4){
				
				dataMap.put("dimensionStr", dimensionStr_str);
				
				try {
					assess_model_multi_combination_Dao.udpateDetailRelationResult(dataMap);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult();
				}
				
			}else{
				log.info("============================>> 编辑测评结果页  问卷类型错误");
			}
			
		}
		
		return AjaxResult.successResult();
	}


	// 跳转到测评结果 区间编辑
	public void EditAssessResult_Section(HttpServletRequest request, Model model) {

		String aqnId_str = request.getParameter("aqnId");
		String aqnCategory_str = request.getParameter("aqnCategory");
		
		String dimension_str = request.getParameter("dimension");  // 维度编号
		String dimensionStr = request.getParameter("dimensionStr"); // 组合维度编号
		String section_str = request.getParameter("section");  // 区间编号
		
		Integer section = null;
		if(StrUtils.isNotEmpty(section_str)){
			section = Integer.valueOf(section_str);
		}
		Integer dimension = null;
		if(StrUtils.isNotEmpty(dimension_str)){
			dimension = Integer.valueOf(dimension_str);
		}
		
		//封装修改数据
		AssessResult assessResult = null;
		
		if(StrUtils.isNotEmpty(aqnCategory_str) && StrUtils.isNotEmpty(aqnId_str)){
			Integer aqnCategory = Integer.valueOf(aqnCategory_str);
			Integer aqnId = Integer.valueOf(aqnId_str);
			
			
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("aqnId", aqnId);
			
			assessResult = new AssessResult();
			// 查询测评结果 详细信息
			// 0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 
			if(aqnCategory == 0){
				dataMap.put("section", section);
				// 查询简单测评模型表  
				List<NiAssessModelSimple> selectAssessResult = niAssessModelSimpleMapper.selectDetailResult(dataMap);
				if(selectAssessResult != null && selectAssessResult.size()>0){
					NiAssessModelSimple niAssessModelSimple = selectAssessResult.get(0);
					if(niAssessModelSimple != null){
						
						Integer intervalBegin = niAssessModelSimple.getIntervalbegin();
						Integer intervalEnd = niAssessModelSimple.getIntervalend();
						String resultSummary = niAssessModelSimple.getResultsummary();
						
						assessResult.setIntervalBegin(intervalBegin);
						assessResult.setIntervalEnd(intervalEnd);
						assessResult.setResultSummary(resultSummary);
					}
				}
				
				
			}else if(aqnCategory == 1){
				// 多维度 
				if(dimension != null){
					dataMap.put("dimension", dimension);
					
					List<NiAssessModelMultiSum> listNiAssessModelMultiSum = niAssessModelMultiSumMapper.selectDetailResult(dataMap);
					if(listNiAssessModelMultiSum!=null && listNiAssessModelMultiSum.size()>0){
						NiAssessModelMultiSum niAssessModelMultiSum = listNiAssessModelMultiSum.get(0);
						if(niAssessModelMultiSum != null){
							
							String resultSummary = niAssessModelMultiSum.getResultSummary();
							Integer priority = niAssessModelMultiSum.getPriority();
							
							assessResult.setResultSummary(resultSummary);
							assessResult.setPriority(priority);
						}
					}
					
				}else if(StrUtils.isNotEmpty(dimensionStr)){
					// 多维度关联
					dataMap.put("dimensionStr", dimensionStr);
					
					List<AssessModelMultiRelation> listAssessModelMultiRelation = niAssessModelMultiSumMapper.selectDetailRelationResult(dataMap);
					if(listAssessModelMultiRelation!=null && listAssessModelMultiRelation.size()>0){
						AssessModelMultiRelation assessModelMultiRelation = listAssessModelMultiRelation.get(0);
						if(assessModelMultiRelation != null){
							String resultSummary = assessModelMultiRelation.getResultSummary();
							
							assessResult.setResultSummary(resultSummary);
						}
					}
				}
				
			}else if(aqnCategory == 2){
				// 2 大五模型
				dataMap.put("dimension", dimension);
				dataMap.put("section", section);
				
				List<AssessModelBigFive> listAssessModelBigFive = AssessModelBigFiveDao.selectDetailResult(dataMap);
				if(listAssessModelBigFive!=null && listAssessModelBigFive.size()>0){
					AssessModelBigFive assessModelBigFive = listAssessModelBigFive.get(0);
					if(assessModelBigFive != null){
						
						Integer intervalBegin = assessModelBigFive.getUpperNum();
						Integer intervalEnd = assessModelBigFive.getLowNum();
						String resultSummary = assessModelBigFive.getResultSummary();
						
						assessResult.setIntervalBegin(intervalBegin);
						assessResult.setIntervalEnd(intervalEnd);
						assessResult.setResultSummary(resultSummary);
					}
				}
				
			}else if(aqnCategory == 3){
				// 3 MBTI模型 
				dataMap.put("dimensionStr", dimensionStr);
				
				List<AssessModelMbtiCombination> listAssessModelMbtiCombination = assessModelMBTIDao.selectDetailRelationResult(dataMap);
				
				if(listAssessModelMbtiCombination != null && listAssessModelMbtiCombination.size()>0){
					AssessModelMbtiCombination assessModelMbtiCombination = listAssessModelMbtiCombination.get(0);
					if(assessModelMbtiCombination != null){
						
						String resultSummary = assessModelMbtiCombination.getResultSummary();
						
						assessResult.setResultSummary(resultSummary);
					}
				}
				
			}else if(aqnCategory == 4){
				dataMap.put("dimension_combination", dimensionStr);
				
				List<Assess_model_multi_combination_relation> assess_model_multi_combination_relation_list = 
						assess_model_multi_combination_Dao.getAssess_model_multi_combination_relation_list(dataMap);
				if(assess_model_multi_combination_relation_list != null && assess_model_multi_combination_relation_list.size()>0){
					Assess_model_multi_combination_relation assess_model_multi_combination_relation = assess_model_multi_combination_relation_list.get(0);
					if(assess_model_multi_combination_relation != null){
						String resultSummary = assess_model_multi_combination_relation.getResultSummary();
						assessResult.setResultSummary(resultSummary);
						
					}
				}
				
			}else{
				log.info("============================>> 编辑测评结果页  问卷类型错误");
			}
			
		}else{
			log.info("==========================>>查询测评结果时出错, 问卷id, 或问卷类型缺失");
		}
		
		model.addAttribute("aqnId", aqnId_str);
		model.addAttribute("dimension", dimension);
		model.addAttribute("dimensionStr", dimensionStr);
		model.addAttribute("section", section);
		model.addAttribute("assessResult", assessResult);
		model.addAttribute("aqnCategory", aqnCategory_str);
		
	}

	// 保存测评结果编辑
	public AjaxResult save_AssessResult_Section(HttpServletRequest request,Model model) {
		
		String aqnId_str = request.getParameter("aqnId");
		String aqnCategory_str = request.getParameter("aqnCategory");
		String dimension_str = request.getParameter("dimension");  // 维度编号
		String dimensionStr = request.getParameter("dimensionStr"); // 组合维度编号
		String section_str = request.getParameter("section");  // 区间编号
		
		String intervalBegin_str = request.getParameter("intervalBegin");
		String intervalEnd_str = request.getParameter("intervalEnd");  
		String resultSummary = request.getParameter("resultSummary");  
		String priority_str = request.getParameter("priority");  
		
		Integer section = null;
		if(StrUtils.isNotEmpty(section_str)){
			section = Integer.valueOf(section_str);
		}
		Integer dimension = null;
		if(StrUtils.isNotEmpty(dimension_str)){
			dimension = Integer.valueOf(dimension_str);
		}
		
		Integer intervalBegin = null;
		Integer intervalEnd = null;
		Integer priority = null;
		//封装修改数据
		AssessResult assessResult = null;
		
		if(StrUtils.isNotEmpty(aqnCategory_str) && StrUtils.isNotEmpty(aqnId_str)){
			Integer aqnCategory = Integer.valueOf(aqnCategory_str);
			Integer aqnId = Integer.valueOf(aqnId_str);
			intervalBegin = StrUtils.changeToInt(intervalBegin_str);
			intervalEnd = StrUtils.changeToInt(intervalEnd_str);
			priority = StrUtils.changeToInt(priority_str);
			
			assessResult = new AssessResult();
			
			assessResult.setAqnId(aqnId);
			assessResult.setDimension(dimension);
			assessResult.setDimensionStr(dimensionStr);
			assessResult.setSection(section);
			assessResult.setIntervalBegin(intervalBegin);
			assessResult.setIntervalEnd(intervalEnd);
			assessResult.setPriority(priority);
			assessResult.setResultSummary(resultSummary);
			
			
			// 查询测评结果 详细信息
			// 0简单测评模型；1多维度加总型模型;2 大五模型; 3 MBTI模型 
			if(aqnCategory == 0){
				//
				try {
					niAssessModelSimpleMapper.update_AssessResult(assessResult);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("编辑简单测评模型失败");
				}
				
			}else if(aqnCategory == 1){
				// 多维度 
				if(dimension != null){
					try {
						niAssessModelMultiSumMapper.update_AssessResult(assessResult);
					} catch (Exception e) {
						e.printStackTrace();
						return AjaxResult.errorResult("编辑多维度测评模型失败");
					}
					
				}else if(StrUtils.isNotEmpty(dimensionStr)){
					// 多维度关联
					try {
						niAssessModelMultiSumMapper.update_AssessResult_Relation(assessResult);
					} catch (Exception e) {
						e.printStackTrace();
						return AjaxResult.errorResult("编辑多维度测评模型失败");
					}
				}
			}else if(aqnCategory == 2){
				// 2 大五模型
				try {
					AssessModelBigFiveDao.update_AssessResult(assessResult);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("编辑大五测评模型失败");
				}
				
			}else if(aqnCategory == 3){
				// 3 MBTI模型 
				try {
					assessModelMBTIDao.update_AssessResult(assessResult);
				} catch (Exception e) {
					e.printStackTrace();
					return AjaxResult.errorResult("编辑MBTI测评模型失败");
				}
				
			}else if(aqnCategory == 4){
				
				assess_model_multi_combination_Dao.updateDetailRelationResult_assessResult(assessResult);
				
			}else{
				log.info("============================>> 编辑测评结果页  问卷类型错误");
			}
			
		}else{
			log.info("==========================>>查询测评结果时出错, 问卷id, 或问卷类型缺失");
		}
		return AjaxResult.successResult();
	}

	// 上传测评问卷
	public AjaxResult NiAssessQuestionnaireSave(HttpServletRequest request,
			MultipartFile file, MultipartFile pic) {
		
		String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		// 上传 excel文件
		Map<String, Object> uploadFile = null;
		try {
			uploadFile = FileUtil.uploadFile(request, file, "qn/aqn/"+nowDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 读取模版中内容, 创建测评问卷
		String realPath = (String) uploadFile.get("realPath");
		
		// 读取新测评模版
		AjaxResult assExcelData_new = getAssExcelData_new(request,realPath, pic, ".xlsx");
		
		return assExcelData_new;
	}

	// 列表查询测评问卷
	public void list_AssessQuestionnaire(HttpServletRequest request, Model model) {
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		log.info("========================>>分页查询调查问卷信息");
		String sqnpublish = request.getParameter("sqnpublish");   //查询类别
		String sqnNameOrpublisherName = request.getParameter("sqnNameOrpublisherName");
		String qnclassId_Str = request.getParameter("qnclassid");
		String surQueryStats = request.getParameter("surQueryStats");

		if( StrUtils.isNotEmpty(sqnpublish) && StrUtils.isNotEmpty(sqnNameOrpublisherName)){
			//判断选择的是文件名称，还是调查机构名称
			if(sqnpublish.trim().equals("1")){
				//问卷名称
				hashMap.put("sqnName", sqnNameOrpublisherName.trim());
			}else if(sqnpublish.trim().equals("0")){
				hashMap.put("aqnId", sqnNameOrpublisherName.trim());
			}
		}
		
		Integer qnclassId = StrUtils.changeToInt(qnclassId_Str);
		Integer staus = StrUtils.changeToInt(surQueryStats);
		
		hashMap.put("qnclassId", qnclassId);
		hashMap.put("staus", staus);
		
		List<NiAqnclassDict> niAqnclassDict_list = aqnclassDictMapper.selectList();
		
		List<NiAssessQuestionnaireVO> selectList = niAssessQuestionnaireMapper.selectList(hashMap);
		
		model.addAttribute("NiAssessQuestionnaires", selectList);
		model.addAttribute("niAqnclassDict_list", niAqnclassDict_list);
		
	}



	@Override
	public NiAssessQuestionnaire getOne_key(HttpServletRequest request) {
		
		String sqnId_str = request.getParameter("sqnId");
		Integer sqnId = StrUtils.changeToInt(sqnId_str);
		
		NiAssessQuestionnaire selectByPrimaryKey = niAssessQuestionnaireMapper.selectByPrimaryKey(sqnId);
		
		return selectByPrimaryKey;
	}
	
}
