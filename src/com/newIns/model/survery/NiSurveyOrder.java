package com.newIns.model.survery;

import java.util.Date;

public class NiSurveyOrder {
    private Integer orderid;

    private Integer userid;

    private Integer sqnid;

    private Date orderctime;

    private Date answerbtime;

    private Date answeretime;

    private Byte orderstatus;

    private Integer sequencenum;

    private Integer awardid;

    private String reviewer;

    private Boolean answerchannel;

    private String comment;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSqnid() {
        return sqnid;
    }

    public void setSqnid(Integer sqnid) {
        this.sqnid = sqnid;
    }

    public Date getOrderctime() {
        return orderctime;
    }

    public void setOrderctime(Date orderctime) {
        this.orderctime = orderctime;
    }

    public Date getAnswerbtime() {
        return answerbtime;
    }

    public void setAnswerbtime(Date answerbtime) {
        this.answerbtime = answerbtime;
    }

    public Date getAnsweretime() {
        return answeretime;
    }

    public void setAnsweretime(Date answeretime) {
        this.answeretime = answeretime;
    }

    public Byte getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Byte orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getSequencenum() {
        return sequencenum;
    }

    public void setSequencenum(Integer sequencenum) {
        this.sequencenum = sequencenum;
    }

    public Integer getAwardid() {
        return awardid;
    }

    public void setAwardid(Integer awardid) {
        this.awardid = awardid;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Boolean getAnswerchannel() {
        return answerchannel;
    }

    public void setAnswerchannel(Boolean answerchannel) {
        this.answerchannel = answerchannel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}