package pers.hdh.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.hdh.beans.User;
import pers.hdh.dao.UserDao;
import pers.hdh.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 添加用户，往user表写入一条记录
     * @param user
     */
    @Override
    public void add(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " insert into user (uid, stuid, name, password, institute, major, grade, email, category) " +
                    " values (?,?,?,?,?,?,?,?,?) ";
        qr.update(sql, user.getUid(), user.getStuid(), user.getName(), user.getPassword(), user.getInstitute(),
                user.getMajor(), user.getGrade(), user.getEmail(), user.getCategory());
    }

    /**
     * 通过stuid和password从user表获取一条记录
     * @param stuid
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public User getByStuidAndPWD(String stuid, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select uid, stuid, name, password, institute, major, grade, email, category, create_at, update_at " +
                " from user where stuid=? and password=? limit 1 ";
        return qr.query(sql, new BeanHandler<User>(User.class), stuid, password);
    }

    /**
     * 修改user表的一条记录
     * @param user
     * @throws SQLException
     */
    @Override
    public void update(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " update user set stuid=?, name=?, password=?, institute=?, major=?, grade=?, email=?, category=? " +
                    " where uid=? ";
        qr.update(sql, user.getStuid(), user.getName(), user.getPassword(), user.getInstitute(),
                user.getMajor(), user.getGrade(), user.getEmail(), user.getCategory(), user.getUid());
    }

    /**
     * 分页查询user表记录
     * @param stuid
     * @param name
     * @param category
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getUsers(String stuid, String name, String category, int currPage, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        // 模糊查询
        String sql = " select uid, stuid, name, password, institute, major, grade, email, category, create_at " +
                " from user where stuid like ? and name like ? and category like ? order by create_at desc limit ?, ? ";

        return qr.query(sql, new BeanListHandler<>(User.class), "%"+stuid.trim()+"%",
                "%"+name.trim()+"%", "%"+category.trim()+"%", (currPage-1)*pageSize, pageSize);
    }

    /**
     * 查询user表总记录数
     * @param stuid
     * @param name
     * @param category
     * @return
     */
    @Override
    public int getTotalCount(String stuid, String name, String category) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select count(*) from user where stuid like ? and name like ? and category like ? ";
        return ((Long)qr.query(sql, new ScalarHandler<>(), "%"+stuid.trim()+"%",
                "%"+name.trim()+"%", "%"+category.trim()+"%")).intValue();
    }

    /**
     * 通过uid查询user表获取一条记录
     * @param uid
     * @return
     */
    @Override
    public User getByUid(String uid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "  select uid, stuid, name, password, institute, major, grade, email, category, create_at " +
                    " from user where uid=? ";
        return qr.query(sql, new BeanHandler<User>(User.class), uid);
    }

    /**
     * 删除用户
     * 使用user表的uid作为外键的record表在DB中将此uid置空值
     * @param uid
     */
    @Override
    public void delete(String uid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from user where uid=? ";
        qr.update(sql, uid);
    }

    /**
     * 通过用户账号和邮箱查询用户
     * @param stuid
     * @param email
     * @return
     */
    @Override
    public User getByStuidAndEmail(String stuid, String email) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select uid, stuid, name, password, institute, major, grade, email, category, create_at " +
                " from user where stuid=? and email=? ";
        return qr.query(sql, new BeanHandler<User>(User.class), stuid, email);
    }
}
