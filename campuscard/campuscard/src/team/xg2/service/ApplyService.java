package team.xg2.service;

import team.xg2.domain.Apply;
import team.xg2.domain.PageBean;

public interface ApplyService {

	void add(Apply apply, int state) throws Exception;

	PageBean<Apply> findApplys(int currPage, int pageSize, String begin, String end, String username) throws Exception;

	Apply findByAid(String aid) throws Exception;

	void update(Apply apply) throws Exception;

	void delete(String aid) throws Exception;

}
