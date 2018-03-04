package team.xg2.service.impl;

import java.util.List;

import team.xg2.dao.ApplyDao;
import team.xg2.dao.UserDao;
import team.xg2.domain.Apply;
import team.xg2.domain.PageBean;
import team.xg2.service.ApplyService;
import team.xg2.utils.BeanFactory;
import team.xg2.utils.DataSourceUtils;

public class ApplyServiceImpl implements ApplyService {
	
	/**
	 * apply表的增添和user表的更新操作
	 */
	@Override
	public void add(Apply apply, int state) throws Exception {
		try {
			ApplyDao apply_dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
			UserDao user_dao = (UserDao) BeanFactory.getBean("UserDao");
			//使用事务处理机制
			//开启事务
			DataSourceUtils.startTransaction();
			//往apply表插入数据
			apply_dao.add(apply);
			//更新user表
			apply.getUser().setState(state);
			user_dao.update(apply.getUser());
			//事务提交
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			//事务回滚
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	/**
	 * 通过创建时间段，用户名查询申请
	 */
	@Override
	public PageBean<Apply> findApplys(int currPage, int pageSize, String begin, String end, String username)
			throws Exception {
		ApplyDao dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
		//获取当前页面符合条件的用户列表
		List<Apply> list = dao.findApplys(currPage, pageSize, begin, end, username);
		
		//获取符合条件的用户总数
		int totalCount = dao.getCount(begin, end, username);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 通过aid查询挂失记录
	 */
	@Override
	public Apply findByAid(String aid) throws Exception {
		ApplyDao dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
		return dao.findByAid(aid);
	}

	/**
	 * 更新apply表数据
	 */
	@Override
	public void update(Apply apply) throws Exception {
		ApplyDao dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
		dao.update(apply);
	}

	/**
	 * 删除apply表数据
	 */
	@Override
	public void delete(String aid) throws Exception {
		ApplyDao dao = (ApplyDao) BeanFactory.getBean("ApplyDao");
		dao.delete(aid);		
	}

}
