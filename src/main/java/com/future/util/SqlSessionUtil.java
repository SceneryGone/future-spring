package com.future.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Objects;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 22:27
 */
public class SqlSessionUtil {

    private SqlSessionUtil() {

    }

    private static final SqlSessionUtil INSTANCE = new SqlSessionUtil();

    public static SqlSessionUtil getInstance() {
        return INSTANCE;
    }

    private static final ThreadLocal<SqlSession> THREAD_SQL_SESSION = new ThreadLocal<>();

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        final InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 功能描述: 从当前线程获取链接
     */
    public SqlSession openSession() {
        SqlSession sqlSession = THREAD_SQL_SESSION.get();
        if (Objects.isNull(sqlSession)) {
            sqlSession = SESSION_FACTORY.openSession(false);
            THREAD_SQL_SESSION.set(sqlSession);
        }

        return sqlSession;
    }

    public void remove() {
        // 从ThreadLocal中移除
        THREAD_SQL_SESSION.remove();
    }

}
