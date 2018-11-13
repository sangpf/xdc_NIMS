package com.newIns.model;
/**
 * @Description 存储从ni_region_dict读出数据的实体
 * @author wanq
 *
 */
public class WQRegion {
	private String regionCode;
	private Integer regionId;
	private String regionName;

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
