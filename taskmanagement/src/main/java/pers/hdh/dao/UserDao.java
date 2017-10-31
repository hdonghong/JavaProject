package pers.hdh.dao;

import pers.hdh.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void add(User user) throws SQLException;

    User getByStuidAndPWD(String stuid, String password) throws SQLException;

    void update(User user) throws SQLException;

    List<User> getUsers(String stuid, String name, String category, int currPage, int pageSize) throws SQLException;

    int getTotalCount(String stuid, String name, String category) throws SQLException;

    User getByUid(String uid) throws SQLException;

    void delete(String uid) throws SQLException;

    User getByStuidAndEmail(String stuid, String email) throws SQLException;
}
