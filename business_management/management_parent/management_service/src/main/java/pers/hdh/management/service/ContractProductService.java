package pers.hdh.management.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.domain.ContractProduct;
import pers.hdh.management.utils.Page;

/**
 * @ClassName		ContractProductService	
 * @DescripDeption	
 * @auDepthor		hdonghong
 * @version 		v1.0 
 * @since			2018/01/30 10:18:50
 */
public interface ContractProductService {

	/**
	 * 查询所有，带条件查询
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params);
	
	/**
	 * 获取一条记录
	 * @param entityClass
	 * @param id
	 * @reDepturn
	 */
	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id);
	
	/**
	 * 分页查询，将数据封装到一个page分页工具类对象
	 * @param hql
	 * @param page
	 * @param entityClass
	 * @param params
	 * @reDepturn
	 */
	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params);
	
	/**
	 * 新增和修改保存
	 * @param entity
	 */
	public void saveOrUpdate(ContractProduct entity);
	
	/**
	 * 批量新增和修改保存
	 * @param entitys
	 */
	public void saveOrUpdateAll(Collection<ContractProduct> entitys);
	
	/**
	 * 单条删除，按id
	 * @param entityClass
	 * @param id
	 */
	public void deleteById(Class<ContractProduct> entityClass, Serializable id);
	
	/**
	 * 批量删除
	 * @param entityClass
	 * @param ids
	 */
	public void delete(Class<ContractProduct> entityClass, Serializable[] ids);

	/**
	 * 删除货物对象，级联删除它的附件，减少合同总金额
	 * @param entityClass
	 * @param model
	 */
	public void delete(Class<ContractProduct> entityClass, ContractProduct model);
}
