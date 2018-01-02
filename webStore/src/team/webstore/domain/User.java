package team.webstore.domain;


/**
 * 用户实体
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:34:05 
 */
public class User {

	private Long uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private Integer state;
	private Long create_at;
	private Long update_at;
	
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
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
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", state=" + state + ", create_at=" + create_at + ", update_at=" + update_at + "]";
	}
	
}
