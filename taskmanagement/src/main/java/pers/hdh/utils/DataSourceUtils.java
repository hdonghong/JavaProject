package pers.hdh.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 操作数据库的工具类
 */
public class DataSourceUtils {
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 从线程中获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 当线程中获取connection
        Connection conn = tl.get();
        if (conn == null) {
            conn = ds.getConnection();
            // 将连接与当前线程绑定
            tl.set(conn);
        }
        return conn;
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
