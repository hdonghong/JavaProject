package team.xg2.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import team.xg2.domain.Bill;
import team.xg2.domain.PageBean;
import team.xg2.service.BillService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.MyConverter;

/**
 * 账单模块
 */
public class AdminBillServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询账单，条件查询，分页展示
	 * @throws Exception 
	 */
	public String findBills(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取参数
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String username = request.getParameter("username");
		String flag = request.getParameter("flag"); //标志，区分查询所有账单/支出账单/收入账单
		//当前页码
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		//设置每页显示数据条数
		int pageSize = 15;
		
		//调用service 获取一个pageBean
		BillService service = (BillService) BeanFactory.getBean("BillService");
		PageBean<Bill> pageBean = service.findBills(currPage, pageSize, begin, end, username, flag);
		
		//将参数放入request域中后请求转发
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("username", username);
		request.setAttribute("flag", flag);
		return "admin/bill/list.jsp";
	}
	
	/**
	 * 通过账单id查询bill表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findByBid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bid = request.getParameter("bid"); //获取id
		Bill bill = ((BillService)BeanFactory.getBean("BillService")).findByBid(bid);
		request.setAttribute("bill", bill);
		return "admin/bill/edit.jsp";
	}
	
	/**
	 * 更新bill表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Bill bill = new Bill();
		//转换器
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(bill, request.getParameterMap());
		//重新封装time，否则会因为转换器的存在使time丢失部分数据
		bill.setTime((((BillService) BeanFactory.getBean("BillService")).findByBid(request.getParameter("bid"))).getTime());
		
		//调用service更新数据
		((BillService)BeanFactory.getBean("BillService")).update(bill);
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/adminBill?method=findBills&currPage=1&begin=&end=&username=");
		return null;
	}
	
	/**
	 * 删除bill表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bid = request.getParameter("bid");//获取参数
		((BillService)BeanFactory.getBean("BillService")).delete(bid);//调用service删除数据
		response.sendRedirect(request.getContextPath()+"/adminBill?method=findBills&currPage=1&begin=&end=&username=");//页面重定向
		return null;
	}
}
