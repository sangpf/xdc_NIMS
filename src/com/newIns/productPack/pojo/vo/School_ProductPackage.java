package com.newIns.productPack.pojo.vo;

import java.util.Date;

/**
 * 根据学校id 查询当前学校购买了哪些产品包, 将返回数据用 pojo 类封装
 * @author sang
 *
 */
public class School_ProductPackage {
	
	private Integer schoolId,id,packagetId,channelId,status;
	
	private Date bTime,eTime;
	
	private String title,schoolName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPackagetId() {
		return packagetId;
	}

	public void setPackagetId(Integer packagetId) {
		this.packagetId = packagetId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
