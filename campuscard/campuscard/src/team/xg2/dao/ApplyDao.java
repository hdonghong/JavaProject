package team.xg2.dao;

import java.util.List;

import team.xg2.domain.Apply;

public interface ApplyDao {

	void add(Apply apply) throws Exception;

	List<Apply> findApplys(int currPage, int pageSize, String begin, String end, String username) throws Exception;

	int getCount(String begin, String end, String username) throws Exception;

	Apply findByAid(String aid) throws Exception;

	void update(Apply apply) throws Exception;

	void delete(String aid) throws Exception;

	void deleteUid(String uid) throws Exception;

}
