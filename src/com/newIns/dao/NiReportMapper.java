package com.newIns.dao;

import com.newIns.model.report.NiReport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lyr on 16/6/29.
 */
public interface NiReportMapper {
    List<NiReport> selectList(HashMap<String, Object> hashMap);
    
    //批量更新状态
    int updateReportStatus(HashMap<String,Object> retMap);
    
    //批量删除
    int deleteReportByIds(HashMap<String, Object> retMap);
   
    int insert(NiReport report);
    
    List<NiReport> selectAllListByReportClassId();

    NiReport selectByPrimaryKey(Integer reportid);
    
    //根据新添加的报告信息查找报告Id
    NiReport getReportIdByReportInfo(NiReport report);
    
    //将报告列表图片路径存入表中
    void updateImgUrl(HashMap<String, Object> retMap);
    
    //
    void updateReportUrl(HashMap<String, Object> retMap);
    
    //编辑
    int edit(NiReport report);
}

