package pers.hdh.management.domain;

import java.util.Date;

/**
 * @ClassName	Userinfo	
 * @Description USER_INFO_P表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/01 08:47:15
 */
public class Userinfo extends BaseEntity {

	private static final long serialVersionUID = -7220122522365048356L;
	
	private String id;
	private String name;// 姓名
	private User manager;// 用户与直属领导 多对一
	private Date joinDate;// 入职时间
	private String salary;// 薪水
	private Date birthday;// 出生年月
	private String gender;// 性别
	private String station;// 岗位
	private String telephone;// 电话
	private Integer degree;// 等级
	private String remark;// 备注
	private Integer orderNo;// 排序号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
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
	
}
