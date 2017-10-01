package pers.hdh.service.impl;

import pers.hdh.beans.User;
import pers.hdh.dao.UserDao;
import pers.hdh.dao.impl.UserDaoImpl;
import pers.hdh.service.UserService;
import pers.hdh.utils.BeanFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    @Override
    public void add(User user) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        dao.add(user);
    }

    @Override
    public User getByStuidAndPWD(String stuid, String password) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        return dao.getByStuidAndPWD(stuid, password);
    }

    @Override
    public void update(User user) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        dao.update(user);
    }
}
