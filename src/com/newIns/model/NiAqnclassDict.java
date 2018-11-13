package com.newIns.model;

import java.io.Serializable;

public class NiAqnclassDict implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer aqnclassid;

    private String aqnclassname;

    private String aqnclassdes;

    private String comment;

    public Integer getAqnclassid() {
        return aqnclassid;
    }

    public void setAqnclassid(Integer aqnclassid) {
        this.aqnclassid = aqnclassid;
    }

    public String getAqnclassname() {
        return aqnclassname;
    }

    public void setAqnclassname(String aqnclassname) {
        this.aqnclassname = aqnclassname == null ? null : aqnclassname.trim();
    }

    public String getAqnclassdes() {
        return aqnclassdes;
    }

    public void setAqnclassdes(String aqnclassdes) {
        this.aqnclassdes = aqnclassdes == null ? null : aqnclassdes.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}