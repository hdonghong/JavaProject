package team.xg2.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		chain.doFilter(new MyRequest(req), resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private boolean flag=true;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String[] arr = getParameterValues(name);
		if (arr == null || arr.length == 0) {
			return null;
		}
		return arr[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		Map<String, String[]> map = getParameterMap();
		if (map == null || map.size() == 0) {
			return null;
		}
		
		return map.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		//获取请求方式
		String requestMethod = request.getMethod();
		
		if ("post".equalsIgnoreCase(requestMethod)) {
			try {
				request.setCharacterEncoding("utf-8");
				return super.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if ("get".equalsIgnoreCase(requestMethod)) {
			Map<String,String[]> map = super.getParameterMap();
			if (flag) {
				for (String key : map.keySet()) {
					String[] arr = map.get(key);
					for (int i = 0; i < arr.length; i++) {
						try {
							arr[i] = new String(arr[i].getBytes("iso8859-1"), "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
				flag = false; //false说明已经转码过，不用重复
			}
			return map;
		}
		
		return super.getParameterMap();
	}
}
