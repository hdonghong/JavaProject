package pers.hdh.management.action.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Dept;
import pers.hdh.management.service.DeptService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	DeptAction	
 * @Description	部门管理的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:13:38
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private Dept model = new Dept();
	@Override
	public Dept getModel() {
		return model;
	}
	
	// 分页查询
	private Page<Dept> page = new Page<>();
	public void setPage(Page<Dept> page) {
		this.page = page;
	}
	public Page<Dept> getPage() {
		return page;
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
		
		deptService.findPage("from Dept", page, Dept.class, null);
		// 设置分页的url
		page.setUrl("deptAction_list");
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
		Dept dept = deptService.get(Dept.class, model.getId());
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
		// 调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state=1", Dept.class, null);
		// 待查询的结果放入值栈中
		super.put("deptList", deptList);
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
		deptService.saveOrUpdate(model);
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
		Dept dept = deptService.get(Dept.class, model.getId());
		// 放入值栈
		super.push(dept);
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
		
		// 根据id获取一个Dept对象
		Dept dept = deptService.get(Dept.class, model.getId());
		
		// 设置需要修改的属性
		dept.setParent(model.getParent());
		dept.setDeptName(model.getDeptName());
		
		// 修改
		deptService.saveOrUpdate(dept);
		
		// 跳页面
		return "alist";
	}
}
