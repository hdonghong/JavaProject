package team.webstore.service.impl;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

import team.webstore.dao.UserDao;
import team.webstore.domain.Orders;
import team.webstore.domain.PageBean;
import team.webstore.domain.User;
import team.webstore.service.UserService;
import team.webstore.utils.EmailUtils;
import team.webstore.utils.MD5Utils;
import team.webstore.utils.SecretUtil;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:26:02 
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void save(User user) {
		// 密码加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		// 设为可用状态(1)
		user.setState(1);
		// 调用持久层保存数据
		userDao.save(user);
	}
	
	@Override
	public User login(User user) {
		// 密码加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		// 调用持久层查询用户
		return userDao.login(user);
	}

	@Override
	public User checkRepeatedUser(User user) {
		return userDao.checkRepeatedUser(user);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public User getById(Long uid) {
		return userDao.getById(uid);
	}

	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public boolean authenticate(User user) {
		// 进行身份验证
		user = userDao.authenticate(user);
		
		if (user != null) {
			// 实现邮件发送功能
			try {
				// 设置邮件信息
				String subject = "WEB商城用户找回密码"; // 邮件主题
//				String from = "799108252@qq.com";  // 发送者
				String from = EmailUtils.getValue("mail.default.from");  // 发送者
				String to = user.getEmail();       // 接收者
				String content = EmailUtils.       // 邮件内容
						getMailCapacity(to, SecretUtil.encrypt(user.getUid().toString()), user.getUsername());
				// 准备发送
				MimeMessage message = mailSender.createMimeMessage(); // 创建一个邮件信息对象
				MimeMessageHelper helper = new MimeMessageHelper(message);
				helper.setFrom(from);
				helper.setSubject(subject);
				helper.setText(content, true);     // true表示可以解析html代码
				helper.setTo(to);
				// 发送邮件
				mailSender.send(message);      
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public PageBean<User> findByPage(DetachedCriteria criteria, Integer currPage, int pageSize) {
		// 先查询用户总数量
		criteria.setProjection(Projections.rowCount());
		int totalCount = userDao.getTotalCount(criteria);
		if (!(totalCount > 0)) {
			return null;
		}
		// 清空COUNT(*)条件
		criteria.setProjection(null);
		// 获取分页的订单列表
		List<User> beanList = userDao.getBeanListByPage(criteria, currPage, pageSize);
		
		return new PageBean<>(currPage, totalCount, pageSize, beanList);
	}
	
}
