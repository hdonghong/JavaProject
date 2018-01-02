package team.webstore.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;

/**
 * 订单模块的持久层
 * @author hdonghong 
 * @version 创建时间：2017年11月27日 下午8:02:40 
 */
public interface OrderDao {

	/**
	 * 往orders表添加一条记录，同时添加多条记录到orderitem表中
	 * @param order
	 */
	void add(Orders order);

	/**
	 * 查询指定用户的订单总数量
	 * @param criteria
	 * @return
	 */
	int getTotalCount(DetachedCriteria criteria);

	/**
	 * 分页查询订单列表记录
	 * @param criteria
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	List<Orders> getBeanListByPage(DetachedCriteria criteria, Integer currPage, int pageSize);

	/**
	 * 删除一条order表的记录
	 * @param order
	 */
	void delete(Orders order);

	/**
	 * 通过订单主键oid查询orders表，并返回记录
	 * @param order
	 * @return
	 */
	Orders getById(Orders order);

	/**
	 * 通过主键，更新order表中一条记录
	 * @param order
	 */
	void update(Orders order);

	/**
	 * 通过主键，获取订单项表中的一条记录
	 * @param item
	 * @return
	 */
	OrderItem getItemById(OrderItem item);

	/**
	 * 更新订单项表中的一条记录
	 * @param existedItem
	 */
	void updateOrderItem(OrderItem existedItem);

}
