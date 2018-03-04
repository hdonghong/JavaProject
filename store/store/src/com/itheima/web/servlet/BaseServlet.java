package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的servlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//1.获取class对象
			Class<? extends BaseServlet> clazz = this.getClass();
			
			//2.获取请求的方法名
			String m = req.getParameter("method");
			if (m == null) {
				m = "index";
			}
			
			//3.获取method对象
			Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);
			
			//4.执行方法,获取请求转发的路径
			String dispatcherPath = (String)method.invoke(this, req, resp);
			
			//5.判断是否需要请求转发
			if (dispatcherPath != null) {
				req.getRequestDispatcher(dispatcherPath).forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return null;
	}
}
