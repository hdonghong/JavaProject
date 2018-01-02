package team.webstore.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import team.webstore.dao.CategoryDao;
import team.webstore.domain.Category;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午2:38:50 
 */
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	@Override
	public List<Category> findAll() {
		// 开启query缓存
		this.getHibernateTemplate().setCacheQueries(true);
		return (List<Category>) this.getHibernateTemplate().find("from Category where state=1");
	}

	@Override
	public void add(Category category) {
		this.getHibernateTemplate().save(category);
	}

	@Override
	public Category getById(Long cid) {
		List<Category> list = (List<Category>) this.getHibernateTemplate().find("from Category where cid=? and state=1", cid);
		return (list != null && list.size() > 0 ) ?
				list.get(0) : null;
	}

	@Override
	public void update(Category category) {
		Category existedCategory = this.getHibernateTemplate().get(category.getClass(), category.getCid());
		if (existedCategory != null) {
			if (category.getCname() != null && category.getCname().trim().length() > 0)
				existedCategory.setCname(category.getCname());
			if (category.getState() != null && category.getState() >= 0 )
				existedCategory.setState(category.getState());
		}
	}

	
}
