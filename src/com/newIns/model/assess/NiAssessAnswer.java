package com.newIns.model.assess;

import java.util.Date;

public class NiAssessAnswer extends NiAssessAnswerKey {
    private Integer aqnid;

    private String choice;

    private String selfdefine;

    private Integer score;

    private Date duration;

    private String comment;

    public Integer getAqnid() {
        return aqnid;
    }

    public void setAqnid(Integer aqnid) {
        this.aqnid = aqnid;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice == null ? null : choice.trim();
    }

    public String getSelfdefine() {
        return selfdefine;
    }

    public void setSelfdefine(String selfdefine) {
        this.selfdefine = selfdefine == null ? null : selfdefine.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}