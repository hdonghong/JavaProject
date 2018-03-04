package team.xg2.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单表实体
 * @author Lenovo
 *
 */
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private String bid;
	private String bdesc;
	private Double money=0.0;
	private Date time;
	private User user;
	private String username; //提供一个javabean属性，方便获取
	
	public Bill(String bid, String bdesc, Double money, Date time, User user) {
		super();
		this.bid = bid;
		this.bdesc = bdesc;
		this.money = money;
		this.time = time;
		this.user = user;
	}
	public Bill() {
		super();
	}
	
	public String getUsername() { //用作javabean
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBdesc() {
		return bdesc;
	}
	public void setBdesc(String bdesc) {
		this.bdesc = bdesc;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
