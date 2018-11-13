package com.newIns.model.vote;

import java.util.Date;

public class NiVoteQuestionnaire {
    private Integer vqnid;
    private Integer validity;
    private String vqnname;

    private Integer publisherid;

    private String publishername;

    private String epilogue;

    private String perface;

    private Integer vqnclassid;

    private Date ctime;

    private Date stime;

    private Integer status;

    private Integer recommandqty;

    private String filepath;

    private Integer validation;

    private Integer tag1id;

    private Integer tag2id;

    private Integer tag3id;

    private Integer tag4id;

    private Integer tag5id;

    private String picpath;

    private String vqnsummary;

    private String editor;

    private String vqtitle;

    private String qImgUrl;

    private Integer questiontype;

    private Integer required;

    private Integer optionnum;
    
    private int optMinNum,optMaxNum;

    private Integer isselfdefine;

    private String correctanswer;

    private String option1des;

    private String option1feedback;

    private Integer option1tagid;
    
    private String option1Pic,option2Pic,option3Pic,option4Pic,option5Pic,option6Pic,option7Pic,option8Pic,option9Pic,option10Pic
    ,option11Pic,option12Pic,option13Pic,option14Pic,option15Pic,option16Pic
    ,option17Pic,option18Pic,option19Pic,option20Pic,option21Pic,option22Pic,option23Pic,option24Pic,option25Pic,option26Pic;

    private String option2des;

    private String option2feedback;

    private Integer option2tagid;

    private String option3des;

    private String option3feedback;

    private Integer option3tagid;

    private String option4des;

    private String option4feedback;

    private Integer option4tagid;

    private String option5des;

    private String option5feedback;

    private Integer option5tagid;

    private String option6des;

    private String option6feedback;

    private Integer option6tagid;

    private String option7des;

    private String option7feedback;

    private Integer option7tagid;

    private String option8des;

    private String option8feedback;

    private Integer option8tagid;

    private String option9des;

    private String option9feedback;

    private Integer option9tagid;

    private String option10des;

    private String option10feedback;

    private Integer option10tagid;

    private String option11des;

    private String option11feedback;

    private Integer option11tagid;

    private String option12des;

    private String option12feedback;

    private Integer option12tagid;

    private String option13des;

    private String option13feedback;

    private Integer option13tagid;

    private String option14des;

    private String option14feedback;

    private Integer option14tagid;

    private String option15des;

    private String option15feedback;

    private Integer option15tagid;

    private String option16des;

    private String option16feedback;

    private Integer option16tagid;

    private String option17des;

    private String option17feedback;

    private Integer option17tagid;

    private String option18des;

    private String option18feedback;

    private Integer option18tagid;

    private String option19des;

    private String option19feedback;

    private Integer option19tagid;

    private String option20des;

    private String option20feedback;

    private Integer option20tagid;

    private String option21des;

    private String option21feedback;

    private Integer option21tagid;

    private String option22des;

    private String option22feedback;

    private Integer option22tagid;

    private String option23des;

    private String option23feedback;

    private Integer option23tagid;

    private String option24des;

    private String option24Feedback;

    private Integer option24tagid;

    private String option25des;

    private String option25feedback;

    private Integer option25tagid;

    private String option26des;

    private String option26feedback;

    private Integer option26tagid;

    private String comment;

    public Integer getVqnid() {
        return vqnid;
    }

    public void setVqnid(Integer vqnid) {
        this.vqnid = vqnid;
    }

    public String getVqnname() {
        return vqnname;
    }

    public void setVqnname(String vqnname) {
        this.vqnname = vqnname == null ? null : vqnname.trim();
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername == null ? null : publishername.trim();
    }

    public String getEpilogue() {
        return epilogue;
    }

    public void setEpilogue(String epilogue) {
        this.epilogue = epilogue == null ? null : epilogue.trim();
    }

    public String getPerface() {
        return perface;
    }

    public void setPerface(String perface) {
        this.perface = perface == null ? null : perface.trim();
    }

    public Integer getVqnclassid() {
        return vqnclassid;
    }

