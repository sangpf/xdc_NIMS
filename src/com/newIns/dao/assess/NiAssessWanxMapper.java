package com.newIns.dao.assess;

import com.newIns.model.assess.NiAssessWanx;

public interface NiAssessWanxMapper {
    int deleteByPrimaryKey(Integer deliveryid);

    int insert(NiAssessWanx record);

    int insertSelective(NiAssessWanx record);

    NiAssessWanx selectByPrimaryKey(Integer deliveryid);

    int updateByPrimaryKeySelective(NiAssessWanx record);

    int updateByPrimaryKey(NiAssessWanx record);
}