package com.future.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 22:27
 */
public class SqlSessionUtil {

    private SqlSessionUtil() {

    }

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        final InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession openSession() {
        return SESSION_FACTORY.openSession();
    }

}
