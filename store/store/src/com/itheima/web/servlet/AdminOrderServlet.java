package com.itheima.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 订单管理模块
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 按状态查询订单
	 * @throws Exception 
	 */
	public String findAllByState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.接收state
		String state = request.getParameter("state");
		//2.调用service
		OrderService service = (OrderService) BeanFactory.getBean("OrderService");
		List<Order> list = service.findAllByState(state);
		//3.将list放入域中
		request.setAttribute("list", list);
		return "/admin/order/list.jsp";
	}
	
	/**
	 * 通过订单id获取订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getDetailByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取oid
		String oid = request.getParameter("oid");
		//2.获取订单项
		OrderService service = (OrderService) BeanFactory.getBean("OrderService");
		List<OrderItem> list = service.getById(oid).getOrderItems();
		//3.将list转成json格式数据
		JsonConfig configJson = JsonUtil.configJson(new String[] {"class", "itemid", "order", "pdate"});
		JSONArray json = JSONArray.fromObject(list, configJson);
		//4.
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 修改订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取oid state
		String oid = request.getParameter("oid");
		int state = Integer.parseInt(request.getParameter("state"));
		//2.调用service查询后修改
		OrderService service = (OrderService) BeanFactory.getBean("OrderService");
		Order order = service.getById(oid);
		order.setState(state);
		service.updateOrder(order);
		//3.页面重定向
		response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
		return null;
	}
}
