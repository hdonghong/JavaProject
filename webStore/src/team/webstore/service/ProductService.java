package team.webstore.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.PageBean;
import team.webstore.domain.Product;

/**
 * 商品模块的业务层
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午7:03:36 
 */
public interface ProductService {

	/**
	 * 查询热门商品
	 * @param listSize 限定的商品数量
	 * @return
	 */
	List<Product> findHotProducts(int listSize);

	/**
	 * 查询最新商品
	 * @param listSize 限定的商品数量
	 * @return
	 */
	List<Product> findNewProducts(int listSize);

	
	/**
	 * 通过商品的Pid查询该商品
	 * @param pid
	 * @return
	 */
	Product getById(Long pid);

	/**
	 * 分页条件查询
	 * @param currPage 当前页
	 * @param pageSize 页面数据量
	 * @param criteria 条件
	 * @return
	 */
	PageBean<Product> findByPage(Integer currPage, int pageSize, DetachedCriteria criteria);
	
	/**
	 * 重载分页条件查询
	 * @param currPage 当前页
	 * @param criteria 条件
	 * @return
	 */
	PageBean<Product> findByPage(Integer currPage, DetachedCriteria criteria);

	/**
	 * 保存新商品
	 * @param product
	 */
	void save(Product product);

	/**
	 * 更新商品信息
	 * @param product
	 */
	void update(Product product);

	/**
	 * 下架指定商品，将state设为0
	 * @param product
	 */
	void delete(Product product);

}
