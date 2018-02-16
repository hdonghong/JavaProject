package cn.itcast.export.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.itcast.export.dao.BaseDao;
import cn.itcast.export.pagination.Page;

/**
 * @Description:
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 */
public class BaseDaoImpl implements BaseDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	//带条件查询
	public <T> List<T> find(String hql, Class<T> entityClass, Object[] params) {
		Query query = this.getSession().createQuery(hql);
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (List<T>) query.list();
	}
	
	//获取一条，根据主键id
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) this.getSession().get(entityClass, id);
	}

	//分页查询，查询两次，一次查询总数，一次查询分页记录
	public <T> Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params){
		
		Query query = this.getSession().createQuery(hql);
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		
		//查询一次，获取记录总数   可以优化 "select count(*) from "+ entityClass.getName()
		int count = query.list().size();
		page.setTotalRecord(count);
		
		//设置分页
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());	//设置开始位置
		query.setMaxResults(page.getPageSize());				//设置获取几条
		page.setResults((List<T>)query.list());
		
		return page;
	}
	
	//新增和修改，hibernate根据id是否为null自动判断
	public <T> void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}
	
	//集合保存，这时新增还是修改，就自动判断，调用时是否简洁。适合批量新增和修改时。（Mrecord控件）
	public <T> void saveOrUpdateAll(Collection<T> entitys){
		for(T entity : entitys){
			this.saveOrUpdate(entity);//为什么hibernate批量操作时，要用循环一条一条记录去更新？原因：一级缓存要同步更新
		}
	}

	//按主键id删除
	public <T> void deleteById(Class<T> entityClass, Serializable id) {
		this.getSession().delete(get(entityClass, id));
	}

	//批量删除
	public <T> void delete(Class<T> entityClass, Serializable[] ids) {
		for(Serializable s : ids){
			deleteById(entityClass, s);
		}
	}


}

