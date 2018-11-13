package com.newIns.productPack.pojo;

import java.util.Date;

public class SchoolMember {
	
	private Integer schoolId;   // 主键
	
	private String schoolName;
	
	private Date bTime,eTime;

	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
