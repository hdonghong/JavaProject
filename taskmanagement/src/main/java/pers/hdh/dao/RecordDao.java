package pers.hdh.dao;

import pers.hdh.beans.Record;
import pers.hdh.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface RecordDao {
    void add(Record record) throws SQLException;

    int update(Record record) throws SQLException;

    int getTotalCount(String category, String desc, String state, String stuid) throws SQLException;

    List<Record> getRecords(String category, String desc, String state, String stuid, int currPage, int pageSize) throws SQLException;

    int getTotalCount(User user) throws SQLException;

    List<Record> getRecords(int currPage, int pageSize, User user) throws SQLException;

    Record getRecord(String rid) throws SQLException;

    List<Record> getRecords(User user) throws SQLException;

    int getNews(User user) throws SQLException;
}
