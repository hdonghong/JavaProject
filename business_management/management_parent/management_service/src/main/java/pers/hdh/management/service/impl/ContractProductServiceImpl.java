package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.domain.ContractProduct;
import pers.hdh.management.domain.ExtCproduct;
import pers.hdh.management.service.ContractProductService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ContractProductServiceImpl	
 * @Description	合同下产品的业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ContractProductServiceImpl implements ContractProductService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ContractProduct entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
			// 取出原来货物的总金额
			double oldAmount = 
					entity.getAmount() == null ? 0d : entity.getAmount();
			// 修改货物总金额
			entity.setAmount(entity.getPrice() * entity.getCnumber());
			// 取出货物所属的购销合同
			Contract contract = baseDao.get(Contract.class, entity.getContract().getId());
			// 修改购销合同总金额
			contract.setTotalAmount(contract.getTotalAmount() - oldAmount + entity.getAmount());
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.delete(entityClass, baseDao.get(entityClass, id));
		}
	}

	@Override
	public void delete(Class<ContractProduct> entityClass, ContractProduct model) {
		// 获取该货物对象
		ContractProduct contractProduct = baseDao.get(ContractProduct.class, model.getId());
		// 获取该货物所属购销合同对象
		Contract contract = contractProduct.getContract();
		// 获取该货物下的附件
		Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
		
		// 修改购销合同总额
		for (ExtCproduct extCproduct : extCproducts)
			contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
		contract.setTotalAmount(contract.getTotalAmount() - contractProduct.getAmount());
		
		// 保存到数据库中
		baseDao.saveOrUpdate(contract);
		// 删除货物，级联删除其附件
		baseDao.deleteById(entityClass, model.getId());
	}

}
