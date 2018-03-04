package team.xg2.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import team.xg2.domain.PageBean;
import team.xg2.domain.User;
import team.xg2.service.UserService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.DataSourceUtils;
import team.xg2.utils.MyConverter;

/**
 * 用户管理模块
 */
public class AdminUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询用户,条件查询,分页展示
	 * @throws Exception 
	 */
	public String findUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取参数
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		//当前页码
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		//设置每页显示数据条数
		int pageSize = 15;
		
		//调用service 获取一个pageBean
		UserService service = (UserService) BeanFactory.getBean("UserService");
		PageBean<User> pageBean = service.findUsers(currPage, pageSize, begin, end, username, state);
		
		//将参数放入request域中后请求转发
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("username", username);
		request.setAttribute("state", state);
		return "admin/user/list.jsp";
	}
	
	/**
	 * 通过uid查找用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findByUid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uid = request.getParameter("uid");
		User user = ((UserService)BeanFactory.getBean("UserService")).findByUid(uid);
		request.setAttribute("user", user);
		return "/admin/user/edit.jsp";
	}
	
	/**
	 * 修改user表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//封装数据
		User user = new User();
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());
		//重新封装time，否则会因为转换器的存在使time丢失部分数据
		user.setTime((((UserService) BeanFactory.getBean("UserService")).findByUid(request.getParameter("uid"))).getTime());
		//调用service更新数据
		UserService service = (UserService) BeanFactory.getBean("UserService");
		service.update(user);
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/adminUser?method=findUsers&currPage=1&begin=&end=&username=");
		return null;
	}
	
	/**
	 * 删除user表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uid = request.getParameter("uid");//获取参数
		((UserService)BeanFactory.getBean("UserService")).delete(uid);//调用service删除数据
		response.sendRedirect(request.getContextPath()+"/adminUser?method=findUsers&currPage=1&begin=&end=&username=");//页面重定向
		return null;
	}
	
	/**
	 * 管理员登录，简单实现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String adusername = request.getParameter("adusername");
		String adpassword = request.getParameter("adpassword");
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where username=? and password=? limit 1 ";
		User admin = qr.query(sql, new BeanHandler<>(User.class), adusername, adpassword);
		if (admin == null) {
			response.sendRedirect(request.getContextPath()+"/admin/");
		} else {
			request.getSession().setAttribute("admin", admin);
			response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
		}
		return null;
	}
}
