package pers.hdh.service;

import pers.hdh.beans.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 添加用户，往user表写入一条记录
     * @param user
     */
    void add(User user) throws SQLException;

    User getByStuidAndPWD(String stuid, String password) throws SQLException;

    void update(User user) throws SQLException;
}
