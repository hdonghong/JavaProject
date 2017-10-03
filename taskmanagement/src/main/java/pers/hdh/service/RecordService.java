package pers.hdh.service;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Record;

import java.sql.SQLException;

public interface RecordService {
    int update(Record record) throws SQLException;

    void add(Record record) throws SQLException;

    PageBean<Record> getRecords(String category, String desc, String state, String stuid, int currPage, int pageSize) throws SQLException;
}
