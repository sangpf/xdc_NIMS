package com.newIns.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.assess.NiAssessWanxMapper;
import com.newIns.model.assess.NiAssessWanx;
import com.newIns.service.NiAssessWanxService;

@Service
public class NiAssessWanxServiceImp implements NiAssessWanxService{
	@Resource
	private NiAssessWanxMapper niAssessWanxMapper;
	//根据主键查询
	public NiAssessWanx selectByPrimaryKey(Integer deliveryid) {
		NiAssessWanx selectByPrimaryKey = niAssessWanxMapper.selectByPrimaryKey(deliveryid);
		return selectByPrimaryKey;
	}
	
	
}
