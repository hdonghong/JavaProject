package com.itheima.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 往订单表中插入一条数据
	 */
	@Override
	public void add2Orders(Order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //转换日期格式 精确到时分秒
		qr.update(DataSourceUtils.getConnection(), sql, order.getOid(), sdformat.format(order.getOrdertime()), order.getTotal(),
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	/**
	 * 往订单项表中插入一条数据
	 */
	@Override
	public void add2Orderitem(OrderItem orderItem) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
				orderItem.getProduct().getPid(), orderItem.getOrder().getOid());
	}

	/**
	 * 分页查询用户订单
	 */
	@Override
	public List<Order> findAllByPage(int currPage, int pageSize, String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ? order by ordertime desc limit ?, ?";
		List<Order> orderList = qr.query(sql, new BeanListHandler<>(Order.class), uid, (currPage-1)*pageSize, pageSize);
		
		sql = "select * from orderitem a, product b where a.pid = b.pid and a.oid=?";
		for (Order order : orderList) {
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : mapList) {
				//封装product
				Product product = new Product();
				BeanUtils.populate(product, map);
				
				//封装orderitem
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				//将product封装到orderitem中
				orderItem.setProduct(product);
				
				//将orderitem封装到order中
				order.getOrderItems().add(orderItem);
			}
		}
		
		return orderList;
	}

	/**
	 * 分页查询用户总订单数
	 */
	@Override
	public int getTotalCount(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid = ?";
		return ((Long)qr.query(sql, new ScalarHandler<>(), uid)).intValue();
	}

	/**
	 * 通过id获取订单
	 */
	@Override
	public Order getById(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where oid = ? limit 1";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
		
		sql = "select * from orderitem a, product b where a.pid=b.pid and a.oid=?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), oid);
		
		for (Map<String, Object> map : mapList) {
			//封装product
			Product product = new Product();
			BeanUtils.populate(product, map);
			//封装orderitem
			OrderItem orderItem = new OrderItem();
			BeanUtils.populate(orderItem, map);
			orderItem.setProduct(product);
			//封装order
			order.getOrderItems().add(orderItem);
			//order.setTotal(order.getTotal()+orderItem.getSubtotal());
		}
		
		return order;
	}

	/**
	 * 更新orders表数据
	 */
	@Override
	public void update(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=?, address=?, name=?, telephone=? where oid=?";
		qr.update(sql, order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
	}

	/**
	 * 根据状态查询订单
	 */
	@Override
	public List<Order> findAllByState(String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where 1=1 ";
		if (state != null && state.trim().length()>0) {
			sql += " and state = ? order by ordertime desc";
			return qr.query(sql, new BeanListHandler<>(Order.class), state);
		}
		sql += " order by ordertime desc";
		return qr.query(sql, new BeanListHandler<>(Order.class));
	}

}
