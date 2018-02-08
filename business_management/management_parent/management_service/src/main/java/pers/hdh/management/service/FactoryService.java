package pers.hdh.management.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.domain.Factory;
import pers.hdh.management.utils.Page;

/**
 * @ClassName		FactoryService	
 * @DescripDeption	
 * @auDepthor		hdonghong
 * @version 		v1.0 
 * @since			2018/01/30 10:18:50
 */
public interface FactoryService {

	/**
	 * 查询所有，带条件查询
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public List<Factory> find(String hql, Class<Factory> entityClass, Object[] params);
	
	/**
	 * 获取一条记录
	 * @param entityClass
	 * @param id
	 * @reDepturn
	 */
	public Factory get(Class<Factory> entityClass, Serializable id);
	
	/**
	 * 分页查询，将数据封装到一个page分页工具类对象
	 * @param hql
	 * @param page
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params);
	
	/**
	 * 新增和修改保存
	 * @param entity
	 */
	public void saveOrUpdate(Factory entity);
	
	/**
	 * 批量新增和修改保存
	 * @param entitys
	 */
	public void saveOrUpdateAll(Collection<Factory> entitys);
	
	/**
	 * 单条删除，按id
	 * @param entityClass
	 * @param id
	 */
	public void deleteById(Class<Factory> entityClass, Serializable id);
	
	/**
	 * 批量删除
	 * @param entityClass
	 * @param ids
	 */
	public void delete(Class<Factory> entityClass, Serializable[] ids);
}
