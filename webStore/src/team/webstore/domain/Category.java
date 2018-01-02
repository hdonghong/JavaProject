package team.webstore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 分类实体
 * @author Lenovo
 */
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long cid;
	private String cname;
	private Integer state;
	
	// 一对多
	@JSONField(serialize=false)
	private Set<Product> products = new HashSet<>();
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	// 该分类下的销售总额
	public Double getTotal() {
		Double total=0.0;
		if (products != null) {
			for (Product product : products) {
				if (product.getState() == 1)
					total += (product.getPcount()*product.getShop_price());
			}
		}
		return total; 
	}
	// 该分类下的销售总量
	public Integer getCount() {
		Integer count=0;
		if (products != null) {
			for (Product product : products) {
				if (product.getState() == 1)
					count += product.getPcount();
			}
		}
		return count;
	}
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
