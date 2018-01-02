package team.webstore.web.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.PageBean;
import team.webstore.domain.User;
import team.webstore.service.UserService;

/**
 * 用户管理的后台模块
 * @author hdonghong 
 * @version 创建时间：2017年12月9日 下午8:28:37 
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = -8758549258324675995L;

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = 
				currPage > 1 ? currPage : 1;
	}
	private Date begin;
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	private Date end;
	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * 查看所有用户
	 * 要求分页查看，条件查询
	 * @return
	 */
	public String findAll() {
		int pageSize = 17;
		// QBC 封装查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(user.getClass());
		// 按状态查询用户
		if (user.getState() != null && user.getState() >= 0) {
			criteria.add(Restrictions.eq("state", user.getState()));
		}
		// 按用户名查询用户
		if (user.getUsername() != null
				&& user.getUsername().trim().length() > 0) {
			criteria.add(Restrictions.like("username", "%"+ user.getUsername().trim() +"%"));
		}
		// 按订单生成时间查询
		if (begin != null) {
			criteria.add(Restrictions.gt("create_at", begin.getTime()));
		}
		if (end != null) {
			criteria.add(Restrictions.lt("create_at", end.getTime()));
		}
		// 上架时间倒序排序
		criteria.addOrder(Order.desc("create_at"));
		
		// 获取一个页面bean
		PageBean<User> page = userService.findByPage(criteria, currPage, pageSize);
		// 压栈
		ActionContext.getContext().getValueStack().set("page", page);			
		ActionContext.getContext().getValueStack().set("begin", begin);
		ActionContext.getContext().getValueStack().set("end", end);
		// 页面跳转
		return "user_list";
	}
	
	/**
	 * 更新用户信息
	 * @return
	 */
	public String update() {
		userService.update(user);
		return "user_findAll";
	}
	
	/**
	 * 查询用户后跳转页面
	 * @return
	 */
	public String getById() {
		user = userService.getById(user.getUid());
		ActionContext.getContext().getValueStack().set("user", user);			
		return "user_edit";
	}
	
	/**
	 * 管理员登录
	 * @return
	 */
	public String adminLogin() {
		
		
		
		return null;
	}

}
