package com.itheima.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String itemid;
	private Integer count;
	private Double subtotal;
	//包含哪种商品
	private Product product;
	//属于哪个订单
	private Order order;
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
