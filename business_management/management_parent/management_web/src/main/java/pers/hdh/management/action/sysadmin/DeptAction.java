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
		// 将page对象压入栈顶
		super.push(page);
		System.out.println(page.getResults());
		List<Dept> list = deptService.find("from Dept", Dept.class, null);
		System.out.println("list: " + list.size());
		
		
		return "list";
	}
	
}
