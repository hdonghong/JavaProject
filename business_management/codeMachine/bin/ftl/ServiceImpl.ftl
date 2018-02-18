package ${ package }.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import ${ package }.dao.BaseDao;
import ${ package }.domain.${ className };
import ${ package }.pagination.Page;
import ${ package }.service.${ className }Service;
import cn.itcast.util.UtilFuns;

<#import "CopyRight.ftl" as my>
<@my.CopyRight/>

public class ${ className }ServiceImpl implements ${ className }Service {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<${ className }> find(String hql, Class<${ className }> entityClass, Object[] params) {
		return baseDao.find(hql, ${ className }.class, params);
	}

	public ${ className } get(Class<${ className }> entityClass, Serializable id) {
		return baseDao.get(${ className }.class, id);
	}

	public Page<${ className }> findPage(String hql, Page<${ className }> page, Class<${ className }> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, ${ className }.class, params);
	}

	public void saveOrUpdate(${ className } entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<${ className }> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<${ className }> entityClass, Serializable id) {
		baseDao.deleteById(${ className }.class, id);
	}

	public void delete(Class<${ className }> entityClass, Serializable[] ids) {
		baseDao.delete(${ className }.class, ids);
	}

}
xxxx yyyyyy,zzzz xxxx
