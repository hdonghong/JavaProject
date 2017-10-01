package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;

public interface TaskService {
    PageBean<Task> getTasks(User user, String category, String desc, int currPage, int pageSize);
}
