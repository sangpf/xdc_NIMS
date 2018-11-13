package com.newIns.model.assess;

public class AssessResult {
	
	private Integer aqnId;
	
    private Integer intervalBegin;  // 上线

    private Integer intervalEnd;  // 下线
    
	private Integer dimension; // 纬度编号
	
	private String dimensionStr; // 联合维度

	private Integer section; // 区间编号
	
	private String resultSummary,resultDetail; //概览描述,详情描述

	private Integer priority; // 优先级
	
	public Integer getIntervalBegin() {
		return intervalBegin;
	}

	public void setIntervalBegin(Integer intervalBegin) {
		this.intervalBegin = intervalBegin;
	}

	public Integer getIntervalEnd() {
		return intervalEnd;
	}

	public void setIntervalEnd(Integer intervalEnd) {
		this.intervalEnd = intervalEnd;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
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

	public String getDimensionStr() {
		return dimensionStr;
	}

	public void setDimensionStr(String dimensionStr) {
		this.dimensionStr = dimensionStr;
	}

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
	
}
