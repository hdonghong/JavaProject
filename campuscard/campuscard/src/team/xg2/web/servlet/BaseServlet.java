package team.xg2.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的 Servlet，当前包下所有servlet的父类
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 通过反射执行指定的方法
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取指定的方法名
		String methodName = request.getParameter("method");
		
		//获取方法对象
		Class<? extends BaseServlet> clazz = this.getClass();
		try {
			Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			//执行方法， 获取返回值(返回值是请求转发路径)
			if (method != null) {
			String result = (String) method.invoke(this, request, response);
				if (result != null) { //请求转发
					request.getRequestDispatcher(result).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "操作错误");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
		}
	}
	
}
