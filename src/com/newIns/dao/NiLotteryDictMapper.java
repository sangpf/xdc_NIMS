package com.newIns.dao;

import com.newIns.model.NiLotteryDict;
import com.newIns.model.NiLotteryDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NiLotteryDictMapper {
    int countByExample(NiLotteryDictExample example);

    int deleteByExample(NiLotteryDictExample example);

    int deleteByPrimaryKey(Integer lotteryid);

    int insert(NiLotteryDict record);

    int insertSelective(NiLotteryDict record);

    List<NiLotteryDict> selectByExample(NiLotteryDictExample example);

    NiLotteryDict selectByPrimaryKey(Integer lotteryid);

    int updateByExampleSelective(@Param("record") NiLotteryDict record, @Param("example") NiLotteryDictExample example);

    int updateByExample(@Param("record") NiLotteryDict record, @Param("example") NiLotteryDictExample example);

    int updateByPrimaryKeySelective(NiLotteryDict record);

    int updateByPrimaryKey(NiLotteryDict record);

}