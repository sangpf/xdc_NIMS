package com.newIns.model.niDailyUpdateWanx;

/**
 * 三更查询实体类  包含三更类不存在的内容, 继承三更基础类
 * @author sangpf
 *
 */
public class NiDailyUpdateWanx_VO_01 extends NiDailyUpdateWanx_01{
	
	private Integer itemStatus;   // 内容状态
	private Integer number;   //份数
	private String showTitle;
	public Integer getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	
	
	
}
