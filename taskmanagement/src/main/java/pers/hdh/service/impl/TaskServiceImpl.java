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
     * 分页查询任务
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
}
