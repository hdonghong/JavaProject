package team.webstore.dao;

import team.webstore.domain.Admin;

/**
 * 管理员模块的持久层
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:42:47 
 */
public interface AdminDao {

	/**
	 * 通过管理员账号和密码获取一条记录
	 * @param admin
	 */
	Admin login(Admin admin);
}
