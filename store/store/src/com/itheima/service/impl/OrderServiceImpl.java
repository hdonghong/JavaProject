package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	/**
	 * 生成订单
	 */
	@Override
	public void add(Order order) throws Exception {
		try {
		//1.开启事务
			DataSourceUtils.startTransaction();
			OrderDao dao = (OrderDao) BeanFactory.getBean("OrderDao");
		//2.往订单表中插入一条数据
			dao.add2Orders(order);
		//3.往订单项表中插入n条数据
			for (OrderItem orderItem : order.getOrderItems()) {
				dao.add2Orderitem(orderItem);
			}
		//4.提交事务/事务回滚
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	/**
	 * 分页查询用户所有订单
	 */
	@Override
	public PageBean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception {
		OrderDao dao = (OrderDao) BeanFactory.getBean("OrderDao");
		//1.调用dao查询订单
		List<Order> list = dao.findAllByPage(currPage, pageSize, user.getUid());
		
		//2.查询订单总条数
		int totalCount = dao.getTotalCount(user.getUid());
		
		
		return new PageBean<Order>(list,currPage, pageSize, totalCount);
	}

	/**
	 * 通过id查看订单详情
	 */
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao dao = (OrderDao) BeanFactory.getBean("OrderDao");
		return dao.getById(oid);
	}

	/**
	 * 更新orders表
	 */
	@Override
	public void updateOrder(Order order) throws Exception {
		OrderDao dao = (OrderDao) BeanFactory.getBean("OrderDao");
		dao.update(order);
	}

	/**
	 * 根据状态查询订单
	 */
	@Override
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao dao = (OrderDao) BeanFactory.getBean("OrderDao");
		return dao.findAllByState(state);
	}

}
