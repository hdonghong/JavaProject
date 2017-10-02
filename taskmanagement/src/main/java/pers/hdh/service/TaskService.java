package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.User;

import java.sql.SQLException;

public interface TaskService {
    PageBean getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException;
}
