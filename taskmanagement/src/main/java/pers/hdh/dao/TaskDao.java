package pers.hdh.dao;

import pers.hdh.beans.Task;
import pers.hdh.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    int getTotalCount(User user, String category, String desc, String state) throws SQLException;

    List<Task> getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException;
}
