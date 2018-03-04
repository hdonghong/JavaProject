package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.constant.Constant;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.MD5Utils;
import com.itheima.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("add方法执行了");
		
		return null;
	}
	
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.封装数据
		User user = new User();
		
		//如果user类使用java.util.Date包则需要转换器，使用java.sql.Date则不需要
		//注册自定义转换器
		//ConvertUtils.register(new MyConverter(), Date.class);
		//DateConverter does not support default String to 'Date' conversion.
		
		BeanUtils.populate(user, request.getParameterMap());
		//设置用户id
		user.setUid(UUIDUtils.getId());
		//设置激活码
		user.setCode(UUIDUtils.getCode());
		//加密密码
		user.setPassword(MD5Utils.md5(user.getPassword()));
		
		//2.调用service完成注册操作
		UserService service = new UserServiceImpl();
		service.regist(user);
		
		//3.页面请求转发
		request.setAttribute("msg", "注册成功，请前往邮箱激活账户");
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取激活码
		String code = request.getParameter("code");
		
		//2.调用service完成激活操作
		UserService service = new UserServiceImpl();
		User user = service.active(code);
		
		//判断user是否为空
		if (user == null) {
			request.setAttribute("msg", "激活失败，请重新激活或重新注册");
		} else {
			request.setAttribute("msg", "激活成功");
		}
		
		//3.页面请求转发
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	/**
	 * 账户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取用户名和密码
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		
		//2.调用service查询是否有该user
		UserService service = new UserServiceImpl();
		User user = service.login(username, password);
		
		//3.判断user是否为空
		if (user == null) { //user为空
			request.setAttribute("msg", "用户名与密码不匹配或该用户不存在");
			return "/jsp/login.jsp";
		} else {
			//判断账户是否已经激活
			if (user.getState() != Constant.USER_IS_STATE) { //账户未激活
				request.setAttribute("msg", "该账户未激活");
				return "/jsp/login.jsp";
			} else {
		//4.重定向
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/");
				return null;
			}
		}
	}
	
	/**
	 * 账户退出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.销毁session
		request.getSession().invalidate();
		//2.页面重定向
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
