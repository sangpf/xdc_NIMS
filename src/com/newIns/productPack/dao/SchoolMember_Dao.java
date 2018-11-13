package com.newIns.productPack.dao;

import java.util.List;
import java.util.Map;

import com.newIns.productPack.pojo.SchoolDict;
import com.newIns.productPack.pojo.SchoolMember;
import com.newIns.productPack.pojo.vo.School_ProductPackage;

public interface SchoolMember_Dao {
	
	List<SchoolMember> findAll();

	void insert(SchoolMember schoolMember);

	void deleBy_KeyList(Map<String, Object> hashMap);

	List<SchoolDict> searchSchool_byName(String schoolName);

	List<School_ProductPackage> select_School_ProductPackage(Integer schoolId);
	
	
}
