package team.xg2.service.impl;

import java.util.List;

import team.xg2.dao.ApplyDao;
import team.xg2.dao.BillDao;
import team.xg2.dao.UserDao;
import team.xg2.domain.PageBean;
import team.xg2.domain.User;
import team.xg2.service.UserService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.DataSourceUtils;

public class UserServiceImpl implements UserService {

	/**
	 * 用户注册：往user表添加一个用户
	 */
	@Override
	public void add(User user) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		dao.add(user);
	}

	/**
	 * 用户登录：通过账号和密码查询user表
	 */
	@Override
	public User findByUsernameAndPwd(String username, String password) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		return dao.findByUsernameAndPwd(username, password);
	}

	/**
	 * 防止重用账号：通过账号查询user表
	 * @return 
	 */
	@Override
	public int findByUsername(String username) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		return dao.findByUsername(username);
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public void update(User user) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		dao.update(user);		
	}

	/**
	 * 查询用户
	 */
	@Override
	public PageBean<User> findUsers(int currPage, int pageSize, String begin, String end, String username, String state) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		//获取当前页面符合条件的用户列表
		List<User> list = dao.findUsers(currPage, pageSize, begin, end, username, state);
		
		//获取符合条件的用户总数
		int totalCount = dao.getCount(begin, end, username, state);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 通过id查询user表
	 */
	@Override
	public User findByUid(String uid) throws Exception {
		UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
		return dao.findByUid(uid);
	}

	/**
	 * 删除user表数据
	 */
	@Override
	public void delete(String uid) throws Exception {
		try {
			//开启事务处理
			DataSourceUtils.startTransaction();
			BillDao bill_dao = (BillDao) BeanFactory.getBean("BillDao");
			ApplyDao apply_dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
			UserDao user_dao = (UserDao) BeanFactory.getBean("UserDao");
			//以uid为外键的表采取置空值删除
			bill_dao.deleteUid(uid);
			apply_dao.deleteUid(uid);
			user_dao.delete(uid);
			//提交事务
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose(); //事务回滚
			throw e;
		}
	}

}
