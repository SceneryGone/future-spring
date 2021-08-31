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

    private static final ThreadLocal<SqlSession> THREAD_SQL_SESSION = new ThreadLocal<>();

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        final InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 功能描述: 当前线程已经有SqlSession 说明在service事务层获取过了 直接拿即可
     */
    public SqlSession openSession() {
        final SqlSession sqlSession = THREAD_SQL_SESSION.get();
        if (Objects.nonNull(sqlSession)) {
            return sqlSession;
        }

        return SESSION_FACTORY.openSession(true);
    }

    /**
     * 功能描述: 从当前线程获取SqlSession
     */
    public SqlSession txOpenSession() {
        SqlSession sqlSession = THREAD_SQL_SESSION.get();
        if (Objects.isNull(sqlSession)) {
            sqlSession = SESSION_FACTORY.openSession(false);
            THREAD_SQL_SESSION.set(sqlSession);
        }

        return sqlSession;
    }

    /**
     * 功能描述: 从ThreadLocal中移除
     */
    public void remove() {
        THREAD_SQL_SESSION.remove();
    }


}
