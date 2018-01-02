package team.webstore.service;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.PageBean;
import team.webstore.domain.User;

/**
 * 用户模块的业务逻辑层
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:26:02 
 */
public interface UserService {

	/**
	 * 用户注册，将新用户信息进行处理后传递给持久层
	 * @param user
	 */
	void save(User user);

	/**
	 * 用户登录，通过用户名和用户密码登录用户，需要将密码进行加密
	 * @return 返回一个user对象
	 */
	User login(User user);

	/**
	 * 检查是否已存在用户名
	 * @param user
	 * @return user对象
	 */
	User checkRepeatedUser(User user);

	/**
	 * 修改用户个人资料
	 * @param user
	 * @return 
	 */
	User update(User user);

	/**
	 * 通过主键uid获取一个user对象
	 * @param uid 作为查询数据库条件
	 * @return user对象
	 */
	User getById(Long uid);

	/**
	 * 验证身份，正确则发送邮件
	 * @param user
	 * @return
	 */
	boolean authenticate(User user);

	/**
	 * 分页按条件查询用户
	 * @param criteria 查询条件
	 * @param currPage 当前页面
	 * @param pageSize 页面数据量
	 * @return 返回一个pageBean封装多个记录
	 */
	PageBean<User> findByPage(DetachedCriteria criteria, Integer currPage, int pageSize);
	
}
