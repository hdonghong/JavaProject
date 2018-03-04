package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {

	/**
	 * 用户注册
	 * @throws Exception 
	 */
	@Override
	public void regist(User user) throws Exception {
		UserDao dao = new UserDaoImpl();
		dao.add(user);
		
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), "<a href='http://localhost:8080/store/user?method=active&code="
				+ user.getCode() + "'>激活账户</a>");
	}

	/**
	 * 用户激活
	 * @throws Exception 
	 */
	@Override
	public User active(String code) throws Exception {
		UserDao dao = new UserDaoImpl();
		//1.通过激活码获取user
		User user = dao.getByCode(code);
		
		if (user == null) { //查找不到用户
			return null;
		}
		
		//2.如果user!=null,修改用户状态
		user.setState(1);
		dao.update(user);
		
		return user;
	}

	/**
	 * 账户登录
	 */
	@Override
	public User login(String username, String password) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.getByUsernameAndPwd(username, password);
	}

}
