package team.webstore.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import team.webstore.dao.UserDao;
import team.webstore.domain.Orders;
import team.webstore.domain.User;
import team.webstore.utils.MD5Utils;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:26:02 
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User checkRepeatedUser(User user) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User where username=?", user.getUsername());
		return (userList == null || userList.size() <= 0) ?
				null : userList.get(0);
	}

	@Override
	public User login(User user) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User where username=? and password=? and state=1",
						user.getUsername(), user.getPassword());
		return (userList == null || userList.size() <= 0) ?
				null : userList.get(0);
	}

	@Override
	public User update(User user) {
		if (user.getUid() == null) return null;
		// 先用uid从数据库中获取user
		User exitedUser = this.getHibernateTemplate().get(User.class, user.getUid());
		
		if (exitedUser != null) { // 更新数据
			if (user.getName() != null && user.getName().trim().length() > 0)
				exitedUser.setName(user.getName());
			if (user.getEmail() != null && user.getEmail().trim().length() > 0)
				exitedUser.setEmail(user.getEmail());
			if (user.getPassword() != null && !user.getPassword().equals("")) { // 如果用户修改了密码，对密码加密
				exitedUser.setPassword(MD5Utils.md5(user.getPassword()));
			}
			if (user.getState() != null && user.getState() >= 0) {
				exitedUser.setState(user.getState());
			}
			// 持久化到数据库中
			this.getHibernateTemplate().save(exitedUser);
			return exitedUser;
		}
		return null;
	}

	@Override
	public User getById(Long uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	@Override
	public User authenticate(User user) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User where username=? and email=? and state=1",
				user.getUsername(), user.getEmail());
		return (userList == null || userList.size() <= 0) ?
				null : userList.get(0);
	}

	@Override
	public int getTotalCount(DetachedCriteria criteria) {
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		return list != null && list.size() > 0 ?
				list.get(0).intValue() : 0;
	}

	@Override
	public List<User> getBeanListByPage(DetachedCriteria criteria, Integer currPage, int pageSize) {
		return (List<User>) this.getHibernateTemplate().findByCriteria(criteria, (currPage-1)*pageSize, pageSize);
	}

	
}
