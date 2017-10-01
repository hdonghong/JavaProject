package pers.hdh.service.impl;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;
import pers.hdh.service.TaskService;

public class TaskServiceImpl implements TaskService {
    @Override
    public PageBean<Task> getTasks(User user, String category, String desc, int currPage, int pageSize) {
        return null;
    }
}
