package com.newIns.productPack.pojo.vo;

import com.newIns.productPack.pojo.ProductRecommend;

public class ProductRecommend_Vo extends ProductRecommend{
	
	private String sourceTitle,targetTitle;

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getTargetTitle() {
		return targetTitle;
	}

	public void setTargetTitle(String targetTitle) {
		this.targetTitle = targetTitle;
	}
	
}
