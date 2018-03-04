package team.xg2.dao;

import java.util.List;

import team.xg2.domain.Bill;
import team.xg2.domain.User;

public interface BillDao {

	List<Bill> findBills(int currPage, int pageSize, User user, String begin, String end, String condition) throws Exception;

	List<Bill> findBills(int currPage, int pageSize, String begin, String end, String username, String flag) throws Exception;
	
	void add(Bill bill) throws Exception;

	int getCount(User user, String begin, String end, String condition) throws Exception;

	int getCount(String begin, String end, String username, String flag) throws Exception;

	Bill findByBid(String bid) throws Exception;

	void update(Bill bill) throws Exception;

	void delete(String bid) throws Exception;

	void deleteUid(String uid) throws Exception;


}
