package com.newIns.dao;

import java.util.HashMap;
import java.util.List;

import com.newIns.model.NiUserBaseInformation;
import com.newIns.model.NiUserDetailInformation;

/**
 * @Description 用户管理DAO
 * @author wanquan
 * 
 */
public interface NiUserInformationMapper {
	//分页查询
	List<NiUserBaseInformation> selectList();
	//根据用户id查询用户数据
	NiUserDetailInformation selectByPrimaryKey(Integer userid);
	
	List<NiUserBaseInformation> selectListByParams(HashMap<String, Object> hashMap);
}
