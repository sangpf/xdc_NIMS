package com.newIns.dao.page;

import java.util.List;

import com.newIns.model.page.Column;

public interface ColumnDao {
	
	List<Column> listColumn();

	// 根据主键查询
	Column findById(Integer columnId);
	
	
}
