package com.newIns.dao;

import com.newIns.model.NiSqnclassDict;
import com.newIns.model.NiSqnclassDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NiSqnclassDictMapper {
    int countByExample(NiSqnclassDictExample example);

    int deleteByExample(NiSqnclassDictExample example);

    int deleteByPrimaryKey(Integer sqnclassid);

    int insert(NiSqnclassDict record);

    int insertSelective(NiSqnclassDict record);

    List<NiSqnclassDict> selectByExample(NiSqnclassDictExample example);
    
    List<NiSqnclassDict> selectList();

    NiSqnclassDict selectByPrimaryKey(Integer sqnclassid);

    int updateByExampleSelective(@Param("record") NiSqnclassDict record, @Param("example") NiSqnclassDictExample example);

    int updateByExample(@Param("record") NiSqnclassDict record, @Param("example") NiSqnclassDictExample example);

    int updateByPrimaryKeySelective(NiSqnclassDict record);

    int updateByPrimaryKey(NiSqnclassDict record);
}