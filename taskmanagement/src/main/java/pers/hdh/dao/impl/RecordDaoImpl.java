package pers.hdh.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import pers.hdh.beans.Record;
import pers.hdh.dao.RecordDao;
import pers.hdh.utils.DataSourceUtils;

import java.sql.SQLException;

public class RecordDaoImpl implements RecordDao {
    /**
     * 添加记录到record表
     * @param record
     */
    @Override
    public void add(Record record) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " insert into record (rid, state, uid, tid) values (?, ?, ?, ? )";
        qr.update(sql, record.getRid(), record.getState(), record.getUser().getUid(), record.getTask().getTid());
    }

    /**
     * 修改record表记录
     * @param record
     * @return
     */
    @Override
    public int update(Record record) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update record set state=? where uid=? and tid=? ";
        return qr.update(sql, record.getState(), record.getUser().getUid(), record.getTask().getTid());
    }
}
