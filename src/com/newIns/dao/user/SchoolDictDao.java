package com.newIns.dao.user;

import com.newIns.productPack.pojo.SchoolDict;

/**
 * 一一对应学校字典表
 * @author sang
 *
 */
public interface SchoolDictDao {
	
	
	SchoolDict select_key(Integer schoolId);
	
	
}
