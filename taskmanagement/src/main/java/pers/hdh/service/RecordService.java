package pers.hdh.service;

import pers.hdh.beans.Record;

import java.sql.SQLException;

public interface RecordService {
    int update(Record record) throws SQLException;

    void add(Record record) throws SQLException;
}
