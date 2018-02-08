package pers.hdh.management.action.cargo;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.ExtCproduct;
import pers.hdh.management.domain.Factory;
import pers.hdh.management.service.ExtCproductService;
import pers.hdh.management.service.FactoryService;
import pers.hdh.management.utils.Page;

/**
 * @ClassName ExtCproductAction
 * @Description 附件管理的控制器
 * @author hdonghong
 * @version v1.0
 * @since 2018/02/02 10:13:38
 */
public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {

	private static final long serialVersionUID = 5486033529237218292L;

	// 模型驱动
	private ExtCproduct model = new ExtCproduct();

	@Override
	public ExtCproduct getModel() {
		return model;
	}

	// 分页查询
	private Page<ExtCproduct> page = new Page<>();

	public void setPage(Page<ExtCproduct> page) {
		this.page = page;
	}

	public Page<ExtCproduct> getPage() {
		return page;
	}

	// 注入ExtCproductService
	private ExtCproductService extCproductService;
	public void setExtCproductService(ExtCproductService extCproductService) {
		this.extCproductService = extCproductService;
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
		// 查出所有生产附件的厂家
		String hql = "from Factory where ctype='附件' and state=1";
		List<Factory> factoryList = factoryService.find(hql, Factory.class, null);

		// 查出该当前货物下的附件
		hql = "from ExtCproduct where contractProduct.id=?";
		extCproductService.findPage(hql, page, ExtCproduct.class,
				new String[] { model.getContractProduct().getId() });
		page.setUrl("extCproductAction_tocreate");
		
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
		extCproductService.saveOrUpdate(model);
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
		ExtCproduct entity = extCproductService.get(ExtCproduct.class, model.getId());
		// 放入值栈
		super.push(entity);
		// 查询出有生产货物的商家列表，并将结果放入值栈
		String hql = "from Factory where ctype='附件' and state=1";
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
		ExtCproduct entity = extCproductService.get(ExtCproduct.class, model.getId());
		// 设置需要修改的属性
		entity.setFactory(model.getFactory());
		entity.setFactoryName(model.getFactoryName());
		entity.setProductNo(model.getProductNo());
		entity.setProductImage(model.getProductImage());
		entity.setCnumber(model.getCnumber());
	    entity.setAmount(model.getAmount());
	    entity.setPackingUnit(model.getPackingUnit());
	    entity.setPrice(model.getPrice());
	    entity.setOrderNo(model.getOrderNo());
	    entity.setProductDesc(model.getProductDesc());   
	    entity.setProductRequest(model.getProductRequest());
		// 修改
		extCproductService.saveOrUpdate(entity);
		// 跳页面
		return tocreate();
	}

	/**
	 * 解决批量删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		extCproductService.delete(ExtCproduct.class, model);
		return tocreate();
	}
}
