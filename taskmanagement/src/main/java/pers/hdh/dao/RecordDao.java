package pers.hdh.dao;

import pers.hdh.beans.Record;

import java.sql.SQLException;

public interface RecordDao {
    void add(Record record) throws SQLException;

    int update(Record record) throws SQLException;
}
