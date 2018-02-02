package pers.hdh.management.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName	Module	
 * @Description MODULE_P表实体
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/01 08:47:15
 */
public class Module extends BaseEntity {

	private static final long serialVersionUID = -7220122522365048356L;
	
	private String id;
	
	private Set<Role> roles = new HashSet<>();// 模块与角色 多对多
	private String parentId;// 父模块的编号
	private String parentName;// 父模块的名称 冗余 用空间换时间
	private String name;// 模块名
	private Integer layerNum;// 层数
	private Integer isBeaf;// 是否叶子节点
	private String ico;// 展示图标
	private String cpermission;// 权限标识
	private String curl;// 链接
	private Integer ctype;// 菜单的类型：主菜单，左侧菜单，按钮
	private Integer state;// 状态
	private String belong;// 从属
	private String cwhich;// 复用标识
	private Integer quoteNum;// 引用次数
	private String remark;// 备注说明
	private Integer orderNo;// 排序号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLayerNum() {
		return layerNum;
	}
	public void setLayerNum(Integer layerNum) {
		this.layerNum = layerNum;
	}
	public Integer getIsBeaf() {
		return isBeaf;
	}
	public void setIsBeaf(Integer isBeaf) {
		this.isBeaf = isBeaf;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getCpermission() {
		return cpermission;
	}
	public void setCpermission(String cpermission) {
		this.cpermission = cpermission;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getCwhich() {
		return cwhich;
	}
	public void setCwhich(String cwhich) {
		this.cwhich = cwhich;
	}
	public Integer getQuoteNum() {
		return quoteNum;
	}
	public void setQuoteNum(Integer quoteNum) {
		this.quoteNum = quoteNum;
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
