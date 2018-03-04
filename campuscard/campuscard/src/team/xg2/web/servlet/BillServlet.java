package team.xg2.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.xg2.domain.Bill;
import team.xg2.domain.PageBean;
import team.xg2.domain.User;
import team.xg2.service.BillService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.UUIDUtils;

/**
 * 账单的servlet
 */
public class BillServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 分页查询账单
	 * @throws Exception 
	 */
	public String findBills(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) { //用户未登录
			String msg = "请先登录";
			request.setAttribute("msg", msg);
			return "/jsp/message.jsp";
		} else if (user.getState() == 1 || user.getState() == 2) {
			request.setAttribute("msg", "您的账户处于“锁定状态”,其间无法查询消费记录");
			return "/jsp/message.jsp";
		}
		//获取查询条件
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String condition = request.getParameter("condition");
		//获取当前页码
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		
		//设置页面显示数据条数为11条
		int pageSize = 11;
		
		//调用service查询 封装一个pagebean对象
		BillService service = (BillService) BeanFactory.getBean("BillService");
		PageBean<Bill> pageBean = service.findBills(currPage, pageSize, user, begin, end, condition);
		
		//将参数放入request域中后请求转发
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("condition", condition);
		return "/jsp/bill_list.jsp";
	}
	
	/**
	 * 余额充值
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		String msg = "请先登录"; //假设用户未登录
		if (user == null) {
			request.setAttribute("msg", msg);
			return "/jsp/message.jsp";
		} else if (user.getState() == 1 || user.getState() == 2) {
			request.setAttribute("msg", "您的账户处于“锁定状态”,其间无法进行消费或余额充值");
			return "/jsp/message.jsp";
		}
		
		//获取充值金额
		Double money = Double.parseDouble(request.getParameter("money"));
		//封装一条账单记录
		Bill bill = new Bill(UUIDUtils.getID(),"余额充值",money,new Date(),user);
		
		//调用service添加数据
		BillService service = (BillService) BeanFactory.getBean("BillService");
		service.add(bill);
		msg="余额充值成功";
		
		//页面请求转发
		request.setAttribute("msg", msg);
		return "jsp/message.jsp";
	}
}
