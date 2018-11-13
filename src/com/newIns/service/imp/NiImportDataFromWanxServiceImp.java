package com.newIns.service.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newIns.dao.NiImportDataFromWanxMapper;
import com.newIns.model.WQDataRowForBase;
import com.newIns.model.WQDataRowForDevice;
import com.newIns.model.WQDataRowForEducation;
import com.newIns.model.WQDataRowForIdentifer;
import com.newIns.model.WQDataRowForSchoolDict;
import com.newIns.model.WQDataRowFromWanx;
import com.newIns.model.WQRegion;
import com.newIns.model.WQSchool;
import com.newIns.model.WQTempRegion;
import com.newIns.service.NiImportDataFromWanxService;

import org.apache.log4j.Logger;

@Service
public class NiImportDataFromWanxServiceImp implements
		NiImportDataFromWanxService {
	@Resource
	private NiImportDataFromWanxMapper niImportDataFromWanxMapper;

	private static Logger logger = Logger
			.getLogger(NiImportDataFromWanxServiceImp.class);

	private final String REPLACED_STR = "\\N";

	// 设置分页大小
	private int pageSize = 1000;
	// 当前页码
	private int page = 0;
	// 最大页码
	private int pageMax = -1;

	private int total;

	private Map<String, WQTempRegion> regionMap;
	private Map<String, String> schoolMap;

	private List<WQDataRowFromWanx> dataFromWanxList;
	private List<WQDataRowForIdentifer> dataForIdentiferList;
	private List<WQDataRowForBase> dataForBaseList;
	private Map<Integer, Object> dataForEducationAndSchoolDictMap;
	private List<WQDataRowForEducation> dataForEducationList;
	private List<WQDataRowForSchoolDict> dataForSchoolDictList;
	private List<WQDataRowForDevice> dataForDeviceList;

	@Override
	public List<WQDataRowFromWanx> selectDataFromWanx(String tableName,
			int from, int length) {
		List<WQDataRowFromWanx> resultList = niImportDataFromWanxMapper
				.selectDataFromWanx(tableName, from, length);
		return resultList;
	}

	@Override
	public List<WQDataRowForIdentifer> convertDataToIdentifer(
			List<WQDataRowFromWanx> dataFromWanx) {
		List<WQDataRowForIdentifer> resultList = new ArrayList<WQDataRowForIdentifer>();
		for (int i = 0; i < dataFromWanx.size(); i++) {
			WQDataRowFromWanx dataRowFromWanx = dataFromWanx.get(i);
			WQDataRowForIdentifer dataRowForIdentifer = new WQDataRowForIdentifer();
			String id = dataRowFromWanx.getId();
			String nickname = dataRowFromWanx.getNickname();
			Integer wanxUserId = null;
			String wanxNickname = null;
			/*
			 * 转换规则实现
			 */
			if (id != null && !id.trim().equals("")) {
				wanxUserId = Integer.parseInt(id);
			} else {
				logger.warn("警告：原表id=" + dataFromWanx.get(i).getId()
						+ "处的id值为null");
			}
			if (nickname != null && !nickname.trim().equals("")) {
				if (!nickname.equals(REPLACED_STR)) {
					wanxNickname = nickname;
				}
			}
			dataRowForIdentifer.setWanxUserId(wanxUserId);
			dataRowForIdentifer.setWanxNickname(wanxNickname);
			resultList.add(dataRowForIdentifer);
		}
		return resultList;
	}

	@Override
	public List<WQDataRowForBase> convertDataToBase(
			List<WQDataRowFromWanx> dataFromWanx) {
		List<WQDataRowForBase> resultList = new ArrayList<WQDataRowForBase>();
		for (int i = 0; i < dataFromWanx.size(); i++) {
			WQDataRowFromWanx dataRowFromWanx = dataFromWanx.get(i);
			WQDataRowForBase dataRowForBase = new WQDataRowForBase();
			String mobile = dataRowFromWanx.getMobile();
			String name = dataRowFromWanx.getName();
			String sex = dataRowFromWanx.getSex();
			String cerno = dataRowFromWanx.getCerno();
			String createstamp = dataRowFromWanx.getCreatestamp();
			String bindmail = dataRowFromWanx.getBindmail();
			String phone = null;
			String userName = null;
			Integer gender = null;
			String idCard = null;
			Timestamp userCTime = null;
			String email = null;
			/*
			 * 转换规则实现
			 */
			if (mobile != null && !mobile.trim().equals("")
					&& mobile.length() < 16) {
				if (!mobile.equals(REPLACED_STR) && mobile.startsWith("1")) {
					phone = mobile;
				} else if (!mobile.equals(REPLACED_STR)
						&& !mobile.startsWith("1")) {
					logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
							+ " 记录的mobile值不是有效的电话号码值");
				}
			}
			if (name != null && !name.trim().equals("")) {
				if (!name.equals(REPLACED_STR)) {
					userName = name;
				}
			} else {
				logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
						+ " 记录的name值为空");
			}

			if (sex != null && !sex.trim().equals("")) {
				if (sex.equals("男")) {
					gender = 1;
				} else if (sex.equals("女")) {
					gender = 2;
				}
			} else {
				logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
						+ " 记录的sex值为空");
			}

			if (cerno != null && !cerno.trim().equals("")) {
				if (!cerno.equals(REPLACED_STR)) {
					idCard = cerno;
				}
			} else {
				logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
						+ " 记录的cerno值为空");
			}

			if (createstamp != null && !createstamp.trim().equals("")) {
				if (!createstamp.equals(REPLACED_STR)) {
					try {
						userCTime = Timestamp.valueOf(createstamp);
					} catch (Exception e) {
						logger.warn("【警告】" + "原表id= "
								+ dataFromWanx.get(i).getId()
								+ "createstamp格式不正确" + e.getMessage());
						userCTime = null;
					}
				}
			} else {
				logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
						+ " 记录的createstamp值为空");
			}

			if (bindmail != null && !bindmail.trim().equals("")) {
				if (!bindmail.equals(REPLACED_STR)) {
					email = bindmail;
				}
			}
			dataRowForBase.setPhone(phone);
			dataRowForBase.setUserName(userName);
			dataRowForBase.setGender(gender);
			dataRowForBase.setIdCard(idCard);
			dataRowForBase.setUserCTime(userCTime);
			dataRowForBase.setEmail(email);
			resultList.add(dataRowForBase);
		}
		return resultList;
	}

	@Override
	public Map<Integer, Object> convertDataToEducationAndSchoolDict(
			List<WQDataRowFromWanx> dataFromWanx,
			Map<String, String> schoolMap, Map<String, WQTempRegion> regionMap) {

		Map<Integer, Object> resultMap = new HashMap<Integer, Object>();

		List<WQDataRowForEducation> educationResultList = new ArrayList<WQDataRowForEducation>();
		List<WQDataRowForSchoolDict> schoolDictResultList = new ArrayList<WQDataRowForSchoolDict>();
		for (int i = 0; i < dataFromWanx.size(); i++) {
			WQDataRowFromWanx dataRowFromWanx = dataFromWanx.get(i);
			WQDataRowForEducation dataRowForEducation = new WQDataRowForEducation();
			WQDataRowForSchoolDict dataRowForSchoolDict = new WQDataRowForSchoolDict();
			String usersn = dataRowFromWanx.getUsersn();
			String srcRole = dataRowFromWanx.getRole();
			String bind_card = dataRowFromWanx.getBind_card();
			String bind_stu = dataRowFromWanx.getBind_stu();
			String score = dataRowFromWanx.getScore();
			String account = dataRowFromWanx.getAccount();
			String school = dataRowFromWanx.getSchool();
			String region_code = dataRowFromWanx.getRegion_code();
			// String region_name = dataRowFromWanx.getRegion_name();
			String customer_code = dataRowFromWanx.getCustomer_code();

			String userSn = null;
			Integer desRole = null;
			Integer bindCard = null;
			Integer bindStudent = null;
			Integer wanxScore = null;// 这个转换类型是double
			String wanxAccount = null;
			Integer schoolId = null;
			String schoolName = null;
			Integer regionId = null;
			String regionNameForEducation = null;

			String wanxSchoolCode = null;
			String wanxSchoolName = null;
			String regionCode = null;
			String regionNameForSchoolDict = null;
			Timestamp addTime = new Timestamp(System.currentTimeMillis());
			/*
			 * 转换规则实现
			 */
			if (usersn != null && !usersn.trim().equals("")) {
				if (!usersn.equals(REPLACED_STR)) {
					userSn = usersn;
				}
			}
			if (srcRole != null && !srcRole.trim().equals("")) {
				if (!srcRole.equals(REPLACED_STR)) {
					try {
						desRole = Integer.parseInt(srcRole);
					} catch (Exception e) {
						logger.warn("【警告】" + "原表id= "
								+ dataFromWanx.get(i).getId() + "role字段值格式不正确"
								+ e.getMessage());
						desRole = null;
					}
				}
			}
			if (bind_card != null && !bind_card.trim().equals("")) {
				if (!bind_card.equals(REPLACED_STR)) {
					try {
						bindCard = Integer.parseInt(bind_card);
					} catch (Exception e) {
						logger.warn("【警告】" + "原表id= "
								+ dataFromWanx.get(i).getId()
								+ "bind_card字段值格式不正确" + e.getMessage());
						bindCard = null;
					}
				}
			}
			if (bind_stu != null && !bind_stu.trim().equals("")) {
				if (!bind_stu.equals(REPLACED_STR)) {
					try {
						bindStudent = Integer.parseInt(bind_stu);
					} catch (Exception e) {
						logger.warn("【警告】" + "原表id= "
								+ dataFromWanx.get(i).getId()
								+ "bind_stu字段值格式不正确" + e.getMessage());
						bindStudent = null;
					}
				}
			}
			if (score != null && !score.trim().equals("")) {
				if (!score.equals(REPLACED_STR)) {
					try {
						wanxScore = (int) Double.parseDouble(score);
					} catch (Exception e) {
						logger.warn("【警告】" + "原表id= "
								+ dataFromWanx.get(i).getId() + "score字段值格式不正确"
								+ e.getMessage());
						wanxScore = null;
					}
				}
			}

			if (account != null && !account.trim().equals("")) {
				if (!account.equals(REPLACED_STR)) {
					wanxAccount = account;
				}
			}
			if (school != null && !school.trim().equals("")) {
				if (!school.equals(REPLACED_STR)) {
					wanxSchoolName = school;
					schoolName = school;
					if (schoolMap.get(school) != null
							&& !schoolMap.get(school).trim().equals("")) {
						try {
							schoolId = Integer.parseInt(schoolMap.get(school));
						} catch (Exception e) {
							logger.warn("【警告】" + "原表id= "
									+ dataFromWanx.get(i).getId()
									+ "school字段值格式不正确" + e.getMessage());
							schoolId = null;
						}
					} else {
						logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
								+ " 中,school= “" + school + "” 的学校名在标准库中不存在");
					}
					// schoolName = school;
				}
			}

			if (region_code != null && !region_code.trim().equals("")) {
				if (!region_code.equals(REPLACED_STR)) {
					if (regionMap.get(region_code) != null) {
						if (regionMap.get(region_code).getRegionId() != null) {
							regionId = regionMap.get(region_code).getRegionId();
						}

						regionNameForEducation = regionMap.get(region_code)
								.getRegionName();
						regionNameForSchoolDict = regionNameForEducation;
						regionCode = region_code;
					} else {
						logger.warn("警告：原表id= " + dataFromWanx.get(i).getId()
								+ " 中,region_code=  " + region_code
								+ "  的地区码在标准库中不存在");
					}
				}
			}
			if (customer_code != null && !customer_code.trim().equals("")) {
				if (!customer_code.equals(REPLACED_STR)) {
					wanxSchoolCode = customer_code;
				}
			}
			dataRowForEducation.setUserSn(userSn);
			dataRowForEducation.setRole(desRole);
			dataRowForEducation.setBindCard(bindCard);
			dataRowForEducation.setBindStudent(bindStudent);
			dataRowForEducation.setWanxScore(wanxScore);
			dataRowForEducation.setWanxAccount(wanxAccount);
			dataRowForEducation.setSchoolId(schoolId);
			dataRowForEducation.setSchoolName(schoolName);
			dataRowForEducation.setRegionId(regionId);
			dataRowForEducation.setRegionName(regionNameForEducation);

			dataRowForSchoolDict.setWanxSchoolCode(wanxSchoolCode);
			dataRowForSchoolDict.setWanxSchoolName(wanxSchoolName);
			dataRowForSchoolDict.setSchoolId(schoolId);
			dataRowForSchoolDict.setRegionCode(regionCode);
			dataRowForSchoolDict.setRegionName(regionNameForSchoolDict);
			dataRowForSchoolDict.setAddTime(addTime);

			educationResultList.add(dataRowForEducation);
			schoolDictResultList.add(dataRowForSchoolDict);
		}
		resultMap.put(1, educationResultList);
		resultMap.put(2, schoolDictResultList);
		return resultMap;
	}

	@Override
	public List<WQDataRowForDevice> convertDataToDevice(
			List<WQDataRowFromWanx> dataFromWanx) {
		List<WQDataRowForDevice> resultList = new ArrayList<WQDataRowForDevice>();
		for (int i = 0; i < dataFromWanx.size(); i++) {
			WQDataRowFromWanx dataRowFromWanx = dataFromWanx.get(i);
			WQDataRowForDevice dataRowForDevice = new WQDataRowForDevice();
			String telephone_model = dataRowFromWanx.getTelephone_model();
			String model = null;
			/*
			 * 转换规则实现
			 */
			if (telephone_model != null && !telephone_model.trim().equals("")) {
				if (!telephone_model.equals(REPLACED_STR)) {
					model = telephone_model;
				}
			}
			dataRowForDevice.setModel(model);
			resultList.add(dataRowForDevice);
		}
		return resultList;
	}

	@Override
	public Map<String, WQTempRegion> selectRegionCodeAndIdAndNameFromRegionDict(
			String tableName) {
		List<WQRegion> regionList = niImportDataFromWanxMapper
				.selectAllDataFromRegionDict(tableName);
		Map<String, WQTempRegion> resultMap = new HashMap<String, WQTempRegion>();
		for (int i = 0; i < regionList.size(); i++) {
			WQTempRegion tempRegion = new WQTempRegion();
			tempRegion.setRegionId(regionList.get(i).getRegionId());
			tempRegion.setRegionName(regionList.get(i).getRegionName());
			resultMap.put(regionList.get(i).getRegionCode(), tempRegion);
		}
		return resultMap;
	}

	@Override
	public Map<String, String> selectSchoolNameAndIdFromSchoolDict(
			String tableName) {
		List<WQSchool> schoolList = niImportDataFromWanxMapper
				.selectAllDataFromSchoolDict(tableName);
		Map<String, String> resultMap = new HashMap<String, String>();
		for (int i = 0; i < schoolList.size(); i++) {
			resultMap.put(schoolList.get(i).getSchoolName(), schoolList.get(i)
					.getSchoolId() + "");
		}
		return resultMap;
	}

	@Override
	public <T> int updateDataToDatabase(String tableName,
			List<String> fieldNames, String id, List<T> dataList) {
		int result = niImportDataFromWanxMapper.updateDataToDatabase(tableName,
				fieldNames, id, dataList);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(String tableName) {
		init(tableName);
		while (page <= pageMax) {
			logger.info("正在从玩校数据库中读出第 " + page + " 页数据");
			dataFromWanxList = selectDataFromWanx(tableName, (page - 1)
					* pageSize, pageSize);// 从玩校数据库中分页读出数据
			dataForIdentiferList = convertDataToIdentifer(dataFromWanxList);// 转换成要插入到ni_user_identifer中的数据
			dataForBaseList = convertDataToBase(dataFromWanxList);// 转换成要插入到ni_user_base中的数据
			dataForEducationAndSchoolDictMap = convertDataToEducationAndSchoolDict(
					dataFromWanxList, schoolMap, regionMap);
			dataForEducationList = (List<WQDataRowForEducation>) dataForEducationAndSchoolDictMap
					.get(1);// 转换成要插入到ni_user_education中的数据
			dataForSchoolDictList = (List<WQDataRowForSchoolDict>) dataForEducationAndSchoolDictMap
					.get(2);// 转换成要插入或者更新到ni_school_dict中的数据

			// dataForEducationList =
			// convertDataToEducationAndSchoolDict(dataFromWanxList, regionMap);
			dataForDeviceList = convertDataToDevice(dataFromWanxList);// 转换成要插入到ni_user_device中的数据
			logger.info("第 " + (page) + " 页数据读出成功");
			logger.info("正在向数据库写入第 " + page + " 页数据");
			// int indexOfIdentifer = -1;
			// int indexOfBase = -1;
			// int indexOfEducation = -1;
			// int indexOfDevice = -1;
			// int indexOfSchoolDict = -1;
			int index = -1;
			try {
				// indexOfIdentifer =
				// insertDataIntoIdentifer(dataForIdentiferList);//将数据写入到ni_user_identifer
				// indexOfBase =
				// insertDataIntoBase(dataForBaseList);//将数据写入到ni_user_base
				// indexOfEducation =
				// insertDataIntoEducation(dataForEducationList);//将数据写入到ni_user_education
				// indexOfDevice =
				// insertDataIntoDevice(dataForDeviceList);//将数据写入到ni_user_device
				// indexOfSchoolDict =
				// insertDataIntoSchoolDict(dataForSchoolDictList);//将数据写入到ni_school_dict
				// logger.info("第 " + page + " 页数据写入成功");
				index = insertDataIntoDatabase(dataForIdentiferList,
						dataForBaseList, dataForEducationList,
						dataForDeviceList, dataForSchoolDictList);
				logger.info("第 " + page + " 页数据写入成功");
			} catch (Exception e) {
				logger.warn("【警告】：" + e.getMessage());
				logger.warn("【警告】：第 " + (page * pageSize + index) + " 页数据写入失败");
			} finally {
				page++;
			}
		}
	}

	@Override
	public int getProgress() {
		return (int) (((page * 1.0) / pageMax) * 100);
	}

	/**
	 * @Despriction 初始化
	 * @param tableName
	 */
	private void init(String tableName) {
		total = niImportDataFromWanxMapper.getCountOfWanx(tableName);
		if (total % pageSize == 0) {
			pageMax = total / pageSize;
		} else {
			pageMax = total / pageSize + 1;
		}

		regionMap = selectRegionCodeAndIdAndNameFromRegionDict("ni_region_dict");
		schoolMap = selectSchoolNameAndIdFromSchoolDict("ni_school_dict");
		page = 1;
	}

	@Override
	public int insertDataIntoIdentifer(List<WQDataRowForIdentifer> dataList) {
		int result = niImportDataFromWanxMapper
				.insertDataIntoIdentifer(dataList);
		return result;
	}

	@Override
	public int insertDataIntoBase(List<WQDataRowForBase> dataList) {
		int result = niImportDataFromWanxMapper.insertDataIntoBase(dataList);
		return result;
	}

	@Override
	public int insertDataIntoEducation(List<WQDataRowForEducation> dataList) {
		int result = niImportDataFromWanxMapper
				.insertDataIntoEducation(dataList);
		return result;
	}

	@Override
	public int insertDataIntoDevice(List<WQDataRowForDevice> dataList) {
		int result = niImportDataFromWanxMapper.insertDataIntoDevice(dataList);
		return result;
	}

	@Override
	public int insertDataIntoSchoolDict(List<WQDataRowForSchoolDict> dataList) {
		int result = niImportDataFromWanxMapper
				.insertDataIntoSchoolDict(dataList);
		return result;
	}

	@Override
	public int getTotalNum() {
		return total;
	}

	@Override
	public int getCurrentNum() {
		if (page >= pageMax) {
			return total;
		} else {
			return page * pageSize;
		}
	}

	@Transactional
	@Override
	public int insertDataIntoDatabase(
			List<WQDataRowForIdentifer> dataForIdentiferList,
			List<WQDataRowForBase> dataForBaseList,
			List<WQDataRowForEducation> dataForEducationList,
			List<WQDataRowForDevice> dataForDeviceList,
			List<WQDataRowForSchoolDict> dataForSchoolDictList) {
		int indexOfIdentifer = insertDataIntoIdentifer(dataForIdentiferList);
		int indexOfBase = insertDataIntoBase(dataForBaseList);
		int indexOfEducation = insertDataIntoEducation(dataForEducationList);
		int indexOfSchoolDict = insertDataIntoSchoolDict(dataForSchoolDictList);
		int indexOfDevice = insertDataIntoDevice(dataForDeviceList);
		int[] array = new int[] { indexOfIdentifer, indexOfBase,
				indexOfEducation, indexOfSchoolDict, indexOfDevice };
		Arrays.sort(array);
		return array[0];
	}
}
