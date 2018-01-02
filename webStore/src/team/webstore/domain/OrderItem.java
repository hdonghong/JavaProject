package team.webstore.domain;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long itemid;
	private Integer count;
	private Double subtotal;
	private String comment;		// 商品评价
	private Long create_at;
	private Long update_at;
	//包含哪种商品
//	@JSONField(serialize=false)
	private Product product;
	//属于哪个订单
	private Orders order;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = 
				comment==null || comment.trim().equals("") ? "评价方未做出评价，系统默认好评。" : comment;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
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
}
