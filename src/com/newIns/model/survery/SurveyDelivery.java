package com.newIns.model.survery;

import java.util.Date;

public class SurveyDelivery {
	
	private String resultMsg;//答题后显示结语
	
	private String titleTag; //标题标签
	
	private String publisherName;
	
	private String sqnName;
	
    private Integer deliveryid;

    private Integer sqnid;

    private Integer collectnum;
    
    private Date birthday;

    private Date btime;

    private Date etime;
    
    private Date uTime;
    
    private Integer lTime;

    private String reason;

    private String showtitle;

    private String showdes;

    private String img;

    private Integer collectednum;

    private String tag1str;

    private String tag2str;

    private String tag3str;

    private String tag4str;

    private String tag5str;
    
    private String relatedStr1,relatedStr2,relatedStr3,relatedUrl1,relatedUrl2,relatedUrl3; //相关推荐

    private int award1id;

    private Integer award2id;

    private Integer award3id;

    private Integer awaed4id;

    private Integer lotteryready;

    private Integer lotteryid;
    
    private Integer evaluateId;  //关联倾向抽奖id

    private Integer adid;

    private Integer status;

    private Integer channelId;   // 渠道id 
    
    private String comment;
    
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Integer getlTime() {
		return lTime;
	}

	public void setlTime(Integer lTime) {
		this.lTime = lTime;
	}

	public Integer getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Integer deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Integer getSqnid() {
        return sqnid;
    }

    public void setSqnid(Integer sqnid) {
        this.sqnid = sqnid;
    }

    public Integer getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(Integer collectnum) {
        this.collectnum = collectnum;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getEtime() {
        return etime;
    }

    public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getShowtitle() {
        return showtitle;
    }

    public void setShowtitle(String showtitle) {
        this.showtitle = showtitle == null ? null : showtitle.trim();
    }

    public String getShowdes() {
        return showdes;
    }

    public void setShowdes(String showdes) {
        this.showdes = showdes == null ? null : showdes.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getCollectednum() {
        return collectednum;
    }

    public void setCollectednum(Integer collectednum) {
        this.collectednum = collectednum;
    }

    public String getTag1str() {
        return tag1str;
    }

    public void setTag1str(String tag1str) {
        this.tag1str = tag1str == null ? null : tag1str.trim();
    }

    public String getTag2str() {
        return tag2str;
    }

    public void setTag2str(String tag2str) {
        this.tag2str = tag2str == null ? null : tag2str.trim();
    }

    public String getTag3str() {
        return tag3str;
    }

    public void setTag3str(String tag3str) {
        this.tag3str = tag3str == null ? null : tag3str.trim();
    }

    public String getTag4str() {
        return tag4str;
    }

    public void setTag4str(String tag4str) {
        this.tag4str = tag4str == null ? null : tag4str.trim();
    }

    public String getTag5str() {
        return tag5str;
    }

    public void setTag5str(String tag5str) {
        this.tag5str = tag5str == null ? null : tag5str.trim();
    }

    public int getAward1id() {
		return award1id;
	}

	public void setAward1id(int award1id) {
		this.award1id = award1id;
	}

	public Integer getAward2id() {
        return award2id;
    }

    public void setAward2id(Integer award2id) {
        this.award2id = award2id;
    }

    public Integer getAward3id() {
        return award3id;
    }

    public void setAward3id(Integer award3id) {
        this.award3id = award3id;
    }

    public Integer getAwaed4id() {
        return awaed4id;
    }

    public void setAwaed4id(Integer awaed4id) {
        this.awaed4id = awaed4id;
    }

    public Integer getLotteryready() {
        return lotteryready;
    }

    public void setLotteryready(Integer lotteryready) {
        this.lotteryready = lotteryready;
    }

    public Integer getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(Integer lotteryid) {
        this.lotteryid = lotteryid;
    }

	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getSqnName() {
		return sqnName;
	}

	public void setSqnName(String sqnName) {
		this.sqnName = sqnName;
	}

	public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getTitleTag() {
		return titleTag;
	}

	public void setTitleTag(String titleTag) {
		this.titleTag = titleTag;
	}
	
}