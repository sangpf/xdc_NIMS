package com.newIns.service;

import java.util.List;
import java.util.Map;

import com.newIns.model.WQDataRowForBase;
import com.newIns.model.WQDataRowForDevice;
import com.newIns.model.WQDataRowForEducation;
import com.newIns.model.WQDataRowForIdentifer;
import com.newIns.model.WQDataRowForSchoolDict;
import com.newIns.model.WQDataRowFromWanx;
import com.newIns.model.WQTempRegion;

public interface NiImportDataFromWanxService {
	/**
	 * @Description 从玩校数据库中分页读取数据
	 * @param tableName
	 *            表名
	 * @param from
	 *            玩校数据库中记录的起始位置
	 * @param length
	 * @return 玩校数据库总的数据
	 */
	List<WQDataRowFromWanx> selectDataFromWanx(String tableName, int from,
			int length);

	/**
	 * @Description 将玩校数据表中的数据转换为插入到ni_user_identifier表中的数据
	 * @param dataFromWanx
	 *            玩校数据表中的数据
	 * @return 要插入到ni_user_identifier表中的数据
	 */
	List<WQDataRowForIdentifer> convertDataToIdentifer(
			List<WQDataRowFromWanx> dataFromWanx);

	/**
	 * @Description 将玩校数据表中的数据转换为插入到ni_user_base表中的数据
	 * @param dataFromWanx
	 * @return 要插入到ni_user_base表中的数据
	 */
	List<WQDataRowForBase> convertDataToBase(
			List<WQDataRowFromWanx> dataFromWanx);


	Map<Integer, Object> convertDataToEducationAndSchoolDict(
			List<WQDataRowFromWanx> dataFromWanx,
			Map<String, String> schoolMap, Map<String, WQTempRegion> regionMap);
	
//	List<WQDataRowForEducation> convertDataToEducation(List<WQDataRowFromWanx> dataFromWanx,Map<String, WQTempRegion> regionMap);

	/**
	 * @Description 将玩校数据表中的数据转换为插入到ni_user_device表中的数据
	 * @param dataFromWanx
	 * @return 要插入到ni_user_device表中的数据
	 */
	List<WQDataRowForDevice> convertDataToDevice(
			List<WQDataRowFromWanx> dataFromWanx);

	// <T> int insertDataIntoDatabase(String tableName, List<T> dataList);

	<T> int updateDataToDatabase(String tableName, List<String> fieldNames,
			String id, List<T> dataList);

	// int getCountOfWanx(String tableName);

	/**
	 * @Description 将从ni_school_dict表获取数据并将数据包装成为Map<String,String>类型返回
	 *              其中key为schoolName，value为schoolId
	 * @param tableName
	 * @return
	 */
	Map<String, String> selectSchoolNameAndIdFromSchoolDict(String tableName);

	/**
	 * @Description 将从ni_region_dict表获取数据并将数据包装成为Map<String,WQTempRegion>类型返回
	 *              其中key为regionCode，value为WQTempRegion对象
	 * @param tableName
	 * @return
	 */
	Map<String, WQTempRegion> selectRegionCodeAndIdAndNameFromRegionDict(
			String tableName);

	/**
	 * @Description 开始导入数据
	 * @param tableName
	 */
	void start(String tableName);

	/**
	 * @Description 获取导入数据的进度值
	 * @return 数据的进度值
	 */
	int getProgress();

	/**
	 * @Description 将数据插入到ni_user_identifer表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoIdentifer(List<WQDataRowForIdentifer> dataList);

	/**
	 * @Description 将数据插入到ni_user_base表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoBase(List<WQDataRowForBase> dataList);

	/**
	 * @Description 将数据插入到ni_user_education表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoEducation(List<WQDataRowForEducation> dataList);

	/**
	 * @Description 将数据插入到ni_user_device表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoDevice(List<WQDataRowForDevice> dataList);

	/**
	 * @Description 将数据插入或者更新到ni_school_dict表中
	 * @param dataList
	 * @return
	 */
	int insertDataIntoSchoolDict(List<WQDataRowForSchoolDict> dataList);

	int getTotalNum();

	int getCurrentNum();

	int insertDataIntoDatabase(
			List<WQDataRowForIdentifer> dataForIdentiferList,
			List<WQDataRowForBase> dataForBaseList,
			List<WQDataRowForEducation> dataForEducationList,
			List<WQDataRowForDevice> dataForDeviceList,
			List<WQDataRowForSchoolDict> dataForSchoolDictList);
}
