package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.domain.ContractProduct;
import pers.hdh.management.domain.Export;
import pers.hdh.management.domain.ExportProduct;
import pers.hdh.management.domain.ExtCproduct;
import pers.hdh.management.domain.ExtEproduct;
import pers.hdh.management.service.ExportService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ExportServiceImpl	
 * @Description	业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ExportServiceImpl implements ExportService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Export entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
			// 设置报运单的状态
			entity.setState(0);
			
			// 修改购销合同状态
			String[] contractIds = entity.getContractIds().split(", ");
			StringBuilder contractNos = new StringBuilder();
			for (String id : contractIds) {
				Contract contract = baseDao.get(Contract.class, id);
				contract.setState(2);// 已报运
				baseDao.saveOrUpdate(contract);// 更新状态
				contractNos.append(contract.getContractNo()).append(" ");
			}
			// 设置合同及确认书号
			entity.setCustomerContract(contractNos.toString());
			entity.setContractIds(entity.getContractIds().replaceAll(" ", ""));
			entity.setInputDate(new Date());// 制单日期
			// 通过购销合同的id集合，跳跃查询出购销合同下的货物列表
			String hql = "from ContractProduct where contract.id in ("+UtilFuns.joinInStr(contractIds) + ")";
			List<ContractProduct> ContProtList = baseDao.find(hql, ContractProduct.class, null);
			// 报运单添加报运商品
			Set<ExportProduct> expProductsSet = new HashSet<>();
			for (ContractProduct cp : ContProtList) {
				ExportProduct ep = new ExportProduct();
				
				// 将货物的数据迁移到报运单下报运商品明细
				ep.setBoxNum(cp.getBoxNum());
				ep.setCnumber(cp.getCnumber());
				ep.setPrice(cp.getPrice());
				ep.setFactory(cp.getFactory());
				ep.setOrderNo(cp.getOrderNo());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setProductNo(cp.getProductNo());
				ep.setExport(entity);// 商品和报运单 多对一
				
				// 报运商品添加附件列表
				Set<ExtEproduct> extEproductsSet = new HashSet<>(); 
				Set<ExtCproduct> extCproductsSet = cp.getExtCproducts();
				for (ExtCproduct extC : extCproductsSet) {
					ExtEproduct extE = new ExtEproduct();
					// 将附件的数据迁移到报运单下附件列表中
					// 拷贝相同属性的值，选择导入org.springframework.beans.BeanUtils
					BeanUtils.copyProperties(extC, extE);
					extE.setId(null);// 注意置空
					extE.setExportProduct(ep);// 附件和商品 多对一
					extEproductsSet.add(extE);
				}
				
				ep.setExtEproducts(extEproductsSet);
				expProductsSet.add(ep);
			}
			entity.setExportProducts(expProductsSet);
			
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Export> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Export> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

	@Override
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Export entity = baseDao.get(Export.class, id);
			entity.setState(state);
			baseDao.saveOrUpdate(entity);
		}
	}

}
