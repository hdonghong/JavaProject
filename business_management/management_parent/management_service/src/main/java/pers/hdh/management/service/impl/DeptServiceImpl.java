package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Dept;
import pers.hdh.management.service.DeptService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	DeptServiceImpl	
 * @Description	部门管理的业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class DeptServiceImpl implements DeptService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Dept get(Class<Dept> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
			entity.setState(1); // 1:启动；0:禁用；默认开启
		} /*else if (entity.getId().equals(entity.getParent().getId())) {
			// 不允许用户修改时选择自己作为自己的父部门
			entity.setParent(null);
		}*/
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Dept> entityClass, Serializable id) {
		// 先删除该部门下的子部门
		List<Dept> subDeptList = baseDao.find("from Dept where parent.id=?", entityClass, new Object[] {id});
		if (subDeptList != null && subDeptList.size() > 0) {
			for (Dept dept : subDeptList) {
				// 递归查询
				deleteById(entityClass, dept.getId());
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

}
