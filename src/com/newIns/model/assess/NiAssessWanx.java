package com.newIns.model.assess;

import java.util.Date;

public class NiAssessWanx {
    private Integer deliveryid;

    private Integer aqnid;

    private Integer pagestatus;

    private Integer istop;

    private Integer showorder;

    private String operator;

    private Date utime;

    private Date ptime;
    
    private Date timer;

    private String comment;
    
    private String assessListCategory;
    
    
    
    public String getAssessListCategory() {
		return assessListCategory;
	}

	public void setAssessListCategory(String assessListCategory) {
		this.assessListCategory = assessListCategory;
	}

	public Date getTimer() {
		return timer;
	}

	public void setTimer(Date timer) {
		this.timer = timer;
	}

	public Integer getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Integer deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Integer getAqnid() {
        return aqnid;
    }

    public void setAqnid(Integer aqnid) {
        this.aqnid = aqnid;
    }

    public Integer getPagestatus() {
        return pagestatus;
    }

    public void setPagestatus(Integer pagestatus) {
        this.pagestatus = pagestatus;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}