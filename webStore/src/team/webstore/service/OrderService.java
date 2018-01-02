package team.webstore.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;
import team.webstore.domain.PageBean;

/**
 * 订单模块的业务逻辑层
 * @author hdonghong 
 * @version 创建时间：2017年11月27日 下午8:01:00 
 */
public interface OrderService {

	/**
	 * 生成订单
	 * @param order
	 */
	void add(Orders order);

	/**
	 * 分页查询用户的个人订单
	 * @param criteria 查询条件
	 * @param currPage 当前页面
	 * @param pageSize 页面订单数
	 * @return
	 */
	PageBean<Orders> findByPage(DetachedCriteria criteria, Integer currPage, int pageSize);

	/**
	 * 删除订单
	 * @param order
	 */
	void delete(Orders order);

	/**
	 * 通过订单编号获取到订单
	 * @param order
	 * @return
	 */
	Orders getById(Orders order);

	/**
	 * 支付订单记录
	 * @param order
	 */
	void pay(Orders order);

	/**
	 * 更新订单的状态，0表示未支付，1表示待发货，2表示待收货，3表示待评价，4表示已完成
	 * @param order
	 */
	void updateState(Orders order);

	/**
	 * 添加评论，封装评论，匹配到对应订单项中，同时更新订单状态
	 * @param items
	 */
	void addComment(List<OrderItem> items);

}
