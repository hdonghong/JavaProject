package team.webstore.service.impl;

import team.webstore.dao.AdminDao;
import team.webstore.domain.Admin;
import team.webstore.service.AdminService;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:42:34 
 */
public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}

}
