package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 添加用户，往user表写入一条记录
     * @param user
     */
    void add(User user) throws SQLException;

    /**
     * 通过stuid和password从user表获取一条记录
     * @param stuid
     * @param password
     * @return
     * @throws SQLException
     */
    User getByStuidAndPWD(String stuid, String password) throws SQLException;

    /**
     * 修改user表的一条记录
     * @param user
     * @throws SQLException
     */
    void update(User user) throws SQLException;

    /**
     * 分页查询user表记录
     * @param stuid
     * @param name
     * @param category
     * @param currPage
     * @param pageSize
     * @return
     */
    PageBean<User> getUsers(String stuid, String name, String category, int currPage, int pageSize) throws SQLException;

    /**
     * 通过uid查询用户
     * @param uid
     * @return
     */
    User getByUid(String uid) throws SQLException;

    /**
     * 通过uid删除用户
     * @param uid
     */
    void delete(String uid) throws SQLException;
}
