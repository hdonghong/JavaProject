package team.xg2.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import team.xg2.dao.BillDao;
import team.xg2.domain.Bill;
import team.xg2.domain.User;
import team.xg2.utils.DataSourceUtils;

public class BillDaoImpl implements BillDao {

	/**
	 * 条件查询bill表
	 */
	@Override
	public List<Bill> findBills(int currPage, int pageSize, User user, String begin, String end, String condition) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select * from bill where uid = ? and time between ? and ?  and bdesc like ? "
				+ " order by time desc limit ?, ? ";
		//当用户查询没有输入终止日期，默认为当前查询时间
		end += (end.equals("") ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : " 23:59:59");
		
		return qr.query(sql, new BeanListHandler<>(Bill.class), user.getUid(), begin, end, "%"+condition+"%", 
						(currPage-1)*pageSize, pageSize);
	}

	/**
	 * 条件查询bill数据条数
	 */
	@Override
	public int getCount(User user, String begin, String end, String condition) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from bill where uid = ? and time between ? and ?  and bdesc like ? ";
		//当用户查询没有输入终止日期，默认为当前查询时间
		end += (end.equals("") ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) : " 23:59:59");
		return ((Long)qr.query(sql, new ScalarHandler<>(), user.getUid(), begin, end, "%"+condition+"%")).intValue();
	}
	
	/**
	 * 添加数据到bill表
	 */
	@Override
	public void add(Bill bill) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into bill values(?, ?, ?, ?, ?)";
		qr.update(sql, bill.getBid(), bill.getBdesc(), bill.getMoney(), bill.getTime(),bill.getUser().getUid());
	}

	/**
	 * 通过创建时间段，用户名，消费形式查询账单
	 */
	@Override
	public List<Bill> findBills(int currPage, int pageSize, String begin, String end, String username, String flag)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select bill.*, user.username from user, bill where bill.uid=user.uid "
				+ " and bill.time between ? and ? and user.username like ? ";
		if (!(flag==null || flag.trim().length()==0)) {
			if (flag.equals("1")) { //支出
				sql += " and bill.bdesc not like '%充值%' ";
			} else if (flag.equals("2")) { //收入
				sql += " and bill.bdesc like '%充值%' ";
			}
		}
		sql += " order by bill.time desc limit ?, ? ";
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		
		return qr.query(sql, new BeanListHandler<>(Bill.class), begin, end, "%"+username+"%", (currPage-1)*pageSize, pageSize);
	}

	/**
	 * 通过创建时间段，用户名，消费形式查询账单总数
	 */
	@Override
	public int getCount(String begin, String end, String username, String flag) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		//拼接sql语句
		String sql = "select count(*) from user, bill where bill.uid=user.uid "
				+ " and bill.time between ? and ? and user.username like ? ";
		if (!(flag==null || flag.trim().length()==0)) {
			if (flag.equals("1")) { //支出
				sql += " and bill.bdesc not like '%充值%' ";
			} else if (flag.equals("2")) { //收入
				sql += " and bill.bdesc like '%充值%' ";
			}
		}
		
		//当用户查询没有输入终止日期，默认为当前查询时间
		if (end == null || end.equals("")) {
			end = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} else {
			end += " 23:59:59";
		}
		return ((Long)qr.query(sql, new ScalarHandler<>(), begin, end, "%"+username+"%")).intValue();
	}

	/**
	 * 通过账单id查询账单
	 */
	@Override
	public Bill findByBid(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select bill.*, username from user, bill where user.uid=bill.uid and bill.bid = ? limit 1 ";
		return qr.query(sql, new BeanHandler<>(Bill.class), bid);
	}

	/**
	 * 更新账单数据
	 */
	@Override
	public void update(Bill bill) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());		
		String sql = "update bill set bdesc=?, money=?, time=? where bid= ? limit 1 ";
		qr.update(sql, bill.getBdesc(), bill.getMoney(), bill.getTime(), bill.getBid());
	}

	/**
	 * 删除bill表数据
	 */
	@Override
	public void delete(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from bill where bid = ? ";
		qr.update(sql, bid);
	}

	/**
	 * 置空值删除其外键
	 */
	@Override
	public void deleteUid(String uid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update bill set uid=null where uid = ? ";
		qr.update(DataSourceUtils.getConnection(),sql, uid);		
	}
}
