package com.itheima.service;

import java.util.List;

import com.itheima.domain.Category;

public interface CategoryService {

	List<Category> findAll() throws Exception;

	void add(Category category) throws Exception;

	Category getById(String cid) throws Exception;

	void update(Category category) throws Exception;

	void delete(String cid) throws Exception;

}
