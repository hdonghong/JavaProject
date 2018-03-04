package team.xg2.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.xg2.dao.UserDao;
import team.xg2.domain.User;
import team.xg2.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * user表添加数据
	 */
	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), 
				user.getEmail(), user.getBirthday(), user.getSex(), user.getTime(), user.getBalance(), user.getState());
	}

	/**
	 * user表查询数据，通过账号和密码
	 */
	@Override
	public User findByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username, password);
	}

	/**
	 * user表查询数据，通过账号
	 */
	@Override
	public int findByUsername(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username) != null ?
				1 : 0;
	}

	/**
	 * 更新user表
	 */
	@Override
	public void update(User user) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update user set username=?, password=?, name=?, email=?, birthday=?, sex=?, time=?, balance=?, state=?"
				+ " where uid=?";
		qr.update(DataSourceUtils.getConnection(), sql, user.getUsername(), user.getPassword(), user.getName(), 
				user.getEmail(), user.getBirthday(), user.getSex(), user.getTime(), user.getBalance(), user.getState(), user.getUid());
	}

	/**
	 * 条件查询user表
	 */
	@Override
	public List<User> findUsers(int currPage, int pageSize, String begin, String end, String username, String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select * from user where time between ? and ? and username like ? ";
		if (!(state==null || state.trim().length()==0)) {
			sql += " and state="+state;
		}
		sql += " order by time desc limit ?, ? ";
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		
		return qr.query(sql, new BeanListHandler<>(User.class), begin, end, "%"+username+"%", (currPage-1)*pageSize, pageSize);
	}

	/**
	 * 查询user表数据条数
	 */
	@Override
	public int getCount(String begin, String end, String username, String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select count(*) from user where time between ? and ? and username like ? ";
		if (!(state==null || state.trim().length()==0)) {
			sql += " and state="+state;
		}
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		return ((Long)qr.query(sql, new ScalarHandler<>(), begin, end, "%"+username+"%")).intValue();
	}

	/**
	 * user表查询数据，通过id
	 */
	@Override
	public User findByUid(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where uid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), uid);
	}

	/**
	 * 删除user表数据
	 */
	@Override
	public void delete(String uid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from user where uid = ? ";
		qr.update(DataSourceUtils.getConnection(),sql, uid);
	}

}
