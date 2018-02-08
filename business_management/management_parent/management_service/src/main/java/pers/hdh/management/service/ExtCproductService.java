package pers.hdh.management.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.domain.ExtCproduct;
import pers.hdh.management.utils.Page;

/**
 * @ClassName		ExtCproductService	
 * @DescripDeption	
 * @auDepthor		hdonghong
 * @version 		v1.0 
 * @since			2018/01/30 10:18:50
 */
public interface ExtCproductService {

	/**
	 * 查询所有，带条件查询
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params);
	
	/**
	 * 获取一条记录
	 * @param entityClass
	 * @param id
	 * @reDepturn
	 */
	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id);
	
	/**
	 * 分页查询，将数据封装到一个page分页工具类对象
	 * @param hql
	 * @param page
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params);
	
	/**
	 * 新增和修改保存
	 * @param entity
	 */
	public void saveOrUpdate(ExtCproduct entity);
	
	/**
	 * 批量新增和修改保存
	 * @param entitys
	 */
	public void saveOrUpdateAll(Collection<ExtCproduct> entitys);
	
	/**
	 * 单条删除，按id
	 * @param entityClass
	 * @param id
	 */
	public void deleteById(Class<ExtCproduct> entityClass, Serializable id);
	
	/**
	 * 批量删除
	 * @param entityClass
	 * @param ids
	 */
	public void delete(Class<ExtCproduct> entityClass, Serializable[] ids);

	/**
	 * 删除附件
	 * @param entityClass
	 * @param model
	 */
	public void delete(Class<ExtCproduct> entityClass, ExtCproduct model);
}
