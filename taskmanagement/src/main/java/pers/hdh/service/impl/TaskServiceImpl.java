package pers.hdh.service.impl;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;
import pers.hdh.dao.TaskDao;
import pers.hdh.service.TaskService;
import pers.hdh.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    /**
     * 分页查询用户页面展示的所有任务
     * @param user
     * @param category
     * @param desc
     * @param state
     *@param currPage
     * @param pageSize   @return
     */
    @Override
    public PageBean<Task> getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");

        // 获取总记录数
        int totalCount = dao.getTotalCount(user, category, desc, state);
        // 获取当前页记录
        List<Task> list = dao.getTasks(user, category, desc, state, currPage, pageSize);

        return new PageBean<>(list, currPage, pageSize, totalCount);
    }

    /**
     * 分页查询管理员页面展示的所有任务
     * @param category
     * @param desc
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Task> getTasks(String category, String desc, int currPage, int pageSize) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");

        // 获取总记录数
        int totalCount = dao.getTotalCount(category, desc);
        // 获取当前页记录
        List<Task> list = dao.getTasks(category, desc, currPage, pageSize);

        return new PageBean<>(list, currPage, pageSize, totalCount);
    }

    /**
     * 通过主键获取任务
     * @param tid
     * @return
     */
    @Override
    public Task getByTid(String tid) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");
        return dao.getByTid(tid);
    }

    /**
     * 修改任务信息
     * @param task
     */
    @Override
    public void update(Task task) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");
        dao.update(task);
    }

    /**
     * 发布任务
     * @param task
     */
    @Override
    public void add(Task task) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");
        dao.add(task);
    }

    /**
     * 删除任务
     * @param tid
     */
    @Override
    public void delete(String tid) throws SQLException {
        TaskDao dao = (TaskDao) BeanFactory.getBean("TaskDao");
        dao.delete(tid);
    }
}
