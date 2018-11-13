package com.newIns.model.assess;

public class NiAssessModelSimple extends NiAssessModelSimpleKey {
    private Integer intervalbegin;

    private Integer intervalend;

    private String resultsummary;

    private String resultdetail;

    private String comment;

    public Integer getIntervalbegin() {
        return intervalbegin;
    }

    public void setIntervalbegin(Integer intervalbegin) {
        this.intervalbegin = intervalbegin;
    }

    public Integer getIntervalend() {
        return intervalend;
    }

    public void setIntervalend(Integer intervalend) {
        this.intervalend = intervalend;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}