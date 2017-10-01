package pers.hdh.dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pers.hdh.beans.User;
import pers.hdh.dao.UserDao;
import pers.hdh.utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void add(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user (uid, stuid, name, password, institute, major, grade, email, category) " +
                    "values (?,?,?,?,?,?,?,?,?) ";
        qr.update(sql, user.getUid(), user.getStuid(), user.getName(), user.getPassword(), user.getInstitute(),
                user.getMajor(), user.getGrade(), user.getEmail(), user.getCategory());
    }

    @Override
    public User getByStuidAndPWD(String stuid, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select uid, stuid, name, password, institute, major, grade, email, category, create_at, update_at " +
                "from user where stuid=? and password=? limit 1 ";
        return qr.query(sql, new BeanHandler<User>(User.class), stuid, password);
    }

    @Override
    public void update(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set stuid=?, name=?, password=?, institute=?, major=?, grade=?, email=?, category=?" +
                    "where uid=? ";
        qr.update(sql, user.getStuid(), user.getName(), user.getPassword(), user.getInstitute(),
                user.getMajor(), user.getGrade(), user.getEmail(), user.getCategory(), user.getUid());
    }
}
