package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		return dao.findNew();
	}

	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		return dao.findHot();
	}

	/**
	 * 通过商品id查询商品
	 */
	@Override
	public Product getById(String pid) throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		return dao.getById(pid);
	}

	/**
	 * 按类别分页查询商品
	 */
	@Override
	public PageBean<Product> findByPage(String cid, int currPage, int pageSize) throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		
		//当前页数据
		List<Product> list = dao.findByPage(cid, currPage, pageSize);
		//数据总条数
		int totalCount = dao.getTotalCount(cid);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 查询所有商品
	 */
	@Override
	public List<Product> findAll() throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		return dao.findAll();
	}

	/**
	 * 添加商品
	 */
	@Override
	public void add(Product product) throws Exception {
		ProductDao dao = (ProductDao) BeanFactory.getBean("ProductDao");
		dao.add(product);
	}

}
