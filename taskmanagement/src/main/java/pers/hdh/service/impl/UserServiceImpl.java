package pers.hdh.service.impl;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.User;
import pers.hdh.dao.UserDao;
import pers.hdh.service.UserService;
import pers.hdh.utils.BeanFactory;
import pers.hdh.utils.MailUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    /**
     * 添加用户，往user表写入一条记录
     * @param user
     */
    @Override
    public void add(User user) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        dao.add(user);
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
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        return dao.getByStuidAndPWD(stuid, password);
    }

    /**
     * 修改user表的一条记录
     * @param user
     * @throws SQLException
     */
    @Override
    public void update(User user) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        dao.update(user);
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
    public PageBean<User> getUsers(String stuid, String name, String category, int currPage, int pageSize) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        // 分页查询记录
        List<User> list = dao.getUsers(stuid, name, category, currPage, pageSize);
        // 查询总记录数
        int totalCount = dao.getTotalCount(stuid, name, category);

        return new PageBean<>(list, currPage, pageSize, totalCount);
    }

    /**
     * 通过uid查询用户
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public User getByUid(String uid) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        return dao.getByUid(uid);
    }

    /**
     * 通过uid删除用户
     * @param uid
     */
    @Override
    public void delete(String uid) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        dao.delete(uid);
    }

    /**
     * 删除多个用户
     * @param uids
     */
    @Override
    public void delete(String[] uids) throws SQLException {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        for (String uid: uids) {
            dao.delete(uid);
        }
    }

    /**
     * 申请修改密码
     * @param stuid
     * @param email
     * @return
     */
    @Override
    public User getByStuidAndEmail(String stuid, String email) throws Exception {
        UserDao dao = (UserDao) BeanFactory.getBean("UserDao");
        User user = dao.getByStuidAndEmail(stuid, email);
        if (user != null) {
            MailUtils.sendMail(user.getEmail(), user.getUid());
        }
        return user;
    }
}
