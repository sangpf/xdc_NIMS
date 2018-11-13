package com.newIns.service;

import java.util.List;

import com.newIns.model.assess.NiAssessModelMultiSum;

/**
 * 多维度测评模型
 * @author 11409
 *
 */
public interface NiAssessModelMultiSumService {

	//根据测评问卷id 查询多维度测评模型信息
	List<NiAssessModelMultiSum> selectByAqnId(Integer id);

	//根据 测评问卷id 纬度编号 编辑
	void updateByPrimaryKeySelective(NiAssessModelMultiSum niAssessModelMultiSum);
	
	
	
}
