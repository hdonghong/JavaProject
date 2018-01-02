package team.webstore.web.action;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.Cart;
import team.webstore.domain.CartItem;
import team.webstore.domain.OrderItem;
import team.webstore.domain.Orders;
import team.webstore.domain.PageBean;
import team.webstore.domain.User;
import team.webstore.service.OrderService;
import team.webstore.utils.PaymentUtils;

/**
 * 订单模块的控制器
 * @author hdonghong 
 * @version 创建时间：2017年11月27日 下午5:40:04 
 */
public class OrderAction extends ActionSupport implements ModelDriven<Orders> {

	private static final long serialVersionUID = 6692020866569877329L;

	private Orders order = new Orders();
	@Override
	public Orders getModel() {
		return order;
	}
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	/**
	 * 生成订单
	 * @return
	 */
	public String add() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart != null) {
			// 封装订单数据生成订单
			order = new Orders();
			// 订单用户
			order.setUser(user);
			// 订单金额
			order.setTotal(cart.getTotal());
			// 订单状态
			order.setState(0);
			// 订单项
			Collection<CartItem> cartItems = cart.getCartItems();
			for (CartItem cartItem : cartItems) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(cartItem.getCount());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItem.setProduct(cartItem.getProduct());
				// 单向关联
				order.getOrderItems().add(orderItem);
			}
			
			// 到业务层处理
			orderService.add(order);
			// 清空购物车
			session.removeAttribute("cart");
			// 压栈
			ActionContext.getContext().getValueStack().set("order", order);
		}
		
		return "order_info";
	}
	
	/**
	 * 支付订单前查询到此订单
	 * @return
	 */
	public String getById() {
		// 通过id获取到订单
		order = orderService.getById(order);
		// 压栈
		ActionContext.getContext().getValueStack().set("order", order);
		return "order_info";
	}
	
	private String payMethod;
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * 订单支付功能
	 * @return
	 */
	public String pay() {
		
		if (payMethod != null) {
			if (payMethod.equals("alipay")) { // 选择支付宝支付
				orderService.pay(order);
				ActionContext.getContext().getValueStack().set("order", order);
				return "order_alipay";
			} else if (payMethod.equals("bank")) { // 选择网银支付
				orderService.pay(order);
				payByBank();
				return NONE;
			} else if (payMethod.equals("cod")) { // 选择货到付款
				HttpServletRequest request = ServletActionContext.getRequest();
				String out_trade_no = request.getParameter("out_trade_no");
				if (out_trade_no != null) {
					order.setOid(out_trade_no);
				}
				// 将state设为1，表示已支付
				order.setState(1);
			}
		}
		
		
		orderService.pay(order);
		return "order_findByPage";
	}
	
	/**
	 * 用户选择网银支付时调用的方法
	 */
	public void payByBank() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		// 组织发送支付公司需要哪些数据
		String pd_FrpId = request.getParameter("pd_FrpId");
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = order.getOid();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtils.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
	
		
		//发送给第三方
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 网银支付的第三方所调用的回调函数
	 * @return
	 * @throws Exception
	 */
	public String callback() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtils.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				// System.out.println("付款成功！222");
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态
//			OrderService s=(OrderService) BeanFactory.getBean("OrderService");
//			Order order = s.getById(r6_Order);
			order.setState(1);
			orderService.pay(order);
			return "order_findByPage";
//			
//			s.updateOrder(order);
			
		} else {
			// 数据无效
			System.out.println("数据被篡改！");
		}
		
		return "msg";
	}
	
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = 
				currPage > 1 ? currPage : 1;
	}


	/**
	 * 分页展示个人订单
	 * @return
	 */
	public String findByPage() {
		// 设置每页展示订单数
		int pageSize = 3;
		// 获取当前用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user != null) {
			// 封装查询条件
			DetachedCriteria criteria = DetachedCriteria.forClass(Orders.class);
			criteria.add(Restrictions.eq("user.uid", user.getUid()));
			criteria.addOrder(Order.desc("create_at"));
			// 查询
			PageBean<Orders> pageBean = orderService.findByPage(criteria, currPage, pageSize);
			// 压栈
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			// 请求转发
			return "order_list";
		}
		
		return NONE;
	}
	
	/**
	 * 取消订单，通过订单编号
	 * @return
	 */
	public String cancelOrder() {
		orderService.delete(order);
		return "order_findByPage";
	}

	/**
	 * 更新订单状态
	 * @return
	 */
	public String updateState() {
		orderService.updateState(order);
		return "order_findByPage";
	}
	
	/**
	 * 跳转到评价商品页面
	 * @return
	 */
	public String comment() {
		// 通过id获取到订单
		order = orderService.getById(order);
		// 压栈
		ActionContext.getContext().getValueStack().set("order", order);
		return "order_comment";
	}
	
	
	private List<OrderItem> items;
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	/**
	 * 添加商品评价
	 * @return
	 */
	public String addComment() {
		if (items != null) {
			orderService.addComment(items);
		}
		return "order_findByPage";
	}
}
