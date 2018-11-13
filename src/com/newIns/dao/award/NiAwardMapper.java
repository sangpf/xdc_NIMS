package com.newIns.dao.award;

import com.newIns.model.award.NiAward;
import com.newIns.model.award.NiAwardExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiAwardMapper {
    int countByExample(NiAwardExample example);

    int deleteByExample(NiAwardExample example);

    int deleteByPrimaryKey(Integer awardid);

    int insert(NiAward record);

    int insertSelective(NiAward record);
    
    List<NiAward> selectList();

    List<NiAward> selectByExample(NiAwardExample example);

    NiAward selectByPrimaryKey(Integer awardid);

    int updateByExampleSelective(@Param("record") NiAward record, @Param("example") NiAwardExample example);

    int updateByExample(@Param("record") NiAward record, @Param("example") NiAwardExample example);

    int updateByPrimaryKeySelective(NiAward record);

    int updateByPrimaryKey(NiAward record);
}