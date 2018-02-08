package pers.hdh.management.action.cargo;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.service.ContractService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName	ContractAction	
 * @Description	购销合同的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/02 10:13:38
 */
public class ContractAction extends BaseAction implements ModelDriven<Contract> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private Contract model = new Contract();
	@Override
	public Contract getModel() {
		return model;
	}
	
	// 分页查询
	private Page<Contract> page = new Page<>();
	public void setPage(Page<Contract> page) {
		this.page = page;
	}
	public Page<Contract> getPage() {
		return page;
	}
	
	// 注入ContractService
	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		
		contractService.findPage("from Contract", page, Contract.class, null);
		// 设置分页的url
		page.setUrl("contractAction_list");
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
		Contract module = contractService.get(Contract.class, model.getId());
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
		contractService.saveOrUpdate(model);
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
		Contract module = contractService.get(Contract.class, model.getId());
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
		Contract entity = contractService.get(Contract.class, model.getId());
		// 设置需要修改的属性
		// 修改数据
		entity.setCustomName(model.getCustomName());
		entity.setCustomName(model.getCustomName());
		entity.setPrintStyle(model.getPrintStyle());
		entity.setContractNo(model.getContractNo());
		entity.setOfferor(model.getOfferor());
		entity.setInputBy(model.getInputBy());
		entity.setCheckBy(model.getCheckBy());
		entity.setInspector(model.getInspector());
		entity.setSigningDate(model.getSigningDate());
		entity.setImportNum(model.getImportNum());
		entity.setShipTime(model.getShipTime());
		entity.setTradeTerms(model.getTradeTerms());
		entity.setDeliveryPeriod(model.getDeliveryPeriod());
		entity.setCrequest(model.getCrequest());
		entity.setRemark(model.getRemark());
		
		// 修改
		contractService.saveOrUpdate(entity);
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
		contractService.delete(Contract.class, ids);
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
		contractService.changeState(ids, 1);
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
		contractService.changeState(ids, 0);
		return "alist";
	}
}
