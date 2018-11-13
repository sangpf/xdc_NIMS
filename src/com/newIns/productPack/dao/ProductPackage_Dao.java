package com.newIns.productPack.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newIns.productPack.pojo.ProductPackage;

public interface ProductPackage_Dao {
	
	List<ProductPackage> list_ProductPackage(Map<String,Object> dataMap);

	void insert_ProductPackage(ProductPackage productPackage);

	ProductPackage select_key(Integer productPackage_Id);

	void update_key(ProductPackage productPackage);

	void update_up_down(ArrayList<Integer> arrayList);
	
	
}
