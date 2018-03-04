package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Product;

public interface ProductDao {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(String cid, int currPage, int pageSize) throws Exception;

	int getTotalCount(String cid) throws Exception;

	void updateCid(String cid) throws Exception;

	List<Product> findAll() throws Exception;

	void add(Product product) throws Exception;

}
