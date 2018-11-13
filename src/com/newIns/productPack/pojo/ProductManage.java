package com.newIns.productPack.pojo;
/**
 * （产品包内容表）-- 存储产品包内容问卷推文等
 * @author sang
 *
 */
public class ProductManage {
	
	private Integer id; // key
	
	private Integer productId,itemId,itemType,showOrder,state,columId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getColumId() {
		return columId;
	}

	public void setColumId(Integer columId) {
		this.columId = columId;
	}

}
