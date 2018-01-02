package team.webstore.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import team.webstore.dao.AdminDao;
import team.webstore.domain.Admin;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:45:02 
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	@Override
	public Admin login(Admin admin) {
		List<Admin> list = (List<Admin>) this.getHibernateTemplate().
				find("from Admin where adminname=? and password=?", admin.getAdminname(), admin.getPassword());
		return list != null && list.size() > 0 ?
				list.get(0) : null;
	}

}
