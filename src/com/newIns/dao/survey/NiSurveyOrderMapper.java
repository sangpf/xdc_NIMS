package com.newIns.dao.survey;

import com.newIns.model.survery.NiSurveyOrder;
import com.newIns.model.survery.NiSurveyOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiSurveyOrderMapper {
    int countByExample(NiSurveyOrderExample example);

    int deleteByExample(NiSurveyOrderExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(NiSurveyOrder record);

    int insertSelective(NiSurveyOrder record);

    List<NiSurveyOrder> selectByExample(NiSurveyOrderExample example);

    NiSurveyOrder selectByPrimaryKey(Integer orderid);
    
    //根据订单表主键查询记录数
    int selectCountNumbysqnId(Integer id);

    int updateByExampleSelective(@Param("record") NiSurveyOrder record, @Param("example") NiSurveyOrderExample example);

    int updateByExample(@Param("record") NiSurveyOrder record, @Param("example") NiSurveyOrderExample example);

    int updateByPrimaryKeySelective(NiSurveyOrder record);

    int updateByPrimaryKey(NiSurveyOrder record);
}