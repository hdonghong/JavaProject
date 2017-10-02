package pers.hdh.service.impl;

import pers.hdh.beans.Record;
import pers.hdh.dao.RecordDao;
import pers.hdh.service.RecordService;
import pers.hdh.utils.BeanFactory;

import java.sql.SQLException;

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
}
