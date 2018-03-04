package team.xg2.service;

import team.xg2.domain.Bill;
import team.xg2.domain.PageBean;
import team.xg2.domain.User;

public interface BillService {

	PageBean<Bill> findBills(int currPage, int pageSize, User user, String begin, String end, String condition) throws Exception;

	void add(Bill bill) throws Exception;

	PageBean<Bill> findBills(int currPage, int pageSize, String begin, String end, String username, String flag) throws Exception;

	Bill findByBid(String bid) throws Exception;

	void update(Bill bill) throws Exception;

	void delete(String bid) throws Exception;

}
