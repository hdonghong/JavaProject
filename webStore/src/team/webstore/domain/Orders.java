package team.webstore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

//@JsonIgnoreProperties(value={"orderItems"})   //破除子对象往回级联输出
public class Orders implements  Serializable{
	private static final long serialVersionUID = 1L;

	private String oid;
	private Double total;	 // 订单总计
	private Integer state; //订单状态 0:未支付   1:已支付
	private String address;
	private String name;
	private String telephone;
	private Long create_at;
	private Long update_at;
	// 多对一，多个订单对应一个用户
	private User user;
	//包含哪些订单项，一对多
	@JSONField(serialize=false)
	private Set<OrderItem> orderItems = new HashSet<>();
	
	
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
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
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
	
	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", total=" + total + ", state=" + state + ", address=" + address + ", name="
				+ name + ", telephone=" + telephone + ", create_at=" + create_at + ", update_at=" + update_at
				+ ", user=" + user + ", orderItems=" + orderItems + "]";
	}
}
