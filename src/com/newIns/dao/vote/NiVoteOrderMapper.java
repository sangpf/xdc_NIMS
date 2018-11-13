package com.newIns.dao.vote;

import com.newIns.model.vote.NiVoteOrder;
import com.newIns.model.vote.NiVoteOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiVoteOrderMapper {
	
    //根据订单表主键查询记录数
    int selectCountNumbysqnId(Integer id);
	
    int countByExample(NiVoteOrderExample example);

    int deleteByExample(NiVoteOrderExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(NiVoteOrder record);

    int insertSelective(NiVoteOrder record);

    List<NiVoteOrder> selectByExample(NiVoteOrderExample example);

    NiVoteOrder selectByPrimaryKey(Integer orderid);

    int updateByExampleSelective(@Param("record") NiVoteOrder record, @Param("example") NiVoteOrderExample example);

    int updateByExample(@Param("record") NiVoteOrder record, @Param("example") NiVoteOrderExample example);

    int updateByPrimaryKeySelective(NiVoteOrder record);

    int updateByPrimaryKey(NiVoteOrder record);
}