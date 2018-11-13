package com.newIns.model.tweet;

import java.util.Date;

/**
 * 推文
 * @author 11409
 *
 */
public class NiTweet {
	private Integer tweetId,tweetStatus,wanxViewNum,weixViewNum,appViewNum;
	private String tweetTitle,tweetUrl,comment,author,tag,picUrl;
	private Date pTime; //文章发布时间
	
	
	public Integer getTweetStatus() {
		return tweetStatus;
	}
	public void setTweetStatus(Integer tweetStatus) {
		this.tweetStatus = tweetStatus;
	}
	public Integer getWanxViewNum() {
		return wanxViewNum;
	}
	public void setWanxViewNum(Integer wanxViewNum) {
		this.wanxViewNum = wanxViewNum;
	}
	public Integer getWeixViewNum() {
		return weixViewNum;
	}
	public void setWeixViewNum(Integer weixViewNum) {
		this.weixViewNum = weixViewNum;
	}
	public Integer getAppViewNum() {
		return appViewNum;
	}
	public void setAppViewNum(Integer appViewNum) {
		this.appViewNum = appViewNum;
	}
	public Integer getTweetId() {
		return tweetId;
	}
	public void setTweetId(Integer tweetId) {
		this.tweetId = tweetId;
	}
	public String getTweetTitle() {
		return tweetTitle;
	}
	public void setTweetTitle(String tweetTitle) {
		this.tweetTitle = tweetTitle;
	}
	public String getTweetUrl() {
		return tweetUrl;
	}
	public void setTweetUrl(String tweetUrl) {
		this.tweetUrl = tweetUrl;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getpTime() {
		return pTime;
	}
	public void setpTime(Date pTime) {
		this.pTime = pTime;
	}
	
	
	
}
