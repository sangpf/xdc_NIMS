package com.newIns.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.survey.NiSurveyOrderMapper;
import com.newIns.service.NiSurveyOrderService;

@Service
public class NiSurveyOrderServiceImp implements NiSurveyOrderService{
	
	@Resource
	private NiSurveyOrderMapper niSurveyOrderMapper;
	
	/**
	 * 根据主键查询记录数
	 */
	public int selectCountNumbysqnId(Integer id) {
		int selectCountNumbysqnId = niSurveyOrderMapper.selectCountNumbysqnId(id);
		return selectCountNumbysqnId;
	}

}
