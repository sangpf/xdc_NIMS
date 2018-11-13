package com.newIns.productPack.pojo;

import java.util.Date;

/**
 *  学校 -- 产品包 关联表
 * @author sang
 *
 */
public class School_Product {
	
	private Integer id;  // key
	
	private Integer schoolId,packagetId,status;
	
	private Date bTime,eTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getPackagetId() {
		return packagetId;
	}

	public void setPackagetId(Integer packagetId) {
		this.packagetId = packagetId;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
