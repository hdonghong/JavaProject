package team.webstore.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import team.webstore.dao.ProductDao;
import team.webstore.domain.Product;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午7:06:08 
 */
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	@Override
	public List<Product> findHotProducts(int listSize) {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		hibernateTemplate.setMaxResults(listSize);
		return (List<Product>) hibernateTemplate.find("from Product p inner join fetch p.category c where p.state=1 and c.state=1 order by p.pcount desc");
	}

	@Override
	public List<Product> findNewProducts(int listSize) {
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		hibernateTemplate.setMaxResults(listSize);
		return (List<Product>) hibernateTemplate.find("from Product p inner join fetch p.category c where p.state=1 and c.state=1 order by p.create_at desc");
	}

	@Override
	public Product getById(Long pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	@Override
	public int getTotalCountByCategory(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		return list != null && list.size() > 0 ?
				list.get(0).intValue() : 0;
	}

	@Override
	public List<Product> getBeanListByCategory(Integer currPage, int pageSize, DetachedCriteria criteria) {
		return (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, (currPage-1)*pageSize, pageSize);
	}

	@Override
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	@Override
	public void update(Product product) {
		Product existedProduct = this.getHibernateTemplate().get(product.getClass(), product.getPid());
		if (existedProduct != null) {
			if (product.getPname() != null && product.getPname().trim().length() > 0)
				existedProduct.setPname(product.getPname());
			if (product.getCategory() != null)
				existedProduct.setCategory(product.getCategory());
			if (product.getMarket_price() > 0)
				existedProduct.setMarket_price(product.getMarket_price());;
			if (product.getShop_price() > 0)
				existedProduct.setShop_price(product.getShop_price());
			if (product.getPimage() != null && product.getPimage().trim().length() > 0)
				existedProduct.setPimage(product.getPimage());
			if (product.getPdesc() != null && product.getPdesc().trim().length() > 0)
				existedProduct.setPdesc(product.getPdesc());
			if (product.getState() != null && product.getState() >= 0 )
				existedProduct.setState(product.getState());
		}
	}

	
}
