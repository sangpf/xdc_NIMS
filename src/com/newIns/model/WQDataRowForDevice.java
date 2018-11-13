package com.newIns.model;
/**
 * @Description 存储插入到ni_user_device表中数据的实体
 * @author wanq
 *
 */
public class WQDataRowForDevice {
	private Integer userId;
	private String model;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
