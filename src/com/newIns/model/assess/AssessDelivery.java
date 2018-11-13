package com.newIns.model.assess;

import java.util.Date;

public class AssessDelivery {
	
	private Integer deliveryId;  // 主键
	
	private String titleTag; //标题标签
	
    private Integer aqnId,collectNum;

    private Date bTime,cTime,eTime;
    
    private Integer lTime;

    private String reason;

    private String showTitle,showDes,img;

    private Integer collectedNum;

    private String tag1Str,tag2Str,tag3Str,tag4Str,tag5Str;
    
    private String relatedStr1,relatedStr2,relatedStr3,relatedUrl1,relatedUrl2,relatedUrl3; //相关推荐

    private Integer award1Id,award2Id,award3Id,awaed4Id;

    private Integer lotteryReady,lotteryId,adId,status,channelId;

    private String comment;
    
    private String birthday,resultMsg;   //答题后结果提示语

    private String payTag1,payTag2,payTag3,discountInfo;
    
    private Double price;  //问卷价格
    
    private String headResultDetail,tailResultDetail; // 测评结果 头尾编辑
    
    private Integer cornerFlag;  // 角棋类型:0.没有角棋，1.PRO专业版，2.HOT，3.NEW
    
	public Integer getCornerFlag() {
		return cornerFlag;
	}

	public void setCornerFlag(Integer cornerFlag) {
		this.cornerFlag = cornerFlag;
	}

	public String getHeadResultDetail() {
		return headResultDetail;
	}

	public void setHeadResultDetail(String headResultDetail) {
		this.headResultDetail = headResultDetail;
	}

	public String getTailResultDetail() {
		return tailResultDetail;
	}

	public void setTailResultDetail(String tailResultDetail) {
		this.tailResultDetail = tailResultDetail;
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getTitleTag() {
		return titleTag;
	}

	public void setTitleTag(String titleTag) {
		this.titleTag = titleTag;
	}

	public Integer getAqnId() {
		return aqnId;
	}

	public void setAqnId(Integer aqnId) {
		this.aqnId = aqnId;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Integer getlTime() {
		return lTime;
	}

	public void setlTime(Integer lTime) {
		this.lTime = lTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getShowDes() {
		return showDes;
	}

	public void setShowDes(String showDes) {
		this.showDes = showDes;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCollectedNum() {
		return collectedNum;
	}

	public void setCollectedNum(Integer collectedNum) {
		this.collectedNum = collectedNum;
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

	public String getTag5Str() {
		return tag5Str;
	}

	public void setTag5Str(String tag5Str) {
		this.tag5Str = tag5Str;
	}

	public String getRelatedStr1() {
		return relatedStr1;
	}

	public void setRelatedStr1(String relatedStr1) {
		this.relatedStr1 = relatedStr1;
	}

	public String getRelatedStr2() {
		return relatedStr2;
	}

	public void setRelatedStr2(String relatedStr2) {
		this.relatedStr2 = relatedStr2;
	}

	public String getRelatedStr3() {
		return relatedStr3;
	}

	public void setRelatedStr3(String relatedStr3) {
		this.relatedStr3 = relatedStr3;
	}

	public String getRelatedUrl1() {
		return relatedUrl1;
	}

	public void setRelatedUrl1(String relatedUrl1) {
		this.relatedUrl1 = relatedUrl1;
	}

	public String getRelatedUrl2() {
		return relatedUrl2;
	}

	public void setRelatedUrl2(String relatedUrl2) {
		this.relatedUrl2 = relatedUrl2;
	}

	public String getRelatedUrl3() {
		return relatedUrl3;
	}

	public void setRelatedUrl3(String relatedUrl3) {
		this.relatedUrl3 = relatedUrl3;
	}

	public Integer getAward1Id() {
		return award1Id;
	}

	public void setAward1Id(Integer award1Id) {
		this.award1Id = award1Id;
	}

	public Integer getAward2Id() {
		return award2Id;
	}

	public void setAward2Id(Integer award2Id) {
		this.award2Id = award2Id;
	}

	public Integer getAward3Id() {
		return award3Id;
	}

	public void setAward3Id(Integer award3Id) {
		this.award3Id = award3Id;
	}

	public Integer getAwaed4Id() {
		return awaed4Id;
	}

	public void setAwaed4Id(Integer awaed4Id) {
		this.awaed4Id = awaed4Id;
	}

	public Integer getLotteryReady() {
		return lotteryReady;
	}

	public void setLotteryReady(Integer lotteryReady) {
		this.lotteryReady = lotteryReady;
	}

	public Integer getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getPayTag1() {
		return payTag1;
	}

	public void setPayTag1(String payTag1) {
		this.payTag1 = payTag1;
	}

	public String getPayTag2() {
		return payTag2;
	}

	public void setPayTag2(String payTag2) {
		this.payTag2 = payTag2;
	}

	public String getPayTag3() {
		return payTag3;
	}

	public void setPayTag3(String payTag3) {
		this.payTag3 = payTag3;
	}

	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
  
}