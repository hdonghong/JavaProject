package pers.hdh.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        chain.doFilter(new MyRequest(request), resp);
    }

    public void init(FilterConfig config) throws ServletException {

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