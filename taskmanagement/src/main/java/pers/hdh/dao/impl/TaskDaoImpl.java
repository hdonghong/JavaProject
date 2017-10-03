package pers.hdh.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;
import pers.hdh.dao.TaskDao;
import pers.hdh.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    /**
     * 用户页面：获取总记录数
     * @param user
     * @param category
     * @param desc
     * @param state
     * @return
     * @throws SQLException
     */
    @Override
    public int getTotalCount(User user, String category, String desc, String state) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = " SELECT COUNT(*) FROM task t LEFT JOIN " +
                " (SELECT tid, state FROM record r WHERE uid = ?) temp " +
                " ON t.tid=temp.tid where t.category like ? and t.desc like ? ";
        if (state != null && state.trim().length() > 0) {
            sql += " and IFNULL(temp.state, 0) = " + state;
        }

        return ((Long)qr.query(sql, new ScalarHandler<>(), user.getUid(), "%"+category.trim()+"%", "%"+desc.trim()+"%")).intValue();
    }

    /**
     * 管理员页面：获取总记录数
     * @param category
     * @param desc
     * @return
     */
    @Override
    public int getTotalCount(String category, String desc) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql =  " SELECT COUNT(*) FROM task WHERE category LIKE ? AND `desc` LIKE ? ";
        return ((Long)qr.query(sql, new ScalarHandler<>(), "%"+category.trim()+"%", "%"+desc.trim()+"%")).intValue();
    }

    /**
     * 用户页面：分页查询数据
     * @param user
     * @param category
     * @param desc
     * @param state
     * @param currPage
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<Task> getTasks(User user, String category, String desc, String state, int currPage, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = " SELECT t.tid, t.category, t.desc, t.create_at, IFNULL(temp.state,0) state FROM task t LEFT JOIN " +
                " (SELECT tid, state FROM record r WHERE uid = ?) temp " +
                " ON t.tid=temp.tid where t.category like ? and t.desc like ? ";
        if (state != null && state.trim().length() > 0) {
            sql += " and IFNULL(temp.state,0) = " + state;
        }
        sql += " order by create_at desc limit ?, ? ";

        return qr.query(sql, new BeanListHandler<Task>(Task.class), user.getUid(), "%"+category.trim()+"%",
                "%"+desc.trim()+"%", (currPage-1)*pageSize, pageSize);
    }

    /**
     * 管理员页面：分页查询数据
     * @param category
     * @param desc
     * @param currPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Task> getTasks(String category, String desc, int currPage, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " SELECT tid, category, `desc`, create_at, update_at FROM task " +
                " WHERE category LIKE ? AND `desc` LIKE ? ORDER BY create_at DESC LIMIT ?, ? ";
        return qr.query(sql, new BeanListHandler<Task>(Task.class), "%"+category.trim()+"%",
                "%"+desc.trim()+"%", (currPage-1)*pageSize, pageSize);
    }

    /**
     * 通过主键查询任务
     * @param tid
     * @return
     */
    @Override
    public Task getByTid(String tid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select tid, category, `desc`, create_at, update_at from task where tid=? ";
        return qr.query(sql, new BeanHandler<>(Task.class), tid);
    }

    /**
     * 修改task表的一条记录
     * @param task
     */
    @Override
    public void update(Task task) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " update task set category=?, `desc`=? where tid=? ";
        qr.update(sql, task.getCategory(), task.getDesc(), task.getTid());
    }

    /**
     * 添加一个记录到task表
     * @param task
     */
    @Override
    public void add(Task task) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into task (tid, category, `desc`) values (?, ?, ?)";
        qr.update(sql, task.getTid(), task.getCategory(), task.getDesc());
    }

    /**
     * 删除task表的一条记录
     * @param tid
     */
    @Override
    public void delete(String tid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from task where tid=? limit 1 ";
        qr.update(sql, tid);
    }
}
