package com.itheima.service.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.CategoryDao;
import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.DataSourceUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CategoryServiceImpl implements CategoryService {

	/**
	 * 查询所有的分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		//1.在classes目录下获取流
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ehcache.xml");
		
		//2.获取缓存管理器
		CacheManager cm = CacheManager.create(is);
		
		//3.获取指定的缓存对象
		Cache cache = cm.getCache("categoryCache");
		
		//4.将cache看作map，通过缓存获取数据
		Element element = cache.get("clist");
		List<Category> clist = null;
		
		//5.判断数据
		if (element == null) {
			//缓存中没有该数据时需要查询数据库，并将数据放入缓存中
			CategoryDao dao = (CategoryDao) BeanFactory.getBean("CategoryDao");
			clist = dao.findAll();
			cache.put(new Element("clist", clist));
			//System.out.println("缓存中没有数据，查询数据库");
		} else {
			clist = (List<Category>) element.getObjectValue();
			//System.out.println("缓存中存在数据");
		}
		
		return clist;
	}

	/**
	 * 添加一个分类
	 */
	@Override
	public void add(Category category) throws Exception {
		CategoryDao dao = (CategoryDao) BeanFactory.getBean("CategoryDao");
		dao.add(category);
		
		//更新缓存
		CacheManager manager = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = manager.getCache("categoryCache");
		cache.remove("clist");
	}

	/**
	 * 通过分类id获取一个分类
	 */
	@Override
	public Category getById(String cid) throws Exception {
		CategoryDao dao = (CategoryDao) BeanFactory.getBean("CategoryDao");
		return dao.getById(cid);
	}

	/**
	 * 编辑分类
	 */
	@Override
	public void update(Category category) throws Exception {
		CategoryDao dao = (CategoryDao) BeanFactory.getBean("CategoryDao");
		dao.update(category);
		
		//更新缓存
		CacheManager manager = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = manager.getCache("categoryCache");
		cache.remove("clist");
	}

	/**
	 * 删除分类
	 * @throws Exception 
	 */
	@Override
	public void delete(String cid) throws Exception {
		//需要先处理分类下的商品
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			ProductDao pdao = (ProductDao) BeanFactory.getBean("ProductDao");
			pdao.updateCid(cid);
			CategoryDao cdao = (CategoryDao) BeanFactory.getBean("CategoryDao");
			cdao.update(cid);
			//提交事务
			DataSourceUtils.commitAndClose();
		} catch (SQLException e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose(); //事务回滚
			throw e;
		}
		
		//更新缓存
		CacheManager manager = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = manager.getCache("categoryCache");
		cache.remove("clist");
	}

}
