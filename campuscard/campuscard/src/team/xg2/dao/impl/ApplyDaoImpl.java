package team.xg2.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.xg2.dao.ApplyDao;
import team.xg2.domain.Apply;
import team.xg2.utils.DataSourceUtils;

public class ApplyDaoImpl implements ApplyDao {

	/**
	 * apply表添加数据
	 */
	@Override
	public void add(Apply apply) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into apply values(?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, apply.getAid(), apply.getAdesc(), apply.getTime(), apply.getUser().getUid());
	}

	/**
	 * 通过创建时间段，用户名查询申请数据
	 */
	@Override
	public List<Apply> findApplys(int currPage, int pageSize, String begin, String end, String username)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select apply.*, user.username from user, apply where apply.uid=user.uid "
				+ " and apply.time between ? and ? and user.username like ? order by apply.time desc limit ?, ? ";
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		
		return qr.query(sql, new BeanListHandler<>(Apply.class), begin, end, "%"+username+"%", (currPage-1)*pageSize, pageSize);
	}

	/**
	 * 通过创建时间段，用户名查询申请数据总数
	 */
	@Override
	public int getCount(String begin, String end, String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select count(*) from user, apply where apply.uid=user.uid "
				+ " and apply.time between ? and ? and user.username like ? ";
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		return ((Long)qr.query(sql, new ScalarHandler<>(), begin, end, "%"+username+"%")).intValue();
	}

	/**
	 * 通过aid查询挂失记录
	 */
	@Override
	public Apply findByAid(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select apply.*, username from user, apply where user.uid=apply.uid and apply.aid = ? limit 1 ";
		return qr.query(sql, new BeanHandler<>(Apply.class), aid);
	}

	/**
	 * 更新apply表数据
	 */
	@Override
	public void update(Apply apply) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());		
		String sql = "update apply set adesc=?, time=? where aid=? limit 1 ";
		qr.update(sql, apply.getAdesc(), apply.getTime(), apply.getAid());
	}

	/**
	 * 删除apply表数据
	 */
	@Override
	public void delete(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from apply where aid = ? ";
		qr.update(sql, aid);
	}

	/**
	 * 置空值删除其外键
	 */
	@Override
	public void deleteUid(String uid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update apply set uid=null where uid = ? ";
		qr.update(DataSourceUtils.getConnection(),sql, uid);		
	}

}
