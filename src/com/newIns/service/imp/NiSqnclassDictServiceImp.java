package com.newIns.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiSqnclassDictMapper;
import com.newIns.model.NiSqnclassDict;
import com.newIns.service.NiSqnclassDictService;

@Service
public class NiSqnclassDictServiceImp implements NiSqnclassDictService{
	@Resource
	private NiSqnclassDictMapper niSqnclassDictMapper;
	//列表查询
	public List<NiSqnclassDict> selectList() {
		List<NiSqnclassDict> selectList = niSqnclassDictMapper.selectList();
		return selectList;
	}
	
}
