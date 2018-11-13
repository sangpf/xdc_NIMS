package com.newIns.dao.assess;

import com.newIns.model.assess.NiAssessOrder;
import com.newIns.model.assess.NiAssessOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiAssessOrderMapper {
	
    //根据订单表主键查询记录数
    int selectCountNumbysqnId(Integer id);
	
    int get_count_order_by_deliveryId(Integer deliveryId);
    
    int countByExample(NiAssessOrderExample example);

    int deleteByExample(NiAssessOrderExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(NiAssessOrder record);

    int insertSelective(NiAssessOrder record);

    List<NiAssessOrder> selectByExample(NiAssessOrderExample example);

    NiAssessOrder selectByPrimaryKey(Integer orderid);

    int updateByExampleSelective(@Param("record") NiAssessOrder record, @Param("example") NiAssessOrderExample example);

    int updateByExample(@Param("record") NiAssessOrder record, @Param("example") NiAssessOrderExample example);

    int updateByPrimaryKeySelective(NiAssessOrder record);

    int updateByPrimaryKey(NiAssessOrder record);
}