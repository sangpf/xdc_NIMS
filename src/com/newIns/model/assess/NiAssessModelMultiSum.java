package com.newIns.model.assess;

public class NiAssessModelMultiSum {
	private Integer aqnId,dimension; // 测评问卷id,纬度序号  ,联合主键
	private String resultSummary,resultDetail,comment; //概览描述,详情描述
	
	private Integer priority;  // 优先级
	
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getAqnId() {
		return aqnId;
	}
	public void setAqnId(Integer aqnId) {
		this.aqnId = aqnId;
	}
	public Integer getDimension() {
		return dimension;
	}
	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
	public String getResultSummary() {
		return resultSummary;
	}
	public void setResultSummary(String resultSummary) {
		this.resultSummary = resultSummary;
	}
	public String getResultDetail() {
		return resultDetail;
	}
	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