    public void setVqnclassid(Integer vqnclassid) {
        this.vqnclassid = vqnclassid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommandqty() {
        return recommandqty;
    }

    public void setRecommandqty(Integer recommandqty) {
        this.recommandqty = recommandqty;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Integer getValidation() {
        return validation;
    }

    public void setValidation(Integer validation) {
        this.validation = validation;
    }

    public Integer getTag1id() {
        return tag1id;
    }

    public void setTag1id(Integer tag1id) {
        this.tag1id = tag1id;
    }

    public Integer getTag2id() {
        return tag2id;
    }

    public void setTag2id(Integer tag2id) {
        this.tag2id = tag2id;
    }

    public Integer getTag3id() {
        return tag3id;
    }

    public void setTag3id(Integer tag3id) {
        this.tag3id = tag3id;
    }

    public Integer getTag4id() {
        return tag4id;
    }

    public void setTag4id(Integer tag4id) {
        this.tag4id = tag4id;
    }

    public Integer getTag5id() {
        return tag5id;
    }

    public void setTag5id(Integer tag5id) {
        this.tag5id = tag5id;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath == null ? null : picpath.trim();
    }

    public String getVqnsummary() {
        return vqnsummary;
    }

    public void setVqnsummary(String vqnsummary) {
        this.vqnsummary = vqnsummary == null ? null : vqnsummary.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getVqtitle() {
        return vqtitle;
    }

    public void setVqtitle(String vqtitle) {
        this.vqtitle = vqtitle == null ? null : vqtitle.trim();
    }

    public String getqImgUrl() {
		return qImgUrl;
	}

	public void setqImgUrl(String qImgUrl) {
		this.qImgUrl = qImgUrl;
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

    public String getOption20feedback() {
        return option20feedback;
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

    public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public void setOption24des(String option24des) {
        this.option24des = option24des == null ? null : option24des.trim();
    }

    public String getOption24Feedback() {
		return option24Feedback;
	}

	public void setOption24Feedback(String option24Feedback) {
		this.option24Feedback = option24Feedback;
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
    
    public String getOption1Pic() {
		return option1Pic;
	}

	public void setOption1Pic(String option1Pic) {
		this.option1Pic = option1Pic;
	}

	public String getOption2Pic() {
		return option2Pic;
	}

	public void setOption2Pic(String option2Pic) {
		this.option2Pic = option2Pic;
	}

	public String getOption3Pic() {
		return option3Pic;
	}

	public void setOption3Pic(String option3Pic) {
		this.option3Pic = option3Pic;
	}

	public String getOption4Pic() {
		return option4Pic;
	}

	public void setOption4Pic(String option4Pic) {
		this.option4Pic = option4Pic;
	}

	public String getOption5Pic() {
		return option5Pic;
	}

	public void setOption5Pic(String option5Pic) {
		this.option5Pic = option5Pic;
	}

	public String getOption6Pic() {
		return option6Pic;
	}

	public void setOption6Pic(String option6Pic) {
		this.option6Pic = option6Pic;
	}

	public String getOption7Pic() {
		return option7Pic;
	}

	public void setOption7Pic(String option7Pic) {
		this.option7Pic = option7Pic;
	}

	public String getOption8Pic() {
		return option8Pic;
	}

	public void setOption8Pic(String option8Pic) {
		this.option8Pic = option8Pic;
	}

	public String getOption9Pic() {
		return option9Pic;
	}

	public void setOption9Pic(String option9Pic) {
		this.option9Pic = option9Pic;
	}

	public String getOption10Pic() {
		return option10Pic;
	}

	public void setOption10Pic(String option10Pic) {
		this.option10Pic = option10Pic;
	}

	public String getOption11Pic() {
		return option11Pic;
	}

	public void setOption11Pic(String option11Pic) {
		this.option11Pic = option11Pic;
	}

	public String getOption12Pic() {
		return option12Pic;
	}

	public void setOption12Pic(String option12Pic) {
		this.option12Pic = option12Pic;
	}

	public String getOption13Pic() {
		return option13Pic;
	}

	public void setOption13Pic(String option13Pic) {
		this.option13Pic = option13Pic;
	}

	public String getOption14Pic() {
		return option14Pic;
	}

	public void setOption14Pic(String option14Pic) {
		this.option14Pic = option14Pic;
	}

	public String getOption15Pic() {
		return option15Pic;
	}

	public void setOption15Pic(String option15Pic) {
		this.option15Pic = option15Pic;
	}

	public String getOption16Pic() {
		return option16Pic;
	}

	public void setOption16Pic(String option16Pic) {
		this.option16Pic = option16Pic;
	}

	public String getOption17Pic() {
		return option17Pic;
	}

	public void setOption17Pic(String option17Pic) {
		this.option17Pic = option17Pic;
	}

	public String getOption18Pic() {
		return option18Pic;
	}

	public void setOption18Pic(String option18Pic) {
		this.option18Pic = option18Pic;
	}

	public String getOption19Pic() {
		return option19Pic;
	}

	public void setOption19Pic(String option19Pic) {
		this.option19Pic = option19Pic;
	}

	public String getOption20Pic() {
		return option20Pic;
	}

	public void setOption20Pic(String option20Pic) {
		this.option20Pic = option20Pic;
	}

	public String getOption21Pic() {
		return option21Pic;
	}

	public void setOption21Pic(String option21Pic) {
		this.option21Pic = option21Pic;
	}

	public String getOption22Pic() {
		return option22Pic;
	}

	public void setOption22Pic(String option22Pic) {
		this.option22Pic = option22Pic;
	}

	public String getOption23Pic() {
		return option23Pic;
	}

	public void setOption23Pic(String option23Pic) {
		this.option23Pic = option23Pic;
	}

	public String getOption24Pic() {
		return option24Pic;
	}

	public void setOption24Pic(String option24Pic) {
		this.option24Pic = option24Pic;
	}

	public String getOption25Pic() {
		return option25Pic;
	}

	public void setOption25Pic(String option25Pic) {
		this.option25Pic = option25Pic;
	}

	public String getOption26Pic() {
		return option26Pic;
	}

	public void setOption26Pic(String option26Pic) {
		this.option26Pic = option26Pic;
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
	
	public String getOptionPicByIndex(int OptionIndex){
    	switch(OptionIndex){
    	case 1:
    		return option1Pic;
    	case 2:
    		return option2Pic;
    	case 3:
    		return option3Pic;
    	case 4:
    		return option4Pic;
    	case 5:
    		return option5Pic;
    	case 6:
    		return option6Pic;
    	case 7:
    		return option7Pic;
    	case 8:
    		return option8Pic;
    	case 9:
    		return option9Pic;
    	case 10:
    		return option10Pic;
    	case 11:
    		return option11Pic;
    	case 12:
    		return option12Pic;
    	case 13:
    		return option13Pic;
    	case 14:
    		return option14Pic;
    	case 15:
    		return option15Pic;
    	case 16:
    		return option16Pic;
    	case 17:
    		return option17Pic;
    	case 18:
    		return option18Pic;
    	case 19:
    		return option19Pic;
    	case 20:
    		return option20Pic;
    	case 21:
    		return option21Pic;
    	case 22:
    		return option22Pic;
    	case 23:
    		return option23Pic;
    	case 24:
    		return option24Pic;
    	case 25:
    		return option25Pic;
    	case 26:
    		return option26Pic;

    	default:
    		return "hehe";
    	}
    }

}