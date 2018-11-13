package com.newIns.dao;

import com.newIns.model.page.NiDaily3updateWanx;
import com.newIns.model.page.NiDaily3updateWanxExample;
import com.newIns.model.page.NiDaily3updateWanxKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiDaily3updateWanxMapper {
	
	//三更列表 定时发布任务  检查发布时间 修改页面状态为发布
	int updateStateByTimer();
	
	//定时任务 根据联合主键修改状态
	int updateStateById_timer(NiDaily3updateWanx recode);
	
    int countByExample(NiDaily3updateWanxExample example);

    int deleteByExample(NiDaily3updateWanxExample example);

    int deleteByPrimaryKey(NiDaily3updateWanxKey key);

    int insert(NiDaily3updateWanx record);

    int insertSelective(NiDaily3updateWanx record);

    List<NiDaily3updateWanx> selectByExample(NiDaily3updateWanxExample example);

    NiDaily3updateWanx selectByPrimaryKey(NiDaily3updateWanxKey key);

    int updateByExampleSelective(@Param("record") NiDaily3updateWanx record, @Param("example") NiDaily3updateWanxExample example);

    int updateByExample(@Param("record") NiDaily3updateWanx record, @Param("example") NiDaily3updateWanxExample example);

    int updateByPrimaryKeySelective(NiDaily3updateWanx record);

    int updateByPrimaryKey(NiDaily3updateWanx record);
}