package com.newIns.model.assess;

import java.io.Serializable;
import java.util.Date;

public class NiAssessQuestionnaire implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer aqnid;
    private Integer validity;
    private String aqnname;
    private Integer publisherid,authorId;
    private String publishername,epilogue,perface;
    private Integer aqnclassid,aqnCategory;  //测评问卷纬度类型
    private Date ctime,stime;
    private Integer staus,recommandqty,questionQty;
    private String filepath;
    private Integer validation;
    private Integer tag1id,tag2id,tag3id,tag4id,tag5id;
    private String picpath,aqnsummary,editor;
    private String subtitle,comment;
    private Integer showType,resutShowType;  // 1.整页显示，2.分页显示  | 结果页样式显示

    
    public Integer getResutShowType() {
		return resutShowType;
	}
	public void setResutShowType(Integer resutShowType) {
		this.resutShowType = resutShowType;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getAqnid() {
        return aqnid;
    }

    public void setAqnid(Integer aqnid) {
        this.aqnid = aqnid;
    }

    public String getAqnname() {
        return aqnname;
    }

    public void setAqnname(String aqnname) {
        this.aqnname = aqnname == null ? null : aqnname.trim();
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public Integer getAqnCategory() {
		return aqnCategory;
	}

	public void setAqnCategory(Integer aqnCategory) {
		this.aqnCategory = aqnCategory;
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

    public Integer getAqnclassid() {
        return aqnclassid;
    }

    public void setAqnclassid(Integer aqnclassid) {
        this.aqnclassid = aqnclassid;
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

    public Integer getStaus() {
        return staus;
    }

    public void setStaus(Integer staus) {
        this.staus = staus;
    }

    public Integer getRecommandqty() {
        return recommandqty;
    }

    public void setRecommandqty(Integer recommandqty) {
        this.recommandqty = recommandqty;
    }

    public Integer getQuestionQty() {
		return questionQty;
	}

	public void setQuestionQty(Integer questionQty) {
		this.questionQty = questionQty;
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

    public String getAqnsummary() {
        return aqnsummary;
    }

    public void setAqnsummary(String aqnsummary) {
        this.aqnsummary = aqnsummary == null ? null : aqnsummary.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getComment() {
        return comment;
    }

    public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}