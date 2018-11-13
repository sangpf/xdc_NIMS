/**
 * 
 */
package com.newIns.model.assess;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @Description 与接口对应的question model
 * @author Guan
 * @time 2016年6月28日 下午7:56:43
 */
@Component(value = "AssessQuestion")
public class AssessQuestion {
	int aqId, questionNum, questionType, required, optionNum, isSelfDefine;
	String aqTitle, correctAnswer;
	List<AssessQuestionOption> options;

	public int getAqId() {
		return aqId;
	}

	public void setAqId(int aqId) {
		this.aqId = aqId;
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

	public String getAqTitle() {
		return aqTitle;
	}

	public void setAqTitle(String aqTitle) {
		this.aqTitle = aqTitle;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<AssessQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<AssessQuestionOption> options) {
		this.options = options;
	}

}
