package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.ExportProduct;
import pers.hdh.management.service.ExportProductService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ExportProductServiceImpl	
 * @Description	业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ExportProductServiceImpl implements ExportProductService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ExportProduct entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<ExportProduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

}
