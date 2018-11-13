package com.newIns.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiVqnclassDictMapper;
import com.newIns.model.NiVqnclassDict;
import com.newIns.service.NiVqnclassDictService;

@Service
public class NiVqnclassDictServiceImp implements NiVqnclassDictService{
	@Resource
	private NiVqnclassDictMapper niVqnclassDictMapper;
	
	/**
	 * 列表查询
	 */
	public List<NiVqnclassDict> selectList() {
		List<NiVqnclassDict> selectList = niVqnclassDictMapper.selectList();
		return selectList;
	}
	
}
