package com.newIns.model.report;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lyr on 16/6/29.
 */
public class NiReport {

    private Integer reportId;
    private String reportTitle;
    private Integer reportStatus;
    private String reportImg;
    private Integer baseViewNum;
    private Integer commentNum;
    private Integer reportClassId;
    private String reportClassName;
    private NiReportClass reportClass;
    private Integer tag1Id;
    private Integer tag2Id;
    private Integer tag3Id;
    private Integer tag4Id;
    private String tag1Str;
    private String tag2Str;
    private String tag3Str;
    private String tag4Str;
    private Integer qnId;
    private Integer qnType;
    private Integer qnCollectedNum;
    private String summary;
    private String reportUrl;
    private String publisher;
    private Date pTime;
    private String comment;
    private String author;
    private String editor;
    
    private Timestamp cTime;

    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }



	public String getReportClassName() {
		return reportClassName;
	}

	public void setReportClassName(String reportClassName) {
		this.reportClassName = reportClassName;
	}

	public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportImg() {
        return reportImg;
    }

    public void setReportImg(String reportImg) {
        this.reportImg = reportImg;
    }


    public Integer getBaseViewNum() {
		return baseViewNum;
	}

	public void setBaseViewNum(Integer baseViewNum) {
		this.baseViewNum = baseViewNum;
	}

	public Integer getQnCollectedNum() {
		return qnCollectedNum;
	}

	public void setQnCollectedNum(Integer qnCollectedNum) {
		this.qnCollectedNum = qnCollectedNum;
	}

	public Integer getReportClassId() {
        return reportClassId;
    }

    public void setReportClassId(Integer reportClassId) {
        this.reportClassId = reportClassId;
    }

    public Integer getTag1Id() {
        return tag1Id;
    }

    public void setTag1Id(Integer tag1Id) {
        this.tag1Id = tag1Id;
    }

    public Integer getTag2Id() {
        return tag2Id;
    }

    public void setTag2Id(Integer tag2Id) {
        this.tag2Id = tag2Id;
    }

    public Integer getTag3Id() {
        return tag3Id;
    }

    public void setTag3Id(Integer tag3Id) {
        this.tag3Id = tag3Id;
    }

    public Integer getTag4Id() {
        return tag4Id;
    }

    public void setTag4Id(Integer tag4Id) {
        this.tag4Id = tag4Id;
    }

    public String getTag1Str() {
        return tag1Str;
    }

    public void setTag1Str(String tag1Str) {
        this.tag1Str = tag1Str;
    }

    public String getTag2Str() {
        return tag2Str;
    }

    public void setTag2Str(String tag2Str) {
        this.tag2Str = tag2Str;
    }

    public String getTag3Str() {
        return tag3Str;
    }

    public void setTag3Str(String tag3Str) {
        this.tag3Str = tag3Str;
    }

    public String getTag4Str() {
        return tag4Str;
    }

    public void setTag4Str(String tag4Str) {
        this.tag4Str = tag4Str;
    }

    public Integer getQnId() {
        return qnId;
    }

    public void setQnId(Integer qnId) {
        this.qnId = qnId;
    }

    public Integer getQnType() {
        return qnType;
    }

    public void setQnType(Integer qnType) {
        this.qnType = qnType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Date pTime) {
        this.pTime = pTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public NiReportClass getReportClass() {
        return reportClass;
    }

    public void setReportClass(NiReportClass reportClass) {
        this.reportClass = reportClass;
    }

	public Timestamp getcTime() {
		return cTime;
	}

	public void setcTime(Timestamp cTime) {
		this.cTime = cTime;
	}
    
}
