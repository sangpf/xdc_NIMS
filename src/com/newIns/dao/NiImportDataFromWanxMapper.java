package com.newIns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newIns.model.WQDataRowForBase;
import com.newIns.model.WQDataRowForDevice;
import com.newIns.model.WQDataRowForEducation;
import com.newIns.model.WQDataRowForIdentifer;
import com.newIns.model.WQDataRowForSchoolDict;
import com.newIns.model.WQDataRowFromWanx;
import com.newIns.model.WQRegion;
import com.newIns.model.WQSchool;
/**
 * 
 * @author wanq
 *
 */
public interface NiImportDataFromWanxMapper {
	/**
	 * @Description 从玩校数据库中分页读取数据
	 * @param tableName 表名
	 * @param from 玩校数据库中记录的起始位置
	 * @param length
	 * @return 玩校数据库总的数据
	 */
	List<WQDataRowFromWanx> selectDataFromWanx(
			@Param("tableName") String tableName, @Param("from") int from,
			@Param("length") int length);
	/**
	 * @Description 从ni_school_dict表中读取所有的记录
	 * @param tableName 只能取ni_shool_dict
	 * @return
	 */
	List<WQSchool> selectAllDataFromSchoolDict(@Param("tableName") String tableName);

	/**
	 * @Description 从ni_region_dict表中读取所有的记录
	 * @param tableName 只能取ni_region_dict
	 * @return
	 */
	List<WQRegion> selectAllDataFromRegionDict(@Param("tableName") String tableName);

//	<T> int insertDataIntoDatabase(@Param("tableName") String tableName,@Param("dataList")List<T> dataList);

	<T> int updateDataToDatabase(String tableName, List<String> fieldNames,
			String id, List<T> dataList);

	/**
	 * @Description 从玩校数据库表中获取记录总数
	 * @param tableName 玩校数据库表名
	 * @return
	 */
	int getCountOfWanx(@Param("tableName") String tableName);
	
	/**
	 * @Description 将数据插入到ni_user_identifer表中
	 * @param dataList
	 * @return
	 */
	
	int insertDataIntoIdentifer(@Param("dataList") List<WQDataRowForIdentifer> dataList);
	
	/**
	 * @Description 将数据插入到ni_user_base表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoBase(@Param("dataList") List<WQDataRowForBase> dataList);
	/**
	 * @Description 将数据插入到ni_user_education表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoEducation(@Param("dataList") List<WQDataRowForEducation> dataList);
	/**
	 * @Description 将数据插入到ni_user_device表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoDevice(@Param("dataList") List<WQDataRowForDevice> dataList);
	/**
	 * @Description 将数据插入或者更新到ni_school_dict表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoSchoolDict(@Param("dataList") List<WQDataRowForSchoolDict> dataList);
}
