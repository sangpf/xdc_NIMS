package com.newIns.dao;

import java.util.Map;

import com.newIns.model.page.NiSuperWanx;

/**
 * 超级列表页面
 * @author 11409
 *
 */
public interface NiSuperWanxMapper {

	//添加
	void insertSuperListCategory(NiSuperWanx niSuperWanx);

	//根据投放id查询
	NiSuperWanx selectByDeliveryId(Integer delivery_id);

	//根据联合主键查询  投放类型 , 投放id ,页面类型
	NiSuperWanx selectByPrimaryKey(Map<String, Object> retMap);
    
}