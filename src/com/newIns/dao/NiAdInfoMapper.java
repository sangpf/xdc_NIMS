package com.newIns.dao;

import com.newIns.model.NiAdInfo;
import com.newIns.model.NiAdInfoExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NiAdInfoMapper {
	int countByExample(NiAdInfoExample example);

	int deleteByExample(NiAdInfoExample example);

	int deleteByPrimaryKey(Integer adid);

	int insert(NiAdInfo record);

	int insertSelective(NiAdInfo record);

	List<NiAdInfo> selectByExample(NiAdInfoExample example);

	// 存储过程查询所有
	List<NiAdInfo> selectList();

	// 筛选查询
	List<NiAdInfo> searchList(HashMap<String, Object> retMap);

	NiAdInfo selectByPrimaryKey(Integer adid);

	int updateByExampleSelective(@Param("record") NiAdInfo record,
			@Param("example") NiAdInfoExample example);

	int updateByExample(@Param("record") NiAdInfo record,
			@Param("example") NiAdInfoExample example);

	int updateByPrimaryKeySelective(NiAdInfo record);

	int updateByPrimaryKey(NiAdInfo record);

	// 广告池管理
	int deleteAdInfoByIds(HashMap<String, Object> retMap);// 批量删除
	// 更改状态

	int changeAdInfoStatus(HashMap<String, Object> retMap);

	void replaceAdInfo(HashMap<String, Object> hashmap);// 结束广告池管理

}