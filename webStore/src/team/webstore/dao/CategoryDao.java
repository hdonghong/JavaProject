package team.webstore.dao;

import java.util.List;

import team.webstore.domain.Category;

/**
 * 分类模块的持久层
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午2:38:18 
 */
public interface CategoryDao {

	/**
	 * 查询category表所有记录
	 * @return 返回所有记录的List
	 */
	List<Category> findAll();

	/**
	 * 添加分类
	 * @param category
	 */
	void add(Category category);

	/**
	 * 通过分类主键cid获取一条category记录
	 * 要求该记录的state为1
	 * @param cid
	 * @return 
	 */
	Category getById(Long cid);

	/**
	 * 通过分类主键cid获取到一条category记录后更新这条记录的数据
	 * @param category
	 */
	void update(Category category);

}
