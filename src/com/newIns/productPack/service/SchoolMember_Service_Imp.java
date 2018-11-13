package com.newIns.productPack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.newIns.dao.user.SchoolDictDao;
import com.newIns.productPack.dao.ProductPackage_Dao;
import com.newIns.productPack.dao.SchoolMember_Dao;
import com.newIns.productPack.dao.School_Product_Dao;
import com.newIns.productPack.pojo.ProductPackage;
import com.newIns.productPack.pojo.SchoolDict;
import com.newIns.productPack.pojo.SchoolMember;
import com.newIns.productPack.pojo.School_Product;
import com.newIns.productPack.pojo.vo.School_ProductPackage;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.DateUtil;
import com.newIns.tools.StrUtils;

@Service
public class SchoolMember_Service_Imp implements SchoolMember_Service{
	@Autowired
	private SchoolDictDao schoolDictDao;
	@Autowired
	private SchoolMember_Dao schoolMember_Dao;
	@Autowired
	private ProductPackage_Dao productPackage_Dao;
	@Autowired
	private School_Product_Dao sochool_Product_Dao;
	
	public void findAll_SchoolMember(HttpServletRequest request, Model model) {
		
		List<SchoolMember> findAll = schoolMember_Dao.findAll();
		model.addAttribute("SchoolMember_list", findAll);
	}

	public void add_SchoolMember(HttpServletRequest request, Model model) {
		
	}

	public AjaxResult save_SchoolMember(HttpServletRequest request, Model model) {
		String school_Id_Name_str = request.getParameter("schoolId_Name");
		String eTime_str = request.getParameter("eTime");
		
		String[] split = school_Id_Name_str.split("\\|");
		
		String schoolId_str = split[0];
		String schoolName = split[1];
		
		Integer schoolId = StrUtils.changeToInt(schoolId_str);
		
		SchoolMember schoolMember = new SchoolMember();
		
		schoolMember.setSchoolId(schoolId);
		schoolMember.setSchoolName(schoolName);
		schoolMember.setbTime(new Date());
		schoolMember.seteTime(new Date());
		
		try {
			schoolMember_Dao.insert(schoolMember);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		return AjaxResult.successResult();
	}

	public AjaxResult dele_SchoolMember(HttpServletRequest request, Model model) {
		String schoolId_arr_str = request.getParameter("schoolId_arr");
		
		String[] schoolId_arr = schoolId_arr_str.split("\\|");
		
		Map<String, Object> hashMap = new HashMap<>();
		
		List<Integer> arrayList = new ArrayList<>();
		
		if(schoolId_arr != null){
			
			for(int i=0 ; i<schoolId_arr.length ; i++){
				String schoolId_str = schoolId_arr[i];
				
				Integer schoolId = StrUtils.changeToInt(schoolId_str);
				
				arrayList.add(schoolId);
			}
			
		}
		
		hashMap.put("schoolId_list", arrayList);
		
		try {
			schoolMember_Dao.deleBy_KeyList(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		return AjaxResult.successResult("删除成功");
	}

	// 搜索学校
	public AjaxResult searchSchool_byName(HttpServletRequest request,Model model) {
		
		AjaxResult ajaxResult = new AjaxResult();
		
		String schoolName = request.getParameter("schoolName");
		
		// 根据学校名称模糊查询
		List<SchoolDict> SchoolDict_list = schoolMember_Dao.searchSchool_byName(schoolName);
		
		if(SchoolDict_list != null && SchoolDict_list.size()>0){
			ajaxResult.put("SchoolDict_list", SchoolDict_list);
			ajaxResult.put("success", true);
			
		}else{
			return AjaxResult.errorResult("未查询到学校");
		}
		
		return ajaxResult;
	}

	// 查询学校 产品包列表
	public void search_School_ProductPackage(HttpServletRequest request,Model model) {
		String schoolId_str = request.getParameter("schoolId");
		
		Integer schoolId = StrUtils.changeToInt(schoolId_str);
		
		// 根据学校id , 查询该学校所有产品包
		
		List<School_ProductPackage> listProductPackage = schoolMember_Dao.select_School_ProductPackage(schoolId);
		
		model.addAttribute("listProductPackage", listProductPackage);
		
		// 查询学校名称
		SchoolDict schoolDict = schoolDictDao.select_key(schoolId);
		
		model.addAttribute("schoolDict", schoolDict);
	}

	// 跳转到添加产品包页面
	public void add_School_ProductPackage(HttpServletRequest request,Model model) {
		
		String schoolId_str = request.getParameter("schoolId");
		model.addAttribute("schoolId", schoolId_str);
		
		HashMap<String, Object> dataMap = new HashMap<>();
		
		// 查询所有的产品包
		List<ProductPackage> list_ProductPackage = productPackage_Dao.list_ProductPackage(dataMap);
		
		model.addAttribute("list_ProductPackage", list_ProductPackage);
		
	}

	// 保存学校产品包 关联表
	public AjaxResult save_School_ProductPackage(HttpServletRequest request,
			Model model) {
		
		String schoolId_str = request.getParameter("schoolId");
		String packagetId_str = request.getParameter("packagetId");
		String eTime_str = request.getParameter("eTime");
		
		// 
		Integer schoolId = StrUtils.changeToInt(schoolId_str);
		Integer packagetId = StrUtils.changeToInt(packagetId_str);
		Date eTime = DateUtil.toUtilDateFromStrDateByFormat(eTime_str, "yyyy-MM-dd");
		
		School_Product school_Product = new School_Product();
		
		school_Product.setSchoolId(schoolId);
		school_Product.setPackagetId(packagetId);
		school_Product.setbTime(new Date());
		school_Product.seteTime(eTime);
		school_Product.setStatus(1);
		
		// 新增前, 先查询是否已经添加
		School_Product select_school_Product = sochool_Product_Dao.select_model(school_Product);
		
		if(select_school_Product!=null){
			return AjaxResult.errorResult("产品包已经添加");
		}
		
		// 执行新增
		try {
			sochool_Product_Dao.insert(school_Product);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}
	
}
