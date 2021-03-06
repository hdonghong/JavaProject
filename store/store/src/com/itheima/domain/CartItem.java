package com.itheima.domain;

import java.io.Serializable;

/**
 * 购物车项实体
 * @author Lenovo
 *
 */
public class CartItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private Integer count;
	private Double subtotal;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}
	
}
