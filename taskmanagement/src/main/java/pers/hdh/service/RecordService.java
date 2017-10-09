package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Record;
import pers.hdh.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface RecordService {
    int update(Record record) throws SQLException;

    void add(Record record) throws SQLException;

    PageBean<Record> getRecords(String category, String desc, String state, String stuid, int currPage, int pageSize) throws SQLException;

    PageBean<Record> getRecords(int currPage, int pageSize, User user) throws SQLException;

    Record getRecord(String rid) throws SQLException;

    List<Record> getRecords(User user) throws SQLException;
}
