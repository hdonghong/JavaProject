package team.webstore.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 商品的实体
 * @author Lenovo
 *
 */
public class Product {
	private Long pid;
	private String pname;		// 商品名称
	private double market_price;// 市场价
	private double shop_price;	// 商城价
	private String pimage;		// 商品图片
	private String pdesc;		// 商品描述
	private Integer pcount;		// 总销售量
	private Integer state;		// 是否可用 1：可用；0：被删除；
	private Long create_at;
	private Long update_at;
	private Category category;	// 商品类型
	@JSONField(serialize=false)
	private Set<OrderItem> orderItems = new HashSet<>();
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Double getTotal() {
		return this.pcount*this.shop_price;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	
	public Integer getPcount() {
		return pcount;
	}
	
	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Long create_at) {
		this.create_at = create_at;
	}

	public Long getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Long update_at) {
		this.update_at = update_at;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdesc=" + pdesc + ", pcount=" + pcount + ", state=" + state
				+ ", create_at=" + create_at + ", update_at=" + update_at + ", category=" + category + "]";
	}
	
}
