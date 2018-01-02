package team.webstore.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.Product;

/**
 * 商品模块的持久层
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午7:05:29 
 */
public interface ProductDao {

	/**
	 * 查询product表中count字段值最大的前listsize条记录
	 * @param listSize 限定查询的记录数
	 * @return
	 */
	List<Product> findHotProducts(int listSize);

	/**
	 * 查询product表中update_at字段值最大的前listsize条记录
	 * @param listSize 限定查询的记录数
	 * @return
	 */
	List<Product> findNewProducts(int listSize);

	/**
	 * 通过商品的主键查询product表中的商品
	 * @param pid
	 * @return
	 */
	Product getById(Long pid);

	/**
	 * 分类获取某分类下上架商品数量
	 * @param criteria
	 * @return
	 */
	int getTotalCountByCategory(DetachedCriteria criteria);

	/**
	 * 分类分页查询某分类下上架商品记录
	 * @param currPage
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	List<Product> getBeanListByCategory(Integer currPage, int pageSize, DetachedCriteria criteria);

	/**
	 * 将一条记录保存到product表中
	 * @param product
	 */
	void save(Product product);

	/**
	 * 查询product表中一条记录后更新此项记录信息
	 * @param product
	 */
	void update(Product product);

}
