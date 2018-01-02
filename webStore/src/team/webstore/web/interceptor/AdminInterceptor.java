package team.webstore.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import team.webstore.domain.Admin;

/**
 * 后台的管理员登录拦截，管理员必须登录后才能操作
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:20:39 
 */
public class AdminInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 2352155804257060445L;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取session对象
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if (admin == null) {
			// 没有登录
			return "adminLogin";
		}
		
		return invocation.invoke();
	}

}
