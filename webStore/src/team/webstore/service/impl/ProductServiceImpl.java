package team.webstore.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import team.webstore.dao.ProductDao;
import team.webstore.domain.PageBean;
import team.webstore.domain.Product;
import team.webstore.service.ProductService;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午7:04:22 
 */
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public List<Product> findHotProducts(int listSize) {
		return productDao.findHotProducts(listSize);
	}
	@Override
	public List<Product> findNewProducts(int listSize) {
		return productDao.findNewProducts(listSize);
	}

	@Override
	public Product getById(Long pid) {
		return productDao.getById(pid);
	}

	@Override
	public PageBean<Product> findByPage(Integer currPage, int pageSize, DetachedCriteria criteria) {
		// 先查询数据量
		int totalCount = productDao.getTotalCountByCategory(criteria);
		if (totalCount == 0) {
			return null;
		}
		// 注意：把count(*)先清空，变成默认的select *
		criteria.setProjection(null);
		// 再查询具体数据
		List<Product> beanList = productDao.getBeanListByCategory(currPage, pageSize, criteria);
		
		return new PageBean<>(currPage, totalCount, pageSize, beanList);
	}
	
	@Override
	public PageBean<Product> findByPage(Integer currPage, DetachedCriteria criteria) {
		// 先查询数据量
		int totalCount = productDao.getTotalCountByCategory(criteria);
		if (totalCount == 0) {
			return null;
		}
		// 注意：把count(*)先清空，变成默认的select *
		criteria.setProjection(null);
		// 再查询具体数据
		List<Product> beanList = productDao.getBeanListByCategory(currPage, totalCount, criteria);
		
		return new PageBean<>(currPage, totalCount, 0, beanList);
	}

	@Override
	public void save(Product product) {
		// 设为可用状态
		product.setState(1);
		// 设为销量0
		product.setPcount(0);
		// 保存到数据库中
		productDao.save(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public void delete(Product product) {
		// 将state设为0达到“假删除”效果
		product.setState(0);
		productDao.update(product);
	}
}
