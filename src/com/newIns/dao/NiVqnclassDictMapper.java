package com.newIns.dao;

import com.newIns.model.NiVqnclassDict;
import com.newIns.model.NiVqnclassDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NiVqnclassDictMapper {
    int countByExample(NiVqnclassDictExample example);

    int deleteByExample(NiVqnclassDictExample example);

    int deleteByPrimaryKey(Integer vqnclassid);

    int insert(NiVqnclassDict record);

    int insertSelective(NiVqnclassDict record);

    List<NiVqnclassDict> selectByExample(NiVqnclassDictExample example);
    
    List<NiVqnclassDict> selectList();

    NiVqnclassDict selectByPrimaryKey(Integer vqnclassid);

    int updateByExampleSelective(@Param("record") NiVqnclassDict record, @Param("example") NiVqnclassDictExample example);

    int updateByExample(@Param("record") NiVqnclassDict record, @Param("example") NiVqnclassDictExample example);

    int updateByPrimaryKeySelective(NiVqnclassDict record);

    int updateByPrimaryKey(NiVqnclassDict record);
}