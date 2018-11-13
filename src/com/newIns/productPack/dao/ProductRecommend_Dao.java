package com.newIns.productPack.dao;

import java.util.List;
import java.util.Map;

import com.newIns.productPack.pojo.ProductRecommend;
import com.newIns.productPack.pojo.vo.ProductRecommend_Vo;

public interface ProductRecommend_Dao {

	List<ProductRecommend_Vo> list_ProductRecommend(Map<String, Object> dataMap);

	List<ProductRecommend_Vo> list_ProductRecommend_target(Map<String, Object> dataMap);

	List<ProductRecommend> list_ProductRecommend_model(ProductRecommend productRecommend);

	void insert_ProductRecommend(ProductRecommend productRecommend);

	void update_ShowOrder_Source(ProductRecommend productRecommend);

	void delete_productRecommend_Id_list(Map<String, Object> hashMap);

	void update_model(ProductRecommend productRecommend);
	
}
