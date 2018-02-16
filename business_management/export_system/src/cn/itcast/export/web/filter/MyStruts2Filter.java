package cn.itcast.export.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * 自制过滤器，不拦截url中包含ws的
 */
public class MyStruts2Filter extends StrutsPrepareAndExecuteFilter {


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest) req).getRequestURI().contains("/ws")) {
			chain.doFilter(req, resp);// 放行
		} else 
			super.doFilter(req, resp, chain);
	}
}
