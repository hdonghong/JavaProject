package team.webstore.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import team.webstore.dao.OrderDao;
import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月27日 下午8:03:21 
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Override
	public void add(Orders order) {
		this.getHibernateTemplate().save(order);
	}

	@Override
	public int getTotalCount(DetachedCriteria criteria) {
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		return list != null && list.size() > 0 ?
				list.get(0).intValue() : 0;
	}

	@Override
	public List<Orders> getBeanListByPage(DetachedCriteria criteria, Integer currPage, int pageSize) {
		return (List<Orders>) this.getHibernateTemplate().findByCriteria(criteria, (currPage-1)*pageSize, pageSize);
	}

	@Override
	public void delete(Orders order) {
		HibernateTemplate template = this.getHibernateTemplate();
		order = template.get(Orders.class, order.getOid());
		if (order != null) {
			template.delete(order);
		}
	}

	@Override
	public Orders getById(Orders order) {
		return this.getHibernateTemplate().get(Orders.class, order.getOid());
	}

	@Override
	public void update(Orders order) {
		this.getHibernateTemplate().update(order);
	}

	@Override
	public OrderItem getItemById(OrderItem item) {
		return this.getHibernateTemplate().get(item.getClass(), item.getItemid());
	}

	@Override
	public void updateOrderItem(OrderItem existedItem) {
		this.getHibernateTemplate().update(existedItem);
	}

	
}
