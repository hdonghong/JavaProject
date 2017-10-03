package pers.hdh.dao;

import pers.hdh.beans.Task;
import pers.hdh.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    int getTotalCount(User user, String category, String desc, String state) throws SQLException;

    int getTotalCount(String category, String desc) throws SQLException;

    List<Task> getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException;

    List<Task> getTasks(String category, String desc, int currPage, int pageSize) throws SQLException;

    Task getByTid(String tid) throws SQLException;

    void update(Task task) throws SQLException;

    void add(Task task) throws SQLException;

    void delete(String tid) throws SQLException;
}
