package pers.hdh.management.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import pers.hdh.management.dao.BaseDao;
import pers.hdh.management.domain.User;
import pers.hdh.management.service.UserService;
import pers.hdh.management.utils.Encrypt;
import pers.hdh.management.utils.Page;
import pers.hdh.management.utils.SysConstant;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	UserServiceImpl	
 * @Description	用户管理的业务逻辑层
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/01/30 10:26:22
 */
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	private SimpleMailMessage mailMessage;
	private JavaMailSender mailSender;
	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// 处理业务逻辑的代码
		if (UtilFuns.isEmpty(entity.getId())) {
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserinfo().setId(id);
			
			// 加入shiro框架后补充密码加密
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));
			baseDao.saveOrUpdate(entity);
			
			// 开启新的线程发送邮件提醒新入职员工
			new Thread(() -> {
				try {
//					MailUtils.sendMessage(entity.getUserinfo().getEmail(), "新员工入职的系统账户通知", 
//						"欢迎加入本集团，您的用户名：" + entity.getUserName() + "，初始密码：" + SysConstant.DEFAULT_PASS);
					mailMessage.setTo(entity.getUserinfo().getEmail());
					mailMessage.setSubject("新员工入职的系统账户通知");
					mailMessage.setText("欢迎加入本集团，您的用户名：" + entity.getUserName() + "，初始密码：" + SysConstant.DEFAULT_PASS);
					mailSender.send(mailMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			
		} else {
			baseDao.saveOrUpdate(entity);
		}
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(entityClass, id);
		}
	}

}
