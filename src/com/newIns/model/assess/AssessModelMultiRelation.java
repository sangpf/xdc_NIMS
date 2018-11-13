package com.newIns.model.assess;

public class AssessModelMultiRelation {
	
	private Integer aqnId;  // 主键 ,测评id
	
	private String relation,resultSummary,resultDetail,comment;

	public Integer getAqnId() {
		return aqnId;
	}

	public void setAqnId(Integer aqnId) {
		this.aqnId = aqnId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
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
