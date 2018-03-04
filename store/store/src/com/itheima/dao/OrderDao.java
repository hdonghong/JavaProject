package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;

public interface OrderDao {

	void add2Orders(Order order) throws Exception;

	void add2Orderitem(OrderItem orderItem) throws Exception;

	List<Order> findAllByPage(int currPage, int pageSize, String uid) throws Exception;

	int getTotalCount(String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
