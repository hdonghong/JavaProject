package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Export;
import pers.hdh.management.service.ExportService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ExportServiceImpl	
 * @Description	业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ExportServiceImpl implements ExportService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Export entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Export> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Export> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

	@Override
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Export entity = baseDao.get(Export.class, id);
			entity.setState(state);
			baseDao.saveOrUpdate(entity);
		}
	}

}
