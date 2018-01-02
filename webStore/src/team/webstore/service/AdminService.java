package team.webstore.service;

import team.webstore.domain.Admin;

/**
 * 管理员模块的业务逻辑层
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:41:22 
 */
public interface AdminService {

	/**
	 * 管理员登录
	 */
	Admin login(Admin admin);
	
}
