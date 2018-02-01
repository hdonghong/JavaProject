package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Role;
import pers.hdh.management.service.RoleService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	RoleServiceImpl	
 * @Description	角色管理的业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class RoleServiceImpl implements RoleService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Role get(Class<Role> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Role> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Role> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Role> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

}
