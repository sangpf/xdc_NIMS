package com.newIns.model;

/**
 * 
 * 倾向抽奖描述
 * @author 11409
 *
 */
public class NiLotteryPreferDict {
	
	private Integer evaluateId,
	highBegin,highEnd,highAwardId,highAwardTotal,highAwardLeft,
	midBegin,midEnd,midAwardId,midAwardTotal,midAwardLeft,
	lowBegin,lowEnd,lowAwardId,lowAwardTotal,lowAwardLeft;  //起始值,结束值,奖品id,一等奖总数,一等奖剩余数 只业务用
	
	private String ruleName,comment,highAwardName,midAwardName,lowAwardName;   //规则名称
	
	private Float highRate,midRate,lowRate; //中奖概率
	
	public String getHighAwardName() {
		return highAwardName;
	}
	public void setHighAwardName(String highAwardName) {
		this.highAwardName = highAwardName;
	}
	public String getMidAwardName() {
		return midAwardName;
	}
	public void setMidAwardName(String midAwardName) {
		this.midAwardName = midAwardName;
	}
	public String getLowAwardName() {
		return lowAwardName;
	}
	public void setLowAwardName(String lowAwardName) {
		this.lowAwardName = lowAwardName;
	}
	public Integer getEvaluateId() {
		return evaluateId;
	}
	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}
	public Integer getHighBegin() {
		return highBegin;
	}
	public void setHighBegin(Integer highBegin) {
		this.highBegin = highBegin;
	}
	public Integer getHighEnd() {
		return highEnd;
	}
	public void setHighEnd(Integer highEnd) {
		this.highEnd = highEnd;
	}
	
	public Integer getHighAwardId() {
		return highAwardId;
	}
	public void setHighAwardId(Integer highAwardId) {
		this.highAwardId = highAwardId;
	}
	public Integer getHighAwardTotal() {
		return highAwardTotal;
	}
	public void setHighAwardTotal(Integer highAwardTotal) {
		this.highAwardTotal = highAwardTotal;
	}
	public Integer getHighAwardLeft() {
		return highAwardLeft;
	}
	public void setHighAwardLeft(Integer highAwardLeft) {
		this.highAwardLeft = highAwardLeft;
	}
	public Integer getMidAwardTotal() {
		return midAwardTotal;
	}
	public void setMidAwardTotal(Integer midAwardTotal) {
		this.midAwardTotal = midAwardTotal;
	}
	public Integer getMidAwardLeft() {
		return midAwardLeft;
	}
	public void setMidAwardLeft(Integer midAwardLeft) {
		this.midAwardLeft = midAwardLeft;
	}
	public Integer getLowAwardTotal() {
		return lowAwardTotal;
	}
	public void setLowAwardTotal(Integer lowAwardTotal) {
		this.lowAwardTotal = lowAwardTotal;
	}
	public Integer getLowAwardLeft() {
		return lowAwardLeft;
	}
	public void setLowAwardLeft(Integer lowAwardLeft) {
		this.lowAwardLeft = lowAwardLeft;
	}
	public Integer getMidBegin() {
		return midBegin;
	}
	public void setMidBegin(Integer midBegin) {
		this.midBegin = midBegin;
	}
	public Integer getMidEnd() {
		return midEnd;
	}
	public void setMidEnd(Integer midEnd) {
		this.midEnd = midEnd;
	}
	public Integer getMidAwardId() {
		return midAwardId;
	}
	public void setMidAwardId(Integer midAwardId) {
		this.midAwardId = midAwardId;
	}
	public Integer getLowBegin() {
		return lowBegin;
	}
	public void setLowBegin(Integer lowBegin) {
		this.lowBegin = lowBegin;
	}
	public Integer getLowEnd() {
		return lowEnd;
	}
	public void setLowEnd(Integer lowEnd) {
		this.lowEnd = lowEnd;
	}
	public Integer getLowAwardId() {
		return lowAwardId;
	}
	public void setLowAwardId(Integer lowAwardId) {
		this.lowAwardId = lowAwardId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Float getHighRate() {
		return highRate;
	}
	public void setHighRate(Float highRate) {
		this.highRate = highRate;
	}
	public Float getMidRate() {
		return midRate;
	}
	public void setMidRate(Float midRate) {
		this.midRate = midRate;
	}
	public Float getLowRate() {
		return lowRate;
	}
	public void setLowRate(Float lowRate) {
		this.lowRate = lowRate;
	}
	
	

	
}
