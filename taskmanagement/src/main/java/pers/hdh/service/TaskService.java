package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;

import java.sql.SQLException;

public interface TaskService {
    PageBean<Task> getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException;

    PageBean<Task> getTasks(String category, String desc, int currPage, int pageSize) throws SQLException;

    Task getByTid(String tid) throws SQLException;

    void update(Task task) throws SQLException;

    void add(Task task) throws SQLException;

    void delete(String tid) throws SQLException;
}
