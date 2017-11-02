package pers.hdh.service.impl;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Record;
import pers.hdh.beans.User;
import pers.hdh.dao.RecordDao;
import pers.hdh.service.RecordService;
import pers.hdh.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    /**
     * 修改record表数据
     * @param record
     * @return
     */
    @Override
    public int update(Record record) throws SQLException {
        RecordDao dao = (RecordDao) BeanFactory.getBean("RecordDao");
        return dao.update(record);
    }

    /**
     * 添加记录到record表
     * @param record
     */
    @Override
    public void add(Record record) throws SQLException {
        RecordDao dao = (RecordDao) BeanFactory.getBean("RecordDao");
        dao.add(record);
    }

    /**
     * 分页查询record表
     * @param category
     * @param desc
     * @param state
     * @param stuid
     *@param currPage
     * @param pageSize   @return
     */
    @Override
    public PageBean<Record> getRecords(String category, String desc, String state, String stuid, int currPage, int pageSize) throws SQLException {
        RecordDao dao = (RecordDao) BeanFactory.getBean("RecordDao");
        // 查询总记录数
        int totalCount = dao.getTotalCount(category, desc, state, stuid);
        // 获取记录
        List<Record> list = dao.getRecords(category, desc, state, stuid, currPage, pageSize);
        return new PageBean<>(list, currPage, pageSize, totalCount);
    }

    /**
     * 分页查询record表中state是3或4的
     * @param currPage
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public PageBean<Record> getRecords(int currPage, int pageSize, User user) throws SQLException {
        RecordDao dao = (RecordDao) BeanFactory.getBean("RecordDao");
        // 查询总记录数
        int totalCount = dao.getTotalCount(user);
        // 获取记录
        List<Record> list = dao.getRecords(currPage, pageSize, user);
        return new PageBean<>(list, currPage, pageSize, totalCount);
    }

    /**
     * 通过id获取一条记录
     * @param rid
     * @return
     */
    @Override
    public Record getRecord(String rid) throws SQLException {
        RecordDao dao = (RecordDao) BeanFactory.getBean("RecordDao");
        return dao.getRecord(rid);
    }

    /**
     * 查询该用户的所有任务记录
     * @param user
     * @return
     */
    @Override
    public List<Record> getRecords(User user) throws SQLException {
        return ((RecordDao) BeanFactory.getBean("RecordDao")).getRecords(user);
    }

    /**
     * 查看新消息
     * @param user
     * @return
     */
    @Override
    public int getNews(User user) throws SQLException {
        return ((RecordDao) BeanFactory.getBean("RecordDao")).getNews(user);
    }
}
