package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.PackingList;
import pers.hdh.management.service.PackingListService;
import pers.hdh.management.utils.Page;

 /**
 * @ClassName	PackingList	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018-2-19 7:27:23
 */

public class PackingListServiceImpl implements PackingListService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params) {
		return baseDao.find(hql, PackingList.class, params);
	}

	public PackingList get(Class<PackingList> entityClass, Serializable id) {
		return baseDao.get(PackingList.class, id);
	}

	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, PackingList.class, params);
	}

	public void saveOrUpdate(PackingList entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(0d);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<PackingList> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<PackingList> entityClass, Serializable id) {
		baseDao.deleteById(PackingList.class, id);
	}

	public void delete(Class<PackingList> entityClass, Serializable[] ids) {
		baseDao.delete(PackingList.class, ids);
	}

}
