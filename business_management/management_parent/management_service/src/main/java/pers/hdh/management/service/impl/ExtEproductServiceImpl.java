package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.ExtEproduct;
import pers.hdh.management.service.ExtEproductService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ExtEproductServiceImpl	
 * @Description	业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ExtEproductServiceImpl implements ExtEproductService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExtEproduct> find(String hql, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExtEproduct get(Class<ExtEproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExtEproduct> findPage(String hql, Page<ExtEproduct> page, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ExtEproduct entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<ExtEproduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ExtEproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

}
