package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * 用户注册
	 * @param user
	 * @throws SQLException 
	 */
	@Override
	public void add(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
	}

	/**
	 * 通过激活码获取user
	 * @throws SQLException 
	 */
	@Override
	public User getByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where code = ?";
		return qr.query(sql, new BeanHandler<>(User.class), code);
	}

	/**
	 * 用户修改
	 */
	@Override
	public void update(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set username=?, password=?, name=?, email=?, birthday=?, sex=?, state=?, code=? where uid=?";
		qr.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday(),
				user.getSex(), user.getState(), null, user.getUid());
	}

	/**
	 * 用户登录
	 */
	@Override
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username, password);
	}

}
