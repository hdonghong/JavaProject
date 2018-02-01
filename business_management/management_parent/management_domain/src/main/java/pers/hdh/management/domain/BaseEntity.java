package pers.hdh.management.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName	BaseEntity	
 * @Description	通用domain类
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/01 15:03:27
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4718045743252472792L;
	
	private String createBy;// 创建者的id
	private String createDept;// 创建者所在部门的id
	private String createTime;// 创建时间
	private String updateBy;// 更新者的id
	private Date updateTime;// 更新时间
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
