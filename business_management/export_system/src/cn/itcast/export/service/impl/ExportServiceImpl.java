package cn.itcast.export.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import cn.itcast.export.dao.BaseDao;
import cn.itcast.export.domain.Export;
import cn.itcast.export.pagination.Page;
import cn.itcast.export.service.ExportService;

/**
 * @Description:	ExportService接口
 * @Author:			传智播客 java学院	传智宋江
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-7-6 19:46:34
 */

public class ExportServiceImpl implements ExportService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, Export.class, params);
	}

	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(Export.class, id);
	}

	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Export.class, params);
	}

	public void saveOrUpdate(Export entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Export> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(Export.class, id);
	}

	public void delete(Class<Export> entityClass, Serializable[] ids) {
		baseDao.delete(Export.class, ids);
	}

}

