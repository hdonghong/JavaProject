package pers.hdh.management.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName	User	
 * @Description USER_P表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/01 08:47:15
 */
public class User extends BaseEntity {

	private static final long serialVersionUID = -7220122522365048356L;
	
	private String id;
	private Dept dept;// 用户与部门 多对一
	private Userinfo userinfo; // 用户与扩展信息 一对一
	private Set<Role> roles = new HashSet<>(); // 用户与角色 多对多
	private String userName;// 用户名
	private String password;// 密码 加密
	private Integer state;// 状态

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
