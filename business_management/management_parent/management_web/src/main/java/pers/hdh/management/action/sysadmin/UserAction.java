package pers.hdh.management.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Dept;
import pers.hdh.management.domain.Role;
import pers.hdh.management.domain.User;
import pers.hdh.management.exception.SysException;
import pers.hdh.management.service.DeptService;
import pers.hdh.management.service.RoleService;
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
	// 注入RoleService
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
		try {
			// 调用service处理，根据id获取对象
			User user = userService.get(User.class, model.getId());
			// 压入栈顶
			super.push(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("对不起，请先选择后操作");
		}
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
	
	/**
	 * 进入角色分配页面，显示角色列表
	 * @return
	 * @throws Exception
	 */
	public String torole() throws Exception {
		// 通过id获取相应对象
		User entity = userService.get(User.class, model.getId());
		// 将对象保存到值栈中
		this.push(entity);
		// 将当前用户的角色拼接成字符串保存到值栈中
		StringBuilder roleStr = new StringBuilder();
		for (Role role : entity.getRoles()) {
			roleStr.append(role.getName() + ",");
		}
		this.put("roleStr", roleStr);
		// 获取角色列表
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		// 将角色列表保存到值栈中
		this.put("roleList", roleList);
		
		return "torole";
	}
	
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 * 修改用户角色
	 * @return
	 * @throws Exception
	 */
	public String role() throws Exception {
		// 通过id获取对象
		User entity = userService.get(User.class, model.getId());
		// 获取修改的角色对象
		Set<Role> roles = new HashSet<>();
		for (String roleId : roleIds) {
			roles.add(roleService.get(Role.class, roleId));
		}
		// 保存到user对象中
		entity.setRoles(roles);
		// 调用业务层进行逻辑处理，修改的是ROLE_USER_P表
		userService.saveOrUpdate(entity);
		
		return "alist";
	}

}
