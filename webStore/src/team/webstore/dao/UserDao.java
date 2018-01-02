package team.webstore.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import team.webstore.domain.User;

/**
 * 用户的持久层，将用户的数据持久化到数据库中
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:26:02 
 */
public interface UserDao {

	/**
	 * 用户注册，将用户信息写入user表
	 * @param user
	 */
	void save(User user);

	/**
	 * 使用username值查询user表是否已存在该值
	 * @param user
	 */
	User checkRepeatedUser(User user);

	/**
	 * 使用用户的用户名和密码匹配user表是否存在此用户
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 修改用户个人资料
	 * @param user
	 * @return 
	 */
	User update(User user);

	/**
	 * 通过主键uid从数据库中获取一条记录
	 * @param uid
	 * @return
	 */
	User getById(Long uid);

	/**
	 * 身份验证。通过用户名和绑定的邮箱查询用户表
	 * @param user
	 * @return
	 */
	User authenticate(User user);

	/**
	 * 条件查询数据量
	 * @param criteria 条件
	 * @return
	 */
	int getTotalCount(DetachedCriteria criteria);

	/**
	 * 分页条件查询user表所有用户
	 * @param criteria 条件
	 * @param currPage 当前页
	 * @param pageSize 页面数据量
	 * @return 记录集合
	 */
	List<User> getBeanListByPage(DetachedCriteria criteria, Integer currPage, int pageSize);

}
