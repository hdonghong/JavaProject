package pers.hdh.management.action.cargo;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.ContractProduct;
import pers.hdh.management.domain.Factory;
import pers.hdh.management.service.ContractProductService;
import pers.hdh.management.service.FactoryService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName ContractProductAction
 * @Description 货物管理的控制器
 * @author hdonghong
 * @version v1.0
 * @since 2018/02/02 10:13:38
 */
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private ContractProduct model = new ContractProduct();

	@Override
	public ContractProduct getModel() {
		return model;
	}

	// 分页查询
	private Page<ContractProduct> page = new Page<>();

	public void setPage(Page<ContractProduct> page) {
		this.page = page;
	}

	public Page<ContractProduct> getPage() {
		return page;
	}

	// 注入ContractProductService
	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	// 注入FactoryService
	private FactoryService factoryService;

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	/**
	 * 进入新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
		// 查出所有生产该货物的厂家
		String hql = "from Factory where ctype='货物' and state=1";
		List<Factory> factoryList = factoryService.find(hql, Factory.class, null);

		// 查出该该购销合同下所有货物
		hql = "from ContractProduct where contract.id=?";
		contractProductService.findPage(hql, page, ContractProduct.class,
				new String[] { model.getContract().getId() });
		page.setUrl("contractProductAction_tocreate");
		
		// 放入值栈中
		super.put("factoryList", factoryList);
		super.push(page);

		// 跳页面
		return "tocreate";
	}

	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 添加
		contractProductService.saveOrUpdate(model);
		// 跳页面
		return tocreate();
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toupdate() throws Exception {
		// 根据id获取一个对象
		ContractProduct entity = contractProductService.get(ContractProduct.class, model.getId());
		// 放入值栈
		super.push(entity);
		// 查询出有生产货物的商家列表，并将结果放入值栈
		String hql = "from Factory where ctype='货物' and state=1";
		List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
		super.put("factoryList", factoryList);
		// 跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id获取一个对象
		ContractProduct entity = contractProductService.get(ContractProduct.class, model.getId());
		// 设置需要修改的属性
		entity.setFactory(model.getFactory());
		entity.setFactoryName(model.getFactoryName());
		entity.setProductNo(model.getProductNo());
		entity.setProductImage(model.getProductImage());
		entity.setCnumber(model.getCnumber());
	    entity.setAmount(model.getAmount());
	    entity.setPackingUnit(model.getPackingUnit());
	    entity.setLoadingRate(model.getLoadingRate());
	    entity.setBoxNum(model.getBoxNum());
	    entity.setPrice(model.getPrice());
	    entity.setOrderNo(model.getOrderNo());
	    entity.setProductDesc(model.getProductDesc());   
	    entity.setProductRequest(model.getProductRequest());
		// 修改
		contractProductService.saveOrUpdate(entity);
		// 跳页面
		return tocreate();
	}

	/**
	 * 解决批量删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		contractProductService.delete(ContractProduct.class, model);
		return tocreate();
	}
}
