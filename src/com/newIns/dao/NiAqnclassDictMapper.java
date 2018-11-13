package com.newIns.dao;

import com.newIns.model.NiAqnclassDict;
import com.newIns.model.NiAqnclassDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NiAqnclassDictMapper {
    int countByExample(NiAqnclassDictExample example);

    int deleteByExample(NiAqnclassDictExample example);

    int deleteByPrimaryKey(Integer aqnclassid);

    int insert(NiAqnclassDict record);

    int insertSelective(NiAqnclassDict record);

    List<NiAqnclassDict> selectByExample(NiAqnclassDictExample example);
    
    List<NiAqnclassDict> selectList();

    NiAqnclassDict selectByPrimaryKey(Integer aqnclassid);

    int updateByExampleSelective(@Param("record") NiAqnclassDict record, @Param("example") NiAqnclassDictExample example);

    int updateByExample(@Param("record") NiAqnclassDict record, @Param("example") NiAqnclassDictExample example);

    int updateByPrimaryKeySelective(NiAqnclassDict record);

    int updateByPrimaryKey(NiAqnclassDict record);
}