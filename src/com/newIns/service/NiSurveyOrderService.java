package com.newIns.service;

public interface NiSurveyOrderService {
	
    //根据订单表主键查询记录数
    int selectCountNumbysqnId(Integer id);
}
