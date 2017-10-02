package pers.hdh.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
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
     * 获取总记录数
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
     * 分页查询数据
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
}
