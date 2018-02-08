package pers.hdh.management.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.domain.Contract;
import pers.hdh.management.utils.Page;

/**
 * @ClassName		ContractService	
 * @DescripDeption	
 * @auDepthor		hdonghong
 * @version 		v1.0 
 * @since			2018/01/30 10:18:50
 */
public interface ContractService {

	/**
	 * 查询所有，带条件查询
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params);
	
	/**
	 * 获取一条记录
	 * @param entityClass
	 * @param id
	 * @reDepturn
	 */
	public Contract get(Class<Contract> entityClass, Serializable id);
	
	/**
	 * 分页查询，将数据封装到一个page分页工具类对象
	 * @param hql
	 * @param page
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params);
	
	/**
	 * 新增和修改保存
	 * @param entity
	 */
	public void saveOrUpdate(Contract entity);
	
	/**
	 * 批量新增和修改保存
	 * @param entitys
	 */
	public void saveOrUpdateAll(Collection<Contract> entitys);
	
	/**
	 * 单条删除，按id
	 * @param entityClass
	 * @param id
	 */
	public void deleteById(Class<Contract> entityClass, Serializable id);
	
	/**
	 * 批量删除
	 * @param entityClass
	 * @param ids
	 */
	public void delete(Class<Contract> entityClass, Serializable[] ids);

	/**
	 * 改变购销合同的状态
	 * @param ids
	 * @param state
	 */
	public void changeState(String[] ids, int state);
}
