package pers.hdh.management.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.domain.PackingList;
import pers.hdh.management.utils.Page;

 /**
 * @ClassName	PackingListService
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018-2-19 7:27:23
 */

public interface PackingListService {

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params);
	public PackingList get(Class<PackingList> entityClass, Serializable id);
	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params);
	
	public void saveOrUpdate(PackingList entity);
	public void saveOrUpdateAll(Collection<PackingList> entitys);
	
	public void deleteById(Class<PackingList> entityClass, Serializable id);
	public void delete(Class<PackingList> entityClass, Serializable[] ids);
}
