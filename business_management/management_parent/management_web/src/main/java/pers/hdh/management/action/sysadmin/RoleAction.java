package pers.hdh.management.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Module;
import pers.hdh.management.domain.Role;
import pers.hdh.management.service.ModuleService;
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
	// 注入ModuleServiec
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
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
		Role role = roleService.get(Role.class, model.getId());
		// 压入栈顶
		super.push(role);
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
		// 根据id获取一个对象
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
	
	/**
	 * 跳转到权限模块分配页面
	 */
	public String tomodule() throws Exception {
		// 通过id获取对象
		Role entity = roleService.get(Role.class, model.getId());
		// 存入值栈
		this.push(entity);
		
		return "tomodule";
	}

	/**
	 * 拼接json格式字符串返回到jRoleModule.jsp页面
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr() throws Exception {
		// 通过id获取对象
		Role entity = roleService.get(Role.class, model.getId());
		// 对象导航方式获取当前角色下的module
		Set<Module> currModules = entity.getModules();
		// 获取所有module
		List<Module> allModules = moduleService.find("from Module", Module.class, null);
		// 拼接json串 [{"id":"模块id","parentId":"父模块","name":"模块名","checked":true|false},...]
		StringBuilder jsonStr = new StringBuilder("[");
		for (Module module : allModules) {
			jsonStr.append("{\"id\":\"").append(module.getId());
			jsonStr.append("\",\"pId\":\"").append(module.getParentId());
			jsonStr.append("\",\"name\":\"").append(module.getName());
			jsonStr.append("\",\"checked\":").append(currModules.contains(module));
			jsonStr.append("},");
		}
		jsonStr.replace(jsonStr.length()-1, jsonStr.length(), "]");
		// 获取response对象，输出json串
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(jsonStr.toString());
		
		return NONE;
	}
	
	private String moduleIds;
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
	/**
	 * 更新角色下的权限模块信息
	 * @return
	 * @throws Exception
	 */
	public String module() throws Exception {
		// 通过id获取角色对象
		Role entity = roleService.get(Role.class, model.getId());
		// 封装当前角色更新后的权限模块
		Set<Module> modules = new HashSet<>();
		String[] moduleIdsArr = moduleIds.split(",");
		for (String moduleId : moduleIdsArr) {
			// 添加选中的模块
			modules.add(moduleService.get(Module.class, moduleId));
		}
		// 角色分配新的权限模块
		entity.setModules(modules);
		// 调用业务处理方法
		roleService.saveOrUpdate(entity);
		// 跳页面
		return "alist";
	}
	
}
