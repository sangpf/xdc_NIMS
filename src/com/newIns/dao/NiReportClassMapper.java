package com.newIns.dao;


import com.newIns.model.report.NiReportClass;

public interface NiReportClassMapper {
    int deleteByPrimaryKey(Integer reportclassid);

    int insert(NiReportClass record);

    int insertSelective(NiReportClass record);

    NiReportClass selectByPrimaryKey(Integer reportclassid);

    int updateByPrimaryKeySelective(NiReportClass record);

    int updateByPrimaryKey(NiReportClass record);
}