package com.newIns.model;

public class NiVqnclassDict {
    private Integer vqnclassid;

    private String vqnclassname;

    private String vqnclassdes;

    private String comment;

    public Integer getVqnclassid() {
        return vqnclassid;
    }

    public void setVqnclassid(Integer vqnclassid) {
        this.vqnclassid = vqnclassid;
    }

    public String getVqnclassname() {
        return vqnclassname;
    }

    public void setVqnclassname(String vqnclassname) {
        this.vqnclassname = vqnclassname == null ? null : vqnclassname.trim();
    }

    public String getVqnclassdes() {
        return vqnclassdes;
    }

    public void setVqnclassdes(String vqnclassdes) {
        this.vqnclassdes = vqnclassdes == null ? null : vqnclassdes.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}