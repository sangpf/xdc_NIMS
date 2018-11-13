package com.newIns.model.vo;

/**
 * 超级调查列表
 * @author 11409
 *
 */
public class SuperListCategory {
	
//	页面列表分类 surveyQnList, 调查，awardQnList, 有奖问卷，funQnList, 吃喝玩乐，gestureQnList, 涨姿势，roseQnList, 花式开撩
	private String srtId;
	private String strName;
	
	public SuperListCategory(String srtId, String strName) {
		super();
		this.srtId = srtId;
		this.strName = strName;
	}
	public String getSrtId() {
		return srtId;
	}
	public void setSrtId(String srtId) {
		this.srtId = srtId;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
	
}
