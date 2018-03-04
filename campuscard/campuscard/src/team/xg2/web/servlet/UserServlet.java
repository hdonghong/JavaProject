package team.xg2.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import team.xg2.domain.User;
import team.xg2.service.UserService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.MyConverter;
import team.xg2.utils.UUIDUtils;

/**
 * 用户的servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 跳转到登录页面
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "jsp/index.jsp";
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取输入的账号和用户名
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//调用service进行查询
		UserService service = (UserService) BeanFactory.getBean("UserService");
		User user = service.findByUsernameAndPwd(username, password);
		
		if (user == null) {
			request.setAttribute("msg", "不存在的账户或密码错误");
			return "/";
		} else {
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/jsp/home.jsp");
			return null;
		}
	}
	
	
	/**
	 * 跳转到注册页面
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "jsp/regist.jsp";
	}
	
	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//封装数据
		User user = new User();
		user.setUid(UUIDUtils.getID());
		user.setTime(new Date());
		//注册自定义转换器
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());
		
		//调用service执行业务逻辑，往user表添加一个用户
		UserService service = (UserService) BeanFactory.getBean("UserService");
		
		//二次查验，确保无重用账号
		if (service.findByUsername(user.getUsername()) == 1) {
			//账号重名了
			response.sendRedirect(request.getContextPath()+"/jsp/registError.jsp");
			return null;
		}
		service.add(user);
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/jsp/registSuccess.jsp");
		return null;
	}
	
	/**
	 * 查询已存在账户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findByUsername(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取账号
		String username = request.getParameter("username");
		//调用service查询
		UserService service = (UserService) BeanFactory.getBean("UserService");
		int flag = service.findByUsername(username);
		
		//将标志写回浏览器
		response.getWriter().print(flag);
		return null;
	}
	
	/**
	 * 退出用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//销毁session
		request.getSession().invalidate();
		//页面重定向
		response.sendRedirect(request.getContextPath());
		return null;
	}
	
	/**
	 * 更新用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//封装数据
		User user = new User();
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());
		user.setTime(((User)request.getSession().getAttribute("user")).getTime());
		
		//调用service更新数据
		UserService service = (UserService) BeanFactory.getBean("UserService");
		service.update(user);
		
		//更新数据重新获取user，更新session域中的user数据
		user = service.findByUsernameAndPwd(user.getUsername(), user.getPassword());
		
		//将新的user放入session中覆盖旧的user
		request.getSession().setAttribute("user", user);
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/jsp/personal_info.jsp");
		return null;
	}
}
