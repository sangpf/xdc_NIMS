package com.newIns.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiOaManageUserMapper;
import com.newIns.model.NiOaManageUser;
import com.newIns.service.NiOaManageUserService;

@Service
public class NiOaManageUserServiceImp implements NiOaManageUserService{
	@Resource
	private NiOaManageUserMapper niUserMapper;
	
	public NiOaManageUser findByid(int userId) {
		NiOaManageUser findByid = niUserMapper.findByid(userId);
		return findByid;
	}

	public NiOaManageUser findByAccount(String account) {
		NiOaManageUser findByAccount = niUserMapper.findByAccount(account);
		return findByAccount;
	}

	public int addUser(NiOaManageUser recode) {
		int addUser = niUserMapper.addUser(recode);
		return addUser;
	}

	//后台管理根据用户id查询该用户所拥有的权限名称
	public List<String> findAuthNamebyUserId(int userId) {
		List<String> findAuthNamebyUserId = niUserMapper.findAuthNamebyUserId(userId);
		return findAuthNamebyUserId;
	}

}
