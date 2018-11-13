package com.newIns.model.report;

public class NiReportClass {
    private Integer reportclassid;

    private String reportclassname;

    private String reportclassdes;

    private String comment;

    public Integer getReportclassid() {
        return reportclassid;
    }

    public void setReportclassid(Integer reportclassid) {
        this.reportclassid = reportclassid;
    }

    public String getReportclassname() {
        return reportclassname;
    }

    public void setReportclassname(String reportclassname) {
        this.reportclassname = reportclassname == null ? null : reportclassname.trim();
    }

    public String getReportclassdes() {
        return reportclassdes;
    }

    public void setReportclassdes(String reportclassdes) {
        this.reportclassdes = reportclassdes == null ? null : reportclassdes.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}