package pers.hdh.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.hdh.beans.Record;
import pers.hdh.beans.User;
import pers.hdh.dao.RecordDao;
import pers.hdh.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        String sql = "update record set state=?, is_read=? where 1=1 ";

        List<Object> paramsList = new ArrayList<>();
        paramsList.add(record.getState());
        paramsList.add(record.getIs_read());
        if (record.getRid()!=null) { // 通过主键确认记录
            sql += " and rid=? ";
            paramsList.add(record.getRid());
        } else { // 或者通过两个外键确认记录
            sql += " and uid=? and tid=? ";
            paramsList.add(record.getUser().getUid());
            paramsList.add(record.getTask().getTid());
        }
        return qr.update(sql, paramsList.toArray());
    }

    /**
     * 获取总数据量
     * @param category
     * @param desc
     * @param state
     * @param stuid
     * @return
     */
    @Override
    public int getTotalCount(String category, String desc, String state, String stuid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM record r, `user` u, task t WHERE r.uid=u.uid AND r.tid=t.tid " +
                " AND t.category LIKE ? AND t.`desc` LIKE ? AND r.state LIKE ? AND u.stuid LIKE ? ";
        return ((Long)qr.query(sql, new ScalarHandler<>(), "%"+category.trim()+"%", "%"+desc.trim()+"%",
                "%"+state+"%", "%"+stuid.trim()+"%")).intValue();
    }

    /**
     * 分页查询
     * @param category
     * @param desc
     * @param state
     * @param stuid
     *@param currPage
     * @param pageSize   @return
     */
    @Override
    public List<Record> getRecords(String category, String desc, String state, String stuid, int currPage, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT r.rid, r.state,r.create_at, r.update_at, u.stuid, t.category, t.`desc` " +
                " FROM record r, `user` u, task t WHERE r.uid=u.uid AND r.tid=t.tid " +
                " AND t.category LIKE ? AND t.`desc` LIKE ? AND r.state LIKE ? AND u.stuid LIKE ? " +
                " ORDER BY r.update_at DESC LIMIT ?, ? ";
        return qr.query(sql, new BeanListHandler<Record>(Record.class), "%"+category.trim()+"%", "%"+desc.trim()+"%",
                "%"+state+"%", "%"+stuid.trim()+"%", (currPage-1)*pageSize, pageSize);
    }

    /**
     * 查询该用户完成或失败的任务记录总数
     * @param user
     * @return
     */
    @Override
    public int getTotalCount(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from record where uid= ? and state in (3,4) ";
        return ((Long)qr.query(sql, new ScalarHandler<>(), user.getUid())).intValue();
    }

    /**
     * 分页查询该用户完成或失败的任务记录
     * @param currPage
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public List<Record> getRecords(int currPage, int pageSize, User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select rid, state, update_at, is_read from record where uid = ? and state in (3,4) " +
                " order by update_at desc limit ?, ?";
        return qr.query(sql, new BeanListHandler<>(Record.class), user.getUid(), (currPage-1)*pageSize, pageSize);
    }

    /**
     * 获取一条记录
     * @param rid
     */
    @Override
    public Record getRecord(String rid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from record where rid = ? limit 1";
        return qr.query(sql, new BeanHandler<Record>(Record.class), rid);
    }

    /**
     * 查找该用户所有任务记录
     * @param user
     * @return
     */
    @Override
    public List<Record> getRecords(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from record where uid = ? ";
        return qr.query(sql, new BeanListHandler<Record>(Record.class), user.getUid());
    }
}
