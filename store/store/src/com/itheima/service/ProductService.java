package com.itheima.service;

import java.util.List;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

public interface ProductService {

	List<Product> findNew() throws Exception;

	List<Product> findHot() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(String cid, int currPage, int pageSize) throws Exception;

	List<Product> findAll() throws Exception;

	void add(Product product) throws Exception;

}
