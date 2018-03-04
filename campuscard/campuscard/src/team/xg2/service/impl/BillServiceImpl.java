package team.xg2.service.impl;

import java.util.List;

import team.xg2.dao.BillDao;
import team.xg2.domain.Bill;
import team.xg2.domain.PageBean;
import team.xg2.domain.User;
import team.xg2.service.BillService;
import team.xg2.utils.BeanFactory;

public class BillServiceImpl implements BillService {

	/**
	 * 通过创建时间段，关键词查询账单
	 */
	@Override
	public PageBean<Bill> findBills(int currPage, int pageSize, User user, String begin, String end, String condition) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		//条件查询bill数据
		List<Bill> list = dao.findBills(currPage, pageSize, user, begin, end, condition);
		//查询bill总条数
		int totalCount = dao.getCount(user, begin, end, condition);
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 添加账单
	 */
	@Override
	public void add(Bill bill) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		dao.add(bill);
		bill.getUser().setBalance((bill.getUser().getBalance()+bill.getMoney()));//更新session中的user
	}

	/**
	 * 通过创建时间段，用户名，消费形式查询账单
	 */
	@Override
	public PageBean<Bill> findBills(int currPage, int pageSize, String begin, String end, String username, String flag) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		//获取当前页面符合条件的用户列表
		List<Bill> list = dao.findBills(currPage, pageSize, begin, end, username, flag);
		
		//获取符合条件的用户总数
		int totalCount = dao.getCount(begin, end, username, flag);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 通过账单id查询账单
	 */
	@Override
	public Bill findByBid(String bid) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		return dao.findByBid(bid);
	}

	/**
	 * 更新账单数据
	 */
	@Override
	public void update(Bill bill) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		dao.update(bill);
	}

	/**
	 * 删除bill表数据
	 */
	@Override
	public void delete(String bid) throws Exception {
		BillDao dao = (BillDao) BeanFactory.getBean("BillDao");
		dao.delete(bid);		
	}

}
