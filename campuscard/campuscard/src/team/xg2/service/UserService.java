package team.xg2.service;

import team.xg2.domain.PageBean;
import team.xg2.domain.User;

public interface UserService {

	void add(User user) throws Exception;

	User findByUsernameAndPwd(String username, String password) throws Exception;

	int findByUsername(String username) throws Exception;

	void update(User user) throws Exception;

	PageBean<User> findUsers(int currPage, int pageSize, String begin, String end, String username, String state) throws Exception;

	User findByUid(String uid) throws Exception;

	void delete(String uid) throws Exception;

}
