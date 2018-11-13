package com.newIns.model;

import java.sql.Timestamp;

/**
 * @Description 存储插入到ni_school_dict表中数据的实体
 * @author wanq
 *
 */
public class WQDataRowForSchoolDict {
	private Integer schoolId;
	private String wanxSchoolCode;
	private String wanxSchoolName;
	private String regionCode;
	private String regionName;
	private Timestamp addTime;
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getWanxSchoolCode() {
		return wanxSchoolCode;
	}

	public void setWanxSchoolCode(String wanxSchoolCode) {
		this.wanxSchoolCode = wanxSchoolCode;
	}

	public String getWanxSchoolName() {
		return wanxSchoolName;
	}

	public void setWanxSchoolName(String wanxSchoolName) {
		this.wanxSchoolName = wanxSchoolName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
