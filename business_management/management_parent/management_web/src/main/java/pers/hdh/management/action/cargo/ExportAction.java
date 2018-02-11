package pers.hdh.management.action.cargo;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.domain.Export;
import pers.hdh.management.domain.User;
import pers.hdh.management.service.ContractService;
import pers.hdh.management.service.ExportService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	ExportAction	
 * @Description	出口报运的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/02 10:13:38
 */
public class ExportAction extends BaseAction implements ModelDriven<Export> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private Export model = new Export();
	@Override
	public Export getModel() {
		return model;
	}
	
	// 分页查询
	private Page page = new Page<>();
	public void setPage(Page page) {
		this.page = page;
	}
	public Page getPage() {
		return page;
	}
	
	private ExportService exportService;
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}
	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		User currUser = super.getCurrUser();// 当前用户
		Integer degree = currUser.getUserinfo().getDegree();// 用户级别
		String hql = "from Export where 1=1 ";
		if (degree == 4) {// 员工
			hql += " and createBy='" + currUser.getId() + "'";
		} else if (degree == 3) {// 部门经理
			hql += " and createDept='" + currUser.getDept().getId() + "'";
		} else if (degree == 2) {// 管理本部门和下属部门
			
		} else if (degree == 1) {// 副总
			
		} else if (degree == 0) {// 总经理
			
		}
		
		exportService.findPage(hql, page, Export.class, null);
		// 设置分页的url
		page.setUrl("exportAction_list");
		// △将page对象压入栈顶
		super.push(page);
		
		return "list";
	}
	
	/**
	 * 查询状态为1的购销合同
	 * @return
	 * @throws Exception
	 */
	public String contractList() throws Exception {
		String hql = "from Contract where state=1";
		page = contractService.findPage(hql, page, Contract.class, null);
		page.setUrl("exportAction_contractList");
		super.push(page);
		return "";
	}
	
	/**
	 * 查看详情
	 * @return
	 * @throws Exception
	 */
	public String toview() throws Exception {
		// 调用service处理，根据id获取对象
		Export module = exportService.get(Export.class, model.getId());
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
		// 增加细粒度权限控制的数据
		model.setCreateBy(super.getCurrUser().getId());
		model.setCreateDept(super.getCurrUser().getDept().getId());
		// 添加
		exportService.saveOrUpdate(model);
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
		Export module = exportService.get(Export.class, model.getId());
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
		Export entity = exportService.get(Export.class, model.getId());
		// 设置需要修改的属性
		// 修改数据
		
		// 修改
		exportService.saveOrUpdate(entity);
		// 跳页面
		return "alist";
	}
	
	/**
	 * 解决批量删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		exportService.delete(Export.class, ids);
		return "alist";
	}

	/**
	 * 提交购销合同
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		String[] ids = model.getId().split(", ");
		// 遍历ids，通过id取出每个购销合同对象，再改为已上报状态
		exportService.changeState(ids, 1);
		return "alist";
	}
	
	/**
	 * 取消购销合同
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		String[] ids = model.getId().split(", ");
		// 遍历ids，通过id取出每个购销合同对象，再取消提交状态
		exportService.changeState(ids, 0);
		return "alist";
	}
}
