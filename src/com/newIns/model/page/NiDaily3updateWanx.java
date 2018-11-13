package com.newIns.model.page;

import java.util.Date;

public class NiDaily3updateWanx extends NiDaily3updateWanxKey {
    private Integer qnid;

    private Integer pagestatus;

    private Integer istop;

    private Integer showorder;

    private String operator;

    private Date utime;

    private Date ptime;
    
    private Date timer;

    private String comment;  //类型
    
    private Integer itemId,reportId,tweetId; // 主键, 报告id , 文章id 
    
    
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Integer getTweetId() {
		return tweetId;
	}

	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}

	public Date getTimer() {
		return timer;
	}

	public void setTimer(Date timer) {
		this.timer = timer;
	}

	public Integer getQnid() {
        return qnid;
    }

    public void setQnid(Integer qnid) {
        this.qnid = qnid;
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