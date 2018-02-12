package pers.hdh.management.action.cargo;

import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ModelDriven;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.domain.Export;
import pers.hdh.management.domain.ExportProduct;
import pers.hdh.management.domain.User;
import pers.hdh.management.service.ContractService;
import pers.hdh.management.service.ExportProductService;
import pers.hdh.management.service.ExportService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

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
	private ExportProductService exportProductService;
	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		User currUser = super.getCurrUser();// 当前用户
		Integer degree = currUser.getUserinfo().getDegree();// 用户级别
		String hql = "from Export where 1=1 ";
/*		if (degree == 4) {// 员工
			hql += " and createBy='" + currUser.getId() + "'";
		} else if (degree == 3) {// 部门经理
			hql += " and createDept='" + currUser.getDept().getId() + "'";
		} else if (degree == 2) {// 管理本部门和下属部门
			
		} else if (degree == 1) {// 副总
			
		} else if (degree == 0) {// 总经理
			
		}*/
		
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
		return "contractList";
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
		return contractList();
	}
	
	/**
	 * 进入修改页面
	 * addTRRecord(mRecordTable,id,productNo,cnumber,grossWeight,netWeight,sizeLength,sizeWidth,sizeHeight,exPrice,tax)
	 * @return
	 * @throws Exception
	 */
	public String toupdate() throws Exception {
		// 根据id获取一个对象
		Export entity = exportService.get(Export.class, model.getId());
		// 放入值栈
		super.push(entity);
		// 获取报运单下的商品
		Set<ExportProduct> exportProducts = entity.getExportProducts();
		// 拼接查询的字符串
		StringBuilder mRecordData = new StringBuilder();
		for (ExportProduct ep : exportProducts) {
			mRecordData.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId());
			mRecordData.append("\", \"").append(ep.getProductNo());
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getCnumber()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()));
			mRecordData.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		super.put("mRecordData", mRecordData.toString());
		// 跳页面
		return "toupdate";
	}

	private String[] mr_id;
	private String[] mr_changed;
	private Integer[] mr_cnumber;
	private Double[] mr_grossWeight;
	private Double[] mr_netWeight;
	private Double[] mr_sizeLength;
	private Double[] mr_sizeWidth;
	private Double[] mr_sizeHeight;
	private Double[] mr_exPrice;
	private Double[] mr_tax;
	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}
	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}
	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}
	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}
	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}
	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}
	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}
	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}
	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}
	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}
	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id获取一个对象
		Export entity = exportService.get(Export.class, model.getId());
		// 设置需要修改的属性
		entity.setInputDate(model.getInputDate());
		entity.setLcno(model.getLcno());// 信用证号
		entity.setConsignee(model.getConsignee());// 收货人及地址
		entity.setShipmentPort(model.getShipmentPort());// 装运港
		entity.setDestinationPort(model.getDestinationPort());// 目的港
		entity.setTransportMode(model.getTransportMode());// 运输方式
		entity.setPriceCondition(model.getPriceCondition());// 价格条件
		entity.setMarks(model.getMarks());// 唛头，指具有一定格式的备注信息
		entity.setRemark(model.getRemark());// 备注
		
		Set<ExportProduct> epSet = new HashSet<>();
		for (int i = 0; i < mr_id.length; i++) {
			// 遍历数组，得到每个商品对象
			ExportProduct ep = exportProductService.get(ExportProduct.class, mr_id[i]);
			// 加入集合中
			epSet.add(ep);
			// 如果有修改就更新数据
			if ("1".equals(mr_changed[i])) {
				ep.setCnumber(mr_cnumber[i]);
				ep.setGrossWeight(mr_grossWeight[i]);
				ep.setNetWeight(mr_netWeight[i]);
				ep.setSizeLength(mr_sizeLength[i]);
				ep.setSizeWidth(mr_sizeWidth[i]);
				ep.setSizeHeight(mr_sizeHeight[i]);
				ep.setExPrice(mr_exPrice[i]);
				ep.setTax(mr_tax[i]);
			}
			// 更新到报运单
			entity.setExportProducts(epSet);
		}
		
		// 保存修改
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
	 * 提交报运单
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
	 * 取消报运单
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
