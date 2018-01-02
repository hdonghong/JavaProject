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

import team.webstore.domain.Admin;

/**
 * 管理员权限控制的过滤器
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午6:51:59 
 */
public class AdminFilter implements Filter {

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
		Admin admin = (Admin) req.getSession().getAttribute("admin");
		if (req.getRequestURI().equals("/webStore/admin/") || req.getRequestURI().contains("index")) {}
		else if (admin == null) {
			resp.sendRedirect(req.getContextPath()+"/admin");
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
