package com.newIns.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiUserInformationMapper;
import com.newIns.model.NiUserBaseInformation;
import com.newIns.model.NiUserDetailInformation;
import com.newIns.service.NiUserInformationService;
@Service
public class NiUserInformationServiceImp implements NiUserInformationService{

	@Resource
	private NiUserInformationMapper userInformationMapper;
	
	@Override
	public List<NiUserBaseInformation> selectList() {
		List<NiUserBaseInformation> userInformationList = userInformationMapper.selectList();
		return userInformationList;
	}

	@Override
	public NiUserDetailInformation selectByPrimaryKey(Integer userid) {
		NiUserDetailInformation niDetailUserInformation = userInformationMapper.selectByPrimaryKey(userid);
		return niDetailUserInformation;
	}

	@Override
	public List<NiUserBaseInformation> selectListByParams(
			HashMap<String, Object> hashMap) {
		List<NiUserBaseInformation> userInformationList = userInformationMapper.selectListByParams(hashMap);
		return userInformationList;
	}

}
