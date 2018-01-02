package team.webstore.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import team.webstore.domain.User;

/**
 * 前台的用户登录拦截，对比没有登录的用户不允许其进行购买商品等操作，
 * 并跳转到信息提示/登录页面
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 上午11:19:41 
 */
public class UserInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 3315623049694874949L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取session对象
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null) {
			// 没有登录
			ActionContext.getContext().getValueStack().set("msg", "请先登录");
			return "msg";
		}
		
		return invocation.invoke();
	}

}
