package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.Contract;
import pers.hdh.management.domain.ExtCproduct;
import pers.hdh.management.service.ExtCproductService;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	ExtCproductServiceImpl	
 * @Description	合同下附件的业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class ExtCproductServiceImpl implements ExtCproductService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ExtCproduct entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())) {
			// 取出原来货物的总金额
			double oldAmount = 
					entity.getAmount() == null ? 0d : entity.getAmount();
			// 修改附件总金额
			entity.setAmount(entity.getPrice() * entity.getCnumber());
			// 取出附件所属的货物的购销合同
			Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
			// 修改购销合同总金额
			contract.setTotalAmount(contract.getTotalAmount() - oldAmount + entity.getAmount());
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

	@Override
	public void delete(Class<ExtCproduct> entityClass, ExtCproduct model) {
		// 得到附件对象
		ExtCproduct extCproduct = baseDao.get(entityClass, model.getId());
		// 得到所属的购销合同对象
		Contract contract = baseDao.get(Contract.class, model.getContractProduct().getContract().getId());
		// 修改购销合同总金额
		contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
		// 保存
		baseDao.saveOrUpdate(contract);
		// 删除附件
		this.deleteById(entityClass, extCproduct.getId());
	}

}
