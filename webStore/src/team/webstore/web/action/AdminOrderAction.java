package team.webstore.web.action;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;
import team.webstore.domain.PageBean;
import team.webstore.service.OrderService;
import team.webstore.utils.FastJsonUtils;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年12月9日 下午12:22:06 
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Orders> {

	private static final long serialVersionUID = 8739008549105532167L;

	private Orders order = new Orders();
	@Override
	public Orders getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Integer currPage = 1;
	private Date begin;
	private Date end;
	public void setCurrPage(Integer currPage) {
		this.currPage = 
				currPage > 1 ? currPage : 1;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	/**
	 * 分页查询订单
	 * @return
	 */
	public String findAll() {
		int pageSize = 17;
		// QBC 封装查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(order.getClass());
		// 按照订单状态展示订单
		if (order.getState() != null && order.getState() >= 0) {
			criteria.add(Restrictions.eq("state", order.getState()));
		}
		// 按用户名查询订单
		if (order.getUser() != null 
				&& order.getUser().getUsername() != null
				&& order.getUser().getUsername().trim().length() > 0) {
			criteria.createCriteria("user").
				add(Restrictions.like("username", "%"+ order.getUser().getUsername().trim() +"%"));
		}
		// 按订单生成时间查询
		if (begin != null) {
			criteria.add(Restrictions.gt("create_at", begin.getTime()));
		}
		if (end != null) {
			criteria.add(Restrictions.lt("create_at", end.getTime()));
		}
		// 上架时间倒序排序
		criteria.addOrder(Order.desc("create_at"));
		
		// 获取一个页面bean
		PageBean<Orders> page = orderService.findByPage(criteria, currPage, pageSize);
		// 压栈
		ActionContext.getContext().getValueStack().set("page", page);			
		ActionContext.getContext().getValueStack().set("begin", begin);
		ActionContext.getContext().getValueStack().set("end", end);
		// 页面跳转
		return "order_list";
	}
	
	/**
	 * 查看订单项
	 * @return
	 */
	public String showOrderItems() {
		order = orderService.getById(order);
		// 获取订单项
		Set<OrderItem> orderItems = order.getOrderItems();
		// 转成json格式数据
		String jsonString = FastJsonUtils.toJSONString(orderItems);
		// 写回浏览器
		FastJsonUtils.write_json(ServletActionContext.getResponse(), jsonString);
		
		return NONE;
	}
	
	/**
	 * 更新订单状态
	 * @return
	 */
	public String updateState() {
		orderService.updateState(order);
		return "order_findAll";
	}
	
	/**
	 * 取消该订单
	 * @return
	 */
	public String delete() {
		orderService.delete(order);
		return "order_findAll";
	}
	
}
