package team.webstore.service;

import java.util.List;

import team.webstore.domain.Category;

/**
 * 分类模块的业务逻辑层
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午2:37:00 
 */
public interface CategoryService {

	/**
	 * 查询所有的商品分类
	 * @return 返回值为List类型的分类记录
	 */
	List<Category> findAll();

	/**
	 * 添加商品分类
	 * @param category
	 */
	void add(Category category);

	/**
	 * 通过分类编号获取一个分类
	 * @param cid
	 * @return 
	 */
	Category getById(Long cid);

	/**
	 * 更新分类的名称
	 * @param category
	 */
	void update(Category category);

	/**
	 * 删除此项分类
	 * 采用“假删除”，将分类的state属性设置为0后更新数据
	 * @param category
	 */
	void delete(Category category);

	
}
