package pers.hdh.management.domain;

/**
 * @ClassName	Factory	
 * @Description	生产厂家，FACTORY_C表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/07 19:49:05
 */
public class Factory extends BaseEntity {

	private static final long serialVersionUID = 5666383207431052595L;

	private String id;
	private String ctype;      // 厂家类型，货物/附件
	private String fullName;   // 厂家全程
	private String factoryName;// 厂家简称
	private String contacts;   // 联系人
	private String phone;      // 电话
	private String mobile;     // 手机
	private String fax;        // 传真
	private String address;    // 地址
	private String inspector;  // 验货员
	private String remark;     // 说明
	private Integer orderNo;   // 排序号
	private Integer state;     // 状态：1=正常；0=停用（假删除）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
