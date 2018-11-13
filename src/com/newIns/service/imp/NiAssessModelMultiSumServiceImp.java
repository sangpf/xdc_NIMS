package com.newIns.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newIns.dao.assess.NiAssessModelMultiSumMapper;
import com.newIns.model.assess.NiAssessModelMultiSum;
import com.newIns.service.NiAssessModelMultiSumService;

/**
 * 多维度测评模型
 * @author 11409
 *
 */
@Service
public class NiAssessModelMultiSumServiceImp implements NiAssessModelMultiSumService{
	@Autowired
	private NiAssessModelMultiSumMapper niAssessModelMultiSumMapper;
	
	//根据测评问卷id 查询多维度测评模型信息
	public List<NiAssessModelMultiSum> selectByAqnId(Integer id) {
		return niAssessModelMultiSumMapper.findByAqnId(id);
		
	}

	//根据测评问卷id  纬度id 编辑
	public void updateByPrimaryKeySelective(
			NiAssessModelMultiSum niAssessModelMultiSum) {
		
		niAssessModelMultiSumMapper.updateByPrimaryKeySelective(niAssessModelMultiSum);
		
	}
	
	
	
}
