package com.newIns.model;

public class NiSqnclassDict {
    private Integer sqnclassid;

    private String sqnclassname;

    private String sqnclassdes;

    private String comment;

    public Integer getSqnclassid() {
        return sqnclassid;
    }

    public void setSqnclassid(Integer sqnclassid) {
        this.sqnclassid = sqnclassid;
    }

    public String getSqnclassname() {
        return sqnclassname;
    }

    public void setSqnclassname(String sqnclassname) {
        this.sqnclassname = sqnclassname == null ? null : sqnclassname.trim();
    }

    public String getSqnclassdes() {
        return sqnclassdes;
    }

    public void setSqnclassdes(String sqnclassdes) {
        this.sqnclassdes = sqnclassdes == null ? null : sqnclassdes.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}