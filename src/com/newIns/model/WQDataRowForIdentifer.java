package com.newIns.model;
/**
 * @Description 存储插入到ni_user_identifer表中数据的实体
 * @author wanq
 *
 */
public class WQDataRowForIdentifer {
	private Integer userId;
	private Integer wanxUserId;
	private String wanxNickname;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getWanxUserId() {
		return wanxUserId;
	}

	public void setWanxUserId(Integer wanxUserId) {
		this.wanxUserId = wanxUserId;
	}

	public String getWanxNickname() {
		return wanxNickname;
	}

	public void setWanxNickname(String wanxNickname) {
		this.wanxNickname = wanxNickname;
	}

}
