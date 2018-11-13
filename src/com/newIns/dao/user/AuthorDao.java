package com.newIns.dao.user;

import java.util.List;
import java.util.Map;

import com.newIns.model.user.Author;

public interface AuthorDao {
	
	List<Author> author_List(Map<String,Object> dataMap);

	Author selectOneById(Integer authorId);

	void updateById(Author author);

	void insert(Author author);
	
	
	
}
