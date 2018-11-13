package com.newIns.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiDaily3updateWanxMapper;
import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxKey;
import com.newIns.service.NiDaily3updateWanxService;

@Service
public class NiDaily3updateWanxServiceImp implements NiDaily3updateWanxService{
	@Resource
	private NiDaily3updateWanxMapper daily3updateWanxMapper;
	/**
	 * 根据主键查询
	 */
	public NiDaily3updateWanx selectByPrimaryKey(NiDaily3updateWanxKey key) {
		NiDaily3updateWanx selectByPrimaryKey = daily3updateWanxMapper.selectByPrimaryKey(key);
		return selectByPrimaryKey;
	}
	
}
