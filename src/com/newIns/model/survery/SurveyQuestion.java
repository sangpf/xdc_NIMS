/**
 * 
 */
package com.newIns.model.survery;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @Description 与接口对应的question model 用于管理后台问卷预览
 * @author Guan
 * @time 2016年6月28日 下午7:56:43
 */
@Component(value = "SurveyQuestion")
public class SurveyQuestion {
	int sqId,questionNum, questionType, required, optionNum, isSelfDefine;
	String sqTitle, correctAnswer,answerAnalysis,userAnswer;
	List<SurveyQuestionOption> options;

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getAnswerAnalysis() {
		return answerAnalysis;
	}

	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}

	public int getSqId() {
		return sqId;
	}

	public void setSqId(int sqId) {
		this.sqId = sqId;
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public int getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}

	public int getIsSelfDefine() {
		return isSelfDefine;
	}

	public void setIsSelfDefine(int isSelfDefine) {
		this.isSelfDefine = isSelfDefine;
	}

	public String getSqTitle() {
		return sqTitle;
	}

	public void setSqTitle(String sqTitle) {
		this.sqTitle = sqTitle;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<SurveyQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<SurveyQuestionOption> options) {
		this.options = options;
	}





}
