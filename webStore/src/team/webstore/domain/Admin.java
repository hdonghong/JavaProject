package team.webstore.domain;

/**
 * 管理员实体
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:32:41 
 */
public class Admin {

	private Long aid;
	private String adminname;
	private String password;
	
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
