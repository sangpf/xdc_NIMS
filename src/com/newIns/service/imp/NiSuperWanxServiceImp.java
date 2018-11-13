package com.newIns.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newIns.dao.NiSuperWanxMapper;
import com.newIns.model.page.NiSuperWanx;
import com.newIns.service.NiSuperWanxService;

@Service
public class NiSuperWanxServiceImp implements NiSuperWanxService{
	@Resource
	private NiSuperWanxMapper niSuperWanxMapper;
	
	//根据投放id查询
	public NiSuperWanx selectByDeliveryId(Integer delivery_id) {
		
		NiSuperWanx selectByDeliveryId = niSuperWanxMapper.selectByDeliveryId(delivery_id);
		
		return selectByDeliveryId;
	}
	
}
