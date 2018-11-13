package com.newIns.model.assess;

import java.util.Date;

public class NiAssessOrder {
    private Integer orderid;

    private Integer userid;

    private Integer aqnid;

    private Date orderctime;

    private Date answerbtime;

    private Date answeretime;

    private Byte orderstatus;

    private Integer sequencenum;

    private Integer awardid;

    private String reviewer;

    private Boolean answerchannel;

    private Integer totalscore;

    private String resultsummary;

    private String resultdetail;

    private Byte awardgetstatus;

    private Byte awardpaystatus;

    private Date awardgettime;

    private Date awardpaytime;

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

    public Integer getAqnid() {
        return aqnid;
    }

    public void setAqnid(Integer aqnid) {
        this.aqnid = aqnid;
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

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public String getResultsummary() {
        return resultsummary;
    }

    public void setResultsummary(String resultsummary) {
        this.resultsummary = resultsummary == null ? null : resultsummary.trim();
    }

    public String getResultdetail() {
        return resultdetail;
    }

    public void setResultdetail(String resultdetail) {
        this.resultdetail = resultdetail == null ? null : resultdetail.trim();
    }

    public Byte getAwardgetstatus() {
        return awardgetstatus;
    }

    public void setAwardgetstatus(Byte awardgetstatus) {
        this.awardgetstatus = awardgetstatus;
    }

    public Byte getAwardpaystatus() {
        return awardpaystatus;
    }

    public void setAwardpaystatus(Byte awardpaystatus) {
        this.awardpaystatus = awardpaystatus;
    }

    public Date getAwardgettime() {
        return awardgettime;
    }

    public void setAwardgettime(Date awardgettime) {
        this.awardgettime = awardgettime;
    }

    public Date getAwardpaytime() {
        return awardpaytime;
    }

    public void setAwardpaytime(Date awardpaytime) {
        this.awardpaytime = awardpaytime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}