package team.xg2.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import team.xg2.domain.Apply;
import team.xg2.domain.PageBean;
import team.xg2.domain.User;
import team.xg2.service.ApplyService;
import team.xg2.service.UserService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.MyConverter;
import team.xg2.utils.UUIDUtils;

/**
 * 挂失申请记录模块
 */
public class AdminApplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询申请记录，条件查询，分页展示
	 * @throws Exception 
	 */
	public String findApplys(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取参数
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String username = request.getParameter("username");
		//当前页码
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		//设置每页显示数据条数
		int pageSize = 15;
		
		//调用service 获取一个pageBean
		ApplyService service = (ApplyService) BeanFactory.getBean("ApplyService");
		PageBean<Apply> pageBean = service.findApplys(currPage, pageSize, begin, end, username);
		
		//将参数放入request域中后请求转发
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("username", username);
		return "admin/apply/list.jsp";
	}

	/**
	 * 修改用户状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取参数
		String adesc = request.getParameter("adesc");
		String uid = request.getParameter("uid");
		int state = Integer.parseInt(request.getParameter("state"));
		
		User user = ((UserService)BeanFactory.getBean("UserService")).findByUid(uid);
		if (user == null) {
			return null;
		}
		Apply apply = new Apply(UUIDUtils.getID(), adesc, new Date(), user);
		
		//调用service完成对apply表的增添和user表的更新操作
		ApplyService service = (ApplyService) BeanFactory.getBean("ApplyService");
		service.add(apply, state);
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/adminUser?method=findUsers&currPage=1&begin=&end=&username=");
		return null;
	}
	
	/**
	 * 通过aid查询挂失记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findByAid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String aid = request.getParameter("aid");//获取参数
		Apply apply = ((ApplyService)BeanFactory.getBean("ApplyService")).findByAid(aid);
		request.setAttribute("apply", apply);
		return "/admin/apply/edit.jsp";
	}
	
	/**
	 * 更新apply表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Apply apply = new Apply();
		//转换器
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(apply, request.getParameterMap());
		//重新封装time，否则会因为转换器的存在使time丢失部分数据
		apply.setTime((((ApplyService) BeanFactory.getBean("ApplyService")).findByAid(request.getParameter("aid"))).getTime());
		
		//调用service更新数据
		((ApplyService)BeanFactory.getBean("ApplyService")).update(apply);
		
		//页面重定向
		response.sendRedirect(request.getContextPath()+"/adminApply?method=findApplys&currPage=1&begin=&end=&username=");
		return null;
	}
	
	/**
	 * 删除apply表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String aid = request.getParameter("aid");//获取参数
		((ApplyService)BeanFactory.getBean("ApplyService")).delete(aid);//调用service删除数据
		response.sendRedirect(request.getContextPath()+"/adminApply?method=findApplys&currPage=1&begin=&end=&username=");//页面重定向
		return null;
	}
}
