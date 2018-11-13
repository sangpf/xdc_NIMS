package com.newIns.productPack.dao;

import java.util.List;
import java.util.Map;

import com.newIns.productPack.pojo.ProductManage;
import com.newIns.productPack.pojo.vo.ProductManage_Vo;

public interface ProductManage_Dao {

	List<ProductManage_Vo> select_product_manageList_map(Map<String, Object> dataMap);

	ProductManage select_productManage_model(ProductManage productManage);

	void insert(ProductManage productManage);

	void increasing_showOrder_map(Map<String, Object> dataMap);

	void update_model(ProductManage productManage);
	
	
	
}
