package team.xg2.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 挂失申请记录的表实体
 * @author Lenovo
 *
 */
public class Apply implements Serializable{
	private static final long serialVersionUID = 1L;
	private String aid;
	private String adesc;
	private Date time;
	private User user;
	private String username; //添加一个javabean对象，方便使用
	
	public Apply(String aid, String adesc, Date time, User user) {
		super();
		this.aid = aid;
		this.adesc = adesc;
		this.time = time;
		this.user = user;
	}
	public Apply() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAdesc() {
		return adesc;
	}
	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
