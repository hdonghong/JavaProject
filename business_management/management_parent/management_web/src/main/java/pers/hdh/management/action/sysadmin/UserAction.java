package pers.hdh.management.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Dept;
import pers.hdh.management.domain.User;
import pers.hdh.management.service.DeptService;
import pers.hdh.management.service.UserService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	UserAction	
 * @Description	用户管理的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:13:38
 */
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private User model = new User();
	@Override
	public User getModel() {
		return model;
	}
	
	// 分页查询
	private Page<User> page = new Page<>();
	public void setPage(Page<User> page) {
		this.page = page;
	}
	public Page<User> getPage() {
		return page;
	}
	
	// 注入UserService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	// 注入DeptService
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		
		userService.findPage("from User", page, User.class, null);
		// 设置分页的url
		page.setUrl("userAction_list");
		// △将page对象压入栈顶
		super.push(page);
		
		return "list";
	}
	
	/**
	 * 查看详情
	 * @return
	 * @throws Exception
	 */
	public String toview() throws Exception {
		// 调用service处理，根据id获取对象
		User user = userService.get(User.class, model.getId());
		// 压入栈顶
		super.push(user);
		// 跳转页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
		// 调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		List<User> userList = userService.find("from User where state=1", User.class, null);
		// 待查询的结果放入值栈中
		super.put("deptList", deptList);
		super.put("userList", userList);
		// 跳页面
		return "tocreate";
	}
	
	/**
	 * 新增部门
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 添加
		userService.saveOrUpdate(model);
		// 跳页面
		return "alist";
	}
	
	/**
	 * 进入修改页面
	 * @return
	 * @throws Exception
	 */
	public String toupdate() throws Exception {
		// 根据id获取一个对象
		User user = userService.get(User.class, model.getId());
		// 放入值栈
		super.push(user);
		// 查询父部门
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		// 压栈
		super.put("deptList", deptList);
		// 跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id获取一个对象
		User user = userService.get(User.class, model.getId());
		// 设置需要修改的属性
		user.setDept(model.getDept());
		user.setUserName(model.getUserName());
		user.setState(model.getState());
		// 修改
		userService.saveOrUpdate(user);
		// 跳页面
		return "alist";
	}
	
	/**
	 * 删除部门
	 * 解决批量删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		userService.delete(User.class, ids);
		return "alist";
	}
}
