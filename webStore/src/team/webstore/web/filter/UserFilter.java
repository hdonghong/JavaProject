package team.webstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.webstore.domain.User;

/**
 * 用户权限控制的过滤器
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午6:51:59 
 */
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 强转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// 处理业务层逻辑
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			req.setAttribute("msg", "请先登录");
			req.getRequestDispatcher("/jsp/msg.jsp").forward(req, resp);;
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
