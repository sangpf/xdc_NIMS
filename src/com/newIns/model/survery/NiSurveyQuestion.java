package com.newIns.model.survery;

import java.io.Serializable;

public class NiSurveyQuestion implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer validity;
	private Integer sqid;

    private String sqtitle;

    private Integer sqnid;

    private String qImgUrl;

    private Integer questionnum;

    private Integer vieworder;

    private Integer questiontype;

    private Integer required;

    private Integer optionnum;
    
    private int optMinNum,optMaxNum;  //多选限制

    private Integer isselfdefine;

    private String correctanswer,answerAnalysis;

    private String option1des;

    private Integer option1link;

    private String option1feedback;

    private Integer option1tagid;
    
    private Integer option1PreferValue,option2PreferValue,option3PreferValue,option4PreferValue,option5PreferValue,
    option6PreferValue,option7PreferValue,option8PreferValue,option9PreferValue,option10PreferValue,option11PreferValue,option12PreferValue,
    option13PreferValue,option14PreferValue,option15PreferValue,option16PreferValue,option17PreferValue,option18PreferValue,option19PreferValue,
    option20PreferValue,option21PreferValue,option22PreferValue,option23PreferValue,option24PreferValue,option25PreferValue,option26PreferValue;

    private String option2des;

    private Integer option2link;

    private String option2feedback;

    private Integer option2tagid;

    private String option3des;

    private Integer option3link;

    private String option3feedback;

    private Integer option3tagid;

    private String option4des;

    private Integer option4link;

    private String option4feedback;

    private Integer option4tagid;

    private String option5des;

    private Integer option5link;

    private String option5feedback;

    private Integer option5tagid;

    private String option6des;

    private Integer option6link;

    private String option6feedback;

    private Integer option6tagid;

    private String option7des;

    private Integer option7link;

    private String option7feedback;

    private Integer option7tagid;

    private String option8des;

    private Integer option8link;

    private String option8feedback;

    private Integer option8tagid;

    private String option9des;

    private Integer option9link;

    private String option9feedback;

    private Integer option9tagid;

    private String option10des;

    private Integer option10link;

    private String option10feedback;

    private Integer option10tagid;

    private String option11des;

    private Integer option11link;

    private String option11feedback;

    private Integer option11tagid;

    private String option12des;

    private Integer option12link;

    private String option12feedback;

    private Integer option12tagid;

    private String option13des;

    private Integer option13link;

    private String option13feedback;

    private Integer option13tagid;

    private String option14des;

    private Integer option14link;

    private String option14feedback;

    private Integer option14tagid;

    private String option15des;

    private Integer option15link;

    private String option15feedback;

    private Integer option15tagid;

    private String option16des;

    private Integer option16link;

    private String option16feedback;

    private Integer option16tagid;

    private String option17des;

    private Integer option17link;

    private String option17feedback;

    private Integer option17tagid;

    private String option18des;

    private Integer option18link;

    private String option18feedback;

    private Integer option18tagid;

    private String option19des;

    private Integer option19link;

    private String option19feedback;

    private Integer option19tagid;

    private String option20des;

    private Integer option20link;

    private String option20feedback;

    private Integer option20tagid;

    private String option21des;

    private Integer option21link;

    private String option21feedback;

    private Integer option21tagid;

    private String option22des;

    private Integer option22link;

    private String option22feedback;

    private Integer option22tagid;

    private String option23des;

    private Integer option23link;

    private String option23feedback;

    private Integer option23tagid;

    private String option24des;

    private Integer option24link;

    private String option24feedback;

    private Integer option24tagid;

    private String option25des;

    private Integer option25link;

    private String option25feedback;

    private Integer option25tagid;

    private String option26des;

    private Integer option26link;

    private String option26feedback;

    private Integer option26tagid;

    private String comment;

	public int getOptMinNum() {
		return optMinNum;
	}

	public void setOptMinNum(int optMinNum) {
		this.optMinNum = optMinNum;
	}

	public int getOptMaxNum() {
		return optMaxNum;
	}

	public void setOptMaxNum(int optMaxNum) {
		this.optMaxNum = optMaxNum;
	}

	public Integer getSqid() {
        return sqid;
    }

    public void setSqid(Integer sqid) {
        this.sqid = sqid;
    }

    public String getSqtitle() {
        return sqtitle;
    }

    public void setSqtitle(String sqtitle) {
        this.sqtitle = sqtitle == null ? null : sqtitle.trim();
    }

    public Integer getSqnid() {
        return sqnid;
    }

    public void setSqnid(Integer sqnid) {
        this.sqnid = sqnid;
    }

    public String getqImgUrl() {
		return qImgUrl;
	}

	public void setqImgUrl(String qImgUrl) {
		this.qImgUrl = qImgUrl;
	}

	public Integer getQuestionnum() {
        return questionnum;
    }

    public void setQuestionnum(Integer questionnum) {
        this.questionnum = questionnum;
    }

    public Integer getVieworder() {
        return vieworder;
    }

    public void setVieworder(Integer vieworder) {
        this.vieworder = vieworder;
    }

    public Integer getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(Integer questiontype) {
        this.questiontype = questiontype;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getOptionnum() {
        return optionnum;
    }

    public void setOptionnum(Integer optionnum) {
        this.optionnum = optionnum;
    }

    public Integer getIsselfdefine() {
        return isselfdefine;
    }

    public void setIsselfdefine(Integer isselfdefine) {
        this.isselfdefine = isselfdefine;
    }

    public String getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer == null ? null : correctanswer.trim();
    }

    public String getOption1des() {
        return option1des;
    }

    public void setOption1des(String option1des) {
        this.option1des = option1des == null ? null : option1des.trim();
    }

    public Integer getOption1link() {
        return option1link;
    }

    public void setOption1link(Integer option1link) {
        this.option1link = option1link;
    }

    public String getOption1feedback() {
        return option1feedback;
    }

    public void setOption1feedback(String option1feedback) {
        this.option1feedback = option1feedback == null ? null : option1feedback.trim();
    }

    public Integer getOption1tagid() {
        return option1tagid;
    }

    public void setOption1tagid(Integer option1tagid) {
        this.option1tagid = option1tagid;
    }

    public String getOption2des() {
        return option2des;
    }

    public void setOption2des(String option2des) {
        this.option2des = option2des == null ? null : option2des.trim();
    }

    public Integer getOption2link() {
        return option2link;
    }

    public void setOption2link(Integer option2link) {
        this.option2link = option2link;
    }

    public String getOption2feedback() {
        return option2feedback;
    }

    public void setOption2feedback(String option2feedback) {
        this.option2feedback = option2feedback == null ? null : option2feedback.trim();
    }

    public Integer getOption2tagid() {
        return option2tagid;
    }

    public void setOption2tagid(Integer option2tagid) {
        this.option2tagid = option2tagid;
    }

    public String getOption3des() {
        return option3des;
    }

    public void setOption3des(String option3des) {
        this.option3des = option3des == null ? null : option3des.trim();
    }

    public Integer getOption3link() {
        return option3link;
    }

    public void setOption3link(Integer option3link) {
        this.option3link = option3link;
    }

    public String getOption3feedback() {
        return option3feedback;
    }

    public void setOption3feedback(String option3feedback) {
        this.option3feedback = option3feedback == null ? null : option3feedback.trim();
    }

    public Integer getOption3tagid() {
        return option3tagid;
    }

    public void setOption3tagid(Integer option3tagid) {
        this.option3tagid = option3tagid;
    }

    public String getOption4des() {
        return option4des;
    }

    public void setOption4des(String option4des) {
        this.option4des = option4des == null ? null : option4des.trim();
    }

    public Integer getOption4link() {
        return option4link;
    }

    public void setOption4link(Integer option4link) {
        this.option4link = option4link;
    }

    public String getOption4feedback() {
        return option4feedback;
    }

    public void setOption4feedback(String option4feedback) {
        this.option4feedback = option4feedback == null ? null : option4feedback.trim();
    }

    public Integer getOption4tagid() {
        return option4tagid;
    }

    public void setOption4tagid(Integer option4tagid) {
        this.option4tagid = option4tagid;
    }

    public String getOption5des() {
        return option5des;
    }

    public void setOption5des(String option5des) {
        this.option5des = option5des == null ? null : option5des.trim();
    }

    public Integer getOption5link() {
        return option5link;
    }

    public void setOption5link(Integer option5link) {
        this.option5link = option5link;
    }

    public String getOption5feedback() {
        return option5feedback;
    }

    public void setOption5feedback(String option5feedback) {
        this.option5feedback = option5feedback == null ? null : option5feedback.trim();
    }

    public Integer getOption5tagid() {
        return option5tagid;
    }

    public void setOption5tagid(Integer option5tagid) {
        this.option5tagid = option5tagid;
    }

    public String getOption6des() {
        return option6des;
    }

    public void setOption6des(String option6des) {
        this.option6des = option6des == null ? null : option6des.trim();
    }

    public Integer getOption6link() {
        return option6link;
    }

    public void setOption6link(Integer option6link) {
        this.option6link = option6link;
    }

    public String getOption6feedback() {
        return option6feedback;
    }

    public void setOption6feedback(String option6feedback) {
        this.option6feedback = option6feedback == null ? null : option6feedback.trim();
    }

    public Integer getOption6tagid() {
        return option6tagid;
    }

    public void setOption6tagid(Integer option6tagid) {
        this.option6tagid = option6tagid;
    }

    public String getOption7des() {
        return option7des;
    }

    public void setOption7des(String option7des) {
        this.option7des = option7des == null ? null : option7des.trim();
    }

    public Integer getOption7link() {
        return option7link;
    }

    public void setOption7link(Integer option7link) {
        this.option7link = option7link;
    }

    public String getOption7feedback() {
        return option7feedback;
    }

    public void setOption7feedback(String option7feedback) {
        this.option7feedback = option7feedback == null ? null : option7feedback.trim();
    }

    public Integer getOption7tagid() {
        return option7tagid;
    }

    public void setOption7tagid(Integer option7tagid) {
        this.option7tagid = option7tagid;
    }

    public String getOption8des() {
        return option8des;
    }

    public void setOption8des(String option8des) {
        this.option8des = option8des == null ? null : option8des.trim();
    }

    public Integer getOption8link() {
        return option8link;
    }

    public void setOption8link(Integer option8link) {
        this.option8link = option8link;
    }

    public String getOption8feedback() {
        return option8feedback;
    }

    public void setOption8feedback(String option8feedback) {
        this.option8feedback = option8feedback == null ? null : option8feedback.trim();
    }

    public Integer getOption8tagid() {
        return option8tagid;
    }

    public void setOption8tagid(Integer option8tagid) {
        this.option8tagid = option8tagid;
    }

    public String getOption9des() {
        return option9des;
    }

    public void setOption9des(String option9des) {
        this.option9des = option9des == null ? null : option9des.trim();
    }

    public Integer getOption9link() {
        return option9link;
    }

    public void setOption9link(Integer option9link) {
        this.option9link = option9link;
    }

    public String getOption9feedback() {
        return option9feedback;
    }

    public void setOption9feedback(String option9feedback) {
        this.option9feedback = option9feedback == null ? null : option9feedback.trim();
    }

    public Integer getOption9tagid() {
        return option9tagid;
    }

    public void setOption9tagid(Integer option9tagid) {
        this.option9tagid = option9tagid;
    }

    public String getOption10des() {
        return option10des;
    }

    public void setOption10des(String option10des) {
        this.option10des = option10des == null ? null : option10des.trim();
    }

    public Integer getOption10link() {
        return option10link;
    }

    public void setOption10link(Integer option10link) {
        this.option10link = option10link;
    }

    public String getOption10feedback() {
        return option10feedback;
    }

    public void setOption10feedback(String option10feedback) {
        this.option10feedback = option10feedback == null ? null : option10feedback.trim();
    }

    public Integer getOption10tagid() {
        return option10tagid;
    }

    public void setOption10tagid(Integer option10tagid) {
        this.option10tagid = option10tagid;
    }

    public String getOption11des() {
        return option11des;
    }

    public void setOption11des(String option11des) {
        this.option11des = option11des == null ? null : option11des.trim();
    }

    public Integer getOption11link() {
        return option11link;
    }

    public void setOption11link(Integer option11link) {
        this.option11link = option11link;
    }

    public String getOption11feedback() {
        return option11feedback;
    }

    public void setOption11feedback(String option11feedback) {
        this.option11feedback = option11feedback == null ? null : option11feedback.trim();
    }

    public Integer getOption11tagid() {
        return option11tagid;
    }

    public void setOption11tagid(Integer option11tagid) {
        this.option11tagid = option11tagid;
    }

    public String getOption12des() {
        return option12des;
    }

    public void setOption12des(String option12des) {
        this.option12des = option12des == null ? null : option12des.trim();
    }

    public Integer getOption12link() {
        return option12link;
    }

    public void setOption12link(Integer option12link) {
        this.option12link = option12link;
    }

    public String getOption12feedback() {
        return option12feedback;
    }

    public void setOption12feedback(String option12feedback) {
        this.option12feedback = option12feedback == null ? null : option12feedback.trim();
    }

    public Integer getOption12tagid() {
        return option12tagid;
    }

    public void setOption12tagid(Integer option12tagid) {
        this.option12tagid = option12tagid;
    }

    public String getOption13des() {
        return option13des;
    }

    public void setOption13des(String option13des) {
        this.option13des = option13des == null ? null : option13des.trim();
    }

    public Integer getOption13link() {
        return option13link;
    }

    public void setOption13link(Integer option13link) {
        this.option13link = option13link;
    }

    public String getOption13feedback() {
        return option13feedback;
    }

    public void setOption13feedback(String option13feedback) {
        this.option13feedback = option13feedback == null ? null : option13feedback.trim();
    }

    public Integer getOption13tagid() {
        return option13tagid;
    }

    public void setOption13tagid(Integer option13tagid) {
        this.option13tagid = option13tagid;
    }

    public String getOption14des() {
        return option14des;
    }

    public void setOption14des(String option14des) {
        this.option14des = option14des == null ? null : option14des.trim();
    }

    public Integer getOption14link() {
        return option14link;
    }

    public void setOption14link(Integer option14link) {
        this.option14link = option14link;
    }

    public String getOption14feedback() {
        return option14feedback;
    }

    public void setOption14feedback(String option14feedback) {
        this.option14feedback = option14feedback == null ? null : option14feedback.trim();
    }

    public Integer getOption14tagid() {
        return option14tagid;
    }

    public void setOption14tagid(Integer option14tagid) {
        this.option14tagid = option14tagid;
    }

    public String getOption15des() {
        return option15des;
    }

    public void setOption15des(String option15des) {
        this.option15des = option15des == null ? null : option15des.trim();
    }

    public Integer getOption15link() {
        return option15link;
    }

    public void setOption15link(Integer option15link) {
        this.option15link = option15link;
    }

    public String getOption15feedback() {
        return option15feedback;
    }

    public void setOption15feedback(String option15feedback) {
        this.option15feedback = option15feedback == null ? null : option15feedback.trim();
    }

    public Integer getOption15tagid() {
        return option15tagid;
    }

    public void setOption15tagid(Integer option15tagid) {
        this.option15tagid = option15tagid;
    }

    public String getOption16des() {
        return option16des;
    }

    public void setOption16des(String option16des) {
        this.option16des = option16des == null ? null : option16des.trim();
    }

    public Integer getOption16link() {
        return option16link;
    }

    public void setOption16link(Integer option16link) {
        this.option16link = option16link;
    }

    public String getOption16feedback() {
        return option16feedback;
    }

    public void setOption16feedback(String option16feedback) {
        this.option16feedback = option16feedback == null ? null : option16feedback.trim();
    }

    public Integer getOption16tagid() {
        return option16tagid;
    }

    public void setOption16tagid(Integer option16tagid) {
        this.option16tagid = option16tagid;
    }

    public String getOption17des() {
        return option17des;
    }

    public void setOption17des(String option17des) {
        this.option17des = option17des == null ? null : option17des.trim();
    }

    public Integer getOption17link() {
        return option17link;
    }

    public void setOption17link(Integer option17link) {
        this.option17link = option17link;
    }

    public String getOption17feedback() {
        return option17feedback;
    }

    public void setOption17feedback(String option17feedback) {
        this.option17feedback = option17feedback == null ? null : option17feedback.trim();
    }

    public Integer getOption17tagid() {
        return option17tagid;
    }

    public void setOption17tagid(Integer option17tagid) {
        this.option17tagid = option17tagid;
    }

    public String getOption18des() {
        return option18des;
    }

    public void setOption18des(String option18des) {
        this.option18des = option18des == null ? null : option18des.trim();
    }

    public Integer getOption18link() {
        return option18link;
    }

    public void setOption18link(Integer option18link) {
        this.option18link = option18link;
    }

    public String getOption18feedback() {
        return option18feedback;
    }

    public void setOption18feedback(String option18feedback) {
        this.option18feedback = option18feedback == null ? null : option18feedback.trim();
    }

    public Integer getOption18tagid() {
        return option18tagid;
    }

    public void setOption18tagid(Integer option18tagid) {
        this.option18tagid = option18tagid;
    }

    public String getOption19des() {
        return option19des;
    }

    public void setOption19des(String option19des) {
        this.option19des = option19des == null ? null : option19des.trim();
    }

    public Integer getOption19link() {
        return option19link;
    }

    public void setOption19link(Integer option19link) {
        this.option19link = option19link;
    }

    public String getOption19feedback() {
        return option19feedback;
    }

    public void setOption19feedback(String option19feedback) {
        this.option19feedback = option19feedback == null ? null : option19feedback.trim();
    }

    public Integer getOption19tagid() {
        return option19tagid;
    }

    public void setOption19tagid(Integer option19tagid) {
        this.option19tagid = option19tagid;
    }

    public String getOption20des() {
        return option20des;
    }

    public void setOption20des(String option20des) {
        this.option20des = option20des == null ? null : option20des.trim();
    }

    public Integer getOption20link() {
        return option20link;
    }

    public void setOption20link(Integer option20link) {
        this.option20link = option20link;
    }

    public String getOption20feedback() {
        return option20feedback;
    }

    public Integer getValidity() {
		return validity;
	}
    
	public Integer getOption1PreferValue() {
		return option1PreferValue;
	}

	public void setOption1PreferValue(Integer option1PreferValue) {
		this.option1PreferValue = option1PreferValue;
	}

	public Integer getOption2PreferValue() {
		return option2PreferValue;
	}

	public void setOption2PreferValue(Integer option2PreferValue) {
		this.option2PreferValue = option2PreferValue;
	}

	public Integer getOption3PreferValue() {
		return option3PreferValue;
	}

	public void setOption3PreferValue(Integer option3PreferValue) {
		this.option3PreferValue = option3PreferValue;
	}

	public Integer getOption4PreferValue() {
		return option4PreferValue;
	}

	public void setOption4PreferValue(Integer option4PreferValue) {
		this.option4PreferValue = option4PreferValue;
	}

	public Integer getOption5PreferValue() {
		return option5PreferValue;
	}

	public void setOption5PreferValue(Integer option5PreferValue) {
		this.option5PreferValue = option5PreferValue;
	}

	public Integer getOption6PreferValue() {
		return option6PreferValue;
	}

	public void setOption6PreferValue(Integer option6PreferValue) {
		this.option6PreferValue = option6PreferValue;
	}

	public Integer getOption7PreferValue() {
		return option7PreferValue;
	}

	public void setOption7PreferValue(Integer option7PreferValue) {
		this.option7PreferValue = option7PreferValue;
	}

	public Integer getOption8PreferValue() {
		return option8PreferValue;
	}

	public void setOption8PreferValue(Integer option8PreferValue) {
		this.option8PreferValue = option8PreferValue;
	}

	public Integer getOption9PreferValue() {
		return option9PreferValue;
	}

	public void setOption9PreferValue(Integer option9PreferValue) {
		this.option9PreferValue = option9PreferValue;
	}

	public Integer getOption10PreferValue() {
		return option10PreferValue;
	}

	public void setOption10PreferValue(Integer option10PreferValue) {
		this.option10PreferValue = option10PreferValue;
	}

	public Integer getOption11PreferValue() {
		return option11PreferValue;
	}

	public void setOption11PreferValue(Integer option11PreferValue) {
		this.option11PreferValue = option11PreferValue;
	}

	public Integer getOption12PreferValue() {
		return option12PreferValue;
	}

	public void setOption12PreferValue(Integer option12PreferValue) {
		this.option12PreferValue = option12PreferValue;
	}

	public Integer getOption13PreferValue() {
		return option13PreferValue;
	}

	public void setOption13PreferValue(Integer option13PreferValue) {
		this.option13PreferValue = option13PreferValue;
	}

	public Integer getOption14PreferValue() {
		return option14PreferValue;
	}

	public void setOption14PreferValue(Integer option14PreferValue) {
		this.option14PreferValue = option14PreferValue;
	}

	public Integer getOption15PreferValue() {
		return option15PreferValue;
	}

	public void setOption15PreferValue(Integer option15PreferValue) {
		this.option15PreferValue = option15PreferValue;
	}

	public Integer getOption16PreferValue() {
		return option16PreferValue;
	}

	public void setOption16PreferValue(Integer option16PreferValue) {
		this.option16PreferValue = option16PreferValue;
	}

	public Integer getOption17PreferValue() {
		return option17PreferValue;
	}

	public void setOption17PreferValue(Integer option17PreferValue) {
		this.option17PreferValue = option17PreferValue;
	}

	public Integer getOption18PreferValue() {
		return option18PreferValue;
	}

	public void setOption18PreferValue(Integer option18PreferValue) {
		this.option18PreferValue = option18PreferValue;
	}

	public Integer getOption19PreferValue() {
		return option19PreferValue;
	}

	public void setOption19PreferValue(Integer option19PreferValue) {
		this.option19PreferValue = option19PreferValue;
	}

	public Integer getOption20PreferValue() {
		return option20PreferValue;
	}

	public void setOption20PreferValue(Integer option20PreferValue) {
		this.option20PreferValue = option20PreferValue;
	}

	public Integer getOption21PreferValue() {
		return option21PreferValue;
	}

	public void setOption21PreferValue(Integer option21PreferValue) {
		this.option21PreferValue = option21PreferValue;
	}

	public Integer getOption22PreferValue() {
		return option22PreferValue;
	}

	public void setOption22PreferValue(Integer option22PreferValue) {
		this.option22PreferValue = option22PreferValue;
	}

	public Integer getOption23PreferValue() {
		return option23PreferValue;
	}

	public void setOption23PreferValue(Integer option23PreferValue) {
		this.option23PreferValue = option23PreferValue;
	}

	public Integer getOption24PreferValue() {
		return option24PreferValue;
	}

	public void setOption24PreferValue(Integer option24PreferValue) {
		this.option24PreferValue = option24PreferValue;
	}

	public Integer getOption25PreferValue() {
		return option25PreferValue;
	}

	public void setOption25PreferValue(Integer option25PreferValue) {
		this.option25PreferValue = option25PreferValue;
	}

	public Integer getOption26PreferValue() {
		return option26PreferValue;
	}

	public void setOption26PreferValue(Integer option26PreferValue) {
		this.option26PreferValue = option26PreferValue;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public void setOption20feedback(String option20feedback) {
        this.option20feedback = option20feedback == null ? null : option20feedback.trim();
    }

    public Integer getOption20tagid() {
        return option20tagid;
    }

    public void setOption20tagid(Integer option20tagid) {
        this.option20tagid = option20tagid;
    }

    public String getOption21des() {
        return option21des;
    }

    public void setOption21des(String option21des) {
        this.option21des = option21des == null ? null : option21des.trim();
    }

    public Integer getOption21link() {
        return option21link;
    }

    public void setOption21link(Integer option21link) {
        this.option21link = option21link;
    }

    public String getOption21feedback() {
        return option21feedback;
    }

    public void setOption21feedback(String option21feedback) {
        this.option21feedback = option21feedback == null ? null : option21feedback.trim();
    }

    public Integer getOption21tagid() {
        return option21tagid;
    }

    public void setOption21tagid(Integer option21tagid) {
        this.option21tagid = option21tagid;
    }

    public String getOption22des() {
        return option22des;
    }

    public void setOption22des(String option22des) {
        this.option22des = option22des == null ? null : option22des.trim();
    }

    public Integer getOption22link() {
        return option22link;
    }

    public void setOption22link(Integer option22link) {
        this.option22link = option22link;
    }

    public String getOption22feedback() {
        return option22feedback;
    }

    public void setOption22feedback(String option22feedback) {
        this.option22feedback = option22feedback == null ? null : option22feedback.trim();
    }

    public Integer getOption22tagid() {
        return option22tagid;
    }

    public void setOption22tagid(Integer option22tagid) {
        this.option22tagid = option22tagid;
    }

    public String getOption23des() {
        return option23des;
    }

    public void setOption23des(String option23des) {
        this.option23des = option23des == null ? null : option23des.trim();
    }

    public Integer getOption23link() {
        return option23link;
    }

    public void setOption23link(Integer option23link) {
        this.option23link = option23link;
    }

    public String getOption23feedback() {
        return option23feedback;
    }

    public void setOption23feedback(String option23feedback) {
        this.option23feedback = option23feedback == null ? null : option23feedback.trim();
    }

    public Integer getOption23tagid() {
        return option23tagid;
    }

    public void setOption23tagid(Integer option23tagid) {
        this.option23tagid = option23tagid;
    }

    public String getOption24des() {
        return option24des;
    }

    public void setOption24des(String option24des) {
        this.option24des = option24des == null ? null : option24des.trim();
    }

    public Integer getOption24link() {
        return option24link;
    }

    public void setOption24link(Integer option24link) {
        this.option24link = option24link;
    }

    public String getOption24feedback() {
        return option24feedback;
    }

    public void setOption24feedback(String option24feedback) {
        this.option24feedback = option24feedback == null ? null : option24feedback.trim();
    }

    public Integer getOption24tagid() {
        return option24tagid;
    }

    public void setOption24tagid(Integer option24tagid) {
        this.option24tagid = option24tagid;
    }

    public String getOption25des() {
        return option25des;
    }

    public void setOption25des(String option25des) {
        this.option25des = option25des == null ? null : option25des.trim();
    }

    public Integer getOption25link() {
        return option25link;
    }

    public void setOption25link(Integer option25link) {
        this.option25link = option25link;
    }

    public String getOption25feedback() {
        return option25feedback;
    }

    public void setOption25feedback(String option25feedback) {
        this.option25feedback = option25feedback == null ? null : option25feedback.trim();
    }

    public Integer getOption25tagid() {
        return option25tagid;
    }

    public void setOption25tagid(Integer option25tagid) {
        this.option25tagid = option25tagid;
    }

    public String getOption26des() {
        return option26des;
    }

    public void setOption26des(String option26des) {
        this.option26des = option26des == null ? null : option26des.trim();
    }

    public Integer getOption26link() {
        return option26link;
    }

    public void setOption26link(Integer option26link) {
        this.option26link = option26link;
    }

    public String getOption26feedback() {
        return option26feedback;
    }

    public void setOption26feedback(String option26feedback) {
        this.option26feedback = option26feedback == null ? null : option26feedback.trim();
    }

    public Integer getOption26tagid() {
        return option26tagid;
    }

    public void setOption26tagid(Integer option26tagid) {
        this.option26tagid = option26tagid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

	public String getAnswerAnalysis() {
		return answerAnalysis;
	}

	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}

	public String getOptionDesByIndex(int OptionIndex){
    	switch(OptionIndex){
    	case 1:
    		return option1des;
    	case 2:
    		return option2des;
    	case 3:
    		return option3des;
    	case 4:
    		return option4des;
    	case 5:
    		return option5des;
    	case 6:
    		return option6des;
    	case 7:
    		return option7des;
    	case 8:
    		return option8des;
    	case 9:
    		return option9des;
    	case 10:
    		return option10des;
    	case 11:
    		return option11des;
    	case 12:
    		return option12des;
    	case 13:
    		return option13des;
    	case 14:
    		return option14des;
    	case 15:
    		return option15des;
    	case 16:
    		return option16des;
    	case 17:
    		return option17des;
    	case 18:
    		return option18des;
    	case 19:
    		return option19des;
    	case 20:
    		return option20des;
    	case 21:
    		return option21des;
    	case 22:
    		return option22des;
    	case 23:
    		return option23des;
    	case 24:
    		return option24des;
    	case 25:
    		return option25des;
    	case 26:
    		return option26des;

    	default:
    		return "hehe";
    	}
    }
	
	public String setOptionDesByIndex(int OptionIndex){
    	switch(OptionIndex){
    	case 1:
    		return option1des;
    	case 2:
    		return option2des;
    	case 3:
    		return option3des;
    	case 4:
    		return option4des;
    	case 5:
    		return option5des;
    	case 6:
    		return option6des;
    	case 7:
    		return option7des;
    	case 8:
    		return option8des;
    	case 9:
    		return option9des;
    	case 10:
    		return option10des;
    	case 11:
    		return option11des;
    	case 12:
    		return option12des;
    	case 13:
    		return option13des;
    	case 14:
    		return option14des;
    	case 15:
    		return option15des;
    	case 16:
    		return option16des;
    	case 17:
    		return option17des;
    	case 18:
    		return option18des;
    	case 19:
    		return option19des;
    	case 20:
    		return option20des;
    	case 21:
    		return option21des;
    	case 22:
    		return option22des;
    	case 23:
    		return option23des;
    	case 24:
    		return option24des;
    	case 25:
    		return option25des;
    	case 26:
    		return option26des;

    	default:
    		return "hehe";
    	}
    }

    
}