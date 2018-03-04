package com.itheima.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Order implements  Serializable{
	private static final long serialVersionUID = 1L;

	private String oid;
	private Date ordertime;
	private Double total;
	private Integer state=0; //订单状态 0:未支付   1:已支付
	private String address;
	private String name;
	private String telephone;
	//属于哪个用户
	private User user;
	//包含哪些订单项
	private List<OrderItem> orderItems = new LinkedList<>();
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
