package pers.hdh.management.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Role;
import pers.hdh.management.service.RoleService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	RoleAction	
 * @Description	角色管理的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:13:38
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private Role model = new Role();
	@Override
	public Role getModel() {
		return model;
	}
	
	// 分页查询
	private Page<Role> page = new Page<>();
	public void setPage(Page<Role> page) {
		this.page = page;
	}
	public Page<Role> getPage() {
		return page;
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
		
		roleService.findPage("from Role", page, Role.class, null);
		// 设置分页的url
		page.setUrl("roleAction_list");
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
		Role dept = roleService.get(Role.class, model.getId());
		// 压入栈顶
		super.push(dept);
		// 跳转页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
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
		roleService.saveOrUpdate(model);
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
		Role role = roleService.get(Role.class, model.getId());
		// 放入值栈
		super.push(role);
		// 跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id获取一个User对象
		Role role = roleService.get(Role.class, model.getId());
		// 设置需要修改的属性
    	role.setName(model.getName());
    	role.setRemark(model.getRemark());
		// 修改
		roleService.saveOrUpdate(role);
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
		roleService.delete(Role.class, ids);
		return "alist";
	}
}
