package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void add(Category category) throws Exception;

	Category getById(String cid) throws Exception;

	void update(Category category) throws Exception;

	void update(String cid) throws Exception;

}
