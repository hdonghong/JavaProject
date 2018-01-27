package pers.hdh.management.domain;

import java.io.Serializable;

/**
 * @ClassName	Dept	
 * @Description:TODO
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/27 08:47:15
 */
public class Dept implements Serializable{

	private static final long serialVersionUID = -7220122522365048356L;
	
	private String id;
	private String deptName;// 部门名称
	private Dept parent;	// 自关联，子部门与父部门，多对一
	private Integer state;	// 状态，1代表启用，0代表停用
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
