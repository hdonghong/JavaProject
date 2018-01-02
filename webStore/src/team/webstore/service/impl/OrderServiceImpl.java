package team.webstore.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import team.webstore.dao.OrderDao;
import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;
import team.webstore.domain.PageBean;
import team.webstore.service.OrderService;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月27日 下午8:01:52 
 */
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Override
	public void add(Orders order) {
		orderDao.add(order);
	}

	@Override
	public PageBean<Orders> findByPage(DetachedCriteria criteria, Integer currPage, int pageSize) {
		
		// 先查询该用户订单总数量
		criteria.setProjection(Projections.rowCount());
		int totalCount = orderDao.getTotalCount(criteria);
		if (!(totalCount > 0)) {
			return null;
		}
		
		// 清空COUNT(*)条件
		criteria.setProjection(null);
		// 获取分页的订单列表
		List<Orders> beanList = orderDao.getBeanListByPage(criteria, currPage, pageSize);
		
		return new PageBean<>(currPage, totalCount, pageSize, beanList);
	}

	@Override
	public void delete(Orders order) {
		orderDao.delete(order);
	}

	@Override
	public Orders getById(Orders order) {
		return orderDao.getById(order);
	}

	@Override
	public void pay(Orders order) {
		Orders existedOrder = orderDao.getById(order);
		if (existedOrder != null) {
			if (order.getName()!=null) existedOrder.setName(order.getName());
			if (order.getAddress()!=null) existedOrder.setAddress(order.getAddress());
			if (order.getTelephone()!=null) existedOrder.setTelephone(order.getTelephone());
			if (order.getState()!=null) existedOrder.setState(order.getState());
			for (OrderItem item : existedOrder.getOrderItems()) {
				item.getProduct().setPcount(
						item.getProduct().getPcount() + item.getCount());
			}
			// 调用dao层保存
			orderDao.update(existedOrder);
		}
	}

	@Override
	public void updateState(Orders order) {
		Orders existedOrder = orderDao.getById(order);
		if (existedOrder != null) {
			existedOrder.setState(order.getState());
			//调用dao层更新
			orderDao.update(existedOrder);
		}
	}

	@Override
	public void addComment(List<OrderItem> items) {
		// 先获取订单项所属订单,订单状态更新为已完成
		Orders existedOrder = getById(items.get(0).getOrder());
		existedOrder.setState(4);
		orderDao.update(existedOrder);
		
		for (OrderItem item : items) {
			OrderItem existedItem = orderDao.getItemById(item);
			if (existedItem != null) {
				existedItem.setComment(item.getComment());
				orderDao.updateOrderItem(existedItem);
			}
		}
	}
	
}
