package com.newIns.model.assess;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @Description 调查问卷model
 * @author Guan
 * @time 2016年6月28日 下午7:48:35
 */
@Component(value = "AssessQuestionnaire")
public class AssessQuestionnaire {
	String success;
	int aqnId;

	String aqnName;

	String publisherName;

	String epilogue;

	String perface;

	String aqnClassName;

	int questionQty;

	String picPath;

	String aqnSummary;

	String comment;

	List<AssessQuestion> questions;

	public int getAqnId() {
		return aqnId;
	}

	public void setAqnId(int aqnId) {
		this.aqnId = aqnId;
	}

	public String getAqnName() {
		return aqnName;
	}

	public void setAqnName(String aqnName) {
		this.aqnName = aqnName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getEpilogue() {
		return epilogue;
	}

	public void setEpilogue(String epilogue) {
		this.epilogue = epilogue;
	}

	public String getPerface() {
		return perface;
	}

	public void setPerface(String perface) {
		this.perface = perface;
	}

	public String getAqnClassName() {
		return aqnClassName;
	}

	public void setAqnClassName(String aqnClassName) {
		this.aqnClassName = aqnClassName;
	}

	public int getQuestionQty() {
		return questionQty;
	}

	public void setQuestionQty(int questionQty) {
		this.questionQty = questionQty;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getAqnSummary() {
		return aqnSummary;
	}

	public void setAqnSummary(String aqnSummary) {
		this.aqnSummary = aqnSummary;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<AssessQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AssessQuestion> questions) {
		this.questions = questions;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}



}