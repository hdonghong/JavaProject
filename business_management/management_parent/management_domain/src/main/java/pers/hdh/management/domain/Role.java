package pers.hdh.management.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName	Role	
 * @Description Role_P表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/01 08:47:15
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = -7220122522365048356L;
	
	private String id;
	
	private Set<User> users = new HashSet<>();// 角色与用户 多对多
	private Set<Module> modules = new HashSet<>();// 角色与模块 多对多
	private String name;// 角色名
	private String remark;// 备注
	private Integer orderNo;// 排序号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
}
