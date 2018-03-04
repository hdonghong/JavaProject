package team.xg2.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.xg2.domain.Apply;
import team.xg2.domain.User;
import team.xg2.service.ApplyService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.UUIDUtils;

/**
 * 挂失申请的Servlet
 */
public class ApplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 申请：包括 挂失处理，解锁申请，取消申请
	 * @throws Exception 
	 */
	public String apply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) { //用户未登录
			String msg = "请先登录";
			request.setAttribute("msg", msg);
			return "/jsp/message.jsp";
		}
		
		//获取参数
		String adesc = request.getParameter("adesc");
		int state = Integer.parseInt(request.getParameter("state"));
		Apply apply = new Apply(UUIDUtils.getID(), adesc, new Date(), user);
		
		//调用service完成对apply表的增添和user表的更新操作
		ApplyService service = (ApplyService) BeanFactory.getBean("ApplyService");
		service.add(apply, state);
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/jsp/apply.jsp");
		return null;
	}
	
}
