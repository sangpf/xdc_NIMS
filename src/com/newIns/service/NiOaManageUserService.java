package com.newIns.service;

import java.util.List;

import com.newIns.model.NiOaManageUser;

public interface NiOaManageUserService {
	
	NiOaManageUser findByid(int userId);
	
	NiOaManageUser findByAccount(String account);
	
	int addUser(NiOaManageUser recode);
	
	//根据后台管理用户的id查询该用户所拥有的权限名称
	List<String> findAuthNamebyUserId(int userId);
	
}
