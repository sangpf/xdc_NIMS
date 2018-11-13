package com.newIns.model.assess;

public class AssessModelBigFive {
	
	private Integer aqnId; 
	
	private String resultSummary,resultDetail,comment;
	
	private Integer dimension,section;
	
	private Integer upperNum,lowNum;

	public Integer getAqnId() {
		return aqnId;
	}

	public void setAqnId(Integer aqnId) {
		this.aqnId = aqnId;
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

	public Integer getUpperNum() {
		return upperNum;
	}

	public void setUpperNum(Integer upperNum) {
		this.upperNum = upperNum;
	}

	public Integer getLowNum() {
		return lowNum;
	}

	public void setLowNum(Integer lowNum) {
		this.lowNum = lowNum;
	}
	
}
