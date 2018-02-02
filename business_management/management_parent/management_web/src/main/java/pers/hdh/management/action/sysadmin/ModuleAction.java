package pers.hdh.management.action.sysadmin;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Module;
import pers.hdh.management.service.ModuleService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	ModuleAction	
 * @Description	模块管理的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/02 10:13:38
 */
public class ModuleAction extends BaseAction implements ModelDriven<Module> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private Module model = new Module();
	@Override
	public Module getModel() {
		return model;
	}
	
	// 分页查询
	private Page<Module> page = new Page<>();
	public void setPage(Page<Module> page) {
		this.page = page;
	}
	public Page<Module> getPage() {
		return page;
	}
	
	// 注入ModuleService
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		
		moduleService.findPage("from Module", page, Module.class, null);
		// 设置分页的url
		page.setUrl("moduleAction_list");
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
		Module module = moduleService.get(Module.class, model.getId());
		// 压入栈顶
		super.push(module);
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
		moduleService.saveOrUpdate(model);
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
		Module module = moduleService.get(Module.class, model.getId());
		// 放入值栈
		super.push(module);
		// 跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id获取一个对象
		Module module = moduleService.get(Module.class, model.getId());
		// 设置需要修改的属性
		// 修改数据
		module.setName(model.getName());
		module.setLayerNum(model.getLayerNum());
		module.setCpermission(model.getCpermission());
		module.setCurl(model.getCurl());
		module.setCtype(model.getCtype());
		module.setState(module.getState());
		module.setBelong(model.getBelong());
		module.setCwhich(model.getCwhich());
		module.setRemark(model.getRemark());
		module.setOrderNo(model.getOrderNo());
		// 修改
		moduleService.saveOrUpdate(module);
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
		moduleService.delete(Module.class, ids);
		return "alist";
	}
}
